/*
 * ConfigNotFoundException.java
 *
 * Created on October 23, 2002, 8:07 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.config;

/**
 *
 * @author  kevind
 */
public class ConfigNotFoundException extends java.lang.RuntimeException {
    
    /**
     * Creates a new instance of <code>ConfigNotFoundException</code> without detail message.
     */
    public ConfigNotFoundException() {
    }
    
    
    /**
     * Constructs an instance of <code>ConfigNotFoundException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ConfigNotFoundException(String msg) {
        super(msg);
    }
    public ConfigNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
