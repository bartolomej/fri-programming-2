package quizes.quiz3;

public class Q9 {

    public static void main(String[] args) {
        System.out.println(izracunaj("1 2 3 4 + * +"));
    }

    static int izracunaj(String exp) {
        java.util.Stack<Integer> operandStack = new java.util.Stack<>();
        java.util.Stack<Character> operatorStack = new java.util.Stack<>();
        String[] parts = exp.split(" ");
        for (String p : parts) {
            int code = p.charAt(0);
            if (code >= 48 && code <= 57) {
                operandStack.push(Integer.parseInt(p));
            } else {
                operatorStack.push((char)code);
            }
        }
        reverseStack(operandStack);
        while (true) {
            int a = operandStack.pop();
            int b = operandStack.pop();
            char op = operatorStack.pop();
            try {
                int result = calc(a, b, op);
                if (operandStack.empty()) {
                    return result;
                } else {
                    operandStack.push(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static int calc(int a, int b, int op) throws Exception {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: throw new Exception("Operation not permitted");
        }
    }

    static void reverseStack(java.util.Stack<Integer> stack) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        java.util.Collections.reverse(list);
        stack.addAll(list);
    }
}
