package ThreadLearn.Basic.ch1;

public class MyThreadSleep implements Runnable{
  private String name;

  public MyThreadSleep(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    for (int i = 0; i < 50; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(name + ":" + i);
    }
  }
}
