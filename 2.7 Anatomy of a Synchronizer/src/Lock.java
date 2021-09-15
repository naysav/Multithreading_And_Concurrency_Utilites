public class Lock{
	private boolean isLocked = false;

	public synchronized void lock()	throws InterruptedException{
		System.out.println(Thread.currentThread().getName() +
				" lock/SET-method starting");
		System.out.println(Thread.currentThread().getName() +
				" lock/STATE and lock/ACCESS CONDITION = " + isLocked);
		while(isLocked){
			System.out.println(Thread.currentThread().getName() + " lock/WAIT STRATEGY");
			wait();
		}
		isLocked = true;
		System.out.println(Thread.currentThread().getName() + " lock/STATE CHANGE (true)");
	}

	public synchronized void unlock(){
		System.out.println(Thread.currentThread().getName() + " unlock/SET-method starting");
		isLocked = false;
		System.out.println(Thread.currentThread().getName() + " unlock/STATE CHANGE (false)");
		notify();
		System.out.println(Thread.currentThread().getName() + " unlock/NOTIFICATION STRATEGY");
	}
}