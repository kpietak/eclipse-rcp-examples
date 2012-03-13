package pl.edu.agh.toik.ep.definition;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import pl.edu.agh.toik.ep.definition.translator.ITranslator;

/**
 * This class controls all aspects of the application's execution
 */
public class Application3 implements IApplication {

	// This is the ID from your extension point
	private static final String TRANSLATOR_ID = "pl.edu.agh.toik.translator";

	private static final String NAME = "name";

	private static final String LANG_IN = "lang_in";

	private static final String LANG_OUT = "lang_out";

	private static final String[] words = { "jeden", "dwa", "trzy", "cztery",
			"piêæ", "szeœæ", "siedem", "osiem", "dziewiêæ", "dziesiêæ" };

	private static final String NO_ENTRY = "No entry found";

	private boolean running = true;

	public Object start(IApplicationContext context) throws Exception {
		System.out.println("Hello RCP World!");

		while (running) {
			translatePredefinedWords();
			Thread.sleep(10000);
		}

		return IApplication.EXIT_OK;
	}

	public void stop() {
		// nothing to do
	}

	private void translatePredefinedWords() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(TRANSLATOR_ID);

		if (config.length == 0) {
			System.out.println("There is no registered translators");
		}

		for (IConfigurationElement e : config) {
			System.out.println(String.format("Translator %s from %s to %s",
					e.getAttribute(NAME), e.getAttribute(LANG_IN),
					e.getAttribute(LANG_OUT)));

			try {
				final ITranslator t = (ITranslator) e
						.createExecutableExtension("class");
				translatePredefinedWords(t);
			} catch (CoreException ex) {
				ex.printStackTrace();
			}
		}

	}

	private void translatePredefinedWords(ITranslator t) {

		for (String word : words) {
			String translated = t.translate(word);
			System.out.println(String.format("%s -> %s", word,
					translated != null ? translated : NO_ENTRY));
		}

	}

}
