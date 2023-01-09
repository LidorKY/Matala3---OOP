package Ex2_2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;

public class CustomExecutor {

    private PriorityBlockingQueue<Runnable> ThreadPool;
    private ExecutorService executor;
    private int Max_Priority;

    //constructor//
    /**
     * this is the constructor of the class.
     * it contains a PriorityBlockingQueue named ThreadPool and a ThreadPoolExecutor named executor.
     */
    public CustomExecutor(){
        ThreadPool = new PriorityBlockingQueue<>();
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()/2
                ,Runtime.getRuntime().availableProcessors()-1,300,TimeUnit.MILLISECONDS,ThreadPool);
    }

    //1//
    /**
     * this function add a new given task to the PriorityBlockingQueue if the isn't full yet.
     * In addition, when adding a task to the PriorityBlockingQueue we check the max priority in the queue
     * in order to make the queue actually be sorted by priority.
     * @param task - the task that we want to add to the PriorityBlockingQueue.
     * @return - the functions returns an object of a Future type.
     */
    public Future submit(Task task) {
        if(ThreadPool.size()==Runtime.getRuntime().availableProcessors()-1){//checking if there is more space for new threads.
            return null;
        }
        if(task.getPriority()<Max_Priority){ //Checking if the new thread has higher priority than Max_Priority.
            Max_Priority = task.getPriority();
        }
        return executor.submit(task);
    }

    //2//
    /**
     * This function uses the function above. The unction creates new task by using the method createTask and adds
     * the task to the PriorityBlockingQueue.
     * @param c - the thread that is being created.
     * @param t - defines the type o the task we are creating.
     * @return - an object of Future type that was added to the PriorityBlockingQueue.
     */
    public Future submit(Callable c,TaskType t) {
        Task task =  Task.createTask(c,t);
        Future f = submit(task);
        return f;
    }

    //3//
    /**
     * The same function as just as the function above. The only difference here is that we don't get a TaskType parameter.
     * @param c - the thread that is being created.
     * @return - an object of Future type that was added to the PriorityBlockingQueue.
     */
    public Future submit(Callable c){
        Task task = Task.createTask(c);
        Future f = submit(task);
        return f;
    }

    //10//
    /**
     * this function gives us the max priority in the PriorityBlockingQueue.
     * @return - the largest priority in the PriorityBlockingQueue.
     */
    public int getCurrentMax(){
        return Max_Priority;
    }

    //11.b//

    /**
     * this function runs all over the PriorityBlockingQueue and executes all the tasks.
     */
    public void executeAll(){
        try {
            for (Runnable runnable : ThreadPool) {
                ((Callable) runnable).call();
            }
        }
        catch (Exception e){
            System.out.println("error in trying to execute all the tasks in the queue");
            e.printStackTrace();
        }
    }

    //11.c//
    /**
     * this function ends all the tasks that are currently running.
     */
    public void gracefullyTerminate(){
        executor.shutdown();
    }
}
