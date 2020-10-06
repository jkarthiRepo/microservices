package com.text.analysis.core;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;

public class OperatorsOfSymanticAlgebra {

	public static void find(List<Concept> relativeRelevence) {
		System.out.println("\nFormal operators of Semantic Algebra : \n");
		for (Concept c1 : relativeRelevence) {
			DoubleSummaryStatistics AttributesofC1 = c1.getAttributesList().stream().mapToDouble(x -> {
				Concept tagwithProb = CoreUtil.getConceptWithProb(x);
				return (Double) (tagwithProb.getPropobility());
			}).summaryStatistics();

			double Ai = AttributesofC1.getAverage();

			DoubleSummaryStatistics ObjectssofC1 = c1.getObjectsList().stream().mapToDouble(x -> {
				Concept tagwithProb = CoreUtil.getConceptWithProb(x);
				return (Double) (tagwithProb.getPropobility());
			}).summaryStatistics();

			double Oi = ObjectssofC1.getAverage();

			for (Concept c2 : relativeRelevence) {
				if (!c1.getConcept().equals(c2.getConcept())) {
					DoubleSummaryStatistics AttributesofC2 = c2.getAttributesList().stream().mapToDouble(x -> {
						Concept tagwithProb = CoreUtil.getConceptWithProb(x);
						return (Double) (tagwithProb.getPropobility());
					}).summaryStatistics();

					double Aj = AttributesofC2.getAverage();

					DoubleSummaryStatistics ObjectssofC2 = c2.getObjectsList().stream().mapToDouble(x -> {
						Concept tagwithProb = CoreUtil.getConceptWithProb(x);
						return (Double) (tagwithProb.getPropobility());
					}).summaryStatistics();

					double Oj = ObjectssofC2.getAverage();

					String E1 = c1.getConcept();
					String E2 = c2.getConcept();

					float value = CoreUtil.normalise((float) ((((Ai * Aj) / (Ai + Aj))) * (((Oi * Oj) / (Oi + Oj)))));

					String status = (value > 0.4) ? "Entity Semantic Equivalance (" + E1 + " = " + E2 + ")"
							: (value >= 0 && value < 0.1) ? "Entity Semantic In-Equivalent (" + E1 + " ! = " + E2 + ")"
									: (value >= 0.3 && value < 0.4) ? "" + E1 + " Sub-Entity of " + E2
											: (value >= 0.2 && value < 0.3)
													? "" + E1 + " partially equivalent entity of " + E2
													: (value >= 0.1 && value < 0.2) ? "" + E1 + " super entity of " + E2
															: "None";

					System.out.println(E1 + "~" + E2 + " =>" + status);
				}
			}
		}
		System.out.println(
				"============================================================================================================================================");

	}
}
