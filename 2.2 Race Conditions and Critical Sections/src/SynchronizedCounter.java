public class SynchronizedCounter {
	private int count1 = 0;
	private static int count2 = 0;
	private int count3 = 0;

	private final Integer sum1Lock = new Integer(1);
	private final Integer sum3Lock = new Integer(3);

	public synchronized void add(int value) {
		this.count1 += value;
		System.out.println(Thread.currentThread().getName() + " count1 = " + count1);
	}

	public static synchronized void st_add(int value) {
		count2 += value;
		System.out.println(Thread.currentThread().getName() + " count2 = " + count2);
	}

	public void bl_add(int value){
		synchronized(this.sum1Lock){
			this.count1 += value;
			System.out.println(Thread.currentThread().getName() + " count1 = " + count1);
		}
		synchronized(this.sum3Lock){
			this.count3 += value;
			System.out.println(Thread.currentThread().getName() + " count3 = " + count3);
		}
	}
}
