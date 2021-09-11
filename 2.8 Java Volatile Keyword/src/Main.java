public class Main {

	public static void main(String[] args) {
		new Main().start();
	}

	volatile private boolean let = false;

	public void start() {
		new Thread(checkLet).start();
		System.out.println("checking lets starting");
		new Thread(movement).start();
		System.out.println("movement starting");
	}

	Runnable checkLet = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}
			let = true;
			System.out.println("let is finding");
		}
	};

	Runnable movement = new Runnable() {
		@Override
		public void run() {
			int destroyTimer = 6;

			while (true) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {}
				if (!let){
					System.out.println("moving");
					destroyTimer = 10;
				}
				if (let && destroyTimer > 0){
					System.out.println("destroying");
					destroyTimer--;
				}
				if (destroyTimer == 0){
					System.out.println("Clear!");
					break;
				}
			}
		}
	};
}
