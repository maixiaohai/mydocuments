package lintcode.DFSANDBFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/18
 * @Description 根据n皇后问题，现在返回n皇后不同的解决方案的数量而不是具体的放置布局
 *              小米面试真题
 */
public class TotalNQueens {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(totalNQueens(n));
    }

    private static int sum = 0;
    public static int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        search(new ArrayList<Integer>(), n);
        return sum;
    }

    private static void search(List<Integer> list, int n) {
        if (list.size() == n) {
            sum++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValid(list, i)) {
                continue;
            }
            list.add(i);
            search(list, n);
            list.remove(list.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> list, int col) {
        //list存储了每一行Q所在的列
        int row = list.size();
        for (int i = 0 ; i < row; i++) {
            if (list.get(i) == col) {
                return false;
            }
            //不能在同一列，且不能为对象线上的元素
            if (Math.abs(i - row) == Math.abs(list.get(i) - col)) {
                return false;
            }
        }
        return true;
    }
}
