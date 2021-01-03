/*
 * StringUtil.java
 *
 * Created on October 19, 2002, 6:17 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.util;

import com.modelgenerated.foundation.logging.Logger;

/**
 *
 * @author  kevind
 */
public class StringUtil {
    private static String LOGGER_CATEGORY = "com.modelgenerated.util.StringUtil";
    private static String DOUBLEQUOTE = "\\x27";
    private static String QUOTE = "\\x22";

    
    /** Creates a new instance of StringUtil */
    private StringUtil() {
    }

    /* Increases the length strBuf to the specified length by padding with spaces.
     */ 
    public static void padBuffer(StringBuffer strBuff, int length) {
        strBuff.setLength(length);
        String str = strBuff.toString().replaceAll("\0", " ");
        strBuff.replace (0, length, str);        
    }

    public static String padString(String str, int length) {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append(str);
        padBuffer(strBuff, length);
        return strBuff.toString();
    }
    
    public static String padLeft(String str, int paddedLength, char paddingCharacter) {
        StringBuffer strBuff = new StringBuffer();
        // FIXME: this is a workaround for a bug in StringBuffer.append(char);
        // AbstractStringBuffer visibility bug.
        String charString = "" + paddingCharacter;
        for (int i = 0; i < paddedLength - str.length(); i++) {
            strBuff.append(charString);
        }
        strBuff.append(str);
        
        return strBuff.toString();        
    }
    
    
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;            
        }
        if (str.length() == 0) {
            return true;
        }
        if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }
    
    public static String nullToEmpty(String input) {
        if (input == null) {
            return "";
        } else {
            return input;
        }
    }
    
    public static String emptyToNull(String input) {
        if ("".equals(input)) {
            return null;
        } else {
            return input;
        }
    }
    
    public static String truncate(String input, int length) {
    	if (input == null) {
    		return null;
    	}
        return input.substring(0, Math.min(length, input.length()));
    }
    
    
    /* 
     * remove surrounding quotes.
     *
     */
    public static String removeQuotes(String input) {
        if (StringUtil.isEmpty(input)) {
            return input;
        }

        String output = input;
        if (output.substring(0, 1).equals("\"") || output.substring(0, 1).equals("'")) {                        
            output = output.substring(1, output.length());
        }
        int length = output.length();
        if (length > 0 && (output.substring(length-1, length).equals("\"") || output.substring(length-1, length).equals("'"))) {                        
            Logger.debug(LOGGER_CATEGORY, "last char:" + output.substring(length-1, length));
            output = output.substring(0, length-1);
        }
        Logger.debug(LOGGER_CATEGORY, "output:" + output);
        
        return output;
    }
    
    public static String getJavaVariableFromClassName(String className) {
        Assert.check(!StringUtil.isEmpty(className), "className should not be empty");
        
        if (className.length() == 1) {
            return className.toLowerCase();
        } else {
            return className.substring(0,1).toLowerCase() + className.substring(1);
        }
    }
    
    public static String getPathFromFilePath(String filePath) {
        int lastSeparator = filePath.lastIndexOf("/");
        
        return filePath.substring(0, lastSeparator);
    }
    
    public static String getExtensionFromFilePath(String filePath) {
        //Logger.debug(LOGGER_CATEGORY, "original filepath " + filePath);
        String REGEX_BACKSLASH = "\\\\";
    	filePath = filePath.replaceAll(REGEX_BACKSLASH, "/");
        //Logger.debug(LOGGER_CATEGORY, "new filepath " + filePath);
    	
        int lastSeparator = filePath.lastIndexOf("/");
        int lastPeriod = filePath.lastIndexOf(".");
        //Logger.debug(LOGGER_CATEGORY, "lastSeparator " + lastSeparator);
        //Logger.debug(LOGGER_CATEGORY, "lastPeriod " + lastPeriod);
        
        if (lastPeriod != -1 && lastPeriod > lastSeparator) {
        	return filePath.substring(lastPeriod + 1);
        } else {
        	return "";
        }
    }
    
    public static String httpEncode(String source) {
        // TODO: Using String.replaceAll is not efficient. It creates 5 copies of the String.
        
        String result = source.replaceAll("&","&amp;");
        result = result.replaceAll(DOUBLEQUOTE, "&#39;"); 
        result = result.replaceAll(QUOTE,"&quot;"); 
        result = result.replaceAll(">","&gt;"); 
        result = result.replaceAll("<","&lt;"); 
        return result;
    }

    public static String urlEncode(String source) {
        // TODO: Using String.replaceAll is not efficient. It creates 5 copies of the String.
        
        String result = httpEncode(source);
        result = result.replaceAll("/", "%2f"); 
        return result;
    }

    public static String jsonEncode(String source) {
        // TODO: Using String.replaceAll is not efficient. It creates 5 copies of the String.
        Logger.debug(LOGGER_CATEGORY, "DOUBLEQUOTE " + DOUBLEQUOTE);
        Logger.debug(LOGGER_CATEGORY, "Other DOUBLEQUOTE " + "\\\"");
        
        String result = source.replaceAll("\"", "");
        return result;
    }


    /* Tests if two strings are the "same".
     * Same means either both strings are null or they are "equal".
     */
    public static boolean same(String string1, String string2) {
        if (string1 != null) {
            return string1.equals(string2);
        } 
        return (string2 == null);
    }
    
    public static String fullyQualifiedNameToPackage(String fqn) {
        // note: this is different than the one in JavaCodeBaseGenerator
        // this one is right as it searches for /
        Logger.debug(LOGGER_CATEGORY, "FQN: " + fqn);
        return fqn.substring(0, fqn.lastIndexOf("/"));
    }
    
    public static String fullyQualifiedNameToClass(String fqn) {
        Logger.debug(LOGGER_CATEGORY, "FQN: " + fqn);
        return fqn.substring(fqn.lastIndexOf(".") + 1);
    }
    
    public static String convertToJavaVariableName(String variableName) {
        if (StringUtil.isEmpty(variableName)) {
            return variableName;
        }

        if (variableName.length() == 1) {
            return variableName.toLowerCase();
        } else {
            return variableName.substring(0,1).toLowerCase() + variableName.substring(1);
        }
    }
    
    public static int matchCount(String str, char c) {
        int count = 0;
        
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        
        return count;   
    }

	public static boolean allWhiteSpace(String str) {
		Assert.check(str != null, "str != null");
		String newStr = str.replaceAll(" ", "");
		newStr = newStr.replaceAll("\t", "");
		return newStr.length() == 0;
	}
    
    
}
