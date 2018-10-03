package examples.blockingqueues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * The PriorityBlockingQueue is an unbounded concurrent queue. It uses the same
 * ordering rules as the java.util.PriorityQueue class. You cannot insert null
 * into this queue.
 * 
 * All elements inserted into the PriorityBlockingQueue must implement the
 * java.lang.Comparable interface. The elements thus order themselves according
 * to whatever priority you decide in your Comparable implementation.
 * 
 * @author Amit Sadafule
 *
 *         03-Oct-2018
 */
public class PriorityBlockingQueueExample {

	public static void main(String[] args) {
		BlockingQueue<Event> queue = new PriorityBlockingQueue<Event>(10);
		Producer producer = new Producer(queue, new ComparableEventGenerator());
		Consumer consumer = new Consumer(queue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
	}

}
