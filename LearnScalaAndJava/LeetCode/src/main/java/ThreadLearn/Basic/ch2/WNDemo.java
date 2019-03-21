package ThreadLearn.Basic.ch2;

import jdk.nashorn.internal.ir.WhileNode;

class Data{
  int i;
  public void add() {
    synchronized (this) {
      i++;
      if (i % 5 == 0) {
        notifyAll();
      }
    }
  }

  public void sub() {
    synchronized (this) {
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Before: " + i);
      i -= 5;
      System.out.println("After: " + i);
    }
  }

}

class Cosumer extends Thread {
  Data data;
  Cosumer(Data data) {
    this.data = data;
  }

  public void run() {
    while (true) {
      data.sub();
    }
  }
}


class Producer extends Thread {
  Data data;

  Producer(Data data) {
    this.data = data;
  }

  @Override
  public void run() {
    while(true) {
      data.add();
    }
  }
}

public class WNDemo {
  public static void main(String[] args) {
    Data d = new Data();
    new Producer(d).start();
    new Cosumer(d).start();
  }
}
