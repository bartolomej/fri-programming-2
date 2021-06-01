package quizes.quiz1;

public class Q18 {

    public static void main(String[] args) {
        trikotnik(15,5);
    }

    static void trikotnik(int n, int tip) {
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
            case 4: {
                type4(n);
                break;
            }
            case 5: {
                type5(n);
                break;
            }
            case 6: {
                type6(n);
                break;
            }
            case 7: {
                type7(n);
                break;
            }
        }
    }

    static void type1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.printf("%d ", (j + 1) % 10);
            }
            System.out.println();
        }
    }

    static void type2(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j < i) {
                    System.out.print("  ");
                } else {
                    System.out.printf("%d ", (j - i + 1) % 10);
                }
            }
            System.out.println();
        }
    }

    static void type3(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j + i + 1 < n) {
                    System.out.print("  ");
                } else {
                    System.out.printf("%d ", (n - j) % 10);
                }
            }
            System.out.println();
        }
    }

    static void type4(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.printf("%d ", (n - j - i) % 10);
            }
            System.out.println();
        }
    }

    static void type5(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                if (j >= n - i - 1 && j <= n + i - 1) {
                    System.out.printf("%d ", (n - Math.abs(n - 1 - j) - (n - i - 1)) % 10);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    static void type6(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                if (j >= i && j <= 2 * n - i - 2) {
                    System.out.printf("%d ", (n - Math.abs(n - 1 - j) - i) % 10);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    static void type7(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                if (j >= n - i - 1 && j <= n + i - 1) {
                    System.out.printf("%d ", (n - Math.abs(n - 1 - j) - (n - i - 1) + i) % 10);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

}