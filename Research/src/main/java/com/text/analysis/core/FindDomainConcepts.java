package com.text.analysis.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;

public class FindDomainConcepts {

	public static List<Concept> semanticFiltering(List<Concept> candidateConceptsList, List<String> mergedDocList,
			Map<String, List<String>> docMap) {
		List<Concept> list = new ArrayList<Concept>();
		for (Concept concept : candidateConceptsList) {
			float tiA = 0.0f;
			float absFrequency = mergedDocList.stream()
					.filter(x -> x.trim().toLowerCase().equals(concept.getConcept().trim().toLowerCase())).count();
			for (Map.Entry<String, List<String>> map : docMap.entrySet()) {
				List<String> valuesList = map.getValue();
				if (valuesList.stream()
						.filter(x -> x.trim().toLowerCase().equals(concept.getConcept().trim().toLowerCase()))
						.count() > 0) {
					tiA++;
				}

			}
			float relativeFrequency = 0.0f;
			if (absFrequency > 0.0) {
				for (String seedCon : concept.getSeedConceptsList()) {
					float absFrequencyOfSeed = mergedDocList.stream()
							.filter(x -> x.trim().toLowerCase().equals(seedCon.trim().toLowerCase())).count();
					if (absFrequencyOfSeed > 0.0) {
						relativeFrequency = 1.0f;
					}
				}
			}

			float jaccardCoefficient = CoreUtil
					.normalise(relativeFrequency / (absFrequency + (tiA - relativeFrequency)));
			concept.setJaccardCoefficient(jaccardCoefficient);
			list.add(concept);
		}
		list.sort(Comparator.comparing(Concept::getScore).reversed());
		System.out.println("\nConcepts with Jackard Coefficient\n");

		list.stream().forEach(x -> System.out.println(x.getConcept() + "(" + x.getJaccardCoefficient() + ")"));

		System.out.println(
				"============================================================================================================================================");
		DoubleSummaryStatistics stats = list.stream().mapToDouble((x) -> x.getScore()).summaryStatistics();

		float threshold = (float) stats.getAverage();

		List<Concept> domainConcepts = list.stream().filter(x -> x.getJaccardCoefficient() > threshold)
				.collect(Collectors.toList());

		System.out.println("\nDomain Concepts\n");

		System.out.println("Threshold value : " + threshold + "\n");

		domainConcepts.stream()
				.forEach(x -> System.out.println(x.getConcept() + "(" + x.getJaccardCoefficient() + ")"));

		System.out.println(
				"============================================================================================================================================");

		return domainConcepts;
	}
}
