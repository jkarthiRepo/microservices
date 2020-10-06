package com.text.analysis.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.tartarus.snowball.ext.PorterStemmer;

import com.text.analysis.readerwriter.FilesWriter;
import com.text.analysis.readerwriter.PDFReader;
import com.text.analysis.util.PropertiesUtil;

public class Preprocessing {

	public static boolean preprocessFiles() {
		boolean writeStatus = false;
		String inputDataPath = PropertiesUtil.getProperty("inputDataPath");
		String preprocessedDataPath = PropertiesUtil.getProperty("preprocessedDataPath");
		File dir1 = new File(inputDataPath);
		File listDir1[] = dir1.listFiles();
		for (File file1 : listDir1) {
			String inPDFContent = PDFReader.pdfFileReader(inputDataPath + "/" + file1.getName());
			String preprocessedText = Preprocessing.preprocessData(inPDFContent);
			if (!preprocessedText.isEmpty())
				writeStatus = FilesWriter.writeContent(preprocessedDataPath + "/" + file1.getName(), preprocessedText);
		}
		if (writeStatus) {
			System.out.println("All files are Preprocessed Successfully");
		} else {
			System.out.println("Getting Exception while preprocessing");
		}
		return writeStatus;
	}

	public static String preprocessData(String request) {

		String response = "";
		String stpWordsFilePath = PropertiesUtil.getProperty("stpWordsFilePath");

		// Removing stopWords
		response = RemovingStpWords(request, stpWordsFilePath);
		// System.out.println("After Removing stopWords : " + response);

		// Stemming
		// response = stemming(response);
		// System.out.println("After Stemming : " + response);

		// Removing punctuation
		response = RemovingPunctuation(response);
		// System.out.println("After Removing punctuation : " + response);

		// Removing numbers
		response = RemovingNumbers(response);
		// System.out.println("After Removing numbers : " + response);

		// Convert to lowercasing
		response = lowercasing(response);
		// System.out.println("After Convert to lowercasing : " + response);

		// Tokenization
		response = tokenizer(response);
		// System.out.println("After Tokenization : " + response);

		// Removing Small Keywords
		response = removeSmallKeyWords(response);
		// System.out.println("After removing Small Keywords : " + response);

		return response;
	}

	private static String lowercasing(String response) {
		return response.toLowerCase();
	}

	private static String RemovingNumbers(String response) {
		response = response.replaceAll("[0-9]", "");
		return response;
	}

	private static String RemovingPunctuation(String response) {
		response = response.replaceAll("[!\"#$%&'()*+,-./:;?@\\\\[\\\\]^_`'{|}~]", "");
		return response;
	}

	private static String RemovingStpWords(String request, String stpWordsFilePath) {
		List<String> stpWordsList = getStopWordsList(stpWordsFilePath);
		String strArr[] = request.split(" ");
		String sentence = "";
		for (String val : strArr) {
			if (!stpWordsList.contains(val)) {
				sentence = sentence + " " + val;
			}
		}
		return sentence;
	}

	private static List<String> getStopWordsList(String stpWordsFilePath) {
		String line = "";
		List<String> stpWordsList = new LinkedList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(stpWordsFilePath));
			while ((line = br.readLine()) != null) {
				stpWordsList.add(line.toLowerCase().trim());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stpWordsList;
	}

	public static String stemming(String input) {
		String strArr[] = input.split(" ");
		String stemmedSentence = "";
		for (String in : strArr) {
			stemmedSentence = stemmedSentence + " " + stem(in);
		}
		return stemmedSentence;
	}

	public static String stem(String input) {
		PorterStemmer state = new PorterStemmer();
		state.setCurrent(input);
		state.stem();
		return state.getCurrent();
	}

	public static String tokenizer(String input) {
		StringTokenizer defaultTokenizer = new StringTokenizer(input);
		String tokanizedSentence = "";
		while (defaultTokenizer.hasMoreTokens()) {
			tokanizedSentence = tokanizedSentence + " " + defaultTokenizer.nextToken();
		}
		return input;
	}

	private static String removeSmallKeyWords(String response) {
		String strArr[] = response.split("\\n");
		String sentence = "";
		for (String val : strArr) {
			String row = "";
			String str[] = val.split(" ");
			for (String string : str) {
				if (null != string && string.trim().length() > 5) {
					row = row + " " + string.trim();
				}
			}
			if (null != row && !row.isEmpty()) {
				sentence = sentence + "\r\n" + row.trim();
			}
			
		}
		return sentence;
	}

}
