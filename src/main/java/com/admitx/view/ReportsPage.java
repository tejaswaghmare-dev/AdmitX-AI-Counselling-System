package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ReportsPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Reports");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Generate and review counselling reports across students, colleges and CAP rounds."
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

        Button studentReport =
                createReportButton(
                        "Student Report",
                        "Registered students, categories and verification status."
                );

        Button meritReport =
                createReportButton(
                        "Merit Report",
                        "Provisional and final merit ranking information."
                );

        Button collegeReport =
                createReportButton(
                        "College-wise Report",
                        "Seat allocation and admission information by college."
                );

        Button branchReport =
                createReportButton(
                        "Branch-wise Report",
                        "Seat availability and allotment grouped by branch."
                );

        Button categoryReport =
                createReportButton(
                        "Category-wise Report",
                        "Admission statistics grouped by reservation category."
                );

        Button roundReport =
                createReportButton(
                        "Round-wise Report",
                        "CAP Round 1, 2 and 3 allotment performance."
                );

        studentReport.setOnAction(e ->
                show("Student Report")
        );

        meritReport.setOnAction(e ->
                show("Merit Report")
        );

        collegeReport.setOnAction(e ->
                show("College-wise Report")
        );

        branchReport.setOnAction(e ->
                show("Branch-wise Report")
        );

        categoryReport.setOnAction(e ->
                show("Category-wise Report")
        );

        roundReport.setOnAction(e ->
                show("Round-wise Report")
        );

        GridPane reportGrid =
                new GridPane();

        reportGrid.setHgap(16);
        reportGrid.setVgap(16);

        reportGrid.add(
                studentReport,
                0,
                0
        );

        reportGrid.add(
                meritReport,
                1,
                0
        );

        reportGrid.add(
                collegeReport,
                0,
                1
        );

        reportGrid.add(
                branchReport,
                1,
                1
        );

        reportGrid.add(
                categoryReport,
                0,
                2
        );

        reportGrid.add(
                roundReport,
                1,
                2
        );

        reportGrid.getColumnConstraints()
                .addAll(
                        createColumn(),
                        createColumn()
                );

        VBox reportCard =
                new VBox(
                        14,
                        createSectionTitle(
                                "AVAILABLE REPORTS"
                        ),
                        reportGrid
                );

        reportCard.setPadding(
                new Insets(20)
        );

        reportCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        VBox overviewCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "REPORT OVERVIEW"
                        ),
                        createOverviewRow(
                                "Students",
                                "1,250"
                        ),
                        createOverviewRow(
                                "Verified Students",
                                "980"
                        ),
                        createOverviewRow(
                                "Participating Colleges",
                                "145"
                        ),
                        createOverviewRow(
                                "CAP Rounds Completed",
                                "2 / 3"
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

        Label note =
                new Label(
                        "Select any report to generate a dummy counselling report. "
                        + "You can later connect these actions to PDF, CSV or database exports."
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
                        reportCard,
                        overviewCard,
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
                        "Reports",
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

    private static Button createReportButton(
            String title,
            String description
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 14px;" +
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

        Label action =
                new Label("Generate Report  →");

        action.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox graphic =
                new VBox(
                        7,
                        titleLabel,
                        descriptionLabel,
                        action
                );

        graphic.setAlignment(
                Pos.CENTER_LEFT
        );

        Button button =
                new Button();

        button.setGraphic(
                graphic
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                105
        );

        button.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;" +
                "-fx-padding: 14px;" +
                "-fx-cursor: hand;"
        );

        GridPane.setHgrow(
                button,
                Priority.ALWAYS
        );

        return button;
    }

    private static HBox createOverviewRow(
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

    private static ColumnConstraints createColumn() {

        ColumnConstraints column =
                new ColumnConstraints();

        column.setPercentWidth(
                50
        );

        return column;
    }

    private static void show(
            String report
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("Report");
        alert.setHeaderText(report);

        alert.setContentText(
                report +
                " generated successfully."
        );

        alert.showAndWait();
    }
}