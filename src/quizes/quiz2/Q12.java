package quizes.quiz2;

public class Q12 {

    public static void main(String[] args) {
        System.out.println(fibo(3));
    }

    static int fibo(int n) {
        int[][] fib = new int[n][n];
        int a = 0, b = 1, c;
        for (int i = 0; i < fib.length; i++) {
            for (int j = 0; j < fib[i].length; j++) {
                c = a + b;
                b = a;
                a = c;
                fib[i][j] = a;
            }
        }
        int sum = 0;
        for (int i = 0; i < fib.length; i++) {
            sum += fib[i][i] + fib[fib.length - i - 1][i];
        }
        return sum;
    }

}
