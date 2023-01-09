package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyThread extends Thread{

    private String name;
    private int NumOfLines;
    public MyThread(String name){
        super();
        this.name = name;
        this.NumOfLines = 0;
    }
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
    public int get1(){
        return this.NumOfLines;
    }
}
