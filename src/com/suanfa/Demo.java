package com.suanfa;

//11.	判断101-200之间有多少个素数，并输出所有素数
public class Demo {

	public static void main(String[] args) {
		int a=0;
		for(int x=101;x<=200;x++){
			boolean flag=true;
			for(int y=2;y<=x/2;y++){
				if(x%y==0){
					//表示不是素数
					flag=false;
				}
			}
			if(flag){
				a++;
				System.out.println("第"+a+"个素数:"+x);
			}
		}
	}
	
}
