package com.tree.binarysearchtree;

import java.util.Comparator;

import com.tree.binarysearchtree.printer.BinaryTrees;

/**
 * http://520it.com/binarytrees/
 */
public class Main {
	


	static void test1() {
		Integer data[] = new Integer[] {
				8,4,13,2,6,10,1,3,5,7,9,12,11
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		bst.remove(4);
		System.out.println("-------------------------------------------------");
		BinaryTrees.println(bst);

		/*System.out.println("------前序---------");
		bst.preorderTraversalMethod();//前序
		System.out.println("----------------");

		System.out.println("------中序---------");
		bst.inorderTraversalMethod();//中序
		System.out.println("----------------");

		System.out.println("--------后序-------");
		bst.postorderTraversalMethod();//后序
		System.out.println("----------------");

		System.out.println("---------层序遍历--------");
		bst.levelorderTraversalMethod();


		System.out.println("\r\n"+"-------------递归算二叉树的高度----------");
		bst.treeHeighMethod();

		System.out.println(bst.levelorderTraversalDealHeight());*/



	}
	

	
	public static void main(String[] args) {
		test1();
	}

}
