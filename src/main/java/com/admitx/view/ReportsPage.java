package com.admitx.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ReportsPage {

    public static Scene getScene() {

        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📊 Reports");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Generate and view various reports");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Card with report buttons
        VBox card = new VBox(12);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30, 35, 35, 35));
        card.setMaxWidth(500);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Button studentReport = createReportButton("👤 Student Report");
        Button meritReport = createReportButton("📋 Merit Report");
        Button collegeReport = createReportButton("🏛️ College-wise Report");
        Button branchReport = createReportButton("📚 Branch-wise Report");
        Button categoryReport = createReportButton("📊 Category-wise Report");
        Button roundReport = createReportButton("🔄 Round-wise Report");

        studentReport.setOnAction(e -> show("Student Report"));
        meritReport.setOnAction(e -> show("Merit Report"));
        collegeReport.setOnAction(e -> show("College-wise Report"));
        branchReport.setOnAction(e -> show("Branch-wise Report"));
        categoryReport.setOnAction(e -> show("Category-wise Report"));
        roundReport.setOnAction(e -> show("Round-wise Report"));

        card.getChildren().addAll(
                studentReport,
                meritReport,
                collegeReport,
                branchReport,
                categoryReport,
                roundReport
        );

        Button back = new Button("← Back to Dashboard");
        back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        back.setOnMouseEntered(e ->
            back.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnMouseExited(e ->
            back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnAction(e -> Navigation.goTo(CounsellorDashboardPage.getScene()));

        // Footer
        Label footer = new Label("© 2026 AdmitX · Reports Dashboard");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.5;" +
                "-fx-padding: 20 0 0 0;"
        );

        root.getChildren().addAll(title, subtitle, card, back, footer);

        return new Scene(root, 1000, 700);
    }

    private static Button createReportButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(280);
        button.setPrefHeight(44);
        button.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;"
        );
        button.setOnMouseEntered(e ->
            button.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;"
            )
        );
        button.setOnMouseExited(e ->
            button.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;"
            )
        );
        return button;
    }

    private static void show(String report) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report");
        alert.setHeaderText(report);
        alert.setContentText(report + " generated successfully.");
        alert.showAndWait();
    }
}