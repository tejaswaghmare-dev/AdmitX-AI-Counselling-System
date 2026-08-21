package com.example.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class CounsellorLayout {

    private static final String BG = "#101410";
    private static final String PANEL = "#172017";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#A7B0A0";

    public static BorderPane create(
            String activePage,
            Node content
    ) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        root.setLeft(createSidebar(activePage));

        VBox main = new VBox();

        main.getChildren().add(
                createTopBar()
        );

        StackPane contentArea =
                new StackPane(content);

        contentArea.setPadding(
                new Insets(25)
        );

        VBox.setVgrow(
                contentArea,
                Priority.ALWAYS
        );

        main.getChildren().add(
                contentArea
        );

        root.setCenter(main);

        return root;
    }

    private static VBox createSidebar(
            String activePage
    ) {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(240);

        sidebar.setPadding(
                new Insets(25, 18, 20, 18)
        );

        sidebar.setSpacing(8);

        sidebar.setStyle(
                "-fx-background-color: " + PANEL + ";"
        );

        Label logo =
                new Label("ADMITX AI");

        logo.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        Label role =
                new Label("COUNSELLOR PORTAL");

        role.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        VBox logoBox =
                new VBox(
                        4,
                        logo,
                        role
                );

        logoBox.setPadding(
                new Insets(0, 0, 25, 8)
        );

        sidebar.getChildren().add(
                logoBox
        );

        addButton(
                sidebar,
                "Dashboard",
                "Dashboard",
                activePage,
                () -> Navigation.goTo(
                        CounsellorDashboardPage.getScene()
                )
        );

        addButton(
                sidebar,
                "Students",
                "Students",
                activePage,
                () -> Navigation.goTo(
                        StudentManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "Colleges",
                "Colleges",
                activePage,
                () -> Navigation.goTo(
                        CollegeManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "Merit List",
                "Merit List",
                activePage,
                () -> Navigation.goTo(
                        MeritListManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "Option Form",
                "Option Form",
                activePage,
                () -> Navigation.goTo(
                        OptionFormManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "CAP Round 1",
                "CAP Round 1",
                activePage,
                () -> Navigation.goTo(
                        CAPRound1ManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "CAP Round 2",
                "CAP Round 2",
                activePage,
                () -> Navigation.goTo(
                        CAPRound2ManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "CAP Round 3",
                "CAP Round 3",
                activePage,
                () -> Navigation.goTo(
                        CAPRound3ManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "Reports",
                "Reports",
                activePage,
                () -> Navigation.goTo(
                        ReportsPage.getScene()
                )
        );

        addButton(
                sidebar,
                "Notices",
                "Notices",
                activePage,
                () -> Navigation.goTo(
                        NoticeManagementPage.getScene()
                )
        );

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        sidebar.getChildren().add(
                spacer
        );

        addButton(
                sidebar,
                "Profile",
                "Profile",
                activePage,
                () -> Navigation.goTo(
                        CounsellorProfilePage.getScene()
                )
        );

        addButton(
                sidebar,
                "Logout",
                "Logout",
                activePage,
                () -> Navigation.goTo(
                        CounsellorLoginPage.getScene()
                )
        );

        return sidebar;
    }

    private static void addButton(
            VBox sidebar,
            String text,
            String page,
            String activePage,
            Runnable action
    ) {

        Button button =
                new Button(text);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setPrefHeight(44);

        if (page.equals(activePage)) {

            button.setStyle(
                    "-fx-background-color: " + LIME + ";" +
                    "-fx-text-fill: #101410;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8px;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + TEXT + ";" +
                    "-fx-font-size: 14px;" +
                    "-fx-background-radius: 8px;"
            );

            button.setOnMouseEntered(e ->
                    button.setStyle(
                            "-fx-background-color: #263326;" +
                            "-fx-text-fill: " + LIME + ";" +
                            "-fx-background-radius: 8px;"
                    )
            );

            button.setOnMouseExited(e ->
                    button.setStyle(
                            "-fx-background-color: transparent;" +
                            "-fx-text-fill: " + TEXT + ";" +
                            "-fx-background-radius: 8px;"
                    )
            );
        }

        button.setOnAction(e -> action.run());

        sidebar.getChildren().add(button);
    }

    private static HBox createTopBar() {

        HBox top =
                new HBox();

        top.setPadding(
                new Insets(18, 25, 18, 25)
        );

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        top.setStyle(
                "-fx-background-color: #141914;" +
                "-fx-border-color: #293329;" +
                "-fx-border-width: 0 0 1px 0;"
        );

        Label title =
                new Label("Counsellor Dashboard");

        title.setStyle(
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label user =
                new Label("Counsellor Admin");

        user.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-weight: bold;"
        );

        top.getChildren().addAll(
                title,
                spacer,
                user
        );

        return top;
    }
}