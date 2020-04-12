package com.sort;

import com.suanfa.TimeTool;

public class SortMain {
    public static void main(String[] args) {
        int demo[]={11,23,45,2,3,6,7,34,22,88,65};



        TimeTool.check("共计耗时",new TimeTool.Task(){
            @Override
            public void deal() {
                mao(demo);
            }
        });
    }

    //冒泡排序
    public static void mao(int [] demo){

        //注意条件，外层条件x<size-1
        //内层条件 y<size-1-x
        for(int x=0;x<demo.length-1;x++){
            for(int y=0;y<demo.length-x-1;y++){
                if(demo[y]>demo[y+1]){
                    int temp=demo[y];
                    demo[y]=demo[y+1];
                    demo[y+1]=temp;
                }
            }

        }

        for (int x=0;x<demo.length;x++){
            System.out.print(demo[x]+"_");
        }
    }
}
