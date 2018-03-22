package lintcode.Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/11/10
 * @Description  给定 n 对括号，请写一个函数以将其生成新的括号组合，并返回所有组合结果。
 */
public class GenarateParenthesis {
    public static void main(String[] args) {
        // "((()))", "(()())", "(())()", "()(())", "()()()"
        int n = 3;
        List<String> res = generateParenthesis(n);
        for (String str : res) {
            System.out.println(str + " ");
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if (n == 0) {
            return res;
        }
        helper(res, n, n, "");
        return res;
    }

    private static void helper(List<String> res, int left, int right, String str) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left < 0 || right < 0 || left > right) {
            return;
        }
        helper(res, left - 1, right, str + "(");
        helper(res, left, right - 1, str + ")");
    }
}
