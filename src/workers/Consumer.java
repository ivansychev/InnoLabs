package workers;

import storage.InputStorage;
import storage.SumManager;

/**
 * Created by ivans on 09/04/2017.
 */
public class Consumer implements Runnable {

    private InputStorage inputStorage;
    private NumFileParser numFileParser = new NumFileParser();

    public Consumer(InputStorage inputStorage) {
        this.inputStorage = inputStorage;
    }

    @Override
    public void run() {
        while (!InputStorage.isError)
        {
            String path = null;
            synchronized (inputStorage.getReadLock())
            {
                path = inputStorage.getFilePaths().poll();
            }
            if(path!=null)
            {
                System.out.println(Thread.currentThread().getName() + " is parsing: " + path);
                numFileParser.passFile(path);
                long temp = numFileParser.parseSum();
                if(!InputStorage.isError) {
                    long result = SumManager.sum += temp;
                    SumManager.printSumInfo(Thread.currentThread().getName(), path, temp, result);
                }
            }
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(InputStorage.isError) System.out.println(Thread.currentThread().getName() + " was shut down!");
        }
    }
}
