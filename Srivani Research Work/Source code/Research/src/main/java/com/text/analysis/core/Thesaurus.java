package com.text.analysis.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.text.analysis.model.Concept;

public class Thesaurus {

	public static List<Concept> findSynanyms(List<Concept> nounPharaseConceptsList) {
		List<Concept> synanymsList = new ArrayList<Concept>();
		for (Concept concept : nounPharaseConceptsList) {
			List<String> synList = getSynList(concept.getConcept());
			if (synList.size() > 0) {
				synList = synList.stream().map(x -> x.trim().toLowerCase()).distinct().collect(Collectors.toList());
				Concept con = concept;
				con.setSynanymsList(synList);
				synanymsList.add(con);
			}
		}
		return synanymsList;

	}

	private static List<String> getSynList(String concept) {
		List<String> list = new ArrayList<String>();
		try {
			URL oracle = new URL("https://www.thesaurus.com/browse/" + concept);
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String soucepage = "";
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				soucepage = soucepage + " " + inputLine;
			}
			if (null != soucepage && !soucepage.isEmpty()) {
				int count = 0;
				String[] arr = soucepage.split("</h1>");
				for (String string : arr) {
					if (count == 1) {
						// System.out.println(string);
						int Incount = 0;
						String[] split1 = string.split("IN A SENTENCE BELOW");
						for (String string2 : split1) {
							if (Incount == 0) {
								// System.out.println(string2);
								String[] split2 = string2.split("eh475bn1\">");
								for (String string3 : split2) {
									if (string3.contains("<!--")) {
										// System.out.println(string3.substring(0, string3.indexOf("<!--")));
										list.add(string3.substring(0, string3.indexOf("<!--")));
									}
								}
							}
							Incount++;
						}
					}
					count++;
				}
			}
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return list;
	}

}
