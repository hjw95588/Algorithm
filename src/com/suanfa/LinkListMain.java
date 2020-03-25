package com.suanfa;

public class LinkListMain {

	public static void main(String [] args){
		LinkList<Integer> list=new LinkList<>();
		
		list.add(0);
		list.add(1);
		list.add(2);
		
		list.add(3, 3);
		
		list.add(4);
		list.add(5);
		
		list.remove(3);
		
		System.out.println(list.size());
	}
}
