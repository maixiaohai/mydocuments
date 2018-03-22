package lintcode.Array;

import java.util.HashMap;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/29
 * @Description 给定 n 和 k，求123..n组成的排列中的第 k 个排列
 *              challenge: O(n*k) in time complexity is easy, can you do it in O(n^2) or less
 */
public class Permutation {
    public static void main(String[] args) {
        int n = 3;
        int k = 4;
        System.out.println(getPermutation(n, k)); // print 231
        System.out.println(getPermutation(1, 1)); //print 1
    }

    public static String getPermutation(int n, int k) {
        int factorial = 1;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
            factorial *= (i + 1);
        }
        StringBuilder res = new StringBuilder();
        // change K from (1,n) to (0, n-1) to accord to index
        k--;
        for (int i = 0; i < n; i++) {
            factorial = factorial / (n - i);
            int choosed = k / factorial;
            res.append(nums[choosed]);
            System.out.println("choosed : " + choosed + " k : " + k + " factorial : " + factorial);
            for (int j = choosed; j < n - i - 1; j++) {
                nums[j] = nums[j + 1];
            }
            k = k % factorial;
            System.out.println("new k : " + k);
        }
        return res.toString();
    }
}
