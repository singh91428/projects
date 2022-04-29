package com.esign.evendata;

public class RandomTesting {
	private static int random;
	static{
		int min = 1;  
		int max = 20;      
		random = (int)(Math.random()*(max-min+1)+min);  
	}
	public static int getRandomno() {
		return random;
	}
}
