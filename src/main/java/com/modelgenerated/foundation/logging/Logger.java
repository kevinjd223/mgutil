/*
 * Logger.java
 *
 * Created on December 3, 2002, 9:58 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;

/**
 * This is a wrapper around log4j.
 *
 * @author  kevind
 */
public class Logger {
    
    /** Creates a new instance of Logger */
    public Logger() {
    }
    
    static private void log(Priority priority, String category, Object message) {
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(category);
        logger.log(priority, message);
    }
    
    static public void debug(String category, Object message) {
        log(Level.DEBUG, category, message);
    }
    
    static public void debug(Object objectCategory, Object message) {
        debug(objectCategory.getClass().getName(), message);
    }
    
    static public void info(String category, Object message) {
        log(Level.INFO, category, message);
    }
    
    static public void info(Object objectCategory, Object message) {
        info(objectCategory.getClass().getName(), message);
    }
    
    static public void warn(String category, Object message) {
        log(Level.WARN, category, message);
    }
    
    static public void warn(Object objectCategory, Object message) {
        warn(objectCategory.getClass().getName(), message);
    }
    
    static public void error(String category, Object message) {
        log(Level.ERROR, category, message);
    }
    
    static public void error(Object objectCategory, Object message) {
        error(objectCategory.getClass().getName(), message);
    }
    
}
