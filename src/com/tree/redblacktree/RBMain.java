package com.tree.redblacktree;

import com.tree.avltree.AVLTree;
import com.tree.binarysearchtree.printer.BinaryTrees;

/**
 * http://520it.com/binarytrees/
 */
public class RBMain {



	static void test1() {
		Integer data[] = new Integer[] {
				1,2,3,4,5,6
		};

		RedBlackTree<Integer> bst = new RedBlackTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
		for (int i = 0; i < data.length; i++) {
			System.out.println(i+"    "+data[i]);
			bst.remove(data[i]);

			BinaryTrees.println(bst);
		}




	}
	

	
	public static void main(String[] args) {
		test1();
	}

}
