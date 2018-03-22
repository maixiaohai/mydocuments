package lintcode.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/29
 * @Description 给出一组候选数字(C)和目标数字(T),找到C中所有的组合，使找出的数字和为T。C中的数字可以无限制重复被选取。
 *              例如,给出候选数组[2,3,6,7]和目标数字7，所求的解为：[7]，[2,2,3]
 *      注意事项  (1)所有的数字(包括目标数字)均为正整数。
 *               (2)元素组合(a1, a2, … , ak)必须是非降序(ie, a1 ≤ a2 ≤ … ≤ ak)。
 *               (3)解集不能包含重复的组合。
 */
public class Combination {
    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res = combinationSum(candidates, target);
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }
    }

    // 主要有两点，一点是tmp这个数组每次加入到结果集的时候，需要现copy到另一个新建List，然后排序再add
    // 第二点是循环中，改变的tmp和target需要在递归调用后再修改回来
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0 || target == 0) {
            return res;
        }
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        combinationSum(candidates, target, res, tmp);
        return res;
    }

    private static void combinationSum(int[] candidates, int target, List<List<Integer>> res, ArrayList<Integer> tmp) {
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
        for (int i = 0; i < candidates.length; i++) {
            target -= candidates[i];
            tmp.add(candidates[i]);
            combinationSum(candidates, target, res, tmp);
            target += candidates[i];
            //tmp.remove(candidates[i]); remove删除index
            tmp.remove(tmp.size() - 1);
        }
    }
}
