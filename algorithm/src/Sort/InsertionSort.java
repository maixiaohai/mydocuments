package Sort;

/**
 * Created by maixiaohai on 17/2/4.
 * 常用于，把新的数据插入到已经排好的数据列中，最差平均为O(n^2),最好是O(n)。
 */
public class InsertionSort {
    public static void main(String[] args) {
        String[] a = {"test", "xiaofei", "dog", "times", "fire", "rain", "face"};
        sort(a);
        if (isSorted(a)) {
            System.out.println("has sorted, print");
            show(a);
        }
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j],a[j-1]);j--)
                exch(a, j, j-1);
        }

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
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
