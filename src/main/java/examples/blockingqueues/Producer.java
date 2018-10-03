package examples.blockingqueues;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Producer will generate events after random milli-seconds
 * @author Amit Sadafule
 *
 * 03-Oct-2018
 */
final class Producer implements Runnable {

	private BlockingQueue<Event> queue = null;
	private EventGenerator generator = null;
	
	Producer(BlockingQueue<Event> queue, EventGenerator generator) {
		this.queue = queue;
		this.generator = generator;
	}
	
	@Override
	public void run() {
		Random randomMillis = new Random();
		for(int i = 0;;i++) {
			try {
				System.out.println("[Producer] " + new Date()
						+ "] Produced : " + i);
				queue.put(generator.generate(i));
				System.out.println("[Producer] " + new Date()
						+ "] Added to queue : " + i);
				Thread.sleep(randomMillis.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
