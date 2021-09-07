public class Main {
	static public void main(String[] args) throws InterruptedException{
		//Thread Subclass
		ThreadSubclass threadSubclass = new ThreadSubclass();
		threadSubclass.start();

		//Anonymous Subclass of Thread
		Thread anonymousSubclass = new Thread("aS") {
			@Override
			public void run () {
				for (int i = 0; i < 3; i++) {
					System.out.println(getName() + i);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};
		anonymousSubclass.start();

		//Starting a Thread With a Runnable
		MyRunnable myRun = new MyRunnable();
		Thread myRunThread = new Thread(myRun, "mR");
		myRunThread.start();

		//Starting a Thread With Anonymous Runnable
		Runnable anonymousMyRun = new Runnable() {
			@Override
			public void run(){
				System.out.println("Current thread: " + Thread.currentThread().getName());
				for (int i = 0; i < 3; i++) {
					System.out.println("aMR" + i);
				}

			}
		};
		Thread anonymousMyRunThread = new Thread(anonymousMyRun, "aMR");
		anonymousMyRunThread.start();

		//Lambda Implementation of Runnable
		Runnable lambdaRun =
				() -> {System.out.println ("Lambda Runnable executed"); };
		Thread lambdaRunThread = new Thread (lambdaRun, "lR");
		lambdaRunThread.start();
		System.out.println("Lambda Name: " + lambdaRunThread.getName());

		//Stop the Class Implements Runnable
		myRun.doStop();
	}
}
