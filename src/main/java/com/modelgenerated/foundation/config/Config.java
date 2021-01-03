/*
 * Config.java
 *
 * Created on October 7, 2002, 7:19 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.config;

import java.io.InputStream;


/**
 *
 * @author  kevind
 */
public interface Config {
    String getName (); 
    void load (InputStream configInputStream);
    
}
