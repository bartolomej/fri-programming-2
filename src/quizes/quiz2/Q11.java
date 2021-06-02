package quizes.quiz2;

public class Q11 {

    public static void main(String[] args) {
        System.out.println(vMorse("Kako si kaj?"));
    }

    static String vMorse(String message) {
        String s = "";
        message = message.toUpperCase();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            s += c == ' ' ? " " : letterToMorse(c) + ' ';
        }
        return s;
    }

    static String letterToMorse(char key) {
        String[] charMap = new String[91];
        if ((int)(key) > charMap.length) {
            return "?";
        }
        charMap['A'] = ".-";
        charMap['B'] = "-...";
        charMap['C'] = "-.-.";
        charMap['D'] = "-..";
        charMap['E'] = ".";
        charMap['F'] = "..-.";
        charMap['G'] = "--.";
        charMap['H'] = "....";
        charMap['I'] = "..";
        charMap['J'] = ".---";
        charMap['K'] = "-.-";
        charMap['L'] = ".-..";
        charMap['M'] = "--";
        charMap['N'] = "-.";
        charMap['O'] = "---";
        charMap['P'] = ".--.";
        charMap['Q'] = "--.-";
        charMap['R'] = ".-.";
        charMap['S'] = "...";
        charMap['T'] = "-";
        charMap['U'] = "..-";
        charMap['V'] = "...-";
        charMap['W'] = ".--";
        charMap['X'] = "-..-";
        charMap['Y'] = "-.--";
        charMap['Z'] = "--..";
        charMap['1'] = ".----";
        charMap['2'] = "..---";
        charMap['3'] = "...--";
        charMap['4'] = "....-";
        charMap['5'] = ".....";
        charMap['6'] = "-....";
        charMap['7'] = "--...";
        charMap['8'] = "---..";
        charMap['9'] = "----.";
        charMap['0'] = "-----";
        charMap['.'] = ".-.-.-";
        charMap['?'] = "..--..";
        charMap[','] = "--..--";
        return charMap[key];
    }
}
