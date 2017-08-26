package BasicLearn.JavaProgram.chapter21_concurrent;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hjw on 17/3/19.
 */
public class CachedTreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<3; i++){
            exec.execute(new LiffOff());
        }
        //防止新任务被提交
        exec.shutdown();
    }
}
//#0(9), #0(8), #0(7), #0(6), #0(5), #0(4), #0(3),
// #0(2), #2(9), #0(1), #1(9), #1(8), #1(7), #1(6), #1(5),
// #0(LiffOff!), #2(8), #2(7), #2(6), #1(4), #2(5), #1(3),
// #2(4), #1(2), #2(3), #1(1), #2(2), #1(LiffOff!), #2(1), #2(LiffOff!),
