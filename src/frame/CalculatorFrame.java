package frame;

import logic.CalculatorLogic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    private final JTextField display;
    private String currentOp = "";
    private double firstNum = 0;
    private boolean start = true;
    private final CalculatorLogic logic = new CalculatorLogic();

    public CalculatorFrame() {
        setTitle("Swing Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "DEL", "", ""
        };
        for (String text : buttons) {
            if (text.isEmpty()) {
                panel.add(new JLabel());
                continue;
            }
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(new ButtonHandler());
            panel.add(btn);
        }
        add(panel, BorderLayout.CENTER);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = ((JButton) e.getSource()).getText();
            if (cmd.matches("[0-9.]")) {
                if (start) {
                    display.setText("");
                    start = false;
                }
                display.setText(display.getText() + cmd);
            } else if (cmd.equals("C")) {
                display.setText("");
                currentOp = "";
                start = true;
            } else if (cmd.equals("DEL")) {
                String text = display.getText();
                if (!text.isEmpty()) {
                    display.setText(text.substring(0, text.length() - 1));
                }
            } else if (cmd.equals("=")) {
                if (!currentOp.isEmpty()) {
                    try {
                        double secondNum = Double.parseDouble(display.getText());
                        double result = logic.calculate(firstNum, secondNum, currentOp);
                        display.setText(String.valueOf(result));
                        currentOp = "";
                        start = true;
                    } catch (Exception ex) {
                        display.setText("Error");
                        start = true;
                    }
                }
            } else { // operator
                try {
                    firstNum = Double.parseDouble(display.getText());
                    currentOp = cmd;
                    start = true;
                } catch (Exception ex) {
                    display.setText("Error");
                    start = true;
                }
            }
        }
    }
}
