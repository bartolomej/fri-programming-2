package quizes.quiz1;

public class Q10 {

    public static void main(String[] args) {
        vDesetisko(231);
        vDesetisko(43854);
    }

    static void vDesetisko(int octal) {
        int decimal = 0, number = octal, n = 0;
        while (number > 0) {
            int ones = number % 10;
            if (ones >= 8) {
                System.out.printf("Število %d ni število v osmiškem sistemu (števka %d)\n", octal, ones);
                return;
            }
            number = number / 10;
            decimal += ones * Math.pow(8, n);
            n++;
        }
        System.out.printf("%d(8) = %d(10)\n", octal, decimal);
    }
}
