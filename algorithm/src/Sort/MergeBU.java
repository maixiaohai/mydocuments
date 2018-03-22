package Sort;

/**
 * Created by maixiaohai on 17/2/7.
 */
public class MergeBU {
    private static Comparable[] aux;
    public static void main(String[] arg) {
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
        for (int sz = 1; sz < N; sz = sz + sz) {
            System.out.println(sz);
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz -1, N -1));
            }
        }
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        System.out.println(lo + "===" + hi);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
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

    public static void show (Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
