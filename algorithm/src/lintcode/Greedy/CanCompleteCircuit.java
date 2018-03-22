package lintcode.Greedy;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/1
 * @Description  在一条环路上有 N 个加油站，其中第 i 个加油站有汽油gas[i]，并且从第_i_个加油站前往第_i_+1个加油站需要消耗汽油cost[i]。
 *               你有一辆油箱容量无限大的汽车，现在要从某一个加油站出发绕环路一周，一开始油箱为空。
 *               求可环绕环路一周时出发的加油站的编号，若不存在环绕一周的方案，则返回-1。
 */
public class CanCompleteCircuit {
    public static void main(String[] args) {
        //现在有4个加油站，汽油量gas[i]=[1, 1, 3, 1]，
        // 环路旅行时消耗的汽油量cost[i]=[2, 2, 1, 1]。则出发的加油站的编号为2。
        int[] gas = new int[]{1, 1, 3, 1};
        int[] cost = new int[]{2, 2, 1, 1};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int toalCost = 0;
        int cur = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            cur += (gas[i] - cost[i]);
            totalGas += gas[i];
            toalCost += cost[i];
            if (cur < 0) {
                start = i + 1;
                cur = 0;
            }
        }
        if (toalCost > totalGas) {
            return -1;
        }
        return start;
    }
}
