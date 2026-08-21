package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StudentDashboardPage {

    public static Scene getScene() {

        Label title = new Label("Welcome, Student");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label status = new Label(
                "Profile Completion: 40%\n\n" +
                "Application Status: Draft\n\n" +
                "Document Status: Pending\n\n" +
                "Merit Status: Not Published\n\n" +
                "CAP Round Status: Not Started"
        );

        status.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Button nextStep = new Button("Next Step");

        nextStep.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        nextStep.setOnAction(e ->
                Navigation.goTo(PersonalDetailsPage.getScene())
        );

        VBox content = new VBox(
                25,
                title,
                status,
                nextStep
        );

        content.setAlignment(Pos.TOP_LEFT);
        content.setPadding(new Insets(35));

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Student Dashboard",
                        content
                )
        );
    }
}