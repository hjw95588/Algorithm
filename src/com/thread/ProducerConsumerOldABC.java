package com.thread;

/**
 * 操作共享资源
 * 生产者和消费者  wait，notify版本
 * ABC 顺序输出
 * @author 1824633692@qq.com
 *
 */
public class ProducerConsumerOldABC {
    public static void main(String[] args) {
    	
    	SourceABC source=new SourceABC();
    	
		new Thread(()->{
			for(int x=0;x<10;x++){
				try {
					source.printA();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"A").start();
		new Thread(()->{
			for(int x=0;x<10;x++){
				try {
					source.printB();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"B").start();
		
		new Thread(()->{
			for(int x=0;x<10;x++){
				try {
					source.printC();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"C").start();
	}
}

//判断等待  业务 通知
//1A 2B 3C
class SourceABC{ //数字 资源类
	
	private  int num=1;
	public synchronized  void printA()throws InterruptedException{
		//避免虚假唤醒
		while(num!=1){
			this.wait();
		}
		
		num=2;
		System.out.println(Thread.currentThread().getName()+"  ");
		this.notifyAll(); 
	}
	
	public synchronized  void printB()throws InterruptedException{
		//避免虚假唤醒
		while(num!=2){
			this.wait();
		}
		
		num=3;
		System.out.println(Thread.currentThread().getName()+"  ");
		this.notifyAll(); 
	}
	
	public synchronized  void printC()throws InterruptedException{
		//避免虚假唤醒
		while(num!=3){
			this.wait();
		}
		
		num=1;
		System.out.println(Thread.currentThread().getName()+"  ");
		this.notifyAll(); 
	}
	
	
	
	
}


