package com.thread;
import java.util.concurrent.locks.*;
/**
 * 操作共享资源
 * 生产者和消费者  wait，notify版本
 * @author 1824633692@qq.com
 *
 */
public class ProducerConsumerNew {
    public static void main(String[] args) {
    	
    	Source2 source=new Source2();
    	
		new Thread(()->{
			for(int x=0;x<10;x++){
				try {
					source.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"A").start();
		new Thread(()->{
			for(int x=0;x<10;x++){
				try {
					source.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"B").start();
	}
}

//判断等待  业务 通知
class Source2{ //数字 资源类
	private  int num=0;
	
	Lock lock=new ReentrantLock();
	Condition condition=lock.newCondition(); //await 替代wait，signal替代 notify
	
	//增加
	public   void increment()throws InterruptedException{
		lock.lock();
		try{
			//避免虚假唤醒
			while(num!=0){
				condition.await();
			}
			num++;
			System.out.println(Thread.currentThread().getName()+"  "+num);
			condition.signalAll(); //通知其他线程 +1完毕
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		
		
	}
	
	//减少
		public synchronized  void decrement()throws InterruptedException{
			lock.lock();
			try{
				while(num==0){
					condition.await();
				}
				
				num--;
				System.out.println(Thread.currentThread().getName()+"  "+num);
				condition.signalAll(); //通知其他线程 -1完毕
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			
			
		}
	
	
}


