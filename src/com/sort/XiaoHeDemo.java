package com.sort;

import com.suanfa.TimeTool;

/**
 * 小和问题
 * 可以用归并排序
 * 所谓的小和，例子
 * 1,2,3,4,5
 * 
 * 1左边没有比1小的值  
 * 2左边1比2小                              1
 * 3左边1,2比3小                    1+2
 * 4左边1,2,3小          1+2+3
 * 5左边1,2,3,4小    1+2+3+4
 * 
 * 所有的小和1+（ 1+2）+（1+2+3）+（1+2+3+4）
 * @author 1824633692@qq.com
 *
 */
public class XiaoHeDemo {

	public static void main(String[] args) {
		int arrs[]={1,2,4,3,5,7};
	        TimeTool.check("共计耗时",()->{
	        	mergeSortTest(arrs);
	        });
	        
	        printArray(arrs);
	}
	
	 public static void mergeSortTest(int [] arrs) {
	    	if(arrs==null || arrs.length<2) {
	    		return ;
	    	}
	    	
	    	//下面是递归排序的逻辑
	    	int all=sortProcess(arrs, 0,arrs.length-1);
	    	System.out.println(all);
	    }
	
	 public static int sortProcess(int arrs[],int left,int right) {
	    	if(left==right) {
	    		return 0;
	    	}
	    	
	    	int center=(left+right)/2; //中间位置
	    	
	   return  sortProcess(arrs, left, center)  //左边小和统计一下
	          +sortProcess(arrs, center+1,right) //右边小和统计一下
	        +merge(arrs,left,center,right);   //最后左右，没炸的小和继续炸一下
	    }
	    
	    
	    private static int merge(int arrs[], int left, int center, int right) {
			
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
	    	
	    	int all=0;
	    	
	    	while(p_left<=center&&p_right<=right) {
	    		
	    		/**
	    		 * 这里需要注意一下，巧妙的思想
	    		 * 因为左右两边，都是靠着彼此的指针，滑动着。就是意味着每个数都被扫过了
	    		 * 举个例子，左边下标1的值，小于右边下标3的值，表示右边下标3后面的值，都会别1大,
	    		 * 所以这里小和，右边下标3.。。Right的小标，这个区间的个数都会炸一次1的角标值
	    		 * 即（个数）* 左边下标1的值
	    		 */
	    		//all=all+(arrs[p_left]<arrs[p_right]?(right-p_right+1)*arrs[p_left]:0);
	    		all+=(arrs[p_left]<arrs[p_right]?(right-p_right+1)*arrs[p_left]:0);
	    		
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
	    	
	    	return all;
	    	
		}
	
	 public static void printArray(int [] arrs) {
	    	if(arrs!=null && arrs.length>1) {
	    		for(int x=0;x<arrs.length;x++) {
	    			System.out.print(arrs[x]+"_");
	    		}
	    		System.out.println();
	    	}
	    }
}
