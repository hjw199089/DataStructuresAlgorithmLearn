package Algorithm.Basic;

import java.util.Arrays;
import java.util.LinkedList;

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
    
    /*
     * 有n个面包，有三种吃法：一次1个，一次2个，一次3个；
     * 打印一共有多少次吃法
     */
    public static void eatBread(int n, LinkedList<Integer> list) {
        if (n <= 0) {
            if(n == 0 && list.size() > 0) {
                printBread(list);
            }
            return;
        }
        
        for (int i = 1; i <= 3; i++) {
            list.add(i);
            n = n - i;
            eatBread(n, list);
            n = n + i;
            list.removeLast();
        }
    }
    
    private static void printBread(LinkedList<Integer> list) {
        System.out.println();
        list.stream().forEach(v -> {
            System.out.print(v + " --> ");
        });
    }
    
    /*
    * 数组逆序对数
    * [7,1,3,4]
    * (7,1) (7,3),(7,4) 
    * 思路：归并(从小到大)的同时计算逆序对数, 归并时从大侧开始合并 
    */
    
    public static int inversionPairs(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }
        
        int[] copy = Arrays.copyOf(data, data.length);
        
        for(int i=0; i< data.length; i++){
            copy[i] = data[i];
        }
        return inversionPairsCore(data, copy, 0, data.length -1);
    }
    
    public static int inversionPairsCore(int[] data, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = data[start];
            return 0; //迭代终止条件
        }
        
        int mid = (end - start)/2;
        int left = inversionPairsCore(copy, data, start, start + mid);
        int right = inversionPairsCore(copy, data, start + mid + 1, end);
        
        //进行归并排序（从小到大），同时计数逆序数
        int leftIndex = start + mid; //初始化为前半段最后一个数字的下标
        int rightIndex = end; //初始化为后半段最后一个数字的下标
        int index = end;//辅助数组复制的数组的最后一个数字的下标
        int count = 0;//计数--逆序对的数目
        while(leftIndex >= start && rightIndex >= start + mid + 1) {
           if (data[leftIndex] > data[rightIndex]) {
               copy[index--] = data[leftIndex--];
               count += rightIndex - (start + mid + 1) + 1;
           } else {
               copy[index--] = data[rightIndex--];
           }
        }
        
        while (leftIndex >= start) { 
            copy[index--] = data[leftIndex--];
        }

        while (rightIndex >= start + mid + 1) {
            copy[index--] = data[rightIndex--];
        }
        
        return count + left + right;
    }
    
    // ----V2
    public static int inversionPairsV2(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }
        
        return inversionPairsCoreV2(data, 0, data.length -1);
    }

    public static int inversionPairsCoreV2(int[] data, int start, int end) {
        if (start == end) {
            return 0; //迭代终止条件
        }

        int mid = (start + end)/2;
        int left = inversionPairsCoreV2(data, start, mid);
        int right = inversionPairsCoreV2(data, mid + 1, end);

        //进行归并排序（从小到大），同时计数逆序数
        int leftIndex = mid; //初始化为前半段最后一个数字的下标
        int rightIndex = end; //初始化为后半段最后一个数字的下标
        int[] copy = new int[end - start + 1];
        int index = copy.length - 1;//辅助数组复制的数组的最后一个数字的下标
        int count = 0;//计数--逆序对的数目
        while(leftIndex >= start && rightIndex >=  mid + 1) {
            if (data[leftIndex] > data[rightIndex]) {
                copy[index--] = data[leftIndex--];
                count += rightIndex - mid;
            } else {
                copy[index--] = data[rightIndex--];
            }
        }

        while (leftIndex >= start) {
            copy[index--] = data[leftIndex--];
        }

        while (rightIndex >= mid + 1) {
            copy[index--] = data[rightIndex--];
        }
        
        for (int i = 0; i < copy.length; i++) {
            data[i + start] = copy[i];
        }

        return count + left + right;
    }


    public static void main(String[] args) {
//        swap(1,2);
//        resetRightOne(10);

        //吃面包测试
//        eatBread(5, new LinkedList<>());
        
        // 逆序对测试
        int[] arr = {7,2,1,3,4};
        System.out.println(inversionPairsV2(arr));
    }
}
