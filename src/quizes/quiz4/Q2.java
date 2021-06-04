package quizes.quiz4;

public class Q2 {

    public static void main(String[] args) {
        poisciInIzpisiBarve("./src/quizes/quiz4/text.css");
    }

    static void poisciInIzpisiBarve(String imeDatoteke) {
        java.util.Scanner sc = null;
        try {
            sc = new java.util.Scanner(new java.io.File(imeDatoteke));
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            java.util.regex.Pattern p = java.util.regex.Pattern.compile("([;\t ]|^)color: #([0-9]|[a-f]){6};?", java.util.regex.Pattern.CASE_INSENSITIVE);
            java.util.regex.Matcher m = p.matcher(line);
            while (m.find()) {
                String hex = m.group().split(":")[1].trim();
                hex = hex.contains(";") ? hex.substring(0, hex.length() - 1) : hex;
                java.awt.Color c = java.awt.Color.decode(hex);
                int[] hsl = rgbToHsl(c.getRed(), c.getGreen(), c.getBlue());
                System.out.printf("%s -> rgb(%d, %d, %d) -> hsl(%d, %d, %d)\n", hex, c.getRed(), c.getGreen(), c.getBlue(), hsl[0], hsl[1], hsl[2]);
            }
        }
    }

    static int[] rgbToHsl(double r, double g, double b) {
        r /= 255;
        g /= 255;
        b /= 255;
        double max = max(r, g, b);
        double min = min(r, g, b);
        double c = max - min;
        double h1 = 0;
        double h, l, s;
        if (max == r) {
            h1 = ((g - b) / c) % 6;
        }
        if (max == g) {
            h1 = ((b - r) / c) + 2;
        }
        if (max == b) {
            h1 = ((r - g) / c) + 4;
        }
        h = h1 * 60;
        if (h < 0) {
            h += 360;
        }
        l = (1.0/2) * (max + min);
        if (l == 1) {
            s = 0;
        } else {
            s = c / (1 - Math.abs(2 * l - 1));
        }
        return new int[]{(int)Math.round(h), (int)Math.round(s * 100), (int)Math.round(l * 100)};
    }

    static double max(double a, double b, double c) {
        return Math.max(Math.max(a, b), c);
    }

    static double min(double a, double b, double c) {
        return Math.min(Math.min(a, b), c);
    }
}
