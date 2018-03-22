package lintcode.DFSANDBFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/17
 * @Description  n皇后问题是将n个皇后放置在n*n的棋盘上，皇后彼此之间不能相互攻击。
 *               给定一个整数n，返回所有不同的n皇后问题的解决方案。
 *               每个解决方案包含一个明确的n皇后放置布局，其中“Q”和“.”分别表示一个女王和一个空位置。
 *               思路：深度优先搜索
 *               小米面试真题(8皇后问题，有多少解)复杂版
 */
public class SolveNQueens {
    public static void main(String[] args) {
        List<List<String>> res = solveNQueens(4);
        for (List<String> list : res) {
            for (String str : list) {
                System.out.println(str);
            }
            System.out.println();
        }
    }
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (n <= 0) {
            return res;
        }
        search(res, new ArrayList<Integer>(), n);
        return res;
    }

    private static void search(List<List<String>> res, List<Integer> list, int n) {
        if (list.size() == n) {
            List<String> resList = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (list.get(i) == j) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                resList.add(sb.toString());
            }
            res.add(resList);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValid(list, i)) {
                continue;
            }
            list.add(i);
            search(res, list, n);
            list.remove(list.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> list, int col) {
        int row = list.size();
        for (int i = 0; i < row; i++) {
            if (list.get(i) == col) {
                return false;
            }
            if (Math.abs(col - list.get(i)) == Math.abs(row - i)) {
                return false;
            }
        }
        return true;
    }
}
