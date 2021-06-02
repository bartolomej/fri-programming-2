package quizes.quiz2;

public class Q14 {

    public static void main(String[] args) {
        izpisKoledarja(2000, 1);
    }

    static void izpisKoledarja(int year, int month) {
        java.time.YearMonth yearMonth = java.time.YearMonth.of(year, month);
        int nDays = yearMonth.lengthOfMonth();
        int firstWeekday = java.time.LocalDate.of(year, month, 1).getDayOfWeek().getValue();
        printHeader();
        int count = 1;
        int[] days = new int[7];
        for (int i = firstWeekday - 1; i < 7; i++) {
            days[i] = count;
            count++;
        }
        printWeek(days);
        for (int i = 0; i < 5; i++) {
            days = new int[7];
            for (int j = 0; j < 7; j++) {
                if (count > nDays) {
                    break;
                }
                days[j] = count;
                count++;
            }
            if (days[0] > 0) {
                printWeek(days);
            }
        }
    }

    static void printHeader() {
        String[] days = new String[]{"PO", "TO", "SR", "ČE", "PE", "SO", "NE"};
        for (int i = 0; i < days.length; i++) {
            System.out.printf("%2s", days[i]);
            if (i < days.length - 1) {
                System.out.print("  ");
            }
        }
        System.out.println();
    }

    static void printWeek(int[] days) {
        for (int i = 0; i < days.length; i++) {
            if (days[i] > 0) {
                System.out.printf("%2d", days[i]);
            } else {
                System.out.print("  ");
            }
            if (i < days.length - 1) {
                System.out.print("  ");
            }
        }
        System.out.println();
    }
}
