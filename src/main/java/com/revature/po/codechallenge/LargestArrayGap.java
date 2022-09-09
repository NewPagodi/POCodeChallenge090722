package com.revature.po.codechallenge;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

public class LargestArrayGap {
	private final int[] arr;
	
	LargestArrayGap(int[] arr) {
		this.arr = arr;
	}
	
	public int findLargestGap() {
		IntSummaryStatistics stats2 = Arrays.stream(arr)
				.boxed().collect(Collectors.summarizingInt(Integer::intValue));
		
		int min = stats2.getMin();
		int max = stats2.getMax();
		
		return max-min;
	}
}
