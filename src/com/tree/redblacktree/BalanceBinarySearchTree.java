package com.tree.redblacktree;

import com.tree.avltree.BinaryTree;

import java.util.Comparator;

/**
 * 平衡二叉搜索树
 * 较avltree 文件夹的内容相比，进行了代码的调整
 * 在二叉搜索树的基础上，增加了旋转的功能
 */
public class BalanceBinarySearchTree<E> extends BinarySearchTree<E>{

    public BalanceBinarySearchTree(){
        this(null);
    }
    public BalanceBinarySearchTree(Comparator comparator){
        super(comparator);
    }

    //左旋
    protected void rotateLeft(Node<E> grand){

        Node<E> parent=grand.right;

        Node<E> children=parent.left;

        grand.right=parent.left;
        parent.left=grand;

        afterRotate(grand,parent,children);

    }
    //右旋
    protected void rotateRight(Node<E> grand){
        Node<E> parent=grand.left;
       Node<E> children=parent.right;

        grand.left=parent.right;
        parent.right=grand;

        afterRotate(grand,parent,children);

    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> children){
        //让parent成为树的根节点
        parent.parent=grand.parent;
        if(grand.isLeftChild()){
            grand.parent.left=parent;
        }else if(grand.isRightChild()){
            grand.parent.right=parent;
        }else{
            //grand原本是root节点
            root=parent;
        }

        //更新children的parent
        if(children!=null){
            children.parent=grand;
        }

        //更新grand的parent
        grand.parent=parent;


    }

    //统一处理旋转  参数有点像中序遍历的意思
    //这里a,g其实可以不用处理，针对于AVL,红黑树
    protected void rotate(
            //之前的根节点
            Node<E> beforeRootNode,
           Node<E> a,Node<E> b, Node<E> c,
            //d为最后旋转后的根节点
           Node<E> d,
           Node<E> e,Node<E> f, Node<E> g
    ){

        //先处理d
        d.parent=beforeRootNode.parent;
        if(beforeRootNode.isLeftChild()){
            //如果beforeRootNode 是父节点的左孩子
            beforeRootNode.parent.left=d;
        }else if(beforeRootNode.isRightChild()){
            beforeRootNode.parent.right=d;
        }else{
            //没有父节点
            root=d;
        }

        //a-b-c
        b.left=a;
        if(a!=null){
            a.parent=b;
        }
        b.right=c;
        if(c!=null){
            c.parent=b;
        }


        //e-f-g
        f.left=e;
        if(e!=null){
            e.parent=f;
        }
        f.right=g;
        if(g!=null){
            g.parent=f;
        }


        //b-d-f
        d.left=b;
        d.right=f;
        b.parent=d;
        f.parent=d;



    }


}
