package examples.blockingqueues;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Amit Sadafule
 *
 * 03-Oct-2018
 */
public class DelayedEvent implements Event, Delayed {

	private int data;
	private long expiryTime;
	
	DelayedEvent(int data, long delay) {
		this.data = data;
		this.expiryTime = System.currentTimeMillis() + delay;
	}
	
	@Override
	public int compareTo(Delayed o) {
		if (this.expiryTime < ((DelayedEvent) o).expiryTime) {
			return -1;
		}
		if (this.expiryTime > ((DelayedEvent) o).expiryTime) {
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff = expiryTime - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	@Override
	public int getData() {
		return data;
	}

}
