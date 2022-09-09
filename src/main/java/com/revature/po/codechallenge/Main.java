package com.revature.po.codechallenge;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4} ;
		int[] arr = {2, 2, -2};
		
		MaximumSumSubArray mssa = new MaximumSumSubArray(arr);
		
		List<int[]> subs = mssa.getMaxSumSubArray();
		
		Iterator<int[]> it = subs.iterator();
		while ( it.hasNext() ) {
			System.out.println((Arrays.toString(it.next())));
		}
	}

}
