package com.admitx.view;


import com.admitx.model.ApplicationData;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class FinalMeritPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📊 Final Merit List");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Your final merit rank and eligibility details");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Card
        VBox card = new VBox(16);
        card.setPadding(new Insets(25, 30, 30, 30));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setMaxWidth(500);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        card.getChildren().addAll(
                detail("👤 Candidate", value(data.getCandidateName())),
                detail("🏅 Final Merit Rank", "1498"),
                detail("📋 Category Rank", "Open - 701"),
                detail("🔄 Eligible CAP Rounds", "CAP Round 1, 2 and 3")
        );

        Button optionForm = new Button("🏛️ Proceed to College Search →");
        optionForm.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 240px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        optionForm.setOnMouseEntered(e ->
            optionForm.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 240px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        optionForm.setOnMouseExited(e ->
            optionForm.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 240px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        optionForm.setOnAction(e -> Navigation.goTo(CollegeSearchPage.getScene()));

        content.getChildren().addAll(title, subtitle, card, optionForm);

        return new Scene(
                StudentLayout.create("Final Merit List", content)
        );
    }

    private static VBox detail(String label, String value) {
        Label l1 = new Label(label);
        l1.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;"
        );

        Label l2 = new Label(value);
        l2.setStyle(
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;"
        );

        VBox box = new VBox(4, l1, l2);
        box.setPadding(new Insets(6, 0, 6, 0));
        return box;
    }

    private static String value(String text) {
        if (text == null || text.isBlank())
            return "Not Available";
        return text;
    }
}
