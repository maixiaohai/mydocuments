package lintcode.StringBasic;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/4
 * @Description 实现atoi这个函数，将一个字符串转换为整数。如果没有合法的整数，返回0。
 *              如果整数超出了32位整数的范围，返回INT_MAX(2147483647)如果是正整数，
 *              或者INT_MIN(-2147483648)如果是负整数。
 *              =================小米面试题==================
 *              思路：需要考虑正负数、小数、以及超过32位整数范围的正负数
 *              还有空格、中间的字母
 */
public class Atoi {
    public static void main(String[] args) {
        String str1 = "10";
        String str2 = "-1";
        String str3 = "1.0";
        String str4 = "123123123123123";  //print 2147483647
        String str5 = "    52lintcode   "; // print 52
        String str6 = "";
        System.out.println(atoi(str1));
        System.out.println(atoi(str2));
        System.out.println(atoi(str3));
        System.out.println(atoi(str4));
        System.out.println(atoi(str5));
        System.out.println(atoi(str6));
        // 7、8、9的例子说明中间有+ 或 - 或其他字符 就直接截断返回
        String str7 = "   +-1111 "; // print 0
        System.out.println(atoi(str7));
        String str8 = " 15+4"; // print 15
        System.out.println(atoi(str8));
        String str9 = "        112469032d53"; //print 112469032
        System.out.println(atoi(str9));
        String str10 = "1234567890123456789012345678901234567890"; //超过了long的界限
        System.out.println(Long.MAX_VALUE);//这个例子将越界判断放入for循环里，超过就返回
        System.out.println(atoi(str10)); // print 2147483647
    }

    public static int atoi(String str) {
        long res = 0;
        str = str.split("\\.")[0]; // 去掉小数点及其后面的部分
        str = str.trim(); //去掉字符串开头和结尾的空格字符
        if ("".equals(str)) {
            return 0;
        }
        int i = 0;
        boolean negative = false;
        if (str.charAt(0) == '-') {
            negative = true;
            i++;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        for (;i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                res *= 10;
                res += c - '0';
                if (!negative && res > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (negative && res > Integer.MAX_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
        }
        if (negative) {
            res = 0 - res;
        }
        return (int)res;
    }
}
