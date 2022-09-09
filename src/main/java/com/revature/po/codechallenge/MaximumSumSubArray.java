package com.revature.po.codechallenge;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;


/**
 * This class represents a sub-array of a given array. It implements the
 * Comparable interface so that the sub-arrays can be kept in a tree set where
 * the tree set keep the sub-arrays in order. This only ever intended to be used
 * with disjoint sub-arrays and both the compareTo and merge methods will lead
 * to nonsense results if applied to overlapping sub-arrays.
 * 
 * @author michael
 */
class SubArray implements Comparable<SubArray>{
	
	final private int start;
	final private int end;
	final private int[] arr;
	final private int sum;
	
	SubArray(int[] A, int start, int end) {
		this.arr = A;
		this.start = start;
		this.end = end;
		
		this.sum = IntStream.range(start, end + 1)
				            .map(i->A[i])
				            .reduce(0,Integer::sum);
	}
	
	private SubArray(int[] A, int start, int end, int sum) {
		this.arr = A;
		this.start = start;
		this.end = end;
		
		this.sum = sum;
	}

	static int sumComparator(SubArray a, SubArray b) {
		return a.sum - b.sum;
	}
	
	@Override
	public int compareTo(SubArray o) {
		return  this.start - o.start;
	}
	
	/** 
	 * This only works if the subarays are disjoint.
	 * 
	 * @param b
	 * @return
	 */
	SubArray merge(SubArray b) {
		int newStart = Math.min(this.start, b.start);
		int newEnd = Math.max(this.end, b.end);
		int mergedSum = this.sum + b.sum + gapSum(b);
		
		return new SubArray(this.arr, newStart, newEnd, mergedSum);
	}
	
	public int gapSum(SubArray b) {
		int gapStart = Math.min(this.end, b.end) + 1;
		int gapEnd = Math.max(this.start, b.start) -1;
		
		return IntStream.range(gapStart, gapEnd + 1)
				        .map(i->this.arr[i])
				        .reduce(0, Integer::sum);
	}
	
	int[] toArray() {
		return Arrays.copyOfRange(arr, start, end + 1);
	}

	int getSum() {
		return this.sum;
	}

	@Override
	public String toString() {
		return "SubArray [start=" + start + ", end=" + end + ", sum=" + sum + "]";
	}
}


public class MaximumSumSubArray {
	final private int[] arr;
	
	MaximumSumSubArray(int[] arr) {
		this.arr = arr;
	}
	
	
	/**
	 * Find the maximum sum sub-arrays via divide and conquer.
	 * 
	 * @return a list of all sub-arrays with the maximal sum.
	 */
	public int getMaxSumSubArray() {
		
		// Phase 1: From the given array, construct a set of sub-arrays
		// consisting of only non-negative elements.
		Set<SubArray> subs = new TreeSet<>();
		
		int curStart = -1;
		
		for ( int i = 0 ; i < arr.length ; ++i ) {
			if ( arr[i] >= 0 ) {
				if ( curStart == -1 ) {
					curStart = i;
				}
				else {
					// We're continuing a span of nonnegative nos - no action
					// is needed.
				}
			}
			else {
				if ( curStart == -1 ) {
					// We're continuing a span of negative nos - no action
					// is needed.
				}
				else {
					// We're just finished looping over a sub-array of 
					// nonnegative ints - add that sub to subs.
					subs.add(new SubArray(arr, curStart, i - 1));
					curStart = -1;
				}
			}
		}
		
		// If we were in the middle of a set of nonnegative nos when the
		// array ended, we need to add that last sub-array to subs now.
		if ( curStart != -1 ) {
			subs.add(new SubArray(arr, curStart, arr.length - 1));
		}
		
		// Before the conquer phase, there are 2 special cases that must
		// be handled separately:
		// 1) subs has 0 members - in this case, there were only negative
		//    numbers in the array. The maximum sum sub-arrays will just be the
		//    maximum members.
		// 2) subs has only 1 member. In this case, the maximum sum sub-array
		//    will just be that 1 member.
		
		if ( subs.size() <= 1 ) {
			int max = -1;
			
			if ( subs.size() == 0 ) {
				max = Arrays.stream(arr).max().getAsInt();
			}
			else {
				// There is only one. Get it and put it in maxs.
				max = subs.stream().findFirst().get().getSum();
			}
			
			return max;
		}
		
		// If neither of the previous cases applied, now proceed to the conquer
		// phase. In this phase, we'll merge 2 adjacent sub-arrays together if
		// the sub obtained by merging them out weighs the total of the negative
		// numbers between them. Repeat the process until no additional
		// merging is possible. 
		// In addition, since we will know the sum at each merge operation, we
		// might as well track the maximum sum now. 
		boolean merged = true;
		SubArray s = subs.stream().max(SubArray::sumComparator).get();
		int curMaxSum = s.getSum();
		
		while ( merged ) {
			merged = false; 
			
			Iterator<SubArray> itr = subs.iterator(); 
			SubArray prev = itr.next();
			SubArray cur = null;
			int curGapSum = 0;
			
			while ( itr.hasNext() ) {
				cur = itr.next();
				
				int min = Math.min(cur.getSum(), prev.getSum());
				curGapSum= prev.gapSum(cur);
				
				if ( min + curGapSum >= 0 ) {
					merged = true;
					break;
				}
				
				prev = cur;
			}
			
			if ( merged ) {
				SubArray mergedSub = prev.merge(cur);
				
				if ( mergedSub.getSum() >= curMaxSum ) {
					curMaxSum = mergedSub.getSum();
				}
				
				subs.remove(prev);
				subs.remove(cur);
				subs.add(mergedSub);
				
				if ( subs.size() == 1 ) {
					merged = false;
				}
			}
		}
	

		// All merging is complete and the maximal sum is known. 
		return curMaxSum;
	}
}

