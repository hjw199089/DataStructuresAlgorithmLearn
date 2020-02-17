package BasicLearn.JavaProgram.chapter21_concurrent;

/**
 * Created by hjw on 17/3/19.
 */
public class LiffOff implements  Runnable {
    protected int countDown = 10; //default
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiffOff(){}
    public LiffOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id + "("+
                (countDown > 0? countDown : "LiffOff!") + "), ";
    }
    /*
    实现run 方法;
    为一个由退出条件的循环
    有任务切换
     */
    public void run() {
        while(countDown -- > 0){
            System.out.print(status());
        }
    }

    public static void main(String[] args) {

//        LiffOff lauch = new LiffOff();
//        /*
//        这里是一个任务循环分配给自己
//         */
//        lauch.run();


        //将一个实现了Runnable 的对象传给Thread并调用run方法
        //run方法会调用Runnable的run方法
        for(int i = 0; i<5; i++){
            new Thread(new LiffOff(4)).start();
        }
        //时间切片不均匀
        System.out.println("Waiting for liffOff");

//        #0(3), #1(3), #0(2), #0(1), #3(3), Waiting for liffOff
//        #2(3), #2(2), #2(1), #2(LiffOff!),
//        #4(3), #3(2), #0(LiffOff!), #1(2), #3(1), #4(2), #4(1), #4(LiffOff!), #3(LiffOff!), #1(1), #1(LiffOff!),

    }
}
