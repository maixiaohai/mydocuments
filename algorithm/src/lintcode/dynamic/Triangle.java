package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/15
 * @Description 给出一个数字三角形，给出从上到下的最小路径和
 */
public class Triangle {
    public static void main(String[] args) {
        int[][] triangle = new int[][]{{2}, {3, 4},{5, 6, 7}, {8, 9, 10, 11}};
        System.out.println(minimumTotal(triangle));
    }

    public static int minimumTotal(int[][] triangle) {
        // 从底往上，将每行的元素改为加上下一行的元素的两个值中的最小值
        if (triangle == null) {
            return 0;
        }
        if (triangle.length < 2) {
            return triangle[0][0];
        }
        int m = triangle.length;
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                System.out.println("i: " + i + " j: " + j);
                triangle[i][j] = triangle[i][j] + Math.min(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        return triangle[0][0];
    }
}
