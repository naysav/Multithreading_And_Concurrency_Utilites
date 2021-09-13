import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.*;

public class Main {
	ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
	ReadWriteLock lock = new ReadWriteLock();

	Map<String, String> map = new HashMap<String, String>();

	public static void main(String[] args) {
		new Main().start();
	}

	public void start() {
		Thread reentrantLockThread1 = new Thread(() -> {
			reentrantLock.writeLock().lock();
			try {
				try {
				Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				map.put("1", "apple");
				System.out.println(Thread.currentThread().getName() + " write apple!");
			} finally {
				reentrantLock.writeLock().unlock();
			}
			reentrantLock.writeLock().lock();
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				map.put("2", "tea");
				System.out.println(Thread.currentThread().getName() + " write tea!");
			} finally {
				reentrantLock.writeLock().unlock();
			}
			reentrantLock.readLock().lock();
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				System.out.println(Thread.currentThread().getName() +
						" read 1 " + map.get("1"));
			} finally {
				reentrantLock.readLock().unlock();
			}

		}, "reentrantLockThread1");

		Thread reentrantLockThread2 = new Thread(() -> {
			reentrantLock.writeLock().lock();
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				map.put("4", "coffee");
				System.out.println(Thread.currentThread().getName() + " write coffee!");
			} finally {
				reentrantLock.writeLock().unlock();
			}
			reentrantLock.readLock().lock();
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				System.out.println(Thread.currentThread().getName() +
						" read 4 " + map.get("4"));
			} finally {
				reentrantLock.readLock().unlock();
			}
			reentrantLock.writeLock().lock();
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				map.put("3", "orange");
				System.out.println(Thread.currentThread().getName() + " write orange!");
			} finally {
				reentrantLock.writeLock().unlock();
			}
		}, "reentrantLockThread2");

		Thread lockThread1 = new Thread(() -> {
			try {
				lock.lockWrite();
			} catch (InterruptedException ignored) {}
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				map.put("5", "lemon");
				System.out.println(Thread.currentThread().getName() + " write lemon!");
			} finally {
				try {
					lock.unlockWrite();
				} catch (InterruptedException ignored) {}
			}

			try {
				lock.lockWrite();
			} catch (InterruptedException ignored) {}
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				map.put("6", "mango");
				System.out.println(Thread.currentThread().getName() + " write mango!");
			} finally {
				try {
					lock.unlockWrite();
				} catch (InterruptedException ignored) {}
			}
		}, "lockThread1");

		Thread lockThread2 = new Thread(() -> {
			try {
				lock.lockRead();
			} catch (InterruptedException ignored) {}
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				System.out.println(Thread.currentThread().getName() +
						" read 5 " + map.get("5"));
			} finally {
				lock.unlockRead();
			}

			try {
				lock.lockRead();
			} catch (InterruptedException ignored) {}
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				System.out.println(Thread.currentThread().getName() +
						" read 6 " + map.get("6"));
			} finally {
				lock.unlockRead();
			}

			try {
				lock.lockWrite();
			} catch (InterruptedException ignored) {}
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				map.put("1", "vodka");
				System.out.println(Thread.currentThread().getName() +
						" write again in 1 vodka!");
			} finally {
				try {
					lock.unlockWrite();
				} catch (InterruptedException ignored) {}
			}

			reentrantLock.readLock().lock();
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {}
				System.out.println(map.get("1") + " " + map.get("2") +
						" " +map.get("3") + " "+ map.get("4") + " " +
						map.get("5") + " " + map.get("6"));
			} finally {
				reentrantLock.readLock().unlock();
			}
		}, "lockThread2");

		reentrantLockThread1.start();
		reentrantLockThread2.start();
		lockThread1.start();
		lockThread2.start();
	}
}
