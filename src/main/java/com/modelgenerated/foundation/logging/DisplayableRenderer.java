/*
 * DisplayableRenderer.java
 *
 * Created on January 22, 2003, 7:55 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.logging;

import com.modelgenerated.foundation.debug.Displayable;

/**
 *
 * @author  kevind
 */
public class DisplayableRenderer  {
    
    /** Creates a new instance of DisplayableRenderer */
    public DisplayableRenderer() {
    }
    
    public String doRender(Object obj) {
        Displayable displayable = (Displayable)obj;
        return displayable.display();
    }
    
}
