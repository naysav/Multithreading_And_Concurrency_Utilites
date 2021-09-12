public class SignalClass{
	final MonitorObject myMonitorObject = new MonitorObject();
	boolean wasSignalled = false;
	int stopCounter = 0;

	public void doWait(){
		stopCounter++;
		synchronized(myMonitorObject){
			while (!wasSignalled){  // while - doing the spin lock (without Spurious Wakeups)
				try{
					myMonitorObject.wait();
				} catch(InterruptedException ignored) {}
			}
			wasSignalled = false;   //clear signal and continue running.
		}
	}

	public void doNotify(){
		synchronized(myMonitorObject){
			wasSignalled = true;
			myMonitorObject.notify();
		}
	}
}

