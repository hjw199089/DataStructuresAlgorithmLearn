package ThreadLearn.Basic.ch2;

import java.util.concurrent.Semaphore;

class Person extends Thread {
  private Semaphore semaphore;

  public Person(Semaphore semaphore, String name) {
    setName(name);
    this.semaphore = semaphore;
  }

  @Override
  public void run() {
    System.out.println(getName() + " 等待信号亮...");
    try {
      semaphore.acquire();
      System.out.println(getName() + " 服务中...");
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(getName() + " 服务完成...");
    //释放信号量
    semaphore.release();
  }
}
public class SemaphoreDemo {
  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(2);
    Person p1 = new Person(semaphore,"A");
    p1.start();
    Person p2 = new Person(semaphore,"B");
    p2.start();
    Person p3 = new Person(semaphore,"C");
    p3.start();
  }

}
//  A 等待信号亮...
//  B 等待信号亮...
//  A 服务中...
//  B 服务中...
//  C 等待信号亮...
//  A 服务完成...
//  B 服务完成...
//  C 服务中...
//  C 服务完成...