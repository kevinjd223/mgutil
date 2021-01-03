package com.modelgenerated.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.modelgenerated.foundation.logging.Logger;

public class NumberUtilTest {

	@Test
	public void testParseDouble() {
		
		Double d1 = NumberUtil.parseDouble("1234.123");
		
        assertTrue("cutSheetDocumentData should not be null", d1 == 1234.123);		
		
		// fail("Not yet implemented");
	}
	
	@Test
	public void testFormatDouble() {
		String testString = NumberUtil.format(1234.123, 1); 
		Logger.debug("com.modelgenerated.NumberUtilTest", "testString1: " + testString);
        assertTrue("testString should be 1234.1", "1,234.1".equals(testString));
		testString = NumberUtil.format(1234.123, 2); 
        assertTrue("testString should be 1234.12", "1,234.12".equals(testString));
		testString = NumberUtil.format(1234.123, 4); 
        assertTrue("testString should be 1234.123", "1,234.123".equals(testString));
		
	}
	
	@Test
	public void testSame() {
		
		
	}

}
