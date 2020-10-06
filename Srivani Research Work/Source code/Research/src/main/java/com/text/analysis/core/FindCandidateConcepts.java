package com.text.analysis.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;

public class FindCandidateConcepts {

	public static List<Concept> find(List<Concept> seedConceptsList) {
		List<Concept> list = new ArrayList<Concept>();

		for (Concept concept : seedConceptsList) {
			float termLength = (float) ((concept.getConcept().length() > 5) ? 0.2
					: (concept.getConcept().length() < 5) ? 0.1 : 0.0);
			float alpha = 0.1f, betta = 0.5f, comma = 0.4f;
			float score = CoreUtil.normalise((float) ((alpha * concept.getDomainRelevenses())
					+ (betta * concept.getDomainConsenses()) + (comma * termLength)));
			concept.setScore(score);
			list.add(concept);
		}
		list.sort(Comparator.comparing(Concept::getScore).reversed());
		System.out.println("\nConcepts with Score\n");

		list.stream().forEach(x -> System.out.println(x.getConcept() + "(" + x.getScore() + ")"));

		System.out.println(
				"============================================================================================================================================");
		DoubleSummaryStatistics stats = list.stream().mapToDouble((x) -> x.getScore()).summaryStatistics();

		float threshold = (float) stats.getAverage();

		List<Concept> candidateConcepts = list.stream().filter(x -> x.getScore() > threshold)
				.collect(Collectors.toList());

		System.out.println("\nCandidate Concepts\n");

		System.out.println("Threshold value : " + threshold + "\n");

		candidateConcepts.stream().forEach(x -> System.out.println(x.getConcept() + "(" + x.getScore() + ")"));

		System.out.println(
				"============================================================================================================================================");

		return candidateConcepts;
	}
}
