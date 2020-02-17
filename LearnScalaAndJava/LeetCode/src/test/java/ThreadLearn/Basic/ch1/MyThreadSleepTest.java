package ThreadLearn.Basic.ch1;

public class MyThreadSleepTest {
  public static void main(String[] args) {
    MyRunnable r1 = new MyRunnable("A");
    Thread t1 = new Thread(r1);
    t1.start();
  }


}
