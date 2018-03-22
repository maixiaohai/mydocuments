package lintcode.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/29
 * @Description  给出一组候选数字(C)和目标数字(T),找到C中所有的组合，使找出的数字和为T。C中的数字只能使用一次。
 *              例如,给出候选数组[10,1,6,7,2,1,5]和目标数字8，所求的解为：[[1,7],[1,2,5],[2,6],[1,1,6]]
 *      注意事项  (1)所有的数字(包括目标数字)均为正整数。
 *               (2)元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
 *               (3)解集不能包含重复的组合。
 */
public class Combination2 {
    public static void main(String[] args) {
        int[] nums = new int[]{10,1,6,7,2,1,5};
        int target = 8;
        List<List<Integer>> res = combinationSum2(nums, target);
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> combinationSum2(int[] num, int target) {
        if (num == null || num.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        combinationSum2(num, target, 0, num.length - 1, res, tmp);
        return res;
    }

    private static void combinationSum2(int[] num, int target, int begin, int end, List<List<Integer>> res,
                                        ArrayList<Integer> tmp) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            List<Integer> tmpItem = (List<Integer>) tmp.clone();
            Collections.sort(tmpItem);
            if (!res.contains(tmpItem)) {
                res.add(tmpItem);
            }
            return;
        }
        for (int i = begin; i <= end; i++) {
            tmp.add(num[i]);
            target -= num[i];
            combinationSum2(num, target, i + 1, end, res, tmp);
            tmp.remove(tmp.size() - 1);
            target += num[i];
        }
    }
}
