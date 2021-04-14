import java.io.File;
import java.io.FileNotFoundException;
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
    }

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

}
