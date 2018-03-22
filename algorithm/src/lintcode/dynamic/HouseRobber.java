package lintcode.dynamic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/5
 * @Description 相邻的屋子自能打劫一个
 */
public class HouseRobber {
    public static void main(String[] args) throws Exception {
        int[] input = new int[]{3};
        System.out.println(houseRobber(input));
    }

    public static long houseRobber(int[] A) {
        int length = A.length;
        if (null == A || A.length == 0) {
            return 0;
        }
        long[] res = new long[length];
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                res[i] = A[i];
            } else if (i == 1) {
                res[i] = Math.max(A[0], A[1]);
            } else {
                res[i] = Math.max(res[i - 1], A[i] + res[i - 2]);
            }
        }
        return res[res.length - 1];
    }
}
