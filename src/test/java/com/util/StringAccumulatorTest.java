package com.util;

import java.io.IOException;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class StringAccumulatorTest extends TestCase {

	// TEST METHOD
	
    /**
     * Rigourous Test :-)
     */
    public void testBasic1() {
    	normalCase("testBasic1", "1", 1);
    }
    
    public void testBasic2() {
    	normalCase("testBasic2", "1,2", 3);
    }
    
    public void testLineBreak1() {
    	normalCase("testLineBreak1", "1\n2,3", 6);
    }
    
    public void testLineBreak2() {
    	exceptionCase("testLineBreak2", "1,\n");
    }
    
    public void testOverlimit1() {
    	normalCase("testOverlimit1", "2,1000", 1002);
    }
    
    public void testOverlimit2() {
    	normalCase("testOverlimit2", "2,1001", 2);
    }
    
    public void testCustomizedDelimiters1() {
    	normalCase("testCustomizedDelimiters1", "//;\n1;2", 3);
    }

    public void testCustomizedDelimiters2() {
    	normalCase("testCustomizedDelimiters2", "//***\n1***2***3", 6);
    }
    
    public void testCustomizedMultiDelimiters1() {
    	normalCase("testCustomizedMultiDelimiters1", "//*|%\n1*2%3", 6);
    }
    
    public void testCustomizedMultiDelimiters2() {
    	normalCase("testCustomizedMultiDelimiters1", "//***|%\n1***2%3***4", 10);
    }
    
    //PRIVATE METHOD
    private void normalCase(String testcaseName, String input, int expectedOutput) {
    	System.out.println("Testcase: " + testcaseName);
    	
    	try {
    		int output = StringAccumulator.add(input);
    		System.out.println(input.replace("\n", "\\n") + " -> " + output);
			assertEquals(output, expectedOutput);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
	    	
	    	assertFalse(false);
		}
    	System.out.println();
    }
    
    private void exceptionCase(String testcaseName, String input) {
    	System.out.println("Testcase: " + testcaseName);
    	System.out.println("Excepted throw exception: " + input.replace("\n", "\\n"));
    	System.out.println();
    	
    	try {
			StringAccumulator.add(input);
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(true);
		}
    }
    
}
