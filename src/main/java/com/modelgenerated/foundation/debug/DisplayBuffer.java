/*
 * DisplayBuffer.java
 *
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.debug;

/**
 *
 * @author  kevind
 */
public class DisplayBuffer {
    private static final String TAB = "  ";
    StringBuilder strBuf;
    
    /** Creates a new instance of DisplayUtil */
    public DisplayBuffer() {
        strBuf = new StringBuilder();
    }
    
    public static DisplayBuffer newInstance(String className, String objectDescription, int level, int maxLevel) {
        if (maxLevel != 0 && level > maxLevel) {
            return null;
        }
        DisplayBuffer displayBuffer = new DisplayBuffer();
        if (level == 0) {
            displayBuffer.addLine(level, "DEBUG DISPLAY");
        }
        String title;
        if (objectDescription != null && objectDescription.length() > 0) {
            title = className + " - " + objectDescription;
        } else { 
            title = className;
        }
        displayBuffer.addLine(level, title);
        
        return displayBuffer;
        
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
    
    
    //here is the static stuff
    private static String indent(int tabs) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tabs; i++) {
            str.append(TAB);
        }
        return str.toString();
    }
    
    
}
