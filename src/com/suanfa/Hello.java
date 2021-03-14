package com.suanfa;
class A{
    static{
        System.out.print("1");
    }
    public A(){
        System.out.print("2");
    }
}
class B extends A{
    static{
        System.out.print("a");
    }
    public B(){
        System.out.print("b");
    }   
}
public class Hello{
    public static void main(String[] ars){
         new B(); //执行到此处,
 		 new B(); //执行到此处,
    }
}
