package mPA.completableFuture;

import java.util.concurrent.CompletableFuture;

import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;

public class HelloWorldService {

    public  String helloWorld() {
    	CommonUtil.delay(1000);
        LoggerUtil.log("inside helloWorld");
        return "hello world";
    }

    public  String hello() {
    	CommonUtil.delay(1000);
        LoggerUtil.log("inside hello");
        return "hello";
    }

    public  String world() {
    	CommonUtil.delay(1000);
        LoggerUtil.log("inside world");
        return " world!";
    }

    public CompletableFuture<String> worldFuture(String input) {
        return CompletableFuture.supplyAsync(()->{
        	CommonUtil.delay(1000);
            return input+" world!";
        });
    }

}