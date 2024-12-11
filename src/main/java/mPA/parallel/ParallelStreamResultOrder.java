package mPA.parallel;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import mPA.utils.CommonUtil;
import mPA.utils.LoggerUtil;

public class ParallelStreamResultOrder {

	
	public static List<Integer> listOrder(List<Integer> imputList ){
		return imputList.parallelStream()
				.map(t -> t*2 )
				.collect(Collectors.toList());
	}
	
	public static Set<Integer> setOrder(Set<Integer> imputList ){
		return imputList.parallelStream()
				.map(t -> t*2 )
				.collect(Collectors.toSet());
	}
	
	public static void main(String[] args) {
		
		/*
		 * List<Integer> inputList = List.of(1,2,3,4,5,6,7,8,9);
		 * LoggerUtil.log(inputList.toString());
		 * 
		 * List<Integer> result= listOrder(inputList);
		 * LoggerUtil.log(result.toString());
		 */
		
		Set<Integer> setList = Set.of(1,2,3,4,5,6,7,8,9);
		LoggerUtil.log(setList.toString());
		
		Set<Integer> result= setOrder(setList);
		LoggerUtil.log(result.toString());
	}
	
}
//CommonUtil.startTimer();
//CommonUtil.timeTaken();
//LoggerUtil.log();