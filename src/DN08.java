import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class DN08 {

    private static final String[] tipiParcel = {"TRAVNATA POVRSINA", "GOZDNA POVRSINA", "OBDELOVALNA POVRSINA", "BIVALNO POSLOPJE", "INDUSTRIJSKO POSLOPJE"};
    private static final double[] vrednostiTipovParcel = {500.0, 750.0, 1000.0, 10000.0, 50000.0};

    static char[] heightMap = new char[]{
            ' ',
            '.',
            ':',
            '-',
            '=',
            '+',
            '*',
            '#',
            '%',
            '@'
    };

    public static void main(String[] args) throws FileNotFoundException {
        int[][] teren = preberiTeren("src/teren.txt");
        izrisTerena(teren);
        int[] visine = prestejVisine(teren);
        for (int i = 0; i < visine.length; i++) {
            System.out.printf("Visina %d: %dx\n", i, visine[i]);
        }
        System.out.printf("Povprecna visina: %.2f\n", povprecnaVisina(teren));
        int[][] tipiParcel = preberiTipParcel("src/kategorije_parcel.txt");
        boolean[][] visinskaPoplava = visinskaPoplava(teren, 2.1);
        izrisiPoplavo(teren, visinskaPoplava);
        porociloSkode(teren, tipiParcel, visinskaPoplava);
        boolean[][] kolicinskaPoplava = kolicinskaPoplava(teren, 100);
        izrisiPoplavo(teren, kolicinskaPoplava);
    }

    // NALOGA 1

    public static void izrisTerena(int[][] teren) {
       for (int[] line : teren) {
           for (int height : line) {
               System.out.print(heightMap[height]);
           }
           System.out.println();
       }
    }

    public static double povprecnaVisina(int[][] teren) {
        double sum = 0;
        for (int[] line : teren) {
            for (int height : line) {
                sum += height;
            }
        }
        return sum / (teren.length * teren[0].length);
    }

    public static int[] prestejVisine(int[][] teren) {
        int[] statistika = new int[heightMap.length];
        for (int[] line : teren) {
            for (int height : line) {
                statistika[height]++;
            }
        }
        return statistika;
    }

    public static int[][] preberiTeren(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int nRows = scanner.nextInt();
        int nCols = scanner.nextInt();
        scanner.nextLine();
        int[][] table = new int[nRows][nCols];
        int row = 0;
        int col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNextInt()) {
                table[row][col] = lineScanner.nextInt();
                col++;
            }
            row++;
            col = 0;
        }
        return table;
    }


    // NALOGA 2

    public static int[][] preberiTipParcel(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int nRows = scanner.nextInt();
        int nCols = scanner.nextInt();
        scanner.nextLine();
        int[][] table = new int[nRows][nCols];
        int row = 0;
        int col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                table[row][col] = indexOfParcelType(c);
                col++;

            }
            row++;
            col = 0;
        }
        return table;
    }

    private static int indexOfParcelType(char c) {
        int index = -1;
        for (int i = 0; i < tipiParcel.length; i++) {
            if (tipiParcel[i].charAt(0) == c) {
                index = i;
            }
        }
        return index;
    }

    public static boolean[][] visinskaPoplava(int[][] teren, double visinaPoplave) {
        boolean[][] poplava = new boolean[teren.length][teren[0].length];
        for (int i = 0; i < teren.length; i ++) {
            for (int j = 0; j < teren[i].length; j++) {
                poplava[i][j] = teren[i][j] < visinaPoplave;
            }
        }
        return poplava;
    }

    public static void izrisiPoplavo(int[][] teren, boolean[][] poplava) {
        for (int i = 0; i < teren.length; i++) {
            for (int j = 0; j < teren[i].length; j++) {
                System.out.print(poplava[i][j] ? '~' : heightMap[teren[i][j]]);
            }
            System.out.println();
        }
    }

    public static void porociloSkode(int[][] teren, int[][] tipParcele, boolean[][] poplava) {
        int[] count = new int[tipiParcel.length];
        for (int i = 0; i < teren.length; i++) {
            for (int j = 0; j < teren[i].length; j++) {
                if (poplava[i][j]) {
                    count[tipParcele[i][j]]++;
                }
            }
        }
        System.out.format("%21s%11s%21s\n", "Tip parcele", "Število", "Ocenjena škoda");
        System.out.println("-".repeat(53));
        double skodaSum = 0;
        int countSum = 0;
        for (int i = 0; i < tipiParcel.length; i++) {
            double skoda = count[i] * vrednostiTipovParcel[i];
            skodaSum += skoda;
            countSum += count[i];
            System.out.format("%21s%11s%21s\n",tipiParcel[i], count[i], formatMoney(skoda));
        }
        System.out.println("-".repeat(53));
        System.out.format("%21s%11s%21s\n", "SKUPAJ", countSum, formatMoney(skodaSum));

    }

    private static String formatMoney(double value) {
        Locale locale = new Locale("sl", "SI");
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
        DecimalFormatSymbols symbol = new DecimalFormatSymbols(locale);
        symbol.setCurrencySymbol("EUR");
        formatter.setDecimalFormatSymbols(symbol);
        return formatter.format(value);
    }


    // NALOGA 3

    public static boolean[][] kolicinskaPoplava(int[][] teren, double kolicina) {
        int[] visine = prestejVisine(teren);
        int visineSum1 = 0;
        int visineSum2 = 0;
        double dosezenaVisina = 0;
        for (int i = 0; i < visine.length; i++) {
            visineSum1 = visineSum2;
            for (int j = i; j >= 0; j--) {
                visineSum2 += visine[j];
            }
            if (visineSum2 > kolicina) {
                dosezenaVisina = i + (kolicina - visineSum1) / visineSum2;
                break;
            }
        }
        return visinskaPoplava(teren, dosezenaVisina);
    }

}
