package frame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.CalculatorLogic;

public class CalculatorFX extends Application {
    private TextField display = new TextField();
    private String currentOp = "";
    private double firstNum = 0;
    private boolean start = true;
    private CalculatorLogic logic = new CalculatorLogic();

    @Override
    public void start(Stage primaryStage) {
        display.setEditable(false);
        display.setMinSize(200, 50);
        GridPane pane = new GridPane();
        pane.add(display, 0, 0, 4, 1);
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
        int row = 1, col = 0;
        for (String text : buttons) {
            Button btn = new Button(text);
            btn.setMinSize(50, 50);
            btn.setOnAction(e -> handleButton(text));
            pane.add(btn, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }
        Scene scene = new Scene(pane, 220, 270);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calcolatrice JavaFX");
        primaryStage.show();
    }

    private void handleButton(String text) {
        if (text.matches("[0-9]") || text.equals(".")) {
            if (start) {
                display.clear();
                start = false;
            }
            display.appendText(text);
        } else if (text.equals("C")) {
            display.clear();
            currentOp = "";
            start = true;
        } else if (text.equals("=")) {
            if (!currentOp.isEmpty()) {
                try {
                    double secondNum = Double.parseDouble(display.getText());
                    double result = logic.calculate(firstNum, secondNum, currentOp);
                    display.setText(String.valueOf(result));
                    currentOp = "";
                    start = true;
                } catch (Exception ex) {
                    display.setText("Errore");
                    start = true;
                }
            }
        } else { // operator
            try {
                firstNum = Double.parseDouble(display.getText());
                currentOp = text;
                start = true;
            } catch (Exception ex) {
                display.setText("Errore");
                start = true;
            }
        }
    }
}
