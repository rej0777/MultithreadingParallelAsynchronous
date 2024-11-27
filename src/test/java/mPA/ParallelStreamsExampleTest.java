package mPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import mPA.parallelStreams.ParallelStreamsExample;
import mPA.utils.DataSet;

class ParallelStreamsExampleTest {

	ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();
	
	@Test
	void stringTransform() {
		
		List<String> inputList = DataSet.namesList();
		List<String> resultList = parallelStreamsExample.stringTransform(inputList);
		
		assertEquals(4, resultList.size());
		
		resultList.forEach(name ->{
			assertTrue(name.contains("-"));					
		});
	}

}
