package quizes.quiz3;


public class Q10 {

    public static void main(String[] args) {
        System.out.println(bsdChecksum("src/quizes/quiz3/checksum1.txt"));
    }

    static int bsdChecksum(String filename) {
        java.io.File file = new java.io.File(filename);
        java.io.FileInputStream fis;
        int checksum = 0;
        try {
            fis = new java.io.FileInputStream(file);
        } catch (java.io.FileNotFoundException e) {
            return checksum;
        }
        try {
            while (fis.available() > 0) {
                int c = fis.read();
                // rotate the checksum by one bit to the right
                checksum = (checksum >> 1) + ((checksum & 1) << 15);
                checksum += c;
            }
        } catch (java.io.IOException e) {
            return 0;
        }
        return checksum;
    }
}
