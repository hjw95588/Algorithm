package com.suanfa;

/**
 * 自定义LinkList
 * @author 1824633692@qq.com
 *
 */
public class LinkList<E> {

	private int size; //个数
	private Node<E> firstNode; //头节点
	
	
	//内部类  节点信息
	private class Node<E>{
		E element; //值
		Node<E> nextNode; //下一个节点信息
		public Node(E element,Node<E> nextNode){
			this.element=element;
			this.nextNode=nextNode;
		}
	}
	
	public E remove(int x){
		Node<E> nowNode=getNodeByIndex(x);
		if(x==0){
			firstNode=firstNode.nextNode;
		}else{
			Node<E> prevNode=getNodeByIndex(x-1);
			prevNode.nextNode=prevNode.nextNode.nextNode;
		}
		size--;
		return nowNode.element;
	}
	
	public int indexOf(E element){
		//如果传递的值为null
		Node<E> node=firstNode;
		if(element==null){
			for(int x=0;x<size;x++){
				if(node.element==null) return x;
				node=node.nextNode;
			}
		}else{
			for(int x=0;x<size;x++){
				if(element.equals(node.element)) return x;
				node=node.nextNode;
			}
		}
		return 0;
	}
	
	//清除
   public void clear(){
		size=0;
		firstNode=null;
	}
	
	public void add(int x,E element){
		valIndexMethod(x);
		if(x==0){
			firstNode=new Node<E>(element, firstNode);
		}else{
			//前一个节点
			Node<E> prevNode=getNodeByIndex(x-1);
			
			prevNode.nextNode=new Node<E>(element, prevNode.nextNode);
		}
		size++;
	}
	
	public void add(E element){
		add(size,element);
	}
	
	public E set(int x,E element){
		Node<E> node= getNodeByIndex(x);
		E old=node.element;
		
		node.element=element;
		
		return old;
	}
	
    public E get(int x){
		return getNodeByIndex(x).element;
	}
    
    //index下标，获取当前节点
    private Node<E> getNodeByIndex(int index){
    	valIndex(index);
    	Node<E> node=firstNode;
    	for(int x=0;x<index;x++){
    		node=node.nextNode;
    	}
    	return node;
    }
    
    //校验参数是否符合规定 
    private void valIndex(int index){
    	if(index<0 || index>=size){
    		throw new RuntimeException("index不合理   "+index+"   size："+size);
    	}
    }
    
    //添加的有参数index
    private void valIndexMethod(int index){
    	if(index<0 || index>size){
    		throw new RuntimeException("index不合理   "+index+"   size："+size);
    	}
    }
    
    public int size(){
    	return size;
    }
}
