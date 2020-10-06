package com.text.analysis.readerwriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;
import com.text.analysis.util.PropertiesUtil;

public class Reader {

	public static List<Concept> readNounPhrases() {
		List<Concept> list = new ArrayList<Concept>();
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader("./local/NounPhrases Output/NounPhrases_Topics.txt"));
			while ((line = br.readLine()) != null) {
				if (null != line && !line.trim().isEmpty()) {
					String[] arr = line.trim().split(" ");
					String topic = "";
					for (String string : arr) {
						if (string.startsWith("Topic")) {
							topic = string.trim().split(":")[1];
						} else {
							Concept concept = CoreUtil.getConceptWithProb(string);
							concept.setTopic(topic);
							list.add(concept);
						}
					}
				}
			}
			br.close();
		} catch (Exception e) {
		}
		return list;
	}

	public static Map<String, List<String>> readPreprocessFiles() throws IOException {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String preprocessedDataPath = PropertiesUtil.getProperty("preprocessedDataPath");
		File dir1 = new File(preprocessedDataPath);
		File listDir1[] = dir1.listFiles();
		for (File file1 : listDir1) {
			String line = "";
			List<String> list = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(preprocessedDataPath+"/"+file1.getName()));
			while ((line = br.readLine()) != null) {
				if (null != line && !line.trim().isEmpty()) {
					list.add(line);
				}
			}
			br.close();
			map.put(file1.getName(), list);
		
		}
		
		return map;
	}

}
