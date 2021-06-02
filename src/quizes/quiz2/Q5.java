package quizes.quiz2;

import common.Utils;

public class Q5 {

    public static void main(String[] args) {
        Utils.print(duplikati(new int[]{1, 5, 8, 56, 3, 9, 1, 43, 1, 2, 56, 12, 1, 3}));
    }

    static int[] duplikati(int[] tabela) {
        int[] temp = new int[tabela.length];
        int count = 0;
        for (int e : tabela) {
            if (!contains(temp, e)) {
                temp[count] = e;
                count++;
            }
        }
        int[] res = new int[count];
        System.arraycopy(temp, 0, res, 0, count);
        return res;
    }

    static boolean contains(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return true;
            }
        }
        return false;
    }
}
