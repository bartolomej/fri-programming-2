import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DN07 {

    /**
     * Napiši program DN07.java, ki izpiše n največjih števil, ki so zapisana v datoteki.
     * Ime datoteke s števili je podano kot prvi argument ob klicu programa.
     * V datoteki je največ 100 naravnih števil, zapisana so eden za drugim in ločena s presledkom.
     * Število n je podano kot drugi argument.
     *
     * Primer: če datoteka "stevila.txt" vsebuje števila 3 5 1 8 6 5 3 7 1 15 9, potem naj klic
     *
     * java DN07 stevila.txt 3
     *
     * izpiše
     *
     * 15
     * 9
     * 8
     */

    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        int total = Integer.parseInt(args[1]);
        int[] max = new int[total];
        ArrayList<Integer> data = readNumbers(filename);
        for (int n : data) {
            int c = n;
            int d = n;
            for (int i = max.length - 1; i >= 0; i--) {
                c = d;
                if (c > max[i]) {
                    d = max[i];
                    max[i] = c;
                }
            }
        }
        for (int i = max.length - 1; i >= 0; i--) {
            System.out.println(max[i]);
        }
    }

    static ArrayList<Integer> readNumbers(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner myReader = new Scanner(file);
        ArrayList<Integer> numbers = new ArrayList<>();
        while (myReader.hasNextInt()) {
            numbers.add(myReader.nextInt());
        }
        myReader.close();
        return numbers;
    }

}
