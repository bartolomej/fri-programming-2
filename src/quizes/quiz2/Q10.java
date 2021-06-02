package quizes.quiz2;

public class Q10 {

    public static void main(String[] args) {
        System.out.println(prepleti("december", "maj"));
        odpleti(prepleti("december", "maj"));
    }

    static String prepleti(String niz1, String niz2) {
        String out = "";
        int maxSize = Math.max(niz1.length(), niz2.length());
        niz1 = fixSize(niz1, maxSize);
        niz2 = fixSize(niz2, maxSize);
        for (int i = 0; i < maxSize * 2; i++) {
            if (i % 2 == 0) {
                out += niz1.charAt(i / 2);
            } else {
                out += niz2.charAt(i / 2);
            }
        }
        return out;
    }

    static void odpleti(String niz) {
        String niz1 = "";
        String niz2 = "";
        for (int i = 0; i < niz.length(); i++) {
            if (i % 2 == 0) {
                niz1 += niz.charAt(i);
            } else {
                niz2 += niz.charAt(i);
            }
        }
        System.out.println(niz1);
        System.out.println(niz2);
    }

    static String fixSize(String s, int size) {
        if (s.length() < size) {
            return s + " ".repeat(size - s.length());
        } else {
            return s;
        }
    }

}
