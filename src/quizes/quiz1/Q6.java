package quizes.quiz1;

public class Q6 {

    public static void main(String[] args) {
        javaJavaJava(4);
    }

    static void javaJavaJava(int n) {
        if (n < 0) {
            System.out.println("Napaka: negativen n");
        }
        System.out.print(repeat("     J    a   v     v  a   ", n) + "\n" +
                repeat("     J   a a   v   v  a a  ", n) + "\n" +
                repeat("  J  J  aaaaa   V V  aaaaa ", n) + "\n" +
                repeat("   JJ  a     a   V  a     a", n) + "\n");
    }

    static String repeat(String s, int n) {
        String res = "";
        for (int i = 0; i < n; i++) {
            res += s;
        }
        return res;
    }
}
