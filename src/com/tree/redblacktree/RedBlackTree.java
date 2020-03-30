package com.tree.redblacktree;

import java.util.Comparator;

/**
 * 红黑树
 */
public class RedBlackTree<E> extends BalanceBinarySearchTree<E>{

    private static  final Boolean RED=false;
    private static  final Boolean BLACK=true;

    public RedBlackTree(){
        this(null);
    }
    public RedBlackTree(Comparator comparator){
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    private static class RBNode<E> extends Node<E>{

        boolean color=RED; //默认添加红色（这样能保证满足，1,2,3,5条件，4可能不满足，后续进行调整）

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str="";
            if(color==RED){
                str="_R_";
            }
            return str+element.toString();
        }
    }

    //染色
    private Node<E> color(Node<E> node,Boolean color){
     if(node==null) return node;
        ((RBNode<E>)node).color=color;
     return node;
    }

    //染红色
    private Node<E> red(Node<E> node){
        return color(node,RED);
    }

    //染黑色
    private Node<E> black(Node<E> node){
        return color(node,BLACK);
    }

    //获取节点颜色
    private boolean colorOf(Node<E> node){
        return node==null?BLACK:((RBNode<E>)node).color;
    }


    private boolean isRed(Node<E> node){
        return colorOf(node)==RED;
    }

    private boolean isBlack(Node<E> node){
        return colorOf(node)==BLACK;
    }


    //删除节点之后  调整
    @Override
    protected void afterDel(Node<E> node, Node<E> replace) {
        //如果删除的节点是红色 不用处理
        if(isRed(node)) return;

        //如果用于取代node的子节点是红色
        if(isRed(replace)){
            black(replace);
            return;
        }

        //删除的是黑色叶子节点
        Node<E> parent=node.parent;

        //如果当前节点，是根节点，则直接返回不处理
        if(parent==null) return;

        /*考虑到，如果能走到这一步，表示传递的node是要删除的黑色叶子节点
        * 它本身被删除了，仅仅表示它父节点的左右不指向它，但是它的父节点还是存在的
        * 1.如果parent的left为null或者right为null，表示的它之前是父节点的，left或者right
        * 2.还有一种情况，下方内容在调整的时候，它的父节点，又上溢，继续需要执行该方法afterDel，
        * 所以判断为node.isLeftChild()
        * */
       boolean left= (parent.left==null|| node.isLeftChild() );
       Node<E> sibling=left?parent.right:parent.left; //左边为空，兄弟节点就是右边。反之，则为左边

       if(left){//被删除的节点在左边，兄弟节点在右边 和下方那种情况完全对称
           //如果兄弟节点是红色。则将转成兄弟节点是黑色的情况
           if(isRed(sibling)){
               black(sibling);
               red(parent);
               rotateLeft(parent);
               //之前的侄子节点变成了，兄弟节点(更换兄弟)
               sibling=parent.right;
           }
           //兄弟节点是黑色的情况  以下兄弟节点必然是黑色--------
           if(isBlack(sibling.left) && isBlack(sibling.right)){
               //兄弟节点没有一个黑色子节点 即不能借东西给（这里可能有人疑问，
               // 为啥不能有黑色节点呢，综合B树考虑，如果有的话，当前层不可能是最后一层）
               //不能借，那就合并
               boolean parentBlack=isBlack(parent);
               red(sibling);
               black(parent);
               //如果parent是黑色。删除则会下溢
               if(parentBlack){
                   afterDel(parent,null);
               }

           }else{
               //兄弟节点至少有一个红色节点，向兄弟节点借元素  三种情况。第一种先兄弟节点左旋转，在对父节点右旋转
               // (第二种，第三种都是父节点右旋转）
               if(isBlack(sibling.right)){
                   rotateRight(sibling); //兄弟左旋转
                   sibling=parent.right;//旋转一次后，需要变更兄弟节点，不然下方代码，不能统一用
               }
               color(sibling,colorOf(parent));//获取父节点之前的节点，颜色。用于之前的兄弟节点，继承当前颜色
               black(sibling.right);
               black(parent);
               rotateLeft(parent);
           }
       }else{ //被删除的节点在右边，兄弟节点在左边
           //如果兄弟节点是红色。则将转成兄弟节点是黑色的情况
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateRight(parent);
                //之前的侄子节点变成了，兄弟节点(更换兄弟)
                sibling=parent.left;
            }
            //兄弟节点是黑色的情况  以下兄弟节点必然是黑色--------
            if(isBlack(sibling.left) && isBlack(sibling.right)){
                //兄弟节点没有一个黑色子节点 即不能借东西给（这里可能有人疑问，
                // 为啥不能有黑色节点呢，综合B树考虑，如果有的话，当前层不可能是最后一层）
                //不能借，那就合并
                boolean parentBlack=isBlack(parent);
                red(sibling);
                black(parent);
                //如果parent是黑色。删除则会下溢
                if(parentBlack){
                    afterDel(parent,null);
                }

            }else{
              //兄弟节点至少有一个红色节点，向兄弟节点借元素  三种情况。第一种先兄弟节点左旋转，在对父节点右旋转
                // (第二种，第三种都是父节点右旋转）
              if(isBlack(sibling.left)){
                  rotateLeft(sibling); //兄弟左旋转
                  sibling=parent.left;//旋转一次后，需要变更兄弟节点，不然下方代码，不能统一用
              }
              color(sibling,colorOf(parent));//获取父节点之前的节点，颜色。用于之前的兄弟节点，继承当前颜色
              black(sibling.left);
              black(parent);
              rotateRight(parent);
            }
       }

    }

    //添加节点之后  调整
    @Override
    protected void afterAdd(Node<E> node) {

        Node<E> parent=node.parent;

        //添加共计12种情形，

        //如果是根节点 或者上溢到达了根节点
        if(parent==null){
            black(node);
            return ;
        }

        //1如果父节点是黑色，则直接返回 包含4种
        if(isBlack(parent)) return;

        Node<E> grand=parent.parent; //祖父节点
        Node<E> uncle=parent.sibling();//叔父节点

        //2如果叔父是红色节点  上溢了 4种
        if(isRed(uncle)){

            //2.1 parent、uncle 染成 BLACK
            black(parent);
            black(uncle);
            afterAdd(red(grand));//2.2 grand 向上合并 （把祖父节点当做新添加的节点）
            return ;
        }

        //3如果叔父不是红色节点   4种

        //判定条件：uncle 不是 RED
        //1. parent 染成 BLACK，grand 染成 RED
        //2. grand 进行单旋操作
        //LL：右旋转  1种
        //RR：左旋转  1种

        //判定条件：uncle 不是 RED
        //1. 自己染成 BLACK，grand 染成 RED
        //2. 进行双旋操作
        //LR：parent 左旋转， grand 右旋转 1种
        //RL：parent 右旋转， grand 左旋转 1种

        if(parent.isLeftChild()){//L
            red(grand);
            if(node.isLeftChild()){//LL
                black(parent);
            }else{//LR
              black(node);
              rotateLeft(parent);
            }
            rotateRight(grand);
        }else{//R
            red(grand);
            if(node.isLeftChild()){//RL
                black(node);
                rotateRight(parent);
            }else{//RR
                black(parent);
            }
            rotateLeft(grand);
        }


    }
}
