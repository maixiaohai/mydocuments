package lintcode.DFSANDBFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/10/5
 * @Description  给一个由数字组成的字符串。求出其可能恢复为的所有IP地址
 *               给出字符串 "25525511135"，所有可能的IP地址为：
 *               [
 *               "255.255.11.135",
 *               "255.255.111.35"
 *               ]
 */
public class RestoreIpAddresses {
    public static void main(String[] args) {
        String ipstr = "25525511135";
        List<String> res = restoreIpAddresses(ipstr);
        for (String ip : res) {
            System.out.println(ip);
        }
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if (s.length() > 12 || s.length() < 4) {
            return res;
        }
        List<String> list = new ArrayList<String>();
        helper(res, list, s, 0);
        return res;
    }

    private static void helper(List<String> res, List<String> list, String s, int start) {
        if (list.size() == 4) {
            if (start != s.length()) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(list.get(i));
                if (i != 3) {
                    sb.append(".");
                }
            }
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < s.length() && i < start + 3; i++) {
            String tmp = s.substring(start, i + 1);
            if (isValid(tmp)) {
                list.add(tmp);
                helper(res, list, s, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    private static boolean isValid(String ip) {
        if (ip.length() == 0 || ip.length() >= 4) {
            return false;
        }
        if (ip.charAt(0) == '0') {
            return ip.equals("0");
        }
        int num = Integer.parseInt(ip);
        if (num < 0 || num > 255) {
            return false;
        }
        return true;
    }
}
