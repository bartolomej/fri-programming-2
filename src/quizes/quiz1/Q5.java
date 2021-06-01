package quizes.quiz1;

public class Q5 {

    public static void main(String[] args) {
        System.out.println(pretvoriSekunde(65));
        System.out.println(pretvoriSekunde(49330));
        System.out.println(pretvoriSekunde(-12));
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
