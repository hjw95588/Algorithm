package com.thread;
import java.util.concurrent.locks.*;
/**
 * 操作共享资源
 * 生产者和消费者  wait，notify版本
 * @author 1824633692@qq.com
 *
 */
public class ProducerConsumerNewABC {
    public static void main(String[] args) {
    	
    	SourceLockABC source=new SourceLockABC();
    	
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
class SourceLockABC{ //数字 资源类
	private  int num=1;
	
	Lock lock=new ReentrantLock();
	Condition condition=lock.newCondition(); //await 替代wait，signal替代 notify
	

	public   void printA()throws InterruptedException{
		lock.lock();
		try{
			//避免虚假唤醒
			while(num!=1){
				condition.await();
			}
			num=2;
			System.out.print(Thread.currentThread().getName());
			condition.signalAll(); 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
		
	}
	
	public   void printB()throws InterruptedException{
		lock.lock();
		try{
			//避免虚假唤醒
			while(num!=2){
				condition.await();
			}
			num=3;
			System.out.print(Thread.currentThread().getName());
			condition.signalAll(); 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
		
	}
	
	public   void printC()throws InterruptedException{
		lock.lock();
		try{
			//避免虚假唤醒
			while(num!=3){
				condition.await();
			}
			num=1;
			System.out.print(Thread.currentThread().getName());
			condition.signalAll(); 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
		
	}
	
	
	
	
}


