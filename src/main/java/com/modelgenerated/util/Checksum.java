/*
 * Created on Jan 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 * Copyright 2002-2005 Kevin Delargy.
 */
package com.modelgenerated.util;

import com.modelgenerated.foundation.logging.Logger;
/**
 * @author kevin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Checksum {

    
    
    
    public static String mod11(String digitString) {
        int len = digitString.length();
        int sum = 0;
        int rem = 0;
        int weightIndex = 0; 
        int[] digitArray = new int[len];
        
        for (int k = len-1; k >= 0; k--) {
            sum += weight(weightIndex) * Character.getNumericValue(digitString.charAt(k));
            weightIndex = ++weightIndex % 6;  
            
        }
        Logger.debug("Checksum", "sum " + sum);
        
        int c1 = sum % 11;
        
        int c = c1 == 0 ? 0 : (11 - c1) % 10;
        
        return "" + c;
    }
    
    private static int weight(int weightIndex) {
        return weightIndex + 2;        
    }

}
