package mPA.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import mPA.utils.CommonUtil;
import mPA.utils.DataSet;
import mPA.utils.LoggerUtil;

public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {

    private List<String> inputList;

    public ForkJoinUsingRecursion(List<String> inputList) {
        this.inputList = inputList;
    }

    public static void main(String[] args) {

    	CommonUtil.stopWatch.start();
        List<String> resultList = new ArrayList<>();
        List<String> names = DataSet.namesList();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion = new ForkJoinUsingRecursion(names);
        resultList = forkJoinPool.invoke(forkJoinUsingRecursion);

        CommonUtil.stopWatch.stop();
        LoggerUtil.log("Final Result : "+ resultList);
        LoggerUtil.log("Total Time Taken : "+ CommonUtil.stopWatch.getTime());
    }


    private static String addNameLengthTransform(String name) {
    	CommonUtil.delay(500);
        return name.length()+" - "+name ;
    }

    @Override
    protected List<String> compute() {

        if(inputList.size()<=1){
           List<String> resultList = new ArrayList<>();
           inputList.forEach(name-> resultList.add(addNameLengthTransform(name)));
           return resultList;
        }
        int midpoint = inputList.size()/2;
        ForkJoinTask<List<String>> leftInputList=  new ForkJoinUsingRecursion(inputList.subList(0,midpoint)).fork();
        inputList = inputList.subList(midpoint, inputList.size());
        List<String> rightResult = compute(); //recursion happens
        List<String> leftResult = leftInputList.join();
        leftResult.addAll(rightResult);
        return leftResult;
    }
}