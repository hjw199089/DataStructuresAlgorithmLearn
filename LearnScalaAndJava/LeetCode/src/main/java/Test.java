import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hjw on 17/8/28.
 */
public class Test {
    public static  void testFun(int in){
        in++;
    }

    public static void main(String[] args) {
        String a = "ssssssEEEEEEDDD";
        System.out.println(a.matches(".*?MDD.*?"));
    }
    

   public static int minLengthSumArr(int[] inArr, int len, int s) {
       if (inArr == null || len <= 0) {
           return -1;
       }
       int sum = 0;
       int slow = 0;
       int fast = 0;
       int min = Integer.MAX_VALUE;
       while(fast < len) {
           sum += inArr[fast++];
           while (sum >= s) {
               if (sum == s) {
                   min = Math.min(min, fast - slow);
               } 
               sum -= inArr[slow++];
           }
       }
       return 0;
   }

    /**
     * 最小问题：每个位置为终点的最大子串的长度
     * 状态：位置为序号，最大子串的长度为值
     * @param iArr
     * @return
     */
   public static int maxMLS(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, 0);
        for (int k = 1; k < iArr.length; k++) {
            int maxlen = 0;
            for (int j = 0; j < k; j++) {
                if (iArr[j] < iArr[k]) {
                  if (maxlen < list.get(j)) {
                      maxlen = list.get(j);
                  } 
                }
            }
            list.add(k, maxlen);
        }
        return Collections.max(list);
   }
}

