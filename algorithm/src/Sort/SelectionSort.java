package Sort;

/**
 * Created by maixiaohai on 17/2/3.
 * 常用于取序列中最大最小的几个数, 最差平均为O(n^2)。
 */
public class SelectionSort {
    public static void main(String[] args) {
        String[] a = {"test", "xiaofei", "dog", "times", "fire", "rain", "face"};
        sort(a);
        if (isSorted(a)) {
            System.out.println("has sorted, print");
            show(a);
        }
    }

    // 比较N方，交换N
    public static void sort(Comparable[] a) {
        int min;
        for (int i = 0; i < a.length; i++) {
            min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j],a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (less(a[i+1], a[i])) return false;
        }
        return true;
    }

}
