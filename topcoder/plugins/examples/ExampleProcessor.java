package examples;

import java.util.HashMap;
import java.util.Map;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.Renderer;

/**
 * Example code processor that does really nothing much!
 */
public class ExampleProcessor {

	/*
	 * This method will process the source code prior to giving it to the plugin
	 * 
	 * @param source      the existing source (either saved source OR writer supplied source code)
	 * @param component   the component being edited
	 * @param language    the currently selected language
	 * @param renderer    the renderer used to render the component to html/text
	 * 
	 * @return should return blank if not saved code or writer supplied code
	 */
	public String preProcess(String source, ProblemComponentModel component, Language language, Renderer renderer) {
		// Create my tags, both MUST be String's
		// FileEdit/PopsEdit will ignore any key/value that are non String class
		
		// *** STRONGLY *** recommened to surround your tags with dollar signs ("$")
		// FileEdit/PopsEdit does a simple string find/replace.  Make sure your
		// tags are unique enough to only replace what you want...
		
		// If you are testing this one - make sure you define atleast one of these
		// tags in your code template in FileEdit/PopsEdit
		myTags.put("$PHONE$", "If you want to hire me - call 867-5309");
		myTags.put("$NAME$", "Jenny");
		myTags.put("$LANGUAGE$", language.getName());

		// Return nothing to allow PopsEdit/FileEdit to create the template
		return "// PreProcessor added line:  howdy!\nName: $NAME$ \nPhone: $PHONE$\n" + source;
	}
	

	/**
	 * This method will process the source code prior to giving it to the applet
	 * 
	 * @param source the source code to modify
	 * @param language    the currently selected language
	 * @return returns the source code after modifications (if any)
	 */
	public String postProcess(String source, Language language) {
		return "// PostProcessor added line:  later!" + source;
	}

	/* Map used to store my tags */
	HashMap myTags = new HashMap();
	
	/*
	 * This method will return my tags.  This method is ALWAYS called after preProcess()
	 * 
	 * @return a map of my tags
	 */
	public Map getUserDefinedTags() {
		return myTags;
	}	
}
