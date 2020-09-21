package hex.test.threads;

public class RunnableCaller {

    public static SumRunnable getResultFromSumRunnable(int ... massive) throws InterruptedException {
        SumRunnable sumRunnable = new SumRunnable(massive);
        Thread thread = new Thread(sumRunnable);
        thread.start();
        thread.join();
        return sumRunnable;
    }
}
