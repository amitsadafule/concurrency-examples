package examples.blockingqueues;

/**
 * @author Amit Sadafule
 *
 * 03-Oct-2018
 */
public class ComparableEventGenerator implements EventGenerator {

	@Override
	public Event generate(int i) {
		return new PriorityQueueEvent(i);
	}

}
