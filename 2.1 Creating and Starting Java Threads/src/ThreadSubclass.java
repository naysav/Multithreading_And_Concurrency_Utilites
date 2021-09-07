public class ThreadSubclass extends Thread {
	@Override
	public void run(){
		for (int i = 0; i < 3; i++) {
			System.out.println("tS" + i);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
