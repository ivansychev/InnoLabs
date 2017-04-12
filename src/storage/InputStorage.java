package storage;

import java.io.File;
import java.util.ArrayDeque;

/**
 * Created by ivans on 09/04/2017.
 */
public class InputStorage {

    public static volatile boolean isError = false;

    private Object readLock = new Object();
    private Object writeLock = new Object();
    private String path;

    public Object getWriteLock() {
        return writeLock;
    }

    public Object getReadLock() {
        return readLock;
    }

    /**
     * For this programm I chose ArrayDeque, cause I thought that (FILO) would be the best
     * way to simulate the process. Plus, adding and reading files in queue takes O(1) time. In addition,
     * poll() and peek() methods make it easier to work with queue items.
    */
    private final ArrayDeque<String> filePaths = new ArrayDeque<>();

    private File storage;
    private boolean isInitialized = false;

    public ArrayDeque<String> getFilePaths() {
        return filePaths;
    }

    public String getPath() {
        return path;
    }

    public InputStorage(String path) {
        this.path = path;

        File temp = new File(path);
        if(!temp.isDirectory()) System.out.println("is not directory");
        else this.storage = new File(path);
    }

    public File getStorage() {
        return storage;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void initializeStorage()
    {
        for(File file : storage.listFiles())
        {
            filePaths.addLast(file.getPath());
        }
        isInitialized = true;
    }
}
