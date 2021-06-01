package quizes.quiz1;

public class Q19 {

    public static void main(String[] args) {
        metulj(5, 3);
    }

    static void metulj(int n, int tip) {
        switch (tip) {
            case 1: {
                type1(n);
                break;
            }
            case 2: {
                type2(n);
                break;
            }
            case 3: {
                type3(n);
                break;
            }
        }
    }

    static void type1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                int diff = n - Math.abs(n - j - 1);
                if (i >= diff - 1) {
                    System.out.printf("%d ", diff % 10);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    static void type2(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                int diff = n - Math.abs(n - j - 1);
                if (n - i >= diff) {
                    System.out.printf("%d ", diff % 10);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    static void type3(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                int diff = n - Math.abs(n - j - 1);
                if (i >= diff - 1) {
                    System.out.printf("%d ", diff % 10);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                int diff = n - Math.abs(n - j - 1);
                if (n - i >= diff) {
                    System.out.printf("%d ", diff % 10);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
