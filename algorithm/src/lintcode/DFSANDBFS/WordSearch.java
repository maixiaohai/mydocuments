package lintcode.DFSANDBFS;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/13
 * @Description  给出一个二维的字母板和一个单词，寻找字母板网格中是否存在这个单词。
 *               单词可以由按顺序的相邻单元的字母组成，其中相邻单元指的是水平或者垂直方向相邻。
 *               每个单元中的字母最多只能使用一次。
 */
public class WordSearch {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED"; // exist
        //String word = "ABCB"; // not exist
        if (exist(board, word)) {
            System.out.println(word + " exist in board");
        } else {
            System.out.println("Sorry, " + word + " not exist in board");
        }
    }

    public static boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null) {
            return false;
        }
        int m = board.length;
        if (m == 0) {
            return false;
        }
        int n = board[0].length;
        if (n == 0) {
            return false;
        }
        int[][] isVisited = new int[m][n];
        char[] array = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(find(board, array, isVisited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean find(char[][] board, char[] array, int[][] isVisited, int x, int y, int index) {
        if (index == (array.length - 1) && board[x][y] == array[index]) {
            return true;
        }
        int[] xStep = new int[]{1, -1, 0, 0};
        int[] yStep = new int[]{0, 0, -1, 1};
        boolean isValid = false;
        if (board[x][y] == array[index]) {
            for (int k = 0; k < 4; k++) {
                int newX = x + xStep[k];
                int newY = y + yStep[k];
                if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length
                        && isVisited[newX][newY] == 0) {
                    isVisited[x][y] = 1;
                    isValid = isValid || find(board, array, isVisited, newX, newY, index + 1);
                    isVisited[x][y] = 0;
                }
            }
        } else {
            return false;
        }
        return isValid;
    }
}
