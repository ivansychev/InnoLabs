package workers;

import storage.InputStorage;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

/**
 * Created by ivans on 09/04/2017.
 * The thread of this class listens to the main directory and updates
 * our ArrayDeque<String> filePaths with files that were copied
 */
public class Producer implements Runnable{
    private InputStorage inputStorage;
    private WatchService watcher = FileSystems.getDefault().newWatchService();
    private Path path;

    public Producer(InputStorage inputStorage) throws IOException {
        this.inputStorage = inputStorage;
        this.path = Paths.get(inputStorage.getPath());
        this.path.register(watcher, ENTRY_CREATE);
    }

    @Override
    public void run() {
        while (!InputStorage.isError)
        {
            WatchKey key;
            boolean isFileNameCreated = false;
            try {
                key = watcher.take();
            } catch (InterruptedException ex) {
                return;
            }
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

                if (kind == OVERFLOW) {
                    System.out.println("WARNING!!! Too much files are transfered at once!! Some files can be lost in the process!!");
                } else if (kind == ENTRY_CREATE) {

                    while(!isFileNameCreated)
                    {
                        synchronized (inputStorage.getWriteLock())
                        {
                            String name = fileName.toString();
                            System.out.println(Thread.currentThread().getName() + " has created: " + name);
                            inputStorage.getFilePaths().addLast("inputFiles\\"+name);
                            isFileNameCreated = true;
                        }
                    }
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
