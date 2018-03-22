package lintcode.StringBasic;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/11
 * @Description  给定一个字符串，验证其是否为数字。
 *               "0" => true
 *               " 0.1 " => true
 *               "abc" => false
 *               "1 a" => false
 *               "2e10" => true
 */
public class IsNumber {
    public static void main(String[] args) {
        String a = "0";
        System.out.println(isNumber(a));
        a = " 0.1";
        System.out.println(isNumber(a));
        a = "abc";
        System.out.println(isNumber(a));
        a = "1 a";
        System.out.println(isNumber(a));
        a = "2e10";
        System.out.println(isNumber(a));
        //如.1=true  1.=true e1=false 1e=false
        a = ".1";
        System.out.println(isNumber(a));
        a = "1.";
        System.out.println(isNumber(a));
        a = "e1";
        System.out.println(isNumber(a));
        a = "1e";
        System.out.println(isNumber(a));
        a = "3e4.6";
        System.out.println(isNumber(a)); // false
    }

    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim();
        if (s.length() == 0) { return false; }
        if (s.charAt(0) == '.' && s.length() == 1 ) {
            return false;
        }
        if (s.charAt(0) == 'e') {
            return false;
        }
        boolean isFirstE = false;
        boolean isFirstDot = false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                continue;
            }
            char x = s.charAt(i);
            if (x >= '0' && x <= '9') {
                continue;
            } else if (x == 'e' && !isFirstE && i != (s.length() - 1)) {
                isFirstE = true;
                continue;
            } else if (x == '.' && !isFirstDot && !isFirstE) { //e出现时可以有. 但.出现时不应该有e
                isFirstDot = true;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
