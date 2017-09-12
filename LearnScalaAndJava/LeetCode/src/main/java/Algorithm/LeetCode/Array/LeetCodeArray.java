package Algorithm.LeetCode.Array;

/**
 * Created by hjw on 17/9/8.
 * LeetCode基础学习
 * 参考多谢:http://blog.csdn.net/hellobinfeng/article/category/1350225
 */
public class LeetCodeArray {


    public static void printArr(int arr[]) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "-->");
        }
        System.out.println("null");
    }

    /*
     * Remove Duplicates from Sorted Array
     * Given a sorted array, remove the duplicates in place such that each element appear
     * only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with
     * constant memory.
     * For example, Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     */
    public static int removeDulElementFun(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr == null ? 0 : arr.length;
        }

        //跳过第一个
        int slow = 1;
        int fast = 1;

        for (; fast < arr.length; fast++) {
            if (arr[fast] != arr[slow]) {
                arr[slow++] = arr[fast];
            }
        }

        return slow;
    }

    /*
    题目：
    Implement strStr()
    实现strStr() 函数
    思路：
    1.用indexOf找到第一次出现的index，如果找到则调用substring，否则返回null ==> 因为用到了indexOf，所以面试时候肯定不可以
    2.用O(n2)的暴力匹配（推荐）
    3.KMP O(n)， 面试时一般不会要求写出
     */
    public static String strStrFun1(String srcStr, String subStr) {
        if (srcStr == null)
            return null;
        if (srcStr.length() < subStr.length())
            return null;
        int index = srcStr.indexOf(subStr);
        return index < 0 ? null : srcStr.substring(index, index + subStr.length());
    }

    //暴力匹配
    public static String strStrFun2(String srcStr, String subStr) {
        if (srcStr == null)
            return null;
        if (srcStr.length() < subStr.length())
            return null;
        int i=0,j=0;
        boolean isFind = false;
        for (i = 0; i < srcStr.length() - subStr.length(); i++) {
            for (j = 0; j < subStr.length(); j++) {
                if (srcStr.charAt(i + j) != subStr.charAt(j))
                    break;
            }
            if (j == subStr.length()){
                isFind = true;
                break;
            }
        }
        if (isFind){
            return srcStr.substring(i,i+j);
        }
        return null;
    }
    //KMP http://blog.csdn.net/joylnwang/article/details/6778316/


    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        System.out.println(removeDulElementFun(arr));
        printArr(arr);

        String substr = strStrFun1("ceshistrceshii", "str");
        System.out.println("子串:\t" + substr);
        substr = strStrFun2("ceshistrceshii", "str");
        System.out.println("子串:\t" + substr);
    }

}
