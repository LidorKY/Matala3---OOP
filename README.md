#Threads and ThreadPool - Ex2_1


At first we created 10,000 files with random amount of lines. In our main we measure the time it took to the different techniques 
to count the lines in all of the files.

The first technique - using a regular function with no threads = 5.092 sec.
The second technique - using multiple threads = 2.511 sec.
The third technique - using threadpool =  3.438 sec

Secondly we created 1,000 files

The first technique - using a regular function with no threads = 1.074 sec.
The second technique - using multiple threads = 0.285 sec.
The third technique - using threadpool = 0.191 sec

(Note: we have done those tests for several times)
In our project after we have compared between the times it took to do the same task, like counting lines in files but in different techniques,
we have got to conclusion that using threads helps the project to run faster rather than using a regular function.
Threads can be executed concurrently, while regular functions are executed sequentially.
Moreover, using Threadpool helps the project to run even faster by starting all the threads at the same time.

When you create a new thread, it is scheduled by the operating system to be executed concurrently with other threads.
This means that the CPU can switch between different threads and execute them in parallel,
improving the overall performance of the program.

On the other hand, regular functions are executed sequentially in the order they are called.
This means that the CPU executes one function at a time, and it can't switch to another function until
the current function has finished executing.

Therefore, we assumes that if you have a program that performs a lot of independent tasks that can be parallelized,
using threads can improve the performance of the program by allowing the tasks to be executed concurrently and by using
Threadpool we will make the project more effecient.
However , notice that when using a lot of file we use a lot of resources so we need to take it into account.
Therfore, when we know that we will use too much resources it might be better not to use threads.

#CustomExecutor - Ex2_2

Firstly, we created the class Task.
Task is class that implements Callable which means that we can makes objects with call
function (those are the threads). Moreover, we made "compareTo" function in order to
define to the priorityBlockingQueue to sort by priority.

Secondly, we have created CustomExecutor class that supposed to be a ThreadPool with
priority. 
CustomExecutor uses PriorityBlockingQueue<Runnable> and ExecutorService.
With the ExecutorService we will submit the threads and with the 
PriorityBlockingQueue<Runnable> we will keep the threads sorted by their priority.
