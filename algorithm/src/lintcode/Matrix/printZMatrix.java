package lintcode.Matrix;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/27
 * @Description
 */
public class printZMatrix {
    public static void main(String[] args) {
        //[[1],[2],[3],[4],[5],[6],[9],[8],[7]]
        int[][] matrix = new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {9}, {8}, {7}};
        int[] res = printZMatrix(matrix);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public static int[] printZMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 1){
            return matrix[0];
        }
        if (n == 1){
            int [] ree = new int [m];
            for(int k = 0; k < m; k++){
                ree[k] = matrix[k][0];
            }
        }
        int count = m * n;
        int[] res = new int[count];
        int i = 0;
        int j = 0;
        res[0] = matrix[i][j];
        int k = 1;
        while(k < count){
            //斜向上遍历
            while(i > 0 && j+1 < n){
                res[k++] = matrix[--i][++j];
            }
            //斜向上结束，试着往右或者下移动
            if(j+1 < n){
                res[k++] = matrix[i][++j];
            }else{
                if(i+1 <m){
                    res[k++] = matrix[++i][j];
                }
            }
            //斜向下遍历
            while(i+1 < m && j > 0){
                res[k++] = matrix[++i][--j];
            }
            //斜向下遍历结束，试着往下或者往右移动
            if(i+1 < m){
                res[k++] = matrix[++i][j];
            }else{
                if(j + 1 < n){
                    res[k++] = matrix[i][++j];
                }
            }

        }
        return res;
    }
}
