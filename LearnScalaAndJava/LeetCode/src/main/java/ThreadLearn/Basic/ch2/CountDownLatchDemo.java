package ThreadLearn.Basic.ch2;

import java.util.concurrent.CountDownLatch;


//计数器
class Racer extends Thread {
  private CountDownLatch countDownLatch;

  public Racer(CountDownLatch countDownLatch, String name) {
    setName(name);
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void run() {
    try {
      countDownLatch.await();
      for (int i = 0; i < 3; i++) {
        System.out.println(getName() + ":" + i);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class CountDownLatchDemo {
  public static void main(String[] args) {
    CountDownLatch countDownLatch = new CountDownLatch(3);

    new Racer(countDownLatch, "A").start();
    new Racer(countDownLatch, "B").start();
    new Racer(countDownLatch, "C").start();

    for (int i = 0; i< 3; i++ ) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(i);
      countDownLatch.countDown();
      if (i == 2) {
        System.out.println("Start ...");
      }
    }
  }
}
//  0
//  1
//  2
//  Start ...
//  A:0
//  A:1
//  A:2
//  B:0
//  B:1
//  B:2
//  C:0
//  C:1
//  C:2