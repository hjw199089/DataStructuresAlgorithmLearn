package Algorithm.List;

/**
 * Created by hjw on 17/8/30.
 */
public class List {

    ListNode head;

    public List(ListNode head) {
        this.head = null;
    }

    //====================
    public static ListNode createNodeWithString(String[] input){
        if (input == null)
            return null;
        ListNode head = new ListNode(input[0]);
        ListNode point = head;
        int iter = 1;
        while(iter < input.length){
            point.setNext(new ListNode(input[iter++]));
            point = point.getNext();
        }
        return head;
    }

    public static void printList(ListNode head){
        if (head == null)
            return;
        ListNode point = head;
        while(point != null){
            System.out.print(point.getElement() + "-->");
            point = point.getNext();
        }
        System.out.println("NULL");
    }

    /*
        查找中间节点:快慢指针法
        head为链表的头部,tail为尾部(不包括该元素)
         */
    public static ListNode getMidNodeOfList(ListNode head,ListNode tail) {
        if (head == tail)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.getNext() != tail && fast.getNext().getNext() != tail) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    public static void main(String[] args) {
        String[] listStr = {"1","2","3"};
        ListNode head = createNodeWithString(listStr);
        printList(head);
        System.out.println(getMidNodeOfList(head,null));
        System.out.println(getMidNodeOfList(head,head.getNext().getNext()));
    }


}
