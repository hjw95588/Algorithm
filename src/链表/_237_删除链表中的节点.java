package 链表;

public class _237_删除链表中的节点 {
	
	/**https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
	 * 这个因为只有下一个节点，
	 * 第一个节点，第二个节点，第三个节点，第四个节点
	 * 假如我们要删除第二个节点，把第二个节点的值变成第三个节点的值。
	 * 第二个节点的next指向第四个节点
	 * @param node
	 */
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}
