package com.text.analysis.readerwriter;

import java.io.FileInputStream;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFReader {

	public static String pdfFileReader(String inFileNamePath) {
		PDFParser parser = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		PDFTextStripper pdfStripper;
		String parsedText;
		String fulltext = "";
		try {
			parser = new PDFParser(new FileInputStream(inFileNamePath));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
			String[] a = parsedText.split("\r");

			for (int k = 0; k <= a.length - 1; k++) {
				fulltext += a[k] + "\r\n";
			}

			pdDoc.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		return fulltext;
	}
}
