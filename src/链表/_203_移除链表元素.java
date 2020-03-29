package 链表;

/**
 * 输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
 * @author 1824633692@qq.com
 *https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_移除链表元素 {
	
	//递归其实不是最优解
	public static ListNode removeElements(ListNode head, int val) {

		 if(head == null)
				return null;
			
			head.next = removeElements(head.next, val);
			return head.val == val ? head.next : head;

	  }
	
	public static ListNode remove2Elements(ListNode head, int val) {
		 ListNode dummyHead = new ListNode(-1); //建立虚拟节点
		 dummyHead.next=head;
		 
		 ListNode p=dummyHead;
		 
		 while(p.next!=null){
			 if(p.next.val!=val){
				 p=p.next;
			 }else{
				 p.next=p.next.next;
			 }
		 }
		 
	      return dummyHead.next;

	  }
	 
	 public static void main(String[] args) {
		ListNode a1=new ListNode(1);
		ListNode a2=new ListNode(2);
		ListNode a6=new ListNode(6);
		ListNode a3=new ListNode(3);
		ListNode a4=new ListNode(4);
		ListNode a5=new ListNode(5);
		ListNode a66=new ListNode(6);
		
		a1.next=a2;
		a2.next=a6;
		a6.next=a3;
		a3.next=a4;
		a4.next=a5;
		a5.next=a66;
		
		ListNode d=remove2Elements(a1, 6);
		
		System.out.println(d);
		
	}
	 
}
