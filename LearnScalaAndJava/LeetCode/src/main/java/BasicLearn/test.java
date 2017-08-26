package BasicLearn;

/**
 * Created by hjw on 17/8/8.
 */
public class test {


    public  static void testFunc(int[] a)
    {
        a[0] = a[0]+1;
    }

    public static void main(String[] args) {
        int[] b = {1};
        testFunc(b);
        System.out.println(b[0]);
    }
}
