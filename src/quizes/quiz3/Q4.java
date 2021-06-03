package quizes.quiz3;

public class Q4 {

    public static void main(String[] args) {
        System.out.println(najdaljsiPalindrom("perica reze raci rep", false));
        System.out.println(najdaljsiPalindrom("perica reze raci rep", true));
    }

    static String najdaljsiPalindrom(String s, boolean includeSpaces) {
        String temp = s;
        int searchLength = s.length() + 1;
        int index = 0;
        while (searchLength > 0) {
            if (isPalindrom(temp, includeSpaces)) {
                return temp;
            }
            if (index + searchLength >= s.length()) {
                index = 0;
                searchLength--;
            }
            temp = s.substring(index, index + searchLength);
            index++;
        }
        return "";
    }

    static boolean isPalindrom(String s, boolean includeSpaces) {
        s = includeSpaces ? s : s.replaceAll("\\s", "");
        if (s.length() == 1 || s.length() == 0) {
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        return isPalindrom(s.substring(1, s.length() - 1), includeSpaces);
    }
}
