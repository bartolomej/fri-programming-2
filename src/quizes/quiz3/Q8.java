package quizes.quiz3;

public class Q8 {

    public static void main(String[] args) {
        Kompleksno a = new Kompleksno("a", 3.56, -2.7);
        System.out.println(a);
        Kompleksno b = new Kompleksno("b", -5, 12.33);
        System.out.println(b);
        a.pristej(b);
        System.out.println(a);
        b.pomnozi(new Kompleksno(1.3, 3.22));
        System.out.println(b);
        System.out.printf("Velikost %s je %f\n", a, a.velikost());
    }
}

class Kompleksno {

    private String name;
    private double x;
    private double y;

    public Kompleksno(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Kompleksno(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double velikost() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public void pristej(Kompleksno b) {
        this.x += b.x;
        this.y += b.y;
    }

    public void pomnozi(Kompleksno k) {
        double newX = (this.x * k.x - this.y * k.y);
        double newY = (this.x * k.y + this.y * k.x);
        this.x = newX;
        this.y = newY;
    }

    public String toString() {
        return String.format("%s = (%.3f + %.3f*i)", this.name, this.x, this.y);
    }

}
