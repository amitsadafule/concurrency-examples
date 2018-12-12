package examples;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * CountDownLatch works in latch principle, main thread will wait until Gate is
 * open. One thread waits for n number of threads specified while creating
 * CountDownLatch in Java. Any thread, usually main thread of application, which
 * calls CountDownLatch.await() will wait until count reaches zero or its
 * interrupted by another Thread. <br>
 * All other thread are required to do count down
 * by calling CountDownLatch.countDown() once they are completed or ready to the
 * job. as soon as count reaches zero, Thread awaiting starts running. One of
 * the disadvantage of CountDownLatch is that <strong>its not reusable once count
 * reaches to zero</strong>
 * 
 * @author Amit Sadafule
 *
 *         01-Oct-2018
 */
public class CountDownLatchExample {

	private static final CountDownLatch latch = new CountDownLatch(3);

	public static void main(String[] args) {

		Service networkService = new Service("Network service", 500);
		Service dbService = new Service("DB service", 1000);
		Service cacheService = new Service("Cache service", 2000);

		// Start the services
		try {
			Executors.newFixedThreadPool(3).invokeAll(Arrays.asList(networkService, dbService, cacheService));

			// wait for services to be started
			latch.await();
			System.out.println("Application started successfully!!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class Service implements Callable<Void> {
		private String serviceName;
		private int delay;

		Service(String message, int delay) {
			this.serviceName = message;
			this.delay = delay;
		}

		@Override
		public Void call() throws Exception {
			System.out.println(serviceName + " is starting...");
			try {
				Thread.sleep(delay);
				latch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(serviceName + " is started");
			return null;
		}
	}
}
