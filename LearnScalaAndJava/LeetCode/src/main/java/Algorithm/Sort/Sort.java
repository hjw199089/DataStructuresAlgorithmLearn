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
            if (inArr[i] < inArr[i - 1]) {
                int temp = inArr[i];
                inArr[i] = inArr[i - 1];
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
    private static void ShellInsert(int inArr[], int len, int delt) {
        if (inArr == null || len <= 1)
            return;
        int i, j;
        for (i = delt; i < len; i++) {
            if (inArr[i] < inArr[i - delt]) {
                int temp = inArr[i];
                inArr[i] = inArr[i - delt];
                for (j = i - 2 * delt; j >= 0 && inArr[j] > temp; j = j - delt) {
                    inArr[j + delt] = inArr[j];
                }
                inArr[j + delt] = temp;
            }
        }
    }

    public static void ShellSort(int inArr[], int len, int delt[], int deltLen) {
        for (int i = 0; i < deltLen; i++) {
            ShellInsert(inArr, len, delt[i]);
            System.out.print("\t第" + (i + 1) + "轮希尔排序后的结果:\t");
            print(inArr);
        }

    }

    /*
    选择排序
    平均:O(n^2)
    最坏:O(n^2)
    最好:O(n^2)
    稳定性:不稳定
     */
    public static void selectSort(int inArr[], int len) {
        if (inArr == null || len <= 1)
            return;

        int i, j, minloc;
        for (i = 0; i < len - 1; i++) {
            minloc = i;
            for (j = i + 1; j < len; j++) {
                if (inArr[minloc] > inArr[j])
                    minloc = j;//找到最小者的位置
            }
            if (minloc != i) {
                inArr[minloc] = inArr[minloc] ^ inArr[i];
                inArr[i] = inArr[minloc] ^ inArr[i];
                inArr[minloc] = inArr[minloc] ^ inArr[i];
            }
        }
    }

    /*
    快排
    平均:O(nlogn)
    最坏:O(n^2)
    最好:O(nlogn)
    稳定性:不稳定
     */
    private static int partationFun(int inArr[], int low, int high) {
        int pivotloc = inArr[low];
        while (low < high) {
            while (high > low && inArr[high] > pivotloc) {
                high--;
            }
            inArr[low] = inArr[high];
            while (high > low && inArr[low] < pivotloc) {
                low++;
            }
            inArr[high] = inArr[low];
        }
        inArr[low] = pivotloc;
        return low;
    }

    private static void quickSortFun(int inArr[], int low, int high) {
        int pivotloc;
        if (low < high) {//长度>1
            pivotloc = partationFun(inArr, low, high);//二分
            quickSortFun(inArr, low, pivotloc - 1);
            quickSortFun(inArr, pivotloc + 1, high);
        }
    }

    public static void quickSort(int inArr[], int len) {
        if (inArr == null || len <= 1)
            return;
        quickSortFun(inArr, 0, len - 1);
    }

    /*
    归并排序
    平均:O(nlogn)
    最坏:O(nlogn)
    最好:O(nlogn)
    稳定性:稳定
     */
    //C/C++版本
    /*    void  Merge(int List1[], int List1_size, int List2[], int List2_size)
        {//将SR[s..m]和SR[m+1..t]归并到TR[s...t]
            int i,j,k;
            int temp[10];
            i = j = k = 0;
            while(i < List1_size && j < List2_size)
            {
                if(List1[i] > List2[j]) temp[k++] = List2[j++];
                else  temp[k++] = List1[i++];
            }
            while(i < List1_size)
            {
                temp[k++] =  List1[i++];
            }
            while(j < List2_size)
            {
                temp[k++] =  List2[j++];
            }
            for(i = 0; i < k; i++)
            {
                List1[i] = temp[i];
            }
        }
        void MergeSort(int k[], int Len)
        {
            if(Len > 1)
            {
                int *List1 = k;
                int  List1_size = Len/2;
                int *List2 = k + Len/2;
                int List2_size = Len-List1_size;
                MergeSort(List1,List1_size);
                MergeSort(List2,List2_size);
                Merge(List1, List1_size, List2,List2_size );
            }
        }
      */
    public static void mergeFun(int inArr[], int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (inArr[i] < inArr[j]) {
                temp[k++] = inArr[i++];
            } else {
                temp[k++] = inArr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = inArr[i++];
        }
        while (j <= end) {
            temp[k++] = inArr[j++];
        }

        for (i = 0; i < k; i++) {
            inArr[start + i] = temp[i];
        }

    }

    public static void mergeSort(int inArr[], int start, int end) {
        if (inArr == null)
            return;
        if (start < end) {
            int mid = (start + end) >> 1;
            mergeSort(inArr, start, mid);
            mergeSort(inArr, mid + 1, end);
            mergeFun(inArr, start, mid, end);
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
        int[] deltaArr = {3, 1};
        System.out.println("希尔排序:");
        ShellSort(in, in.length, deltaArr, deltaArr.length);

        in = new int[]{3, 4, 2, 1, 6, 8, 7};
        selectSort(in, in.length);
        System.out.print("选择排序:\t");
        print(in);

        in = new int[]{3, 4, 2, 1, 6, 8, 7};
        quickSort(in, in.length);
        System.out.print("快速排序:\t");
        print(in);

        in = new int[]{3, 4, 2, 1, 6, 8, 7};
        mergeSort(in, 0, in.length - 1);
        System.out.print("归并排序:\t");
        print(in);
    }
}
