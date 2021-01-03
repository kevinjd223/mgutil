package com.modelgenerated.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.modelgenerated.foundation.logging.Logger;

public class FileUtilTest {

	@Test
	public void testcreateValidFileName() {
		
        String tempDir = FileUtil.getTempDir();
        Logger.debug(this, "tempDir:" + tempDir);
    	
		String input = "ASDFasdf1234567890";
		String expectedReturnVal = input;
		String returnVal = FileUtil.createValidFileName(input);		
        assertTrue("expectedReturnVal.equals(returnVal); expectedReturnVal: " + expectedReturnVal + "returnVal: " + returnVal, expectedReturnVal.equals(returnVal));
		

		input             = "ASDFasdf~!@#$%^&*()_+";
		expectedReturnVal = "ASDFasdf~!@#$%^&()_+";
		returnVal = FileUtil.createValidFileName(input);		
        assertTrue("expectedReturnVal.equals(returnVal); expectedReturnVal: " + expectedReturnVal + " returnVal: " + returnVal, expectedReturnVal.equals(returnVal));
		
        
        
	}
	
}
