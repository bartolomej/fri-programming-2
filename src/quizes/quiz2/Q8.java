package quizes.quiz2;

public class Q8 {

    public static void main(String[] args) {
        System.out.println(vsotaSkupnihCifer(112, 113));
    }

    static int vsotaSkupnihCifer(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        int sum = 0;
        for (int i = 0; i < length(max); i++) {
            int n = numberAt(max, i);
            if (contains(min, n) && !contains(max, n, i - 1)) {
                sum += n;
            }
        }
        return sum;
    }

    static int length(int number) {
        return (int)Math.floor(Math.log10(number)) + 1;
    }

    static boolean contains(int number, int digit) {
        return contains(number, digit, length(number));
    }

    static int numberAt(int number, int index) {
        int count = 0;
        while (number > 0) {
            if (count == index) {
                return number % 10;
            }
            number = number / 10;
            count++;
        }
        return 0;
    }

    static boolean contains(int number, int digit, int toIndex) {
        int count = 0;
        while (count <= toIndex) {
            if (number % 10 == digit) {
                return true;
            }
            number = number / 10;
            count++;
        }
        return false;
    }
}
