package com.example.library.book.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class TestInterview{
	TestInterview(){
		this("Hi Test");
	}
	TestInterview(String s){
		System.out.println(s);
	}
}

class Student{
	int age;
	String name;
	Student(int age,String name){
		this.age = age;
		this.name = name;
	}
	
	public String toString() {
        return name + " (" + age + ")";
    }
	
}
class AgeComparator implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o1.age - o2.age;
	}
	
}



public class Singleton {
	private static Singleton instance;
	
	private Singleton() {
		System.out.println("Single ton is created");
	}
	
	public static Singleton getInstance() {
		
		if(instance == null) {
			instance = new Singleton();
		}
		
		return instance;
	}
	
	public void showMessage() {
		System.out.println("This is Singleton");
	}
	
	public static void main(String[] args){
		Singleton s1= Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		
		s1.showMessage();
		
		System.out.println(s1.equals(s2));
		
		
		int[] n1 = new int[0];
		try {
			boolean[] n2 = new boolean[-200];
		}catch(NegativeArraySizeException e) {
			System.out.println("ths is negative array size");
		}finally {
			System.out.println("This is always run");
		}
		
		//double[] n3 = new double[2241423798];
		char[] ch = new char[20];
		
		List<Student> list = new ArrayList<>();
		list.add(new Student(19,"Alice"));
		list.add(new Student(15,"Bob"));
		list.add(new Student(30,"Julian"));
		
		Collections.sort(list,new AgeComparator());
		System.out.println(list);
		TestInterview t = new TestInterview();
		
	}
	

}
