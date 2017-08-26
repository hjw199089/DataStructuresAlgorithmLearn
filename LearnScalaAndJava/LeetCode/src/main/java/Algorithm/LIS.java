package Algorithm;

/**
 * Created by hjw on 17/8/20.
 */
/*
=================最长递增子串==============
http://blog.csdn.net/v_JULY_v/article/details/6110269
https://my.oschina.net/leejun2005/blog/117167
 */

/*
=================最长递增子序列==============
 */
//struct DATA{
//
//        int a;
//        int b;
//        };
//
//        int cmp(const void* num_1,const void* num_2)
//        {
//        return (*(DATA *)num_1).a - (*(DATA *)num_2).a;
//        }
//
//        int MaxLen(DATA* data, int len)
//        {
//        int *L = new int[len];
//        L[0] = 1;
//        for(int i = 1; i < len; i++)
//        {
//        if((data + i)->b <= (data + i - 1)->b)
//        L[i] =  L[i-1];
//        else
//        L[i] =  L[i-1]  + 1;
//        }
//        int MAXLen = L[0];
//        for(int i = 1; i < len; i++)
//        {
//        if(L[i] > MAXLen)
//        MAXLen = L[i];
//        }
//
//        delete []L;
//        return MAXLen;
//        }
//
//
//        int main()
//        {
//        DATA data[4] = {{1,3},{3,1},{4,2},{2,0}};
//
//        qsort(data,4,sizeof(DATA),cmp);
//
//        cout<<MaxLen(data,4);
//        return 0;
//        }
public class LIS {
    private int max(int[] lis) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lis.length; i++){
            if (lis[i] > max)
                max = lis[i];
        }
        return max;
    }
/*
    编程之美-最长递增子序列
    1，-1，2，-3，4，-5，6，-7
    最长递增子序列1，2，4，6
            【方法一】
    第i元素作为结尾时的最长子序列，对第i+1个元素作为结尾时的LIS无影响
    满足无后效性，可以使用动态规划
    使i表示当前遍历的位置：
    当i= 1，LIS = （1），length = 1
    当i= 2，LIS = （1）或（-1），lenght = 1
    当i= 3，LIS = （1，2）或（-1，2）length = 2
    类推
    前i个元素的LIS[i]表示,则
    LIS[i+1] = max{1, LIS[k] + 1}, array[i+1] > array[k], for any k <=i
    即若array[i+1]>array[k],第i+1个元素可以拼在LIS[k]长度的子序列的尾部构成一个更长的子序列。同时array[i+1]自身至少是一个长度为1的子序列。
   */
    public int LIS(int[] array){
        int[] LIS = new int[array.length];
        for (int i =0; i < array.length;i++){
            LIS[i] = 1;
            //对前k个元素的LIS判断
            for (int k = 0; k <i;k++){
                if(array[i] > array[k] && LIS[k] + 1 > LIS[i]){
                    LIS[i] = LIS[k] + 1;
                }
            }
        }
        return max(LIS);
    }
/*
    【方法二】
    对于方法1，O（n^2）,进一步分析对于前i个元素，如果这个子序列的最大元素比array[i+1]小，即可将a[i+1]加到尾部构成一个新的子序列，我们希望找到前i个元素中的一个递增子序列使得这个递增子序列的最大值元素比array[i]小，且长度尽量长。这样讲将a[i+1]加到尾部构成一个更长的子序列
    这样如下：
    长度为1的子序列的的最大元素的最小值时MaxV[1];
    长度为2的子序列的的最大元素的最小值时MaxV[2];
    .....
    长度为LIS[i]的子序列的的最大元素的最小值时MaxV[LIS[i]];
    维护这个数组，在算法中减少比较的次数
*/
    public int LIS_2(int[] array){
        int[] MaxV = new int[array.length + 1];
        MaxV[1] = array[0]; //长度为1的LIS的最大值的最小值
        MaxV[0] = Integer.MIN_VALUE;
        int[] LIS = new int[array.length];

        for (int i =0; i < array.length;i++){
            LIS[i] = 1;
        }

        int nMaxLIS = 1; //MaxV的长度

        for (int i =1; i < array.length;i++) {
            int j;
            for (j = nMaxLIS; j >= 0; j--){
                if(array[i] > MaxV[j]){
                    //倒序,这样们希望找到前i个元素中的一个
                    // 递增子序列使得这个递增子序列的最大值元素比array[i]小，
                    // 且长度尽量长。这样讲将a[i+1]加到尾部构成一个更长的子序列
                    LIS[i] = j +1;
                    break;
                }
            }
            //如果LIS[i]长度大于最长递增序列的长度,更新
            if(LIS[i] > nMaxLIS){
                nMaxLIS = LIS[i];
                MaxV[LIS[i]]= array[i];
            }else if(MaxV[j] < array[i] && array[i] < MaxV[j+1]){
                MaxV[j +  1] = array[i]; //跟新最小值
            }
        }
        return nMaxLIS;
    }
}
