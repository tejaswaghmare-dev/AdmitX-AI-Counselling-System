package com.example.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NoticeBoardPage {

    public static Scene getScene() {

        Label title = new Label("Notice Board");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        VBox notices = new VBox(15);

        notices.getChildren().add(
                createNotice(
                        "CAP Schedule",
                        "CAP Round 1 allotment results have been published."
                )
        );

        notices.getChildren().add(
                createNotice(
                        "Counsellor Notice",
                        "Students are requested to verify their documents."
                )
        );

        notices.getChildren().add(
                createNotice(
                        "Important Update",
                        "Option form filling is now open."
                )
        );

        Button back = new Button("Dashboard");

        back.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;"
        );

        back.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content = new VBox(
                25,
                title,
                notices,
                back
        );

        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Notice Board",
                        content
                )
        );
    }

    private static VBox createNotice(
            String heading,
            String message
    ) {

        Label title = new Label(heading);

        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        Label text = new Label(message);

        text.setWrapText(true);

        text.setStyle(
                "-fx-text-fill: #3F6212;"
        );

        VBox box = new VBox(
                8,
                title,
                text
        );

        box.setPadding(new Insets(18));
        box.setMaxWidth(750);

        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 8px;"
        );

        return box;
    }
}