package ThreadLearn.Basic.ch1;

public class MyThreadTest {
  public static void main(String[] args) {
    MyThread t1 = new MyThread("A");
    MyThread t2 = new MyThread("B");
    //线程的启动用start
    t1.start();
    t2.start();
  }
}
//  A:0
//  B:0
//  A:1
//  B:1
//  B:2
//  B:3
//  B:4
//  B:5
//  A:2
//  B:6
//  A:3
//  A:4
//  A:5
//  A:6
//  A:7
//  A:8
//  B:7
//  A:9
//  B:8
//  B:9
