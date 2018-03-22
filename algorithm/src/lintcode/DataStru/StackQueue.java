package lintcode.DataStru;

import java.util.Stack;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/5
 * @Description
 */
public class StackQueue {
    public static void main(String[] args) {
        StackQueue queue = new StackQueue();
        queue.push(1);
        System.out.println(queue.pop());
        queue.push(2);
        queue.push(3);
        System.out.println(queue.top());
        System.out.println(queue.pop());
    }
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public StackQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public void push(int element) {
        stack1.push(element);
    }

//    public int pop() {
//        int res = stack1.get(0);
//        stack1.remove(0);
//        return res;
//    }
//
//    public int top() {
//        return stack1.get(0);
//    }
    public int pop() {
        if (stack2.isEmpty()) {
            int res;
            while (!stack1.isEmpty()) {
                res = stack1.pop();
                stack2.push(res);
            }
        }
        return stack2.pop();
    }

    public int top() {
        if (stack2.isEmpty()) {
            int res;
            while (!stack1.isEmpty()) {
                res = stack1.pop();
                stack2.push(res);
            }
        }
        return stack2.peek();
    }
}
