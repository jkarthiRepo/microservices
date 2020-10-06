package com.text.analysis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.text.analysis.core.BehaviourSemanticEquivalence;
import com.text.analysis.core.Clustering;
import com.text.analysis.core.FindCandidateConcepts;
import com.text.analysis.core.FindDomainConcepts;
import com.text.analysis.core.FindRelativeRelevence;
import com.text.analysis.core.FindSeedConcepts;
import com.text.analysis.core.FormalSymantics;
import com.text.analysis.core.OperatorsOfConceptAlgebra;
import com.text.analysis.core.OperatorsOfSymanticAlgebra;
import com.text.analysis.core.POSTagger;
import com.text.analysis.core.Relations;
import com.text.analysis.core.SymanticAnalysis;
import com.text.analysis.core.Thesaurus;
import com.text.analysis.model.Concept;
import com.text.analysis.readerwriter.Reader;
import com.text.analysis.util.CoreUtil;

import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;

public class MainApp {

	public static void main(String[] args) throws IOException {

		// Preprocessing.preprocessFiles();

		Tokenizer tokanizer = POSTagger.tokanizer();
		POSTaggerME POSTaggerME = POSTagger.posTaggerME();

		Map<String, List<String>> docMap = Reader.readPreprocessFiles();

		List<Concept> nounPharaseConceptsList = Reader.readNounPhrases();

		List<Concept> synanymsList = Thesaurus.findSynanyms(nounPharaseConceptsList);

		List<String> mergedDocList = CoreUtil.convertSingleDoc(docMap);

		List<Concept> seedConceptsList = FindSeedConcepts.find(synanymsList, docMap, mergedDocList);

		List<Concept> candidateConceptsList = FindCandidateConcepts.find(seedConceptsList);

		List<Concept> domainConceptsList = FindDomainConcepts.semanticFiltering(candidateConceptsList, mergedDocList,
				docMap);

		Map<String, List<Concept>> clustersMap = Clustering.clustering(candidateConceptsList, domainConceptsList);

		List<Concept> objectAttributesList = FormalSymantics.findSymanticsOfConcept(clustersMap, synanymsList,
				mergedDocList, tokanizer, POSTaggerME);

		List<String> relations = Relations.findRelations(objectAttributesList);

		List<String> outputRelations = Relations.findOutputRelations(domainConceptsList, candidateConceptsList);

		List<String> inputRelations = Relations.findInputRelations(seedConceptsList, nounPharaseConceptsList);

		List<Concept> relativeRelevence = FindRelativeRelevence
				.findRelativeRelevenceOfObjectsAttributes(objectAttributesList);

		OperatorsOfConceptAlgebra.find(relativeRelevence);

		SymanticAnalysis.analysis(relativeRelevence, relations, inputRelations, outputRelations);

		OperatorsOfSymanticAlgebra.find(relativeRelevence);

		BehaviourSemanticEquivalence.find(relativeRelevence);

		System.out.println();

	}

}
