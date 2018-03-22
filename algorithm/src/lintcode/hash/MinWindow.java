package lintcode.hash;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/8/24
 * @Description 给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串中长度最小的
 */
public class MinWindow {
    public static void main(String[] args) {
        String source = "abc";
        String target = "ac";
        System.out.println(minWindow(source, target));
    }

    public static String minWindow(String source, String target) {
        if (source == null || target == null || source.length() == 0 || target.length() == 0) {
            return "";
        }
        int[] recordT = new int[255];
        int[] recordS = new int[255];
        for (int i = 0; i < target.length(); i++) {
            recordT[target.charAt(i)]++;
        }
        int begin = -1;
        int end = source.length() - 1;
        int found = 0;
        int minLength = source.length();
        int start = 0;
        for (int i = 0; i < source.length(); i++) {
            recordS[source.charAt(i)]++;
            if (recordS[source.charAt(i)] <= recordT[source.charAt(i)]) {
                found++;
            }
            if (found == target.length()) {
                System.out.println("==i : " + i + " start : " + start);
                // 将开头没用的都跳过，没用是指该字符出现次数超过了目标串中出现的次数，并把它们出现次数都减1
                // 意思是这个字符串开头不在指定目标字符串内的字符，可以直接减去，同时将start前移。
                while (start < i && (recordS[source.charAt(start)] > recordT[source.charAt(start)])) {
                    recordS[source.charAt(start)]--;
                    start++;
                }
                // 这时候start指向该子串开头的字母，判断该子串长度
                if ((i - start + 1) <= minLength) {
                    minLength = i - start + 1;
                    begin = start;
                    end = i;
                }
                // 把开头的这个匹配字符跳过，并将匹配字符数减1
                recordS[source.charAt(start)]--;
                found--;
                // 子串起始位置加1，我们开始看下一个子串了
                start++;
                System.out.println("begin :" + begin + " end: " + end + " minlength : " +minLength);
            }
        }
        // 如果begin没有修改过，返回空
        return begin == -1 ? "" : source.substring(begin, end + 1);
    }
}
