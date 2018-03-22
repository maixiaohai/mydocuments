package Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maixiaohai on 17/2/7.
 * 1.选择相邻两个数组成一个有序序列。
 * 2.选择相邻的两个有序序列组成一个有序序列。
 * 3.重复第二步，直到全部组成一个有序序列。
 */
public class Merge {
    private static Comparable[] aux;
    public static void main(String[] args){
        String[] a = {"test", "xiaofei", "dog", "times", "fire", "rain", "face"};
        sort(a);
        if (isSorted(a)) {
            System.out.println("has sorted, print");
            show(a);
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        sort(a, 0 , N - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        System.out.println(lo + "===" + hi);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        show(a);
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j],aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        show(a);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length -1; i++) {
            if (less(a[i+1], a[i])) return false;
        }
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
