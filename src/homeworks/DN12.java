package homeworks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DN12 {
    private JFrame frame;
    private JTextField textField;

    public DN12() {
        frame = new JFrame("Calculator");
        frame.setResizable(false);
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(generateLayout());
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DN12();
    }

    private JPanel generateLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        textField = generateTextField();
        panel.add(textField);
        panel.add(generateButtons());
        return panel;
    }

    private JTextField generateTextField() {
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setFont(new Font(textField.getFont().getName(), Font.PLAIN, 30));
        textField.setPreferredSize(new Dimension(400, 100));
        textField.setMaximumSize(textField.getPreferredSize());
        return textField;
    }

    private JPanel generateButtons() {
        JPanel panel = new JPanel();
        GridLayout grid = new GridLayout(4, 3);
        grid.setHgap(5);
        grid.setVgap(5);
        panel.setLayout(grid);
        for (int i = 1; i < 10; i++) {
            panel.add(generateNumberButton(i));
        }
        // poskusil sem vec nacinov za poravnavo elementov, ampak neuspesno
        // oprostite mi za tale manjsi heck :)
        JButton invisibleButton = generateNumberButton(0);
        invisibleButton.setVisible(false);
        panel.add(invisibleButton);
        // zdaj je zadnji button pravilno sredinsko centriran
        panel.add(generateNumberButton(0));
        return panel;
    }

    private JButton generateNumberButton(int n) {
        JButton button = new JButton(String.format("%d", n));
        button.setFont(new Font(button.getFont().getName(), Font.PLAIN, 30));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e);
                textField.setText(textField.getText() + n);
            }
        });
        return button;
    }
}
