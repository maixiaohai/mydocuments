package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/15
 * @Description 给定m*n矩阵，给出从左上角到右下角有多少条不同的路径
 */
public class UniquePath {
    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        System.out.println(uniquePaths(m, n));
    }

    public static int uniquePaths(int m, int n) {
        if (m == 0 && n == 0) {
            return 0;
        }
        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            sum[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            sum[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }
        return sum[m - 1][n - 1];
    }
}
