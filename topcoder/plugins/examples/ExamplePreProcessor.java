package examples;

import com.topcoder.client.contestant.ProblemComponentModel;
import com.topcoder.shared.language.Language;
import com.topcoder.shared.problem.Renderer;

/**
 * @author Tim "Pops" Roberts
 *
 * Example preprocessor
 */
public class ExamplePreProcessor {
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
		return "// PreProcessor added line:  howdy!\n" + source;
	}
	
}
