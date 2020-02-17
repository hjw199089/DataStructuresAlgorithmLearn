package Algorithm.List;

import java.util.List;

/**
 * Created by hjw on 17/8/30.
 */
public class ListNode {
    //节点对象
    private Object element;
    //下一元素指针
    private ListNode next;

    public ListNode() {
        this.element = null;
        this.next = null;
    }

    public ListNode(Object element) {
        this.element = element;
        this.next = null;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "element=" + element +
                '}';
    }
}
