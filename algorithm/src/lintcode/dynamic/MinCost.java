package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/7/17
 * @Description 这里有n个房子在一列直线上，现在我们需要给房屋染色，分别有红色蓝色和绿色等k种颜色。每个房屋染不同的颜色费用也不同，
 *              你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小。
 *              费用通过一个nxk 的矩阵给出，比如cost[0][0]表示房屋0染红色的费用，cost[1][2]表示房屋1染绿色的费用。
 */
public class MinCost {
    // costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
    // 房屋 0 蓝色, 房屋 1 绿色, 房屋 2 蓝色， 2 + 5 + 3 = 10

    public static void main(String[] args) {
        int[][] costs = new int[][]{{14, 2, 11},{11, 14, 5},{14, 3, 10}};
        //[[1,2,3],[1,4,6]]
        int[][] costs2 = new int[][]{{1, 2, 3}, {1, 4, 6}};
        System.out.println(minCost(costs2));
    }

    // time limit exceed
    public static int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int preMin = 0;
        int preSecond = 0;
        int preIndex = -1;
        int n = costs.length;
        int colors = costs[0].length;
        for (int i = 0; i < n; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecond = Integer.MAX_VALUE;
            int curIndex = 0;
            for (int j = 0; j < colors; j++) {
                if (j != preIndex) {
                    costs[i][j] = costs[i][j] + preMin;
                } else {
                    costs[i][j] = costs[i][j] + preSecond;
                }
                if (curMin > costs[i][j]) {
                    curSecond = curMin;
                    curMin = costs[i][j];
                    curIndex = j;
                } else {
                    curSecond = Math.min(curSecond, costs[i][j]);
                }
//                for (int k = 0; k < colors; k++) {
//                    找出res[i - 1][k]中k不是j的最小值
//                    需要找出数组中的最小值，以及除了最小值的第二小值
//                    how? 通过上一层的循环赋值，引入变量存储每次的最小值，第二最小值，以及最小值的颜色index
//                }
            }
            preIndex = curIndex;
            preMin = curMin;
            preSecond = curSecond;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < colors; i++) {
            result = Math.min(result, costs[n - 1][i]);
        }
        return result;
    }
}
