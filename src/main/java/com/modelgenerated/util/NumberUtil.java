/*
 * NumberUtil.java
 *
 * Created on February 21, 2003, 8:25 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.util;

import com.modelgenerated.util.StringUtil;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 *
 * @author  kevind
 */
public class NumberUtil {

    public static Double parseDouble(String valueString) {        
        Double value = null;
        if (!StringUtil.isEmpty(valueString)) {
            try {
                NumberFormat numberFormat = NumberFormat.getInstance();
                Number number = numberFormat.parse(valueString);
                value = new Double(number.doubleValue());
            } catch (NumberFormatException e) {
            } catch (ParseException e) {
            }
        }
        return value;
    }

    public static String format(Double value, int maxDecimalPlaces) {
        if (value == null) {
            return "";
        }
        
        return format(value.doubleValue(), maxDecimalPlaces);        
    }
    
    public static String format(Double value, int maxDecimalPlaces, int minDecimalPlaces) {
        if (value == null || value.isNaN()) {
            return "";
        }
        
        return format(value.doubleValue(), maxDecimalPlaces, minDecimalPlaces);        
    }
    
    public static String format(double value, int maxDecimalPlaces) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(maxDecimalPlaces);
        return numberFormat.format(value);        
    }
    
    public static String format(double value, int maxDecimalPlaces, int minDecimalPlaces) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(maxDecimalPlaces);
        numberFormat.setMinimumFractionDigits(minDecimalPlaces);
        return numberFormat.format(value);        
    }

    /**
     * Compares two numbers and returns true if they are the same. 
     * This treats two null values as being the same.
     * the comparison is not right.
     */ 
    public static boolean same(Double value1, Double value2) {
        if (value1 != null) {
            return value1.equals(value2);
        } 
        return (value2 == null);
    }
    
    /** 
     * Compares two numbers.
     * if values are really close returns 0 else
     * return value of double compare
     */
    public static int compare(double value1, double value2) {
    	if (Math.abs(value1 - value2) < 0.000001d) {
    		return 0;
    	} else {
    		return Double.compare(value1, value2);
    	}
    	
    }
    
    /** 
     * Round to the precision indicated. Doubles are sometimes off by small amount when 
     * sometimes get small messed up when multiplied
     * 
     */
    public static double round(double input, int precision) {
    	Assert.check(precision < 5, "NumberUtil.round does not work for precision > 4");
    	double power = Math.pow(10, precision);
    	double fudgeFactor = input < 0 ? -0.000001 : 0.000001;
    	double returnValue = Math.round((input * power) + fudgeFactor) / power;
    	
    	return returnValue;
    }
    
    /** 
     * Truncates to the precision indicated. Doubles are sometimes off by small amount when 
     * sometimes get small messed up when 
     */
    public static double truncate(double input, int precision) {
    	Assert.check(precision < 5, "NumberUtil.round does not work for precision > 4");
    	double power = Math.pow(10, precision);
    	double returnValue = Math.floor(input * power) / power;
    	
    	return returnValue;
    }
    
    

}
