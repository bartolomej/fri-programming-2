package quizes.quiz4;

public class Q5 {

    public static void main(String[] args) {
        izpisi("src/quizes/quiz4/skrito.dat");
        preveri("44513715", "src/quizes/quiz4/skrito.dat");
    }

    static void preveri(String stevilka, String imeDatoteke) {
        java.io.File file = new java.io.File(imeDatoteke);
        java.io.FileInputStream fis;
        try {
            fis = new java.io.FileInputStream(file);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                if (fis.available() == 0) break;
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            try {
                String phone = parseBinPhone(readNBytes(3, fis));
                if (phone.equals(stevilka)) {
                    System.out.printf("Številka %s je v datoteki\n", stevilka);
                    return;
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Številke %s ni v datoteki\n", stevilka);
    }

    static void izpisi(String imeDatoteke) {
        java.io.File file = new java.io.File(imeDatoteke);
        java.io.FileInputStream fis;
        try {
            fis = new java.io.FileInputStream(file);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("V datoteki skrito.dat so naslednje številke:");
        while (true) {
            try {
                if (fis.available() == 0) break;
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            try {
                String phone = parseBinPhone(readNBytes(3, fis));
                System.out.println(phone);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    static String parseBinPhone(byte[] bytes) {
        int n = (toUnsignedByte(bytes[0]) << 16) + (toUnsignedByte(bytes[1]) << 8) + toUnsignedByte(bytes[2]);
        return String.format("0%s / %s %s", numberAt(n, 6, 7), numberAt(n, 3, 5), numberAt(n, 0, 2));
    }

    static String numberAt(int number, int startIndex, int endIndex) {
        String out = "";
        int count = 0;
        while (number > 0) {
            if (count >= startIndex && count <= endIndex) {
                out += (number % 10);
            }
            number = number / 10;
            count++;
        }
        return new java.lang.StringBuilder(out).reverse().toString();
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

    // for debugging purposes
    static String toBinary(int decimal) {
        String out = "";
        int binary[] = new int[40];
        int index = 0;
        while (decimal > 0) {
            binary[index++] = decimal % 2;
            decimal = decimal / 2;
        }
        for (int i = index - 1; i >= 0; i--) {
            out += binary[i];
        }
        return out;
    }
}
