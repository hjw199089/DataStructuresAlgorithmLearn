package ThreadLearn.ch2;

import java.util.concurrent.locks.LockSupport;

//public enum State {
//  /**
//   * Thread state for a thread which has not yet started.
//   */
//  NEW,
//
//  /**
//   * Thread state for a runnable thread.  A thread in the runnable
//   * state is executing in the Java virtual machine but it may
//   * be waiting for other resources from the operating system
//   * such as processor.
//   */
//  RUNNABLE,
//
//  /**
//   * Thread state for a thread blocked waiting for a monitor lock.
//   * A thread in the blocked state is waiting for a monitor lock
//   * to enter a synchronized block/method or
//   * reenter a synchronized block/method after calling
//   * {@link Object#wait() Object.wait}.
//   */
//  BLOCKED,
//
//  /**
//   * Thread state for a waiting thread.
//   * A thread is in the waiting state due to calling one of the
//   * following methods:
//   * <ul>
//   *   <li>{@link Object#wait() Object.wait} with no timeout</li>
//   *   <li>{@link #join() Thread.join} with no timeout</li>
//   *   <li>{@link LockSupport#park() LockSupport.park}</li>
//   * </ul>
//   *
//   * <p>A thread in the waiting state is waiting for another thread to
//   * perform a particular action.
//   *
//   * For example, a thread that has called <tt>Object.wait()</tt>
//   * on an object is waiting for another thread to call
//   * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
//   * that object. A thread that has called <tt>Thread.join()</tt>
//   * is waiting for a specified thread to terminate.
//   */
//  WAITING,
//
//  /**
//   * Thread state for a waiting thread with a specified waiting time.
//   * A thread is in the timed waiting state due to calling one of
//   * the following methods with a specified positive waiting time:
//   * <ul>
//   *   <li>{@link #sleep Thread.sleep}</li>
//   *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
//   *   <li>{@link #join(long) Thread.join} with timeout</li>
//   *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
//   *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
//   * </ul>
//   */
//  TIMED_WAITING,
//
//  /**
//   * Thread state for a terminated thread.
//   * The thread has completed execution.
//   */
//  TERMINATED;
//}

/*
建立一个thread 后调用 start才会起一个线程执行run方法
注意thread.run 只是方法调用

java 单继承为此 设计一个Runnable接口，该接口只有一个run方法，用于多继承实现
*/

public class CreatThread3  implements Runnable {

  @Override
  public void run() {
    System.out.println("thread running .... ");
  }

  public static void main(String[] args) {

    //方法一 建议
    Thread thread1 = new Thread(new CreatThread3());
    thread1.start();



    Thread thread2 = new Thread(){
      @Override
      public void run() {
        System.out.println(this.getId() + "\trunning...");
      }
    };

    thread2.start();

    System.out.println("this is in main function ....");
  }


}
