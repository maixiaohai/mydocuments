package Sort;

/**
 * Created by maixiaohai on 17/2/14.
 */
public class Quick {
    //平均时间复杂度为O(NlogN)。要求时间最快时使用。
    //1.选择第一个数为p，小于p的数放在左边，大于p的数放在右边。
    //2.递归的将p左边和右边的数都按照第一步进行，直到不能递归。
    //其实快速排序是基于一种叫做“二分”的思想
    public static void main(String[] args) {
        String[] a = {"test", "xiaofei", "dog", "times", "fire", "rain", "face"};
        sort(a);
        if (isSorted(a)) {
            System.out.println("has sorted, print");
            show(a);
        }
    }

    public static void sort(Comparable[] a) {
        // shuffle
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i],v)) if(i==hi) break;
            while (less(v,a[--j])) if(j==lo) break;
            if (i>=j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0;i < a.length - 1;i++) {
            if (less(a[i+1], a[i])) return false;
        }
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }
        System.out.println();
    }
}
