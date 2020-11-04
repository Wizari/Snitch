package com.gmail.wizaripost.snitch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

public class Main extends Application {

    @Getter
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Snitch 0.2");
        primaryStage.setScene(new Scene(root, 700, 800)); //ширина,высота
        primaryStage.show();
        stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);

    }
}
