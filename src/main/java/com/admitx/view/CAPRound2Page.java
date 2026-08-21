package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CAPRound2Page {

    public static Scene getScene() {

        Label title = new Label("CAP Round 2");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label status = new Label("Round 2 Allotment Published");

        status.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        GridPane details = new GridPane();

        details.setHgap(30);
        details.setVgap(18);

        addDetail(
                details,
                "Previous College",
                "College of Engineering Pune",
                0,
                0
        );

        addDetail(
                details,
                "Previous Branch",
                "Computer Engineering",
                2,
                0
        );

        addDetail(
                details,
                "New College",
                "Vishwakarma Institute of Technology",
                0,
                1
        );

        addDetail(
                details,
                "New Branch",
                "Information Technology",
                2,
                1
        );

        addDetail(
                details,
                "Upgrade Status",
                "Upgraded",
                0,
                2
        );

        addDetail(
                details,
                "Round",
                "CAP Round 2",
                2,
                2
        );

        VBox card = new VBox(
                20,
                status,
                details
        );

        card.setPadding(new Insets(25));

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Button freeze = new Button("Freeze");

        freeze.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;"
        );

        freeze.setOnAction(e -> {

            showMessage(
                    "Seat Frozen",
                    "Your Round 2 seat has been accepted."
            );

            Navigation.goTo(
                    CAPRound3Page.getScene()
            );
        });

        Button betterment = new Button("Betterment");

        betterment.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;"
        );

        betterment.setOnAction(e -> {

            showMessage(
                    "Betterment Requested",
                    "You will be considered for further upgrade."
            );

            Navigation.goTo(
                    CAPRound3Page.getScene()
            );
        });

        HBox buttons = new HBox(
                15,
                freeze,
                betterment
        );

        buttons.setAlignment(Pos.CENTER);

        Button dashboard = new Button("Dashboard");

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

        VBox content = new VBox(
                25,
                title,
                card,
                buttons,
                dashboard
        );

        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(30));
        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "CAP Round 2",
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

        Label label = new Label(labelText);

        label.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label valueLabel = new Label(value);

        valueLabel.setStyle(
                "-fx-text-fill: #3F6212;"
        );

        VBox box = new VBox(
                5,
                label,
                valueLabel
        );

        box.setPrefWidth(280);

        grid.add(box, column, row);
    }

    private static void showMessage(
            String title,
            String message
    ) {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}