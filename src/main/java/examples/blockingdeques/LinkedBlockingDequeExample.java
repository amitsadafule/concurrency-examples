package examples.blockingdeques;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The word Deque comes from the term "Double Ended Queue". A Deque is thus a
 * queue where you can insert and remove elements from both ends of the queue.
 * 
 * The LinkedBlockingDeque is a Deque which will block if a thread attempts to
 * take elements out of it while it is empty, regardless of what end the thread
 * is attempting to take elements from.
 * 
 * @author Amit Sadafule
 *
 *         10-Oct-2018
 */
public class LinkedBlockingDequeExample {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

		deque.addFirst("1");
		deque.add("3");
		deque.addLast("2");
		deque.add("4"); // adds to last of the queue
		System.out.println(deque);

		System.out.println(deque.takeLast()); //synchronous call
		System.out.println(deque.takeFirst());
	}

}
