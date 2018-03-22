package lintcode.Matrix;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/18
 * @Description  写出一个高效的算法来搜索m×n矩阵中的值，返回这个值出现的次数。
 *               这个矩阵具有以下特性：
 *               (1)每行中的整数从左到右是排序的。
 *               (2)每一列的整数从上到下是排序的。
 *               (3)在每一行或每一列中没有重复的整数。
 */
public class SearchMatrix {
    //[ [1, 3, 5, 7], [2, 4, 7, 8], [3, 5, 9, 10] ]
    //input 3 output 2

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {2, 4, 7, 8},
                {3, 5, 9, 10}
        };
        int target = 8;
        System.out.println(searchMatrix(matrix, target));
    }

    public static int searchMatrix(int[][] matrix, int target) {
        int res = 0;
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int row = m - 1;
        int col = 0;
        while (row >= 0 && col <= n - 1) {
            System.out.println("row: " + row + " col: " + col);
            if (matrix[row][col] == target) {
                res++;
                col++;
                row--;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }
        return res;
    }
}
