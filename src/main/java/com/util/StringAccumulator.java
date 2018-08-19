package com.util;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 */
public class StringAccumulator {
	private static final String lineSeparator = "\n";
	
    public static int add(String numbers) throws IOException {
    	// declare the delimiters
    	final String delimiters;
    	if(numbers.startsWith("//")) {
    		int dataStartPoint = numbers.indexOf("\n");
    		if(dataStartPoint<0) {
        		throw new IOException("invalid input format missing \\n after declare delimiters.");
    		}

    		// place // before *, +, ?
    		delimiters = numbers.substring(2, numbers.indexOf("\n"))
    				.replace("*", "\\*")
    				.replace("+", "\\+")
    				.replace("?", "\\?")
    				+ "|" + lineSeparator;
    		
    		numbers = numbers.substring(numbers.indexOf("\n")+1);
    	} else {
    		delimiters = "\\,|" + lineSeparator;
    	}
    	
    	// separate the string to integer array
    	List<Integer> data = 
    			Pattern.compile(delimiters)
    			.splitAsStream(numbers)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
    	
    	// throw exception if there are any negative values
    	List<Integer> negatives = data.stream()
    			.filter(number -> number<0)
    			.collect(Collectors.toList());
    	if(negatives.size()>0) {
    		throw new IOException(String.format("negatives not allowed %s", negatives));
    	}
    	
    	// sum up the results
    	int sum = data.stream().filter(number -> number<=1000).mapToInt(i -> i).sum();
    	
    	return sum;
    }
    
}
