package lintcode.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/29
 * @Description 组给出两个整数n和k，返回从1......n中选出的k个数的所有组合。
 *              例如 n = 4 且 k = 2 返回的解为：[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
 */
public class Combine {
    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        List<List<Integer>> res = combine(n, k);
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    public static List<List<Integer>> combine(int n, int k) {
        if (n == 0 || k == 0) {
            return null;
        }
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = i + 1;
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        combine(num, k, 0, n - 1, res, tmp);
        return res;
    }

    private static void combine(int[] num, int k, int begin, int end, List<List<Integer>> res, ArrayList<Integer> tmp) {
        if (k == 0) {
            List<Integer> tmpItem = (List<Integer>) tmp.clone();
            res.add(tmpItem);
            return;
        }
        for (int i = begin; i <= end; i++) {
            tmp.add(num[i]);
            k--;
            combine(num, k, i + 1, end, res, tmp);
            k++;
            tmp.remove(tmp.size() - 1);
        }
    }
}
