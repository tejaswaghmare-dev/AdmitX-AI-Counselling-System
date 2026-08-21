package com.admitx.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AdmissionConfirmationPage {

    public static Scene getScene() {

        Label title =
                new Label("Admission Confirmation");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label success =
                new Label("✓ Admission Successfully Confirmed");

        success.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        Label seat =
                new Label(
                        "Final Seat: Allotted"
                );

        Label college =
                new Label(
                        "College: Vishwakarma Institute of Technology"
                );

        Label branch =
                new Label(
                        "Branch: Information Technology"
                );

        Label reporting =
                new Label(
                        "Reporting Status: Pending"
                );

        Label admission =
                new Label(
                        "Admission Status: Complete"
                );

        VBox card =
                new VBox(
                        15,
                        success,
                        seat,
                        college,
                        branch,
                        reporting,
                        admission
                );

        card.setPadding(
                new Insets(30)
        );

        card.setMaxWidth(650);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Button allotmentLetter =
                new Button(
                        "Download Dummy Allotment Letter"
                );

        allotmentLetter.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 230px;" +
                "-fx-pref-height: 40px;"
        );

        allotmentLetter.setOnAction(e ->
                showMessage(
                        "Download",
                        "Dummy Allotment Letter downloaded."
                )
        );

        Button receipt =
                new Button(
                        "Download Admission Receipt"
                );

        receipt.setStyle(
                "-fx-background-color: #3F6212;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;"
        );

        receipt.setOnAction(e ->
                showMessage(
                        "Download",
                        "Dummy Admission Receipt downloaded."
                )
        );

        Button dashboard =
                new Button("Go to Dashboard");

        dashboard.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 40px;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox buttons =
                new VBox(
                        12,
                        allotmentLetter,
                        receipt,
                        dashboard
                );

        buttons.setAlignment(
                Pos.CENTER
        );

        VBox content =
                new VBox(
                        25,
                        title,
                        card,
                        buttons
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
                        "Admission Confirmation",
                        content
                )
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