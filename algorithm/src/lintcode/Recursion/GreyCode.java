package lintcode.Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/25
 * @Description   格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个二进制的差异。
 *                给定一个非负整数 n ，表示该代码中所有二进制的总数，请找出其格雷编码顺序。
 *                一个格雷编码顺序必须以 0 开始，并覆盖所有的 2n 个整数。
 *                思路：考察二级制的操作，首先先找出格雷码的规律：Gray[i] = Gray[i-1]^B[i].
 */
public class GreyCode {
    //给定 n = 2， 返回 [0,1,3,2] => 00>00,01>01,10>11,11>10
    public static void main(String[] args) {
        int n = 2;
        for (Integer i : grayCode(n)) {
            System.out.print(i + " ");
        }
        System.out.println();
        n = 3;
        for (Integer i : grayCode(n)) {
            System.out.print(i + " ");
        }
    }

    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        int size = 1 << n;
        for (int i = 0; i < size; i++) {
            res.add(i ^ i >> 1);
        }
        return res;
    }
}
