package ThreadLearn.Basic.ch2;

import java.util.concurrent.Phaser;

class Worker extends Thread {
  private Phaser phaser;

  public Worker(Phaser phaser, String name) {
    this.phaser = phaser;
    this.setName(name);
    phaser.register();
  }

  @Override
  public void run() {
    for (int i = 1; i <= 3; i++) {
      System.out.println("current order is " + i + ":" + getName());
      if (i == 3) {
        phaser.arriveAndDeregister();
      }else  {
        phaser.arriveAndAwaitAdvance();
      }
    }
  }
}

public class PhaserDemo  {
  public static void main(String[] args) {
    Phaser phaser = new Phaser(1);
    System.out.println("starting ...");
    new Worker(phaser, "服务员").start();
    new Worker(phaser, "厨师").start();
    new Worker(phaser, "上菜员").start();

    for (int i= 1; i <= 3; i++) {
      phaser.arriveAndAwaitAdvance();
      System.out.println("Order " + i + " is finished");
    }
    phaser.arriveAndDeregister();
    System.out.println("ALL Done!");
  }
}
//  starting ...
//  current order is 1:服务员
//  current order is 1:厨师
//  current order is 1:上菜员
//  current order is 2:上菜员
//  current order is 2:服务员
//  current order is 2:厨师
//  Order 1 is finished
//  Order 2 is finished
//  current order is 3:上菜员
//  current order is 3:厨师
//  current order is 3:服务员
//  Order 3 is finished
//  ALL Done!