package com.example.view;

import com.example.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Sidebar {

    public static VBox create() {

        Button dashboard = createButton("Dashboard");
        Button application = createButton("Application");
        Button merit = createButton("Merit List");
        Button college = createButton("College Search");
        Button preference = createButton("Preference Filling");
        Button cap = createButton("CAP Rounds");
        Button admission = createButton("Admission");
        Button notices = createButton("Notices");
        Button profile = createButton("Profile");
        Button help = createButton("Help");
        Button logout = createButton("Logout");

        dashboard.setOnAction(e ->
                Navigation.goTo(StudentDashboardPage.getScene())
        );

        application.setOnAction(e ->
                Navigation.goTo(PersonalDetailsPage.getScene())
        );

        merit.setOnAction(e ->
            Navigation.goTo(
                    ProvisionalMeritPage.getScene()
            )
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

        VBox sidebar = new VBox(
                8,
                dashboard,
                application,
                merit,
                college,
                preference,
                cap,
                admission,
                notices,
                profile,
                help,
                logout
        );

        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(220);
        sidebar.setAlignment(Pos.TOP_CENTER);

        sidebar.setStyle(
                "-fx-background-color: #0A0A0A;"
        );

        return sidebar;
    }

    private static Button createButton(String text) {

        Button button = new Button(text);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(42);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #BEF264;" +
                "-fx-font-size: 14px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 15 0 15;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: #1A2E05;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-alignment: CENTER_LEFT;" +
                        "-fx-padding: 0 15 0 15;" +
                        "-fx-background-radius: 6px;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: #BEF264;" +
                        "-fx-font-size: 14px;" +
                        "-fx-alignment: CENTER_LEFT;" +
                        "-fx-padding: 0 15 0 15;"
                )
        );

        return button;
    }
}