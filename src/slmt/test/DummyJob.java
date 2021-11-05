package slmt.test;

public class DummyJob implements Runnable {
	
	private long outerStartTime;
	private long innerStartTime;
	private long stopTime;

	@Override
	public void run() {
		startInnerTimer();
		
		// Doing job
		long sum = 0;
		for (long i = 0; i < 1_000_000; i++) {
			
			if (i % 100_000 == 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			sum += i;
			if (sum > 100_000_000)
				sum = 0;
		}
		
		stopTimers();
	}

	public void startOuterTimer() {
		outerStartTime = System.nanoTime();
	}
	
	public void startInnerTimer() {
		innerStartTime = System.nanoTime();
	}
	
	public void stopTimers() {
		stopTime = System.nanoTime();
	}
	
	public long getOuterTimeUs() {
		return (stopTime - outerStartTime) / 1000;
	}
	
	public long getInnerTimeUs() {
		return (stopTime - innerStartTime) / 1000;
	}
}
