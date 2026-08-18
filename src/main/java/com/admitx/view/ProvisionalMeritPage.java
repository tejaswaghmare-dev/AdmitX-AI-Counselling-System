package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;
import com.example.model.ApplicationData;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProvisionalMeritPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("Provisional Merit List");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Your provisional merit list details");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 5 0;"
        );

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
                detail("🆔 Application ID", "MHTCET20260001"),
                detail("📊 MHT CET Percentile", value(data.getCetPercentile())),
                detail("📋 Provisional Merit No.", "1542"),
                detail("🏅 Category Rank", "Open - 742"),
                detail("✅ Status", "Published")
        );

        Button grievance = new Button("Raise Grievance");
        grievance.setStyle(
                "-fx-background-color: #7F3A0C;" +
                "-fx-text-fill: #FBBF24;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(251, 191, 36, 0.3);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        grievance.setOnMouseEntered(e ->
            grievance.setStyle(
                "-fx-background-color: #9C4A0E;" +
                "-fx-text-fill: #FCD34D;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(251, 191, 36, 0.5);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        grievance.setOnMouseExited(e ->
            grievance.setStyle(
                "-fx-background-color: #7F3A0C;" +
                "-fx-text-fill: #FBBF24;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(251, 191, 36, 0.3);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        grievance.setOnAction(e -> Navigation.goTo(GrievanceSubmissionPage.getScene()));

        Button dashboard = new Button("← Dashboard");
        dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 160px;" +
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
                "-fx-pref-width: 160px;" +
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
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboard.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        content.getChildren().addAll(
                title,
                subtitle,
                card,
                grievance,
                dashboard
        );

        return new Scene(
                StudentLayout.create("Provisional Merit List", content)
        );
    }

    private static VBox detail(String label, String value) {
        Label l1 = new Label(label);
        l1.setStyle("-fx-font-weight: bold; -fx-text-fill: #8AA8C7; -fx-font-size: 13px;");
        Label l2 = new Label(value);
        l2.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 15px;");
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