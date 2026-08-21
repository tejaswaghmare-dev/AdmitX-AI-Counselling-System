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

public class CAPRound1Page {

    public static Scene getScene() {

        Label title = new Label("CAP Round 1");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label roundStatus = new Label("Round Status");

        roundStatus.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label status = new Label("Allotment Published");

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
                "Application ID",
                "MHTCET20260001",
                0,
                0
        );

        addDetail(
                details,
                "Allotment Status",
                "Seat Allotted",
                2,
                0
        );

        addDetail(
                details,
                "College",
                "College of Engineering Pune",
                0,
                1
        );

        addDetail(
                details,
                "Branch",
                "Computer Engineering",
                2,
                1
        );

        addDetail(
                details,
                "Category",
                "Open",
                0,
                2
        );

        addDetail(
                details,
                "Allotted Preference",
                "Preference No. 1",
                2,
                2
        );

        VBox resultCard =
                new VBox(
                        20,
                        roundStatus,
                        status,
                        details
                );

        resultCard.setPadding(
                new Insets(25)
        );

        resultCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Label actionTitle =
                new Label("Choose Your Action");

        actionTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label actionDescription =
                new Label(
                        "Select Freeze to accept the current seat, " +
                        "Betterment to participate in the next round, " +
                        "or Reject to decline the allotted seat."
                );

        actionDescription.setWrapText(true);

        actionDescription.setStyle(
                "-fx-text-fill: #4D7C0F;"
        );

        Button freezeButton =
                new Button("Freeze");

        freezeButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;"
        );

        freezeButton.setOnAction(e -> {

            showMessage(
                    "Seat Frozen",
                    "Your allotted seat has been accepted."
            );

            Navigation.goTo(
                    Round1ConfirmationPage.getScene(
                            "Seat Accepted"
                    )
            );
        });

        Button bettermentButton =
                new Button("Betterment");

        bettermentButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;"
        );

        bettermentButton.setOnAction(e -> {

            showMessage(
                    "Betterment Requested",
                    "You have requested betterment for the next CAP round."
            );

            Navigation.goTo(
                    Round1ConfirmationPage.getScene(
                            "Betterment Requested"
                    )
            );
        });

        Button rejectButton =
                new Button("Reject");

        rejectButton.setStyle(
                "-fx-background-color: #DC2626;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;"
        );

        rejectButton.setOnAction(e -> {

            showMessage(
                    "Seat Rejected",
                    "Your allotted seat has been rejected."
            );

            Navigation.goTo(
                    Round1ConfirmationPage.getScene(
                            "Seat Rejected"
                    )
            );
        });

        HBox actionButtons =
                new HBox(
                        15,
                        freezeButton,
                        bettermentButton,
                        rejectButton
                );

        actionButtons.setAlignment(
                Pos.CENTER
        );

        VBox actionCard =
                new VBox(
                        15,
                        actionTitle,
                        actionDescription,
                        actionButtons
                );

        actionCard.setPadding(
                new Insets(25)
        );

        actionCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Button dashboardButton =
                new Button("Go to Dashboard");

        dashboardButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;"
        );

        dashboardButton.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content =
                new VBox(
                        25,
                        title,
                        resultCard,
                        actionCard,
                        dashboardButton
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
                        "CAP Round 1",
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

    private static void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}