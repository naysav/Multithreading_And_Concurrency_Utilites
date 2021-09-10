public class ExampleClass {
	private int counter = 0;

	public int getCounter() {
		return counter;
	}

	//Safe using Object Member Variable
	public void useCounter(int value) {
		synchronized(this) {
			this.counter += value;
			System.out.println(Thread.currentThread().getName() + "/Counter: " + counter);
		}
	}

	//Local Variable for Threads
	public int useLocalVariable(int value) {
		int threadSafeInt = 0;

		while (threadSafeInt < value)
			threadSafeInt++;
		System.out.println(Thread.currentThread().getName() + "/SafeInt: " + threadSafeInt);
		return threadSafeInt;
	}

	//Local Object Reference for Threads
	public int useLocalObjectReference(int value){
		LocalObject localObject = new LocalObject();

		changeLocalObject(localObject, value);
		System.out.println(Thread.currentThread().getName() + "/Field: " + localObject.getField());
		return localObject.getField();
	}

	public void changeLocalObject(LocalObject localObject, int value){
		localObject.setField(value);
		localObject.doubleField();
		localObject.doubleField();
	}


}
