package examples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		printSeparator();
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
		printSeparator();
		return CompletableFuture.runAsync(() -> System.out.println("runAsync"));
	}

	/**
	 * CompletableFuture.supplyAsync does return any output. It accepts Supplier
	 * instance
	 * 
	 * @return
	 */
	static Future<String> supplyAsync() {
		printSeparator();
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
		printSeparator();
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
		printSeparator();
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
		printSeparator();
		return CompletableFuture.supplyAsync(() -> "call for ").thenRun(() -> System.out.println("thenRun"));
	}

	/**
	 * The result of this chaining is itself a CompletableFuture that allows further
	 * chaining and combining. This approach is ubiquitous in functional languages
	 * and is often referred to as a monadic design pattern. thenCompose method us
	 * used to chain two Futures <b>sequentially</b>. Notice that this method takes
	 * a function that returns a CompletableFuture instance. The argument of this
	 * function is the result of the previous computation step. This allows us to
	 * use this value inside the next CompletableFuture‘s lambda
	 * 
	 * @return
	 */
	static Future<String> thenCompose() {
		printSeparator();
		return CompletableFuture.supplyAsync(() -> "call for ")
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "thenCompose"));
	}

	/**
	 * If you want to execute two independent Futures and do something with their
	 * results, use the thenCombine method that accepts a Future and a Function with
	 * two arguments to process both results
	 * 
	 * @return
	 */
	static Future<String> thenCombine() {
		printSeparator();
		return CompletableFuture.supplyAsync(() -> "call for ")
				.thenCombine(CompletableFuture.supplyAsync(() -> "thenCombine"), (value1, value2) -> value1 + value2);
	}

	/**
	 * When you want to do something with two Futures‘ results, but don’t need to
	 * pass any resulting value down a Future chain then use thenAcceptBoth method
	 * 
	 * @return
	 */
	static Future<Void> thenAcceptBoth() {
		printSeparator();
		return CompletableFuture.supplyAsync(() -> "call for ").thenAcceptBoth(
				CompletableFuture.supplyAsync(() -> "thenAcceptBoth"),
				(value1, value2) -> System.out.println(value1 + value2));
	}

	/**
	 * The CompletableFuture.allOf static method allows to wait for completion of
	 * all of the Futures provided as a var-arg. Return type of the
	 * CompletableFuture.allOf() is a CompletableFuture<Void>. The limitation of
	 * this method is that it does not return the combined results of all Futures.
	 * Instead you have to manually get results from Futures.
	 * 
	 * @return
	 */
	static Future<Void> combineMultipleFutures() {
		printSeparator();
		return CompletableFuture.allOf(CompletableFuture.supplyAsync(() -> "This"),
				CompletableFuture.supplyAsync(() -> "is"),
				CompletableFuture.supplyAsync(() -> "CompletableFuture.join"));
	}

	/**
	 * CompletableFuture.join() method and Java 8 Streams API allows us to return
	 * output
	 * 
	 * @return
	 */
	static String combineMultipleFuturesWithStreams() {
		printSeparator();
		String output = Stream
				.of(CompletableFuture.supplyAsync(() -> "This"), CompletableFuture.supplyAsync(() -> "is"),
						CompletableFuture.supplyAsync(() -> "Stream power"))
				.map(CompletableFuture::join).collect(Collectors.joining(" "));
		return output;
	}

	/**
	 * CompletableFuture class allows you to handle it in a special handle method.
	 * This method receives two parameters: a result of a computation (if it
	 * finished successfully) and the exception thrown (if some computation step did
	 * not complete normally)
	 * 
	 * @return
	 */
	static Future<String> handleExceptions() {
		printSeparator();
		return CompletableFuture.supplyAsync(() -> {
			int i = 0;
			if (i == 0) {
				throw new RuntimeException("i is zero.. :P");
			}
			return "call for handleExceptions";
		}).handle((s, t) -> s != null ? s : t.getMessage());
	}

	@SuppressWarnings("rawtypes")
	private static void getAndPrintOutput(String message, Future future)
			throws InterruptedException, ExecutionException {
		System.out.println("******** " + message + " ********");
		System.out.println("Value : " + future.get());
	}

	private static void printSeparator() {
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
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

			future = thenCompose();
			getAndPrintOutput("CompletableFuture.supplyAsync.thenCompose", future);

			future = thenCombine();
			getAndPrintOutput("CompletableFuture.supplyAsync.thenCombine", future);

			voidFuture = thenAcceptBoth();
			getAndPrintOutput("CompletableFuture.supplyAsync.thenAcceptBoth", voidFuture);

			voidFuture = combineMultipleFutures();
			getAndPrintOutput("CompletableFuture.allOf", voidFuture);

			System.out.println("Value : " + combineMultipleFuturesWithStreams());

			future = handleExceptions();
			getAndPrintOutput("CompletableFuture.supplyAsync.handle", future);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
