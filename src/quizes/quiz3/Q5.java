package quizes.quiz3;


public class Q5 {

    public static void main(String[] args) throws java.io.FileNotFoundException {
        Tocka[] t1 = Tocka.preberiTocke("src/quizes/quiz3/tocke.txt");
        Tocka[] t2 = Tocka.preberiTocke("src/quizes/quiz3/tocke1.txt");
        System.out.println(Tocka.tabelaToString(t1));
        System.out.println(Tocka.tabelaToString(t2));
        Tocka.najblizji(t1, t2);
    }
}

class Tocka  {

    public int x;
    public int y;

    public Tocka(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Tocka[] preberiTocke(String filename)  {
        java.util.ArrayList<Tocka> tocke = new java.util.ArrayList<>();
        java.util.Scanner sc = null;
        try {
            sc = new java.util.Scanner(new java.io.File(filename));
        } catch (java.io.FileNotFoundException e) {
            return tocke.toArray(new Tocka[0]);
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            if (parts.length < 2) {
                continue;
            }
            tocke.add(new Tocka(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        return tocke.toArray(new Tocka[0]);
    }

    static String tabelaToString(Tocka[] tocke) {
        String out = "[";
        for (int i = 0; i < tocke.length; i++) {
            out += tocke[i];
            if (i < tocke.length - 1) {
                out += ", ";
            }
        }
        out += "]";
        return out;
    }

    // Q6 (nadaljevanje Q5)
    static void najblizji(Tocka[] t1, Tocka[] t2) {
        if (t1.length == 0) {
            System.out.println("Prva tabela ne vsebuje točk");
            return;
        }
        if (t2.length == 0) {
            System.out.println("Druga tabela ne vsebuje točk");
            return;
        }
        Tocka k1 = t1[0];
        Tocka k2 = t2[0];
        for (int i = 0; i < t1.length; i++) {
            for (int j = 0; j < t2.length; j++) {
                if (distance(t1[i], t2[j]) < distance(k1, k2)) {
                    k1 = t1[i];
                    k2 = t2[j];
                }
            }
        }
        System.out.printf("Najbližji točki sta Tocka %s in Tocka %s, razdalja med njima je %.2f", k1, k2, distance(k1, k2));
    }

    static double distance(Tocka t1, Tocka t2) {
        return Math.sqrt(Math.pow(t1.x - t2.x, 2) + Math.pow(t1.y - t2.y, 2));
    }

    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }
}