package slmt.test;

public class ThreadTest {
	
	private static final int THREAD_COUNT = 100;

	public static void main(String[] args) {
		DummyJob[] jobs = new DummyJob[THREAD_COUNT];
		Thread[] threads = new Thread[THREAD_COUNT];
		
		// Create threads
		for (int i = 0; i < THREAD_COUNT; i++) {
			jobs[i] = new DummyJob();
			threads[i] = new Thread(jobs[i]);
		}
		
		// Set priority
		for (int i = 0; i < THREAD_COUNT; i++) {
			threads[i].setPriority(i % 10 + 1);
		}
		
		// Start timers and threads
		for (int i = 0; i < THREAD_COUNT; i++) {
			jobs[i].startOuterTimer();
			threads[i].start();
		}
		
		// Wait for threads
		try {
			for (int i = 0; i < THREAD_COUNT; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Report results
		for (int i = 0; i < THREAD_COUNT; i++) {
			String msg = String.format("%d,%d,%d,%d",
					i, threads[i].getPriority(),
					jobs[i].getOuterTimeUs(), jobs[i].getInnerTimeUs());
			System.out.println(msg);
		}
	}

}
