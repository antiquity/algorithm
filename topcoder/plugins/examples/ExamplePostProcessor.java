package examples;
import com.topcoder.shared.language.Language;
/**
 * @author Tim "Pops" Roberts
 *
 * Example post processor
 */
public class ExamplePostProcessor {
	/**
	 * This method will process the source code prior to giving it to the applet
	 * 
	 * @param source the source code to modify
	 * @param language    the currently selected language
	 * @return returns the source code after modifications (if any)
	 */
	public String postProcess(String source, Language language) {
		return "// PostProcessor added line:  later!\n" + source;
	}
}
