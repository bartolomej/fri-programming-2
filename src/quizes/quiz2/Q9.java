package quizes.quiz2;

public class Q9 {

    public static void main(String[] args) {
        System.out.println(prevod("Napaka"));
    }

    static String prevod(String s) {
        if (isPapaya(s)) {
            return fromPapaya(s);
        } else {
            return toPapaya(s);
        }
    }

    static boolean isPapaya(String s) {
        int i = 0;
        while (i < s.length()) {
            if (jeSamoglasnik(s.charAt(i))) {
               if (!(s.length() >= i + 3 && s.startsWith("pa", i + 1))) {
                   return false;
               } else {
                   i += 3;
               }
            } else {
                i++;
            }
        }
        return true;
    }

    static String fromPapaya(String s) {
        String out = "";
        int i = 0;
        while (i < s.length()) {
            out += s.charAt(i);
            if (jeSamoglasnik(s.charAt(i))) {
                i += 3;
            } else {
                i++;
            }
        }
        return out;
    }

    static String toPapaya(String s) {
        String out = "";
        String in = s;
        for (int i = 0; i < in.length(); i++) {
            char letter = in.charAt(i);
            if (jeSamoglasnik(letter)) {
                out += letter + "pa";
            } else {
                out += letter;
            }
        }
        return out;
    }

    static boolean jeSamoglasnik(char c) {
        char[] samoglasniki = new char[]{'A', 'E', 'I', 'O', 'U'};
        char a = c >= 'a' ? (char)(c - 32) : c;
        for (char b : samoglasniki) {
            if (a == b) {
                return true;
            }
        }
        return false;
    }
}
