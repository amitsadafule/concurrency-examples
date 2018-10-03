package examples.blockingqueues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue is a bounded, blocking queue that stores the elements
 * internally in an array. That it is bounded means that it cannot store
 * unlimited amounts of elements. There is an upper bound on the number of
 * elements it can store at the same time. You set the upper bound at
 * instantiation time, and after that it cannot be changed.
 * 
 * @author Amit Sadafule
 *
 *         03-Oct-2018
 */
public class ArrayBlockingQueueExample {

	public static void main(String[] args) {
		BlockingQueue<Event> queue = new ArrayBlockingQueue<Event>(10);
		Producer producer = new Producer(queue, new SimpleIntGenerator());
		Consumer consumer = new Consumer(queue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
	}

}
