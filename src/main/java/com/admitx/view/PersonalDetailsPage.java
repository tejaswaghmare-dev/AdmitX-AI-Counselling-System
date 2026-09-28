package com.admitx.view;

import com.admitx.controller.StudentInfoAddController;
import com.admitx.model.Student;

import java.time.LocalDate;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PersonalDetailsPage {

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

        Label title = new Label("Personal Details");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description = new Label(
                "Enter your basic personal information as mentioned in your official documents."
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

        // =====================================================
        // PROGRESS
        // =====================================================

        Label progressTitle =
                new Label("APPLICATION PROGRESS");

        progressTitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox progress = new HBox(
                8,

                createStep("1", "Personal", true),
                createLine(true),

                createStep("2", "Address", false),
                createLine(false),

                createStep("3", "Academic", false),
                createLine(false),

                createStep("4", "Documents", false),
                createLine(false),

                createStep("5", "Preview", false)
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
        // PERSONAL FIELDS
        // =====================================================

        TextField candidateName =
                createTextField(
                        "Enter candidate full name"
                );

        TextField fatherName =
                createTextField(
                        "Enter father's full name"
                );

        TextField motherName =
                createTextField(
                        "Enter mother's full name"
                );

        ComboBox<String> gender =
                createComboBox(
                        "Select gender",
                        "Male",
                        "Female",
                        "Other"
                );

        DatePicker dob =
                createDatePicker();

        ComboBox<String> nationality =
                createComboBox(
                        "Select nationality",
                        "Indian",
                        "Other"
                );

        TextField aadhaar =
                createTextField(
                        "Enter 12 digit Aadhaar number"
                );

        ComboBox<String> category =
                createComboBox(
                        "Select category",
                        "Open",
                        "OBC",
                        "SC",
                        "ST",
                        "VJ/DT",
                        "NT-A",
                        "NT-B",
                        "NT-C",
                        "NT-D",
                        "EWS"
                );

        TextField religion =
                createTextField(
                        "Enter religion"
                );

        TextField caste =
                createTextField(
                        "Enter caste"
                );

        ComboBox<String> minority =
                createYesNoCombo(
                        "Select minority status"
                );

        ComboBox<String> pwd =
                createYesNoCombo(
                        "Select PwD status"
                );

        ComboBox<String> defence =
                createYesNoCombo(
                        "Select defence status"
                );

        ComboBox<String> tfws =
                createYesNoCombo(
                        "Select TFWS status"
                );

        ComboBox<String> ews =
                createYesNoCombo(
                        "Select EWS status"
                );

        Student data = Student.getInstance();

        setIfPresent(
                candidateName,
                Student.hasValue(data.getCandidateName())
                        ? data.getCandidateName()
                        : data.getUsername()
        );
        setIfPresent(fatherName, data.getFatherName());
        setIfPresent(motherName, data.getMotherName());
        selectIfPresent(gender, data.getGender());
        setDateIfPresent(dob, data.getDob());
        selectIfPresent(nationality, data.getNationality());
        setIfPresent(aadhaar, data.getAadhaar());
        selectIfPresent(category, data.getCategory());
        setIfPresent(religion, data.getReligion());
        setIfPresent(caste, data.getCaste());
        selectIfPresent(minority, data.getMinority());
        selectIfPresent(pwd, data.getPwd());
        selectIfPresent(defence, data.getDefence());
        selectIfPresent(tfws, data.getTfws());
        selectIfPresent(ews, data.getEws());

        // =====================================================
        // FORM
        // =====================================================

        GridPane form =
                new GridPane();

        form.setHgap(22);
        form.setVgap(20);

        addField(
                form,
                "Candidate Name",
                candidateName,
                0,
                0,
                true
        );

        addField(
                form,
                "Father's Name",
                fatherName,
                1,
                0,
                false
        );

        addField(
                form,
                "Mother's Name",
                motherName,
                0,
                1,
                false
        );

        addField(
                form,
                "Gender",
                gender,
                1,
                1,
                true
        );

        addField(
                form,
                "Date of Birth",
                dob,
                0,
                2,
                true
        );

        addField(
                form,
                "Nationality",
                nationality,
                1,
                2,
                true
        );

        addField(
                form,
                "Aadhaar (Dummy)",
                aadhaar,
                0,
                3,
                false
        );

        addField(
                form,
                "Category",
                category,
                1,
                3,
                true
        );

        addField(
                form,
                "Religion",
                religion,
                0,
                4,
                false
        );

        addField(
                form,
                "Caste (required for reserved category)",
                caste,
                1,
                4,
                false
        );

        addField(
                form,
                "Minority",
                minority,
                0,
                5,
                true
        );

        addField(
                form,
                "PwD",
                pwd,
                1,
                5,
                true
        );

        addField(
                form,
                "Defence",
                defence,
                0,
                6,
                true
        );

        addField(
                form,
                "TFWS",
                tfws,
                1,
                6,
                true
        );

        addField(
                form,
                "EWS",
                ews,
                0,
                7,
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

        Label sectionTitle =
                new Label("PERSONAL INFORMATION");

        sectionTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label requiredNote =
                new Label("* Basic identity fields are mandatory. Some personal fields may be Not Applicable.");

        requiredNote.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + ERROR + ";"
        );

        VBox formCard =
                new VBox(
                        18,
                        sectionTitle,
                        requiredNote,
                        form
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
                new Button("←  Back");

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
                        StudentDashboardPage.getScene()
                )
        );

        // =====================================================
        // SAVE + VALIDATION
        // =====================================================

        nextButton.setOnAction(e -> {

            if (candidateName.getText().trim().isEmpty()) {
                showValidation(
                        "Candidate Name is required."
                );

                candidateName.requestFocus();
                return;
            }

            if (gender.getValue() == null) {
                showValidation(
                        "Please select Gender."
                );

                gender.requestFocus();
                return;
            }

            if (dob.getValue() == null) {
                showValidation(
                        "Please select Date of Birth."
                );

                dob.requestFocus();
                return;
            }

            if (nationality.getValue() == null) {
                showValidation(
                        "Please select Nationality."
                );

                nationality.requestFocus();
                return;
            }

            if (!aadhaar.getText().trim().isEmpty()
                    && !aadhaar.getText().trim().matches("\\d{12}")) {
                showValidation(
                        "Aadhaar must contain exactly 12 digits."
                );

                aadhaar.requestFocus();
                return;
            }

            if (category.getValue() == null) {
                showValidation(
                        "Please select Category."
                );

                category.requestFocus();
                return;
            }

            boolean casteRequired =
                    category.getValue() != null
                            && !"Open".equalsIgnoreCase(category.getValue())
                            && !"EWS".equalsIgnoreCase(category.getValue());

            if (casteRequired && caste.getText().trim().isEmpty()) {
                showValidation(
                        "Caste is required for the selected reserved category."
                );
                caste.requestFocus();
                return;
            }

            if (minority.getValue() == null) {
                showValidation(
                        "Please select Minority status."
                );

                minority.requestFocus();
                return;
            }

            if (pwd.getValue() == null) {
                showValidation(
                        "Please select PwD status."
                );

                pwd.requestFocus();
                return;
            }

            if (defence.getValue() == null) {
                showValidation(
                        "Please select Defence status."
                );

                defence.requestFocus();
                return;
            }

            if (tfws.getValue() == null) {
                showValidation(
                        "Please select TFWS status."
                );

                tfws.requestFocus();
                return;
            }

            if (ews.getValue() == null) {
                showValidation(
                        "Please select EWS status."
                );

                ews.requestFocus();
                return;
            }

            String name =
                    candidateName.getText().trim();

            String fname =
                    valueOrNotApplicable(fatherName.getText());

            String mname =
                    valueOrNotApplicable(motherName.getText());

            String gende =
                    gender.getValue();

            String dbirth =
                    dob.getValue().toString();

            String nation =
                    nationality.getValue();

            String adhar =
                    valueOrNotApplicable(aadhaar.getText());

            String cate =
                    category.getValue();

            String reli =
                    valueOrNotApplicable(religion.getText());

            String cast =
                    valueOrNotApplicable(caste.getText());

            String minor =
                    minority.getValue();

            String pwdd =
                    pwd.getValue();

            String defen =
                    defence.getValue();

            String tf =
                    tfws.getValue();

            String ew =
                    ews.getValue();

            nextButton.setDisable(true);
            backButton.setDisable(true);

            nextButton.setText(
                    "Saving..."
            );

            AsyncTaskRunner.run(

                    () -> {

                        StudentInfoAddController controller =
                                new StudentInfoAddController();

                        return controller.addStudentInfo(
                                name,
                                fname,
                                mname,
                                gende,
                                dbirth,
                                nation,
                                adhar,
                                cate,
                                reli,
                                cast,
                                minor,
                                pwdd,
                                defen,
                                tf,
                                ew
                        );
                    },

                    saved -> {

                        if (Boolean.TRUE.equals(saved)) {

                            Navigation.goTo(
                                    AddressDetailsPage.getScene()
                            );

                        } else {

                            nextButton.setDisable(false);
                            backButton.setDisable(false);

                            nextButton.setText(
                                    "Save & Continue  →"
                            );

                            showSaveError(
                                    "Please check your connection and try again."
                            );
                        }
                    },

                    error -> {

                        nextButton.setDisable(false);
                        backButton.setDisable(false);

                        nextButton.setText(
                                "Save & Continue  →"
                        );

                        showSaveError(
                                error.getMessage() == null
                                        ? "Please check your connection and try again."
                                        : error.getMessage()
                        );
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
        // CONTENT
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
                        25,
                        15
                )
        );

        content.setFillWidth(true);

        ScrollPane scrollPane =
                new ScrollPane(content);

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
                        "Personal Details",
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

        comboBox.setPrefHeight(44);

        comboBox.setMaxWidth(
                Double.MAX_VALUE
        );

        styleComboBox(
                comboBox
        );

        return comboBox;
    }

    private static ComboBox<String> createYesNoCombo(
            String prompt
    ) {

        return createComboBox(
                prompt,
                "Yes",
                "No"
        );
    }

    // =========================================================
    // DATE PICKER
    // =========================================================

    private static DatePicker createDatePicker() {

        DatePicker picker =
                new DatePicker();

        picker.setPromptText(
                "Select date of birth"
        );

        picker.setPrefHeight(44);

        picker.setMaxWidth(
                Double.MAX_VALUE
        );

        String normal =
                "-fx-background-color: " + FIELD_BG + ";" +
                "-fx-border-color: #3A493A;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;";

        String hover =
                "-fx-background-color: " + FIELD_HOVER + ";" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;";

        picker.setStyle(
                normal
        );

        picker.getEditor().setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;"
        );

        picker.setOnMouseEntered(e ->
                picker.setStyle(
                        hover
                )
        );

        picker.setOnMouseExited(e ->
                picker.setStyle(
                        normal
                )
        );

        return picker;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

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

        // Makes selected value clearly visible
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

        // Makes dropdown items visible
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

                        } else {

                            setText(item);

                            String itemNormal =
                                    "-fx-background-color: #171F17;" +
                                    "-fx-text-fill: " + WHITE + ";" +
                                    "-fx-font-size: 13px;" +
                                    "-fx-padding: 8px 12px;" +
                                    "-fx-cursor: hand;";

                            String itemHover =
                                    "-fx-background-color: " + LIME + ";" +
                                    "-fx-text-fill: #0B100B;" +
                                    "-fx-font-size: 13px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-padding: 8px 12px;" +
                                    "-fx-cursor: hand;";

                            setStyle(isSelected() ? itemHover : itemNormal);

                            setOnMouseEntered(e -> setStyle(itemHover));
                            setOnMouseExited(e ->
                                    setStyle(isSelected() ? itemHover : itemNormal)
                            );
                        }
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
    // ADD FORM FIELD
    // =========================================================

    private static void addField(
            GridPane grid,
            String labelText,
            Control control,
            int column,
            int row,
            boolean required
    ) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        HBox labelBox =
                new HBox(
                        3,
                        label
                );

        if (required) {

            Label requiredMark =
                    new Label("*");

            requiredMark.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + ERROR + ";"
            );

            labelBox.getChildren().add(
                    requiredMark
            );
        }

        control.setMaxWidth(
                Double.MAX_VALUE
        );

        VBox box =
                new VBox(
                        8,
                        labelBox,
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
    // PROGRESS STEP
    // =========================================================

    private static HBox createStep(
            String number,
            String text,
            boolean active
    ) {

        Label numberLabel =
                new Label(number);

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
                new Label(text);

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

        line.setPrefWidth(38);
        line.setPrefHeight(2);

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

        button.setPrefHeight(44);

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

        button.setPrefHeight(44);

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

    private static void setIfPresent(TextInputControl field, String value) {
        if (Student.hasValue(value)) {
            field.setText(value.trim());
        }
    }

    private static void selectIfPresent(ComboBox<String> comboBox, String value) {
        if (Student.hasValue(value) && comboBox.getItems().contains(value.trim())) {
            comboBox.setValue(value.trim());
        }
    }

    private static void setDateIfPresent(DatePicker datePicker, String value) {
        if (!Student.hasValue(value)) {
            return;
        }
        try {
            datePicker.setValue(LocalDate.parse(value.trim()));
        } catch (Exception ignored) {
        }
    }

    private static String valueOrNotApplicable(String value) {
        return value == null || value.trim().isEmpty()
                ? "Not Applicable"
                : value.trim();
    }

    // =========================================================
    // VALIDATION
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

    private static void showSaveError(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "Save Failed"
        );

        alert.setHeaderText(
                "Could not save personal details"
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}