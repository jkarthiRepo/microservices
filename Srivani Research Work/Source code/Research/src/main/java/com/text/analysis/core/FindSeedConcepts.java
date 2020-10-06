package com.text.analysis.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;

public class FindSeedConcepts {

	public static List<Concept> find(List<Concept> synanymsList, Map<String, List<String>> allDocumentsMap,
			List<String> mergedDocList) {
		List<Concept> list = new ArrayList<Concept>();
		for (Concept concept : synanymsList) {
			float noOfConcDocOccured = 0.0f;
			float absFrequency = mergedDocList.stream()
					.filter(x -> x.trim().toLowerCase().equals(concept.getConcept().trim().toLowerCase())).count();
			for (Map.Entry<String, List<String>> map : allDocumentsMap.entrySet()) {
				List<String> valuesList = map.getValue();
				if (valuesList.stream()
						.filter(x -> x.trim().toLowerCase().equals(concept.getConcept().trim().toLowerCase()))
						.count() > 0) {
					noOfConcDocOccured++;
				}

			}
			concept.setSeedConceptsList(concept.getSynanymsList());
			float domainRelevenses = CoreUtil.normalise((float) ((concept.getPropobility() / absFrequency)
					/ (concept.getPropobility() / noOfConcDocOccured)));
			float domainConsenses = CoreUtil.normalise(
					(float) (concept.getPropobility() + (Math.log((1 / concept.getPropobility())) / Math.log(2))));
			concept.setDomainConsenses(domainConsenses);
			concept.setDomainRelevenses(domainRelevenses);
			list.add(concept);
			System.out.println("Concept : " + concept.getConcept() + "(" + concept.getPropobility() + ")");
			if (concept.getSynanymsList().size() > 1) {
				System.out.println("\nSeed Concepts : ");
				concept.getSynanymsList().stream()
						.forEach(x -> System.out.println(x + "(" + concept.getPropobility() + ")"));
			}
			System.out.println("\nDomain Relevenses : " + domainRelevenses);
			System.out.println("Domain Consenses : " + domainConsenses);
			System.out.println(
					"============================================================================================================================================");
		}
		return list;
	}
}
