package exam;

import java.util.Arrays;

public class Naloga21 {

    public static void main(String[] args) {
        char[] zabojcek = args[0].toCharArray();

        System.out.printf("%s --> %s", Arrays.toString(zabojcek),
                Arrays.toString(sipanje(zabojcek)));
    }

    static char[] sipanje(char[] zaboj) {
        char curr;
        char next;
        // ponovi tolikokrat da se bodo zagotovo vsi prekati s peskom premaknili do zelene tocke
        for (int j = 0; j < zaboj.length; j++) {
            for (int i = zaboj.length - 1; i > 0; i--) {
                curr = zaboj[i];
                next = zaboj[i - 1];
                if (curr == 'P' && next == '-') {
                    zaboj[i - 1] = curr;
                    zaboj[i] = next;
                }
            }
        }
        return zaboj;
    }
}
