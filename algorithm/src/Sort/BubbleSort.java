package Sort;

/**
 * Created by maixiaohai on 17/2/3.
 * 一般不用这种方法： 最差平均为O(n^2),最好是O(n),此时已经有序。
 * 1.将序列中所有元素两两比较，将最大的放在最后面。
 * 2.将剩余序列中所有元素两两比较，将最大的放在最后面。
 * 3.重复第二步，直到只剩下一个数。
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {7, 5, 1, 3, 6, 4};
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        String[] a1 = {"test", "xiaofei", "dog", "times", "fire", "rain", "face"};
        sort(a1);
        if (isSorted(a1)) {
            System.out.println("has sorted, print");
            show(a1);
        }
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int tmp = a[j];
                    a[j] = a[i];
                    a[i] = tmp;
                }
            }
        }
    }

    // 从后往前
    // 比较N方，交换N方
    public static void sort(Comparable[] a) {
        for (int i = a.length - 1;i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (less(a[j+1],a[j])) exch(a, j+1, j);
            }
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
            if (less(a[i+1],a[i])) return false;
        }
        return true;
    }
}
