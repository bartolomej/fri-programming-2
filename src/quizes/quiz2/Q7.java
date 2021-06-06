package quizes.quiz2;

public class Q7 {

    public static void main(String[] args) {
        System.out.println(binarnoSestej("101011001101110101", "111110010000111010"));
    }

    static String binarnoSestej(String binA, String binB) {
        int size = Math.max(binA.length(), binB.length());
        String res = "";
        boolean c = false;
        int i = 0;
        while (i < size || c) {
            boolean a = b(i >= binA.length() ? '0' : binA.charAt(binA.length() - 1 - i));
            boolean b = b(i >= binB.length() ? '0' : binB.charAt(binB.length() - 1 - i));
            res = ((xor(xor(a, b), c) || (a && b && c)) ? '1' : '0') + res;
            c = (b && a) || (b && c) || (a && c);
            i++;
        }
        return res;
    }

    static boolean b(char c) {
        return c == '1';
    }

    static boolean xor(boolean a, boolean b) {
        return (a && !b) || (!a && b);
    }
}
