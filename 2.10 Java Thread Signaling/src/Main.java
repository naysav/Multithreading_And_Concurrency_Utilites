public class Main {
	private int firstHalf = 0;
	private int secondHalf = 0;
	private int sum = 0;
	private int result = 0;

	public static void main(String[] args) {
		new Main().start();
	}

	public void start() {
		SignalClass signal = new SignalClass();

		Thread firstThread = new Thread(() -> {
			while (firstHalf < (int) (Math.random() * 100D))
				firstHalf++;
			System.out.println("1 firstHalf is " + firstHalf);

			signal.doWait();

			firstHalf = sum % 2;
			System.out.println("2 firstHalf is " + firstHalf);
		}, "firstThread");

		Thread secondThread = new Thread(() -> {
			while (secondHalf < (int) (Math.random() * 100D))
				secondHalf++;
			System.out.println("1 secondHalf is " + secondHalf);

			signal.doWait();

			secondHalf = sum % 3;
			System.out.println("2 secondHalf is " + secondHalf);
		}, "secondThread");

		Thread thirdThread = new Thread(() -> {
			try {
				Thread.sleep(3000);
			}
			catch (InterruptedException ignored) {}
			sum = firstHalf + secondHalf;
			System.out.println("SUM IS " + sum);
			signal.doNotify();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException ignored) {}
			signal.doNotify();
			try {
				Thread.sleep(3000);
			}
			catch (InterruptedException ignored) {}
			result = firstHalf + secondHalf;
			System.out.println("RESULT IS " + result);
		}, "thirdThread");

		firstThread.start();
		secondThread.start();
		thirdThread.start();
	}

}
