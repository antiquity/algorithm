package pops;

import java.util.HashMap;
import java.util.Map;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.*;


/**
 * @author Tim "Pops" Roberts
 *
 * This processor class is for JAVA ONLY.  Feel free to implement other languages if you want
 * 
 * This processor will define TWO tags that can be embedded within PopsEdit/FileEdit code template:
 *    $WRITERCODE$ - place holder for writer code - will be blank if none found
 *    $MAIN$ - place holder for where to put the main method
 * 
 * This processor will then surround the main method with cutting tags and remove them prior to 
 * returning the source to the applet
 */
public class PopsProcessor {

	/* Map used to store my tags */
	private HashMap myTags = new HashMap();
	
	/* Constants */
	private static final String WRITERCODE = "$WRITERCODE$";
	private static final String MAIN = "$MAIN$";
	
	/* Cut tags - made them VERY unique to avoid false positive matches */
	private static final String BEGINCUT = "\n// -- Begin Cutting Here of the Main Method $&%*@# --\n";
	private static final String ENDCUT = "\n// -- End Cutting Here of the Main Method $&%*@# --\n";
	
	/*
	 * PreProcess the source code
	 * 
	 * First determines if it is saved code, writer code, or nothing and stores it in $WRITERCODE$ tag
	 * Secondly builds a main method with default test cases
	 */
	public String preProcess(String source, ProblemComponentModel component, Language language, Renderer renderer) {
		
		// Set defaults for the tags in case we exit out early
		myTags.put(WRITERCODE, "");
		myTags.put(MAIN, "");
		
		// If there is source and the source is NOT equal to the default solution, return it
		if(source.length()>0 && !source.equals(component.getDefaultSolution())) return source;
		
		// Check to see if the component has any signature 
		if(!component.hasSignature()) {
			myTags.put(MAIN, "// Problem has no signature defined for it");
			return "";
		}
		
		// Get the testcases
		TestCase[] testCases = component.getTestCases();

		// Check to see if test cases are defined
		if(testCases==null || testCases.length==0) {
			myTags.put(MAIN, "// No test cases defined for this problem");
			return "";
		}
		
		// re-initialize the tags
		myTags.clear();
		myTags.put(WRITERCODE, component.getDefaultSolution());

		// Create the mainmethod
		StringBuffer mainMethod = new StringBuffer("\tpublic static void main(String[] a) {\n");
		mainMethod.ensureCapacity(5000);
		
		// Write out the examples
		for(int x=0;x<testCases.length;x++) {
			mainMethod.append("\t\tnew ");
			mainMethod.append(component.getClassName());
			mainMethod.append("().runTestCase(");
			mainMethod.append(x);
			mainMethod.append(");\n");
		}
		
		// Add the run method
		mainMethod.append("\t}\n\n\tpublic void runTestCase(int nbr) {\n\t\tswitch(nbr) {\n");
		
		// Add each individual test case
		for(int x=0;x<testCases.length;x++) {
			mainMethod.append("\t\t\tcase ");
			mainMethod.append(x);	
			mainMethod.append(" : {\n");
			addTestCase(component, mainMethod, testCases[x], x);
			mainMethod.append(" break;\n\t\t\t}\n");
		}
			
		// End the run method
		mainMethod.append("\t\t}\n\t}\n");

		// Add the comparison methods
		mainMethod.append(addCompareMethods());		
		
		// Attach our begin/end cut tags
		mainMethod.insert(0, BEGINCUT);
		mainMethod.append(ENDCUT);
			
		// Store the main method with the begin/end cut tags
		myTags.put(MAIN, mainMethod.toString());

		return "";
	}
	
	/*
	 * Creates the test case
	 */
	private static final void addTestCase(ProblemComponentModel component, StringBuffer buf, TestCase testCase, int nbr) {

		// Get the information
		DataType[] parms = component.getParamTypes();
		DataType rc = component.getReturnType();
		String[] inputs = testCase.getInput();
		String output = testCase.getOutput();				
		
		// Double check the parms...
		if(parms.length!=inputs.length) {
			System.out.println("Parms don't match inputs");
			return;
		}
		
		// Put in the check
		buf.append("\t\t\t\tcheckOutput(");
		buf.append(component.getMethodName());
		buf.append("(");

		// Decode each of the parms
		for(int x=0;x<parms.length;x++) {
			decodeValue(buf, parms[x], inputs[x]);
			if(x<parms.length-1) buf.append(", ");
		}
	
		// Ending parent
		buf.append("), ");
		decodeValue(buf, rc, output);
		buf.append(", ");
		buf.append(nbr);
		buf.append(");");
			
	}

	/*
	 * Decodes the given datatype
	 */
	private static final void decodeValue(StringBuffer buf, DataType parm, String input) {
		
		// Is it an array?
		if(parm.getDescription().endsWith("[]")) {
			buf.append("new ");
			buf.append(parm.getDescription());
			buf.append(" ");
			input = input.replace('\n',' ');
			buf.append(input);
		} else {
			try {
				// Hopefully an input of a double isn't allowed...
				
				// Is it a long or integer?
				long inputL = Long.parseLong(input);
				buf.append(input);

				// If it's outside range of integer (it's a long) - concatenate a "L" to it
				if(inputL > Integer.MAX_VALUE || inputL < Integer.MIN_VALUE) {
					buf.append("L");
				}
			} catch (NumberFormatException e) {
				// Not a numeric - simply attach it
				buf.append(input);
			}
		}
			
	}

	/*
	 * Adds all the comparison methods
	 * Probably can make this smart to only include the ones I need
	 * But then again - the full thing is cut before submitting - so why bother
	 */
	private static final String addCompareMethods() {
		StringBuffer f = new StringBuffer(1000);
		f.append("\tfinal void checkOutput(int mine, int them, int nbr) {\n");
		f.append("\t\tboolean success = (mine==them);\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(mine);\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(them);\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(long mine, long them, int nbr) {\n");
		f.append("\t\tboolean success = (mine==them);\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(mine);\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(them);\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(double mine, double them, int nbr) {\n");
		f.append("\t\tboolean success = (mine==them);\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(mine);\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(them);\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(char mine, char them, int nbr) {\n");
		f.append("\t\tboolean success = (mine==them);\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(\"'\");\n");
		f.append("\t\t\tout.append(mine);\n");
		f.append("\t\t\tout.append(\"'\");\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(\"'\");\n");
		f.append("\t\t\tout.append(them);\n");
		f.append("\t\t\tout.append(\"'\");\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(String mine, String them, int nbr) {\n");
		f.append("\t\tboolean success = (mine.equals(them));\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(\"\\\"\");\n"); 
		f.append("\t\t\tout.append(mine);\n");
		f.append("\t\t\tout.append(\"\\\"\");\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(\"\\\"\");\n");
		f.append("\t\t\tout.append(them);\n");
		f.append("\t\t\tout.append(\"\\\"\");\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(long[] mine, long[] them, int nbr) {\n");
		f.append("\t\tboolean success = (Arrays.equals(mine, them));\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<mine.length;x++) {\n");
		f.append("\t\t\t\tout.append(mine[x]);\n");
		f.append("\t\t\t\tif(x<mine.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<them.length;x++) {\n");
		f.append("\t\t\t\tout.append(them[x]);\n");
		f.append("\t\t\t\tif(x<them.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(char[] mine, char[] them, int nbr) {\n");
		f.append("\t\tboolean success = (Arrays.equals(mine, them));\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<mine.length;x++) {\n");
		f.append("\t\t\t\tout.append(mine[x]);\n");
		f.append("\t\t\t\tif(x<mine.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<them.length;x++) {\n");
		f.append("\t\t\t\tout.append(them[x]);\n");
		f.append("\t\t\t\tif(x<them.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(double[] mine, double[] them, int nbr) {\n");
		f.append("\t\tboolean success = (Arrays.equals(mine, them));\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<mine.length;x++) {\n");
		f.append("\t\t\t\tout.append(mine[x]);\n");
		f.append("\t\t\t\tif(x<mine.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<them.length;x++) {\n");
		f.append("\t\t\t\tout.append(them[x]);\n");
		f.append("\t\t\t\tif(x<them.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(int[] mine, int[] them, int nbr) {\n");
		f.append("\t\tboolean success = (Arrays.equals(mine, them));\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<mine.length;x++) {\n");
		f.append("\t\t\t\tout.append(mine[x]);\n");
		f.append("\t\t\t\tif(x<mine.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<them.length;x++) {\n");
		f.append("\t\t\t\tout.append(them[x]);\n");
		f.append("\t\t\t\tif(x<them.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		f.append("\tfinal void checkOutput(String[] mine, String[] them, int nbr) {\n");
		f.append("\t\tboolean success = (Arrays.equals(mine, them));\n");
		f.append("\t\tStringBuffer out = new StringBuffer();\n");
		f.append("\t\tout.append(\"Example \");\n");
		f.append("\t\tout.append((nbr+1));\n");
		f.append("\t\tout.append(\" - \");\n");
		f.append("\t\tout.append(success ? \"success\" : \"failure   \");\n");
		f.append("\t\tif(!success) {\n");
		f.append("\t\t\tout.append(\"Got: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<mine.length;x++) {\n");
		f.append("\t\t\t\tout.append(mine[x]);\n");
		f.append("\t\t\t\tif(x<mine.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t\tout.append(\", Expected: \");\n");
		f.append("\t\t\tout.append(\"{\");\n");
		f.append("\t\t\tfor(int x=0;x<them.length;x++) {\n");
		f.append("\t\t\t\tout.append(them[x]);\n");
		f.append("\t\t\t\tif(x<them.length-1) out.append(\", \");\n");
		f.append("\t\t\t}\n");
		f.append("\t\t\tout.append(\"}\");\n");
		f.append("\t\t}\n");
		f.append("\t\tSystem.out.println(out);\n");
		f.append("\t}\n");

		return f.toString();
	}	

	/**
	 * This method will cut the main method above out
	 */
	public String postProcess(String source, Language language) {
		// Create a buffer capable of holding the source
		StringBuffer buf = new StringBuffer(source.length());
		
		// Find the beginning (if not found - return source)
		int start = source.indexOf(BEGINCUT);
		if(start<0) return source;
		
		// Find the ending (if not found - return source)
		int end   = source.indexOf(ENDCUT);
		if(end<0) return source;
		
		// Sanity check
		if(end<=start) return source;
		
		// Cut out the text
		buf.append(source.substring(0, start));
		buf.append(source.substring(end + ENDCUT.length()));

		// Return the cut stuff
		return buf.toString();
	}

	/*
	 * This method will return my tags.  This method is ALWAYS called after preProcess()
	 * 
	 * @return a map of my tags
	 */
	public Map getUserDefinedTags() {
		return myTags;
	}	
}
