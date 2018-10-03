package examples.blockingqueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The LinkedBlockingQueue keeps the elements internally in a linked structure
 * (linked nodes). This linked structure can optionally have an upper bound if
 * desired. If no upper bound is specified, Integer.MAX_VALUE is used as the
 * upper bound.
 * 
 * @author Amit Sadafule
 *
 *         03-Oct-2018
 */
public class LinkedBlockingQueueExample {

	public static void main(String[] args) {
		BlockingQueue<Event> queue = new LinkedBlockingQueue<Event>(10);
		Producer producer = new Producer(queue, new SimpleIntGenerator());
		Consumer consumer = new Consumer(queue);

		new Thread(producer).start();
		new Thread(consumer).start();
	}
}
