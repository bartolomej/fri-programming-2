package quizes.quiz1;

public class Q9 {

    public static void main(String[] args) {
        izrisiZastavo(1);
        izrisiZastavo(2);
        izrisiZastavo(3);
    }

    static void izrisiZastavo(int n) {
        for (int i = 0; i < n * 5; i++) {
            System.out.println((i < 3 * n ? stars(n * 4, i) : lines(n * 4)) + lines(12 * n - (n - 1)));
        }
    }

    static String stars(int length, int line) {
        String s = "";
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                s += " ";
            } else {
                s += i % 2 == 0 ? line % 2 == 0 ? '*' : ' ' : line % 2 == 0 ? ' ' : '*';
            }
        }
        return s;
    }

    static String lines(int n) {
        String res = "";
        for (int i = 0; i < n; i++) {
            res += "=";
        }
        return res;
    }
}
