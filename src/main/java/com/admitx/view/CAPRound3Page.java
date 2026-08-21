package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CAPRound3Page {

    public static Scene getScene() {

        Label title =
                new Label("CAP Round 3");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label status =
                new Label("Final Allotment");

        status.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        GridPane details =
                new GridPane();

        details.setHgap(30);
        details.setVgap(18);

        addDetail(
                details,
                "Final Seat",
                "Allotted",
                0,
                0
        );

        addDetail(
                details,
                "Final College",
                "Vishwakarma Institute of Technology",
                2,
                0
        );

        addDetail(
                details,
                "Final Branch",
                "Information Technology",
                0,
                1
        );

        addDetail(
                details,
                "CAP Round",
                "Round 3",
                2,
                1
        );

        VBox card =
                new VBox(
                        20,
                        status,
                        details
                );

        card.setPadding(
                new Insets(25)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Button accept =
                new Button("Accept Admission");

        accept.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 42px;"
        );

        accept.setOnAction(e ->
                Navigation.goTo(
                        AdmissionConfirmationPage.getScene()
                )
        );

        Button dashboard =
                new Button("Dashboard");

        dashboard.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content =
                new VBox(
                        25,
                        title,
                        card,
                        accept,
                        dashboard
                );

        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "CAP Round 3",
                        content
                )
        );
    }

    private static void addDetail(
            GridPane grid,
            String labelText,
            String value,
            int column,
            int row
    ) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-text-fill: #3F6212;"
        );

        VBox box =
                new VBox(
                        5,
                        label,
                        valueLabel
                );

        box.setPrefWidth(280);

        grid.add(
                box,
                column,
                row
        );
    }
}