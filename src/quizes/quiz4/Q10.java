package quizes.quiz4;

public class Q10 {

    public static void main(String[] args) {
        izpisi(new int[]{129, 129, 129, 255, 129, 129, 129, 129});
        izpisi(new int[]{24, 24, 36, 36, 126, 66, 66, 129});
        izpisi(new int[]{255, 129, 129, 129, 129, 129, 129, 255});
        izpisi(new int[]{255, 255, 24, 24, 24, 24, 24, 24});
    }

    static void izpisi(int[] character) {
        for (int c : character) {
            for (int i = 0; i < 8; i++) {
                int f = c & (int) Math.pow(2, i);
                System.out.print(f > 0 ? '*' : ' ');
            }
            System.out.println();
        }
    }
}
