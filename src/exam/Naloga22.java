package exam;

import java.util.ArrayList;
import java.util.Collections;

public class Naloga22 {

    public static void main(String[] args) {
        ArrayList<Datum> datumi = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            datumi.add(new Datum(args[i]));
        }
        Collections.sort(datumi);
        for (Datum datum : datumi) {
            System.out.println(datum);
        }
    }

}

class Datum implements Comparable {

    private int dan;
    private int mesec;
    private int leto;

    private String[] meseci = new String[]{
            "januar",
            "februar",
            "marec",
            "april",
            "maj",
            "junij",
            "julij",
            "avgust",
            "september",
            "oktober",
            "november",
            "december"
    };

    public Datum (String datum) {
        String[] parts = datum.split("\\.");
        this.dan = Integer.parseInt(parts[0]);
        this.mesec = Integer.parseInt(parts[1]);
        this.leto = Integer.parseInt(parts[2]);
    }

    public String toString() {
        return String.format("%d. %s %d", this.dan, this.meseci[this.mesec - 1], this.leto);
    }

    @Override
    public int compareTo(Object o) {
        return this.toNumber() - ((Datum)o).toNumber();
    }

    private int toNumber() {
        return this.leto * (int)Math.pow(10, 4) + this.mesec * (int)Math.pow(10, 2) + this.dan;
    }
}
