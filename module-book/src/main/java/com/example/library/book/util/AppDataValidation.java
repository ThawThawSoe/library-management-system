package com.example.library.book.util;

public class AppDataValidation {
	
	public static boolean checkString(String inputString,int size) {
		
		if(inputString == null || inputString.trim().isEmpty()) {
			return false;
			
		}else if(inputString.length()>size){
			return false;
		}
		return true;
	}

}
