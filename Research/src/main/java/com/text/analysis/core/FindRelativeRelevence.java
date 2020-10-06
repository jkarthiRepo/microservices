package com.text.analysis.core;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;

public class FindRelativeRelevence {

	public static List<Concept> findRelativeRelevenceOfObjectsAttributes(List<Concept> objectAttributesList) {
		System.out.println("\nRelative Relevence of Objects and Attributes :\n");

		List<Concept> conceptList = new ArrayList<Concept>();
		for (Concept concept : objectAttributesList) {
			List<String> objList = concept.getObjectsList();
			List<String> attrList = concept.getAttributesList();

			List<Float> initialObjectsList = objList.stream().map(x -> {
				Concept tagwithProb = CoreUtil.getConceptWithProb(x);
				return (float) (tagwithProb.getPropobility());
			}).collect(Collectors.toList());

			DoubleSummaryStatistics initialObjectsStatistics = initialObjectsList.stream().mapToDouble(x -> (x))
					.summaryStatistics();

			List<Float> selectedObjectsList = initialObjectsList.stream().filter(x -> x >= 0.5)
					.collect(Collectors.toList());

			DoubleSummaryStatistics selectedObjectsStatistics = selectedObjectsList.stream().mapToDouble(x -> (x))
					.summaryStatistics();

			Float relativeRelevenceOfObjects = CoreUtil.normalise(
					((float) ((initialObjectsStatistics.getSum() * selectedObjectsStatistics.getSum())
							/ (selectedObjectsStatistics.getSum()))));

			List<Float> initialAttrsList = attrList.stream().map(x -> {
				Concept tagwithProb = CoreUtil.getConceptWithProb(x);
				return (float) (tagwithProb.getPropobility());
			}).collect(Collectors.toList());

			DoubleSummaryStatistics initialAttrsStatistics = initialAttrsList.stream().mapToDouble(x -> (x))
					.summaryStatistics();

			List<Float> selectedAttrsList = initialObjectsList.stream().filter(x -> x >= 0.5)
					.collect(Collectors.toList());

			DoubleSummaryStatistics selectedAttrsStatistics = selectedAttrsList.stream().mapToDouble(x -> (x))
					.summaryStatistics();

			Float relativeRelevenceOfAttribs = CoreUtil.normalise(
					((float) ((initialAttrsStatistics.getSum() * selectedAttrsStatistics.getSum())
							/ (selectedAttrsStatistics.getSum()))));
			System.out.println("\nConcept : " + concept.getConcept() + "(" + concept.getPropobility() + ")");
			System.out.println("Relative Relevence Of Objects : " + relativeRelevenceOfObjects);
			System.out.println("Relative Relevence Of Attributes : " + relativeRelevenceOfAttribs);

			concept.setRelativeRelevenceOfAttributes(relativeRelevenceOfAttribs);
			concept.setRelativeRelevenceOfObjects(relativeRelevenceOfObjects);
			conceptList.add(concept);
		}
		System.out.println(
				"============================================================================================================================================");

		return conceptList;
	}
}
