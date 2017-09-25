package Algorithm.DP;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hjw on 17/9/16.
 */
public class DP_MLS {
/*
输入的第一行是序列的长度N (1 <= N <= 1000)。
第二行给出序列中的N 个整数，
这些整数的取值范围都在0 到10000
输出要求
最长上升子序列的长度。
输入样例
7
1 7 3 5 9 4 8
输出样例
4
如何把这个问题分解成子问题
“求以ak（k=1, 2, 3…N）为终点的最长上升子序列的长度”是个好的子问题
这里把一个上升子序列中最右边的那个数，称为该子序列的“终点”。
只要这N 个子问题都解决了，那么这N 个子问题的解中，最大的那个就是整个问题的解。
由上所述的子问题只和一个变量相关，就是数字的位置。
因此序列中数的位置k 就是“状态”，而状态 k 对应的“值”，就是以ak 做为“终点”的最长上升子序列的长度。
MaxLen (1) = 1
MaxLen (k) = Max { MaxLen (i)：1<i < k 且 ai < ak 且 k≠1 } + 1
*/
    public static int maxMLS(int[] inArr){
        if (inArr == null)
            return 0;
        int len  = inArr.length;
        ArrayList<Integer> maxLenList = new ArrayList();
        maxLenList.add(1);//第0位的长度为1
        for (int k = 1; k < len; k++){
            int tempMax = 0;
           for (int j = 0 ;j < k; j++){
               if (inArr[j] < inArr[k]){
                   if (tempMax < maxLenList.get(j)){
                       tempMax = maxLenList.get(j);
                   }
               }
           }
            maxLenList.add(k,tempMax + 1);
        }

        return Collections.max(maxLenList);
    }

    public static void main(String[] args) {
        int [] inArr = {1 ,7 ,3 ,5 ,9 ,4 ,8};
        int mls = maxMLS(inArr);
        System.out.println("最长上升子序列的长度 = " + mls);
    }
}
