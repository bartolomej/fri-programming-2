package quizes.quiz2;

import common.Utils;

public class Q13 {

    public static void main(String[] args) {
        Utils.print(pascal(7));
    }

    static int[] pascal(int n) {
        int[] elements = new int[n];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = p(n - 1, i);
        }
        return elements;
    }

    static int p(int row, int col) {
        return fac(row) / (fac(col) * fac(row - col));
    }

    static int fac(int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }
}
