package quizes.quiz1;

public class Q2 {

    public static void main(String[] args) {
        kalkulator(42, 13);
        kalkulator(2, 0);
    }

    static void kalkulator(int a, int b) {
        if (b == 0) {
            System.out.println("Napaka: deljenje z 0");
            return;
        }
        System.out.printf("%d + %d = %d\n", a, b, a + b);
        System.out.printf("%d - %d = %d\n", a, b, a - b);
        System.out.printf("%d x %d = %d\n", a, b, a * b);
        System.out.printf("%d / %d = %d\n", a, b, a / b);
        System.out.printf("%d %% %d = %d\n", a, b, a % b);
    }
}