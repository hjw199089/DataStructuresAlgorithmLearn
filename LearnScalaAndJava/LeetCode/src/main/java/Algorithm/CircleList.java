package Algorithm;

import java.util.List;

/**
 * Created by hjw on 17/8/16.
 */

/*
返回有环链表的环路开头第一个节点
假设K步之后开始入环,环长L
快指针走2K步,慢的走K步,快的入环K步,快的比慢的差L-K
快的追上慢的需要L-K步,此时相遇点处再一步步走K步到入环原点
 */
class ListNode{
    int value;
    ListNode next;
}
public class CircleList {
    public  ListNode findFistNodeOfCircle(ListNode n){
        ListNode fastNode = n;
        ListNode slowNode = n;

        while(fastNode != null && fastNode.next != null && fastNode != slowNode ){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }

        if(fastNode == null || fastNode.next == null){
            return null;
        }
        slowNode = n;
        while(fastNode != slowNode) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        return fastNode;
    }
}
