package quizes.quiz3;

public class Q2 {

    public static void main(String[] args) {
        int[] p1 = {1,2,3,4,5,6};
        int[] p2 = {1,1};
        int[] p1p2 = zmnoziPolinoma(p1, p2);
        System.out.println(java.util.Arrays.toString(p1p2));
    }

    static int[] zmnoziPolinoma(int[] a, int[] b) {
        int[] res = new int[(a.length + b.length) - 1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int exp = i + j;
                int base = a[i] * b[j];
                res[exp] += base;
            }
        }
        return res;
    }
}
