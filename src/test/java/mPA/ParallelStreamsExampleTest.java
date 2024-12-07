package mPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

import mPA.parallelStreams.ParallelStreamsExample;
import mPA.utils.CommonUtil;
import mPA.utils.DataSet;

class ParallelStreamsExampleTest {

	ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
	
	@Test
	void stringTransform() {
		
		List<String> inputList = DataSet.namesList();
		
		CommonUtil.startTimer();
		List<String> resultList = parallelStreamsExample.stringTransform(inputList);
		CommonUtil.timeTaken();
		
		assertEquals(4, resultList.size());
		
		resultList.forEach(name ->{
			assertTrue(name.contains("-"));					
		});
	}
	
	@ParameterizedTest
	@ValueSource(booleans = {false, true})
	void stringTransform_1(boolean isParallel) {
		
		List<String> inputList = DataSet.namesList();
		
		CommonUtil.startTimer();
		List<String> resultList = parallelStreamsExample.stringTransform_1(inputList, isParallel);
		CommonUtil.timeTaken();		
		assertEquals(4, resultList.size());
		
		resultList.forEach(name ->{
			assertTrue(name.contains("-"));					
		});
	}

}
