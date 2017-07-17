package com.ihht.mina.NIO.channel;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Map<Integer,Map<Integer,Double>> map = new HashMap<Integer, Map<Integer,Double>>();
		Map<Integer,Double> m = new HashMap<Integer, Double>();
		m.put(101, 20d);
		map.put(1, m);
		Double double1 = map.get(1).get(102);
		System.out.println(double1);
		if(map.get(1).get(102) == null){
			System.out.println(123);
		}
	}
}
