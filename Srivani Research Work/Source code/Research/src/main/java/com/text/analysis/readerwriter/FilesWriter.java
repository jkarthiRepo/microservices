package com.text.analysis.readerwriter;

import java.io.FileWriter;
import java.io.IOException;

public class FilesWriter {

	public static boolean writeContent(String path, String preprocessedText) {
		path = path.replaceAll(".pdf", ".txt");
		try {
			FileWriter outt = new FileWriter(path);
			outt.write(preprocessedText);
			outt.close();
		} catch (IOException e) {
			return false;
		}
		return true;

	}
}
