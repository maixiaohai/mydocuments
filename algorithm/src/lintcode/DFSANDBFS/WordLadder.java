package lintcode.DFSANDBFS;

import java.util.*;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/15
 * @Description 给出两个单词（start和end）和一个字典，找到从start到end的最短转换序列
 *              每次只能改变一个字母，变换过程中的中间单词必须在字典中出现
 *              广度优先搜索，start为起点，end为终点，找出最短路径
 */
public class WordLadder {
    public static void main(String[] args) {
        String start = "hit";
        String end = "cog";
//        String start = "a";
//        String end = "a";
        Set<String> dict = new LinkedHashSet<String>();
        //dict.add("b");
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        //"hit" -> "hot" -> "dot" -> "dog" -> "cog"
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            ladderLength(start, end, dict);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("method 1 use " + (endtime - starttime) + "ms");
        for (int i = 0; i < 10000; i++) {
            ladderLength2(start, end, dict);
        }
        long endtime2 = System.currentTimeMillis();
        System.out.println("method 2 use " + (endtime2 - endtime) + "ms");
    }

    /**
     * @param start, a string
     * @param end, a string
     * @param dict, a set of string
     * @return an integer
     */
    public static int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null || dict.size() == 0) {
            return 0;
        }
        if (start.equals(end)) {
            return 0;
        }
        HashSet<String> hashSet = new LinkedHashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        hashSet.add(start);
        queue.offer(start);
        dict.add(end);// must add
        int length = 1;
        while (!queue.isEmpty()) {
            length++;
            int size = queue.size();
            //System.out.println(size);
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                //System.out.println(word);
                for (String nextWord : getNextWords(word, dict)) {
                    //System.out.println("nextWord : " + nextWord);
                    if (hashSet.contains(nextWord)) {
                        continue;
                    }
                    if (end.equals(nextWord)) {
                        return length;
                    }
                    hashSet.add(nextWord);
                    queue.offer(nextWord);
                }
            }
        }
        return 0;
    }

    private static String exch(String word, int index, char c) {
        char[] array = word.toCharArray();
        array[index] = c;
        return new String(array);
    }

    private static ArrayList<String> getNextWords(String word, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = exch(word, i, c);
                if (dict.contains(nextWord)) {
                    res.add(nextWord);
                }
            }
        }
        return res;
    }

    public static int ladderLength2(String start, String end, Set<String> dict) {
        if (dict == null || dict.size() == 0) {
            return 0;
        }
        if (start.equals(end)) {
            return 1;
        }
        int count = 0;
        Queue<String> queue = new LinkedList<String>();
        boolean[] visited = new boolean[dict.size()];
        List<String> list = new ArrayList<String>(dict);
        queue.offer(start);
        queue.offer(".");
        while (!queue.isEmpty()) {
            String word = queue.poll();
            if (".".equals(word)) {
                count++;
                continue;
            }
            if (changeOne(word, end)) {
                count += 2;
                break;
            }
            for (int i = 0; i < list.size(); i++) {
                if (!visited[i] && changeOne(word, list.get(i))) {
                    queue.offer(list.get(i));
                    visited[i] = true;
                }
            }
            if (".".equals(queue.peek())) {
                queue.offer(".");
            }
        }
        return count;
    }

    private static boolean changeOne(String source, String target) {
        int l = source.length();
        int count = 0;
        for (int i = 0; i < l; i++) {
            if (source.charAt(i) != target.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1 ? true : false;
    }
}
