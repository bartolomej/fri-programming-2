package exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Naloga23 {

    static private JFrame frame;
    static private JTextField leftTextField;
    static private JTextField rightTextField;

    public static void main(String[] args) {
        startApp();
    }

    static void startApp() {
        frame = new JFrame("Papajščina");
        frame.setResizable(false);
        frame.setSize(460, 70);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(generateLayout());
        frame.setVisible(true);
    }

    static JPanel generateLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        leftTextField = generateTextField();
        rightTextField = generateTextField();
        panel.add(leftTextField);
        panel.add(generateButton());
        panel.add(rightTextField);
        return panel;
    }

    static JTextField generateTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 30));
        return textField;
    }

    static JButton generateButton() {
        JButton button = new JButton("Pretvori ->");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightTextField.setText(toPapaya(leftTextField.getText()));
            }
        });
        return button;
    }

    static String toPapaya(String s) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (jeSamoglasnik(letter)) {
                out.append(letter).append("pa");
            } else {
                out.append(letter);
            }
        }
        return out.toString();
    }

    static boolean jeSamoglasnik(char c) {
        char[] samoglasniki = new char[]{'A', 'E', 'I', 'O', 'U'};
        char a = c >= 'a' ? (char)(c - 32) : c;
        for (char b : samoglasniki) {
            if (a == b) {
                return true;
            }
        }
        return false;
    }
}
