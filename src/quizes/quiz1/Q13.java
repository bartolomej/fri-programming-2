package quizes.quiz1;

public class Q13 {

    public static void main(String[] args) {
        pitagoroviTrojcki(25);
        pitagoroviTrojcki(125);
    }

    // TODO: doesn't work
    static void pitagoroviTrojcki(int limit) {
        int a, b, c = 0;
        int m = 2;
        while (c < limit) {
            for (int n = 1; n < m; ++n) {
                a = m * m - n * n;
                b = 2 * m * n;
                c = m * m + n * n;

                if (c > limit)
                    break;

                System.out.printf("%d %d %d\n", a, b, c);
            }
            m++;
        }
    }
}
