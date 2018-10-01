package examples.forkjoin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author Amit Sadafule
 *
 * 01-Oct-2018
 */
class SampleRecursiveTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;
	private int capacity;
	private String executionType;

	public SampleRecursiveTask(int capacity, String executionType) {
		this.capacity = capacity;
		this.executionType = executionType;
	}
	
	@Override
	protected Long compute() {
		if (this.capacity <= 10) {
			System.out.println("[" + this.executionType + "] ["
					+ Thread.currentThread().getName()
					+ "] [current capacity - " + this.capacity
					+ "] doing work, by myself!!");
			return 1l;
		} else {
			System.out.println("[" + this.executionType + "] ["
					+ Thread.currentThread().getName()
					+ "] [current capacity - " + this.capacity
					+ "] spliting work..");

			List<SampleRecursiveTask> subTasks = Arrays.asList(
					new SampleRecursiveTask(this.capacity / 2, this.executionType), 
					new SampleRecursiveTask(this.capacity / 2, this.executionType)
					);

			subTasks.forEach(task -> task.fork());
			return subTasks
				.stream()
				.mapToLong(task -> task.join())
				.sum();
		}
	}

}
