package mPA.utils;

import org.apache.commons.lang3.time.StopWatch;


import static java.lang.Thread.sleep;

public class CommonUtil {

	   public static StopWatch stopWatch = new StopWatch();

	    public static void startTimer(){
	        stopWatchReset();
	        stopWatch.start();
	    }

	    public static void timeTaken(){
	        stopWatch.stop();
	        LoggerUtil.log("Total Time Taken : " +stopWatch.getTime());
	    }

	    public static void delay(long delayMilliSeconds)  {
	        try{
	            sleep(delayMilliSeconds);
	        }catch (Exception e){
	        	LoggerUtil.log("Exception is :" + e.getMessage());
	        }

	    }

	    public static void stopWatchReset(){
	        stopWatch.reset();
	    }

	}