package com.admitx.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NoticeBoardPage {

    public static Scene getScene() {

        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📢 Notice Board");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Latest updates and announcements");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        VBox notices = new VBox(15);
        notices.setAlignment(Pos.TOP_CENTER);

        notices.getChildren().add(
                createNotice(
                        "📅 CAP Schedule",
                        "CAP Round 1 allotment results have been published. Students are requested to check their allotment status."
                )
        );

        notices.getChildren().add(
                createNotice(
                        "📋 Counsellor Notice",
                        "Students are requested to verify their documents before the deadline. Last date for verification is 30th August."
                )
        );

        notices.getChildren().add(
                createNotice(
                        "⚠️ Important Update",
                        "Option form filling is now open. Last date to fill preferences is 25th August."
                )
        );

        Button back = new Button("← Dashboard");
        back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        back.setOnMouseEntered(e ->
            back.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnMouseExited(e ->
            back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        content.getChildren().addAll(title, subtitle, notices, back);

        return new Scene(
                StudentLayout.create("Notice Board", content)
        );
    }

    private static VBox createNotice(String heading, String message) {
        Label title = new Label(heading);
        title.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #60A5FA;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label text = new Label(message);
        text.setWrapText(true);
        text.setStyle(
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-line-spacing: 2px;"
        );

        VBox box = new VBox(8, title, text);
        box.setPadding(new Insets(18, 22, 18, 22));
        box.setMaxWidth(750);
        box.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 5);"
        );

        box.setOnMouseEntered(e ->
            box.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.8);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.3);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(74, 127, 181, 0.2), 15, 0, 0, 8);"
            )
        );
        box.setOnMouseExited(e ->
            box.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 5);"
            )
        );

        return box;
    }
}
