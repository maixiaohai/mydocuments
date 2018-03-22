package lintcode;

import java.util.*;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/7
 * @Description finger storm 3th
 */
public class test {
    public static void main(String[] args){
//        char[][] matrix = new char[][]{{'i','s','o','x'},{'a','d','c','b'},{'f','j','r','n'},{'d','o','p','e'}};
//        String[] target = new String[]{"xadd"};
//        String[] res = Find(matrix, target);
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }
    }

    public static String[] Find(char[][] matrix, String[] target) {
        HashMap<Character, Stack<String>> hm = char2Map(matrix);
        String[] res = new String[target.length];
        for (int i = 0; i < target.length; i++) {
            String str = target[i];
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < str.length(); j++) {
                if (hm.containsKey(str.charAt(j))) {
                    Stack<String> stack = hm.get(str.charAt(j));
                    if (!stack.isEmpty()) {
                        sb.append(stack.pop());
                        sb.append(" ");
                    }
                }
            }
            res[i] = sb.toString().trim();
        }
        return res;
    }

    private static HashMap<Character, Stack<String>> char2Map(char[][] matrix) {
        HashMap<Character, Stack<String>> hm = new HashMap<Character, Stack<String>>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                String index = Integer.toString(i) + Integer.toString(j);
                char key = matrix[i][j];
                if (hm.containsKey(key)) {
                    Stack<String> stack = hm.get(matrix[i][j]);
                    stack.push(index);
                    hm.put(key,stack);
                } else {
                    Stack<String> stack = new Stack<String>();
                    stack.push(index);
                    hm.put(key, stack);
                }
            }
        }
        return hm;
    }
}
