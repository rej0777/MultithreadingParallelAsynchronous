package mPA.parallelStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mPA.utils.CommonUtil;

public class ArrayListSpliteratorExample {

	
	public List<Integer> multiplyEachValue(ArrayList<Integer> inputList, int multiplyValue, Boolean isParalel){
		
		CommonUtil.startTimer();
		Stream<Integer> integerStream = inputList.stream();
		
		if(isParalel)
			integerStream.parallel();
		
		
		List<Integer> resultList = integerStream
				.map(t -> t * multiplyValue )
				.collect(Collectors.toList());
		
		CommonUtil.timeTaken();
		return resultList;
	}
	
}
