package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyThread extends Thread{

    private String name;
    private int NumOfLines;

    /**
     * this is the constructor of the class. since we are extending Thread then we used the method super().
     * @param name - to get the name that we will run the thread on.
     */
    public MyThread(String name){
        super();
        this.name = name;
        this.NumOfLines = 0;
    }

    /**
     * since we are extending Thread we need to implement this method to run threads.
     */
    public void run(){
            try  {
                BufferedReader reader = new BufferedReader(new FileReader(name));
                while (reader.readLine() != null) {
                    this.NumOfLines++;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * this function gives us the number of lines that we have sumed in a file
     * @return - the num of lines in a file
     */
    public int get1(){
        return this.NumOfLines;
    }
}
