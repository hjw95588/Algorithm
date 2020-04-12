package com.thread;

/**
 * 操作共享资源
 * 生产者和消费者  wait，notify版本
 * @author 1824633692@qq.com
 *
 */
public class ProducerConsumerOld {
    public static void main(String[] args) {
    	
    	Source source=new Source();
    	
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
class Source{ //数字 资源类
	private  int num=0;
	
	//增加
	public synchronized  void increment()throws InterruptedException{
		//避免虚假唤醒
		while(num!=0){
			this.wait();
		}
		
		num++;
		System.out.println(Thread.currentThread().getName()+"  "+num);
		this.notifyAll(); //通知其他线程 +1完毕
	}
	
	//减少
		public synchronized  void decrement()throws InterruptedException{
			while(num==0){
				this.wait();
			}
			
			num--;
			System.out.println(Thread.currentThread().getName()+"  "+num);
			this.notifyAll(); //通知其他线程 -1完毕
		}
	
	
}


