package com.admitx.view;

import com.admitx.view.Navigation;
import com.admitx.view.StudentLayout;

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

        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("🔄 CAP Round 1");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("First round allotment results");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Result Card
        VBox resultCard = new VBox(20);
        resultCard.setPadding(new Insets(30, 35, 35, 35));
        resultCard.setMaxWidth(650);
        resultCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label roundStatus = new Label("Round Status");
        roundStatus.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;"
        );

        Label status = new Label("✅ Allotment Published");
        status.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4ADE80;"
        );

        GridPane details = new GridPane();
        details.setHgap(40);
        details.setVgap(18);
        details.setPadding(new Insets(10, 0, 5, 0));

        addDetail(details, "Application ID", "MHTCET20260001", 0, 0);
        addDetail(details, "Allotment Status", "Seat Allotted", 2, 0);
        addDetail(details, "College", "College of Engineering Pune", 0, 1);
        addDetail(details, "Branch", "Computer Engineering", 2, 1);
        addDetail(details, "Category", "Open", 0, 2);
        addDetail(details, "Allotted Preference", "Preference No. 1", 2, 2);

        resultCard.getChildren().addAll(roundStatus, status, details);

        // Action Card
        VBox actionCard = new VBox(15);
        actionCard.setPadding(new Insets(30, 35, 35, 35));
        actionCard.setMaxWidth(650);
        actionCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label actionTitle = new Label("Choose Your Action");
        actionTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label actionDescription = new Label(
                "Select Freeze to accept the current seat, " +
                "Betterment to participate in the next round, " +
                "or Reject to decline the allotted seat."
        );
        actionDescription.setWrapText(true);
        actionDescription.setStyle(
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;"
        );

        HBox actionButtons = new HBox(15);
        actionButtons.setAlignment(Pos.CENTER);

        Button freezeButton = new Button("❄️ Freeze");
        freezeButton.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        freezeButton.setOnMouseEntered(e ->
            freezeButton.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        freezeButton.setOnMouseExited(e ->
            freezeButton.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        freezeButton.setOnAction(e -> {
            showMessage("Seat Frozen", "Your allotted seat has been accepted.");
            Navigation.goTo(Round1ConfirmationPage.getScene("Seat Accepted"));
        });

        Button bettermentButton = new Button("🔄 Betterment");
        bettermentButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        bettermentButton.setOnMouseEntered(e ->
            bettermentButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        bettermentButton.setOnMouseExited(e ->
            bettermentButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        bettermentButton.setOnAction(e -> {
            showMessage("Betterment Requested", "You have requested betterment for the next CAP round.");
            Navigation.goTo(Round1ConfirmationPage.getScene("Betterment Requested"));
        });

        Button rejectButton = new Button("✖️ Reject");
        rejectButton.setStyle(
                "-fx-background-color: #7F1D1D;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.3);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        rejectButton.setOnMouseEntered(e ->
            rejectButton.setStyle(
                "-fx-background-color: #991B1B;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.5);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        rejectButton.setOnMouseExited(e ->
            rejectButton.setStyle(
                "-fx-background-color: #7F1D1D;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.3);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        rejectButton.setOnAction(e -> {
            showMessage("Seat Rejected", "Your allotted seat has been rejected.");
            Navigation.goTo(Round1ConfirmationPage.getScene("Seat Rejected"));
        });

        actionButtons.getChildren().addAll(freezeButton, bettermentButton, rejectButton);

        actionCard.getChildren().addAll(actionTitle, actionDescription, actionButtons);

        Button dashboardButton = new Button("← Dashboard");
        dashboardButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        dashboardButton.setOnMouseEntered(e ->
            dashboardButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboardButton.setOnMouseExited(e ->
            dashboardButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboardButton.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        content.getChildren().addAll(title, subtitle, resultCard, actionCard, dashboardButton);

        return new Scene(
                StudentLayout.create("CAP Round 1", content)
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

    private static void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}