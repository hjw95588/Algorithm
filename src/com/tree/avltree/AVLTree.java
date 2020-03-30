package com.tree.avltree;

import java.util.Comparator;

public class AVLTree<E> extends BST<E> {

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
    //左旋
    private void rotateLeft(Node<E> grand){

        Node<E> parent=grand.right;

        Node<E> children=parent.left;

        grand.right=parent.left;
        parent.left=grand;

        afterRotate(grand,parent,children);

    }
    //右旋
    private void rotateRight(Node<E> grand){
        Node<E> parent=grand.left;
        Node<E> children=parent.right;

        grand.left=parent.right;
        parent.right=grand;

        afterRotate(grand,parent,children);

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

    //统一处理旋转  参数有点像中序遍历的意思
    //这里a,g其实可以不用处理，针对于AVL,红黑树
    private void rotate(
            //之前的根节点
            Node<E> beforeRootNode,
            Node<E> a, Node<E> b, Node<E> c,
            //d为最后旋转后的根节点
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g
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
        updateHeight(b);

        //e-f-g
        f.left=e;
        if(e!=null){
            e.parent=f;
        }
        f.right=g;
        if(g!=null){
            g.parent=f;
        }
        updateHeight(f);

        //b-d-f
        d.left=b;
        d.right=f;
        b.parent=d;
        f.parent=d;
        updateHeight(d);

    }

    /**
     * 删除之后，调整平衡
     * @param node
     */
    @Override
    protected void afterDel(Node<E> node) {
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

    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> children){
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

        //更新grand，parent的高度
        updateHeight(grand);
        updateHeight(parent);
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
