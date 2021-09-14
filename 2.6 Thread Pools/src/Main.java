import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

public class Main {
	private int counterOfCompletedTasks = 0;

	public static void main(String[] args) throws Exception {
		new Main().startThreadPool();
		new Main().startExecutorService();
	}

	public void startThreadPool() throws Exception {
		ThreadPool threadPool = new ThreadPool(3, 10);

		for(int j = 0; j < 25; j++) {
			threadPool.execute( () -> {
				System.out.println(Thread.currentThread().getName()
						+ " made a useless number " + (int) (Math.random() * 1000));
				counterOfCompletedTasks++;
			});
		}

		threadPool.waitUntilAllTasksFinished();
		threadPool.stop();
		System.out.println("Completed tasks in pool: " + counterOfCompletedTasks);
	}

	public void startExecutorService() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool (10);

		for(int i = 0; i < 24; i++) {

			Future future1 = executorService.submit(new Callable(){
				public Object call() throws Exception {
					counterOfCompletedTasks++;
					return ((int) (Math.random() * 100));
				}
			});

			Future<String> future2 = executorService.submit(new Callable(){
				public Object call() throws Exception {
					counterOfCompletedTasks++;
					return (UUID.randomUUID().toString() + " ");
				}
			});

			System.out.println("Generated - position: " + future1.get() +
					" ID: " + future2.get());
		}

		executorService.shutdown();
		System.out.println("Completed tasks by executors: " + counterOfCompletedTasks);
	}
}