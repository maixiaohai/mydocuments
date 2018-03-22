package lintcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/23
 * @Description 给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。
 *              注意事项：四元组(a, b, c, d)中，需要满足a <= b <= c <= d，答案中不可以包含重复的四元组
 */
public class FourSum {

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
        List<List<Integer>> res = fourSum(numbers, target);
        for (List<Integer> list : res) {
            for (Integer x : list) {
                System.out.println(x);
            }
            System.out.println("============");
        }
    }

    public static List<List<Integer>> fourSum(int[] numbers, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numbers == null || numbers.length < 4) {
            return res;
        }
        Arrays.sort(numbers); //必须排序
        for (int i = 0; i < numbers.length - 3; i++) {
            for (int j = i + 1; j < numbers.length - 2; j++) {
                int left = j + 1;
                int right = numbers.length - 1;
                while (left < right) {
                    int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(numbers[i]);
                        list.add(numbers[j]);
                        list.add(numbers[left]);
                        list.add(numbers[right]);
                        if (!res.contains(list)) {
                            res.add(list);
                        }
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
