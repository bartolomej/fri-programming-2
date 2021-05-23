import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DN10 {

    public static void main(String[] args) throws FileNotFoundException {
        String filepath = args.length > 0 ? args[0] : "src/liki.txt";
        ArrayList<Lik> liki = preberi(filepath);
        System.out.printf("%.2f", ploscina(liki));
    }

    private static ArrayList<Lik> preberi(String filepath) throws FileNotFoundException {
        ArrayList<Lik> liki = new ArrayList<>();
        Scanner sc = new Scanner(new File(filepath));
        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(":");
            switch (parts[0]) {
                case "kvadrat": {
                    liki.add(new Kvadrat(Integer.parseInt(parts[1])));
                    break;
                }
                case "pravokotnik": {
                    liki.add(new Pravokotnik(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
                    break;
                }
                case "krog": {
                    liki.add(new Krog(Integer.parseInt(parts[1])));
                    break;
                }
            }
        }
        return liki;
    }

    private static double ploscina(ArrayList<Lik> liki) {
        double sum = 0;
        for (Lik lik : liki) {
            sum += lik.ploscina();
        }
        return sum;
    }
}

interface Lik {
    public double ploscina();
}

class Krog implements Lik {
    private final int r;

    public Krog(int r) {
        this.r = r;
    }

    public double ploscina() {
        return Math.PI * Math.pow(this.r, 2);
    }
}

class Pravokotnik implements Lik {
    private final int b;
    private final int a;

    public Pravokotnik (int a, int b) {
        this.a = a;
        this.b = b;
    }

    public double ploscina() {
        return this.a * this.b;
    }
}

class Kvadrat extends Pravokotnik {

    public Kvadrat(int a) {
        super(a, a);
    }
}