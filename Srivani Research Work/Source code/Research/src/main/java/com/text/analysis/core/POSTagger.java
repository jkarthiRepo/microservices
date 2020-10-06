package com.text.analysis.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * POS Tagger Example in Apache OpenNLP using Java
 */
public class POSTagger {

	static String findPOStags(String string, POSTaggerME posTagger, Tokenizer tokenizer) {
		String tokens[] = tokenizer.tokenize(string);
		String tags[] = posTagger.tag(tokens);
		double probs[] = posTagger.probs();
		return tags[0] + "(" + probs[0] + ")";
	}

	public static Tokenizer tokanizer() throws IOException {
		InputStream tokenModelIn = null;
		tokenModelIn = new FileInputStream("./supportFiles/en-token.bin");
		TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
		Tokenizer tokenizer = new TokenizerME(tokenModel);
		return tokenizer;
	}

	public static POSTaggerME posTaggerME() throws IOException {
		InputStream posModelIn = null;
		posModelIn = new FileInputStream("./supportFiles/en-pos-maxent.bin");
		POSModel posModel = new POSModel(posModelIn);
		POSTaggerME posTagger = new POSTaggerME(posModel);
		return posTagger;
	}
}
