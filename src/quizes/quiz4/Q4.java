package quizes.quiz4;


public class Q4 {

    public static void main(String[] args) {
        preberiRacunInIzpisi("src/quizes/quiz4/racun.txt");
    }

    static void preberiRacunInIzpisi(String imeDatoteke) {
        String contents;
        try {
            contents = readFile(imeDatoteke);
        } catch (java.io.IOException e) {
            System.out.println("Napaka pri branju datoteke!");
            return;
        }
        double totalDdv = 0;
        double totalZnesek = 0;
        String[] lines = contents.split("\n");
        for (String line : lines) {
            String[] parts = line.split("\t");
            if (parts.length == 3) {
                double ddv = parsePrice(parts[1]);
                double znesek = parsePrice(parts[2]);
                totalDdv += ddv;
                totalZnesek += znesek;
            }
        }
        System.out.printf("Skupaj brez DDV:  %2.2f\n", totalZnesek - totalDdv);
        System.out.printf("DDV:               %2.2f\n", totalDdv);
        System.out.printf("ZNESEK SKUPAJ:    %2.2f\n", totalZnesek);
    }

    static double parsePrice(String value) {
        return Double.parseDouble(value.replace(',', '.'));
    }

    static String readFile(String filename) throws java.io.IOException {
        java.io.File file = new java.io.File(filename);
        java.io.FileInputStream fis = new java.io.FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return new String(data, java.nio.charset.StandardCharsets.UTF_8);
    }
}
