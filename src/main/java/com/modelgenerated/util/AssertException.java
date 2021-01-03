/*
 * AssertException.java
 *
 * Created on October 14, 2002, 7:54 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.util;

/**
 *
 * @author  kevind
 */
public class AssertException extends java.lang.RuntimeException {
    
    /**
     * Creates a new instance of <code>AssertException</code> without detail message.
     */
    public AssertException() {
    }
    
    
    /**
     * Constructs an instance of <code>AssertException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public AssertException(String msg) {
        super(msg);
    }
}
