package Ex2_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor{
    private int Max_Priority;
    public int [] priority_arr;

    //constructor//

    /**
     * this is the constructor of the class. we used the super() method since we have extended ThreadPoolExecutor.
     * In addition we defined an array. Each cell in the array will hold the number of the tasks of its priority.
     */
    public CustomExecutor(){
        super(Runtime.getRuntime().availableProcessors()/2
                ,Runtime.getRuntime().availableProcessors()-1,300,TimeUnit.MILLISECONDS
                ,new PriorityBlockingQueue<>());
        priority_arr = new int[3];
        Max_Priority=0;
    }

    /**
     * this function compares between two objects.
     * @param o - a given object that we want to compare to another one.
     * @return - true if the max priority equals to the max priority of the object that we are comparing and
     * the arrays equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomExecutor that = (CustomExecutor) o;
        return Max_Priority == that.Max_Priority && Arrays.equals(priority_arr, that.priority_arr);
    }

    /**
     * this function gives to an object an unique "id".
     * @return - the unique "id".
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(Max_Priority);
        result = 31 * result + Arrays.hashCode(priority_arr);
        return result;
    }

    //1//
    /**
     * this function adds task to the queue.
     * @param task - the task we want to add to the queue.
     * @return - an object of Future type and adds the task to the queue.
     * @throws Exception - since we use the submit() method and trows an exception if it didn't added.
     */
    public Future submit(Task task) throws Exception {
        if(this.getQueue().size()==Runtime.getRuntime().availableProcessors()-1) {//checking if there is more space for new threads.
            return null;
        }
        priority_arr[task.getPriority()-1]++;
        return super.submit(task);
    }

    //2//

    /**
     * this function adds task to the queue.
     * @param c - the thread we will create.
     * @param t - the type of the task that we want to give to the thread that will be the task.
     * @return - an object of Future type and adds the task to the queue.
     */
    public Future submit(Callable c,TaskType t) {
        Task task =  Task.createTask(c,t);
        Future f = null;
        try {
            f = submit(task);
        }
        catch(Exception e){
            System.out.println("There is problem with submit");
            e.printStackTrace();
        }
        return f;
    }

    //3//

    /**
     * this function adds task to the queue.
     * @param c - the task to submit
     * @return - an object of Future type and adds the task to the queue.
     */
    public Future submit(Callable c){
        Task task = Task.createTask(c);
        Future f = null;
        try {
            f = submit(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return f;
    }

    //10//

    /**
     * this function checks for the max priority of the tasks that in the threadpool.
     * @return - the max priority.
     */
    public int getCurrentMax(){
        if(priority_arr[0]>0){
            this.Max_Priority = 1;
            return 1;
        }
        else if(priority_arr[1]>0){
            this.Max_Priority = 2;
            return 2;
        }
        else if(priority_arr[2]>0){
            this.Max_Priority = 3;
            return 3;
        }
        return 0;
    }

    //11.b//

    /**
     * this function will execute all the tasks in the threadpool.
     */
    public void executeAll(){
        try {
            for (Runnable r : this.getQueue()) {
                ((Task) r).call();
            }
        }
        catch(Exception e){
            System.out.println("There is an error with executeAll");
            e.printStackTrace();
        }
    }

    //11.c//

    /**
     * this function ends all the tasks that are currently running.
     */
    public void gracefullyTerminate(){
        this.shutdown();// After a thread had finished, the executor removing him from the priorityBlockingQueue
    }


    /**
     * @param t the thread that will run task {@code r}
     * @param r the task that will be executed
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r){
        if(getCurrentMax()!=0){
            priority_arr[getCurrentMax()-1]--;

        }
    }
}
