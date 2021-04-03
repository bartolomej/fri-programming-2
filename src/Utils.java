import java.util.List;

public class Utils {

    public static <T> void print(List<T> array) {
        System.out.println();
        for (T e : array) {
            System.out.print(e + " ");
        }
    }

    public static void print(int[] array) {
        System.out.println();
        for (int e : array) {
            System.out.print(e + " ");
        }
    }
}
