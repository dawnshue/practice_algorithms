// https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
/*
Given an array containing only 0s and 1s
Find the largest subarray which contain equal no of 0s and 1s
Expected time complexity is O(n)
*/

public class Solution {
    public static void main(String args[]) {
        Solution.getLongestZeroSum({0, 0, 1, 0, 1, 1, 0});
    }

    /*
    Optimal solution: Time = n = O(n), Space = n + n = O(n)
    Create a sumLeft array
    Create a hashmap of earliest occurrence of sumLeft values
    Can do the first two steps at the same time?
    Either find last occurrence of 0 sum or largest subarray where sumLeft[a] = sumLeft[b]
    */
    public static int getLongestZeroSum(int[] input) {
        if(input.length == 0)
        return 0;
        
        int[] sumLeft = new int[input.length];
        sumLeft[0] = input[0] == 0 ? -1 : 1;
        for(int i = 1; i < input.length; i++) {
            sumLeft[i] = sumLeft[i - 1] + (input[i] == 0 ? -1 : 1);
        }
        
        int longest = -1;
        Map<Integer, Integer> sumToPos = new HashMap<>();
        for(int i = 0; i < sumLeft.length; i++) {
            if(sumLeft[i] == 0) {
            longest = i;
            continue;
            }
            
            Integer index = sumToPos.get(sumLeft[i]);
            if(index == null) {
                sumToPoss.put(sumLeft[i], i);
            } else {
                if ((i - index) > longest)
                longest = i - index;
            }
        }
        return longest;
    }
}