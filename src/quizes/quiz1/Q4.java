package quizes.quiz1;

public class Q4 {

    public static void main(String[] args) {
        krog(7.5, 3);
        krog(7.5, 5);
    }

    static void krog(double r, int d) {
        if (r < 0) {
            System.out.println("Napaka: negativen polmer");
            return;
        }
        if (d < 0) {
            System.out.println("Napaka: negativen d");
            return;
        }
        double ob = 2 * Math.PI * r;
        double p = Math.PI * Math.pow(r, 2);
        System.out.printf("Obseg kroga s polmerom r=%.2f je %." + d +"f\n", r, ob);
        System.out.printf("Ploscina kroga s polmerom r=%.2f je %." + d +"f\n", r, p);
    }
}
