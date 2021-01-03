/*
 * ConfigDescriptor.java
 *
 * Created on October 1, 2003, 7:42 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.config;

/**
 * pack scope class that represents data in the config bootstrap
 * @author  kevind
 */
class ConfigDescriptor {
    public String name;
    public String resourceName;
    public String fileName;
    public String className;
    public Integer order; // UNDONE: should be preLoadOrder??
    
    /** Creates a new instance of ConfigDescriptor */
    public ConfigDescriptor() {
    }   
    
    
}
