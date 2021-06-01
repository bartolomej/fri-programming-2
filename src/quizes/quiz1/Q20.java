package quizes.quiz1;

public class Q20 {

    public static void main(String[] args) {
        veckratnikDelitelj(44, 0);
    }

    static void veckratnikDelitelj(int a, int b) {
        if (a == 0 || b == 0) {
            System.out.println("Napaka: obe števili morata biti različni od nič.");
            return;
        }
        System.out.printf("Največji skupni delitelj je %d.\n", najvecjiSkupniDeljitelj(a, b));
        System.out.printf("Najmanjši skupni večkratnik je %d.\n", najmanjsiSkupniVeckratnik(a, b));
    }

    static int najvecjiSkupniDeljitelj(int a, int b) {
        if (b == 0) {
            return a;
        }
        return najvecjiSkupniDeljitelj(b, a % b);
    }

    static int najmanjsiSkupniVeckratnik(int a, int b) {
        if (a == 0 || b == 0)
            return 0;
        else {
            int gcd = najvecjiSkupniDeljitelj(a, b);
            return Math.abs(a * b) / gcd;
        }
    }
}
