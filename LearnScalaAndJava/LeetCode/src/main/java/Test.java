/**
 * Created by hjw on 17/8/28.
 */
public class Test {
    public static  void testFun(int in){
        in++;
    }

    public static void main(String[] args) {
        int A = 9;
        int start = 5;
        int end = 6;
        int mid = 0;
        int temp = (end - start)>>1;
        int temp2 = start + ((end - start)>>1);
        System.out.println(start + (end - start)>>1);
    }
}
