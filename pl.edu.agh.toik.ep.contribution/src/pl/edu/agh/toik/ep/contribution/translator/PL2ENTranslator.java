package pl.edu.agh.toik.ep.contribution.translator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.toik.ep.definition.translator.ITranslator;

public class PL2ENTranslator implements ITranslator {

	private static final Map<String, String> dictionary;

	static {
		Map<String, String> temp = new HashMap<String, String>();
		temp.put("jeden", "one");
		temp.put("dwa", "two");
		temp.put("trzy", "three");
		temp.put("cztery", "four");
		temp.put("piêæ", "five");
		temp.put("szeœæ", "six");
		temp.put("siedem", "seven");
		temp.put("osiem", "eight");
		temp.put("dziewiêæ", "nine");
		temp.put("dziesiêæ", "ten");

		dictionary = Collections.unmodifiableMap(temp);
	}

	@Override
	public String translate(String word) {
		if (word == null || !dictionary.containsKey(word)) {
			return null;
		}
		return dictionary.get(word);
	}

}
