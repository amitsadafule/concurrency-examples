package examples.blockingqueues;

/**
 * @author Amit Sadafule
 *
 * 03-Oct-2018
 */
final class SimpleIntEvent implements Event {

	private int data;
	SimpleIntEvent(int data) {
		this.data = data;
	}
	
	@Override
	public int getData() {
		return data;
	}

}
