package quizes.quiz2;

import common.Utils;

public class Q3 {

    public static void main(String[] args) {
        Utils.print(range(5, 20, 3));
        Utils.print(range(420, 9001, 420));
    }

    static int[] range(int a, int b, int c) {
        int size = (int)Math.ceil((double)(b - a) / c);
        int[] res = new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = a + (i * c);
        }
        return res;
    }
}
