package lintcode.DFSANDBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/10
 * @Description 给出一个整数数组，返回它的全排列
 */
public class Permutations {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        List<List<Integer>> res = permute(a);
        for (List<Integer> list : res) {
            for (int x : list) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (nums == null) {
            return res;
        }
        Arrays.sort(nums);
        subsetsHelper(res, list, nums);
        return res;
    }

    private static void subsetsHelper(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            //System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));
            //res.add(list); //这样写输出为空
            res.add(new ArrayList<Integer>(list));
        }
        //System.out.println(list.size() + "===");
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            subsetsHelper(res, list, nums);
            //最关键的
            list.remove(list.size() - 1);
        }
    }
}
