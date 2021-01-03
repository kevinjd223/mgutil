/*
 * DomUtil.java
 *
 * Created on December 21, 2002, 5:41 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.util;

import com.modelgenerated.foundation.logging.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author  kevind
 */
public class DomUtil {
    
    /** Creates a new instance of DomUtil */
    public DomUtil() {
    }
    
    
    /* 
     * Gets the first child element with the name childElementName. Returns null if there
     * are none.
     *
     */
    public static Element getChildElement(Element parentElement, String childElementName) {
        // the obvious way
        //NodeList nodes = parentElement.getElementsByTagName(childElementName);
        //return (Element)nodes.item(0);        
        // do it this way so we don't pick up grandchildren
        NodeList nodes = parentElement.getChildNodes();
        for (int i = 0; i < nodes.getLength();i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(childElementName)) {
                return (Element)node;
            }
        }
        return null;        
    }
    
    public static String getChildElementText (Element parentElement, String childElementName) {
        Element childElement = getChildElement(parentElement, childElementName);
        if (childElement != null) {
            return getElementText(childElement);
        }
        return null;
    }
    
    public static String getElementText(Element element) {
        // test to make sure first child is alway Text if there are attributes.
        // test empty string <node></node>
        Node textNode = element.getFirstChild();
        if (textNode == null) {
            return null;
        }
        Assert.check(textNode.getNodeType() == Node.TEXT_NODE, "textNode.getNodeType() == Node.TEXT_NODE");
        return textNode.getNodeValue();
    }
        
    public static String getAttributeValue(Element parentElement, String attributeName) {
        Logger.debug("com.modelgenerated.util.DomUtil", DomUtil.class.getName());
        // the obvious way
        //NodeList nodes = parentElement.getElementsByTagName(childElementName);
        //return (Element)nodes.item(0);        
        // do it this way so we don't pick up grandchildren
        return parentElement.getAttribute(attributeName);
        /*
        NodeList nodes = parentElement.getAttribute(.getChildNodes();
        for (int i = 0; i < nodes.getLength();i++) {
            Node node = nodes.item(i);
            Logger.debug(DomUtil.class.getName(), "nodeName:" + node.getNodeName());
            if (node.getNodeType() == Node.ATTRIBUTE_NODE && node.getNodeName().equals(attributeName)) {
                Attr attribute = (Attr)node;
                return attribute.getValue();
            }
        }
        return null;        
        */
    }
    
    
}
