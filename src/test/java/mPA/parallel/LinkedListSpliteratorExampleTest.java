package mPA.parallel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import mPA.utils.DataSet;

class LinkedListSpliteratorExampleTest {

	LinkedListSpliteratorExample LinkedListSpliteratorExample = new LinkedListSpliteratorExample();
	
	@Test
	void arrayListSpliteratorExample() throws Exception {

		int size = 1000000;
		
		LinkedList<Integer> input = DataSet.generateIntegerLinkedList(size);
		
		List<Integer> resultList = LinkedListSpliteratorExample.multiplyEachValue(input, size,false);
		assertEquals(size, resultList.size());
	}

	@Test
	void arrayListSpliteratorExample_Parallel() throws Exception {

		int size = 1000000;
		
		LinkedList<Integer> input = DataSet.generateIntegerLinkedList(size);
		
		List<Integer> resultList = LinkedListSpliteratorExample.multiplyEachValue(input, size,true);
		assertEquals(size, resultList.size());
	}
}
