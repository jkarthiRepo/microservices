package com.text.analysis.core;

import java.util.ArrayList;
import java.util.List;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;

public class Relations {

	public static List<String> findRelations(List<Concept> objectAttributesList) {
		List<String> list = new ArrayList<String>();
		System.out.println("Relations :\n");
		for (Concept con : objectAttributesList) {
			System.out.println("Concept : " + con.getConcept() + "(" + con.getPropobility() + ")");
			list.add("Concept : " + con.getConcept() + "(" + con.getPropobility() + ")");
			for (String objects : con.getObjectsList()) {
				Concept objwithProb = CoreUtil.getConceptWithProb(objects);

				for (String attrs : con.getAttributesList()) {
					Concept atrwithProb = CoreUtil.getConceptWithProb(attrs);
					System.out
							.println(objwithProb.getConcept() + " " + atrwithProb.getConcept() + " ("
									+ CoreUtil.normalise(
											(float) (objwithProb.getPropobility() + atrwithProb.getPropobility()) / 2)
									+ ")");
					list.add(
							objwithProb.getConcept() + " " + atrwithProb.getConcept() + " ("
									+ CoreUtil.normalise(
											(float) (objwithProb.getPropobility() + atrwithProb.getPropobility()) / 2)
									+ ")");
				}
			}

		}
		// list.forEach(x -> System.out.println(x));
		System.out.println(
				"============================================================================================================================================");
		return list;
	}

	public static List<String> findOutputRelations(List<Concept> domainConceptsList,
			List<Concept> candidateConceptsList) {
		List<String> list = new ArrayList<String>();
		System.out.println("Output relations :\n");
		for (Concept candConcept : candidateConceptsList) {
			for (Concept domConcept : domainConceptsList) {
				if (!candConcept.getConcept().equals(domConcept.getConcept())) {
					float prob = (float) ((candConcept.getPropobility() + domConcept.getPropobility()) / 2);
					String cartesianConcept = candConcept.getConcept() + " " + domConcept.getConcept();
					list.add(cartesianConcept + " (" + prob + ")");
				}
			}
		}
		list.forEach(x -> System.out.println(x));
		System.out.println(
				"============================================================================================================================================");
		return list;
	}

	public static List<String> findInputRelations(List<Concept> seedConceptsList, List<Concept> formalConcepts) {
		List<String> list = new ArrayList<String>();
		System.out.println("\nInput relations :\n");
		for (Concept candConcept : formalConcepts) {
			for (Concept domConcept : seedConceptsList) {
				if (!candConcept.getConcept().equals(domConcept.getConcept())) {
					float prob = (float) ((candConcept.getPropobility() + domConcept.getPropobility()) / 2);
					String cartesianConcept = candConcept.getConcept() + " " + domConcept.getConcept();
					list.add(cartesianConcept + " (" + prob + ")");
				}
			}
		}
		list.forEach(x -> System.out.println(x));
		System.out.println(
				"============================================================================================================================================");
		return list;
	}
}
