public class Main {
	static public void main(String[] args){
		//Synchronized
		SynchronizedCounter syncCounter = new SynchronizedCounter();
		Thread synchronizedThread1 = new Thread("sT1") {
			@Override
			public void run() {
				syncCounter.add(1);
				syncCounter.bl_add(1);
				syncCounter.st_add(1);
			}
		};
		Thread synchronizedThread2 = new Thread("sT2") {
			@Override
			public void run() {
				syncCounter.add(10);
				syncCounter.bl_add(10);
				syncCounter.st_add(10);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		synchronizedThread1.start();
		synchronizedThread2.start();

		//AtomicInteger
		AtomicCounter atomCounter = new AtomicCounter();
		Thread atomicThread1 = new Thread("aT1") {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				atomCounter.setAtomicInteger(3);
				atomCounter.atomicAdd(2);
			}
		};
		Thread atomicThread2 = new Thread("aT2") {
			@Override
			public void run() {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				atomCounter.atomicAdd(3);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				atomCounter.setAtomicInteger(0);
			}
		};
		atomicThread1.start();
		atomicThread2.start();

		//LockInteger
		LockCounter lockCounter = new LockCounter();
		Thread lockThread1 = new Thread("lT1") {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lockCounter.lock_add(7);
			}
		};
		Thread lockThread2 = new Thread("lT2") {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lockCounter.lock_add(4);
			}
		};
		lockThread1.start();
		lockThread2.start();
	}
}
