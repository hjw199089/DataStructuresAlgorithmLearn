package Algorithm.DP;

/**
 * Created by hjw on 17/9/13.
 */
public class DPLearn_1 {

    /*=========================================================
    路径上的每一步只能从一个数走到下一层上和它最近的左边的数或者右边的数。
    输入的第一行是一个整数N (1 < N <= 100)
    数字三角最大路径和
            1
            1 2
            3 4 5
            1,3,2,1
    =========================================================*/

    /*=========================================================
    递归实现V1

    类似于树的深度法求平衡,每一步都要计算下面的每一层,当层数到100,
    计算时长已不可接受Time Limit Exceed
    问题出在重复计算
     =========================================================*/
    private static int maxSumOfTriangleRecursionV1(int[][] inArr, int curRow, int curColumn) {
        if (curRow == inArr.length - 1) //总的行数
            return inArr[curRow][curColumn];

        int leftSum = maxSumOfTriangleRecursionV1(inArr, curRow + 1, curColumn);
        int rightSum = maxSumOfTriangleRecursionV1(inArr, curRow + 1, curColumn + 1);
        return leftSum > rightSum ? leftSum + inArr[curRow][curColumn] : rightSum + inArr[curRow][curColumn];
    }

    public static int maxSumOfTriangleRecV1(int[][] inArr) {
        if (inArr == null)
            return Integer.MIN_VALUE;
        return maxSumOfTriangleRecursionV1(inArr, 0, 0);
    }

     /*=========================================================
    递归实现V2

    类似于树的深度法求平衡,每一步都要计算下面的每一层,当层数到100,
    计算时长已不可接受Time Limit Exceed,问题出在重复计算
    可以将每层的结果存储下来,若已有直接利用
     =========================================================*/

    private static int maxSumOfTriangleRecursionV2(int[][] inArr, int curRow, int curColumn, int[][] maxSumArr) {
        if (curRow == inArr.length - 1) {//总的行数
            maxSumArr[curRow][curColumn] = inArr[curRow][curColumn];
            return inArr[curRow][curColumn];
        }

        int leftSum = maxSumArr[curRow + 1][curColumn];
        int rightSum = maxSumArr[curRow + 1][curColumn + 1];
        if (leftSum == -1)
            leftSum = maxSumOfTriangleRecursionV2(inArr, curRow + 1, curColumn, maxSumArr);
        ;

        if (rightSum == -1)
            rightSum = maxSumOfTriangleRecursionV2(inArr, curRow + 1, curColumn + 1, maxSumArr);

        maxSumArr[curRow][curColumn] = leftSum > rightSum ? leftSum + inArr[curRow][curColumn] : rightSum + inArr[curRow][curColumn];
        return maxSumArr[curRow][curColumn];
    }

    public static int maxSumOfTriangleRecV2(int[][] inArr, int[][] maxSumArr) {
        if (inArr == null)
            return Integer.MIN_VALUE;
        return maxSumOfTriangleRecursionV2(inArr, 0, 0, maxSumArr);
    }

    /*=========================================================
    动态规划法

    这种将一个问题分解为子问题[递归]求解，并且将中间结果保存以避免重复计算的办法，就叫做“动态规划”。
    动态规划通常用来求最优解，能用动态规划解决的求最优解问题，必须满足，最优解的每个局部解也都是最优的。
    上例，最佳路径上面的每个数字到底部的那一段路径，都是从该数字出发到达到底部的最佳路径。

    递归的思想在编程时[未必]要实现为递归函数,递推公式循环代替迭代实现
    找到[递推公式]:
    maxSumArr[r][j] =
         若r = N  inArr[r][j]
         否则     Max(maxSumArr[r+1][j],maxSumArr[r+1][j+1]) + inArr[r][j]
    从inArr[N-1]行,向上递推就可以得到inArr[1][1]

    伪代码
    int [][]maxSumArr
    入参检测
    for N-->0 行
        for 0--->Col 列
            if r == N
                maxSumArr = inArr
             maxSumArr = Max(maxSumArr[r+1][j],maxSumArr[r+1][j+1]) + inArr[r][j]
     =========================================================*/
    public static void  maxSumOfTriangleRecV3(int[][] inArr,int[][] maxSumArr) {
        if (inArr == null) {
            return;
        }
        int row = inArr.length;

        for (int r = row - 1; r >= 0; r--){
            for (int c = 0; c <= r; c++){//此处注意每行的列数目
                if (r == row - 1){//最底层
                    maxSumArr[r][c] = inArr[r][c];
                }else {
                    maxSumArr[r][c] = Math.max(maxSumArr[r+1][c],maxSumArr[r+1][c+1]) + inArr[r][c];
                }
            }
        }
    }

     /*=========================================================
    动态规划法 并 输出具体的路径
    =========================================================*/
    public static void  maxSumOfTriangleRecV4(int[][] inArr,int[][] maxSumArr,String[][] path) {
        if (inArr == null) {
            return;
        }
        int row = inArr.length;

        for (int r = row - 1; r >= 0; r--){
            for (int c = 0; c <= r; c++){//此处注意每行的列数目
                if (r == row - 1){//最底层
                    maxSumArr[r][c] = inArr[r][c];
                    path[r][c] = String.valueOf(inArr[r][c]);
                }else {
                    if (maxSumArr[r+1][c] > maxSumArr[r+1][c+1]){
                        maxSumArr[r][c] = maxSumArr[r+1][c] + inArr[r][c];
                        path[r][c] = String.valueOf(inArr[r][c]) + "+" +  path[r+1][c];
                    }else{
                        maxSumArr[r][c] = maxSumArr[r+1][c+1] + inArr[r][c];
                        path[r][c] = String.valueOf(inArr[r][c]) + "+" +  path[r+1][c+1];
                    }

                }
            }
        }
    }
    /*=========================================================
    动态规划小结

    许多求最优解问题可用动态规划来解决。
    把原问题分解为若干个子问题。注意单纯的递归往往会导致子问题被重复计算，
    用动态规划法，子问题的解一旦求出就要被保存，每个子问题只需求解一次。
    子问题经常和原问题形式相似，有时甚至完全一样，只不过规模从原来的n 变成了n-1，或从原来的n×m 变成了n×(m-1) ……
    找到子问题，就意味着找到了将整个问题逐渐分解的办法。
    分解下去，直到最底层规模最小的的子问题可以一目了然地看出解。
    每一层子问题的解决，会导致上一层子问题的解决，逐层向上，
    如果从最底层的子问题开始，自底向上地推导出一个个子问题的解(递推公式)，那么编程的时候就不需要写递归函数。

    用动态规划解题时，将和子问题相关的各个变量的一组取值，称之为一个“状态”。
    一个“状态”对应于一个或多个子问题，所谓某个“状态”下的“值”，就是这个“状态”所对应的子问题的解。

    比如数字三角形，子问题就是“从位于(r，j)数字开始，到底边路径的最大和”。
    这个子问题和两个变量r 和j 相关，那么一个“状态”，就是r, j 的一组取值，即每个数字的位置就是一个“状态”。
    该“状态”所对应的“值”，就是从该位置的数字开始，到底边的最佳路径上的数字之和。
    定义出什么是“状态”，以及在该 “状态”下的“值”后，就要找出不同的状态之间如何迁移
    即如何从一个或多个“值”已知的 “状态”，求出另一个“状态”的“值”。
    状态的迁移可以用递推公式表示，此递推公式也可被称作“状态转移方程”。
    ========================================================= */

    public static void main(String[] args) {
        int[][] triangleArr = {{1}, {1, 2}, {3, 4, 5}, {1, 3, 2, 1}};

        int maxSum = maxSumOfTriangleRecV1(triangleArr);
        System.out.println("纯递归法\t\tmaxSum =\t" + maxSum);

        int[][] maxSumArr = {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
        int maxSumV2 = maxSumOfTriangleRecV2(triangleArr, maxSumArr);
        System.out.println("改进递归法\tmaxSum =\t" + maxSumV2);

        int[][] maxSumArr3 = {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
        maxSumOfTriangleRecV3(triangleArr,maxSumArr3);
        System.out.println("动态规划法\tmaxSum =\t" + maxSumArr3[0][0]);

        int[][] maxSumArr4 = {{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
        String[][] path = {{"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}};
        maxSumOfTriangleRecV4(triangleArr,maxSumArr4,path);
        System.out.println("动态规划法\tmaxSum =\t" + maxSumArr3[0][0]  + "\t" + "路径 =\t" + path[0][0]);
        // 纯递归法	maxSum =	10
        // 改进递归法	maxSum =	10
        // 动态规划法	maxSum =	10
        // 动态规划法	maxSum =	10	路径 =	1+2+5+2
    }
}
