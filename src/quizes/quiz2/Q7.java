package quizes.quiz2;

public class Q7 {

    public static void main(String[] args) {
    }

    static String binarnoSestej(String a, String b) {
        int size = Math.min(a.length(), b.length());
        String res = "";
        boolean carry = false;
        for (int i = 0; i < size; i++) {
            char cA = a.charAt(a.length() - 1 - i);
            char cB = b.charAt(b.length() - 1 - i);
            res = (cA == '1' || cB == '1') ? "1" : "0" + res;
            carry = cA == '1' && cB == '1';
            // TODO: finish
        }
        return res;
    }
}
