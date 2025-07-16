
import java.util.Stack;

public class SortStackRecursively {
    public static void sort(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int x = stack.pop();
            sort(stack);
            insert(stack, x);
        }
    }

    private static void insert(Stack<Integer> stack, int x) {
        if (stack.isEmpty() || x > stack.peek()) {
            stack.push(x);
            return;
        }
        int y = stack.pop();
        insert(stack, x);
        stack.push(y);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        sort(stack);
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }
}
