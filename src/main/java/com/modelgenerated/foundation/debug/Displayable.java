/*
 * Displayable.java
 *
 * Created on December 21, 2002, 6:51 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.debug;

import java.util.Map;
/**
 *
 * @author  kevind
 */
// TODO: consider moving Displayable to logging package
public interface Displayable {
    
    public String display();
    public String display(String objectDescription);
    public String display(String objectDescription, int level, int maxLevels, Map<Object,Displayable> displayedObject);
    
    
}
