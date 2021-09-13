//import java.util.concurrent.Semaphore;

public class Main {
	BoundedSemaphore semaphoreLock = new BoundedSemaphore(1);
	BoundedSemaphore semaphoreBounded = new BoundedSemaphore(2);
	Semaphore semaphore = new Semaphore();
	private String string = new String("Our threads: ");


	public static void main(String[] args) {
		new Main().start();
	}

	public void start() {
		Runnable MyRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					semaphoreBounded.take();
				} catch (InterruptedException ignored) {
				}
				try {
					System.out.println(Thread.currentThread().getName() + " starting!");
				} finally {
					try {
						semaphoreBounded.release();
					} catch (InterruptedException ignored) {
					}
				}

				try {
					semaphoreLock.take();
				} catch (InterruptedException ignored) {
				}
				try {
					string = string + Thread.currentThread().getName() + " ";
				} finally {
					try {
						semaphoreLock.release();
					} catch (InterruptedException ignored) {
					}
				}

				try {
					semaphore.release();
				} catch (InterruptedException ignored) {}

				try {
					semaphoreLock.take();
				} catch (InterruptedException ignored) {
				}
				try {
					string = Thread.currentThread().getName();
				} finally {
					try {
						semaphoreLock.release();
					} catch (InterruptedException ignored) {
					}
				}
				System.out.println(Thread.currentThread().getName() + " is over!");
			}
		};

		Thread threadLast = new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException ignored) {}

			System.out.println(string);
			for (int i = 0; i < 4; i++){
				try {
					Thread.sleep(500);
					semaphore.take();
				} catch (InterruptedException ignored) {}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {}
			System.out.println(string);

		}, "thread1");

		Thread t1 = new Thread(MyRunnable, "First");
		Thread t2 = new Thread(MyRunnable, "Second");
		Thread t3 = new Thread(MyRunnable, "Third");
		Thread t4 = new Thread(MyRunnable, "Fourth");

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		threadLast.start();
	}
}
