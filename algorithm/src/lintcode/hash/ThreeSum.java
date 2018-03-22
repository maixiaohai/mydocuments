package lintcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/23
 * @Description 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
 *              注意事项: 在三元组(a, b, c)，要求a <= b <= c。结果不能包含重复的三元组。
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] numbers = new int[]{-1, 0, 1, 2, -1, -4};
        // (-1, 0, 1) (-1, -1, 2)
        ArrayList<ArrayList<Integer>> res = threeSum(numbers);
        for (ArrayList<Integer> list : res) {
            for (Integer x : list) {
                System.out.println(x);
            }
            System.out.println("============");
        }
    }

    public static ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (numbers == null) {
            return res;
        }
        Arrays.sort(numbers);//排序方便使用指针
        for (int i = 0; i < numbers.length - 2; i++) {
            int left = i + 1;
            int right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(numbers[i]);
                    list.add(numbers[left]);
                    list.add(numbers[right]);
                    if (!res.contains(list)) { //重复性
                        res.add(list);
                    }
                    left++;
                    right--;
                }
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

}

