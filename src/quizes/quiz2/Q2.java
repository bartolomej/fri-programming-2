package quizes.quiz2;

import common.Utils;

public class Q2 {

    public static void main(String[] args) {
        Utils.print(presek(new int[]{3,6,9,1,3,5}, new int[]{3,4,6,1,3,8}));
    }

    static int[] presek(int[] tabela1, int[] tabela2) {
        int size = Math.min(tabela1.length, tabela2.length);
        int[] temp = new int[size];
        int count = 0;
        for (int j : tabela1) {
            if (contains(tabela2, j) && !contains(temp, j)) {
                temp[count] = j;
                count++;
            }
        }
        int[] res = new int[count];
        System.arraycopy(temp, 0, res, 0, res.length);
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
