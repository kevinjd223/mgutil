/*
 * UUIDUtil.java
 *
 * Created on June 8, 2003, 9:55 PM
 */

package com.modelgenerated.util;

import org.doomdark.uuid.UUID;
import org.doomdark.uuid.UUIDGenerator;

/**
 *
 * @author  kevind
 */
public class UUIDUtil {
    
    /** Creates a new instance of UUIDUtil */
    public UUIDUtil() {
    }
    
    public static String generateUUID() {
        UUID uuid;
        UUIDGenerator uuidGenerator = UUIDGenerator.getInstance();
        uuid = uuidGenerator.generateTimeBasedUUID(uuidGenerator.getDummyAddress());
        return uuid.toString();
    }
    
}
