/*
 * EnvironmentPrinter.java
 *
 * Created on October 13, 2002, 7:58 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.tools;

import java.util.*;
import com.modelgenerated.util.StringUtil;
//import com.modelgenerated.util.Assert;
import com.modelgenerated.util.EnvironmentUtil;

/**
 * Print environment variables to System.out.
 * Used for debugging.
 * @author kevind
 */
public class EnvironmentPrinter {
    
    public static void printEnvironment() {
        // print heading
        StringBuilder output = new StringBuilder("Key");
        StringUtil.padBuffer(output, 40);
        output.append("Value");
        System.out.println(output);
        
        // print properties
        Properties props = System.getProperties();
        Enumeration keys = props.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            output = new StringBuilder(key);
            StringUtil.padBuffer(output, 40);
            output.append(System.getProperty(key));
            System.out.println(output);
        }
        System.out.println("classpath " + System.getProperty("java.class.path"));
        
    }
    
}
