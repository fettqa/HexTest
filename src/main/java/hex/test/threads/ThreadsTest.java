package hex.test.threads;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class ThreadsTest {
    private static int secondsThread;
    private static int secondsRunnable;

    public static void main(String[] args) {
        RandomPair p1 = new RandomPair();
        RandomPair p2 = new RandomPair();
        RandomPair p3 = new RandomPair();

        calculateWithoutThreads(p3);

        testComputingInSumThread(p1);

        testComputingInSumRunnable(p2);

        // checkSleep();

        checkStreams();
    }

    /**
     * The control method without any threads.
     * @param p an object that contains an array and its sum.
     */
    private static void calculateWithoutThreads(RandomPair p) {
        long before = System.currentTimeMillis();

        int actualResult = 0;
        for (int i : p.array) {
            actualResult += i;
        }
        // try {
        //     sleep(500);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        long after = System.currentTimeMillis();
        System.out.printf("\nTime delta: %d nanoseconds for calculateWithoutThreads method\n",
                (after - before));

        String message = String.format("victims.SumThread calculates incorrect sum "
                        + "for the input: %s, expected result: %d, actual result: %d",
                Arrays.toString(p.array),
                p.sum,
                actualResult);

        if (actualResult != p.sum) {
            throw new RuntimeException(message);
        }
    }

    /**
     * Testing:
     * - creation and call of a thread;
     * - the accuracy of the calculation of the amount.
     * @param pair an object that contains an array and its sum.
     */
    private static void testComputingInSumThread(RandomPair pair) {
        System.gc();
        SumThread t = null;

        long before = System.nanoTime();
        try {
            t = ThreadCaller.getResultFromSumThread(pair.array);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = System.nanoTime();

        secondsThread = (int) (after - before);
        System.out.printf("\nTime delta: %d nanoseconds for testSumThread\n", secondsThread);



        final int actualResult = Objects.requireNonNull(t).getResult();
        String message = String.format("victims.SumThread calculates incorrect sum "
                        + "for the input: %s, expected result: %d, actual result: %d",
                Arrays.toString(pair.array),
                pair.sum,
                actualResult);

        if (actualResult != pair.sum) {
            throw new RuntimeException(message);
        }
        System.gc();
    }

    /**
     * Testing:
     * - Create and call Runnable;
     * - the accuracy of the calculation of the amount.
     * @param pair an object that contains an array and its sum.
     */
    private static void testComputingInSumRunnable(RandomPair pair) {

        SumRunnable r = null;
        long before = System.nanoTime();
        try {
            r = RunnableCaller.getResultFromSumRunnable(pair.array);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = System.nanoTime();
        secondsRunnable = (int) (after - before);
        System.out.printf("\nTime delta: %d nanoseconds for testSumRunnable\n", secondsRunnable);

        final int actualResult = Objects.requireNonNull(r).getResult();
        if (actualResult != pair.sum) {
            throw new RuntimeException(String.format("victims.SumRunnable calculates incorrect sum "
                            + "for the input: %s, expected result: %d, actual result: %d",
                    Arrays.toString(pair.array),
                    pair.sum,
                    actualResult));
        }
        System.gc();
    }

// --Commented out by Inspection START (11.07.2020 18:14):
//    /**
//     * Хотел добавит тест на залипание потоков.
//     * Думаю, что он пока лишний.
//     */
//    private static void checkSleep() {
//
//        if (secondsThread >= 1 && secondsThread <= 500) {
//            throw new RuntimeException(String.format("SumThread does not sleep on 500ms. "
//                    + "It was sleep on %dns!",
//                    secondsThread));
//        }
//
//        if (secondsRunnable >= 1 && secondsRunnable <= 500) {
//            throw new RuntimeException(String.format("SumRunnable does not sleep on 500ns."
//                    + " It was sleep on %dms!",
//                    secondsRunnable));
//        }
//    }
// --Commented out by Inspection STOP (11.07.2020 18:14)

    /**
     * Check for execution speed in nanoseconds.
     * Check for the run method.
     */
    private static void checkStreams() {
        SumThread st = new SumThread(new int[]{1, 2, 3, 4});
        Method method;
        try {
            method = st.getClass().getDeclaredMethod("run");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Where is method run() in SumThread?");
        }

        System.out.println(secondsThread);
        if (secondsThread == 0 || !"run".equals(method.getName())) {
            throw new RuntimeException("SumThread has no threads!");
        }

        SumRunnable sr = new SumRunnable(new int[]{1, 2, 3, 4, 5});
        try {
            method = sr.getClass().getDeclaredMethod("run");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Where is method run() in SumRunnable?");
        }
        if (secondsRunnable == 0 || !"run".equals(method.getName())) {
            throw new RuntimeException("SumRunnable has no threads!");
        }
    }

    /**
     * A class that generates an array of numbers and immediately calculates their sum.
     */
    private static class RandomPair {
        private final int[] array;
        private int sum;

        RandomPair() {

            int arraySize = 14_000;
            array = new int[arraySize];
            for (int i = 0; i < arraySize; i++) {
                Random random = new Random();
                array[i] = random.nextInt(10);
                sum += array[i];
            }
        }
    }
}
