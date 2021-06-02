package quizes.quiz2;


import common.Utils;

public class Q1 {

    public static void main(String[] args) {
        Utils.print(unija(new int[]{1,2,3}, new int[]{3,4,5}));
    }

    static int[] unija(int[] tabela1, int[] tabela2) {
        int[] sum = new int[tabela1.length + tabela2.length];
        for (int i = 0; i < tabela1.length; i++) {
            sum[i] = tabela1[i];
        }
        for (int i = 0; i < tabela2.length; i++) {
            sum[tabela1.length + i] = tabela2[i];
        }
        return sum;
    }
}
