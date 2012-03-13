package pl.edu.agh.toik.ep.definition;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	// This is the ID from your extension point
	private static final String TRANSLATOR_ID = "pl.edu.agh.toik.translator";

	private static final String NAME = "name";

	private static final String LANG_IN = "lang_in";

	private static final String LANG_OUT = "lang_out";

	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Hello RCP World!");
		readTranslators();
		return IApplication.EXIT_OK;
	}

	public void stop() {
		// nothing to do
	}

	private void readTranslators() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(TRANSLATOR_ID);

		if (config.length == 0) {
			System.out.println("There is no registered translators");
		}

		for (IConfigurationElement e : config) {
			System.out.println(String.format("Translator %s from %s to %s",
					e.getAttribute(NAME), e.getAttribute(LANG_IN),
					e.getAttribute(LANG_OUT)));

		}

	}

}
