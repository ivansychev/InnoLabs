package workers;

import storage.InputStorage;

import java.io.*;

/**
 * Created by ivans on 09/04/2017.
 */
public class NumFileParser {
    private File numFile;

    public NumFileParser() {

    }
    public void passFile(String path)
    {
        try{
            numFile = new File(path);
        }catch (NullPointerException e)
        {
            System.out.println("Shiiieeeeett");
        }
    }
    public long parseSum()
    {
        long sum =0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(numFile));
            String s;
            while (!InputStorage.isError) {
                s = bufferedReader.readLine();
                if (s != null) {
                    for (String l : s.split(" ")) {
                        long x = 0;
                        try{
                            //regexp
                            x = Long.parseLong(l);
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println(Thread.currentThread().getName() + " found wrong input in the file: " + numFile + ". Please make sure file contains numbers only!");
                            System.out.println("The input: " + l + " was ignored and not included in the total sum!");
                            InputStorage.isError = true;
                            break;
                        }

                        if(x>0&&x%2==0)
                            sum += x;
                    }
                } else break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
