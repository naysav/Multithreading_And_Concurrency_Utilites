import java.lang.Runnable;

//Class Implements Runnable
public class MyRunnable implements Runnable{
	private boolean doStop = false;

	public synchronized void doStop() {
		this.doStop = true;
	}

	private synchronized boolean keepRunning() {
		return this.doStop == false;
	}
	@Override
	public void run(){
		while(keepRunning()) {
			System.out.println("MyRunnable is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
