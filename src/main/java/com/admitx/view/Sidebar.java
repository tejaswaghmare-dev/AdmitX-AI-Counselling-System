package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Sidebar {

    private static final String BG = "#0A0F0A";
    private static final String PANEL = "#121A12";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String HOVER = "#1B2817";

    public static VBox create() {

        Label logo = new Label("ADMITX");
        logo.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle = new Label("AI STUDENT PORTAL");
        subtitle.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        VBox branding = new VBox(2, logo, subtitle);
        branding.setAlignment(Pos.CENTER_LEFT);
        branding.setPadding(new Insets(5, 8, 25, 8));

        Button dashboard = createButton("⌂", "Dashboard");
        Button application = createButton("▣", "Application");
        Button merit = createButton("★", "Merit List");
        Button college = createButton("⌕", "College Search");
        Button preference = createButton("☷", "Preference Filling");
        Button cap = createButton("◉", "CAP Rounds");
        Button admission = createButton("✓", "Admission");
        Button notices = createButton("●", "Notices");
        Button profile = createButton("●", "Profile");
        Button help = createButton("?", "Help");
        Button logout = createButton("⇥", "Logout");

        dashboard.setOnAction(e ->
                Navigation.goTo(StudentDashboardPage.getScene())
        );

        application.setOnAction(e ->
                Navigation.goTo(PersonalDetailsPage.getScene())
        );

        merit.setOnAction(e ->
                Navigation.goTo(ProvisionalMeritPage.getScene())
        );

        college.setOnAction(e ->
                Navigation.goTo(CollegeSearchPage.getScene())
        );

        preference.setOnAction(e ->
                Navigation.goTo(PreferenceFillingPage.getScene())
        );

        cap.setOnAction(e ->
                Navigation.goTo(CAPRound1Page.getScene())
        );

        admission.setOnAction(e ->
                Navigation.goTo(AdmissionConfirmationPage.getScene())
        );

        notices.setOnAction(e ->
                Navigation.goTo(NoticeBoardPage.getScene())
        );

        profile.setOnAction(e ->
                Navigation.goTo(StudentProfilePage.getScene())
        );

        help.setOnAction(e ->
                Navigation.goTo(HelpCentrePage.getScene())
        );

        logout.setOnAction(e ->
                Navigation.goTo(WelcomePage.getScene())
        );

        VBox menu = new VBox(
                5,
                dashboard,
                application,
                merit,
                college,
                preference,
                cap,
                admission,
                notices,
                profile,
                help
        );

        menu.setFillWidth(true);

        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        Label accountLabel = new Label("ACCOUNT");
        accountLabel.setStyle(
                "-fx-text-fill: #596359;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 0 8 8 8;"
        );

        VBox bottom = new VBox(
                8,
                accountLabel,
                logout
        );

        bottom.setFillWidth(true);

        VBox sidebar = new VBox(
                branding,
                menu,
                spacer,
                bottom
        );

        sidebar.setPadding(new Insets(22, 14, 18, 14));
        sidebar.setPrefWidth(245);
        sidebar.setMinWidth(245);
        sidebar.setAlignment(Pos.TOP_CENTER);

        sidebar.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: #202820;" +
                "-fx-border-width: 0 1 0 0;"
        );

        return sidebar;
    }

    private static Button createButton(String icon, String text) {

        Label iconLabel = new Label(icon);
        iconLabel.setMinWidth(25);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 16px;"
        );

        Label textLabel = new Label(text);
        textLabel.setStyle(
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        javafx.scene.layout.HBox content =
                new javafx.scene.layout.HBox(12, iconLabel, textLabel);

        content.setAlignment(Pos.CENTER_LEFT);

        Button button = new Button();
        button.setGraphic(content);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(46);
        button.setMinHeight(46);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 9px;" +
                "-fx-padding: 0 12 0 12;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e -> {

            iconLabel.setStyle(
                    "-fx-text-fill: " + LIME + ";" +
                    "-fx-font-size: 16px;"
            );

            textLabel.setStyle(
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;"
            );

            button.setStyle(
                    "-fx-background-color: " + HOVER + ";" +
                    "-fx-background-radius: 9px;" +
                    "-fx-padding: 0 12 0 12;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        button.setOnMouseExited(e -> {

            iconLabel.setStyle(
                    "-fx-text-fill: " + MUTED + ";" +
                    "-fx-font-size: 16px;"
            );

            textLabel.setStyle(
                    "-fx-text-fill: " + TEXT + ";" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;"
            );

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-padding: 0 12 0 12;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        return button;
    }
}