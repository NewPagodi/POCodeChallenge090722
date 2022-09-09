package com.revature.po.codechallenge;


public class Main {

	public static void main(String[] args) {
		//int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4} ;
		//int[] arr = {2, 2, -2};
		//int[] arr = {-2, -2, -1, -2, -2, -2, -2, -2, -2, -2, -2, -2};
		//int[] arr = {3, 10, 6, 7};
		int[] arr = {-3, -1, 6, 7, 0};
		
		LargestArrayGap lag = new LargestArrayGap(arr);
		System.out.println(lag.findLargestGap());
		
		MaximumSumSubArray mssa = new MaximumSumSubArray(arr);
		System.out.println(mssa.getMaxSumSubArray());
	}

}
