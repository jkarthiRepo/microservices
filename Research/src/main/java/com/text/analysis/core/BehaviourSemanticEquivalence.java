package com.text.analysis.core;

import java.util.List;

import com.text.analysis.model.Concept;

public class BehaviourSemanticEquivalence {

	public static void find(List<Concept> relativeRelevence) {
		System.out.println("\nBehaviour Semantic Equivalence : \n");
		for (Concept behaviour1 : relativeRelevence) {
			for (Concept behaviour2 : relativeRelevence) {
				if (behaviour1.getSubjectList().stream().filter(x -> behaviour2.getSubjectList().contains(x))
						.count() > 0) {
					System.out.println(behaviour1.getConcept() + " ~ " + behaviour2.getConcept() + " => "
							+ behaviour1.getConcept() + " and " + behaviour2.getConcept() + " are Subject related");
				} else if (behaviour1.getVerbList().stream().filter(x -> behaviour2.getVerbList().contains(x))
						.count() > 0) {
					System.out.println(behaviour1.getConcept() + " ~ " + behaviour2.getConcept() + " => "
							+ behaviour1.getConcept() + " and " + behaviour2.getConcept() + " are Verb related");
				} else if (behaviour1.getTimeList().stream().filter(x -> behaviour2.getTimeList().contains(x))
						.count() > 0) {
					System.out.println(behaviour1.getConcept() + " ~ " + behaviour2.getConcept() + " => "
							+ behaviour1.getConcept() + " and " + behaviour2.getConcept() + " are Time related");
				} else if (behaviour1.getObjectsList().stream().filter(x -> behaviour2.getObjectsList().contains(x))
						.count() > 0) {
					System.out.println(behaviour1.getConcept() + " ~ " + behaviour2.getConcept() + " => "
							+ behaviour1.getConcept() + " and " + behaviour2.getConcept() + " are Object related");
				}
			}
		}
		System.out.println(
				"============================================================================================================================================");

	}

}
