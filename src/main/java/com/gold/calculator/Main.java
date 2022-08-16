package com.gold.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 425);
        stage.setTitle("Calculator");
        stage.setResizable(false);
        InputStream icon = getClass().getResourceAsStream("images/calculatorIcon.png");
        assert icon != null;
        stage.getIcons().add(new Image(icon));
            stage.setScene(scene);
            stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}