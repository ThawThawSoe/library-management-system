package com.example.library.book.util;
class Parent{
	int i = 10;
	Parent(){
		System.out.println("This is parent consturor");
	}
	static void show() {
		System.out.println("This is parent show");
	}
}
class Child extends Parent{
	Child(){
		super();
		System.out.println("This is child contructor");
	}
	static void show() {	
		
		System.out.println("This is child show");
	}
}

public class Test  {

	public static void main (String[] args) {
		Parent p = new Child();
		p.show();
	}
	
	
	
	
	
}



