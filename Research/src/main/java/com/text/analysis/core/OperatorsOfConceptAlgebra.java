package com.text.analysis.core;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;

public class OperatorsOfConceptAlgebra {

	public static void find(List<Concept> relativeRelevence) {
		System.out.println("\nFormal operators of Concept Algebra : \n");
		for (Concept c1 : relativeRelevence) {
			DoubleSummaryStatistics AttributesofC1 = c1.getAttributesList().stream().mapToDouble(x -> {
				Concept tagwithProb = CoreUtil.getConceptWithProb(x);
				return (Double) (tagwithProb.getPropobility());
			}).summaryStatistics();

			double Ai = AttributesofC1.getAverage();

			for (Concept c2 : relativeRelevence) {
				if (!c1.getConcept().equals(c2.getConcept())) {
					DoubleSummaryStatistics AttributesofC2 = c2.getAttributesList().stream().mapToDouble(x -> {
						Concept tagwithProb = CoreUtil.getConceptWithProb(x);
						return (Double) (tagwithProb.getPropobility());
					}).summaryStatistics();

					double Aj = AttributesofC2.getAverage();

					String C1 = c1.getConcept();
					String C2 = c2.getConcept();

					float value = CoreUtil.normalise((float) ((((Ai * Aj) / (Ai + Aj)))));

					String status = (value > 0.4) ? "Synanym " + C1 + " & " + C2 + " are equivalent concepts"
							: (value >= 0 && value < 0.1) ? "Antonym " + C1 + " & " + C2 + " are opposite concepts"
									: (value >= 0.3 && value < 0.4) ? "Homonym " + C1 + " is sub-concept of C2+"
											: (value >= 0.2 && value < 0.3)
													? "Partial Synanym " + C1 + " is partially equivalent concept of "
															+ C2
													: (value >= 0.1 && value < 0.2)
															? "Hypernym " + C1 + " is super-concept of " + C2
															: "None";

					System.out.println(C1 + "~" + C2 + " =>" + status);
				}
			}
		}
		System.out.println(
				"============================================================================================================================================");

	}
}
