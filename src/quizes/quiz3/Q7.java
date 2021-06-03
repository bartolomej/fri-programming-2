package quizes.quiz3;

public class Q7 {

    public static void main(String[] args) {
        Matrika a = Matrika.preberiMartiko("src/quizes/quiz3/matrika1.txt");
        Matrika b = Matrika.preberiMartiko("src/quizes/quiz3/matrika2.txt");
        a.izpisi();
        System.out.println();
        b.izpisi();
        System.out.println();
        Matrika c = b.zmnozi(a);
        c.izpisi();
    }
}


class Matrika {

    int[][] matrix;

    public Matrika(int x, int y) {
        this.matrix = new int[x][y];
    }

    int[] row(int index) {
        return this.matrix[index];
    }

    int[] col(int index) {
        int[] col = new int[this.matrix.length];
        for (int i = 0; i < this.matrix.length; i++) {
            col[i] = this.matrix[i][index];
        }
        return col;
    }

    static Matrika preberiMartiko(String imeDatotake) {
        java.util.Scanner sc;
        try {
            sc = new java.util.Scanner(new java.io.File(imeDatotake));
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return new Matrika(0,0);
        }
        int line = 0;
        int size = sc.nextInt();
        sc.nextLine();
        Matrika m = new Matrika(size, size);
        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(" ");
            for (int i = 0; i < parts.length; i++) {
                m.matrix[line][i] = Integer.parseInt(parts[i]);
            }
            line++;
        }
        return m;
    }

    static int dotProduct(int[] a, int[] b) {
        int out = 0;
        for (int i = 0; i < a.length; i++) {
            out += a[i] * b[i];
        }
        return out;
    }

    public Matrika zmnozi(Matrika b) {
        Matrika m = new Matrika(this.matrix.length, this.matrix.length);
        for (int i = 0; i < m.matrix.length; i++) {
            for (int j = 0; j < m.matrix[i].length; j++) {
                m.matrix[i][j] = Matrika.dotProduct(this.row(i), b.col(j));
            }
        }
        return m;
    }

    public void izpisi() {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                System.out.printf("%3d", this.matrix[i][j]);
            }
            System.out.println();
        }
    }

}