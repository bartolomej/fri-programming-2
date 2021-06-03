package quizes.quiz3;

import common.Utils;

public class Q1 {

    public static void main(String[] args) {
        int[] p1 = {1,2,3,4,5,6};
        int[] p2 = {1,2,3};
        Utils.print(sestejPolinoma(p1, p2));
    }

    static int[] sestejPolinoma(int[] a, int[] b) {
        int maxSize = Math.max(a.length, b.length);
        int[] sum = new int[a.length];
        for (int i = 0; i < maxSize; i++) {
            sum[i] = (i >= a.length ? 0 : a[i]) + (i >= b.length ? 0 : b[i]);
        }
        return sum;
    }
}
