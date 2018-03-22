package lintcode.StackSome;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/10
 * @Description  在一个二维01矩阵中找到全为1的最大正方形
 *                思路：最小正方形就是这点的值是1，次小的正方形坐标是：
 *                (i-1,j-1)	(i-1,j)
 *                (i,j-1)	(i,j)
 *                这里，考虑的是右下点是1的时候，再考虑其他三个点的情况，当其他三个点也都是1的时候，当前点(i,j)的值加一，
 *                表示形成的正方形边长是 A(i,j) + 1。若上面的小正方形是另外一个大正方形的一部分，在判断大正方形的（i,j）点的时候，
 *                只需考虑其他三个点是否是零，非零表示可以构成正方形，同时选取这三个点所在值得最小值+ 1 做该大正方形的边长
 */
public class MaxSquare {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,0,1,0,0},
                {1,0,1,1,1},
                {1,1,1,1,1},
                {1,0,0,1,0}
        };
        System.out.println(maxSquare(matrix));  //print 4
        int[][] matrix2 = new int[][] {
                {0,1,1,1,1,1,1,1,1,1},
                {1,0,1,1,1,1,1,1,1,1},
                {1,1,0,1,1,1,1,1,1,1},
                {1,1,1,0,1,1,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,1,1,1,1,0,1,1,1,1},
                {1,1,1,1,1,1,0,1,1,1},
                {1,1,1,1,1,1,1,0,1,1},
                {1,1,1,1,1,1,1,1,0,1},
                {1,1,1,1,1,1,1,1,1,0}
        };
        System.out.println(maxSquare(matrix2)); //print 25
    }

    public static int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];// dp[i][j]表示此点对应的最大正方形边长 只有当前(i, j)位置为1，dp[i][j]才有可能大于0，否则dp[i][j]一定为0。
        // 当(i, j)位置为1，此时要看dp[i-1][j-1], dp[i][j-1]，和dp[i-1][j]这三个位置，我们找其中最小的值，并加上1，就是dp[i][j]的当前值了
        int maxSide = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }
        return maxSide * maxSide;
    }
}
