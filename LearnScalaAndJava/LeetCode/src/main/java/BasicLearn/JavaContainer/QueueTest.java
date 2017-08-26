package BasicLearn.JavaContainer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hjw on 17/8/26.
 java中使用队列:java.util.Queue
 要使用offer()来加入元素
 使用poll()来获取并移出元素。
 它们的优点是通过返回值可以判断成功与否
 LinkedList类实现了Queue接口，可以把LinkedList当成Queue来用。
 */
public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        queue.offer("fist");
        queue.offer("second");
        queue.offer("third");
        String str;
        System.out.println("队列的长度:\t" + queue.size());
        System.out.println("队列头部元素为:\t" + queue.peek());
        System.out.println("队列的遍历:");
        while((str = queue.poll()) != null){
            System.out.println(str);
        }
    }
}
