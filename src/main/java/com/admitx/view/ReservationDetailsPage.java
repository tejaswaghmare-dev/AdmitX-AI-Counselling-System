package com.admitx.view;

import com.admitx.model.ApplicationData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ReservationDetailsPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        Label title = new Label("Reservation Details");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description = new Label(
                "Provide your reservation, category and eligibility certificate details."
        );

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading = new VBox(
                6,
                title,
                description
        );

        Label progressTitle = new Label(
                "APPLICATION PROGRESS"
        );

        progressTitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox progress = new HBox(
                8,

                createStep("1", "Personal", true),
                createLine(true),

                createStep("2", "Address", true),
                createLine(true),

                createStep("3", "Academic", true),
                createLine(true),

                createStep("4", "University", true),
                createLine(true),

                createStep("5", "Reservation", true),
                createLine(true),

                createStep("6", "Documents", false),
                createLine(false),

                createStep("7", "Preview", false)
        );

        progress.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox progressCard = new VBox(
                10,
                progressTitle,
                progress
        );

        progressCard.setPadding(
                new Insets(16)
        );

        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        ComboBox<String> category =
                createYesNoCombo();

        TextField caste =
                createTextField();

        ComboBox<String> validityCertificate =
                createYesNoCombo();

        ComboBox<String> ncl =
                createYesNoCombo();

        ComboBox<String> ews =
                createYesNoCombo();

        TextField income =
                createTextField();

        ComboBox<String> minority =
                createYesNoCombo();

        ComboBox<String> defence =
                createYesNoCombo();

        ComboBox<String> orphan =
                createYesNoCombo();

        GridPane form = new GridPane();

        form.setHgap(20);
        form.setVgap(20);

        addField(
                form,
                "Category",
                category,
                0,
                0
        );

        addField(
                form,
                "Caste",
                caste,
                1,
                0
        );

        addField(
                form,
                "Validity Certificate",
                validityCertificate,
                0,
                1
        );

        addField(
                form,
                "NCL",
                ncl,
                1,
                1
        );

        addField(
                form,
                "EWS",
                ews,
                0,
                2
        );

        addField(
                form,
                "Income",
                income,
                1,
                2
        );

        addField(
                form,
                "Minority",
                minority,
                0,
                3
        );

        addField(
                form,
                "Defence",
                defence,
                1,
                3
        );

        addField(
                form,
                "Orphan",
                orphan,
                0,
                4
        );

        ColumnConstraints firstColumn =
                new ColumnConstraints();

        firstColumn.setPercentWidth(50);

        ColumnConstraints secondColumn =
                new ColumnConstraints();

        secondColumn.setPercentWidth(50);

        form.getColumnConstraints().addAll(
                firstColumn,
                secondColumn
        );

        Label noteTitle =
                new Label("RESERVATION INFORMATION");

        noteTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label note =
                new Label(
                        "Select the options that apply to your application. " +
                        "Certificate-related information should match your " +
                        "official documents."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox formCard = new VBox(
                18,
                noteTitle,
                form,
                note
        );

        formCard.setPadding(
                new Insets(22)
        );

        formCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        Button backButton =
                new Button("←  Back");

        styleSecondaryButton(
                backButton
        );

        Button nextButton =
                new Button("Save & Continue  →");

        stylePrimaryButton(
                nextButton
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        HomeUniversityPage.getScene()
                )
        );

        nextButton.setOnAction(e -> {

            data.setCategory(
                    category.getValue()
            );

            data.setCaste(
                    caste.getText()
            );

            data.setValidityCertificate(
                    validityCertificate.getValue()
            );

            data.setNcl(
                    ncl.getValue()
            );

            data.setEws(
                    ews.getValue()
            );

            data.setIncome(
                    income.getText()
            );

            data.setMinority(
                    minority.getValue()
            );

            data.setDefence(
                    defence.getValue()
            );

            data.setOrphan(
                    orphan.getValue()
            );

            Navigation.goTo(
                    DocumentUploadPage.getScene()
            );
        });

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons = new HBox(
                12,
                backButton,
                spacer,
                nextButton
        );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content = new VBox(
                22,
                heading,
                progressCard,
                formCard,
                buttons
        );

        content.setPadding(
                new Insets(5)
        );

        content.setFillWidth(true);

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        BorderPane page =
                new BorderPane();

        page.setCenter(
                scrollPane
        );

        page.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Reservation Details",
                        page
                )
        );
    }

    private static TextField createTextField() {

        TextField field =
                new TextField();

        field.setPrefHeight(40);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        styleControl(
                field
        );

        return field;
    }

    private static ComboBox<String> createYesNoCombo() {

        ComboBox<String> comboBox =
                new ComboBox<>();

        comboBox.getItems().addAll(
                "Yes",
                "No"
        );

        comboBox.setPromptText(
                "Select"
        );

        comboBox.setPrefHeight(40);

        comboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        styleControl(
                comboBox
        );

        return comboBox;
    }

    private static void addField(
            GridPane grid,
            String labelText,
            Control control,
            int column,
            int row
    ) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        VBox box =
                new VBox(
                        7,
                        label,
                        control
                );

        GridPane.setFillWidth(
                box,
                true
        );

        grid.add(
                box,
                column,
                row
        );
    }

    private static void styleControl(
            Control control
    ) {

        control.setStyle(
                "-fx-background-color: #0F150F;" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 13px;"
        );

        control.setOnMouseEntered(e ->
                control.setStyle(
                        "-fx-background-color: #111811;" +
                        "-fx-border-color: " + LIME + ";" +
                        "-fx-border-radius: 7px;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-text-fill: " + WHITE + ";" +
                        "-fx-font-size: 13px;"
                )
        );

        control.setOnMouseExited(e ->
                control.setStyle(
                        "-fx-background-color: #0F150F;" +
                        "-fx-border-color: #344034;" +
                        "-fx-border-radius: 7px;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-text-fill: " + WHITE + ";" +
                        "-fx-font-size: 13px;"
                )
        );
    }

    private static HBox createStep(
            String number,
            String text,
            boolean active
    ) {

        Label numberLabel =
                new Label(number);

        numberLabel.setMinSize(
                26,
                26
        );

        numberLabel.setAlignment(
                Pos.CENTER
        );

        numberLabel.setStyle(
                "-fx-background-color: " +
                        (active
                                ? LIME
                                : "#252D25") + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: " +
                        (active
                                ? "#0B100B"
                                : MUTED) + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        Label textLabel =
                new Label(text);

        textLabel.setStyle(
                "-fx-text-fill: " +
                        (active
                                ? WHITE
                                : MUTED) + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        HBox step =
                new HBox(
                        6,
                        numberLabel,
                        textLabel
                );

        step.setAlignment(
                Pos.CENTER_LEFT
        );

        return step;
    }

    private static Region createLine(
            boolean active
    ) {

        Region line =
                new Region();

        line.setPrefWidth(25);
        line.setPrefHeight(2);

        line.setStyle(
                "-fx-background-color: " +
                        (active
                                ? LIME
                                : "#293229") + ";"
        );

        return line;
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: #D0FF4D;" +
                        "-fx-text-fill: #0B100B;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: " + LIME + ";" +
                        "-fx-text-fill: #0B100B;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-cursor: hand;"
                )
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: #202B20;" +
                        "-fx-text-fill: " + WHITE + ";" +
                        "-fx-border-color: " + LIME + ";" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: #171F17;" +
                        "-fx-text-fill: " + WHITE + ";" +
                        "-fx-border-color: #344034;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
                )
        );
    }
}