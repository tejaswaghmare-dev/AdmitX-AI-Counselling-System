package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class OptionFormManagementPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Option Form Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Control choice filling and review student preference submissions."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        Label statusBadge =
                new Label(
                        "●  CHOICE FILLING CLOSED"
                );

        statusBadge.setStyle(
                "-fx-background-color: #211F0F;" +
                "-fx-text-fill: #E7D65A;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 7 12 7 12;" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: #665F20;" +
                "-fx-border-radius: 18px;"
        );

        Label currentStatus =
                new Label("Current Status");

        currentStatus.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label currentValue =
                new Label("Choice Filling Closed");

        currentValue.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label statusDescription =
                new Label(
                        "Students cannot currently add or modify their college preferences."
                );

        statusDescription.setWrapText(true);

        statusDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox statusCard =
                new VBox(
                        10,
                        statusBadge,
                        currentStatus,
                        currentValue,
                        statusDescription
                );

        statusCard.setPadding(
                new Insets(20)
        );

        statusCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        Button open =
                createPrimaryAction(
                        "Open Choice Filling",
                        "Allow eligible students to add and modify their preferences."
                );

        Button close =
                createAction(
                        "Close Choice Filling",
                        "Stop students from making further changes to their option forms."
                );

        Button view =
                createAction(
                        "View Student Preferences",
                        "Review submitted college and branch preference lists."
                );

        open.setOnAction(e ->
                showMessage(
                        "Choice Filling",
                        "Choice filling has been opened successfully."
                )
        );

        close.setOnAction(e ->
                showMessage(
                        "Choice Filling",
                        "Choice filling has been closed successfully."
                )
        );

        view.setOnAction(e ->
                showMessage(
                        "Student Preferences",
                        "Student preference list opened."
                )
        );

        VBox actionsCard =
                new VBox(
                        12,
                        createSectionTitle("OPTION FORM ACTIONS"),
                        open,
                        close,
                        view
                );

        actionsCard.setPadding(
                new Insets(20)
        );

        actionsCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        VBox overviewCard =
                new VBox(
                        12,
                        createSectionTitle("OPTION FORM OVERVIEW"),
                        createStatRow(
                                "Eligible Students",
                                "1,184"
                        ),
                        createStatRow(
                                "Forms Started",
                                "1,063"
                        ),
                        createStatRow(
                                "Forms Locked",
                                "987"
                        ),
                        createStatRow(
                                "Pending",
                                "76"
                        )
                );

        overviewCard.setPadding(
                new Insets(20)
        );

        overviewCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        HBox lower =
                new HBox(
                        16,
                        actionsCard,
                        overviewCard
                );

        HBox.setHgrow(
                actionsCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                overviewCard,
                Priority.ALWAYS
        );

        Label note =
                new Label(
                        "Close choice filling only after the official deadline. "
                        + "Students with unlocked forms may lose their preference changes."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;"
        );

        VBox root =
                new VBox(
                        20,
                        heading,
                        statusCard,
                        lower,
                        note
                );

        root.setPadding(
                new Insets(5)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "Option Form",
                        root
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    private static Button createAction(
            String title,
            String description
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox text =
                new VBox(
                        3,
                        titleLabel,
                        descriptionLabel
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label arrow =
                new Label("→");

        arrow.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 16px;"
        );

        HBox graphic =
                new HBox(
                        10,
                        text,
                        spacer,
                        arrow
                );

        graphic.setAlignment(
                Pos.CENTER_LEFT
        );

        Button button =
                new Button();

        button.setGraphic(
                graphic
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                60
        );

        button.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    private static Button createPrimaryAction(
            String title,
            String description
    ) {

        Button button =
                createAction(
                        title,
                        description
                );

        button.setStyle(
                "-fx-background-color: #18220F;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    private static HBox createStatRow(
            String label,
            String value
    ) {

        Label labelText =
                new Label(label);

        labelText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label valueText =
                new Label(value);

        valueText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        HBox row =
                new HBox(
                        labelText,
                        spacer,
                        valueText
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(10)
        );

        row.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 7px;"
        );

        return row;
    }

    private static void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}