package com.admitx.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CAPRound3Page {

    public static Scene getScene() {

        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("🔄 CAP Round 3");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Final allotment results");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Card
        VBox card = new VBox(20);
        card.setPadding(new Insets(30, 35, 35, 35));
        card.setMaxWidth(600);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label status = new Label("✅ Final Allotment");
        status.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4ADE80;"
        );

        GridPane details = new GridPane();
        details.setHgap(40);
        details.setVgap(18);
        details.setPadding(new Insets(10, 0, 5, 0));

        addDetail(details, "Final Seat", "Allotted", 0, 0);
        addDetail(details, "Final College", "Vishwakarma Institute of Technology", 2, 0);
        addDetail(details, "Final Branch", "Information Technology", 0, 1);
        addDetail(details, "CAP Round", "Round 3", 2, 1);

        card.getChildren().addAll(status, details);

        Button accept = new Button("✅ Accept Admission");
        accept.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(6, 95, 70, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
        );
        accept.setOnMouseEntered(e ->
            accept.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(7, 138, 92, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        accept.setOnMouseExited(e ->
            accept.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(6, 95, 70, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        accept.setOnAction(e -> Navigation.goTo(AdmissionConfirmationPage.getScene()));

        Button dashboard = new Button("← Dashboard");
        dashboard.setStyle(
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
        dashboard.setOnMouseEntered(e ->
            dashboard.setStyle(
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
        dashboard.setOnMouseExited(e ->
            dashboard.setStyle(
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
        dashboard.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        content.getChildren().addAll(title, subtitle, card, accept, dashboard);

        return new Scene(
                StudentLayout.create("CAP Round 3", content)
        );
    }

    private static void addDetail(GridPane grid, String labelText, String value, int column, int row) {
        Label label = new Label(labelText);
        label.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;"
        );

        Label valueLabel = new Label(value);
        valueLabel.setStyle(
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;"
        );

        VBox box = new VBox(5, label, valueLabel);
        box.setPrefWidth(280);
        grid.add(box, column, row);
    }
}