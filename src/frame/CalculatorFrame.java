package frame;

import logic.CalculatorLogic;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(340, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        // Modern background color
        getContentPane().setBackground(new Color(245, 246, 250));
        setLayout(new BorderLayout(0, 0));

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.BOLD, 32));
        display.setBackground(new Color(34, 34, 34));
        display.setForeground(Color.WHITE);
        display.setBorder(new EmptyBorder(18, 16, 18, 16));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(5, 4, 14, 14)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(235, 236, 240)); // grigio chiaro
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
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
            btn.setFont(new Font("Segoe UI", Font.BOLD, 22));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(new LineBorder(new Color(223, 228, 234), 2, true));
            btn.setOpaque(true);
            // Colorazione moderna
            if (text.matches("[0-9.]")) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(new Color(34, 34, 34));
            } else if (text.equals("=")) {
                btn.setBackground(new Color(46, 213, 115));
                btn.setForeground(Color.WHITE);
            } else if (text.equals("C") || text.equals("DEL")) {
                btn.setBackground(new Color(255, 71, 87));
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(new Color(112, 161, 255));
                btn.setForeground(Color.WHITE);
            }
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
