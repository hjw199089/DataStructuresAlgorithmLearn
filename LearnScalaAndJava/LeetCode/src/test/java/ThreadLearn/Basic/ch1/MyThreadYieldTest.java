package ThreadLearn.Basic.ch1;

public class MyThreadYieldTest {
  public static void main(String[] args) {
    MyThreadYield r1 = new MyThreadYield("A");
    MyThreadYield r2 = new MyThreadYield("B");
    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);
    t1.start();
    t2.start();
  }
}
//  A:0
//  B:0
//  B:1
//  B:2
//  B:3
//  B:4
//  B:5
//  B:6
//  B:7
//  B:8
//  B:9
//  B:10
//  礼让
//  A:1
//  A:2
//  A:3
//  A:4
//  A:5
//  A:6
//  A:7
//  A:8
//  B:11
//  A:9
//  A:10
//  礼让
//  B:12
//  B:13
//  B:14
//  B:15
//  B:16
//  B:17
//  B:18
//  B:19
//  B:20
//  B:21
//  B:22
//  B:23
//  B:24
//  B:25
//  B:26
//  B:27
//  B:28
//  B:29
//  B:30
//  B:31
//  B:32
//  B:33
//  B:34
//  B:35
//  B:36
//  B:37
//  B:38
//  B:39
//  A:11
//  A:12
//  A:13
//  B:40
//  A:14
//  A:15
//  A:16
//  A:17
//  A:18
//  A:19
//  B:41
//  A:20
//  A:21
//  A:22
//  A:23
//  A:24
//  A:25
//  A:26
//  A:27
//  A:28
//  A:29
//  A:30
//  A:31
//  A:32
//  A:33
//  A:34
//  A:35
//  A:36
//  A:37
//  A:38
//  A:39
//  A:40
//  A:41
//  A:42
//  A:43
//  A:44
//  A:45
//  B:42
//  B:43
//  A:46
//  A:47
//  A:48
//  A:49
//  B:44
//  B:45
//  B:46
//  B:47
//  B:48
//  B:49
