package quizes.quiz2;

import common.Utils;

public class Q4 {

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6};
        rotateRight(array, 2 + array.length);
        Utils.print(array);
    }

    static void rotateRight(int[] tabela, int k) {
        int r = k % tabela.length;
        int[] temp = new int[r];
        int divIndex = tabela.length - 1 - r;
        System.arraycopy(tabela, 0, temp, 0, r);
        System.arraycopy(tabela, r, tabela, 0, tabela.length - r);
        System.arraycopy(temp, 0, tabela, divIndex + 1, r);
    }

    static void rotateLeft(int[] tabela, int k) {
        int r = k % tabela.length;
        int[] temp = new int[r];
        System.arraycopy(tabela, tabela.length - r, temp, 0, r);
        System.arraycopy(tabela, 0, tabela, r, tabela.length - r - 1 + 1);
        System.arraycopy(temp,0, tabela, 0, r);
    }
}
