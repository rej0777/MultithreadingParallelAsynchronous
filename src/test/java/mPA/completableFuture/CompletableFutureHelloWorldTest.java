package mPA.completableFuture;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;

import mPA.utils.CommonUtil;

class CompletableFutureHelloWorldTest {
	
	HelloWorldService hws= new HelloWorldService();
	CompletableFutureHelloWorld cfhw= new CompletableFutureHelloWorld(hws);
	

	@Test
	void helloWorld() {
		CompletableFuture<String> completableFuture =cfhw.helloWorld();
		
		completableFuture.thenAccept(t -> {
			assertEquals("HELLO WORLD", t);
		} ).join();
		
	}

	@Test
	void hellowold_multiple_async_calla() {
		String  helloWorld =cfhw.hellowold_multiple_async_calla();
		
		assertEquals("HELLO WORLD!", helloWorld);		
	}
	
	@Test
	void hellowold_3_async_calla() {
		String  helloWorld =cfhw.hellowold_3_async_callas();
		
		assertEquals("HELLO WORLD!HI FROM FUTURE", helloWorld);		
	}
	
	
	@Test
	void helloWorld_thenCompose() {
		CommonUtil.startTimer();
		CompletableFuture<String> completableFuture =cfhw.helloWorld_thenCompose();
		
		completableFuture.thenAccept(t -> {
			assertEquals("HELLO WORLD!", t);
		} ).join();
		CommonUtil.timeTaken();
	}
}
