package ThreadLearn.Basic.ch1;

public class MyRunnableTest {
  public static void main(String[] args) {
    MyRunnable r1 = new MyRunnable("A");
    MyRunnable r2 = new MyRunnable("B");
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    t1.start();
    t2.start();
  }
}
//  B:0
//  B:1
//  B:2
//  B:3
//  A:0
//  B:4
//  A:1
//  A:2
//  B:5
//  A:3
//  B:6
//  A:4
//  B:7
//  B:8
//  B:9
//  A:5
//  A:6
//  A:7
//  A:8
//  A:9