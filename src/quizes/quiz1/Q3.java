package quizes.quiz1;

public class Q3 {

    public static void main(String[] args) {
        nicli(2,-16,30);
    }

    static void nicli(int a, int b, int c) {
        double d = Math.pow(b, 2) - 4 * a * c;
        if (d < 0) {
            System.out.println("Napaka: nicli enacbe ne obstajata");
            return;
        }
        double x1 = (-b + Math.sqrt(d)) / (2 * a);
        double x2 = (-b - Math.sqrt(d)) / (2 * a);
        System.out.printf("x1=%.2f, x2=%.2f\n", x1, x2);
    }
}
