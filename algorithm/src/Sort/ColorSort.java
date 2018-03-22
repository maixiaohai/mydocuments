package Sort;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/21
 * @Description 给定一个包含红，白，蓝且长度为 n 的数组，将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。
 *              我们可以使用整数 0，1 和 2 分别代表红，白，蓝
 */
public class ColorSort {
    public static void main(String[] args) {
        int[] nums = new int[]{0,2,2,2,2,1,0,1,0,0,0,1,0,2,0};
        sortColors(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    // red指针标记当前0应该被置换的位置，blue指针标记当前2应该被置换的位置
    // 整个逻辑从左边开始处理，一直到右边
    public static void sortColors(int[] nums) {
        int red = 0;
        int blue = nums.length - 1;
        for (int i = 0; i <= blue;) {
            System.out.println("red : " + red + " blue : " + blue + " i : " + i);
            if (nums[i] == 0 && i >= red) {//>=必须 =的意义是，当此位为0时，red++，i++
                exch(nums, i++, red++);
            } else if (nums[i] == 2 && i < blue) {
                exch(nums, i, blue--);
            } else {
                i++;
            }
        }
    }

    private static void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
