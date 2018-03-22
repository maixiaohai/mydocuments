package Sort;

import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/3
 * @Description 两个排序的数组A和B分别含有m和n个数，找到两个排序数组的中位数，要求时间复杂度应为O(log (m+n))。
 *              思路：将题目抽象为“搜索两个有序序列的第k个元素”
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        //给出数组A = [1,2,3,4,5,6] B = [2,3,4,5]，中位数3.5
        //给出数组A = [1,2,3] B = [4,5]，中位数 3
        int[] a = new int[]{1, 2, 3, 4, 5, 6};
        int[] b = new int[]{2, 3, 4, 5};
        System.out.println(findMedianSortedArrays(a, b));
        int[] a1 = new int[]{1, 2, 3};
        int[] b1 = new int[]{4, 5};
        System.out.println(findMedianSortedArrays(a1, b1));
        int[] a2 = new int[]{};
        int[] b2 = new int[]{1};
        System.out.println(findMedianSortedArrays(a2, b2));
    }

    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int k = (m + n) / 2;
        if ((m + n) % 2 == 0) {
            return (findKth(A, B, 0, 0, m, n, k) + findKth(A, B, 0, 0, m, n,k + 1)) / 2;
        } else {
            return findKth(A, B, 0 ,0, m, n, k + 1);
        }
    }

    private static double findKth(int[] arr1, int[] arr2, int start1, int start2, int l1, int l2, int k){
        System.out.println("start1 " + start1 + " start2 " + start2 + " l1 " + l1 + " l2 " + l2 + " k " + k);
        if (l1 > l2) {
            return findKth(arr2, arr1, start2, start1, l2, l1, k);
        }
        if (l1 == 0) {
            return arr2[start2 + k - 1];
        }
        if (k == 1) {
            return Math.min(arr1[start1], arr2[start2]);
        }
        int p1 = Math.min(k / 2, l1);
        int p2 = k - p1;
        if (arr1[start1 + p1 -1] < arr2[start2 + p2 - 1]) {
            return findKth(arr1, arr2, start1 + p1, start2, l1 - p1, l2,k - p1);
        } else if (arr1[start1 + p1 -1] > arr2[start2 + p2 - 1]) {
            return findKth(arr1, arr2, start1, start2 + p2, l1, l2 - p2, k - p2);
        } else {
            //两个数组各走一半得到的数相同，即第kth数就为这个数
            return arr1[start1 + p1 - 1];
        }
    }
}
