import storage.InputStorage;
import workers.Consumer;
import workers.Producer;

import java.io.IOException;

/**
 * Created by ivans on 08/04/2017.
 * Consumer makes the calculations and updates total sum
 * Producer listens for changes in directory and updates our queue
 */
public class Main {

    public static void main(String args[]) throws IOException
    {
        InputStorage inputStorage =  new InputStorage("inputFiles");
        inputStorage.initializeStorage();

        Thread t1 = new Thread(new Consumer(inputStorage),"Consumer-1");
        Thread t2 = new Thread(new Consumer(inputStorage),"Consumer-2");
        Thread t3 = new Thread(new Consumer(inputStorage),"Consumer-3");
        Thread t4 = new Thread(new Producer(inputStorage),"Producer-1");

        t1.start();
        t2.start();
        t3.start();
        //t4.start();
    }
}