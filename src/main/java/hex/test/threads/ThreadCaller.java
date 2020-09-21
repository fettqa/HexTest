package hex.test.threads;

public class ThreadCaller {
    public static SumThread getResultFromSumThread(int ... massive) throws InterruptedException {
        SumThread sumThread = new SumThread(massive);
        sumThread.start();
        sumThread.join();
        return sumThread;
    }
}
