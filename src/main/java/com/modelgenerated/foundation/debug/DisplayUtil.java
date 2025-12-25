/*
 * DisplayUtil.java
 *
 * Created on December 21, 2002, 6:55 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.debug;

/**
 *
 * @author  kevind
 */
public class DisplayUtil {
    StringBuilder strBuf;
    
    /** Creates a new instance of DisplayUtil */
    public DisplayUtil() {
        strBuf = new StringBuilder();
    }
    
    public static DisplayUtil newInstance(String message, int level, int maxLevel) {
        if (maxLevel != 0 && level > maxLevel) {
            return null;
        }
        DisplayUtil displayUtil = new DisplayUtil();
        if (level == 0) {
            displayUtil.addLine(level, "DEBUG DISPLAY");
        }
        displayUtil.addLine(level, message);
        
        return displayUtil;
        
    }
    
    public void addLine(int level, String text) {
        strBuf.append(indent(level));
        strBuf.append(text);
        strBuf.append("\n");
    }
    
    public void append (String str) {
        strBuf.append(str);
    }
    
    public String toString () {
        return strBuf.toString();
    }
    
    
    public static String indent(int indent) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            str.append(" ");
        }
        return str.toString();
    }
    
    //here is the static stuff
    public static void out(int indent, String output) {
        System.out.println(DisplayUtil.indent(indent) + output);
    }

    
    public static void initialize(StringBuilder strBuf, int indent) {
        if (indent == 0) {
            strBuf.append(indent(indent));
            strBuf.append("DEBUG DISPLAY");
            strBuf.append("\n");
        }
    }
    public static void addLine(StringBuilder strBuf, int indent, String text) {
        strBuf.append(indent(indent));
        strBuf.append(text);
        strBuf.append("\n");
    }
    
}
