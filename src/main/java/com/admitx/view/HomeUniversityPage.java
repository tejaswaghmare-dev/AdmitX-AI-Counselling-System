package com.admitx.view;

import com.admitx.controller.StudentInfoAddController;
import com.admitx.model.Student;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class HomeUniversityPage {

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

        // =====================================================
        // PAGE HEADING
        // =====================================================

        Label title =
                new Label(
                        "Home University & Eligibility"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description =
                new Label(
                        "Provide your home university, candidate type and domicile information."
                );

        description.setWrapText(true);

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
        // PROGRESS BAR
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

                        createLine(true),

                        createStep(
                                "4",
                                "University",
                                true
                        ),

                        createLine(false),

                        createStep(
                                "5",
                                "Documents",
                                false
                        ),

                        createLine(false),

                        createStep(
                                "6",
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
                        "Type E",
                        "Not Applicable"
                );

        ComboBox<String> domicileStatus =
                createComboBox(
                        "Select Domicile Status",
                        "Maharashtra Domicile",
                        "Other State Domicile",
                        "Not Applicable"
                );

        // Reuse values already entered in earlier sections.
        selectIfPresent(state, data.getState());
        selectIfPresent(homeUniversity, data.getHomeUniversity());
        selectIfPresent(candidateType, data.getCandidateType());
        selectIfPresent(maharashtraType, data.getMaharashtraType());
        selectIfPresent(domicileStatus, data.getDomicileStatus());

        // Maharashtra Type only applies to Maharashtra State Candidates.
        Runnable updateMaharashtraType = () -> {
            boolean applies = "Maharashtra State Candidate".equals(candidateType.getValue());
            maharashtraType.setDisable(!applies);
            if (!applies) {
                maharashtraType.setValue("Not Applicable");
            } else if ("Not Applicable".equals(maharashtraType.getValue())) {
                maharashtraType.setValue(null);
            }
        };
        candidateType.valueProperty().addListener((obs, oldValue, newValue) ->
                updateMaharashtraType.run());
        updateMaharashtraType.run();

        // =====================================================
        // FORM
        // =====================================================

        GridPane form =
                new GridPane();

        form.setHgap(22);
        form.setVgap(20);

        addField(
                form,
                "State",
                state,
                0,
                0,
                true
        );

        addField(
                form,
                "Home University",
                homeUniversity,
                1,
                0,
                true
        );

        addField(
                form,
                "Candidate Type",
                candidateType,
                0,
                1,
                true
        );

        addField(
                form,
                "Maharashtra Type",
                maharashtraType,
                1,
                1,
                false
        );

        addField(
                form,
                "Domicile Status",
                domicileStatus,
                0,
                2,
                true
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

        // =====================================================
        // SECTION TITLE
        // =====================================================

        Label eligibilityTitle =
                new Label(
                        "ELIGIBILITY INFORMATION"
                );

        eligibilityTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label requiredText =
                new Label(
                        "* Required fields depend on the selected candidate type."
                );

        requiredText.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + ERROR + ";"
        );

        Label eligibilityText =
                new Label(
                        "Your candidate type and domicile status may affect CAP eligibility "
                                + "and seat category. Please make sure the information matches "
                                + "your official documents."
                );

        eligibilityText.setWrapText(true);

        eligibilityText.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox formCard =
                new VBox(
                        16,
                        eligibilityTitle,
                        requiredText,
                        form,
                        eligibilityText
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

        // =====================================================
        // BACK ACTION
        // =====================================================

        backButton.setOnAction(e ->
                Navigation.goTo(
                        AcademicDetailsPage.getScene()
                )
        );

        // =====================================================
        // SAVE + VALIDATION
        // =====================================================

        nextButton.setOnAction(e -> {

            if (state.getValue() == null) {

                showValidation(
                        "Please select State."
                );

                state.requestFocus();
                return;
            }

            if (homeUniversity.getValue() == null) {

                showValidation(
                        "Please select Home University."
                );

                homeUniversity.requestFocus();
                return;
            }

            if (candidateType.getValue() == null) {

                showValidation(
                        "Please select Candidate Type."
                );

                candidateType.requestFocus();
                return;
            }

            if ("Maharashtra State Candidate".equals(candidateType.getValue())
                    && maharashtraType.getValue() == null) {

                showValidation(
                        "Please select Maharashtra Type."
                );

                maharashtraType.requestFocus();
                return;
            }

            if (domicileStatus.getValue() == null) {

                showValidation(
                        "Please select Domicile Status."
                );

                domicileStatus.requestFocus();
                return;
            }

            // =================================================
            // SAVE IN STUDENT MODEL
            // =================================================

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
                                    ReservationDetailsPage.getScene()
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
        // BUTTON BAR
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
        // PAGE CONTENT
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

        // =====================================================
        // SCROLL PANE
        // =====================================================

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
                        "Home University & Eligibility",
                        page
                )
        );
    }

    private static void selectIfPresent(ComboBox<String> comboBox, String value) {
        if (value == null || value.isBlank()) {
            return;
        }
        if (!comboBox.getItems().contains(value)) {
            comboBox.getItems().add(value);
        }
        comboBox.setValue(value);
    }

    // =========================================================
    // COMBO BOX
    // =========================================================

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

        comboBox.setPrefHeight(
                44
        );

        comboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        styleComboBox(
                comboBox
        );

        return comboBox;
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private static void styleComboBox(
            ComboBox<String> comboBox
    ) {

        String normal =
                "-fx-background-color: " + FIELD_BG + ";" +
                "-fx-border-color: #3A493A;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-mark-color: " + LIME + ";";

        String hover =
                "-fx-background-color: " + FIELD_HOVER + ";" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-mark-color: " + LIME + ";";

        comboBox.setStyle(
                normal
        );

        // Selected value / prompt
        comboBox.setButtonCell(
                new ListCell<>() {

                    @Override
                    protected void updateItem(
                            String item,
                            boolean empty
                    ) {

                        super.updateItem(
                                item,
                                empty
                        );

                        if (empty || item == null) {

                            setText(
                                    comboBox.getPromptText()
                            );

                            setStyle(
                                    "-fx-text-fill: " + MUTED + ";" +
                                    "-fx-background-color: transparent;" +
                                    "-fx-font-size: 13px;"
                            );

                        } else {

                            setText(
                                    item
                            );

                            setStyle(
                                    "-fx-text-fill: " + WHITE + ";" +
                                    "-fx-background-color: transparent;" +
                                    "-fx-font-size: 13px;"
                            );
                        }
                    }
                }
        );

        // Dropdown list
        comboBox.setCellFactory(listView ->
                new ListCell<>() {

                    @Override
                    protected void updateItem(
                            String item,
                            boolean empty
                    ) {

                        super.updateItem(
                                item,
                                empty
                        );

                        if (empty || item == null) {

                            setText(null);

                            setStyle(
                                    "-fx-background-color: #171F17;"
                            );

                            return;
                        }

                        setText(
                                item
                        );

                        String itemNormal =
                                "-fx-background-color: #171F17;" +
                                "-fx-text-fill: " + WHITE + ";" +
                                "-fx-font-size: 13px;" +
                                "-fx-padding: 9px 12px;" +
                                "-fx-cursor: hand;";

                        String itemHover =
                                "-fx-background-color: " + LIME + ";" +
                                "-fx-text-fill: #0B100B;" +
                                "-fx-font-size: 13px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 9px 12px;" +
                                "-fx-cursor: hand;";

                        setStyle(isSelected() ? itemHover : itemNormal);

                        setOnMouseEntered(e -> setStyle(itemHover));
                        setOnMouseExited(e ->
                                setStyle(isSelected() ? itemHover : itemNormal)
                        );
                    }
                }
        );

        comboBox.setOnShowing(e -> javafx.application.Platform.runLater(() -> {
            javafx.scene.control.ListView<?> popup =
                    (javafx.scene.control.ListView<?>) comboBox.lookup(".list-view");
            if (popup != null) {
                popup.setStyle(
                        "-fx-background-color: #171F17;" +
                        "-fx-control-inner-background: #171F17;" +
                        "-fx-border-color: #3A493A;" +
                        "-fx-border-width: 1px;"
                );
            }
        }));

        comboBox.setOnMouseEntered(e ->
                comboBox.setStyle(
                        hover
                )
        );

        comboBox.setOnMouseExited(e ->
                comboBox.setStyle(
                        normal
                )
        );
    }

    // =========================================================
    // FORM FIELD
    // =========================================================

    private static void addField(
            GridPane grid,
            String labelText,
            Control control,
            int column,
            int row,
            boolean requiredField
    ) {

        Label label =
                new Label(
                        labelText
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

        box.setFillWidth(
                true
        );

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
    // PROGRESS STEP
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

    // =========================================================
    // PROGRESS LINE
    // =========================================================

    private static Region createLine(
            boolean active
    ) {

        Region line =
                new Region();

        line.setPrefWidth(
                30
        );

        line.setPrefHeight(
                2
        );

        line.setStyle(
                "-fx-background-color: " +
                        (active ? LIME : "#354035") + ";"
        );

        return line;
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(
                44
        );

        button.setMinWidth(
                165
        );

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

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

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        hover
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        normal
                )
        );
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(
                44
        );

        button.setMinWidth(
                110
        );

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        String normal =
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #3A493A;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        String hover =
                "-fx-background-color: #202B20;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        hover
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        normal
                )
        );
    }

    // =========================================================
    // REQUIRED FIELD ALERT
    // =========================================================

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

    // =========================================================
    // SAVE ERROR
    // =========================================================

    private static void showSaveError() {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "Save Failed"
        );

        alert.setHeaderText(
                "Could not save university details"
        );

        alert.setContentText(
                "Please check your connection and try again."
        );

        alert.showAndWait();
    }
}