public class BoundedSemaphore {
	private int signals = 0;
	private int bound   = 0;

	public BoundedSemaphore(int upperBound){
		this.bound = upperBound;
		System.out.println(Thread.currentThread().getName() +
				"constructor/STATE initialization:");
		System.out.println(Thread.currentThread().getName() +
				"signals = " + signals);
		System.out.println(Thread.currentThread().getName() +
				"bound = " + bound);
	}

	public synchronized void take() throws InterruptedException{
		System.out.println(Thread.currentThread().getName() +
				" take/TEST-AND-SET-method starting");
		System.out.println(Thread.currentThread().getName() +
				" take/ACCESS CONDITION = " + (this.signals == bound));
		while(this.signals == bound) {
			System.out.println(Thread.currentThread().getName() +
					" take/WAIT STRATEGY");
			wait();
		}
		this.signals++;
		System.out.println(Thread.currentThread().getName() +
				" take/STATE CHANGE (++) = " + this.signals);
		this.notify();
		System.out.println(Thread.currentThread().getName() +
				" take/NOTIFICATION STRATEGY (notify)");
	}

	public synchronized void release() throws InterruptedException{
		System.out.println(Thread.currentThread().getName() +
				" release/TEST-AND-SET-method starting");
		System.out.println(Thread.currentThread().getName() +
				" release/ACCESS CONDITION = " + (this.signals == bound));
		while(this.signals == 0) {
			System.out.println(Thread.currentThread().getName() +
					" release/WAIT STRATEGY (wait)");
			wait();
		}
		this.signals--;
		System.out.println(Thread.currentThread().getName() +
				" release/STATE CHANGE (--) = " + this.signals);
		this.notify();
		System.out.println(Thread.currentThread().getName() +
				" release/NOTIFICATION STRATEGY");
	}
}