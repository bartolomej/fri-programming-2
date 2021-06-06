package quizes.quiz4;

public class Q9 {

    public static void main(String[] args) {
        histogram("src/quizes/quiz4/ikona.p2b");
    }

    static void histogram(String imeDatoteke) {
        if (!imeDatoteke.endsWith(".p2b")) {
            System.out.println("Datoteka ni v formatu P2B: napaka v imenu datoteke.");
            return;
        }
        java.io.File file = new java.io.File(imeDatoteke);
        java.io.FileInputStream fis;
        try {
            fis = new java.io.FileInputStream(file);
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        java.util.Map<String, Integer> countMap = new java.util.HashMap<>();
        try {
            byte[] header = readNBytes(3, fis);
            if (!new String(header).equals("P2B")) {
                System.out.println("Datoteka ni v formatu P2B: napaka pri podpisu slike.");
                return;
            }
            int width = parseByteNumber(readNBytes(4, fis));
            int height = parseByteNumber(readNBytes(4, fis));
            while (fis.available() > 0) {
                byte[] pixel = readNBytes(3, fis);
                String hexPixel = pixelToHex(pixel);
                if (countMap.containsKey(hexPixel)) {
                    countMap.put(hexPixel, countMap.get(hexPixel) + 1);
                } else {
                    countMap.put(hexPixel, 1);
                }
            }
            java.util.ArrayList<String> pixelStats = new java.util.ArrayList<>(countMap.keySet());
            pixelStats.sort(new java.util.Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Integer.parseInt(o1.split(" ")[0], 16) - Integer.parseInt(o2.split(" ")[0], 16);
                }
            });
            for (int i = 0; i < pixelStats.size(); i++) {
                pixelStats.set(i, String.format("%6s %s", pixelStats.get(i), countMap.get(pixelStats.get(i))));
            }
            for (String s : pixelStats) {
                System.out.println(s);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static String pixelToHex(byte[] pixel) {
        return Integer.toHexString(parseByteNumber(pixel));
    }

    static int parseByteNumber(byte[] bytes) {
        int res = 0;
        for (int i = 0; i < bytes.length; i++) {
            res += toUnsignedByte(bytes[i]) << ((bytes.length - 1 - i) * 8);
        }
        return res;
    }

    static int toUnsignedByte(byte b) {
        return b & 0xFF;
    }

    static byte[] readNBytes(int n, java.io.InputStream stream) throws java.io.IOException {
        int i = 0;
        byte[] bytes = new byte[n];
        while (i < n) {
            int b = stream.read();
            if (b == -1) {
                return bytes;
            }
            bytes[i] = (byte) b;
            i++;
        }
        return bytes;
    }
}
