package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Round1ConfirmationPage {

    public static Scene getScene(String decision) {

        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("CAP Round 1 Confirmation");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        // Result Card
        VBox card = new VBox(15);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setMaxWidth(600);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label result = new Label(decision);
        String resultColor = decision.equals("Seat Accepted") ? "#4ADE80" :
                            decision.equals("Betterment Requested") ? "#60A5FA" : "#F87171";
        result.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + resultColor + ";"
        );

        Label college = new Label("College: College of Engineering Pune");
        college.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 15px;");

        Label branch = new Label("Branch: Computer Engineering");
        branch.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 15px;");

        Label category = new Label("Category: Open");
        category.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 15px;");

        Label message;
        if (decision.equals("Seat Accepted")) {
            message = new Label("✅ You have accepted the allotted seat.");
        } else if (decision.equals("Betterment Requested")) {
            message = new Label("🔄 You have requested betterment in the next CAP round.");
        } else {
            message = new Label("❌ You have rejected the allotted seat.");
        }
        message.setWrapText(true);
        message.setStyle(
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 10 0 0 0;"
        );

        card.getChildren().addAll(result, college, branch, category, message);

        // Buttons
        VBox buttons = new VBox(12);
        buttons.setAlignment(Pos.CENTER);

        Button nextRound = new Button("Continue to CAP Round 2");
        nextRound.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
        );
        nextRound.setOnMouseEntered(e ->
            nextRound.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        nextRound.setOnMouseExited(e ->
            nextRound.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        nextRound.setOnAction(e -> Navigation.goTo(CAPRound2Page.getScene()));

        Button dashboard = new Button("Go to Dashboard");
        dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        dashboard.setOnMouseEntered(e ->
            dashboard.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboard.setOnMouseExited(e ->
            dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboard.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        buttons.getChildren().addAll(nextRound, dashboard);

        content.getChildren().addAll(title, card, buttons);

        return new Scene(
                StudentLayout.create("CAP Round 1 Confirmation", content)
        );
    }
}