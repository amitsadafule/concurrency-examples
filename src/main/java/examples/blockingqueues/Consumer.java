package examples.blockingqueues;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Producer will continuously try to consume events from queue
 * 
 * @author Amit Sadafule
 *
 *         03-Oct-2018
 */
final class Consumer implements Runnable {

	private BlockingQueue<Event> queue = null;

	Consumer(BlockingQueue<Event> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Random randomMillis = new Random();
		for (;;) {
			try {
				System.out.println("[Consumer] [" + new Date()
						+ "] Listening to queue");
				int data = queue.take().getData();
				System.out.println("[Consumer] [" + new Date()
						+ "] Consumed : " + data);
				//enable this for slow consumer
				//Thread.sleep(randomMillis.nextInt(2000)); 
				// enable this for priority queue example
				//Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
