/* Logger.java
 *
 * Copyright 2002-2024 Kevin Delargy.
 */

package com.modelgenerated.foundation.logging;


import org.slf4j.LoggerFactory;

/**
 * This is a wrapper around slf4j.
 */
public class Logger {
    
    /** Creates a new instance of Logger */
    public Logger() {
    }

    static public void debug(String category, Object message) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(category);
        logger.debug(message.toString());
    }
    
    static public void debug(Object objectCategory, Object message) {
        debug(objectCategory.getClass().getName(), message);
    }
    
    static public void info(String category, Object message) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(category);
        logger.info(message.toString());
    }
    
    static public void info(Object objectCategory, Object message) {
        info(objectCategory.getClass().getName(), message);
    }
    
    static public void warn(String category, Object message) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(category);
        logger.warn(message.toString());
    }
    
    static public void warn(Object objectCategory, Object message) {
        warn(objectCategory.getClass().getName(), message);
    }
    
    static public void error(String category, Object message) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(category);
        logger.error(message.toString());
    }
    
    static public void error(Object objectCategory, Object message) {
        error(objectCategory.getClass().getName(), message);
    }
    
}
