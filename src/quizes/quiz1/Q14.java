package quizes.quiz1;

public class Q14 {

    public static void main(String[] args) {
        narisiDrevo(3);
        narisiDrevo(4);
        narisiDrevo(5);
        narisiDrevo(6);
    }

    static void narisiDrevo(int h) {
        if (h == 0) {
            System.out.println(" . ");
        } else {
            for (int i = 0; i < h; i++) {
                if (i < h - 2) {
                    System.out.println(leaves(h - 3 - i, h));
                } else {
                    System.out.println(" | ");
                }
            }
        }
    }

    //TODO: how to calculate this ?
    static String leaves(int i, int h) {
        return h % 2 != 0 && i == 0 ? "* *" : " * ";
    }
}
