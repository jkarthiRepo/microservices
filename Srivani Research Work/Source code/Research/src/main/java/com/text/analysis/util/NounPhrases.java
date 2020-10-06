package com.text.analysis.util;

import java.util.HashSet;
import java.util.Set;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class NounPhrases {

	public Set<String> getNounPhrases(String sentence, MaxentTagger tagger) throws Exception {
		// POS Tagging

		Set<String> nounPhrases = new HashSet<String>();
		String taggedSentence = null;
		try {
			taggedSentence = tagger.tagString(sentence);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n" + "POS Output : \n" + taggedSentence);

		String taggedSentenceArr[] = taggedSentence.split(" ");
		for (String tag : taggedSentenceArr) {
			String taggerArr[] = tag.split("/");
			if (taggerArr[1].equals("NN") || taggerArr[1].equals("NNS") || taggerArr[1].equals("NNP")
					|| taggerArr[1].equals("NNPS")) {
				nounPhrases.add(taggerArr[0]);
			}
		}

		System.out.println("List of Noun Parse : " + nounPhrases);
		return nounPhrases;
	}
}
