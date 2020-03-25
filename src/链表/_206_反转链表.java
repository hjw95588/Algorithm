package 链表;

public class _206_反转链表 {
/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
	//递归算法
	public static ListNode reverseList(ListNode head) {
		if(head==null || head.next==null) return head;
		ListNode p= reverseList(head.next);
		head.next.next=head;
		head.next=null;
		return p;
    }
	/**
	 * 普通方法，当时方法容易理解
	 * 1.避免链表断裂，所以建立了临时变量用于存储
	 * 2 无外乎就是来回交换
	 * @param head
	 * @return
	 */
	public static ListNode method2List(ListNode head) {
		if(head==null || head.next==null) return head;
		ListNode newHead=null;
		while(head!=null){
			ListNode temp=head.next; //临时变量用于保存节点信息，避免断裂
			head.next=newHead;
			newHead=head;
			head=temp;
		}
		return newHead;
    }
	
	public static void main(String [] args){
		ListNode a1=new ListNode(1);
		
		ListNode a2=new ListNode(2);
		ListNode a3=new ListNode(3);
		ListNode a4=new ListNode(4);
		ListNode a5=new ListNode(5);
		
		a5.next=a4;
		a4.next=a3;
		a3.next=a2;
		a2.next=a1;
		ListNode result=method2List(a5);
		System.out.println(result);
	}
	
	
}
