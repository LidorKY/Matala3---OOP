package Ex2_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Ex2_1 {
    /* First Function */
    /**
     * this function creates several files and adds several lines of text to them.
     * @param n - the number of files we want to create.
     * @param seed - type of random numbers.
     * @param bound - the max number that we want to get.
     * @return - an array that holds the names of the files we have created.
     */
    public static String[] createTextFiles(int n, int seed, int bound){
        String textFiles [] = new String [n]; // this array holds the name of the create files
        Random rand = new Random(seed);
        for(int i = 0; i < n; i++){
            try {
                FileWriter file= new FileWriter("RandomFiles\\file_" + (i + 1) + ".txt");
                int x = rand.nextInt(bound);
                while(x > 0){
                    file.write("what is it thread?\n");
                    x--;
                }
                file.close();
                textFiles[i] = "RandomFiles\\file_" + (i + 1) + ".txt";
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return textFiles;
    }

    /* Second Function */
    /**
     * this function counts the num of lines in each file we have created by using the first func
     * and sums it.
     * @param fileNames - an array that holds the name of the files.
     * @return - the num of lines in all the files.
     */
    public static int getNumOfLines(String[] fileNames){
        int lines = 0;
        for(int i = 0; i < fileNames.length; i++){
            try (BufferedReader reader = new BufferedReader(new FileReader("RandomFiles\\file_" + (i + 1) + ".txt"))) {
                while (reader.readLine() != null) {
                    lines++;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    /* Third Function */
    /**
     * this function sums the num of lines in several text files using threads.
     * @param fileNames - an array that holds the name of the files.
     * @return - the num of lines in all the files.
     */
    public int getNumOfLinesThreads(String[] fileNames) {
        int counter = 0;
        MyThread thread_array [] = new MyThread [fileNames.length];
        try {
            for (int i = 0; i < fileNames.length; i++) {
                thread_array[i] = new MyThread(fileNames[i]);
                thread_array[i].start();
            }
            for(int i = 0; i < thread_array.length; i++){
                if(!thread_array[i].isAlive()) {
                    counter = counter + thread_array[i].get1();
                }
                else{
                    thread_array[i].join();
                    counter = counter + thread_array[i].get1();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return counter;
    }

    /* Fourth Function */
    /**
     * this function sums the num of lines in several text files using Threadpool.
     * @param fileNames - an array that holds the name of the files.
     * @return - the num of lines in all the files.
     */
    public int getNumOfLinesThreadPool(String[] fileNames){
        ExecutorService executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(fileNames.length);
        int ans =0;
        ArrayList <Thread2> Threads = new ArrayList<>();
        for (int i=0;i<fileNames.length;i++){
            Threads.add(new Thread2(fileNames[i]));
        }
        try {
            for (Future<Integer> f : (ArrayList<Future<Integer>>) executor.invokeAll(Threads)) {
                ans += f.get();
            }
        }
        catch(Exception e){
            System.out.println("There is problem");
            e.printStackTrace();
        }
        executor.shutdown();
        return ans;
    }
}
