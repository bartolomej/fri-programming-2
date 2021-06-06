package quizes.quiz4;

public class Q7 {

    public static void main(String[] args) {
        izpisiBesedilo("src/quizes/quiz4/poravnava1.txt", 20, 30);
    }

    static void izpisiBesedilo(String imeDatoteke, int n, int s) {
        java.util.Scanner sc;
        try {
            sc = new java.util.Scanner(new java.io.File(imeDatoteke));
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String lastWord;
        java.util.ArrayList<String> lines = new java.util.ArrayList<>();
        String line = "";
        while (sc.hasNext()) {
            lastWord = sc.next();
            if (line.length() + lastWord.length() + 1 <= n) {
                line += (line.length() == 0 ? "" : " ") + lastWord;
            } else {
                lines.add(line);
                line = lastWord;
            }
        }
        // add the last line that wasn't added because there is no new words to read
        lines.add(line);

        for (String l : lines) {
            System.out.println(padLine(l, s));
        }
    }

    static String padLine(String line, int length) {
        int diff = length - line.length();
        int leftDots = (int)Math.floor((double)diff / 2);
        int rightDots = (int)Math.ceil((double)diff / 2);
        return String.format("%s%s%s", ".".repeat(leftDots), line, ".".repeat(rightDots));
    }
}
