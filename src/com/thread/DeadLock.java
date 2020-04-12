package com.thread;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * @author 1824633692@qq.com
 *
 */
public class DeadLock {

	public static void main(String[] args) {
		String lockA="lockA";
		String lockB="lockB";
		
		new Thread(new MyRunnable(lockA, lockB)).start();
		new Thread(new MyRunnable(lockB, lockA)).start();
	}
	
	
}

class MyRunnable implements Runnable{

	
	private String lockA;
	private String lockB;
	
	public MyRunnable(String lockA,String lockB){
		this.lockA=lockA;
		this.lockB=lockB;
	}
	
	@Override
	public void run() {
		synchronized (lockA){
			System.out.println(Thread.currentThread().getName() +
			"lock:"+lockA+"=>get"+lockB);
			try {
			TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
			synchronized (lockB){
			System.out.println(Thread.currentThread().getName() +
			"lock:"+lockB+"=>get"+lockA);
			}
		}
	}
	
}
