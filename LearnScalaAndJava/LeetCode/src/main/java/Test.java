/**
 * Created by hjw on 17/8/28.
 */
public class Test {
    public static  void testFun(int in){
        in++;
    }

    public static void main(String[] args) {
        int A = 9;
        testFun(A);
        System.out.println(A);
    }
}
