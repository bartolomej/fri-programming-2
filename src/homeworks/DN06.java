package homeworks;

import java.util.HashSet;
import java.util.Set;

public class DN06 {

    public static void main(String[] args) throws Exception {
        if (!isPerfectSquare(args.length)) {
            System.out.println("Napacno stevilo argumentov.");
            return;
        }
        int size = (int)(Math.sqrt(args.length));
        int[][] sudoku = new int[size][size];
        int targetRow = 0;
        int targetCol = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int n = Integer.parseInt(args[count]);
                count += 1;
                sudoku[i][j] = n;
                if (n == 0) {
                    targetRow = i;
                    targetCol = j;
                }
            }
        }
        Set<Integer> numberSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            numberSet.add(sudoku[targetRow][i]);
            numberSet.add(sudoku[i][targetCol]);
        }
        for (int i = 1; i < size + 1; i++) {
            if (!numberSet.contains(i)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println("Naloge se ne da resiti.");
    }

    static boolean isPerfectSquare(int n) {
        double sqr = Math.sqrt(n);
        return (sqr - Math.floor(sqr)) == 0;
    }
}
