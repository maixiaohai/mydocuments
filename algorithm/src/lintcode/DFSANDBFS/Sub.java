package lintcode.DFSANDBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/11/16
 * @Description 给定一个含不同整数的集合，返回其所有的子集。子集中的元素排列必须是非降序的，解集必须不包含重复的子集
 *              类似的还有Permutation（全排列）
 */
public class Sub {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        //[[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
        List<List<Integer>> res = subsets(nums);
        for (List<Integer> list : res) {
            System.out.print("[");
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.print("]");
            System.out.println("");
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        helper(res, list, 0, nums);
        return res;
    }

    private static void helper(List<List<Integer>> res, List<Integer> list, int n, int[] nums) {
        res.add(new ArrayList<Integer>(list)); //new
        if (list.size() == nums.length) {
            return;
        }
        for (int i = n; i < nums.length; ++i) {
            if (list.contains(nums[i]) || (list.size() != 0 && list.get(list.size() - 1) > nums[i])) {
                continue;
            } // most important
            list.add(nums[i]);
            helper(res, list, n + 1, nums);
            list.remove(list.size() - 1);
        }
    }
}
