package com.example.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation extends Application{
    public static Stage stage;
    public static void goTo(Scene scene) {
        Navigation.stage.setScene(scene);
    }

    public static void backTo(Scene scene) {
        Navigation.stage.setScene(scene);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        stage.setTitle("Dummy MHT CET CAP Counselling Portal");
        stage.setWidth(1366);
        stage.setHeight(700);
        stage.setResizable(true);

        Navigation.goTo(WelcomePage.getScene());

        stage.show();
    }
}