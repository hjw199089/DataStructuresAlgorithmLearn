package BasicLearn.JavaProgram.chapter21_concurrent;

import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        ArrayList<Future<String>> result = new ArrayList<Future<String>>();

        for (int i = 0; i < 4; i++) {
            result.add(exec.submit(new TaskWithResult(i)));
        }

        for (Future<String> fs : result){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }

    }
}
//        result of TaskWithResult 0
//        result of TaskWithResult 1
//        result of TaskWithResult 2
//        result of TaskWithResult 3