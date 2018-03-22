package lintcode.hash;

import java.util.*;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/3/9
 * @Description
 */
public class ThreeSumBaoli {
    // input : [1,0,-1,-1,-1,-1,0,1,1,1] expect : [[-1,0,1]]
    //input : [-1,0,1,2,-1,-4] expect: [[-1,-1,2],[-1,0,1]]
    public static void main(String[] args) {
        int[] numbers = new int[]{1,0,-1,-1,-1,-1,0,1,1,1};
        //int[] numbers = new int[]{-1,0,1,2,-1,-4};
        ArrayList<ArrayList<Integer>> res = threeSum(numbers);
        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> tmp = res.get(i);
            for (int j = 0; j < tmp.size(); j++) {
                System.out.print(tmp.get(j));
                System.out.print(",");
            }
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        // write your code here
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        HashSet<String> hashSet = new HashSet<String>();
        Arrays.sort(numbers);
        int length = numbers.length;
        int c;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                c = -(numbers[i] + numbers[j]);
                for (int k = j + 1; k < length; k++) {
                    if (numbers[k] == c) {
                        hashSet.add(numbers[i] + "," + numbers[j] + "," + c);
                        break;
                    }
                }
            }
        }
        for (String str: hashSet) {
            String[] strList = str.split(",");
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            for (int i = 0; i < strList.length; i++) {
                tmp.add(Integer.parseInt(strList[i]));
            }
            list.add(tmp);
        }
        return list;
    }
}
