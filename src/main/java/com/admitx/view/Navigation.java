package com.admitx.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigation extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        stage.setTitle(
                "AdmitX - MHT CET CAP Counselling Portal"
        );

        stage.setMinWidth(1100);
        stage.setMinHeight(800);

        stage.setWidth(1400);
        stage.setHeight(800);

        // stage.setWidth(1200);
        // stage.setHeight(750);
        stage.setResizable(true);

        goTo(
                WelcomePage.getScene()
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

    public static void main(String[] args) {

        launch(args);
    }
}

/*
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
        stage.setWidth(1200);
        stage.setHeight(750);
        stage.setResizable(true);

        Navigation.goTo(WelcomePage.getScene());

        stage.show();
    }
}
 */