package examples.blockingqueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * @author Amit Sadafule
 *
 * 03-Oct-2018
 */
public class DelayQueueExample {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		BlockingQueue queue = new DelayQueue();
		Producer producer = new Producer(queue, new DelayedEventGenerator());
		Consumer consumer = new Consumer(queue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}
