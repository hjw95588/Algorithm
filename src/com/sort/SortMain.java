package com.sort;

import com.suanfa.TimeTool;

public class SortMain {
    public static void main(String[] args) {
        int arrs[]={1,2,3,4,-1,-2,-3};
        TimeTool.check("共计耗时",()->{
        	mergeSortTest(arrs);
        });
        
        printArray(arrs);
    }

   /**
    * 冒泡排序。这个是其他地址的写法
    * 冒泡的意思，就是第一个和第二个比较，如果大于则换位置
    * 然后第二个和第三个比较，如果大于则换位置，依次类推，第一轮循环后的最大值在右边
    * 。。。。。。
    * @param arrs
    */
    public static void mao(int [] arrs){

        //注意条件，外层条件x<size-1
        //内层条件 y<size-1-x
        for(int x=0;x<arrs.length-1;x++){
            for(int y=0;y<arrs.length-x-1;y++){
                if(arrs[y]>arrs[y+1]){
                    exchange(arrs, y, y+1);
                }
            }

        }

        printArray(arrs);
    }
    
    
    /**
     * 这一种冒泡排序的写法，很好记，也很好理解
     * @param arrs
     */
    public static void bubbling(int [] arrs){

    	/**
    	 * 外层循环是从n-1,n-2...0一层变少的
    	 * 
    	 */
    	
        for(int x=arrs.length-1;x>0;x--){
            for(int y=0;y<arrs.length-1;y++){
                if(arrs[y]>arrs[y+1]){
                    exchange(arrs, y, y+1);
                }
            }

        }

        printArray(arrs);
    }
    
    /**
     * 归并排序
     * 左边的排好序，右边的排好序，加在一起再次排好序，那就是排好序的
     * 时间复杂度o(n*logn)
     * 
     */
    public static void mergeSortTest(int [] arrs) {
    	if(arrs==null || arrs.length<2) {
    		return ;
    	}
    	
    	//下面是递归排序的逻辑
    	sortProcess(arrs, 0,arrs.length-1);
    }
    
    
    /**
     * 注意一下，这里的left，right，center都是指的下标，可别搞混淆了
     * @param arrs
     * @param left
     * @param right
     */
    public static void sortProcess(int arrs[],int left,int right) {
    	if(left==right) {
    		return;//只有一个数不需要排序，直接返回
    	}
    	
    	int center=(left+right)/2; //中间位置
    	
    	sortProcess(arrs, left, center);//左边排好序
    	sortProcess(arrs, center+1,right);//右边排好序
    	
    	//merge的过程就是左右两边，合并排好序的过程
    	merge(arrs,left,center,right);
    }
    
    
    private static void merge(int arrs[], int left, int center, int right) {
		
    	/*
    	 * 左右两个，各定义一个所谓的指针（这里我们估且叫做指针），彼此比较，如果左边小于右边则左边放到辅助数组里，左边的指针下移一位
    	 * 反之亦然
    	 * 只要这个过程中，左右指针一直在移动，还没有出现任意一方越界那就属于正常的
    	 * 如果左方越界了，那就把右方剩余的数据，全部装载到辅助数组中（反之亦然）
    	 * */
    	
    	//准备一个辅助数组，装排好的数据
    	int [] aux=new int[right-left+1];
		
    	int p_left=left;
    	int p_right=center+1;
    	
    	int i=0;
    	
    	while(p_left<=center&&p_right<=right) {
    		aux[i++]=arrs[p_left]<arrs[p_right]?arrs[p_left++]:arrs[p_right++];
    	}
    	
    	while(p_left<=center) {
    		//表示，右边越界了,把左边的剩余的填入
    		aux[i++]=arrs[p_left++];
    	}
    	
    	while(p_right<=right) {
    		//表示，左边边越界了，把右边的剩余的填入
    		aux[i++]=arrs[p_right++];
    	}
    	
    	for(int a=0;a<aux.length;a++) {
    		arrs[left+a]=aux[a];
    	}
    	
	}

	/**
     * 
     * @param demo 数组
     * @param x
     * @param y
     */
    public static void exchange(int[] demo,int x,int y) {
    	int temp=demo[x];
    	demo[x]=demo[y];
    	demo[y]=temp;
    }
    
    /**
     * 简单输出数组
     * @param arrs
     */
    public static void printArray(int [] arrs) {
    	if(arrs!=null && arrs.length>1) {
    		for(int x=0;x<arrs.length;x++) {
    			System.out.print(arrs[x]+"_");
    		}
    		System.out.println();
    	}
    }
    
    
    
}
