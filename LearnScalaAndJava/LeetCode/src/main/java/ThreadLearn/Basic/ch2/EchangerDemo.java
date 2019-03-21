package ThreadLearn.Basic.ch2;

import java.util.concurrent.Exchanger;

class A extends Thread {
  private Exchanger<String> ex;

  public A(Exchanger<String> ex) {
    this.ex = ex;
  }

  @Override
  public void run() {
    String str = null;
    try {
      str = ex.exchange("Hello");
      System.out.println(str);
      str = ex.exchange("A");
      System.out.println(str);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class B extends Thread {
  private Exchanger<String> ex;

  public B(Exchanger<String> ex) {
    this.ex = ex;
  }

  @Override
  public void run() {
    String str = null;
    try {
      str = ex.exchange("Hi");
      System.out.println(str);
      str = ex.exchange("1");
      System.out.println(str);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
public class EchangerDemo {
  public static void main(String[] args) {
    Exchanger<String> ex = new Exchanger<String>();
    new A(ex).start();
    new B(ex).start();

  }
}
//Hello
//Hi
//1
//A