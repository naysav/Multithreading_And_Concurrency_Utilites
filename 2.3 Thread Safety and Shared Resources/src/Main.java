public class Main {

	static public void main(String[] args) {
		ExampleClass exClass = new ExampleClass();
		Thread exampleThread1 = new Thread("eT1") {
			@Override
			public void run() {
				exClass.useCounter(1);
				exClass.useCounter(exClass.useLocalVariable(3));
				exClass.useCounter(exClass.useLocalObjectReference(2));
			}
		};
		Thread exampleThread2 = new Thread("eT2") {
			@Override
			public void run() {
				exClass.useCounter(1);
				exClass.useCounter(exClass.useLocalVariable(3));
				exClass.useCounter(exClass.useLocalObjectReference(2));
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		exampleThread1.start();
		exampleThread2.start();
	}
}