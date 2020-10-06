package com.text.analysis.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.text.analysis.model.Concept;

public class CoreUtil {

	public static Concept getConceptWithProb(String string) {
		Concept concept = new Concept();
		if (null != string && !string.isEmpty()) {
			String[] arr = string.split("\\(");
			if (arr.length == 2) {
				concept.setConcept(arr[0]);
				String probString = arr[1].replaceAll("\\)", "");
				concept.setPropobility(Double.parseDouble(String.valueOf((float) Double.parseDouble(probString))));
			}
		}
		return concept;
	}

	public static List<String> convertSingleDoc(Map<String, List<String>> documentsMap) {
		List<String> singleDoc = new ArrayList<String>();
		for (Map.Entry<String, List<String>> map : documentsMap.entrySet()) {
			List<String> valuesList = map.getValue();
			singleDoc.addAll(valuesList);
		}
		return singleDoc;
	}

	public static float normalise(float d) {
		float normaliseValue = 0.0f;
		normaliseValue = (d == 0.0 || String.valueOf(d).equals("NaN")) ? (0 + r.nextFloat() * (1 - 0)) : d;
		normaliseValue = (normaliseValue > 1.0) ? Float.parseFloat((String.valueOf(normaliseValue)
				.replaceFirst(String.valueOf(String.valueOf(normaliseValue).charAt(0)), "0"))) : normaliseValue;
		normaliseValue = (normaliseValue > 1.0) ? Float.parseFloat((String.valueOf(normaliseValue)
				.replaceFirst(String.valueOf(String.valueOf(normaliseValue).charAt(0)), "0"))) : normaliseValue;
		normaliseValue = (normaliseValue > 1.0) ? Float.parseFloat((String.valueOf(normaliseValue)
				.replaceFirst(String.valueOf(String.valueOf(normaliseValue).charAt(0)), "0"))) : normaliseValue;
		return normaliseValue;
	}

	static Random r = new Random();

	public static List<String> distinct(List<String> list) {
		list = list.stream().distinct().filter(x -> (!x.isEmpty() && null != x)).collect(Collectors.toList());
		return list;
	}

}
