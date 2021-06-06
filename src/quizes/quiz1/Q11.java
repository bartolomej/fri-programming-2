package quizes.quiz1;

public class Q11 {

    public static void main(String[] args) {
        System.out.println(pretvoriVDesetisko("101010", 2));
        System.out.println(pretvoriVDesetisko("FF", 16));
        System.out.println(pretvoriVDesetisko("101021010", 2)); // pretvorba ni mozna
        System.out.println(pretvoriVDesetisko("GBBA", 16));
    }

    static String pretvoriVDesetisko(String in, int numberSystem) {
        try {
            return String.format("%s(%d)=%s(10)", in, numberSystem, Integer.parseInt(in, numberSystem));
        } catch (java.lang.NumberFormatException e) {
            String n = "";
            for (int i = 0; i < in.length(); i++) {
                char c = in.charAt(i);
                if (c >= 65) {
                    int diff = c - 'A';
                    if (10 + diff >= numberSystem) {
                        n = c + "";
                    }
                } else {
                    if (Integer.parseInt("" + c) >= numberSystem) {
                        n = "" + Integer.parseInt("" + c);
                    }
                }
            }
            return String.format("Napaka pri pretvorbi sistema - števka %s", n);
        }
    }
}
