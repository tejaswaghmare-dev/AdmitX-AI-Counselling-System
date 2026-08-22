package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class CounsellorLayout {

    private static final String BG = "#0B100B";
    private static final String PANEL = "#111811";
    private static final String TOP = "#0D120D";

    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    private static final String BORDER = "#293529";
    private static final String HOVER = "#1B2817";

    public static BorderPane create(
            String activePage,
            Node content
    ) {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        root.setLeft(
                createSidebar(activePage)
        );

        VBox main =
                new VBox();

        HBox topBar =
                createTopBar(activePage);

        StackPane contentArea =
                new StackPane(content);

        contentArea.setPadding(
                new Insets(25)
        );

        contentArea.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        VBox.setVgrow(
                contentArea,
                Priority.ALWAYS
        );

        main.getChildren().addAll(
                topBar,
                contentArea
        );

        root.setCenter(
                main
        );

        return root;
    }

    private static VBox createSidebar(
            String activePage
    ) {

        VBox sidebar =
                new VBox();

        sidebar.setPrefWidth(
                245
        );

        sidebar.setMinWidth(
                245
        );

        sidebar.setPadding(
                new Insets(
                        24,
                        14,
                        18,
                        14
                )
        );

        sidebar.setSpacing(
                5
        );

        sidebar.setStyle(
                "-fx-background-color: " + PANEL + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        Label logo =
                new Label("ADMITX");

        logo.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;"
        );

        Label role =
                new Label(
                        "AI COUNSELLOR PORTAL"
                );

        role.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        VBox logoBox =
                new VBox(
                        2,
                        logo,
                        role
                );

        logoBox.setPadding(
                new Insets(
                        4,
                        8,
                        24,
                        8
                )
        );

        sidebar.getChildren().add(
                logoBox
        );

        addButton(
                sidebar,
                "⌂",
                "Dashboard",
                "Dashboard",
                activePage,
                () -> Navigation.goTo(
                        CounsellorDashboardPage.getScene()
                )
        );

        addButton(
                sidebar,
                "◉",
                "Students",
                "Students",
                activePage,
                () -> Navigation.goTo(
                        StudentManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "▣",
                "Colleges",
                "Colleges",
                activePage,
                () -> Navigation.goTo(
                        CollegeManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "★",
                "Merit List",
                "Merit List",
                activePage,
                () -> Navigation.goTo(
                        MeritListManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "☷",
                "Option Form",
                "Option Form",
                activePage,
                () -> Navigation.goTo(
                        OptionFormManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "1",
                "CAP Round 1",
                "CAP Round 1",
                activePage,
                () -> Navigation.goTo(
                        CAPRound1ManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "2",
                "CAP Round 2",
                "CAP Round 2",
                activePage,
                () -> Navigation.goTo(
                        CAPRound2ManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "3",
                "CAP Round 3",
                "CAP Round 3",
                activePage,
                () -> Navigation.goTo(
                        CAPRound3ManagementPage.getScene()
                )
        );

        addButton(
                sidebar,
                "▤",
                "Reports",
                "Reports",
                activePage,
                () -> Navigation.goTo(
                        ReportsPage.getScene()
                )
        );

        addButton(
                sidebar,
                "●",
                "Notices",
                "Notices",
                activePage,
                () -> Navigation.goTo(
                        NoticeManagementPage.getScene()
                )
        );

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        sidebar.getChildren().add(
                spacer
        );

        Label account =
                new Label("ACCOUNT");

        account.setStyle(
                "-fx-text-fill: #596359;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 0 8 6 8;"
        );

        sidebar.getChildren().add(
                account
        );

        addButton(
                sidebar,
                "●",
                "Profile",
                "Profile",
                activePage,
                () -> Navigation.goTo(
                        CounsellorProfilePage.getScene()
                )
        );

        addButton(
                sidebar,
                "⇥",
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
            String icon,
            String text,
            String page,
            String activePage,
            Runnable action
    ) {

        Label iconLabel =
                new Label(icon);

        iconLabel.setMinWidth(
                24
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        Label textLabel =
                new Label(text);

        HBox content =
                new HBox(
                        11,
                        iconLabel,
                        textLabel
                );

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        Button button =
                new Button();

        button.setGraphic(
                content
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                44
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        boolean active =
                page.equals(activePage);

        if (active) {

            iconLabel.setStyle(
                    "-fx-text-fill: #0B100B;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;"
            );

            textLabel.setStyle(
                    "-fx-text-fill: #0B100B;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;"
            );

            button.setStyle(
                    "-fx-background-color: " + LIME + ";" +
                    "-fx-background-radius: 9px;" +
                    "-fx-padding: 0 12 0 12;" +
                    "-fx-cursor: hand;"
            );

        } else {

            applyNormalStyle(
                    button,
                    iconLabel,
                    textLabel
            );

            button.setOnMouseEntered(e ->
                    applyHoverStyle(
                            button,
                            iconLabel,
                            textLabel
                    )
            );

            button.setOnMouseExited(e ->
                    applyNormalStyle(
                            button,
                            iconLabel,
                            textLabel
                    )
            );
        }

        button.setOnAction(
                e -> action.run()
        );

        sidebar.getChildren().add(
                button
        );
    }

    private static void applyNormalStyle(
            Button button,
            Label icon,
            Label text
    ) {

        icon.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 15px;"
        );

        text.setStyle(
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 9px;" +
                "-fx-padding: 0 12 0 12;" +
                "-fx-cursor: hand;"
        );
    }

    private static void applyHoverStyle(
            Button button,
            Label icon,
            Label text
    ) {

        icon.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 15px;"
        );

        text.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        button.setStyle(
                "-fx-background-color: " + HOVER + ";" +
                "-fx-background-radius: 9px;" +
                "-fx-padding: 0 12 0 12;" +
                "-fx-cursor: hand;"
        );
    }

    private static HBox createTopBar(
            String pageTitle
    ) {

        Label title =
                new Label(pageTitle);

        title.setStyle(
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle =
                new Label(
                        "MHT CET CAP Counselling Management"
                );

        subtitle.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 11px;"
        );

        VBox pageInfo =
                new VBox(
                        3,
                        title,
                        subtitle
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label role =
                new Label("COUNSELLOR");

        role.setStyle(
                "-fx-background-color: #172117;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 10 6 10;" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: #2B3A2B;" +
                "-fx-border-radius: 16px;"
        );

        Label avatar =
                new Label("YA");

        avatar.setMinSize(
                34,
                34
        );

        avatar.setAlignment(
                Pos.CENTER
        );

        avatar.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Label user =
                new Label(
                        "Counsellor Admin"
                );

        user.setStyle(
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );

        HBox userBox =
                new HBox(
                        8,
                        role,
                        avatar,
                        user
                );

        userBox.setAlignment(
                Pos.CENTER
        );

        HBox top =
                new HBox(
                        pageInfo,
                        spacer,
                        userBox
                );

        top.setPadding(
                new Insets(
                        15,
                        25,
                        15,
                        25
                )
        );

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        top.setMinHeight(
                74
        );

        top.setStyle(
                "-fx-background-color: " + TOP + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1 0;"
        );

        return top;
    }
}