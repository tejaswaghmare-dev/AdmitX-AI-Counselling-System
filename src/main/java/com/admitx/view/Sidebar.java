package com.example.view;

import com.example.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

public class Sidebar {

    public static VBox create() {

        VBox sidebar = new VBox(5);
        sidebar.setPadding(new Insets(20, 15, 20, 15));
        sidebar.setStyle(
                "-fx-background-color: #0A0A0F;" +
                "-fx-border-color: rgba(74, 127, 181, 0.08);" +
                "-fx-border-width: 0 1 0 0;" +
                "-fx-min-width: 220px;" +
                "-fx-max-width: 220px;" +
                "-fx-pref-width: 220px;"
        );

        // Logo area
        VBox logoBox = new VBox(2);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setPadding(new Insets(0, 0, 20, 0));

        Label logoIcon = new Label("🎓");
        logoIcon.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-opacity: 0.6;"
        );

        Label brandName = new Label("AdmitX");
        brandName.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label brandSub = new Label("Student Portal");
        brandSub.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #5A7D9E;" +
                "-fx-opacity: 0.6;"
        );

        logoBox.getChildren().addAll(logoIcon, brandName, brandSub);

        // Separator
        Region separator = new Region();
        separator.setPrefHeight(1);
        separator.setStyle("-fx-background-color: rgba(74, 127, 181, 0.1);");
        separator.setPadding(new Insets(0, 0, 15, 0));

        // Navigation items
        VBox navItems = new VBox(8);
        navItems.setPadding(new Insets(0, 0, 15, 0));

        Button dashboard = createNavButton("📊", "Dashboard");
        Button application = createNavButton("📝", "Application");
        Button merit = createNavButton("📋", "Merit List");
        Button college = createNavButton("🏛️", "College Search");
        Button preference = createNavButton("✏️", "Preference Filling");
        Button cap = createNavButton("🔄", "CAP Rounds");
        Button admission = createNavButton("✅", "Admission");
        Button notices = createNavButton("📢", "Notices");
        Button profile = createNavButton("👤", "Profile");
        Button help = createNavButton("❓", "Help");
        Button logout = createNavButton("🚪", "Logout");

        dashboard.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));
        application.setOnAction(e -> Navigation.goTo(PersonalDetailsPage.getScene()));
        merit.setOnAction(e -> Navigation.goTo(ProvisionalMeritPage.getScene()));
        college.setOnAction(e -> Navigation.goTo(CollegeSearchPage.getScene()));
        preference.setOnAction(e -> Navigation.goTo(PreferenceFillingPage.getScene()));
        cap.setOnAction(e -> Navigation.goTo(CAPRound1Page.getScene()));
        admission.setOnAction(e -> Navigation.goTo(AdmissionConfirmationPage.getScene()));
        notices.setOnAction(e -> Navigation.goTo(NoticeBoardPage.getScene()));
        profile.setOnAction(e -> Navigation.goTo(StudentProfilePage.getScene()));
        help.setOnAction(e -> Navigation.goTo(HelpCentrePage.getScene()));
        logout.setOnAction(e -> Navigation.goTo(WelcomePage.getScene()));

        navItems.getChildren().addAll(
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

        // Bottom section
        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(15, 0, 0, 0));

        Region bottomSeparator = new Region();
        bottomSeparator.setPrefHeight(1);
        bottomSeparator.setStyle("-fx-background-color: rgba(74, 127, 181, 0.08);");

        Label versionLabel = new Label("v2.0.1");
        versionLabel.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.4;" +
                "-fx-padding: 10 0 0 0;"
        );

        bottomBox.getChildren().addAll(
                bottomSeparator,
                versionLabel
        );

        sidebar.getChildren().addAll(
                logoBox,
                separator,
                navItems,
                bottomBox
        );

        VBox.setVgrow(navItems, javafx.scene.layout.Priority.ALWAYS);

        return sidebar;
    }

    private static Button createNavButton(String icon, String text) {
        Button btn = new Button(icon + " " + text);
        btn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;"
        );
        
        btn.setOnMouseEntered(e -> {
            btn.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            );
        });
        
        btn.setOnMouseExited(e -> {
            btn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;"
            );
        });

        return btn;
    }
}
