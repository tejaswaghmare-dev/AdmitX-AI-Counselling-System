package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class HelpCentrePage {

    public static Scene getScene() {

        Label title =
                new Label("Help Centre");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        TitledPane faq =
                new TitledPane(
                        "FAQ",
                        new Label(
                                "Frequently asked questions " +
                                "about registration, documents, " +
                                "merit list and CAP rounds."
                        )
                );

        TitledPane counsellor =
                new TitledPane(
                        "Counsellor Contact",
                        new Label(
                                "Dummy Counsellor: " +
                                "020-12345678\n" +
                                "Email: counsellor@example.com"
                        )
                );

        TitledPane guide =
                new TitledPane(
                        "User Guide",
                        new Label(
                                "Complete registration, application, " +
                                "document verification and option form."
                        )
                );

        TitledPane cap =
                new TitledPane(
                        "CAP Process Guide",
                        new Label(
                                "Registration → Application → " +
                                "Merit List → Option Form → " +
                                "CAP Rounds → Admission"
                        )
                );

        faq.setExpanded(false);
        counsellor.setExpanded(false);
        guide.setExpanded(false);
        cap.setExpanded(false);

        VBox help =
                new VBox(
                        10,
                        faq,
                        counsellor,
                        guide,
                        cap
                );

        help.setMaxWidth(800);

        Button dashboard =
                new Button("Dashboard");

        dashboard.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content =
                new VBox(
                        25,
                        title,
                        help,
                        dashboard
                );

        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Help Centre",
                        content
                )
        );
    }
}