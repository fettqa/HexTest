package hex.test.threads;

public class SumThread extends Thread {
    int[] summary;
    int res = 0;

    public SumThread(int ... args) {
        this.summary = args;
    }

    public int getResult() {
       return this.res;
    }

    @Override
    public void run(){
        int res = 0;
        for (int i : summary) {
            res += i;
        }
        this.res = res;
    }
}
