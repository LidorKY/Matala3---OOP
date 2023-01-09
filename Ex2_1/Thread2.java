package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class Thread2 implements Callable<Integer> {

    private String name;

    public Thread2(String name){
        super();
        this.name = name;

    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        int counter = 0;
        try  {
            BufferedReader reader = new BufferedReader(new FileReader(this.name));
            while (reader.readLine() != null) {
                counter++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }
}
