package Sort;

/**
 * Created by maixiaohai on 17/2/7.
 */
public class ShellSort {
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
        int h = 1;
        while(h < N/3) {h = 3 * h + 1;}
        System.out.println("h : " + h);
        while (h>=1) {
            for (int i = h; i < N; i++) {
                System.out.println("i : " + i );
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        System.out.println("exchange " + i + " and " + j);
        Comparable tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public static boolean isSorted(Comparable[] a) {
        for(int i = 0; i < a.length - 1; i++) {
            if (less(a[i+1],a[i])) return false;
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

