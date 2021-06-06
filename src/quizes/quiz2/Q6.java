package quizes.quiz2;

public class Q6 {

    public static void main(String[] args) {
        System.out.println(koren(2, 5));
        System.out.println(Math.sqrt(2));
    }

    static double koren(int x, int d) {
        double c = 1;
        int index = 0;
        while (index <= d) {
            double p = Math.pow(10, -index);
            while (true) {
                if (c * c <= x && (c + p) * (c + p) >= x) {
                    break;
                }
                c += p;
            }
            index++;
        }
        return c;
    }
}
