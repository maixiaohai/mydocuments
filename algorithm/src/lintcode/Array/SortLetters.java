package lintcode.Array;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/28
 * @Description  给定一个只包含字母的字符串，按照先小写字母后大写字母的顺序进行排序。
 */
public class SortLetters {
    public static void main(String[] args) {
        //给出"abAcD"，一个可能的答案为"acbAD"
        String str = "abAcD";
        char[] input = str.toCharArray();
        sortLetters(input);
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
        }
    }

    public static void sortLetters(char[] chars) {
        if (chars == null || chars.length == 0 || chars.length == 1) {
            return;
        }
        int left = 0;
        int right = chars.length - 1;
        for (int i = 0; i < chars.length;) {
            if (chars[i] > 'Z' && i >= left) {
                exch(chars, i++, left++);
            } else if (chars[i] <= 'Z' && i <= right) {
                exch(chars, i, right--);
            } else {
                i++;
            }
        }
    }

    private static void exch(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
