package quizes.quiz1;

public class Q15 {

    public static void main(String[] args) {
        System.out.println(izracunajRazliko( "08:23:10", "10:10:05"));
    }

   static String izracunajRazliko(String first, String second) {
        int s1 = getSeconds(first);
        int s2 = getSeconds(second);
        int diff = Math.abs(s1 - s2);
        return pretvoriSekunde(diff);
   }

   static int getSeconds(String format) {
        String[] parts = format.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        return h * 3600 + m * 60 + s;
   }

    static String pretvoriSekunde(int s) {
        if (s < 0) {
            return "Število sekund ne more biti negativno";
        }
        int hours = s / 3600;
        int minutes = (s - hours * 3600) / 60;
        int seconds = s % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
