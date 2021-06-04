package quizes.quiz4;

public class Q1 {

    public static void main(String[] args) {
        dvojnaNagrada("./src/quizes/quiz4/igralke1.csv", "./src/quizes/quiz4/igralci1.csv");
    }

    static void dvojnaNagrada(String igralkeFilename, String igralciFilename) {
        java.util.Map<String, String> igralkeMap;
        java.util.Map<String, String> igralciMap;
        try {
            igralkeMap = computeMap(igralkeFilename);
            igralciMap = computeMap(igralciFilename);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        java.util.ArrayList<String> res = new java.util.ArrayList<>();
        for (java.util.Map.Entry<String,String> entry : igralciMap.entrySet()) {
            String movie = entry.getKey();
            if (igralkeMap.containsKey(movie)) {
                String[] igralkeParts = igralkeMap.get(movie).split(", ");
                String[] igralciParts = entry.getValue().split(", ");
                res.add(String.format("Film: %s, Leto: %s, Igralka: %s, Igralec: %s", igralciParts[4], igralciParts[1], igralkeParts[3], igralciParts[3]));
            }
        }
        res.sort(new java.util.Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String movie1 = getMovieFromOut(o1);
                String movie2 = getMovieFromOut(o2);
                return movie1.compareTo(movie2);
            }
        });
        for (String e : res) {
            System.out.println(e);
        }
    }

    static String getMovieFromOut(String outLine) {
        return outLine.split(", ")[0].replace("Film: ", "");
    }

    static java.util.Map<String, String> computeMap(String filename) throws java.io.FileNotFoundException {
        java.util.Map<String, String> map = new java.util.HashMap<>();
        java.util.Scanner sc = new java.util.Scanner(new java.io.File(filename));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String movie = line.split(", ")[4];
            map.put(movie, line);
        }
        return map;
    }
}
