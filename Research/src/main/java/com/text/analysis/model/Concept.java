package com.text.analysis.model;

import java.util.List;

public class Concept{
	private String concept;
	private double propobility;
	private String topic;
	private List<String> synanymsList;
	private List<String> seedConceptsList;
	private double domainRelevenses;
	private double domainConsenses;
	private float score;
	private List<String> candidateConceptsList;
	private float jaccardCoefficient;
	private List<String> objectsList;
	private List<String> attributesList;
	private float relativeRelevenceOfObjects;
	private float relativeRelevenceOfAttributes;
	private List<String> subjectList;
	private List<String> verbList;
	private List<String> timeList;
	private List<String> determinersList;
	private List<String> comparativeSuperlativeList;
	
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	public double getPropobility() {
		return propobility;
	}
	public void setPropobility(double propobility) {
		this.propobility = propobility;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<String> getSynanymsList() {
		return synanymsList;
	}
	public void setSynanymsList(List<String> synanymsList) {
		this.synanymsList = synanymsList;
	}
	public double getDomainRelevenses() {
		return domainRelevenses;
	}
	public void setDomainRelevenses(double domainRelevenses) {
		this.domainRelevenses = domainRelevenses;
	}
	public double getDomainConsenses() {
		return domainConsenses;
	}
	public void setDomainConsenses(double domainConsenses) {
		this.domainConsenses = domainConsenses;
	}
	public List<String> getSeedConceptsList() {
		return seedConceptsList;
	}
	public void setSeedConceptsList(List<String> seedConceptsList) {
		this.seedConceptsList = seedConceptsList;
	}
	public List<String> getCandidateConceptsList() {
		return candidateConceptsList;
	}
	public void setCandidateConceptsList(List<String> candidateConceptsList) {
		this.candidateConceptsList = candidateConceptsList;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getJaccardCoefficient() {
		return jaccardCoefficient;
	}
	public void setJaccardCoefficient(float jaccardCoefficient) {
		this.jaccardCoefficient = jaccardCoefficient;
	}
	public List<String> getObjectsList() {
		return objectsList;
	}
	public void setObjectsList(List<String> objectsList) {
		this.objectsList = objectsList;
	}
	public List<String> getAttributesList() {
		return attributesList;
	}
	public void setAttributesList(List<String> attributesList) {
		this.attributesList = attributesList;
	}
	public float getRelativeRelevenceOfObjects() {
		return relativeRelevenceOfObjects;
	}
	public void setRelativeRelevenceOfObjects(float relativeRelevenceOfObjects) {
		this.relativeRelevenceOfObjects = relativeRelevenceOfObjects;
	}
	public float getRelativeRelevenceOfAttributes() {
		return relativeRelevenceOfAttributes;
	}
	public void setRelativeRelevenceOfAttributes(float relativeRelevenceOfAttributes) {
		this.relativeRelevenceOfAttributes = relativeRelevenceOfAttributes;
	}
	
	public List<String> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<String> subjectList) {
		this.subjectList = subjectList;
	}
	public List<String> getVerbList() {
		return verbList;
	}
	public void setVerbList(List<String> verbList) {
		this.verbList = verbList;
	}
	public List<String> getTimeList() {
		return timeList;
	}
	public void setTimeList(List<String> timeList) {
		this.timeList = timeList;
	}
	public List<String> getDeterminersList() {
		return determinersList;
	}
	public void setDeterminersList(List<String> determinersList) {
		this.determinersList = determinersList;
	}
	public List<String> getComparativeSuperlativeList() {
		return comparativeSuperlativeList;
	}
	public void setComparativeSuperlativeList(List<String> comparativeSuperlativeList) {
		this.comparativeSuperlativeList = comparativeSuperlativeList;
	}
	
	
}
