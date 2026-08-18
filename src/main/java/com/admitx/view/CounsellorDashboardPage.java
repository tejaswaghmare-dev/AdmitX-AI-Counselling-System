package com.admitx.view;

import com.admitx.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CounsellorDashboardPage {

    public static Scene getScene() {

        VBox root = new VBox(25);
        root.setPadding(new Insets(35, 40, 40, 40));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📊 Counsellor Dashboard");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );
        DropShadow titleShadow = new DropShadow(20, Color.web("#4A7FB5", 0.2));
        title.setEffect(titleShadow);

        Label subtitle = new Label("MHT CET CAP Counselling Management");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;"
        );

        // Stats Cards
        GridPane stats = new GridPane();
        stats.setHgap(20);
        stats.setVgap(20);

        stats.add(createStatCard("📈 Total Students", "1250"), 0, 0);
        stats.add(createStatCard("✅ Verified Students", "980"), 1, 0);
        stats.add(createStatCard("⏳ Pending Verification", "270"), 2, 0);
        stats.add(createStatCard("🔄 CAP Round Status", "Round 1 Active"), 3, 0);

        // Menu Grid
        GridPane menu = new GridPane();
        menu.setHgap(15);
        menu.setVgap(15);
        menu.setAlignment(Pos.CENTER);

        Button students = createMenuButton("👤 Student Management");
        Button colleges = createMenuButton("🏛️ College Management");
        Button merit = createMenuButton("📋 Merit List Management");
        Button option = createMenuButton("✏️ Option Form Management");
        Button cap1 = createMenuButton("🔄 CAP Round 1");
        Button cap2 = createMenuButton("🔄 CAP Round 2");
        Button cap3 = createMenuButton("🔄 CAP Round 3");
        Button reports = createMenuButton("📊 Reports");
        Button notices = createMenuButton("📢 Notice Management");
        Button profile = createMenuButton("👤 Counsellor Profile");

        students.setOnAction(e -> Navigation.goTo(StudentManagementPage.getScene()));
        colleges.setOnAction(e -> Navigation.goTo(CollegeManagementPage.getScene()));
        merit.setOnAction(e -> Navigation.goTo(MeritListManagementPage.getScene()));
        option.setOnAction(e -> Navigation.goTo(OptionFormManagementPage.getScene()));
        cap1.setOnAction(e -> Navigation.goTo(CAPRound1ManagementPage.getScene()));
        cap2.setOnAction(e -> Navigation.goTo(CAPRound2ManagementPage.getScene()));
        cap3.setOnAction(e -> Navigation.goTo(CAPRound3ManagementPage.getScene()));
        reports.setOnAction(e -> Navigation.goTo(ReportsPage.getScene()));
        notices.setOnAction(e -> Navigation.goTo(NoticeManagementPage.getScene()));
        profile.setOnAction(e -> Navigation.goTo(CounsellorProfilePage.getScene()));

        menu.add(students, 0, 0);
        menu.add(colleges, 1, 0);
        menu.add(merit, 2, 0);
        menu.add(option, 0, 1);
        menu.add(cap1, 1, 1);
        menu.add(cap2, 2, 1);
        menu.add(cap3, 0, 2);
        menu.add(reports, 1, 2);
        menu.add(notices, 2, 2);
        menu.add(profile, 1, 3);

        Button logout = createMenuButton("🚪 Logout");
        logout.setStyle(
                "-fx-background-color: #7F1D1D;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 45px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.3);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
        );
        logout.setOnMouseEntered(e ->
            logout.setStyle(
                "-fx-background-color: #991B1B;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 45px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.5);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        logout.setOnMouseExited(e ->
            logout.setStyle(
                "-fx-background-color: #7F1D1D;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 45px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.3);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        logout.setOnAction(e -> Navigation.goTo(CounsellorLoginPage.getScene()));

        // Footer
        Label footer = new Label("© 2026 AdmitX · Counsellor Dashboard");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.5;" +
                "-fx-padding: 20 0 0 0;"
        );

        root.getChildren().addAll(title, subtitle, stats, menu, logout, footer);

        return new Scene(root, 1200, 850);
    }

    private static VBox createStatCard(String title, String value) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 12px;"
        );

        Label valueLabel = new Label(value);
        valueLabel.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #60A5FA;"
        );

        VBox card = new VBox(8, titleLabel, valueLabel);
        card.setPadding(new Insets(18, 20, 18, 20));
        card.setPrefWidth(200);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 5);"
        );

        card.setOnMouseEntered(e ->
            card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.8);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.3);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(74, 127, 181, 0.2), 15, 0, 0, 8);"
            )
        );
        card.setOnMouseExited(e ->
            card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 5);"
            )
        );

        return card;
    }

    private static Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(220);
        button.setPrefHeight(45);
        button.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
        );
        button.setOnMouseEntered(e ->
            button.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.4), 10, 0, 0, 4);"
            )
        );
        button.setOnMouseExited(e ->
            button.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        return button;
    }
}