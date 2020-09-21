package hex.test.threads;

public class SumRunnable implements Runnable{
    final int[] massive;
    int res = 0;
    public SumRunnable(final int ... massive) {
        this.massive = massive;
    }

    public int getResult(){
        return res;
    }

    @Override
    public void run() {
        Integer res = 0;
        for (int i : massive){
            res+=i;
        }
        this.res = res;
    }
}
