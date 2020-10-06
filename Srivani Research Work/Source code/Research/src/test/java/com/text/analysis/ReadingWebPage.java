package com.text.analysis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadingWebPage {
	public static void main(String[] args) throws Exception {

		URL oracle = new URL("https://www.thesaurus.com/browse/teaching");
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
					//System.out.println(string);
					int Incount = 0;
					String[] split1 = string.split("IN A SENTENCE BELOW");
					for (String string2 : split1) {
						if (Incount == 0) {
							//System.out.println(string2);
							String[] split2 = string2.split("eh475bn1\">");
							for (String string3 : split2) {
								if(string3.contains("<!--")) {
									System.out.println(string3.substring(0, string3.indexOf("<!--")));
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
	}
}