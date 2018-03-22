package lintcode;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/2/21
 * @Description 有效回文监测，注意点：字符串为空时，依然有效; 字母的大小写
 */
public class Palindrome {
    public static void main(String[] args) {
        String a = "time dd em, i** t";
        if (isPalindrome(a)) System.out.println("is palindrome");
    }
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {return true;}
        // Write your code here
        int n = s.length();
        int lo = 0, hi = n - 1;
        boolean flag = true;
        while(lo <= hi) {
            while (!isValid(s.charAt(lo))) {
                lo++;
                if(lo >= hi) return true;
            }
            while (!isValid(s.charAt(hi))) {
                hi--;
                if (hi<=lo) return true;
            }
            char left = s.charAt(lo++);
            char right = s.charAt(hi--);
            if ( left != right && left+32 != right && left-32 != right) {
                return false;
            }
        }
        return flag;
    }

    public static boolean isValid(char x) {
        if (x>='A' && x <= 'z') return true;
        if (x>='0' && x <= '9') return true;
        return false;
    }

}
