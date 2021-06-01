package homeworks;

import java.util.Arrays;

public class DN02 {
    public static void main(String[] args) throws Exception {
        String[] outArray = Arrays.copyOfRange(args, 1, args.length);
        if (args[0].equals("1"))  {
            print(outArray);
        } else if (args[0].equals("2")) {
            printPretty(outArray);
        } else {
            throw new Exception("Invalid first argument");
        }
    }

    private static void printPretty(String[] args) {
        String out = String.join(" ", args);
        int lineLength = out.length() + 4;
        printStarLine(lineLength);
        System.out.println("* " + out + " *");
        printStarLine(lineLength);
    }

    private static void print(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + (i == args.length - 1 ? "" : ", "));
        }
        System.out.println();
    }

    private static void printStarLine(int length) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < length; i++) {
            out.append("*");
        }
        System.out.println(out);
    }
}
