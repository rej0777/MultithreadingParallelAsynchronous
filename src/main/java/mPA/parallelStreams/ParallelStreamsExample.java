package mPA.parallelStreams;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mPA.utils.CommonUtil;
import mPA.utils.DataSet;
import mPA.utils.LoggerUtil;

public class ParallelStreamsExample {

	
	public List<String> stringTransform(List<String> nameList){
		return nameList
				.parallelStream()
				.map(this::addNameLengthTransform)
				.collect(Collectors.toList());
	}
	
	public List<String> stringTransform_1(List<String> nameList, Boolean isParallel){
		
		Stream<String> namesStream = nameList.stream();
		
		if (isParallel) 
			namesStream.parallel();
		
		return namesStream
				.map(this::addNameLengthTransform)
				.collect(Collectors.toList());
	}
	
	
	public List<String> stringTransformParallel(List<String> nameList){
		return nameList
				.stream()
				.map(this::addNameLengthTransform)
				.parallel()
				.collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		
		List<String> nameList = DataSet.namesList();		
		ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
		CommonUtil.startTimer();
		List<String> resultList = parallelStreamsExample.stringTransform(nameList);
		LoggerUtil.log("Results : " + resultList);
		CommonUtil.timeTaken();
		
	}
	
	
    private  String addNameLengthTransform(String name) {
    	CommonUtil.delay(500);
        return name.length()+" - "+name ;
    }
}
