package Ex2_1;

import java.util.Arrays;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String [] array = Ex2_1.createTextFiles(10000,1,10000);
        //System.out.println(Arrays.toString(array));
        System.out.println();

        long start1 = System.currentTimeMillis();
        int temp1 = Ex2_1.getNumOfLines(array);
        long end1 = System.currentTimeMillis();
        System.out.println("temp1: " + temp1 + " time1: " + (end1 - start1)*(0.001));
/*
        long start2 = System.currentTimeMillis();
        int temp2 = Ex2_1.getNumOfLinesThreads(array); //make the func static
        long end2 = System.currentTimeMillis();
        System.out.println("temp2: " + temp2 + " time2: " + (end2 - start2)*(0.001));

        long start3 = System.currentTimeMillis();
        int temp3 = Ex2_1.getNumOfLinesThreadPool(array);//make the func static
        long end3 = System.currentTimeMillis();
        System.out.println("temp3: " + temp3 + " time3: " + (end3 - start3)*(0.001));
 */

    }
}