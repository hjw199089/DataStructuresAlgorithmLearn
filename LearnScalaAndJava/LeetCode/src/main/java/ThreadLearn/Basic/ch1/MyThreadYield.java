package ThreadLearn.Basic.ch1;

public class MyThreadYield  implements Runnable{
  private String name;

  public MyThreadYield(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < 50; i++) {
      System.out.println(name + ":" + i);
      if (i == 10) {
        System.out.println("礼让");
        Thread.yield();
      }
    }
  }
}
