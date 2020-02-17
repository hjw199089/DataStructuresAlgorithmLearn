package BasicLearn.LeetCode.LineTab;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hjw on 17/7/31.
 */
public class lengthOfLongestSubstring003 {
    //Approach #1 Brute Force [Time Limit Exceeded]
    // Complexity Analysis
    // Time complexity : O(n^3)
    // Space complexity : O(min(n, m))O(min(n,m)). We need O(k)O(k) space for checking a substring has no duplicate characters, where kk is the size of the Set. The size of the Set is upper bounded by the size of the string nn and the size of the charset/alphabet mm.
    //i和j遍历,i固定j遍历到串尾部,设置一个函数判断是否出现了重复,修改
    public int lengthOfLongestSubstringBruteForce(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<Character>();
        for (int i = start; i <= end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }


    //======方法二====
    /*
     Sliding Window
     直到窗的长度为0
     Complexity Analysis
     Time complexity : O(2n) = O(n)O(2n)=O(n). In the worst case each character will be visited twice by ii and jj.
     Space complexity : O(min(m, n))O(min(m,n)). Same as the previous approach.
                        We need O(k)O(k) space for the sliding window, where kk is the size of the Set. The size of the Set is upper bounded by the size of the string nn and the size of the charset/alphabet mm.
     */
    public int lengthOfLongestSubstringSlidingWindow(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<Character>();
        int ans = 0,i=0,j=0;
        while(i<n && j<n){
            //这里尝试不断的扩大窗[i,j]
            if(set.contains(s.charAt(j))){
                set.add(s.charAt(j++));//窗口中没有s(j),加入,并后移下标
                ans = Math.max(ans,j-i);//更新最大长度
            }else{
                set.remove(s.charAt(i++));//移除窗前端的一个元素,同时后移下标,这里可以优化
            }
        }
        return ans;
    }

    //======方法三优化窗移动====
    /*
     Sliding Window
     直到窗的长度为0
     Complexity Analysis
     Time complexity : O(2n) = O(n)O(2n)=O(n). In the worst case each character will be visited twice by ii and jj.
     Space complexity : O(min(m, n))O(min(m,n)). Same as the previous approach.
                        We need O(k)O(k) space for the sliding window, where kk is the size of the Set. The size of the Set is upper bounded by the size of the string nn and the size of the charset/alphabet mm.
     */
    public int lengthOfLongestSubstringSlidingWindowMap(String s) {
        int n = s.length();
        Map<Character,Integer> map = new HashMap();
        int ans = 0;
        for(int i = 0,j=0; j<n;j++){
            //这里尝试不断的扩大窗[i,j]
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)),i);//移动左边界
            }
            ans  = Math.max(ans,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return ans;
    }

    //======方法四优化窗移动和MAP====
    /*
     Assuming ASCII 128
     直到窗的长度为0
     The previous implements all have no assumption on the charset of the string s.
     If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.
     Commonly used tables are:
     int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     int[128] for ASCII
     int[256] for Extended ASCII
     Complexity Analysis
     Time complexity : O(n)O(n). Index jj will iterate nn times.
     Space complexity (HashMap) : O(min(m, n))O(min(m,n)). Same as the previous approach.
     Space complexity (Table): O(m)O(m). mm is the size of the charset.
     */
    public int lengthOfLongestSubstringSlidingWindowASCII(String s) {
        int n = s.length();
        int ans = 0;
        int[] index = new int[128];
        for(int i = 0,j=0; j<n;j++){
            //这里尝试不断的扩大窗[i,j]
            i = Math.max(index[s.charAt(j)],i);//更新窗左边界
            ans  = Math.max(ans,j-i+1);
            index[s.charAt(j)] = j+1; //存储出现过的字母的位置
        }
        return ans;
    }

}
