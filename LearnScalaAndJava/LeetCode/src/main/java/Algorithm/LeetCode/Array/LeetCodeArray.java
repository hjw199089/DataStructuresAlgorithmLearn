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
        int i = 0, j = 0;
        boolean isFind = false;
        for (i = 0; i < srcStr.length() - subStr.length(); i++) {
            for (j = 0; j < subStr.length(); j++) {
                if (srcStr.charAt(i + j) != subStr.charAt(j))
                    break;
            }
            if (j == subStr.length()) {
                isFind = true;
                break;
            }
        }
        if (isFind) {
            return srcStr.substring(i, i + j);
        }
        return null;
    }
    //KMP http://blog.csdn.net/joylnwang/article/details/6778316/


    /*
     找到第K大的数
     利用快排的方法
     */
    static int findKthNum(int[] inArr, int len, int k) {
        if (inArr == null || len <= 0 || k <= 0 || k > len)
            return Integer.MIN_VALUE;
        return findKthNumFun(inArr, len, 0, len - 1, k);
    }

    private static int findKthNumFun(int[] inArr, int len, int start, int end, int k) {
        if (inArr == null || len <= 0)
            return Integer.MIN_VALUE;
        if (start == end)
            return inArr[start];

        int partition = findPartition(inArr, len, start, end);
        if (len - partition == k) {
            return inArr[partition];
        } else if (len - partition > k) {
            return findKthNumFun(inArr, len, partition + 1, end, k);
        } else
            return findKthNumFun(inArr, len, start, partition, k);
    }

    private static int findPartition(int[] inArr, int len, int start, int end) {
        int mid = start + ((end - start) >> 1);//这里注意!!!
        int temp = inArr[start];
        inArr[start] = inArr[mid];
        inArr[mid] = temp;

        int low = start, high = end;
        int partition = inArr[start];
        while (low < high) {
            while (inArr[high] >= partition && high > low) {
                high--;
            }
            inArr[low] = inArr[high];
            while (inArr[low] <= partition && high > low) {
                low++;
            }
            inArr[high] = inArr[low];
        }
        inArr[low] = partition;

        return low;
    }

    /*
    给定一个由 n 个整数组成的数组和一个正整数 s ，请找出该数组中满足其和 ≥ s 的最小长度子数组。如果无解，则返回 -1。
    {2,2,1,2,4,3}
    5==>3
    7==>2
     */
    public static int minSubArrayLen(int s, int[] inArr) {
        if (inArr == null) {
            return -1;
        }
        int slow = 0;
        int fast = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        while (fast < inArr.length) {
            sum += inArr[fast++];
            while (sum >= s) { //把前边多的减掉
                if (sum == s)
                    min = Math.min(min, fast - slow);
                sum -= inArr[slow];
                slow++;
            }
        }
        return min == Integer.MAX_VALUE? -1:min ;
    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        System.out.println(removeDulElementFun(arr));
        printArr(arr);

        String substr = strStrFun1("ceshistrceshii", "str");
        System.out.println("子串:\t" + substr);
        substr = strStrFun2("ceshistrceshii", "str");
        System.out.println("子串:\t" + substr);

        int[] inArr = {1, 1, 2, 4, 7, 3, 6};
        System.out.println(findKthNum(inArr, inArr.length, 6));

        int[] inArr2 = {2,2,1,2,4,3};
        System.out.println(minSubArrayLen(7,inArr2));
    }

}
