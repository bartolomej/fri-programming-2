package quizes.quiz4;


public class Q3 {

    public static void main(String[] args) {
        try {
            statistikaStavkov("src/quizes/quiz4/besedilo1.txt");
        } catch (IzjemaManjkajocegaLocila izjemaManjkajocegaLocila) {
            izjemaManjkajocegaLocila.printStackTrace();
        }
    }

    static void statistikaStavkov(String filename) throws IzjemaManjkajocegaLocila {
        java.util.Scanner sc;
        try {
            sc = new java.util.Scanner(new java.io.File(filename));
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        java.util.Map<Integer, Integer> countMap = new java.util.HashMap<>();
        int count = 0;
        while (sc.hasNext()) {
            String word = sc.next();
            count++;
            if (word.endsWith(".") || word.endsWith("!") || word.endsWith("?")) {
                if (countMap.containsKey(count)) {
                    countMap.put(count, countMap.get(count) + 1);
                } else {
                    countMap.put(count, 1);
                }
                count = 0;
            }
        }
        java.util.List<Integer> array = new java.util.ArrayList<>(countMap.keySet());
        java.util.Collections.sort(array);
        for (Integer i : array) {
            System.out.printf("Stavki dolzine %d se pojavijo: %dx.\n", i, countMap.get(i));
        }
    }

    class IzjemaManjkajocegaLocila extends Exception { }
}