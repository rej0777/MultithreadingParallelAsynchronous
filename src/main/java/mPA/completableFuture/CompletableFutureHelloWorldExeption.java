package mPA.completableFuture;

import java.util.concurrent.CompletableFuture;

import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;

public class CompletableFutureHelloWorldExeption {

	HelloWorldService hws;
	
    public CompletableFutureHelloWorldExeption(HelloWorldService hws) {this.hws = hws;}
	
	
    public String hellowold_3_async_callas_handle() {
        
    	CommonUtil.startTimer();
    	
    	CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
    	CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
    	CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
    		CommonUtil.delay(1000);
    		return  " Hi CompletableFuture";
    	});
    	
    	String hw= hello
    			.handle((res, e) -> {
    				if (e!=null) {
						LoggerUtil.log("Exeption is :"+ e.getMessage() );
						return "";
					}else {
						return res;
					}
    				
    			})
    			.thenCombine(world, (t, u) -> t+u )
    			.handle((res, e) -> {
    				if (e!=null) {
    					LoggerUtil.log("Exeption After World2 is :"+ e.getMessage() );
    					return "";
					}else {
						return res;
					}
    				
    			})
    			
    			.thenCombine(hiCompletableFuture, (previus, current) -> previus + current)
    			
    	.thenApply(String::toUpperCase)
    	.join();
    	
    	CommonUtil.timeTaken();
  
     return hw;
    }
    
    public String hellowold_3_async_callas_exceptionally() {
        
    	CommonUtil.startTimer();
    	
    	CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
    	CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
    	CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
    		CommonUtil.delay(1000);
    		return  " Hi CompletableFuture";
    	});
    	
    	String hw= hello
    			.exceptionally(( e) -> {   				
						LoggerUtil.log("Exeption is exceptionally :"+ e.getMessage() );
						return "";   							
    			})
    			.thenCombine(world, (t, u) -> t+u )
    			.exceptionally(( e) -> {   				
    					LoggerUtil.log("Exeption After World2 exceptionally is :"+ e.getMessage() );
    					return "";
    			})    			
    			.thenCombine(hiCompletableFuture, (previus, current) -> previus + current)
    			
    	.thenApply(String::toUpperCase)
    	.join();
    	
    	CommonUtil.timeTaken();
  
     return hw;
    }
    
    
    
 public String hellowold_3_async_callas_whenHandle() {
        
    	CommonUtil.startTimer();
    	
    	CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> hws.hello());
    	CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> hws.world());
    	CompletableFuture<String> hiCompletableFuture = CompletableFuture.supplyAsync(() -> {
    		CommonUtil.delay(1000);
    		return  " Hi CompletableFuture";
    	});
    	
    	String hw= hello
    			.whenComplete((res, e) -> {
    				if(e!=null)
    				LoggerUtil.log("Exeption is :"+ e.getMessage() );   				   				
    			})
    			.thenCombine(world, (t, u) -> t+u )
    			.whenComplete((res, e) -> {
    				if(e!=null)
    				LoggerUtil.log("Exeption After World2 is :"+ e.getMessage() );		
    			})
    			.exceptionally(( e) -> {   				
					LoggerUtil.log("Exeption after whenComplete :"+ e.getMessage() );
					return "";   							
			})
    			
    			.thenCombine(hiCompletableFuture, (previus, current) -> previus + current)
    			
    	.thenApply(String::toUpperCase)
    	.join();
    	
    	CommonUtil.timeTaken();
  
     return hw;
    }
    
}
