package lintcode.DataStru;

import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/5
 * @Description 所有操作要求都在O(1)时间内完成
 */
public class MinStack {
    private Stack<Integer> stack1;
    private Stack<Integer> stackMin;
    private int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        MinStack stack = new MinStack();
//        stack.push(1);
//        System.out.println(stack.pop());
//        stack.push(2);
//        stack.push(3);
//        System.out.println(stack.min());
//        stack.push(1);
//        System.out.println(stack.min());
        stack.push(1);
        stack.push(1);
        stack.push(1);
        System.out.println(stack.min());
        System.out.println(stack.pop());
        System.out.println(stack.min());
        System.out.println(stack.pop());
    }

    public MinStack() {
        stack1 = new Stack<Integer>();
        stackMin = new Stack<Integer>();
    }

    public void push(int number) {
        stack1.push(number);
        if (number < min) {
            min = number;
        }
        stackMin.push(min);
    }

    public int pop() {
        int popNum = stack1.pop();
        stackMin.pop();
        if (!stackMin.isEmpty()) {
            min = stackMin.peek();
        } else {
            min = Integer.MAX_VALUE;
        }
        return popNum;
    }

    public int min() {
        return stackMin.peek();
    }
}
