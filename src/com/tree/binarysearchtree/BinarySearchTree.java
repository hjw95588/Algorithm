package com.tree.binarysearchtree;

import com.tree.binarysearchtree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二差搜索树
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    Comparator comparator; //比较器
    public BinarySearchTree(Comparator comparator){
        this.comparator=comparator;
    }
    public BinarySearchTree(){
       this(null);
    }

    private int size;
    private Node<E> root; //根节点

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).leftNode;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).rightNode;
    }

    @Override
    public Object string(Object node) {
        Node<E> _node=((Node<E>)node);

        String newStr=_node.element+"";
        if(_node.parentNode==null){
            newStr=newStr+"[null]";
        }else{
            newStr=newStr+" p["+_node.parentNode.element+"]";
        }

        return newStr;
    }

    private static class Node<E>{
        E element;
        Node<E> leftNode;
        Node<E> rightNode;
        Node<E> parentNode;
        public  Node(E element,Node<E> parentNode){
            this.element=element;
            this.parentNode=parentNode;
        }
        //叶子节点
        public boolean isLeaf(){
            return leftNode==null && rightNode==null;
        }
        //度为2
        public boolean hasTwoChildren(){
            return leftNode!=null && rightNode!=null;
        }
    }

    //前驱节点
    public Node<E> predecessor(Node<E> node){

        if(node==null) return node;

        /*1 node.left!=null ;predecessor=node.left.right.right....right
        * 循环终止条件：right 为null
        * 前驱节点 在左子树当中
        * */
        Node<E> p=node.leftNode;
       if(p!=null){
           while(p.rightNode!=null){
               p=p.rightNode;
           }
           return p;
       }

       /*
       * 2.node.left为null node.parent 不为null
       * 从父节点，祖父节点中找前驱节点
       * */
       while(node.parentNode!=null && (node==node.parentNode.leftNode)){
           node=node.parentNode;
       }
       //node.parent ==null 返回null
       //node.parent.right==node 返回 node.parent
       return node.parentNode;

    }

    //后继节点
    public Node<E> successor(Node<E> node){

        if(node==null) return node;

        /*1 node.right!=null ;predecessor=node.right.left.left....left
         * 循环终止条件：left 为null
         * 前驱节点 在右子树当中
         * */
        Node<E> p=node.rightNode;
        if(p!=null){
            while(p.rightNode!=null){
                p=p.rightNode;
            }
            return p;
        }

        /*
         * 2.node.right为null node.parent 不为null
         * 从父节点，祖父节点中找后继节点
         * */
        while(node.parentNode!=null && (node==node.parentNode.rightNode)){
            node=node.parentNode;
        }
        //node.parent ==null 返回null
        //node.parent.left==node 返回 node.parent
        return node.parentNode;

    }

    //前序遍历  根节点，前序遍历左子树，前序遍历右子树
    public  void preorderTraversalMethod(){
        preorderTraversal(root);
    }

    private  void preorderTraversal(Node<E> node){
        if(node!=null) {
            System.out.print(node.element + ",");
            preorderTraversal(node.leftNode);
            preorderTraversal(node.rightNode);
        }
    }

    //中序遍历  中序遍历左子树，根节点，中序遍历右子树
    public  void inorderTraversalMethod(){
        inorderTraversal(root);
    }

    private  void inorderTraversal(Node<E> node){
        if(node!=null) {

            inorderTraversal(node.leftNode);
            System.out.print(node.element + ",");
            inorderTraversal(node.rightNode);
        }
    }

    //后序遍历  后序遍历左子树，后序遍历右子树，根节点
    public  void postorderTraversalMethod(){
        postorderTraversal(root);
    }

    private  void postorderTraversal(Node<E> node){
        if(node!=null) {

            postorderTraversal(node.leftNode);
            postorderTraversal(node.rightNode);
            System.out.print(node.element + ",");
        }
    }

    //层序遍历
    public  void levelorderTraversalMethod(){
        if(root==null) return;
        Queue<Node> queue=new LinkedList<>();

        //添加根节点
        queue.offer(root);

        while(!queue.isEmpty()){
            //出队
            Node<E> node=queue.poll();
            System.out.print(node.element+",");
            //出队的同时，将自己的左右子节点，入队
            if(node.leftNode!=null) {
             queue.offer(node.leftNode);
            }
            if(node.rightNode!=null){
                queue.offer(node.rightNode);
            }
        }

    }

    //计算二叉树的高度(深度)
    //1 递归
    public void  treeHeighMethod(){
        System.out.println(treeHeigh(root));
    }
    private int treeHeigh(Node<E> node){
        if(node==null) return 0;

      return Math.max(treeHeigh(node.leftNode),treeHeigh(node.rightNode))+1;
    }

    //2迭代 考虑到层序遍历
    public  int levelorderTraversalDealHeight(){
        if(root==null) return 0;
        Queue<Node<E>> queue=new LinkedList<>();
        //添加根节点
        queue.offer(root);
        int height=0;//树的高度
        int levelSize=1; //每一层的元素数量。默认为1个
        while(!queue.isEmpty()){
            //出队
            Node<E> node=queue.poll();
            //处理逻辑 核心要点：每一层访问完了，下一层的长度就是队列的size
            levelSize--; //当出队一个元素，每一层的个数减少一次，一旦变成0，表示当前层访问完了
            //出队的同时，将自己的左右子节点，入队
            if(node.leftNode!=null) {
                queue.offer(node.leftNode);
            }
            if(node.rightNode!=null){
                queue.offer(node.rightNode);
            }
            if(levelSize==0){
                //意味着即将访问下一层
                levelSize=queue.size();
                height++;
            }
        }
        return height;
    }

    public void add(E element) // 添加元素
    {
        elementNotNull(element);
        //没有根节点  添加的第一个节点
       if(root==null){
           root=new Node<>(element,null);
           size++;
           return;
       }

       //添加的不是第一个节点
        //循环进行比较 start
        Node<E> node=root;
       Node<E> parentNode=root; //记录父节点
        int con=0; //记录是添加的父节点的左边or右边
       //跳出循环的条件是node为空
       while (node!=null){
           con=compare(element,node.element);
           parentNode=node;
           if(con>0){
               //开始追击右边
               node=node.rightNode;
           }else if(con<0){
               //开始追击左边
               node=node.leftNode;
           }else{
               //相等 覆盖
               node.element=element;
               return ;
           }
       }
        //循环进行比较 end

        //看看插入到父节点的哪个位置
        Node<E> newNode=new Node<>(element,parentNode);
        if(con>0){
            parentNode.rightNode=newNode;
        }else if(con<0){
            parentNode.leftNode=newNode;
        }
        size++;
    }

    /*添加元素进行比较  a1插入的值，a2原本节点的值
    * 返回0 a1,a2相等
    * 返回值大于0  a1大于a2
    * 返回值小于0  a1小于a2
    * */
    private int compare(E a1,E a2){
        if(comparator!=null){
            return comparator.compare(a1,a2);
    }
        return ((Comparable)a1).compareTo(a2);
    }

    private void elementNotNull(E element){
        if(element==null){
            throw new RuntimeException("非法参数异常");
        }
    }

    //元素数量
    public int size(){
       return size;
    }

    //是否为空
    public  boolean isEmpty(){
        return true;
    }

    //删除元素
    public void remove(E element){
       remove(node(element));
    }

    //根据传递的数值，获取树中的节点
    public Node<E> node(E element){
        Node<E> node=root;
        while(node!=null){
            int cmp=compare(element,node.element);
            if(cmp==0) return node;
            else if(cmp>0){
                node=node.rightNode;
            }else{
                node=node.leftNode;
            }
        }
        //走到这一步，表示没找到对应的节点，返回null
        return null;
    }

    //删除的逻辑
    private void remove(Node<E> node){
       if(node==null) return;
       size--;

       //如果删除的节点  度为2    树中节点个数=度为0个数+度为1个数+度为2个数
        //1 找到前驱或者后继 2替换其值，删除前驱或者后继节点
       if(node.hasTwoChildren()){
            //找到前驱或者后继节点，这里我考虑找前驱节点
           Node<E> prev=predecessor(node);
           node.element=prev.element;
           node=prev; //node指向prev，作为引用，后续，只需要删除node就行
       }

       //删除node节点 能来到这表示node的度必然是1或者0
        Node<E> replaceNode=node.leftNode!=null?node.leftNode:node.rightNode;
       if(replaceNode!=null){
           //node的度为1

           //替代的节点.parent=删除节点.parent
           replaceNode.parentNode=node.parentNode;
           if(node.parentNode==null){
               //1如果当前删除的节点，为根节点
               root=replaceNode;
           }else if(node==node.parentNode.leftNode){
               //2如果删除节点，在其父节点的左边
               node.parentNode.leftNode=replaceNode;
           }else if(node==node.parentNode.rightNode){
               //3如果删除节点，在其父节点的右边
               node.parentNode.rightNode=replaceNode;
           }


       }else{
           //node的度为0 叶子节点


           if(node.parentNode==null)
           {//1 既是叶子节点又是根节点
               root=null;
           }else if(node==node.parentNode.leftNode){
               //2 非根节点，左叶子节点
               node.parentNode.leftNode=null;
           }else if(node==node.parentNode.rightNode){
               //3 非根节点 ，右叶子节点
               node.parentNode.rightNode=null;
           }
       }

    }


    //清空所有元素
    public void clear(E element){
         root=null;
         size=0;
    }

    //是否包含某元素
    public  boolean contains(E element){
       Node<E> node=node(element);
        return node==null?false:true;
    }
}
