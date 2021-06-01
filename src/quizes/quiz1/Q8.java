package quizes.quiz1;

public class Q8 {

    public static void main(String[] args) {
        System.out.println(jePrastevilo(13));
        System.out.println(jePrastevilo(42));
        System.out.println(jePrastevilo(-13));
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
