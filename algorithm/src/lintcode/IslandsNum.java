package lintcode;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/2
 * @Description 输入为二维boolean数组，查看里面有多少个连通变量，连通条件，上下左右。
 */
public class IslandsNum {
    public static void main(String[] args) {
        boolean[][] a = new boolean[][]{{true,true,false},
                {false,true,false},
                {false,false,true}};
        int m = a.length;
        int n = a[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j]) {
                    count++;
                    a = change(a, i, j);
                }
            }
        }
        System.out.println(count);
    }

    private static boolean[][] change(boolean[][] a, int i, int j) {
        a[i][j] = false;
        if (i > 0 && a[i - 1][j]) {
            change(a, i - 1, j);
        }
        if (i < (a.length - 1) && a[i + 1][j]) {
            change(a, i + 1, j);
        }
        if (j > 0 && a[i][j - 1]) {
            change(a, i , j - 1);
        }
        if (j < (a[0].length - 1) && a[i][j + 1]) {
            change(a, i, j + 1);
        }
        return a;
    }
}
