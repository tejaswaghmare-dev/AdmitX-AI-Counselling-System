package com.admitx.view;

import com.admitx.controller.StudentInfoAddController;
import com.admitx.model.Student;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AcademicDetailsPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#2F3B2F";
    private static final String FIELD_BG = "#0F150F";
    private static final String FIELD_HOVER = "#121A12";

    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String ERROR = "#FF6B6B";

    public static Scene getScene() {

        Student data = Student.getInstance();

        Label title =
                new Label("Academic Details");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description =
                new Label(
                        "Enter your academic qualifications and entrance examination details."
                );

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        description
                );

        // =====================================================
        // PROGRESS
        // =====================================================

        Label progressTitle =
                new Label(
                        "APPLICATION PROGRESS"
                );

        progressTitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox progress =
                new HBox(
                        8,

                        createStep(
                                "1",
                                "Personal",
                                true
                        ),

                        createLine(true),

                        createStep(
                                "2",
                                "Address",
                                true
                        ),

                        createLine(true),

                        createStep(
                                "3",
                                "Academic",
                                true
                        ),

                        createLine(false),

                        createStep(
                                "4",
                                "Documents",
                                false
                        ),

                        createLine(false),

                        createStep(
                                "5",
                                "Preview",
                                false
                        )
                );

        progress.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox progressCard =
                new VBox(
                        12,
                        progressTitle,
                        progress
                );

        progressCard.setPadding(
                new Insets(18)
        );

        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        // =====================================================
        // FIELDS
        // =====================================================

        TextField sscDetails =
                createTextField(
                        "Enter SSC percentage / details"
                );

        TextField hscDetails =
                createTextField(
                        "Enter HSC percentage / details"
                );

        TextField diplomaDetails =
                createTextField(
                        "Enter diploma percentage / details"
                );

        TextField pcmMarks =
                createTextField(
                        "Enter PCM marks"
                );

        TextField cetPercentile =
                createTextField(
                        "Enter MHT CET percentile"
                );

        TextField jeePercentile =
                createTextField(
                        "Enter JEE Main percentile"
                );

        TextField yearOfPassing =
                createTextField(
                        "Enter year of passing"
                );

        // Reuse already saved values when the student returns to this page.
        setIfPresent(sscDetails, data.getSscDetails());
        setIfPresent(hscDetails, data.getHscDetails());
        setIfPresent(diplomaDetails, data.getDiplomaDetails());
        setIfPresent(pcmMarks, data.getPcmMarks());
        setIfPresent(cetPercentile, data.getCetPercentile());
        setIfPresent(jeePercentile, data.getJeePercentile());
        setIfPresent(yearOfPassing, data.getYearOfPassing());

        // =====================================================
        // FORM
        // =====================================================

        GridPane form =
                new GridPane();

        form.setHgap(22);
        form.setVgap(20);

        addField(
                form,
                "SSC Details",
                sscDetails,
                0,
                0,
                true
        );

        addField(
                form,
                "HSC Details",
                hscDetails,
                1,
                0,
                false
        );

        addField(
                form,
                "Diploma Details",
                diplomaDetails,
                0,
                1,
                false
        );

        addField(
                form,
                "PCM Marks",
                pcmMarks,
                1,
                1,
                false
        );

        addField(
                form,
                "MHT CET Percentile",
                cetPercentile,
                0,
                2,
                false
        );

        addField(
                form,
                "JEE Main Percentile",
                jeePercentile,
                1,
                2,
                false
        );

        addField(
                form,
                "Year of Passing",
                yearOfPassing,
                0,
                3,
                true
        );

        ColumnConstraints first =
                new ColumnConstraints();

        first.setPercentWidth(50);

        ColumnConstraints second =
                new ColumnConstraints();

        second.setPercentWidth(50);

        form.getColumnConstraints()
                .addAll(
                        first,
                        second
                );

        Label sectionTitle =
                new Label(
                        "ACADEMIC INFORMATION"
                );

        sectionTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label requiredNote =
                new Label(
                        "* Required fields. HSC or Diploma is required; CET or JEE is required. PCM is required when HSC is used."
                );

        requiredNote.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + ERROR + ";"
        );

        Label academicNote =
                new Label(
                        "Make sure the academic information matches your official marksheets."
                );

        academicNote.setWrapText(true);

        academicNote.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox formCard =
                new VBox(
                        15,
                        sectionTitle,
                        requiredNote,
                        form,
                        academicNote
                );

        formCard.setPadding(
                new Insets(24)
        );

        formCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        Button backButton =
                new Button(
                        "←  Back"
                );

        styleSecondaryButton(
                backButton
        );

        Button nextButton =
                new Button(
                        "Save & Continue  →"
                );

        stylePrimaryButton(
                nextButton
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        AddressDetailsPage.getScene()
                )
        );

        // =====================================================
        // VALIDATION + SAVE
        // =====================================================

        nextButton.setOnAction(e -> {

            if (sscDetails.getText().trim().isEmpty()) {

                showValidation(
                        "SSC Details are required."
                );

                sscDetails.requestFocus();
                return;
            }

            boolean hasHsc = !hscDetails.getText().trim().isEmpty();
            boolean hasDiploma = !diplomaDetails.getText().trim().isEmpty();

            if (!hasHsc && !hasDiploma) {
                showValidation(
                        "Enter either HSC Details or Diploma Details."
                );
                hscDetails.requestFocus();
                return;
            }

            if (hasHsc && pcmMarks.getText().trim().isEmpty()) {
                showValidation(
                        "PCM Marks are required when HSC Details are entered."
                );
                pcmMarks.requestFocus();
                return;
            }

            boolean hasCet = !cetPercentile.getText().trim().isEmpty();
            boolean hasJee = !jeePercentile.getText().trim().isEmpty();

            if (!hasCet && !hasJee) {
                showValidation(
                        "Enter at least one entrance score: MHT CET Percentile or JEE Main Percentile."
                );
                cetPercentile.requestFocus();
                return;
            }

            if (yearOfPassing.getText().trim().isEmpty()) {

                showValidation(
                        "Year of Passing is required."
                );

                yearOfPassing.requestFocus();
                return;
            }

            data.setSscDetails(
                    sscDetails.getText().trim()
            );

            data.setHscDetails(
                    hscDetails.getText().trim()
            );

            data.setDiplomaDetails(
                    valueOrNotApplicable(diplomaDetails.getText())
            );

            data.setPcmMarks(
                    valueOrNotApplicable(pcmMarks.getText())
            );

            data.setCetPercentile(
                    valueOrNotApplicable(cetPercentile.getText())
            );

            data.setJeePercentile(
                    valueOrNotApplicable(jeePercentile.getText())
            );

            data.setYearOfPassing(
                    yearOfPassing.getText().trim()
            );

            nextButton.setDisable(true);
            backButton.setDisable(true);

            nextButton.setText(
                    "Saving..."
            );

            AsyncTaskRunner.run(

                    () ->
                            new StudentInfoAddController()
                                    .saveApplicationSection(),

                    saved -> {

                        if (Boolean.TRUE.equals(saved)) {

                            Navigation.goTo(
                                    HomeUniversityPage.getScene()
                            );

                        } else {

                            nextButton.setDisable(false);
                            backButton.setDisable(false);

                            nextButton.setText(
                                    "Save & Continue  →"
                            );

                            showSaveError();
                        }
                    },

                    error -> {

                        nextButton.setDisable(false);
                        backButton.setDisable(false);

                        nextButton.setText(
                                "Save & Continue  →"
                        );

                        showSaveError();
                    }
            );
        });

        // =====================================================
        // BOTTOM BUTTON BAR
        // =====================================================

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        backButton,
                        spacer,
                        nextButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // PAGE
        // =====================================================

        VBox content =
                new VBox(
                        22,
                        heading,
                        progressCard,
                        formCard,
                        buttons
                );

        content.setPadding(
                new Insets(
                        10,
                        15,
                        30,
                        15
                )
        );

        content.setFillWidth(true);

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: transparent;"
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
                        "Academic Details",
                        page
                )
        );
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private static TextField createTextField(
            String prompt
    ) {

        TextField field =
                new TextField();

        field.setPromptText(
                prompt
        );

        field.setPrefHeight(44);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        styleTextField(
                field
        );

        return field;
    }

    private static void styleTextField(
            TextField field
    ) {

        String normal =
                "-fx-background-color: " + FIELD_BG + ";" +
                "-fx-border-color: #3A493A;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 0 12px;";

        String hover =
                "-fx-background-color: " + FIELD_HOVER + ";" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 0 12px;";

        field.setStyle(
                normal
        );

        field.setOnMouseEntered(e ->
                field.setStyle(
                        hover
                )
        );

        field.setOnMouseExited(e ->
                field.setStyle(
                        normal
                )
        );
    }

    // =========================================================
    // FIELD
    // =========================================================

    private static void addField(
            GridPane grid,
            String text,
            Control control,
            int column,
            int row,
            boolean requiredField
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        HBox labelRow = new HBox(3, label);

        if (requiredField) {
            Label required = new Label("*");
            required.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + ERROR + ";"
            );
            labelRow.getChildren().add(required);
        }

        control.setMaxWidth(
                Double.MAX_VALUE
        );

        VBox box =
                new VBox(
                        8,
                        labelRow,
                        control
                );

        box.setFillWidth(true);

        GridPane.setHgrow(
                box,
                Priority.ALWAYS
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

    // =========================================================
    // PROGRESS
    // =========================================================

    private static HBox createStep(
            String number,
            String text,
            boolean active
    ) {

        Label numberLabel =
                new Label(
                        number
                );

        numberLabel.setMinSize(
                28,
                28
        );

        numberLabel.setAlignment(
                Pos.CENTER
        );

        numberLabel.setStyle(
                "-fx-background-color: " +
                        (active ? LIME : "#252D25") + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: " +
                        (active ? "#0B100B" : MUTED) + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        Label textLabel =
                new Label(
                        text
                );

        textLabel.setStyle(
                "-fx-text-fill: " +
                        (active ? WHITE : MUTED) + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        HBox step =
                new HBox(
                        7,
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

        line.setPrefWidth(38);
        line.setPrefHeight(2);

        line.setStyle(
                "-fx-background-color: " +
                        (active ? LIME : "#354035") + ";"
        );

        return line;
    }

    // =========================================================
    // BUTTONS
    // =========================================================

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(44);
        button.setMinWidth(165);

        String normal =
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;";

        String hover =
                "-fx-background-color: #D0FF4D;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;";

        button.setStyle(normal);

        button.setOnMouseEntered(e ->
                button.setStyle(hover)
        );

        button.setOnMouseExited(e ->
                button.setStyle(normal)
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(44);
        button.setMinWidth(110);

        String normal =
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #3A493A;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        String hover =
                "-fx-background-color: #202B20;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        button.setStyle(normal);

        button.setOnMouseEntered(e ->
                button.setStyle(hover)
        );

        button.setOnMouseExited(e ->
                button.setStyle(normal)
        );
    }

    // =========================================================
    // ALERTS
    // =========================================================

    private static void setIfPresent(TextField field, String value) {
        if (value != null && !value.isBlank() && !"Not Applicable".equalsIgnoreCase(value)) {
            field.setText(value);
        }
    }

    private static String valueOrNotApplicable(String value) {
        return value == null || value.trim().isEmpty()
                ? "Not Applicable"
                : value.trim();
    }

    private static void showValidation(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING
                );

        alert.setTitle(
                "Required Fields"
        );

        alert.setHeaderText(
                "Please complete all mandatory fields"
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    private static void showSaveError() {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "Save Failed"
        );

        alert.setHeaderText(
                "Could not save academic details"
        );

        alert.setContentText(
                "Please check your connection and try again."
        );

        alert.showAndWait();
    }
}