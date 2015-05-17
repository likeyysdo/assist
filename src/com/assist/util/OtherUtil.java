package com.assist.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.jasper.tagplugins.jstl.core.Out;

public class OtherUtil {
	/**
	 * @param map<score,credit>
	 * @return average credit
	 */
	public static double CreditCalcu(Map<Integer, Integer> map) {
		double value = 0;
		int sumCredit = 0;
		Set set = map.entrySet();  
        Iterator iterator =set.iterator();   
        while (iterator.hasNext())  
      {   
          Map.Entry mapentry= (Map.Entry)iterator.next();  
          value += (double)((Integer)(mapentry.getKey())*(Integer)(mapentry.getValue()));
          sumCredit += (Integer)mapentry.getValue();        
      }
        value = value/ (double)sumCredit;
       System.out.println(value);
        if (value >= 90)
			return 4.0;
        if (value >= 85) 
        	return 3.7;
        if (value >= 82)
        	return 3.3;
        if (value >= 78)
        	return 3.0;
        if (value >= 75 )
        	return 2.7;
        if (value >= 71)
        	return 2.3;
        if (value >= 66 )
        	return 2.0;
        if (value >= 62 )
        	return 1.7;
        if (value >= 60 )
        	return 1.3;
         	return 0.0;
	}
	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(100, 5);
		map.put(60, 5);
		System.out.println(CreditCalcu(map));
	}
}
