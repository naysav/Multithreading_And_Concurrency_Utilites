public class Main {
	public static void main(String[] args) throws Exception {
		new Main().test();
	}

	public void test() {
		Lock lock = new Lock();

		Thread threadLock1 = new Thread(() -> {
			System.out.println("==" + Thread.currentThread().getName() + "==");
			try {
				lock.lock();
			} catch (InterruptedException ignored) {}
			for (int i = 0; i < 5; i++)
				System.out.println(Thread.currentThread().getName() + "-" + i);
			lock.unlock();
		}, "tL1");

		Thread threadLock2 = new Thread(() -> {
			System.out.println("==" + Thread.currentThread().getName() + "==");
			try {
				lock.lock();
			} catch (InterruptedException ignored) {}
			for (int i = 0; i < 5; i++)
				System.out.println(Thread.currentThread().getName() + "-" + i);
			lock.unlock();
		}, "tL2");

		threadLock1.start();
		threadLock2.start();

		try {
			threadLock1.join();
			threadLock2.join();
		} catch (InterruptedException ignored) {}

		BoundedSemaphore boundedSemaphore = new BoundedSemaphore(1);

		Thread threadBoundedSemaphore1 = new Thread(() -> {
			System.out.println("==" + Thread.currentThread().getName() + "==");
			try {
				boundedSemaphore.take();
			} catch (InterruptedException ignored) {}
			for (int i = 0; i < 5; i++)
				System.out.println(Thread.currentThread().getName() + "-" + i);
			try {
				boundedSemaphore.release();;
			} catch (InterruptedException ignored) {}
		}, "tBS1");

		Thread threadBoundedSemaphore2 = new Thread(() -> {
			System.out.println("==" + Thread.currentThread().getName() + "==");
			try {
				boundedSemaphore.take();
			} catch (InterruptedException ignored) {}
			for (int i = 0; i < 5; i++)
				System.out.println(Thread.currentThread().getName() + "-" + i);
			try {
				boundedSemaphore.release();;
			} catch (InterruptedException ignored) {}
		}, "tBS2");

		threadBoundedSemaphore1.start();
		threadBoundedSemaphore2.start();
	}
}