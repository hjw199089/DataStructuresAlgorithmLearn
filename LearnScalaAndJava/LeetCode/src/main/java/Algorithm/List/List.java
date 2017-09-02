package Algorithm.List;

import java.util.Stack;
import java.util.regex.Matcher;

/**
 * Created by hjw on 17/8/30.
 */
public class List {

    ListNode head;

    public List(ListNode head) {
        this.head = null;
    }

    //====================

    public static ListNode createNodeWithString(String[] input) {
        if (input == null)
            return null;
        ListNode head = new ListNode(input[0]);
        ListNode point = head;
        int iter = 1;
        while (iter < input.length) {
            point.setNext(new ListNode(input[iter++]));
            point = point.getNext();
        }
        return head;
    }

    public static void printList(ListNode head) {
        if (head == null)
            return;
        ListNode point = head;
        while (point != null) {
            System.out.print(point.getElement().toString() + "-->");
            point = point.getNext();
        }
        System.out.println("NULL");
    }
    /*
    求链表的长度
     */
    public static int getLengthOfList(ListNode head){
        if (head== null)
            return 0;
        int len = 0;
        while(head != null){
            len++;
            head = head.getNext();
        }
        return len;
    }

    /*
        查找中间节点:快慢指针法
        head为链表的头部,tail为尾部(不包括该元素)
         */
    public static ListNode getMidNodeOfList(ListNode head, ListNode tail) {
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

    /*
    翻转链表
    (head)A-->B-->C-->D-->E-->NULL
    NULL<--A<--B<---C<--D<--E (head)
    pre = null <---cur(pre)   next(cur)
    伪代码:
    ListNode reverseList(ListNode head)
    若head为空,返回null
    pre = null
    cur = head
    next = null
    当head不为空时循环
        next = cur.next
        cur.next = pre
        if(cur.next == null) head = cur
        pre = cur.next
    返回head
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null)
            return head;
        ListNode preP = null;
        ListNode curP = head;
        ListNode nextP = null;
        while (curP != null) {
            if (curP.getNext() == null) {
                head = curP;
            }
            nextP = curP.getNext();
            curP.setNext(preP);
            preP = curP;
            curP = nextP;
        }
        return head;
    }
    /*
    查找单链表中的倒数第K个结点（k > 0）
     */
    public static ListNode getKthNodeOfList(ListNode head ,int k) {
        if (head == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.getNext() != null && k > 1) {
            fast = fast.getNext();
            k--;
        }
        if (k>1){
            return null;
        }
        while(fast.getNext() != null){
            fast = fast.getNext();
            slow = slow.getNext();
        }
        return slow;
    }
    /*
    从尾到头打印单链表
    方法1:栈存储
     */
    public static void reversePintListWithStack(ListNode head, Stack<ListNode> stack){
        if (head == null){
            while(!stack.isEmpty()){
                System.out.print(stack.pop().getElement() + "-->");
            }
            return;//注意递归必须有返回
        }
        stack.push(head);
        reversePintListWithStack(head.getNext(),stack);
    }
    /*
    从尾到头打印单链表-递归本身就是栈,可以将栈的存储去掉
     */
    public static void reversePintList(ListNode head){
        if (head == null){
            return;//注意递归必须有返回
        }
        reversePintList(head.getNext());
        System.out.print(head.getElement() + "-->"); //注意放在递归的后边
    }
    /*
    已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序
     */
    public static ListNode mergeSortedList(ListNode headA,ListNode headB){
        if (headA == null)
            return headB;
        if (headB  == null)
            return headA;
        ListNode tempNode = new ListNode();
        if (Integer.parseInt(headA.getElement().toString()) > Integer.parseInt(headB.getElement().toString())){
            tempNode.setElement(headB.getElement());
            headB = headB.getNext();
        }else{
            tempNode.setElement(headA.getElement());
            headA = headA.getNext();
        }
        tempNode.setNext(mergeSortedList(headA,headB));
        return tempNode;
    }
    /*
    判断一个单链表中是否有环
    快慢指针法,快的总比慢的多走一步,若快等于慢的说明有环
     */
    public static  boolean hasCircleInList(ListNode head){
        if (head == null)
            return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast!= null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (fast == slow){
                return true;
            }
        }
        return false;
    }

    /*
    求两个单链表相交的第一个节点
    ListA = 1-2-3-4----|
    Listb =     9-10---5-6-7-8
    链表单向的只能从头部开始遍历,因此相求出各自的长度,长者先走过多出来的,然后两者开始一起向前走,返回交点
     */
    public static ListNode getFisrtCrossNode(ListNode headA,ListNode headB){
        if (headA == null || headB == null)
            return null;

        ListNode pA = headA;
        ListNode pB = headB;
        int lenA = getLengthOfList(pA);
        int lenB = getLengthOfList(pB);

        int absSub = Math.abs(lenA - lenB);
        if (lenA > lenB){
            while(absSub > 0){
                pA = pA.getNext();
                absSub--;
            }
        }else{
            while (absSub > 0){
                pB = pB.getNext();
                absSub--;
            }
        }
        while (pA != null && pA != pB){
            pA = pA.getNext();
            pB = pB.getNext();
        }
        return pA;
    }

    public static void main(String[] args) {
        String[] listStr = {"1", "2", "3"};
        ListNode head = createNodeWithString(listStr);
        int len = getLengthOfList(head);
        System.out.println("链表的长度为:\t" + len);
        System.out.print("遍历打印链表:\t");
        printList(head);
        System.out.print("返回链表中间元素:\t");
        System.out.print(getMidNodeOfList(head, null) + "\t");
        System.out.println(getMidNodeOfList(head, head.getNext().getNext()));

        head = reverseList(head);
        System.out.print("翻转后的链表为:\t");
        printList(head);

        System.out.println("倒数第K个结点:\t" + getKthNodeOfList(head,2));

        String[] listStr1  = {"1", "2", "3","4"};
        head = createNodeWithString(listStr1);
        System.out.print("逆序打印链表(stack):\t");
        reversePintListWithStack(head,new Stack<ListNode>());
        System.out.print("\n逆序打印链表(no stack):\t");
        reversePintList(head);

        String[] listStr2  = {"3","4","6"};
        ListNode headA = createNodeWithString(listStr2);
        String[] listStr3  = {"1","5","7"};
        ListNode headB = createNodeWithString(listStr3);
        ListNode headRes = mergeSortedList(headA,headB);
        System.out.print("\n输出排序列表的合并结果:\t");
        printList(headRes);


        String[] listStr4  = {"1","5","7","8"};
        ListNode headCircle = createNodeWithString(listStr4);
        Boolean hasCircle = hasCircleInList(headCircle);
        System.out.print("判断链表是否有环:\t");
        System.out.print(hasCircle + "\t");
        ListNode startCirclr = headCircle.getNext();
        headCircle.getNext().getNext().getNext().setNext(startCirclr);
        hasCircle = hasCircleInList(headCircle);
        System.out.println(hasCircle);

        /*
        ListA = 1-2-3-4----|
        Listb =     9-10---5-6-null
         */
        String[] listStr5  = {"1","2","3","4"};
        ListNode head5 = createNodeWithString(listStr5);
        String[] listStr6  = {"9","10","5","6"};
        ListNode head6 = createNodeWithString(listStr6);
        head5.getNext().getNext().getNext().setNext(head6.getNext().getNext());
        System.out.println("----求两个单链表相交的第一个节点----");
        System.out.print("链表head5为:\t");
        printList(head5);
        System.out.print("链表head6为:\t");
        printList(head6);
        ListNode crossNode = getFisrtCrossNode(head5,head6);
        System.out.println("相交的第一个节点:\t" + crossNode);

    }


}
