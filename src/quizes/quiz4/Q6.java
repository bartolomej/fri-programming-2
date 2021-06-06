package quizes.quiz4;

import common.Utils;

public class Q6 {

    public static void main(String[] args) {
        Utils.print(getVrstica(20));
    }

    static java.util.Map<Integer, int[]> memo = new java.util.HashMap<>();

    static int[] getVrstica(int n) {
        if (n == 1) {
            return new int[]{1};
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int[] row = new int[n];
        row[0] = n % 10;
        for (int i = 1; i < n; i++) {
            row[i] = (row[i - 1] + getVrstica(n - 1)[i - 1]) % 10;
        }
        memo.put(n, row);
        return row;
    }
}
