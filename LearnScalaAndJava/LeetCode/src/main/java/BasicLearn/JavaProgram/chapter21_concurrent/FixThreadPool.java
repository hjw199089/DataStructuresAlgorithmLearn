package BasicLearn.JavaProgram.chapter21_concurrent;

import BasicLearn.JavaProgram.chapter21_concurrent.LiffOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hjw on 17/3/19.
 */
public class FixThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for (int i=0; i<6; i++){
            exec.execute(new LiffOff(3));
        }
        //防止新任务被提交
        exec.shutdown();
    }
}
//#0(2), #2(2), #1(2), #2(1), #0(1), #0(LiffOff!), #2(LiffOff!),
// #1(1), #3(2), #1(LiffOff!), #4(2), #5(2), #5(1), #5(LiffOff!),
// #3(1), #4(1), #3(LiffOff!), #4(LiffOff!),