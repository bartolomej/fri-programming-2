package quizes.quiz1;

public class Q14 {

    public static void main(String[] args) {
        narisiDrevo(8);
    }

    static void narisiDrevo(int h) {
        int height = h <= 2 ? h : 2 + (int)Math.ceil((double)(h - 2) / 2);
        if (h == 0) {
            System.out.println(" . ");
        } else {
            for (int i = 0; i < height; i++) {
                if (i < height - 2) {
                    System.out.println(h % 2 == 0 || i != 0 ? "* *" : " * ");
                } else {
                    System.out.println(" | ");
                }
            }
        }
    }
}
