package com.gold.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    public ComboBox<String> themeBox;
    @FXML
    public ComboBox<String> modeBox;
    @FXML
    public TextField topTextField;
    @FXML
    public VBox scientific;
    @FXML
    public GridPane gridPane;


    public String firstNum = ""; // stores first input number for calculation
    public String secondNum = ""; // stores second input number for calculation
    public String opt = "";  // stores operator for calculation
    public boolean clearFirst = false;

    @FXML
    public void handleC(){
        topTextField.setText("");
        this.opt = "";
        this.firstNum = "";
        this.secondNum = "";
    }

    @FXML
    public void handleDigit(ActionEvent e){
        assert e.getTarget() instanceof Button;
        String input = ((Button) e.getTarget()).getText();
        if(clearFirst){
            topTextField.clear();
            clearFirst = false;
        }
        if(input.equals("0")){
            if(topTextField.getText().equals("0")){
                return;
            }
        }
        topTextField.setText(topTextField.getText() + input);

        if(opt.isEmpty()){
            firstNum = topTextField.getText();
        } else{
            secondNum = topTextField.getText();
        }

    }
    @FXML
    public void handleDot(){
        if(!topTextField.getText().contains(".") && !topTextField.getText().isEmpty()){
            topTextField.setText(topTextField.getText() + ".");
        }
    }
    @FXML
    public void handleOperator(ActionEvent e){
        assert e.getTarget() instanceof Button;
        String input = ((Button) e.getTarget()).getText();

        if(topTextField.getText().isEmpty()){
            return;
        }
        if (!opt.isEmpty()) {
            topTextField.setText(calculate(opt));
        }
        opt = input;
        clearFirst = true;
    }
    @FXML
    public void handleScientific(ActionEvent e) {
        assert e.getTarget() instanceof Button;
        String input = ((Button) e.getTarget()).getText();
        if (topTextField.getText().isEmpty() || !secondNum.isEmpty()) {
            return;
        }
        double number = Double.parseDouble(topTextField.getText());
        double result = switch (input) {
            case "log" -> Math.log10(number);
            case "ln" -> Math.log(number);
            case "\u221A" -> Math.sqrt(number);
            default -> 0;
        };
        if (result % 1 == 0) {
            topTextField.setText(String.format("%.0f", result));
        } else {
            topTextField.setText(String.valueOf(result));
        }
        firstNum = topTextField.getText();
        opt = "";
    }




    @FXML
    public void handleEquals(){
        topTextField.setText(calculate(opt));
        // cleanup
        opt = "";
    }
    public String calculate(String operator){
        if(operator.isEmpty() || firstNum.isEmpty() || secondNum.isEmpty()){
            return topTextField.getText();
        }
        double first = Double.parseDouble(firstNum);
        double second = Double.parseDouble(secondNum);
        double result = switch (operator) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> 0;
        };
        firstNum = String.valueOf(result);
        secondNum = "";
        if(result % 1 == 0){
            return String.format("%.0f", result);
        }
        return String.valueOf(result);
    }

    @FXML
    public void themeSwitch(){
        String selection = themeBox.getSelectionModel().getSelectedItem();
        if(selection.equals("Dark")){
            setDarkMode();
        } else{
            setClassicMode();
        }
    }

    public void setClassicMode(){
        gridPane.getStylesheets().clear();
        gridPane.getStylesheets().add(getClass().getResource("css/style.css").toString());
    }

    public void setDarkMode(){

        gridPane.getStylesheets().clear();
        gridPane.getStylesheets().add(getClass().getResource("css/dark.css").toString());

    }

    @FXML
    public void modeSwitch(){
        String selection = modeBox.getSelectionModel().getSelectedItem();
        scientific.setVisible(selection.equals("Scientific"));
    }


}