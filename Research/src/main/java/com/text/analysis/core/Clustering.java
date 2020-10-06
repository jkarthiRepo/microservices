package com.text.analysis.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.text.analysis.model.Concept;

public class Clustering {

	public static float gsdmmThreshold = 10.0f;

	public void findPattern(String t, String p) {
		char[] text = t.toCharArray();
		char[] pattern = p.toCharArray();
		int pos = indexOf(text, pattern);
		if (pos == -1)
			System.out.println("\nNo Match\n");
		else
			System.out.println("Pattern found at position : " + pos);
	}

	/** Function to calculate index of pattern substring **/
	public int indexOf(char[] text, char[] pattern) {
		if (pattern.length == 0)
			return 0;
		int charTable[] = makeCharTable(pattern);
		int offsetTable[] = makeOffsetTable(pattern);
		for (int i = pattern.length - 1, j; i < text.length;) {
			for (j = pattern.length - 1; pattern[j] == text[i]; --i, --j)
				if (j == 0)
					return i;

			// i += pattern.length - j; // For naive method
			i += Math.max(offsetTable[pattern.length - 1 - j], charTable[text[i]]);
		}
		return -1;
	}

	/** Makes the jump table based on the mismatched character information **/
	private int[] makeCharTable(char[] pattern) {
		final int ALPHABET_SIZE = 256;
		int[] table = new int[ALPHABET_SIZE];
		for (int i = 0; i < table.length; ++i)
			table[i] = pattern.length;
		for (int i = 0; i < pattern.length - 1; ++i)
			table[pattern[i]] = pattern.length - 1 - i;
		return table;
	}

	/** Makes the jump table based on the scan offset which mismatch occurs. **/
	private static int[] makeOffsetTable(char[] pattern) {
		int[] table = new int[pattern.length];
		int lastPrefixPosition = pattern.length;
		for (int i = pattern.length - 1; i >= 0; --i) {
			if (isPrefix(pattern, i + 1))
				lastPrefixPosition = i + 1;
			table[pattern.length - 1 - i] = lastPrefixPosition - i + pattern.length - 1;
		}
		for (int i = 0; i < pattern.length - 1; ++i) {
			int slen = suffixLength(pattern, i);
			table[slen] = pattern.length - 1 - i + slen;
		}
		return table;
	}

	/** function to check if needle[p:end] a prefix of pattern **/
	private static boolean isPrefix(char[] pattern, int p) {
		for (int i = p, j = 0; i < pattern.length; ++i, ++j)
			if (pattern[i] != pattern[j])
				return false;
		return true;
	}

	/**
	 * function to returns the maximum length of the substring ends at p and is a
	 * suffix
	 **/
	private static int suffixLength(char[] pattern, int p) {
		int len = 0;
		for (int i = p, j = pattern.length - 1; i >= 0 && pattern[i] == pattern[j]; --i, --j)
			len += 1;
		return len;
	}

	/** Main Function **/
	public static Map<String, List<Concept>> clustering(List<Concept> candidateConceptsList,
			List<Concept> domainConceptsList) {

		List<Concept> clusterConceptsList = candidateConceptsList;
		int size = domainConceptsList.size();
		int count = (int) (size / gsdmmThreshold) + 1;
		Map<String, List<Concept>> clustersMap = new LinkedHashMap<String, List<Concept>>();

		for (int i = 0; i < size; i++) {
			i--;
			for (int j = 0; j < count; j++) {
				if (clusterConceptsList.size() != 0) {
					List<Concept> list = clustersMap.get("Cluster " + j + " : ");
					if (null == list) {
						list = new ArrayList<Concept>();
					}

					int rNum = (int) ((Math.random() * ((clusterConceptsList.size() - 1) - 0)) + 0);
					list.add(clusterConceptsList.get(rNum));
					clustersMap.put("Cluster " + j + " : ", list);
					clusterConceptsList.remove(rNum);
					i++;
				}
			}
		}

		System.out.println("\nGSDMM Clustering : \n");
		for (Map.Entry<String, List<Concept>> map : clustersMap.entrySet()) {
			System.out.print(map.getKey() + "{ ");
			for (Concept x : map.getValue()) {
				System.out.print(x.getConcept() + "(" + x.getPropobility() + ") ");
			}
			System.out.print(" }\n");
		}

		System.out.println(
				"============================================================================================================================================");
		return clustersMap;
	}

	public static ArrayList<Integer> ClustersDividend(int count, int sum) {
		ArrayList<Integer> ranvalue = new ArrayList<Integer>();
		// int count = 3;
		// int sum = 9;
		java.util.Random g = new java.util.Random();

		int vals[] = new int[count];
		sum -= count;

		for (int i = 0; i < count - 1; ++i) {
			vals[i] = g.nextInt(sum);
		}
		vals[count - 1] = sum;

		java.util.Arrays.sort(vals);
		for (int i = count - 1; i > 0; --i) {
			vals[i] -= vals[i - 1];
		}
		for (int i = 0; i < count; ++i) {
			++vals[i];
		}

		for (int i = 0; i < count; ++i) {
			System.out.printf("%4d", vals[i]);
			ranvalue.add(vals[i]);
		}
		System.out.printf("\n");
		return ranvalue;
	}

}
