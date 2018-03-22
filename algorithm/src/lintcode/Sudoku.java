package lintcode;

import java.util.HashSet;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/4
 * @Description 判断给定数独是否有效
 */
public class Sudoku {
    public static void main(String[] args){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int m = i / 3 * 3 + j / 3;
                int n = i % 3 * 3 + j % 3;
                System.out.println(i+ " " + " " + j + "===" + m  + " " + n);
            }
        }
    }
    private static HashSet<Character> set = new HashSet<Character>();

    public static boolean isValidSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        // row check
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!check(board[i][j])) {
                    return false;
                }
            }
            set.clear();
        }
        // column check
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!check(board[j][i])) {
                    return false;
                }
            }
            set.clear();
        }
        // for box
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!check(board[i / 3 * 3 + j / 3][i % 3 * 3 + j % 3])) {
                    return false;
                }
            }
            set.clear();
        }
        return true;
    }

    private static boolean check(char x) {
        if (x == '.') {
            return true;
        } else if (x >= '1' && x <= '9') {
            if (set.contains(x)) {
                return false;
            } else {
                set.add(x);
                return true;
            }
        } else {
            return false;
        }
    }
}
