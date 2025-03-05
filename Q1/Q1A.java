package Q1;

import java.util.PriorityQueue;

/**
 * This program solves two problems:
 * 1. Finding the minimum number of measurements required to determine the critical temperature.
 * 2. Finding the kth lowest combined return from two sorted arrays of investment returns.
 */
public class Q1A {
    
    /**
     * Determines the minimum number of measurements required to find the critical temperature.
     * Uses dynamic programming where dp[i][j] represents the maximum number of temperature levels
     * we can check with i samples and j attempts.
     *
     * @param k The number of identical samples.
     * @param n The number of temperature levels.
     * @return The minimum number of measurements required.
     */
    public static int minMeasurements(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + 1;
                if (dp[i][j] >= n) {
                    return j;
                }
            }
        }
        return n;
    }
    
    /**
     * Finds the kth lowest combined return by selecting one investment from each array.
     * Uses a min-heap (priority queue) to efficiently find the kth smallest product.
     *
     * @param returns1 The first sorted array of investment returns.
     * @param returns2 The second sorted array of investment returns.
     * @param k The target index for the kth lowest combined return.
     * @return The kth lowest combined return.
     */
    public static int kthLowestCombinedReturn(int[] returns1, int[] returns2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < returns1.length; i++) {
            minHeap.offer(new int[]{returns1[i] * returns2[0], i, 0});
        }
        
        int count = 0;
        while (!minHeap.isEmpty()) {
            int[] element = minHeap.poll();
            int val = element[0], i = element[1], j = element[2];
            count++;
            
            if (count == k) {
                return val;
            }
            
            if (j + 1 < returns2.length) {
                minHeap.offer(new int[]{returns1[i] * returns2[j + 1], i, j + 1});
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        // Test cases for minMeasurements
        System.out.println(minMeasurements(1, 2)); // Output: 2
        System.out.println(minMeasurements(2, 6)); // Output: 3
        System.out.println(minMeasurements(3, 14)); // Output: 4
        
        // Test cases for kthLowestCombinedReturn
        System.out.println(kthLowestCombinedReturn(new int[]{2, 5}, new int[]{3, 4}, 2)); // Output: 8
        System.out.println(kthLowestCombinedReturn(new int[]{-4, -2, 0, 3}, new int[]{2, 4}, 6)); // Output: 0
    }
}
