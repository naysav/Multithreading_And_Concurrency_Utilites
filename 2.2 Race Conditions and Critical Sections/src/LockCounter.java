import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
	private ReentrantLock lock = new ReentrantLock();
	private int count = 0;

	public void lock_add(int value) {
		lock.lock();
		try {
			count += value;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		} finally {
			lock.unlock ();
		}
	}
}