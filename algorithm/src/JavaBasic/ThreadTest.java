package JavaBasic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/26
 * @Description
 */
public class ThreadTest {
    static class MyThread extends Thread {
    //static class MyThread implements Runnable {
        private  int tickets = 10;
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                if (tickets > 0) {
                    System.out.println("卖票" + Thread.currentThread().getName() + ": tickets left : " + tickets--);
                }
            }
        }
    }

    static class MyThread2 implements Callable<String> {
        @Override
        public String call() {
            for (int i = 0; i < 20; i++) {
                System.out.println("卖票: tickets left " + i);
            }
            return "【票卖完了】";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread mt = new MyThread();
        Thread t1 = new Thread(mt, "线程1");
        Thread t2 = new Thread(mt, "线程2");
        //Thread t3 = new Thread(mt, "线程3");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t2.start();
        t1.start();
        FutureTask<String> futureTask = new FutureTask<String>(new MyThread2());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
