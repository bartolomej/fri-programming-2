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
        String method = args.length > 0 ? args[0] : "example";
        switch (method) {
            case "example" -> runExample();
            case "analiza" -> runAnalizo(args[1]);
            case "izrisi_poplavo" -> runIzrisTerena(args[1], args[2], Double.parseDouble(args[3]));
            case "porocilo_skode" -> runPorociloSkode(args[1], args[2], args[3], Double.parseDouble(args[4]));
            case "nacrt_pobega" -> runNacrtPobega(args[1], args[2], args[3], Double.parseDouble(args[4]));
        }
    }

    private static void runExample() throws FileNotFoundException {
        String terenFile = "src/teren.txt";
        String tipiFile = "src/kategorije_parcel.txt";

        printSectionTitle("ANALIZA");
        runAnalizo(terenFile);

        printSectionTitle("IZRIS TERENA - visinska poplava");
        runIzrisTerena("visinska", terenFile, 2.1);

        printSectionTitle("IZRIS TERENA - kolicinska poplava");
        runIzrisTerena("kolicinska", terenFile, 100);

        printSectionTitle("POROCILO SKODE - visinska poplava");
        runPorociloSkode("visinska", terenFile, tipiFile, 2.1);

        printSectionTitle("POROCILO SKODE - kolicinska poplava");
        runPorociloSkode("kolicinska", terenFile, tipiFile, 100);

        printSectionTitle("NACRT POBEGA - visinska poplava");
        runNacrtPobega("visinska", terenFile, tipiFile, 2.1);

        printSectionTitle("NACRT POBEGA - kolicinska poplava");
        runNacrtPobega("kolicinska", terenFile, tipiFile, 100);
    }

    private static void printSectionTitle(String title) {
        System.out.printf("\n\n\n%s %s %s\n\n", "-".repeat(6), title, "-".repeat(6));
    }

    private static void runAnalizo(String inputFile) throws FileNotFoundException {
        int[][] teren = preberiTeren(inputFile);
        izrisTerena(teren);
        int[] visine = prestejVisine(teren);
        for (int i = 0; i < visine.length; i++) {
            System.out.printf("Visina %d: %dx\n", i, visine[i]);
        }
        System.out.printf("Povprecna visina: %.2f\n", povprecnaVisina(teren));
    }

    private static void runIzrisTerena(String tipPoplave, String terenFile, double kolicina) throws FileNotFoundException {
        int[][] teren = preberiTeren(terenFile);
        switch (tipPoplave) {
            case "visinska" -> izrisiPoplavo(teren, visinskaPoplava(teren, kolicina));
            case "kolicinska" -> izrisiPoplavo(teren, kolicinskaPoplava(teren, kolicina));
        }
    }

    private static void runPorociloSkode(String tipPoplave, String terenFile, String tipiFile, double kolicina) throws FileNotFoundException {
        int[][] teren = preberiTeren(terenFile);
        switch (tipPoplave) {
            case "visinska" -> porociloSkode(teren, preberiTipParcel(tipiFile), visinskaPoplava(teren, kolicina));
            case "kolicinska" -> porociloSkode(teren, preberiTipParcel(tipiFile), kolicinskaPoplava(teren, kolicina));
        }
    }

    private static void runNacrtPobega(String tipPoplave, String terenFile, String tipiFile, double kolicina) throws FileNotFoundException {
        int[][] teren = preberiTeren(terenFile);
        switch (tipPoplave) {
            case "visinska" -> nacrtPobega(teren, preberiTipParcel(tipiFile), visinskaPoplava(teren, kolicina));
            case "kolicinska" -> nacrtPobega(teren, preberiTipParcel(tipiFile), kolicinskaPoplava(teren, kolicina));
        }
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
        for (int i = 0; i < teren.length; i++) {
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
            System.out.format("%21s%11s%21s\n", tipiParcel[i], count[i], formatMoney(skoda));
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


    // NALOGA 4

    public static void nacrtPobega(int[][] teren, int[][] tipParcele, boolean[][] poplava) {
        int nSafe = 0;
        int nCanEscape = 0;
        int nNeedHelp = 0;
        for (int i = 0; i < teren.length; i++) {
            for (int j = 0; j < teren[i].length; j++) {
                if (tipParcele[i][j] != 3) {
                    // preskoci parcelo, ce ni tipa "BIVALNO POSLOPJE"
                    continue;
                }
                if (poplava[i][j]) {
                    if (canEscape(teren, poplava, i, j)) {
                        nCanEscape++;
                    } else {
                        nNeedHelp++;
                    }
                } else {
                    nSafe++;
                }
            }
        }
        System.out.printf("Varne hise: %d\n", nSafe);
        System.out.printf("Lahko pobegnejo: %d\n", nCanEscape);
        System.out.printf("Potrebujejo pomoc: %d\n", nNeedHelp);
    }

    private static boolean canEscape(int[][] teren, boolean[][] poplava, int x, int y) {
        int i = x, j = y;
        while (true) {
            int maxHeight = teren[i][j];
            int current;
            int direction = 0;
            // Zahod
            current = j > 0 ? teren[i][j - 1] : 0;
            if (current > maxHeight) {
                maxHeight = current;
                direction = 4;
            }
            // Jug
            current = i < teren.length - 1 ? teren[i + 1][j] : 0;
            if (current > maxHeight) {
                maxHeight = current;
                direction = 3;

            }
            // Vzhod
            current = j < teren[i].length - 1 ? teren[i][j + 1] : 0;
            if (current > maxHeight) {
                maxHeight = current;
                direction = 2;
            }
            // Sever
            current = i > 0 ? teren[i - 1][j] : 0;
            if (current > maxHeight) {
                direction = 1;
            }
            // noben pogoj se ni izvedel => noben sosed ni visji od trenutnega
            if (direction == 0) {
                return !poplava[i][j];
            } else {
                // premakni se v zahtevano smer
                switch (direction) {
                    case 1 -> i--;
                    case 2 -> j++;
                    case 3 -> i++;
                    case 4 -> j--;
                }
            }
        }
    }

}
