package com.example.library.book.util;

import java.util.HashMap;

public class BankTask extends Thread{
	
	private String taskName;
	
	public BankTask(String taskName) {
		this.taskName  = taskName;
	}
	
	public void run() {
		System.out.println(taskName + " is running....");
		try {
			Thread.sleep(100);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(taskName + " finished!");
	}
	static class Retangle{
		int length =5;
		int breadth = 3;
	}
	
	public static void main(String[] args) {
		BankTask t1 = new BankTask("Deposit");
		BankTask t2 = new BankTask("Windraw");
		BankTask t3 = new BankTask("Send Notification");
		
		t1.start();
		t2.start();
		t3.start();
		
		Retangle obj1 = new Retangle();
		Retangle obj2 = obj1;
		
		System.out.println("Before Changing the object's lenght "+ obj2.length+ " breadth "+ obj2.breadth);
		
		obj1.length = 10;
		obj1.breadth = 6;
		
		System.out.println("After changing the object's lenght  "+ obj2.length + " Breadth "+ obj2.breadth);
		
		String s1= "Hello";
		String s2 = "Hello";
		s1.concat(" World");
		System.out.println("s1 "+ s1);
		System.out.println("s2 "+ s2);
		
		
		 String shared = "Safe";
		 Runnable task = () -> {
			 String local = shared + " Thread";
			 System.out.println(Thread.currentThread().getName() + " -> "+ local);
		 };
		 Thread tt1 = new Thread(task);
		 Thread tt2 = new Thread(task);
		 tt1.start();
		 tt2.start();
		 
		 
		 HashMap<String,String> map = new HashMap<>();
		 String key = "user";
		 map.put(key, "Moe Moe");
		 
		 System.out.println("Map Values "+ map.get(key));
		 
		
		
		
		 
		 
	}
}