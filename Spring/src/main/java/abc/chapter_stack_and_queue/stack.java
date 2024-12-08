package abc.chapter_stack_and_queue;

import java.util.Stack;

public class stack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        System.out.println("栈="+stack);
        Integer peek = stack.peek();
        System.out.println("栈顶元素peek="+peek);

        Integer pop = stack.pop();
        System.out.println("pop="+pop+"stack="+stack);
    }
}
