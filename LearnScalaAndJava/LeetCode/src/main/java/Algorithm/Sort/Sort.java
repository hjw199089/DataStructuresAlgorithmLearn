package Algorithm.Sort;

/**
 * Created by hjw on 17/9/3.
 */
public class Sort {
    private static void print(int[] in) {
        if (in == null)
            return;
        for (int i = 0; i < in.length; i++) {
            System.out.print(in[i] + "--");
        }
        System.out.println("null");
    }

    /*
    冒泡排序
    平均:O(n^2)
    最坏:O(n^2)
    最好:O(n)
    稳定性:稳定
     */
    public static void bubbleSort(int[] inArr, int len) {
        if (inArr == null || len <= 0)
            return;
        boolean change = true;
        for (int i = 0; i < len - 2 && change; i++) {
            change = false;
            //一共冒len - 1次
            for (int j = len - 1; j > i; j--) {
                //把最小的冒上去
                if (inArr[j] < inArr[j - 1]) {
                    //交换
                    inArr[j] = inArr[j] ^ inArr[j - 1];
                    inArr[j - 1] = inArr[j] ^ inArr[j - 1];
                    inArr[j] = inArr[j] ^ inArr[j - 1];
                    change = true;
                }
            }
        }
    }

    /*
    插入排序
    平均:O(n^2)
    最坏:O(n^2)
    最好:O(n)
    稳定性:稳定
     */
    public static void inserSort(int[] inArr, int len) {
        if (inArr == null || len <= 1)
            return;
        int i, j;
        for (i = 1; i < len; i++) {
            if (inArr[i] < inArr[i -1]){
                int temp = inArr[i];
                inArr[i] = inArr[i-1];
                for (j = i - 2; j >= 0 && inArr[j] > temp; j--) {
                    inArr[j + 1] = inArr[j];
                }
                inArr[j + 1] = temp;
            }
        }
    }
    /*
    希尔排序
    平均:O(n^1.3)
    最坏:
    最好:
    稳定性:不稳定
     */
    private static void ShellInsert(int inArr[], int len, int delt)
    {
        if (inArr == null || len <= 1)
            return;
        int i, j;
        for (i = delt; i < len; i++) {
            if (inArr[i] < inArr[i -delt]){
                int temp = inArr[i];
                inArr[i] = inArr[i-delt];
                for (j = i - 2*delt; j >= 0 && inArr[j] > temp; j = j - delt) {
                    inArr[j + delt] = inArr[j];
                }
                inArr[j + delt] = temp;
            }
        }
    }

    public  static  void ShellSort(int inArr[], int len, int delt[],int deltLen)
    {
        for(int i = 0; i < deltLen; i++){
            ShellInsert(inArr,len,delt[i]);
            System.out.print("\t第" + (i+1) + "轮希尔排序后的结果:\t");
            print(inArr);
        }

    }

    public static void main(String[] args) {
        int[] in = {3, 4, 2, 1, 6, 8, 7};
        bubbleSort(in, in.length);
        System.out.print("冒泡排序:\t");
        print(in);

        in = new int[]{3, 4, 2, 1, 6, 8, 7};
        inserSort(in, in.length);
        System.out.print("插入排序:\t");
        print(in);

        in = new int[]{3, 4, 2, 1, 6, 8, 7};
        int[] deltaArr = {3,1};
        System.out.println("希尔排序:");
        ShellSort(in,in.length,deltaArr,deltaArr.length);
    }
}
