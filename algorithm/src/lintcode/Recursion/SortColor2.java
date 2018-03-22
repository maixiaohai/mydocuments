package lintcode.Recursion;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/26
 * @Description 给定一个有n个对象（包括k种不同的颜色，并按照1到k进行编号）的数组，
 *              将对象进行分类使相同颜色的对象相邻，并按照1,2，...k的顺序进行排序。
 */
public class SortColor2 {
    public static void main(String[] args) {
        //给出colors=[3, 2, 2, 1, 4]，k=4, 你的代码应该在原地操作使得数组变成[1, 2, 2, 3, 4]
        int[] colors = new int[]{3, 2, 2, 1, 4};
        int k = 4;
        sortColors2(colors, k);
        for (int i = 0; i < colors.length; i++) {
            System.out.println(colors[i]);
        }
    }

    public static void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0 || colors.length == 1 || k == 1) {
            return;
        }
        sort(colors, 1, k, 0, colors.length - 1);
    }

    private static void sort(int[] nums, int min, int max, int start, int end) {
        if (min >= max) {
            return;
        }
        int left = start;
        int right = end;
        //System.out.println("min " + min + "max " + max);
        //System.out.println("start :" + start + "end " + end);
        for (int i = start; i <= end;) {
            if (nums[i] == min) {
                exch(nums, i++, left++);
            }   else if (nums[i] == max && i <= right) {
                exch(nums, i, right--);
            }  else {
                i++;
            }
        }
        sort(nums, min + 1, max - 1, left, right);
    }

    private static void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
