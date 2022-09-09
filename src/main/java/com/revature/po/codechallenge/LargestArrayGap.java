package com.revature.po.codechallenge;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

public class LargestArrayGap {
	private final int[] arr;
	
	LargestArrayGap(int[] arr) {
		this.arr = arr;
	}
	
	/**
	 * Find the largest gap in arr. ie max(|Ai-Aj|) where 1 ≤ i ≤ N 
	 * and 1 ≤ j ≤ N. 
	 * 
	 * Note that it is sufficient to find max-min, because of the following
	 * fact:
	 * 
	 * if max = max(A1,...,AN) and min = min(A1,...,AN) then
	 * |Ai-Aj| <= max-min for all i, j where 1 ≤ i ≤ N and 1 ≤ j ≤ N.
	 * 
	 * proof: For i,j in the given range, either Ai<Aj, Ai>Aj, or Ai=Aj. We'll
	 * consider each case in turn.
	 * 
	 * case1: Assume Ai<Aj, then |Ai-Aj| = Aj-Ai. By definintion, Aj <= max
	 * and min <= Ai. So:
	 *       Aj + min <= Ai + max
	 *       Aj + min -Ai <= Ai + max - Ai = max
	 *       Aj - Ai = Aj + min - Ai - min <= max - min
	 *       |Ai-Aj| <= max -min
	 * Similar ressoning applies in shows |Ai-Aj| <= max -min for case 2.
	 * case 3: If Ai = Aj, then |Ai-Aj| = 0. By definition max >= min, so 
	 * max - min >=0.  So |Ai-Aj| = 0 <= max - min as needed.
	 * 
	 * Consequently by the principle of proof by cases 
	 * (https://en.wikipedia.org/wiki/Disjunction_elimination), we can
	 * conclude |Ai-Aj| <= max-min as needed.
	 * 
	 * @return the largest gap in arr.
	 */
	public int findLargestGap() {
		IntSummaryStatistics stats2 = Arrays.stream(arr)
				.boxed().collect(Collectors.summarizingInt(Integer::intValue));
		
		int min = stats2.getMin();
		int max = stats2.getMax();
		
		return max-min;
	}
}
