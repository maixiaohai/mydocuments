package lintcode.Array;

import java.util.Arrays;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/29
 * @Description  给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。
 *               思路：左右两个指针，一个代表正数，一个代表负数，根据正负数的个数作不同策略(前提，排序数组)
 */
public class RerangeArray {
    public static void main(String[] args) {
        //给出数组[-1, -2, -3, 4, 5, 6]，重新排序之后，变成[-1, 5, -2, 4, -3, 6]
        int[] input = new int[]{-1, -2, -3, 4, 5, 6};
        rerange(input);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
        System.out.println();
        input = new int[]{-33,-19,30,26,21,-9, -8};
        rerange(input);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }

    public static void rerange(int[] A) {
        if (A == null || A.length <= 2) {
            return;
        }
        int negNum = 0;
        int posNum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                posNum++;
            } else {
                negNum++;
            }
        }
        Arrays.sort(A);//前提
        if (posNum > negNum) { //正数多
            for (int i = 0, j = A.length - 2; i < j; i += 2, j -= 2) {
                exch(A, i, j);
            }
        } else if (posNum < negNum) { //负数多
            for (int i = 1, j = A.length - 1; i < j; i += 2, j -= 2) {
                exch(A, i , j);
            }
        } else { //个数相同
            for (int i = 0, j = A.length - 1; i < j; i += 2, j -= 2) {
                exch(A, i , j);
            }
        }
    }

    private static void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
