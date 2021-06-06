package quizes.quiz4;

public class Q12 {

    public static void main(String[] args) {
        ArrayListPlus alp1 = new ArrayListPlus();

        alp1.set(3, "3");

        alp1.set(4, "4");

        alp1.set(1, "1");

        System.out.println(alp1);  // ;1;;3;4


        ArrayListPlus alp2 = new ArrayListPlus(";b;c;;;;g");

        System.out.println(alp2); //;b;c;;;;g


        ArrayListPlus alp3 = new ArrayListPlus(";b;c;;;;g");

        alp3.set(0, "a");

        alp3.set(4, "e");

        System.out.println(alp3); // a;b;c;;e;;g
    }

}


class ArrayListPlus {
    private java.util.ArrayList<Object> list;

    public ArrayListPlus() {
        list = new java.util.ArrayList<>();
    }

    public ArrayListPlus(String in) {
        list = new java.util.ArrayList<>();
        list.addAll(java.util.Arrays.asList(in.split(";")));
    }

    public void set(int index, Object o) {
        int diff = list.size() - 1 - index;
        if (diff < 0) {
            for (int i = 0; i < Math.abs(diff); i++) {
                list.add("");
            }
        }
        list.set(index, o);
    }

    public String toString() {
        String out = "";
        for (int i = 0; i < list.size(); i++) {
            out += list.get(i);
            if (i != list.size() - 1) {
                out += ";";
            }
        }
        return out;
    }
}