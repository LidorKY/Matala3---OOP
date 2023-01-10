package Ex2_2;

import java.util.concurrent.Callable;
import java.io.*;
import java.util.concurrent.Future;

public class Task<T> implements Callable ,Comparable {

    private Callable func ;
    private int priority;

    /**
     * this function is a constructor that gets 2 parameters.
     * @param func - this parameter is the thread that we want to create that can return argument of future type.
     * @param task - we use this parameter in order to get the priority value of the task.
     */
    private Task(Callable func, TaskType task){
        this.func = func;
        priority = task.getPriorityValue();
    }

    /**
     * this is the same constructor just like as the one before it but here we don't get TaskType therefore we define priority value
     * to be equals to 3.
     * @param func - this parameter is the thread that we want to create that can return argument of future type.
     */
    private Task(Callable func){
        this.func = func;
        priority=3;
    }

    //Factory//
    /**
     * since we have created 2 constructors that are defined to be private, we have built this method that uses
     * the private constructors we have built above.
     * @param func - this parameter is the thread that we want to create that can return argument of future type.
     * @param task - we use this parameter in order to get the priority value of the task.
     * @return - the function return new object Task-type.
     */
    public static Task createTask(Callable func, TaskType task){
        return new Task(func,task);
    }

    //default Factory//
    /**
     * the same idea like the finction above, but for the default constructor.
     * @param func - this parameter is the thread that we want to create that can return argument of future type.
     * @return - the function return new object Task-type.
     */
    public static Task createTask(Callable func){
        return new Task(func);
    }

    /**
     * implemnting this method ron the Callable interface gives us the option to create a thread of Callable-type.
     * @return - this function returns the result of the unction called in Main or Test.
     * @throws Exception - since we used the java method - call, we have to throw an exception.
     */
    @Override
    public Object call() throws Exception {
        return this.func.call();
    }

    /**
     * since we implemented the class Comparable we need to execute the compareTo function in order to define to
     * the PriorityBlockingQueue the priority it should be organized.
     * @param t1 the object to be compared.
     * @return - 1, then the priority if the task that already in the queue is smaller than the priority of the task we want to add.
     * @return - -1, then the priority if the task that already in the queue is bigger than the priority of the task we want to add.
     * @return - 0, then the priority if the task that already in the queue equals to the priority of the task we want to add.
     */
    @Override
    public int compareTo(Object t1){
        if(this.priority< ((Task) t1).priority){
            return 1;
        }
        else if(this.priority> ((Task) t1).priority){
            return -1;
        }
        else{
            return 0;
        }
    }

    /**
     * this function gives us the priority of the task.
     * @return - the priority of the task.
     */
    public int getPriority(){
        return this.priority;
    }
}