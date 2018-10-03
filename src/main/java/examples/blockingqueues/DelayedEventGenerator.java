package examples.blockingqueues;

import java.util.Random;

/**
 * @author Amit Sadafule
 *
 * 03-Oct-2018
 */
final class DelayedEventGenerator implements EventGenerator {

	Random randomMillis = new Random();
	
	@Override
	public Event generate(int i) {
		return new DelayedEvent(i, 2000);
	}

}
