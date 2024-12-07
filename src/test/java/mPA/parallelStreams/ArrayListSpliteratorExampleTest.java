package mPA.parallelStreams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import mPA.utils.DataSet;

class ArrayListSpliteratorExampleTest {

	ArrayListSpliteratorExample arrayListSpliteratorExample = new ArrayListSpliteratorExample();

	@Test
	void arrayListSpliteratorExample() throws Exception {

		int size = 1000000;
		
		ArrayList<Integer> input = DataSet.generateArrayList(size);
		
		List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(input, size,false);
		assertEquals(size, resultList.size());
	}

	
	
	@RepeatedTest(5)
	void multiplyEachValue() throws Exception {

		int size = 1000000;		
		ArrayList<Integer> input = DataSet.generateArrayList(size);		
		List<Integer> resultList = arrayListSpliteratorExample.multiplyEachValue(input, size, true);
		assertEquals(size, resultList.size());
	}
}