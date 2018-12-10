package examples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Reference : https://www.baeldung.com/java-completablefuture
 * 
 * @author Amit Sadafule
 *
 *         01-Oct-2018
 */
public class CompletableFutureExample {

	/**
	 * you can create an instance of this class with a no-arg constructor to
	 * represent some future result, hand it out to the consumers and complete it at
	 * some time in the future using the complete method. The consumers may use the
	 * get method to block the current thread until this result will be provided.
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	static Future<String> calculateAsync() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(500);
			completableFuture.complete("completed");
			return null;
		});

		return completableFuture;
	}

	/**
	 * CompletableFuture.runAsync does not return any output. It accepts Runnable
	 * instance
	 * 
	 * @return
	 */
	static Future<Void> runAsync() {
		return CompletableFuture.runAsync(() -> System.out.println("runAsync"));
	}

	/**
	 * CompletableFuture.supplyAsync does return any output. It accepts Supplier
	 * instance
	 * 
	 * @return
	 */
	static Future<String> supplyAsync() {
		return CompletableFuture.supplyAsync(() -> "Supply Async call");
	}

	/**
	 * The thenApply method does exactly that: accepts a Function instance, uses it
	 * to process the result and returns a Future that holds a value returned by a
	 * function
	 * 
	 * @return
	 */
	static Future<String> thenApply() {
		return CompletableFuture.supplyAsync(() -> "call for ").thenApply(output -> output + "thenApply");
	}

	/**
	 * If you don’t need to return a value down the Future chain, you can use an
	 * instance of the Consumer functional interface. Its single method takes a
	 * parameter and returns void
	 * 
	 * @return
	 */
	static Future<Void> thenAccept() {
		return CompletableFuture.supplyAsync(() -> "call for ")
				.thenAccept(output -> System.out.println(output + "thenAccept"));
	}

	/**
	 * if you neither need the value of the computation nor want to return some
	 * value at the end of the chain, then you can pass a Runnable lambda to the
	 * thenRun method
	 * 
	 * @return
	 */
	static Future<Void> thenRun() {
		return CompletableFuture.supplyAsync(() -> "call for ").thenRun(() -> System.out.println("thenRun"));
	}

	@SuppressWarnings("rawtypes")
	private static void getAndPrintOutput(String message, Future future)
			throws InterruptedException, ExecutionException {
		System.out.println("******** " + message + " ********");
		System.out.println("Value : " + future.get());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Future<String> future = calculateAsync();
			getAndPrintOutput("CompletableFuture as Future", future);

			Future<Void> voidFuture = runAsync();
			getAndPrintOutput("CompletableFuture.runAsync", voidFuture);

			future = supplyAsync();
			getAndPrintOutput("CompletableFuture.supplyAsync", future);

			future = thenApply();
			getAndPrintOutput("CompletableFuture.supplyAsync.thenApply", future);

			voidFuture = thenAccept();
			getAndPrintOutput("CompletableFuture.supplyAsync.thenAccept", voidFuture);

			voidFuture = thenRun();
			getAndPrintOutput("CompletableFuture.supplyAsync.thenRun", voidFuture);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
