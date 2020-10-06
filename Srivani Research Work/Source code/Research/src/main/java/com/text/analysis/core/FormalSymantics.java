package com.text.analysis.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.text.analysis.model.Concept;
import com.text.analysis.util.CoreUtil;

import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;

public class FormalSymantics {

	@SuppressWarnings("unused")
	public static List<Concept> findSymanticsOfConcept(Map<String, List<Concept>> clustersMap,
			List<Concept> synanymsList, List<String> mergedDocList, Tokenizer tokanizer, POSTaggerME pOSTaggerME) {

		List<Concept> objAttrList = new ArrayList<Concept>();
		clustersMap.forEach((k, v) -> {
			String cluster = k;
			System.out.println(cluster);
			List<Concept> conceptList = v;
			conceptList.forEach(list -> {
				Concept con = new Concept();
				String concept = list.getConcept();
				System.out.println("Concept : " + concept);
				List<String> objectsList = new ArrayList<String>();
				List<String> attributesList = new ArrayList<String>();
				List<String> subjectList = new ArrayList<String>();
				List<String> verbList = new ArrayList<String>();
				List<String> timeList = new ArrayList<String>();
				List<String> determinersList = new ArrayList<String>();
				List<String> comparativeSuperlativeList = new ArrayList<String>();
				List<Concept> synList = synanymsList.stream().filter(x -> x.getConcept().equals(concept))
						.collect(Collectors.toList());
				// System.out.println("SynList : " + synList.get(0).getSynanymsList());
				List<String> conceptOccuredSentensesList = mergedDocList.stream().filter(x -> x.contains(concept))
						.collect(Collectors.toList());
				conceptOccuredSentensesList.stream().filter(x -> {
					List<String> intagsList = Arrays.asList(x.split(" "));
					// System.out.println(intagsList);
					intagsList.stream().filter(a -> {
						String tags = POSTagger.findPOStags(a, pOSTaggerME, tokanizer);
						Concept tagwithProb = CoreUtil.getConceptWithProb(tags);
						// System.out.println(a+" "+tags+"---->");
						if (tagwithProb.getConcept().equals("NN") || tagwithProb.getConcept().equals("NNS")
								|| tagwithProb.getConcept().equals("NNP") || tagwithProb.getConcept().equals("NNPS")) {
							objectsList.add(a);
						}
						if (tagwithProb.getConcept().equals("RB") || tagwithProb.getConcept().equals("RBR")
								|| tagwithProb.getConcept().equals("RBS") || tagwithProb.getConcept().equals("JJ")
								|| tagwithProb.getConcept().equals("JJR") || tagwithProb.getConcept().equals("JJS")
								|| tagwithProb.getConcept().equals("AV0") || tagwithProb.getConcept().equals("JJ")) {
							attributesList.add(a);
						}
						if (tagwithProb.getConcept().equals("NN") || tagwithProb.getConcept().equals("NNS")
								|| tagwithProb.getConcept().equals("NNP")) {
							subjectList.add(intagsList.get(0));
						}
						if (tagwithProb.getConcept().equals("VB") || tagwithProb.getConcept().equals("VBD")
								|| tagwithProb.getConcept().equals("VBG") || tagwithProb.getConcept().equals("VBN")
								|| tagwithProb.getConcept().equals("VBP") || tagwithProb.getConcept().equals("VBZ")) {
							verbList.add(a);
						}
						if (tagwithProb.getConcept().equals("DATESPEC") || tagwithProb.getConcept().equals("DATEUNS")
								|| tagwithProb.getConcept().equals("DAYS") || tagwithProb.getConcept().equals("MONTH")
								|| tagwithProb.getConcept().equals("TM")) {

							timeList.add(a);
						}
						if (tagwithProb.getConcept().equals("DT") || tagwithProb.getConcept().equals("WDT")) {
							determinersList.add(a);
						}
						if (tagwithProb.getConcept().equals("JJR") || tagwithProb.getConcept().equals("JJS")
								|| tagwithProb.getConcept().equals("RBR") || tagwithProb.getConcept().equals("RBS")) {
							comparativeSuperlativeList.add(a);
						}
						return false;
					}).collect(Collectors.toList());
					return true;
				}).collect(Collectors.toList());

				// System.out.println("ObjectsList : " + objectsList + "\n");
				// System.out.println("AttributesList : " + attributesList + "\n");

				List<String> remDupObjList = objectsList.stream().filter(x -> (!attributesList.contains(x)))
						.collect(Collectors.toList());

				List<String> remDupAttrList = attributesList.stream().filter(x -> (!objectsList.contains(x)))
						.collect(Collectors.toList());

				// Map<String, Float> objMap = new HashMap<String, Float>();
				List<String> filteredObjList = remDupObjList.stream().distinct().map(m -> {
					String tags = POSTagger.findPOStags(m, pOSTaggerME, tokanizer);
					Concept tagwithProb = CoreUtil.getConceptWithProb(tags);
					// objMap.put(m, (float)(tagwithProb.getPropobility()));
					return m + "(" + tagwithProb.getPropobility() + "";
				}).collect(Collectors.toList());

				List<String> filteredAttrList = remDupAttrList.stream().distinct().map(m -> {
					String tags = POSTagger.findPOStags(m, pOSTaggerME, tokanizer);
					Concept tagwithProb = CoreUtil.getConceptWithProb(tags);
					return m + "(" + tagwithProb.getPropobility() + ")";
				}).collect(Collectors.toList());

				List<String> filteredSubjList = CoreUtil.distinct(subjectList).stream().distinct().map(m -> {
					String tags = POSTagger.findPOStags(m, pOSTaggerME, tokanizer);
					Concept tagwithProb = CoreUtil.getConceptWithProb(tags);
					return m + "(" + tagwithProb.getPropobility() + ")";
				}).collect(Collectors.toList());

				List<String> filteredVerbList = CoreUtil.distinct(verbList).stream().distinct().map(m -> {
					String tags = POSTagger.findPOStags(m, pOSTaggerME, tokanizer);
					Concept tagwithProb = CoreUtil.getConceptWithProb(tags);
					return m + "(" + tagwithProb.getPropobility() + ")";
				}).collect(Collectors.toList());

				List<String> filteredTimeList = CoreUtil.distinct(timeList).stream().distinct().map(m -> {
					String tags = POSTagger.findPOStags(m, pOSTaggerME, tokanizer);
					Concept tagwithProb = CoreUtil.getConceptWithProb(tags);
					return m + "(" + tagwithProb.getPropobility() + ")";
				}).collect(Collectors.toList());

				List<String> filteredDeterminList = CoreUtil.distinct(determinersList).stream().distinct().map(m -> {
					String tags = POSTagger.findPOStags(m, pOSTaggerME, tokanizer);
					Concept tagwithProb = CoreUtil.getConceptWithProb(tags);
					return m + "(" + tagwithProb.getPropobility() + ")";
				}).collect(Collectors.toList());

				List<String> filteredcomparativeSuperlativeList = CoreUtil.distinct(comparativeSuperlativeList).stream()
						.distinct().map(m -> {
							String tags = POSTagger.findPOStags(m, pOSTaggerME, tokanizer);
							Concept tagwithProb = CoreUtil.getConceptWithProb(tags);
							return m + "(" + tagwithProb.getPropobility() + ")";
						}).collect(Collectors.toList());

				if (filteredObjList.size() > 0 && filteredAttrList.size() > 0) {
					con.setConcept(concept);
					con.setPropobility(list.getPropobility());
					con.setObjectsList(filteredObjList);
					con.setAttributesList(filteredAttrList);
					con.setSubjectList(filteredSubjList);
					con.setVerbList(filteredVerbList);
					con.setTimeList(filteredTimeList);
					con.setDeterminersList(filteredDeterminList);
					con.setComparativeSuperlativeList(filteredcomparativeSuperlativeList);
					objAttrList.add(con);

					System.out.println("ObjectsList : " + filteredObjList + "\n");
					System.out.println("AttributesList : " + filteredAttrList + "\n");
				}
			});
		});
		System.out.println(
				"============================================================================================================================================");

		return objAttrList;
	}
}
