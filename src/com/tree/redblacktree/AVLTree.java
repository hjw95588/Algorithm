package com.tree.redblacktree;

import java.util.Comparator;

/**
 * AVL树
 * @param <E>
 */
public class AVLTree<E> extends BalanceBinarySearchTree<E> {

    public AVLTree(){
        this(null);
    }
    public AVLTree(Comparator comparator){
        super(comparator);
    }

    //注意思考：新添加的节点，必然是叶子节点-----------
    @Override
    protected void afterAdd(Node<E> node) {
       while((node=node.parent)!=null){

          if(isBanlanced(node)){
            //如果是平衡的，则更新高度
              updateHeight(node);
          }else{
            //恢复平衡
              rebalance2(node);
              //整棵树平衡
              break;
          }
       }
    }

    /**
     * 恢复平衡
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance(Node<E> grand){
        Node<E> parent=((AVLNode<E>)grand).tallerChild();
        Node<E> node=((AVLNode<E>)parent).tallerChild();

        if(parent.isLeftChild()){ //L
            if(node.isLeftChild()){//LL
                rotateRight(grand);
            }else{//LR
               rotateLeft(parent);
               rotateRight(grand);
            }
        }else{
            //R
            if(node.isLeftChild()){//RL
                rotateRight(parent);
                rotateLeft(grand);

            }else{//RR
                rotateLeft(grand);
            }
        }

    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> children) {
        super.afterRotate(grand, parent, children);
        //更新grand，parent的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 恢复平衡 第二种方法
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance2(Node<E> grand){
        Node<E> parent=((AVLNode<E>)grand).tallerChild();
        Node<E> node=((AVLNode<E>)parent).tallerChild();

        if(parent.isLeftChild()){ //L
            if(node.isLeftChild()){//LL
                rotate(grand,node.left,node,node.right,parent,parent.right,grand,grand.right);
            }else{//LR
                rotate(grand,parent.left,parent,node.left,node,node.right,grand,grand.right);
            }
        }else{
            //R
            if(node.isLeftChild()){//RL
                rotate(grand,grand.left,grand, node.left,node,node.right,parent,parent.right);

            }else{//RR
                rotate(grand,grand.left,grand, parent.left,parent,node.left,node,node.right);
            }
        }

    }

    @Override
    protected void rotate(Node<E> beforeRootNode, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(beforeRootNode, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /**
     * 删除之后，调整平衡
     * @param node
     */
    @Override
    protected void afterDel(Node<E> node,Node<E> replace) {
        //node的parent 线是没断的。只不过删除之后，它的父类的左右子树的线，不在指向它
        while((node=node.parent)!=null){

            if(isBanlanced(node)){
                //如果是平衡的，则更新高度
                updateHeight(node);
            }else{
                //恢复平衡   较添加有区别
                rebalance2(node);

            }
        }
    }




    //更新某个节点的高度
    private void updateHeight(Node<E> node){
        AVLNode<E> _node=(AVLNode<E>)node;
        _node.updateHeight();
    }

    //判断是否平衡 负载因子绝对值《=1 即为平衡
    private boolean isBanlanced(Node<E> node){
        AVLNode<E> _node=(AVLNode<E>)node;
      return Math.abs((_node).balanceFactor())<=1;
    }
    //基于AVL树的节点
    private static class AVLNode<E> extends Node<E>{
        int height=1;//高度  默认传递过来的叶子节点，高度为1

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //计算平衡因子
        public int balanceFactor(){
            int leftHeight= left==null?0:((AVLNode<E>)left).height; //左子树高度
            int rightHeight= right==null?0:((AVLNode<E>)right).height;//右子树高度
            return leftHeight-rightHeight;
        }

        //更新自己节点的高度  1+左右节点的高度
        public void updateHeight(){
            int leftHeight= left==null?0:((AVLNode<E>)left).height; //左子树高度
            int rightHeight= right==null?0:((AVLNode<E>)right).height;//右子树高度

            //计算出当前节点的高度，设置给当前节点  这一步刚才漏掉了，导致更新高度没生效
            height=1+Math.max(leftHeight,rightHeight);
        }

        //比较左右子树哪个高。。返回高的那一个，如果相等，则返回同方向的那个
        public Node<E> tallerChild(){
            int leftHeight= left==null?0:((AVLNode<E>)left).height; //左子树高度
            int rightHeight= right==null?0:((AVLNode<E>)right).height;//右子树高度
            if(leftHeight>rightHeight) return left;
            else if(leftHeight<rightHeight) return right;
            else return isLeftChild()?left:right;
        }

        @Override
        public String toString() {
            String parentString = "null";
            //注意这个判断，我又弄错了
            if (parent != null) {
                parentString = parent.element.toString();
            }
            return element + "_p(" + parentString + ")"+"h("+height+")";
        }
    }

    @Override
    protected Node createNode(Object element, Node parent) {
        return new AVLNode<>(element,parent);
    }


}
