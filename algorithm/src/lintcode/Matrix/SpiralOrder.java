package lintcode.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/11
 * @Description  给定一个包含 m x n 个要素的矩阵，（m 行, n 列），按照螺旋顺序，返回该矩阵中的所有要素。
 */
public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> list = spiralOrder(matrix);
        for (Integer i : list) {
            System.out.print(i + ",");  // print 1,2,3,6,9,8,7,4,5
        }
        System.out.println();
        int[][] matrix2 = new int[][]{{1}, {2}, {3}, {4}, {5}};
        List<Integer> list2 = spiralOrder(matrix2);
        for (Integer i : list2) {
            System.out.print(i + ",");  // print 1,2,3,4,5
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) {
            return list;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int startRow = 0;
        int startCol = 0;
        int endRow = row - 1;
        int endCol = col - 1;
        while (startRow <= endRow && startCol <= endCol) {
            // left to right
            for (int j = startCol; j <= endCol; ++j) {
                list.add(matrix[startRow][j]);
            }
            // bottom to top
            for (int i = startRow + 1; i <= endRow; ++i) {
                list.add(matrix[i][endCol]);
            }
            // right to left
            if (startRow != endRow) {
                for (int j = endCol - 1; j >= startCol; j--) {
                    list.add(matrix[endRow][j]);
                }
            }
            // top to bottom
            if (startCol != endCol) {
                for (int i = endRow - 1; i >= startRow + 1; i--) {
                    list.add(matrix[i][startCol]);
                }
            }
            startRow++;
            startCol++;
            endRow--;
            endCol--;
        }
        return list;
    }
}
