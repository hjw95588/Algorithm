package com.lianbiao;

//自定义双向循环链表
public class CircleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;
        public Node(Node<E> prev,E element, Node<E> next) {
            this.element = element;
            this.prev=prev;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public E get(int index) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        rangeCheckForAdd(index);

        //往最后添加元素
        if(index==size){

            Node<E> oldLast=last;

            Node<E> node=new Node<>(oldLast,element,first);//两根线

            if(oldLast==null){
                //考虑到刚开始添加节点，前方没节点的情况
                first=node;
                last=node;

                //再多两条线  都指向自己
                node.next=node;
                node.prev=node;

            }else{
                //另外两根线
                oldLast.next=node;
                last=node;

                //多一条线
                first.prev=node;
            }



        }else{
            Node<E> next=node(index);
            Node<E> prev=next.prev;
            Node<E> node=new Node<>(prev,element,next);
            next.prev=node;

            prev.next=node;
            if(index==0)//或者另一种写法  next==first
            {
                first=node;
            }

        }






        size++;
    }

    @Override
    public E remove(int index) {
        /*
         * 最好：O(1)
         * 最坏：O(n)
         * 平均：O(n)
         */
        rangeCheck(index);

        Node<E> node=null;
        if(size==1){
            //只有一个元素的删除
            first=null;
            last=null;
        }else{
             node=node(index);
            Node<E> prev=node.prev;
            Node<E> next=node.next;

            prev.next=next;
            next.prev=prev;

            //index==0 两种写法
            if(node==first){
                first=next;
            }

            //index==size-1;  两种写法
            if(node==last){
                last=prev;
            }
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;

                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;

                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 获取index位置对应的节点对象
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        //size>>1 => size/2
        if(index<(size>>1)){
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else{
            Node<E> node = last;
            for (int i = size-1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node);

            node = node.next;
        }
        string.append("]");

        return string.toString();
    }
}
