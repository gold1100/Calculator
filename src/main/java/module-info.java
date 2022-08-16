module com.gold.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gold.calculator to javafx.fxml;
    exports com.gold.calculator;
}