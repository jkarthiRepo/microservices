//package com.text.analysis;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Test {
//
//	public static void main(String[] args) {
//		String a = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
//		List<String> ilist = new ArrayList<String>();
//		String[] arr = a.split(" ");
//		for (int i = 0; i < arr.length; i++) {
//			ilist.add(arr[i]);
//		}
//		int size = ilist.size();
//		
//		float gsdmmThreshold = 10.0f; 
//		int count = (int)(size/gsdmmThreshold)+1;
//		Map<String, List<String>> clustersMap = new LinkedHashMap<String, List<String>>();
//		
//		for (int i = 0; i < size; i++) {
//			i--;
//			for (int j = 0; j < count; j++) {
//				if(ilist.size()!=0) {
//					List<String> list = clustersMap.get("Cluster : "+j);
//					if(null==list) {
//						list = new ArrayList<String>();
//					}
//					
//					int rNum = (int) ((Math.random() * ((ilist.size()-1) - 0)) + 0);
//					list.add(ilist.get(rNum));
//					clustersMap.put("Cluster : "+j, list);
//					ilist.remove(rNum);
//					i++;
//				}
//			}
//			System.out.println( );
//		}
//		
//		for(Map.Entry<String, List<String>> map : clustersMap.entrySet()) {
//			System.out.println(map.getKey());
//			System.out.println(map.getValue()+"\n");
////			for (Concept concept : map.getValue()) {
////				System.out.println(concept.getConcept());
////			}
//		}
//		
//		System.out.println( );
//		
//		
//		
//		
//		
//	}
//	static double normalize(double X, double min, double max) {
//		double s =  (X-min)/(max - min);
//		return s;
//	}
//	
//	public static int getRandomNumber(int min, int max) {
//	    return (int) ((Math.random() * (max - min)) + min);
//	}
//}
