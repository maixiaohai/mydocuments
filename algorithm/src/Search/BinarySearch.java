package Search;

/**
 * Created by maixiaohai on 17/2/2.
 */
public class BinarySearch {
    // 前提，数组a必须是有序的
    // 找到key，返回对应角标，找不到返回-1
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        int mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else  if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
