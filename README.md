# PO CodeChallenge for 09/07/22

## 1) Largest gap in an array:
Given an unsorted array of length N, and we have to find the largest gap between
any two elements of the array. In simple words, find max(|Ai-Aj|)
where 1 ≤ i ≤ N and 1 ≤ j ≤ N.

```
Examples:

Input : arr = {3, 10, 6, 7}
Output : 7
Explanation :
Here, we can see largest gap can be
found between 3 and 10 which is 7
Input : arr = {-3, -1, 6, 7, 0}
Output : 10
Explanation :
Here, we can see largest gap can be 
found between -3 and 7 which is 10 
```

## 2) Maximum Sum SubArray using Divide and Conquer:

Given an array arr[] of integers, the task is to find the maximum sum sub-array
among all the possible sub-arrays.
Examples: 
```
Input: arr[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4} 
Output: 6 
{4, -1, 2, 1} is the required sub-array.
Input: arr[] = {2, 2, -2} 
Output: 4 
```

## Solution

There are 2 utility classes LargestArrayGap and MaximumSumSubArray. In both
cases, you create an object by passing the array in question to the constructor
and then calling the other method to compute the item in question.

In LargestArrayGap the other method is named findLargestGap and in
MaximumSumSubArray the other method is named getMaxSumSubArray.

