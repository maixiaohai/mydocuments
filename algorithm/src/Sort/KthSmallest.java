package Sort;

import java.util.*;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 2017/9/17
 * @Description  在一个排序矩阵中找从小到大的第 k 个整数。排序矩阵的定义为：每一行递增，每一列也递增。
 *               思路：利用一个堆来存储前k个最小值，java中为PriorityQueue
 */
public class KthSmallest {
    public static void main(String[] args) {
        int k = 4;
        int[][] matrix = new int[][]{
                {1, 5, 7},
                {3, 7, 8},
                {4, 8, 9}
        };
        System.out.println(kthSmallest(matrix, k)); //print 5
        String x = "d";
        System.out.println(x.substring(1));
        int[] array = new int[]{1, 4, 5, 2, 79, 33};
        int[] res = selectKmax(array, 3);
        for (Integer x1 : res) {
            System.out.println(x1 + " ");
        }
    }

    public static int kthSmallest(final int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                int x = matrix[a[0]][a[1]];
                int y = matrix[b[0]][b[1]];
                return (x < y) ? -1 : ((x == y) ? 0 : 1);
            }
        });
        heap.add(new int[]{0, 0});
        visited[0][0] = true;
        while (k > 1) {
            int[] res = heap.remove();
            int x = res[0];
            int y = res[1];
            if (x + 1 < row && !visited[x + 1][y]) {
                visited[x + 1][y] = true;
                heap.add(new int[]{x + 1, y});
            }
            if (y + 1 < col && !visited[x][y + 1]) {
                visited[x][y + 1] = true;
                heap.add(new int[]{x, y + 1});
            }
            k--;
        }
        int[] res = heap.remove();
        return matrix[res[0]][res[1]];
    }

    // 求前k个最大的数，用priority queue，然后分析时间复杂度
    // 生成最大堆使用o2-o1,生成最小堆使用o1-o2,
    public static int[] selectKmax(int[] array, int k) {
        int[] res = new int[k];
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < array.length; i++) {
            if (heap.size() < k) {
                heap.add(array[i]);
            } else {
                int min = heap.peek();
                System.out.println("min " + min);
                if (array[i] > min) {
                    heap.poll();
                    heap.add(array[i]);
                }
            }
        }
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll();
        }
        return res;
    }
}
