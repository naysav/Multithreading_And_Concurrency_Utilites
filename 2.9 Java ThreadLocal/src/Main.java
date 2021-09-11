import java.util.UUID;

public class Main {

	public static void main(String[] args) {
		new Main().testThreadLocal();
		new Main().testInheritableThreadLocal();
	}

	public void testThreadLocal() {
		new Thread(MyRunnable, "First").start();
		new Thread(MyRunnable, "Second").start();
	}

	Runnable MyRunnable = new Runnable() {
		private ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<Integer>();
		private ThreadLocal<String> threadLocalString = new ThreadLocal<String>();

		@Override
		public void run() {
			threadLocalInteger.set((int) (Math.random() * 100D));
			threadLocalString.set(UUID.randomUUID().toString());

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}

			System.out.println(Thread.currentThread().getName() +
					" I: " + threadLocalInteger.get() + " S: " + threadLocalString.get());
		}
	};

	public void testInheritableThreadLocal() {
		ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
		InheritableThreadLocal<Integer> inheritableThreadLocal =
				new InheritableThreadLocal<Integer>();

		Thread parentThread = new Thread(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}

			System.out.println(Thread.currentThread().getName() + "============");
			threadLocal.set(666);
			inheritableThreadLocal.set(777);

			System.out.println(threadLocal.get() + "||" + inheritableThreadLocal.get());

			Thread childThread = new Thread( () -> {
				System.out.println(Thread.currentThread().getName() + "=============");
				if (threadLocal.get() == null)
					System.out.println("threadLocal is not available -> " + threadLocal.get());
				if (inheritableThreadLocal.get() == 777)
					System.out.println("child using 777");
				System.out.println(threadLocal.get() + "||" + inheritableThreadLocal.get());
			}, "childThread");
			childThread.start();
		}, "parentThread");

		parentThread.start();

		Thread otherThread = new Thread(() -> {
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {}

			System.out.println(Thread.currentThread().getName() + "=============");
			if (threadLocal.get() == null)
				System.out.println("threadLocal is not available -> " + threadLocal.get());
			if (inheritableThreadLocal.get() == null)
				System.out.println("inheritableThreadLocal is not available -> " + threadLocal.get());
		}, "otherThread");
		otherThread.start();
	}
}

