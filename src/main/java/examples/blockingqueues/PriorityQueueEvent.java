package examples.blockingqueues;

/**
 * @author Amit Sadafule
 *
 * 03-Oct-2018
 */
public class PriorityQueueEvent implements Event, Comparable<PriorityQueueEvent> {

	private int data;
	
	PriorityQueueEvent(int data) {
		this.data = data;
	}
	
	@Override
	public int getData() {
		return data;
	}

	@Override
	public int compareTo(PriorityQueueEvent o) {
		if(o.getData() > this.getData())
			return 1;
		else if (o.getData() < this.getData())
			return -1;
		return 0;
	}

}
