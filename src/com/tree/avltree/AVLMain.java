package com.tree.avltree;

import com.tree.binarysearchtree.BinarySearchTree;
import com.tree.binarysearchtree.printer.BinaryTrees;

/**
 * http://520it.com/binarytrees/
 */
public class AVLMain {
	


	static void test1() {
		Integer data[] = new Integer[] {
				1,2,3,4,5,6
		};
		
		AVLTree<Integer> bst = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		bst.remove(4);

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
