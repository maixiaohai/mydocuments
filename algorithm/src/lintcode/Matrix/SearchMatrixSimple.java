package lintcode.Matrix;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/18
 * @Description
 */
public class SearchMatrixSimple {
    //[[1,   3,  5,  7], [10, 11, 16, 20],[23, 30, 34, 50]]
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int target = 3;
        if (searchMatrix(matrix, target)) {
            System.out.println("exist : " + target);
        } else {
            System.out.println("not exist : " + target);
        }
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        System.out.println(m +" "+n);
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }
        //首先确定所在行
        int lo = 0;
        int hi = m - 1;
        int mid = 0;
        while (lo < hi - 1) {
            mid = (hi -lo) / 2 + lo;
            if (matrix[0][mid] == target) {
                return true;
            } else if (matrix[0][mid] > target) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        int row = (matrix[hi][0] <= target ? hi : lo);
        lo = 0;
        hi = n - 1;
        mid = 0;
        while (lo < hi - 1) {
            System.out.println("lo : " + lo + " hi : " + hi);
            mid = lo + (hi - lo) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] > target) {
                hi = mid;
            } else {
                lo = mid;
            }
            System.out.println("lo : " + lo + " hi : " + hi);
        }
        if (matrix[row][lo] == target || matrix[row][hi] == target) {
            return true;
        } else {
            return false;
        }
    }
}
