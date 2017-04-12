package storage;

/**
 * Created by ivans on 09/04/2017.
 */
public class SumManager {
    public static volatile long sum = 0;

    public static synchronized void printSumInfo(String t, String path, long temp, long result)
    {
        System.out.println(Thread.currentThread().getName() + " has done parsing: " + path + "\n"
                + "File sum is: " + temp + " | " + "Total sum is: " + result);
    }
}
