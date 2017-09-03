package Algorithm.Basic;

/**
 * Created by hjw on 17/9/3.
 */
public class Basic {
    /*
    亦或运算交换两个数
     */
    public static void swap(int first,int second){
        first = first^second;
        second = first^second;
        first = first^second;
        System.out.println("交换两个数:\t" + first + "--" + second);
    }

    /*
    将一个数二进制中最右边一个1置为0
     */
    public static void resetRightOne(int in){
        int temp= in;
        temp = (temp -1)&temp;
        System.out.println(Integer.toBinaryString(in) + "中最右边一个1置为0:\t" + Integer.toBinaryString(temp));
    }

    public static void main(String[] args) {
        swap(1,2);
        resetRightOne(10);
    }

}
