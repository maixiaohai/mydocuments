package lintcode;

import java.util.*;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/6
 * @Description
 */
public class IntervalOpt {

    public static void main(String[] args) throws Exception {
//        String ap_mac = "\u0000\u0000:\u0000\u0000:\u0000\u0000:\u0000\u0000:\u0000\u0000:\u0000\u0000";
//        System.out.println(new String(ap_mac.getBytes(),"utf-8"));
//        String test = "特别吵ttt";
//        System.out.println(new String(test.getBytes(),"utf-8"));
    }

    public static  int romanToInt(String s) {
        int[] table = new int[256];
        table['I'] = 1;
        table['V'] = 5;
        table['X'] = 10;
        table['L'] = 50;
        table['C'] = 100;
        table['D'] = 500;
        table['M'] = 1000;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i + 1) >= s.length() || table[s.charAt(i)] >= table[s.charAt(i + 1)]) {
                res += table[s.charAt(i)];
            } else {
                res -= table[s.charAt(i)];
            }
        }
        return res;
    }

    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        int size = intervals.size();
        for (int i = 0; i < size; i++) {
            Interval oldInterval = intervals.get(i);
            int start = oldInterval.start;
            int end = oldInterval.end;
            if (newInterval.start > end) {
                result.add(oldInterval);
            } else if (newInterval.end < start) {
                result.add(newInterval);
                for (int j = i; j < size; j++) {
                    result.add(intervals.get(j));
                }
                break;
            } else {
                newInterval.start = Math.min(start, newInterval.start);
                newInterval.end = Math.max(end, newInterval.end);
            }
            if (i == (size - 1)) {
                result.add(newInterval);
            }
        }
        return result;
    }
}
