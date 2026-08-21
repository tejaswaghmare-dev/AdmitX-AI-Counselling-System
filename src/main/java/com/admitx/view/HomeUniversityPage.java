package com.admitx.view;

import com.admitx.model.ApplicationData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class HomeUniversityPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        Label title = new Label(
                "Home University & Eligibility"
        );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description = new Label(
                "Provide your home university, candidate type and domicile information."
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

                createStep("5", "Documents", false),
                createLine(false),

                createStep("6", "Preview", false)
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

        ComboBox<String> state =
                createComboBox(
                        "Select State",
                        "Maharashtra",
                        "Gujarat",
                        "Karnataka",
                        "Madhya Pradesh",
                        "Goa",
                        "Other"
                );

        ComboBox<String> homeUniversity =
                createComboBox(
                        "Select Home University",
                        "Savitribai Phule Pune University",
                        "University of Mumbai",
                        "Shivaji University",
                        "Rashtrasant Tukadoji Maharaj Nagpur University",
                        "Dr. Babasaheb Ambedkar Marathwada University",
                        "Other"
                );

        ComboBox<String> candidateType =
                createComboBox(
                        "Select Candidate Type",
                        "Maharashtra State Candidate",
                        "All India Candidate",
                        "Minority Candidate",
                        "Other"
                );

        ComboBox<String> maharashtraType =
                createComboBox(
                        "Select Maharashtra Type",
                        "Type A",
                        "Type B",
                        "Type C",
                        "Type D",
                        "Type E"
                );

        ComboBox<String> domicileStatus =
                createComboBox(
                        "Select Domicile Status",
                        "Maharashtra Domicile",
                        "Other State Domicile",
                        "Not Applicable"
                );

        GridPane form = new GridPane();

        form.setHgap(20);
        form.setVgap(20);

        addField(
                form,
                "State",
                state,
                0,
                0
        );

        addField(
                form,
                "Home University",
                homeUniversity,
                1,
                0
        );

        addField(
                form,
                "Candidate Type",
                candidateType,
                0,
                1
        );

        addField(
                form,
                "Maharashtra Type",
                maharashtraType,
                1,
                1
        );

        addField(
                form,
                "Domicile Status",
                domicileStatus,
                0,
                2
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

        Label eligibilityTitle =
                new Label("ELIGIBILITY INFORMATION");

        eligibilityTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label eligibilityText =
                new Label(
                        "Your candidate type and domicile status may affect " +
                        "CAP eligibility and seat category. Please make sure " +
                        "the information matches your official documents."
                );

        eligibilityText.setWrapText(true);

        eligibilityText.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox formCard = new VBox(
                18,
                eligibilityTitle,
                form,
                eligibilityText
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
                        AcademicDetailsPage.getScene()
                )
        );

        nextButton.setOnAction(e -> {

            data.setState(
                    state.getValue()
            );

            data.setHomeUniversity(
                    homeUniversity.getValue()
            );

            data.setCandidateType(
                    candidateType.getValue()
            );

            data.setMaharashtraType(
                    maharashtraType.getValue()
            );

            data.setDomicileStatus(
                    domicileStatus.getValue()
            );

            Navigation.goTo(
                    ReservationDetailsPage.getScene()
            );
        });

        Region spacer = new Region();

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
                        "Home University & Eligibility",
                        page
                )
        );
    }

    private static ComboBox<String> createComboBox(
            String prompt,
            String... items
    ) {

        ComboBox<String> comboBox =
                new ComboBox<>();

        comboBox.getItems().addAll(
                items
        );

        comboBox.setPromptText(
                prompt
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

        line.setPrefWidth(30);
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