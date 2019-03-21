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
        System.out.println(longestPalindromicSubstr("a"));
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
   
    public static String longestPalindromicSubstr(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }

        String out = "";
        for (int i = 0; i < input.length(); i++) {
          String strSigle = longestSiglePalindromicSubstr(input, i - 1 , i + 1);
          String strDouble = longestDoublePalindromicSubstr(input, i, i + 1);
          String tmp = strSigle.length() >= strDouble.length() ? strSigle : strDouble;
          out = tmp.length() >= out.length() ? tmp : out;
        }
        return out;
    }

    private static String longestDoublePalindromicSubstr(String input, int low, int high) {
        return longestSiglePalindromicSubstr(input, low, high);
    }

    private static String longestSiglePalindromicSubstr(String input, int low, int high) {
        String out = "";
        while(low >= 0 && high <= input.length() - 1) {
            if (input.charAt(low) == input.charAt(high)) {
                low--;
                high++;
            } else {
                break;
            }
        }
        out = input.substring(low + 1, high);
        return out;
    }
    
    
}


