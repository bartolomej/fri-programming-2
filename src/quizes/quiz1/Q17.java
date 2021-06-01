package quizes.quiz1;

public class Q17 {

    public static void main(String[] args) {
        praDvojcek(35);
    }

    static void praDvojcek(int n) {
        int a = 1, b = 1;
        for (int i = 0; i < n; i++) {
            if (jePrastevilo(i)) {
                b = a;
                a = i;
                if (a - b == 2) {
                    System.out.printf("(%d, %d)\n", b, a);
                }
            }
        }
    }

    static boolean jePrastevilo(int n) {
        int i = n + (n > 0 ? -1 : 1);
        while (n > 0 ? i > 1 : i < 1) {
            if (n % i == 0) {
                return false;
            }
            i = i + (n > 1 ? -1 : 1);
        }
        return true;
    }
}
