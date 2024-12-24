package mPA.completableFuture;

import java.util.concurrent.CompletableFuture;

import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;

public class CompletableFutureHelloWorld {
	
	HelloWorldService hws;
	
    public CompletableFutureHelloWorld(HelloWorldService hws) {
		super();
		this.hws = hws;
	}


	public static void main(String[] args) {

		HelloWorldService hws = new HelloWorldService();

		     CompletableFuture.supplyAsync(() -> hws.helloWorld())
					//.thenAccept((t -> t.toUpperCase() ))
					.thenAccept((String::toUpperCase))
			        .thenAccept((result) -> {
			        	LoggerUtil.log("Result is " + result);
			        })
			        .join();
			LoggerUtil.log("Done!");
			//delay(2000);
    }
       
    public CompletableFuture<String> helloWorld (){
    	
    	return    CompletableFuture.supplyAsync(hws::helloWorld)
    			.thenApply(String::toUpperCase);   	
    } 
    
    
    public String hellowold_multiple_async_calla() {
    
    	CommonUtil.startTimer();
    	
    	CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
    	CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
    	
    	String hw= hello.thenCombine(world, (t, u) -> t+u )
    	.thenApply(String::toUpperCase)
    	.join();
    	
    	CommonUtil.timeTaken();
  
     return hw;
    }
    
    public String hellowold_3_async_callas() {
        
    	CommonUtil.startTimer();
    	
    	CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
    	CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
    	CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
    		CommonUtil.delay(1000);
    		return  "HI From FUture";
    	});
    	
    	String hw= hello.
    			thenCombine(world, (t, u) -> t+u ).
    			thenCombine(hiCompletableFuture, (previus, current)-> previus+current)
    			
    	.thenApply(String::toUpperCase)
    	.join();
    	
    	CommonUtil.timeTaken();
  
     return hw;
    }
    
    
    public CompletableFuture<String> helloWorld_thenCompose (){
    	
    	return    CompletableFuture.supplyAsync(hws::hello)
    			.thenCompose((previus)-> hws.worldFuture(previus))
    			.thenApply(String::toUpperCase);   	
    }
    
    
}