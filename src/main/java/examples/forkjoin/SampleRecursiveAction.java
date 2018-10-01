package examples.forkjoin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Recursive Action does not return any output upon completion
 * 
 * @author Amit Sadafule
 *
 *         01-Oct-2018
 */
class SampleRecursiveAction extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	private int capacity;
	private String executionType;

	public SampleRecursiveAction(int capacity, String executionType) {
		this.capacity = capacity;
		this.executionType = executionType;
	}

	@Override
	protected void compute() {

		if (this.capacity <= 10) {
			System.out.println("[" + this.executionType + "] ["
					+ Thread.currentThread().getName()
					+ "] [current capacity - " + this.capacity
					+ "] doing work, by myself!!");
		} else {
			System.out.println("[" + this.executionType + "] ["
					+ Thread.currentThread().getName()
					+ "] [current capacity - " + this.capacity
					+ "] spliting work..");

			List<SampleRecursiveAction> subTasks = Arrays.asList(
					new SampleRecursiveAction(this.capacity / 2,
							this.executionType), new SampleRecursiveAction(
							this.capacity / 2, this.executionType));

			subTasks.forEach(task -> task.fork());
			subTasks.forEach(task -> task.join());
		}
	}

}
