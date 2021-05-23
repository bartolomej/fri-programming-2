import java.io.*;
import java.util.*;

public class DN11 {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            // run test cases
            test();
            return;
        }
        Kataster k = new Kataster();
        switch (args[0]) {
            case "mejniki": {
                k.importMejniki(args[1]);
                k.printMejniki();
                break;
            }
            case "razdalja": {
                k.importMejniki(args[1]);
                k.printDistanceBetween(args[2], args[3]);
                break;
            }
            case "parcele": {
                k.importMejniki(args[1]);
                k.importParcele(args[2]);
                k.printParcele();
            }
            case "bin": {
                k.importLegacyFile(args[1]);
                k.printMejniki();
                break;
            }
            default: {
                System.out.println("Command not found!");
            }
        }
    }

    private static void test() throws Exception {
        Mejnik m = new Mejnik("test", 46.050389f, 14.468778f);
        System.out.println(m);

        Kataster k = new Kataster();
        k.importMejniki("./src/primer_mejniki.txt");
        k.printMejniki();
        k.printDistanceBetween("m01", "m02");
        k.importParcele("./src/primer_parcele.txt");
        k.printParcele();

        k.printParcelWithShortestFence();

        Kataster l = new Kataster();
        l.importLegacyFile("./src/primer_mejniki.bin");
        l.printMejniki();
        l.importParcele("./src/primer_parcele.txt");

        l.printLargestNeighborParcel("Butale", "113/11");
    }
}

class Mejnik implements Comparable<Mejnik>, Cloneable {

    private String name;
    // zemljepisna sirina
    private float latitude;
    // zemljepisna dolzina
    private float longitude;

    public Mejnik(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Mejnik(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = decodeGeoPointPart(latitude, false);
        this.longitude = decodeGeoPointPart(longitude, true);
    }

    private static float decodeGeoPointPart(String value, boolean isLongitude) {
        int degrees = Integer.parseInt(value.substring(0, 2));
        int minutes = Integer.parseInt(value.substring(3, 5));
        double seconds = Double.parseDouble(value.substring(6, 10));
        return (float) (degrees + (float)(minutes) / 60 + seconds / 3600);
    }

    private static String encodeGeoPointPart(float value, boolean isLongitude) {
        int degrees = (int) value;
        int minutes = (int)((value - degrees) * 60);
        float seconds = (value - degrees - (float)minutes / 60) * 3600;
        String postfix = "";
        if (value != 0) {
            postfix = isLongitude
                    ? value < 0 ? "S" : "N"
                    : value < 0 ? "W" : "E";
        }
        return String.format("%d*%02d'%04.1f\"%s", degrees, minutes, seconds, postfix);
    }

    public String toString() {
        return String.format("Mejnik %s (%s %s)", this.name, Mejnik.encodeGeoPointPart(this.latitude, false), Mejnik.encodeGeoPointPart(this.longitude, false));
    }

    public String getName() {
        return name;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public static double distance(Mejnik a, Mejnik b) {
        float dLatitude = a.getLatitude() - b.getLatitude();
        float dLongitude = a.getLongitude() - b.getLongitude();
        float dx = dLatitude * 60 * 1825;
        float dy = dLongitude * 60 * 1290;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public static double triangleArea(Mejnik m1, Mejnik m2, Mejnik m3) {
        double a = Mejnik.distance(m1, m2);
        double b = Mejnik.distance(m2, m3);
        double c = Mejnik.distance(m3, m1);
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public Mejnik clone() {
        return new Mejnik(this.name, this.latitude, this.longitude);
    }

    @Override
    public int compareTo(Mejnik m) {
        return this.getName().compareTo(m.getName());
    }

    public boolean equals(Mejnik m) {
        return this.name.equals(m.name) && this.latitude == m.latitude && this.longitude == m.longitude;
    }
}

class Kataster {
    ArrayList<Mejnik> mejniki;
    ArrayList<Parcela> parcele;

    public Kataster () {
        mejniki = new ArrayList<>();
        parcele = new ArrayList<>();
    }

    private Mejnik find(String name) throws Exception {
        for (Mejnik m : mejniki) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        throw new Exception("NAPAKA: mejnika ni v katastru.");
    }

    private Parcela find(String katastrskaObcina, String stevilka) throws Exception {
        for (Parcela p : parcele) {
            if (p.getStevilka().equals(stevilka) && p.getKatastrskaObcina().equals(katastrskaObcina)) {
                return p;
            }
        }
        throw new Exception("NAPAKA: podane parcele ni v katastru.");
    }

    private boolean exists(String name) {
        for (Mejnik m : mejniki) {
            if (name.equals(m.getName())) {
                return true;
            }
        }
        return false;
    }

    public void importMejniki(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(" ");
            if (this.exists(parts[0])) {
                continue;
            }
            Mejnik m = new Mejnik(parts[0], Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
            mejniki.add(m);
        }
    }

    public void importParcele(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(" ");
            ArrayList<Mejnik> m = new ArrayList<>();
            m.add(this.find(parts[3]));
            m.add(this.find(parts[4]));
            m.add(this.find(parts[5]));
            m.add(this.find(parts[6]));
            if (parts[0].equals("STAVBA")) {
                parcele.add(new StavbnaParcela(Double.parseDouble(parts[7]), parts[2], parts[1], m));
            } else {
                parcele.add(new KmetijskaParcela(parts[0], parts[2], parts[1], m));
            }
        }
    }

    public void printMejniki() {
        System.out.println("Kataster zajema naslednje mejnike:");
        ArrayList<Mejnik> clonedList = new ArrayList<>();
        for (Mejnik m : mejniki) clonedList.add(m.clone());
        Collections.sort(clonedList);
        for (Mejnik m : clonedList) System.out.println(m);
    }

    public void printParcele() {
        System.out.println("Kataster zajema naslednje parcele:");
        for (Parcela p : parcele) System.out.println(p);
    }

    public void printDistanceBetween(String mejnik1, String mejnik2) throws Exception {
        double d = Mejnik.distance(this.find(mejnik1), this.find(mejnik2));
        System.out.printf("Razdalja med %s in %s je %.1f metrov.\n", mejnik1, mejnik2, d);
    }

    public void printParcelWithShortestFence() {
        Parcela smallest = parcele.get(0);
        for (Parcela p : parcele) {
            if (p.obseg() < smallest.obseg()) {
                smallest = p;
            }
        }
        System.out.printf("Parcela z najkrajso mejo (obseg %.2f m; povrsina %.2f m2) je:\n   %s\n", smallest.obseg(), smallest.povrsina(), smallest);
    }

    public void importLegacyFile(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        while (inputStream.available() > 0) {
            byte[] encodedName = inputStream.readNBytes(10);
            byte[] encodedLatitude = inputStream.readNBytes(5);
            byte[] encodedLongitude = inputStream.readNBytes(5);
            mejniki.add(new Mejnik(decodeName(encodedName), decodeGeoPointPart(encodedLatitude), decodeGeoPointPart(encodedLongitude)));
        }
    }

    private String decodeName(byte[] encoded) {
        StringBuilder name = new StringBuilder();
        for (byte b : encoded) {
            name.append((char) b);
        }
        return name.toString().trim();
    }

    private String decodeGeoPointPart(byte[] encoded) {
        int degrees = encoded[0];
        int minutes = encoded[1];
        int tenths = encoded[3];
        double seconds = encoded[2] + (float)(tenths) / 10;
        char c = (char)encoded[4];
        return String.format("%d*%02d'%04.1f\"%C", degrees, minutes, seconds, c);
    }

    public void printLargestNeighborParcel(String katastrskaObcina, String stevilka) throws Exception {
        double max = 0;
        Parcela source = this.find(katastrskaObcina, stevilka);
        Parcela target = null;
        for (Parcela p : this.parcele) {
            if (p.jeSosednja(source) && p.povrsina() > max) {
                target = p;
            }
        }
        if (target == null) {
            throw new Exception("NAPAKA: parcela nima sosednjih parcel.");
        }
        System.out.printf("Najvecja sosednja parcela (povrsine %.2f m2) je:\n  %s", target.povrsina(), target);
    }
}

abstract class Parcela {

    private String stevilka;

    public String getStevilka() {
        return stevilka;
    }

    public String getKatastrskaObcina() {
        return katastrskaObcina;
    }

    private String katastrskaObcina;
    private ArrayList<Mejnik> mejniki;

    public Parcela(String stevilka, String katastrskaObcina, ArrayList<Mejnik> mejniki) {
        this.stevilka = stevilka;
        this.katastrskaObcina = katastrskaObcina;
        this.mejniki = mejniki;
    }

    public abstract double cenaParcele();

    public double povrsina() {
        return Mejnik.triangleArea(this.mejniki.get(0), this.mejniki.get(1), this.mejniki.get(2)) + Mejnik.triangleArea(this.mejniki.get(2), this.mejniki.get(3), this.mejniki.get(0));
    }

    public double obseg() {
        return Mejnik.distance(this.mejniki.get(0), this.mejniki.get(1))
                + Mejnik.distance(this.mejniki.get(1), this.mejniki.get(2))
                + Mejnik.distance(this.mejniki.get(2), this.mejniki.get(3))
                + Mejnik.distance(this.mejniki.get(3), this.mejniki.get(0));
    }

    public boolean jeSosednja(Parcela p) {
        for (Mejnik m : this.mejniki) {
            for (Mejnik n : p.mejniki) {
                if (n.equals(m) && !this.equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder imenaMejnikov = new StringBuilder();
        for (int i = 0; i < this.mejniki.size(); i++) {
            Mejnik m = this.mejniki.get(i);
            imenaMejnikov.append(m.getName());
            if (i < this.mejniki.size() - 1) {
                imenaMejnikov.append(", ");
            }
        }
        return String.format("KO: %s, stevilka: %s, vrednost: %.2f EUR [%s]", this.katastrskaObcina, this.stevilka, this.cenaParcele(), imenaMejnikov);
    }

    public boolean equals(Parcela p) {
        return this.katastrskaObcina.equals(p.katastrskaObcina) && this.stevilka.equals(p.stevilka);
    }
}

class KmetijskaParcela extends Parcela {

    private HashMap<String, Double> cenaMap = new HashMap<>(){{
        put("NJIVA", 3.12);
        put("TRAVNIK", 1.42);
        put("PASNIK", 1.69);
        put("GOZD", 0.57);
        put("DRUGO", 0.15);
    }};

    private String tip;

    public KmetijskaParcela(String tip, String stevilka, String katastrskaObcina, ArrayList<Mejnik> mejniki) {
        super(stevilka, katastrskaObcina, mejniki);
        this.tip = tip;
    }

    @Override
    public double cenaParcele() {
        return this.povrsina() * this.cenaMap.get(this.tip);
    }

}

class StavbnaParcela extends Parcela {

    double povrsinaStavbe;

    public StavbnaParcela(double povrsinaStavbe, String stevilka, String katastrskaObcina, ArrayList<Mejnik> mejniki) {
        super(stevilka, katastrskaObcina, mejniki);
        this.povrsinaStavbe = povrsinaStavbe;
    }

    @Override
    public double cenaParcele() {
        return this.povrsinaStavbe * 523.45 + (this.povrsina() - this.povrsinaStavbe) * 117.65;
    }
}