package com.example.library.book.util;



public class Main {
	private String name;
	
	public Main(String name) {
		this.name = name;
		 System.out.println(name + " is created.");
	}
	public static void test() {
		for(int i=0;i<=10;i++) {
			System.out.println("for loop value "+ i);
		}
		int i = 0;
		while(i<10) {
			
			System.out.println("while loop value "+ i);
			i++;
		}
		int k=0;
		do {
			
			System.out.println("do while value "+ k);
			k++;
		}while(k<10);
	}
	protected void finilize() throws Throwable{
		 System.out.println(name + " is being destroyed by garbage collector.");
	}
	
	static void main() {
		System.out.println("This is parent");
	}
	
	
	public static void main(String[] args) {
		//String i = new String("moe");
		//String j = "moe";
		//System.out.println(i==j);
		//System.out.println(i.equals(j));
		
		
//		 try {
//			  int n = 1000, x = 0;
//			  int arr[] = new int[n];
//			  for (int i = 0; i <= n; i++) {
//			   arr[i] = i / 1;
//			  }
//			 }
//			 catch (ArrayIndexOutOfBoundsException exception) {
//			  System.out.println("1st block = ArrayIndexOutOfBoundsException");
//			 }
//			 catch (ArithmeticException exception) {
//			  System.out.println("2nd block = ArithmeticException");
//			 }
//			 catch (Exception exception) {
//			  System.out.println("3rd block = Exception");
//			 }
//		 
//		 try {
//			 int k = 10/1; 
//		 }catch(ArithmeticException e) {
//			 System.out.println("Arighmtic Catugh");
//		 }finally {
//			System.out.println("This is always run");
//		}
		
		
		
			
		
	}
	
	
	 

}


