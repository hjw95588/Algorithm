package com.sort;

/**
 * 
* Title: QuickSortDemo  
* Description: 快速排序
* 快排1.0,2.0出现的问题，划分值打偏，时间复杂度为o(n2)
* 划分值，几乎中点的位置，时间复杂度为o(n*logn) 
* 因为每次是以最后一个数值为划分的
* 
* 快排1.0
* 
* 快排2.0，类似于荷兰国旗问题
* 
* 快排3.0
* 随机选一个值，概率事件
* 好的它的时间复杂度：o(n*logn)
* 空间复杂度好的情况logn
* 
* @author hjw
* @date 2021年3月6日 下午5:12:50
 */
public class QuickSortDemo {

	
	
	public static void main(String[] args) {
		int arrs[]= {2,4,6,8,10,12,1,5,3,7,5,14,5,0};
		
		quickSort(arrs, 0, arrs.length-1);
		printArrs(arrs);
		
	}
	
	public static void quickSort(int [] arrs,int L,int R) {
		
		
		//L<R,mi[0]这个值若为0，表示当时排好的，左边没有小于区域的，所以不需要比较
		if(L<R) {
			
			//L+(int)(Math.random()*(R-L+1))  这小值为L，最大值为R
			//随机选择一个值，交换之后作为右边的划分值，而不是上来就指定右边的划分值为R的下标值，这种做法的时间复杂度为o(n*logn),就是所谓的快排3.0版本
			swap(arrs, L+(int)(Math.random()*(R-L+1)), R);
			
			
			int[] mi=partition(arrs, L, R);
			
			//左边递归
			quickSort(arrs, L, mi[0]-1);
			
			//右边递归
			quickSort(arrs,mi[1]+1,R);
		}
		
		
	}
	
	//这是一个出路arr[L...R]的函数
	//默认是以arr[R] 做划分
	//返回是等于区域（左边界，右边界），所以返回一个长度为2的数组res,res[0],res[1]
	public static int[] partition(int [] arrs,int L,int R) {
		
		int less=L-1;
		int more=R;//注意看这个是R，属于荷兰国旗的问题的变种，因为表示的是当前的划分值
		
		/*int center=L;
		while(center<more) {
			if(arrs[center]<arrs[R]) {
				//放左边
				swap(arrs,++less,center++);
			}else if(arrs[center]>arrs[R]) {
				//放右边
				swap(arrs,--more,center);
			}else {
				//中间区域
				center++;
			}
			
		}*/
		
		//下面的写法，只是沿用了L，少引用一个变量
		while(L<more) {
			if(arrs[L]<arrs[R]) {
				//放左边
				swap(arrs,++less,L++);
			}else if(arrs[L]>arrs[R]) {
				//放右边
				swap(arrs,--more,L);
			}else {
				//中间区域
				L++;
			}
			
		}
		
		//注意循环完之后，需要将R位置的值(它是抽取出来和别人做比较的值，即是作为划分值存在)，和大于区域的第一个元素换位置，换完之后，
		//这样最后一个值，就处于等于区域了
		swap(arrs,more,R);
		
		//printArrs(arrs);
		
		//返回等于区域的2个下标值，1个是左边界，1个是右边界
		return new int[]{less+1,more};
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


