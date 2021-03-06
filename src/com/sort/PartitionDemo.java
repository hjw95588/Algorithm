package com.sort;

/**
 * 荷兰国旗问题
 * 
* Title: PartitionDemo  
* Description: 简言之，一个数组
* 要求给定一个数num，假如为5
* 要求等于5的放中间，大于5的放右边，小于5的放左边
* 注意这里没有要求，排好后左边，右边的区域是有序的
* 思路：左右区间，L~R
* 搞两个区域
* 小于区域left=L-1
* 大于区域right=R+1
* 中间指针center刚开始在左边，即等于left
* 
* 分3种情况判断
* 1.arrs[center]<num的时候
*     此时left的下一位元素和当前位置交换，之后++left。center++
* 2.arrs[center]>num的时候
*     次数right的前一位元素和当前位置交换，之后right--，但是center不变，因为right的前一位元素属于不确定区域，
*     交换完之后，需要继续进行比较
* 3.arrs[center]==num的时候
*     center++
* 整个过程的条件是，center<right
* 因为right一直在往左边移动，center往右边移动，迟早会撞上
* 
*  
* @author hjw
* @date 2021年3月5日 下午10:06:03
 */
public class PartitionDemo {
    public static void main(String [] args) {
    	int arrs[]= {7,6,-1,-2,3,-5,5,23,6,5,4,6,-2,8,10,11,-43};
    	int news[]=partition(arrs, 0, arrs.length-1, 5);
    	System.out.println();
    }
    
    /**
     * 
     * Title: partition  
     * Description: 
     * @param arrs 数组
     * @param L 左边界
     * @param R 右边界
     */
    public static int[] partition(int arrs[],int L,int R,int num) {
    	int left=L-1;
    	int right=R+1;
    	int center=L;
    	
    	//循环条件是，center小于right
    	while(center<right) {
    		if(arrs[center]<num) {
    			swap(arrs,++left,center++);
    		}
    		else if(arrs[center]>num) {
    			swap(arrs,--right,center);
    		}else {
    			center++;
    		}
    	}
    	
    	printArrs(arrs);
    	
    	return new int[] {left+1,right-1};
    	
    }
    
    //交换值
    public static void swap(int arrs[],int x,int y) {
    	int temp=arrs[x];
    	arrs[x]=arrs[y];
    	arrs[y]=temp;
    }
    
    //打印数组
    public static void printArrs(int [] array) {
    	for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+"  ");
			
		}
    	System.out.println();
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
