package com.admitx.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.admitx.util.AsyncExecutor;
public class Navigation extends Application {

    private static Stage stage;

    

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        stage.setTitle(
                "AdmitX - MHT CET CAP Counselling Portal"
        );

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.setWidth(1400);
        stage.setHeight(850);

        stage.setResizable(true);

        goTo(
                AdmitXWelcomePage.getScene()
        );

        stage.show();
    }

    public static void goTo(Scene scene) {

        if (stage == null || scene == null) {
            return;
        }

        stage.setScene(scene);
    }

    public static Stage getStage() {

        return stage;
    }
    @Override
    public void stop() {

        AsyncExecutor.shutdown();
    }

    
}