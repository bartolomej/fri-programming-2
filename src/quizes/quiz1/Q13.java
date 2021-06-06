package quizes.quiz1;

public class Q13 {

    public static void main(String[] args) {
        pitagoroviTrojcki(25);
        pitagoroviTrojcki(125);
    }

    static void pitagoroviTrojcki(int limit) {
        for (int a = 1; a <= limit; a++) {
            for (int b = a + 1; b <= limit; b++) {
                for (int c = b + 1; c <= limit; c++) {
                    if (a * a + b * b == c * c) {
                        System.out.printf("%d %d %d\n", a, b, c);
                    }
                }
            }
        }
    }
}
