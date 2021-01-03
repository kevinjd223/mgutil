/*
 * EnvironmentUtil.java
 *
 * Created on October 19, 2002, 11:55 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.util;

import java.util.*;

/**
 *
 * @author  kevind
 */
public class EnvironmentUtil {
    
    /** Creates a new instance of EnvironmentUtil */
    public EnvironmentUtil() {
    }
    
    public static void convertArgsToProperties(java.lang.String[] args) {

        for (int i = 0; i < args.length; i++) {
            StringTokenizer tokenizer = new StringTokenizer(args[0], "=");
            Assert.check (tokenizer.countTokens() == 2, "tokenizer.countTokens() != 2");
            System.setProperty(tokenizer.nextToken(), tokenizer.nextToken());
        }

        
    }
    
}
