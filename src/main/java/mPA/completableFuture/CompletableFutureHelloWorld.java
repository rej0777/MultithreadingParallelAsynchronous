package mPA.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    
    public String helloworld_3_async_calls_custom_threadpool(){
    	CommonUtil.startTimer();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello(),executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world(),executorService);
        CompletableFuture<String> hiCompletableFuture =  CompletableFuture.supplyAsync(()->{
        	CommonUtil.delay(1000);
            return " Hi CompletableFuture!";
        },executorService);

        String hw= hello
                .thenCombine(world, (h, w) -> {
                	LoggerUtil.log("thenCombine h/w");
                    return h+w;
                }) // first, second
                .thenCombine(hiCompletableFuture, (previous,current)->{
                	LoggerUtil.log("thenCombine previous/current");
                    return previous+current;
                })
                .thenApply(s -> {
                	LoggerUtil.log("thenApply");
                    return  s.toUpperCase();
                })
                .join();

        CommonUtil.timeTaken();
        return hw;
    } 
    
    public String helloworld_3_async_calls_log_asynch(){
    	CommonUtil.startTimer();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello(),executorService);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world(),executorService);
        CompletableFuture<String> hiCompletableFuture =  CompletableFuture.supplyAsync(()->{
        	CommonUtil.delay(1000);
            return " Hi CompletableFuture!";
        },executorService);

        String hw= hello
                .thenCombineAsync(world, (h, w) -> {
                	LoggerUtil.log("thenCombine h/w");
                    return h+w;
                }) // first, second
                .thenCombineAsync(hiCompletableFuture, (previous,current)->{
                	LoggerUtil.log("thenCombine previous/current");
                    return previous+current;
                })
                .thenApplyAsync(s -> {
                	LoggerUtil.log("thenApply");
                    return  s.toUpperCase();
                })
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