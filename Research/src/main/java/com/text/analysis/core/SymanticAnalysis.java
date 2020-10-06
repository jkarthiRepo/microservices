package com.text.analysis.core;

import java.util.List;

import com.text.analysis.model.Concept;

public class SymanticAnalysis {

	public static void analysis(List<Concept> relativeRelevence, List<String> Relations,
			List<String> inputRelations, List<String> outputRelations) {
		System.out.println("\nFormal Symantics of Conceptual Entities : ");
		for (Concept concept : relativeRelevence) {
			System.out.println("\nConcept : " + concept.getConcept() + "(" + concept.getPropobility() + ")");
			System.out.println("Objects : " + concept.getObjectsList());
			System.out.println("Attributes : " + concept.getAttributesList());
			System.out.println("Relations : " + Relations);
			System.out.println("Input Relations : " + inputRelations);
			System.out.println("Output Relations : " + outputRelations);
		}

		System.out.println("\nFormal Symantics of Behaviour : ");
		for (Concept concept : relativeRelevence) {
			System.out.println("\nConcept : " + concept.getConcept() + "(" + concept.getPropobility() + ")");
			System.out.println("Subjects : " + concept.getSubjectList());
			System.out.println("Objects : " + concept.getObjectsList());
			System.out.println("Verbs : " + concept.getVerbList());
			// System.out.println("Space : " + );
			System.out.println("Time : " + concept.getTimeList());
		}

		System.out.println("\nFormal Symantics of Modifier : ");
		for (Concept concept : relativeRelevence) {
			System.out.println("\nConcept : " + concept.getConcept() + "(" + concept.getPropobility() + ")");
			System.out.println("Determiners : " + concept.getDeterminersList());
			// System.out.println("Qualifiers : ");
			System.out.println("Comparatives and Superlatives : " + concept.getSubjectList());

		}
		System.out.println(
				"============================================================================================================================================");

	}
}
