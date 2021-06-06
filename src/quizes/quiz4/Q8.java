package quizes.quiz4;

public class Q8 {

    public static void main(String[] args) {
        izracunaj("src/quizes/quiz4/test1.clc");
    }

    static void izracunaj(String imeDatoteke) {
        java.io.File file = new java.io.File(imeDatoteke);
        java.io.FileInputStream fis;
        try {
            fis = new java.io.FileInputStream(file);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            byte[] header = readNBytes(4, fis);
            if (!new String(header).equals("CALC")) {
                System.out.printf("Datoteka %s ni v formatu CLC!\n", imeDatoteke);
                return;
            }
            byte[] nCalculations = readNBytes(4, fis);
            for (int i = 0; i < parseByteNumber(nCalculations); i++) {
                byte[] op1 = readNBytes(4, fis);
                byte[] operation = readNBytes(1, fis);
                byte[] op2 = readNBytes(4, fis);
                int result = calculate(parseByteNumber(op1), parseByteNumber(op2), (char)operation[0]);
                System.out.println(result);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            if (e.getMessage().equals("Operacija ni veljavna")) {
                System.out.println("Napačen operator.");
            } else {
                e.printStackTrace();
            }
        }
    }

    static int calculate(int op1, int op2, char operation) throws Exception {
        switch (operation) {
            case '+': return op1 + op2;
            case '-': return op1 - op2;
            case '*': return op1 * op2;
            case '/': return op1 / op2;
            default: throw new Exception("Operacija ni veljavna");
        }
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
