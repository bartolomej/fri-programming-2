package quizes.quiz3;

public class Q3 {

    public static void main(String[] args) {
        System.out.println(jeAnagram("AbC", "bCa", true));
        System.out.println(jeAnagram("AbC", "bCa", false));
    }

    static boolean jeAnagram(String first, String second, boolean ignoreCase) {
        String s1 = format(first, ignoreCase);
        String s2 = format(second, ignoreCase);
        if (s1.length() != s2.length()) {
            return false;
        }
        return java.util.Arrays.equals(sortWord(s1), sortWord(s2));
    }

    static String format(String s, boolean ignoreCase) {
        s = s.replaceAll("\\s", "");
        if (ignoreCase) {
            s = s.toUpperCase();
        }
        return s;
    }

    static char[] sortWord(String s) {
        char[] sorted = s.toCharArray();
        java.util.Arrays.sort(sorted);
        return sorted;
    }
}
