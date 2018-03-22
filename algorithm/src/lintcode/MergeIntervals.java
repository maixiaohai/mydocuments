package lintcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/12
 * @Description 合并区间，要点在于:
 * 1.先排序，自定义Comparator 2.List中的
 */
public class MergeIntervals {
    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(6, 9));
        intervals.add(new Interval(8, 10));
        List<Interval> res = merge(intervals);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).start);
            System.out.println(res.get(i).end);
        }
    }

    public static class Interval{
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        //sort
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                } else {
                    return o1.end - o2.end;
                }
            }
        });
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (last.end >= current.start) {
                last.end = Math.max(current.end, last.end);
            } else {
                res.add(last);
                last = current;
            }
        }
        res.add(last);
        return res;
    }
}
