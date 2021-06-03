package quizes.quiz3;

public class Q11 {

    public static void main(String[] args) {
        preveriInNarisi(new int[]{1,0,3,2});
    }

    static void preveriInNarisi(int[] kraljice) {
        boolean napadajo = false;
        for (int i = 0; i < kraljice.length; i++) {
            napadajo = napadajo || napada(i, kraljice);
            for (int j = 0; j < kraljice.length; j++) {
                if (kraljice[j] == i) {
                    System.out.print("K ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        if (napadajo) {
            System.out.println("Kraljice se napadajo");
        } else {
            System.out.println("Kraljice se ne napadajo");
        }
    }

    static boolean napada(int index, int[] kraljice) {
        for (int i = 0; i < kraljice.length; i++) {
            if (i != index) {
                if (kraljice[i] == kraljice[index]) {
                    return true;
                }
                int diffIndex = i - index;
                if (kraljice[i] == kraljice[index] + diffIndex || kraljice[i] == kraljice[index] - diffIndex) {
                    return true;
                }
            }
        }
        return false;
    }
}
