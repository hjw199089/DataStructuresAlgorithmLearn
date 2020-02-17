package BasicLearn.LeetCode.LineTab;

/**
 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class AddTwoNumbers002{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean flag = false;
        ListNode head = new ListNode(0);
        ListNode current = head;
        int carry = 0;
        ListNode p1 = l1,p2 = l2;

        while (p1 != null || p2 != null) {
            int x = (p1 != null)? p1.val:0;
            int y = (p2 != null)? p2.val:0;
            int sum = carry + x + y;
            carry = sum/10;
            current.next  = new ListNode(sum % 10);

            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        if (carry != 0) {
            ListNode ls = new ListNode(carry);
        }

        return head.next;
    }
}
