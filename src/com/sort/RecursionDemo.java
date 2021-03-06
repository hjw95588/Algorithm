package com.sort;

/**
 * 
* Title: RecursionDemo  
* Description: 从一个小案例出发，递归求最大值
* @author hjw
* @date 2021年3月5日 下午9:47:22
 */
public class RecursionDemo {
   public static void main(String[] args) {
	int arrs[]= {1,5,4,3,3,2,1,8,10,23,11};
	System.out.println(getMax(arrs, 0, arrs.length-1));
}
   
   public static int getMax(int[] arrs,int left,int right) {
	   
	   //结束条件
	   if(left==right) {
		   return arrs[left];
	   }
	   
	   int center=left+((right-left)>>1);//等价left+(right-left)/2;  
	   //左边最大值
	   int maxLeft=getMax(arrs,left,center);
	   //右边最大值
	   int maxRight=getMax(arrs,center+1,right);
	   
	   int max=Math.max(maxLeft, maxRight);
	   return max;
   }
}
