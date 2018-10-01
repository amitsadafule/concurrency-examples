package examples.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * The ForkJoinPool was added to Java in Java 7. The ForkJoinPool is similar to
 * the Java ExecutorService but with one difference. The ForkJoinPool makes it
 * easy for tasks to split their work up into smaller tasks which are then
 * submitted to the ForkJoinPool too. Tasks can keep splitting their work into
 * smaller subtasks for as long as it makes to split up the task.
 * 
 * @author Amit Sadafule
 *
 *         01-Oct-2018
 */

public class ForkJoinPoolExample {

	private static void executeSampleRecursiveAction(int capacity, ForkJoinPool pool) {
		RecursiveAction recursiveAction = new SampleRecursiveAction(capacity, "Execute SampleRecursiveAction");
		pool.execute(recursiveAction);
	}
	
	private static void invokeSampleRecursiveAction(int capacity, ForkJoinPool pool) {
		RecursiveAction recursiveAction = new SampleRecursiveAction(capacity, "Invoke SampleRecursiveAction");
		pool.invoke(recursiveAction);
	}
	
	private static long invokeSampleRecursiveTask(int capacity, ForkJoinPool pool) {
		RecursiveTask<Long> recursiveTask = new SampleRecursiveTask(capacity, "Invoke SampleRecursiveTask");
		return pool.invoke(recursiveTask);
	}
	
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool(4);
		executeSampleRecursiveAction(100, pool);
		System.out.println("End of Recursive Action");
		ForkJoinPool pool1 = new ForkJoinPool(4);
		invokeSampleRecursiveAction(100, pool1);
		Thread.sleep(1000);
		
		System.out.println("===========Recursive Task============");
		System.out.println("output : " + invokeSampleRecursiveTask(100, pool));
	}

}
