/*
 * Assert.java
 *
 * Created on October 14, 2002, 7:42 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.util;

/**
 *
 * @author  kevind
 */
public class Assert {
    public static final boolean assertsEnabled = true;
    
    /** Creates a new instance of Assert */
    private Assert() {
    }
    
    static public void check(boolean test, String message) {
        if (!assertsEnabled) {
            return;
        }
        if (!test) {
            throw new AssertException(message);
        }
    }
    
}
