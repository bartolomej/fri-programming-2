package quizes.quiz1;

public class Q12 {

    public static void main(String[] args) {
        System.out.println(vsotaPrvih(10));
        System.out.println(vsotaPrvih(100));
    }

    static int vsotaPrvih(int n) {
        int count = 0;
        int sum = 0;
        int i = 1;
        while (count < n) {
            i++;
            if (jePrastevilo(i)) {
                sum += i;
                count++;
            }
        }
        return sum;
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
