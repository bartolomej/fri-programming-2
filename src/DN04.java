import java.security.InvalidParameterException;

public class DN04 {

    public static void main(String[] args) {
        String bin = args[0];
        if (bin.length() % 8 != 0) {
            throw new InvalidParameterException("Invalid binary number");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < (bin.length() / 8); i++) {
            String nBin = bin.substring(i * 8, (i + 1) * 8);
            int characterCode = binToDec(nBin);
            result.append((char) characterCode);
        }
        System.out.println(result);
    }

    private static int binToDec(String bin) {
        int sum = 0;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                sum += Math.pow(2, (bin.length() - 1) - i);
            }
        }
        return sum;
    }
}
