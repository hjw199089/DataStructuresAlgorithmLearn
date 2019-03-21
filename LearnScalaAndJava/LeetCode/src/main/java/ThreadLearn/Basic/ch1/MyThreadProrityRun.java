package ThreadLearn.Basic.ch1;

public  class MyThreadProrityRun implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      try {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + ":" + i);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
