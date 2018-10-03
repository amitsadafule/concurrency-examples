package examples.blockingqueues;

/**
 * @author Amit Sadafule
 *
 * 03-Oct-2018
 */
final class SimpleIntGenerator implements EventGenerator {

	@Override
	public Event generate(int data) {
		return new SimpleIntEvent(data);
	}
}
