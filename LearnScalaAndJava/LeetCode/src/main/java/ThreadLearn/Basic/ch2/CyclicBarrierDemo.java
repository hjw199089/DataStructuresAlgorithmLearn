package ThreadLearn.Basic.ch2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Player extends Thread {
   private CyclicBarrier cyclicBarrier;

  public Player(CyclicBarrier cyclicBarrier, String name) {
    setName(name);
    this.cyclicBarrier = cyclicBarrier;
  }

  @Override
  public void run() {
    System.out.println(getName() + " is waiting other players...");
    try {
      cyclicBarrier.await();
      System.out.println(getName() + " 开始...");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}
public class CyclicBarrierDemo {
  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
      @Override
      public void run() {
        System.out.println("游戏开始...");
      }
    });
    new Player(cyclicBarrier,"A").start();
    new Player(cyclicBarrier,"B").start();
    new Player(cyclicBarrier,"C").start();
  }


}

//  A is waiting other players...
//  C is waiting other players...
//  B is waiting other players...
//  游戏开始...
//  C 开始...
//  B 开始...
//  A 开始...