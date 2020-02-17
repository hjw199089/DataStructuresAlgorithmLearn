package ThreadLearn.Basic.ch2;

import java.util.concurrent.*;

class MC implements Callable<Integer> {
  private int begin, end;

  public MC(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  @Override
  public Integer call() throws Exception {
    int sum = 0;
    for (int i = begin; i < end; i++) {
      sum += i;
    }
    return sum;
  }
}

public class CallableDemo {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService es = Executors.newFixedThreadPool(2);
    Future<Integer> r1 = es.submit(new MC(1,100));
    Future<Integer> r2 = es.submit(new MC(100,200));
    System.out.println(r1.get());
    System.out.println(r2.get());
    es.shutdown();

  }

}
//
//4950
//14950
