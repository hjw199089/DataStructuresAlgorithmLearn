package ThreadLearn.Basic.ch1;

import org.junit.experimental.theories.Theories;

public class MyThreadProrityRunTest {
  public static void main(String[] args) {
    Thread t1 = new Thread(new MyThreadProrityRun(), "A");
    Thread t2 = new Thread(new MyThreadProrityRun(), "B");
    Thread t3 = new Thread(new MyThreadProrityRun(), "C");
    t1.setPriority(Thread.MIN_PRIORITY);
    t2.setPriority(Thread.NORM_PRIORITY);
    t3.setPriority(Thread.MAX_PRIORITY);
    t1.start();
    t2.start();
    t3.start();
  }
}
//  B:0
//  C:0
//  A:0
//  B:1
//  A:1
//  C:1
//  B:2
//  C:2
//  A:2
//  C:3
//  A:3
//  B:3
//  C:4
//  A:4
//  B:4