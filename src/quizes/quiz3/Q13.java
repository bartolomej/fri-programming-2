package quizes.quiz3;

public class Q13 {

    public static void main(String[] args) {
        preberiInIzpisi("i1");
    }

    static void preberiInIzpisi(String oznaka) {
        String prefix = "";
        java.util.Scanner sc;
        try {
            sc = new java.util.Scanner(new java.io.File(String.format("%s%s-prijave.txt", prefix, oznaka)));
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        java.util.ArrayList<String> res = new java.util.ArrayList<>();
        java.util.Map<String, Integer> scoreMap = null;
        try {
            scoreMap = readAndMapScore(oznaka);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(":");
            if (parts.length < 2) {
                continue;
            }
            String name = parts[1];
            String id = parts[0];
            res.add(String.format("%s:%s:%s", id, name, scoreMap.get(parts[0]) == null ? "VP" : scoreMap.get(parts[0])));
        }
        res.sort(new java.util.Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String name1 = o1.split(":")[1];
                String name2 = o2.split(":")[1];
                return name1.compareTo(name2);
            }
        });
        for (String s : res) {
            System.out.println(s);
        }
    }

    static java.util.Map<String, Integer> readAndMapScore(String oznaka) throws java.io.FileNotFoundException {
        String prefix = "";
        String[] files = new String[]{"n1.txt", "n2.txt", "n3.txt", "n4.txt"};
        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        for (String file : files) {
            java.util.Scanner sc = new java.util.Scanner(new java.io.File(String.format("%s%s-%s", prefix, oznaka, file)));
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(":");
                if (parts.length < 2) {
                    continue;
                }
                String id = parts[0];
                int score = Integer.parseInt(parts[1]);
                if (map.containsKey(parts[0])) {
                    map.put(id, map.get(id) + score);
                } else {
                    map.put(id, score);
                }
            }
        }
        return map;
    }
}
