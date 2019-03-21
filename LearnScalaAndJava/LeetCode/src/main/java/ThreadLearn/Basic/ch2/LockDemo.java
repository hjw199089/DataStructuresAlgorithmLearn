package ThreadLearn.Basic.ch2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
class Data2 {
   static  int i = 0;
   static Lock lock = new ReentrantLock();
   static AtomicInteger ai = new AtomicInteger(0);

   static void operate() {
//    lock.lock();
//    i++;
//    System.out.println(i);
//    lock.unlock();
     System.out.println(ai.incrementAndGet());
  }
}


class MT extends Thread {
  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Data2.operate();
  }
}
public class LockDemo {
  public static void main(String[] args) {
    new MT().start();
    new MT().start();
    new MT().start();

  }


}
