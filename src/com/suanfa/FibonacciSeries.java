package com.suanfa;

import java.time.Duration;
import java.time.Instant;

import com.suanfa.TimeTool.Task;

/**
 * 裴波那契数列
 * 
 * 1 1 2 3 5 8....
 * @author 1824633692@qq.com
 *
 */
public class FibonacciSeries {

	public static void main(String[] args) {
		
		int n=43;

		TimeTool.check("递归调用", new Task(){
			public void showConsumeTime() {
				System.out.println(recursionMethod(n));
			}
		});
		
		TimeTool.check("普通方法", new Task(){
			public void showConsumeTime() {
				System.out.println(commonMethod(n));
			}
		});
		
		TimeTool.check("数学公式", new Task(){
			public void showConsumeTime() {
				System.out.println(method3(n));
			}
		});
	}
	
	//递归调用 这个n>42就废了 时间太长了 时间复杂度n^2
	public static int recursionMethod(int n){
		/* n代表第几项
		 * 1 2 3 4 5 6...n
		 * 1 1 2 3 5 8
		 * 发现 f(n)=f(n-1)+f(n-2),这里从第三项开始
		 * 前两项，符合n《=1 返回n
		 * */
		if(n<=1){
			return n;
		}
	   return recursionMethod(n-1)+recursionMethod(n-2);
	}
	
	//时间复杂度n
	public static int commonMethod(int n){
		int first=1;
		int second=1;
		for(int x=0;x<n-2;x++){
			/*
			 * 第一种
			 */
			/*int sum=first+second;
			first=second;
			second=sum;*/
			
			/**
			 * 第二种省去多余变量
			 */
		    second+=first;
		    first=second-first;
		}
		return second;
	}
	
	/**
	 * 数学公式方法
	 * @param n
	 * @return
	 */
	public static int method3(int n){
		double c=Math.sqrt(5);
		return (int)((Math.pow((1+c)/2, n)-Math.pow((1-c)/2, n))/c);
	}
	
	

}
