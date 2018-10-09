package examples.blockingqueues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * The SynchronousQueue is a queue that can only contain a single element
 * internally. A thread inseting an element into the queue is blocked until
 * another thread takes that element from the queue. Likewise, if a thread tries
 * to take an element and no element is currently present, that thread is
 * blocked until a thread insert an element into the queue.
 * 
 * @author Amit Sadafule
 *
 *         09-Oct-2018
 */
public class SynchronousQueueExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BlockingQueue<Event> queue = new SynchronousQueue<Event>();
		Producer producer = new Producer(queue, new SimpleIntGenerator());
		Consumer consumer = new Consumer(queue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
	}

}
