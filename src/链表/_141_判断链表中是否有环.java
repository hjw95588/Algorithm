package 链表;

public class _141_判断链表中是否有环 {

	/**
	 * https://leetcode-cn.com/problems/linked-list-cycle/
	 * 思路：快，慢指针
	 * 双指针 fast一次走两个，slow走一个，当两个相遇则有环
	 */
   public boolean hasCycle(ListNode head) {
	   if(head==null || head.next==null) return false;
	   
	   ListNode slow=head;
	   ListNode fast=head.next;
	   
	   while(fast!=null && fast.next!=null){
		  
		  slow=slow.next;
		  fast=fast.next.next;
		  if(fast==slow) return true;
	   }
        
	   return false;
    }
}
