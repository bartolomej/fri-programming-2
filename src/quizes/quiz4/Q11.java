package quizes.quiz4;

public class Q11 {

    public static void main(String[] args) {
        SkladInterface s = new Sklad();
        s.push(1);
        s.push(2);
        s.push(3);

        s.reverse();

        s.push("a");s.push("b");s.push("c");

        while (!s.isEmpty()) {
            System.out.print(s.pop());
        }
    }

    interface SkladInterface {
        public boolean isEmpty();   // je sklad prazen?
        public void push(Object o); // doda element na vrh sklada
        public Object pop();        // vrne element z vrha sklada
        public void reverse();      // obrne vrstni red elementov na skladu
    }

    static class Sklad implements SkladInterface {
        private java.util.ArrayList<Object> list;

        public Sklad() {
            list = new java.util.ArrayList<>();
        }

        @Override
        public boolean isEmpty() {
            return list.size() == 0;
        }

        @Override
        public void push(Object o) {
            list.add(o);
        }

        @Override
        public Object pop() {
            return list.remove(list.size() - 1);
        }

        @Override
        public void reverse() {
            java.util.Collections.reverse(list);
        }
    }
}
