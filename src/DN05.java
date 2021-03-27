import java.util.Arrays;
import java.util.HashMap;

public class DN05 {

    static HashMap<Character, Integer> map;
    static String in;

    public static void main(String[] args) {
        map = new HashMap<>();
        in = String.join(" ", Arrays.copyOfRange(args, 0, args.length));
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if (c >= 'a' && c <= 'z') {
                increment(in.charAt(i));
            }
        }
        printMessage();
    }

    private static void increment(char letter) {
        if (map.containsKey(letter)) {
            map.put(letter, map.get(letter) + 1);
        } else {
            map.put(letter, 1);
        }
    }

    private static void printMessage() {
        System.out.printf("V nizu '%s' ", in);
        Character[] keys = map.keySet().toArray(new Character[map.keySet().size()]);
        if (keys.length == 0) {
            System.out.println("ni malih crk angleske abecede.");
            return;
        } else {
            System.out.print("se pojavijo crke ");
        }
        for (int i = 0; i < keys.length; i++) {
            System.out.printf("%s(%d)", keys[i], map.get(keys[i]));
            if (i == keys.length - 1) {
                System.out.println(".");
            } else {
                System.out.print(", ");
            }
        }
    }
}
