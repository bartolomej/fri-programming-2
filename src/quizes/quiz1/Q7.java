package quizes.quiz1;

public class Q7 {

    public static void main(String[] args) {
        System.out.println(jeFibonaccijevo(13));
        System.out.println(jeFibonaccijevo(14));
        System.out.println(jeFibonaccijevo(34));
        System.out.println(jeFibonaccijevo(2021));
    }

    static boolean jeFibonaccijevo(int n) {
        int a = 1, b = 1, c;
        while (true) {
            c = a + b;
            a = b;
            b = c;
            if (c == n) {
                return  true;
            }
            if (c > n) {
                return false;
            }
        }
    }
}
