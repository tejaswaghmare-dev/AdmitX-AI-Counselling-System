package com.admitx.view;

import com.admitx.controller.StudentInfoAddController;
import com.admitx.model.Student;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class AddressDetailsPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#FFFFF3";
    private static final String MUTED = "#6D736D";

    public static Scene getScene() {

        Student data = Student.getInstance();

        Label title = new Label("Address Details");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description = new Label(
                "Enter your permanent and correspondence address details."
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
                createStep("2", "Address", true),
                createLine(true),
                createStep("3", "Academic", false),
                createLine(false),
                createStep("4", "Documents", false),
                createLine(false),
                createStep("5", "Preview", false)
        );

        progress.setAlignment(Pos.CENTER_LEFT);

        VBox progressCard = new VBox(
                10,
                progressTitle,
                progress
        );

        progressCard.setPadding(new Insets(16));

        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        // ==============================
        // PERMANENT ADDRESS
        // ==============================

        TextArea permanentAddress = createTextArea();

        TextField permanentState = createTextField();
        TextField permanentDistrict = createTextField();
        TextField permanentTaluka = createTextField();
        TextField permanentPincode = createTextField();

        permanentState.setPromptText("Enter state");
        permanentDistrict.setPromptText("Enter district");
        permanentTaluka.setPromptText("Enter taluka");
        permanentPincode.setPromptText("Enter pincode");

        // ==============================
        // CORRESPONDENCE ADDRESS
        // ==============================

        TextArea correspondenceAddress = createTextArea();

        TextField correspondenceState = createTextField();
        TextField correspondenceDistrict = createTextField();
        TextField correspondenceTaluka = createTextField();
        TextField correspondencePincode = createTextField();

        correspondenceState.setPromptText("Enter state");
        correspondenceDistrict.setPromptText("Enter district");
        correspondenceTaluka.setPromptText("Enter taluka");
        correspondencePincode.setPromptText("Enter pincode");

        // Reuse previously saved address data when the student returns.
        setIfPresent(permanentAddress, data.getPermanentAddress());
        setIfPresent(permanentState, data.getState());
        setIfPresent(permanentDistrict, data.getDistrict());
        setIfPresent(permanentTaluka, data.getTaluka());
        setIfPresent(permanentPincode, data.getPinCode());
        setIfPresent(correspondenceAddress, data.getCorrespondenceAddress());
        setIfPresent(correspondenceState, data.getCorrespondenceState());
        setIfPresent(correspondenceDistrict, data.getCorrespondenceDistrict());
        setIfPresent(correspondenceTaluka, data.getCorrespondenceTaluka());
        setIfPresent(correspondencePincode, data.getCorrespondencePinCode());

        // If no correspondence address was saved yet, the checkbox can still be used
        // to copy the permanent address live.

        VBox permanentCard = createAddressCard(
                "PERMANENT ADDRESS",
                permanentAddress,
                permanentState,
                permanentDistrict,
                permanentTaluka,
                permanentPincode
        );

        VBox correspondenceCard = createAddressCard(
                "CORRESPONDENCE ADDRESS",
                correspondenceAddress,
                correspondenceState,
                correspondenceDistrict,
                correspondenceTaluka,
                correspondencePincode
        );

        CheckBox sameAddress = new CheckBox(
                "Correspondence address is same as permanent address"
        );

        sameAddress.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 12px;"
        );

        // ============================================
        // SAME ADDRESS CHECKBOX
        // ============================================

        sameAddress.selectedProperty().addListener(
                (observable, oldValue, selected) -> {

                    if (selected) {

                        copyPermanentToCorrespondence(
                                permanentAddress,
                                permanentState,
                                permanentDistrict,
                                permanentTaluka,
                                permanentPincode,

                                correspondenceAddress,
                                correspondenceState,
                                correspondenceDistrict,
                                correspondenceTaluka,
                                correspondencePincode
                        );
                    }

                    correspondenceAddress.setDisable(selected);
                    correspondenceState.setDisable(selected);
                    correspondenceDistrict.setDisable(selected);
                    correspondenceTaluka.setDisable(selected);
                    correspondencePincode.setDisable(selected);
                }
        );

        // If permanent address changes while checkbox
        // is selected, update correspondence automatically.

        permanentAddress.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (sameAddress.isSelected()) {
                        correspondenceAddress.setText(newValue);
                    }
                }
        );

        permanentState.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (sameAddress.isSelected()) {
                        correspondenceState.setText(newValue);
                    }
                }
        );

        permanentDistrict.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (sameAddress.isSelected()) {
                        correspondenceDistrict.setText(newValue);
                    }
                }
        );

        permanentTaluka.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (sameAddress.isSelected()) {
                        correspondenceTaluka.setText(newValue);
                    }
                }
        );

        permanentPincode.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    if (sameAddress.isSelected()) {
                        correspondencePincode.setText(newValue);
                    }
                }
        );

        VBox addressSection = new VBox(
                15,
                permanentCard,
                sameAddress,
                correspondenceCard
        );

        // ==============================
        // BUTTONS
        // ==============================

        Button backButton =
                new Button("←  Back");

        styleSecondaryButton(backButton);

        Button nextButton =
                new Button("Save & Continue  →");

        stylePrimaryButton(nextButton);

        backButton.setOnAction(e ->
                Navigation.goTo(
                        PersonalDetailsPage.getScene()
                )
        );

        // ==============================
        // SAVE DATA
        // ==============================

        nextButton.setOnAction(e -> {

            String permanentAddressText =
                    permanentAddress.getText().trim();

            String correspondenceAddressText =
                    correspondenceAddress.getText().trim();

            String state =
                    permanentState.getText().trim();

            String district =
                    permanentDistrict.getText().trim();

            String taluka =
                    permanentTaluka.getText().trim();

            String pincode =
                    permanentPincode.getText().trim();

            // Basic validation

            if (permanentAddressText.isEmpty()) {

                showValidationError(
                        "Please enter your permanent address."
                );

                permanentAddress.requestFocus();
                return;
            }

            if (state.isEmpty()) {

                showValidationError(
                        "Please enter your state."
                );

                permanentState.requestFocus();
                return;
            }

            if (district.isEmpty()) {

                showValidationError(
                        "Please enter your district."
                );

                permanentDistrict.requestFocus();
                return;
            }

            if (taluka.isEmpty()) {

                showValidationError(
                        "Please enter your taluka."
                );

                permanentTaluka.requestFocus();
                return;
            }

            if (pincode.isEmpty()) {

                showValidationError(
                        "Please enter your pincode."
                );

                permanentPincode.requestFocus();
                return;
            }

            if (correspondenceAddressText.isEmpty()) {

                showValidationError(
                        "Please enter your correspondence address."
                );

                correspondenceAddress.requestFocus();
                return;
            }

            data.setPermanentAddress(
                    permanentAddressText
            );

            data.setCorrespondenceAddress(
                    correspondenceAddressText
            );
            data.setCorrespondenceState(correspondenceState.getText().trim());
            data.setCorrespondenceDistrict(correspondenceDistrict.getText().trim());
            data.setCorrespondenceTaluka(correspondenceTaluka.getText().trim());
            data.setCorrespondencePinCode(correspondencePincode.getText().trim());

            data.setState(state);
            data.setDistrict(district);
            data.setTaluka(taluka);
            data.setPinCode(pincode);

            nextButton.setDisable(true);
            backButton.setDisable(true);

            nextButton.setText("Saving...");

            AsyncTaskRunner.run(

                    () ->
                            new StudentInfoAddController()
                                    .saveApplicationSection(),

                    saved -> {

                        if (Boolean.TRUE.equals(saved)) {

                            Navigation.goTo(
                                    AcademicDetailsPage.getScene()
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
                addressSection,
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

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: transparent;"
        );

        BorderPane page =
                new BorderPane();

        page.setCenter(scrollPane);

        page.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Address Details",
                        page
                )
        );
    }

    private static void setIfPresent(TextInputControl control, String value) {
        if (value != null && !value.isBlank() && !"Not Applicable".equalsIgnoreCase(value)) {
            control.setText(value);
        }
    }

    // =========================================================
    // COPY ADDRESS
    // =========================================================

    private static void copyPermanentToCorrespondence(

            TextArea permanentAddress,
            TextField permanentState,
            TextField permanentDistrict,
            TextField permanentTaluka,
            TextField permanentPincode,

            TextArea correspondenceAddress,
            TextField correspondenceState,
            TextField correspondenceDistrict,
            TextField correspondenceTaluka,
            TextField correspondencePincode
    ) {

        correspondenceAddress.setText(
                permanentAddress.getText()
        );

        correspondenceState.setText(
                permanentState.getText()
        );

        correspondenceDistrict.setText(
                permanentDistrict.getText()
        );

        correspondenceTaluka.setText(
                permanentTaluka.getText()
        );

        correspondencePincode.setText(
                permanentPincode.getText()
        );
    }

    // =========================================================
    // VALIDATION ERROR
    // =========================================================

    private static void showValidationError(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING
                );

        alert.setTitle(
                "Incomplete Address"
        );

        alert.setHeaderText(
                "Please complete the required details"
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
                "Could not save address details"
        );

        alert.setContentText(
                "Please check your connection and try again."
        );

        alert.showAndWait();
    }

    // =========================================================
    // ADDRESS CARD
    // =========================================================

    private static VBox createAddressCard(

            String sectionTitle,

            TextArea address,

            TextField state,

            TextField district,

            TextField taluka,

            TextField pincode
    ) {

        Label title =
                new Label(sectionTitle);

        title.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        address.setPromptText(
                "Enter complete address"
        );

        GridPane form =
                new GridPane();

        form.setHgap(20);
        form.setVgap(20);

        addField(
                form,
                "Address",
                address,
                0,
                0,
                2,
                true
        );

        addField(
                form,
                "State",
                state,
                0,
                1,
                1,
                true
        );

        addField(
                form,
                "District",
                district,
                1,
                1,
                1,
                true
        );

        addField(
                form,
                "Taluka",
                taluka,
                0,
                2,
                1,
                true
        );

        addField(
                form,
                "Pincode",
                pincode,
                1,
                2,
                1,
                true
        );

        ColumnConstraints first =
                new ColumnConstraints();

        first.setPercentWidth(50);

        ColumnConstraints second =
                new ColumnConstraints();

        second.setPercentWidth(50);

        form.getColumnConstraints().addAll(
                first,
                second
        );

        VBox card =
                new VBox(
                        15,
                        title,
                        form
                );

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        return card;
    }

    // =========================================================
    // FIELD
    // =========================================================

    private static void addField(

            GridPane grid,

            String labelText,

            Control control,

            int column,

            int row,

            int span,

            boolean requiredField
    ) {

        Label label =
                new Label(labelText);

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
                    "-fx-text-fill: #FF6B6B;"
            );
            labelRow.getChildren().add(required);
        }

        VBox box =
                new VBox(
                        7,
                        labelRow,
                        control
                );

        box.setFillWidth(true);

        control.setMaxWidth(
                Double.MAX_VALUE
        );

        GridPane.setColumnSpan(
                box,
                span
        );

        GridPane.setFillWidth(
                box,
                true
        );

        GridPane.setHgrow(
                box,
                Priority.ALWAYS
        );

        grid.add(
                box,
                column,
                row
        );
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private static TextField createTextField() {

        TextField field =
                new TextField();

        field.setPrefHeight(40);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        styleControl(field);

        return field;
    }

    // =========================================================
    // TEXT AREA
    // =========================================================

    private static TextArea createTextArea() {

        TextArea area =
                new TextArea();

        area.setPrefRowCount(3);

        area.setPrefHeight(90);

        area.setMinHeight(90);

        area.setMaxWidth(
                Double.MAX_VALUE
        );

        area.setWrapText(true);

        area.setPromptText(
                "Enter complete address"
        );

        styleTextArea(area);

        return area;
    }

    // =========================================================
    // NORMAL FIELD STYLE
    // =========================================================

    private static void styleControl(
            Control control
    ) {

        String normalStyle =
                "-fx-background-color: #0F150F;" +
                "-fx-border-color: #344034;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;";

        String hoverStyle =
                "-fx-background-color: #111811;" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;";

        control.setStyle(
                normalStyle
        );

        control.setOnMouseEntered(e ->
                control.setStyle(
                        hoverStyle
                )
        );

        control.setOnMouseExited(e ->
                control.setStyle(
                        normalStyle
                )
        );
    }

    // =========================================================
    // TEXT AREA STYLE
    // =========================================================

    private static void styleTextArea(
            TextArea area
    ) {

        String normalStyle =
                "-fx-control-inner-background: #0F150F;" +
                "-fx-background-color: #0F150F;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-highlight-fill: " + LIME + ";" +
                "-fx-highlight-text-fill: #0B100B;" +
                "-fx-border-color: #344034;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 4px;";

        String hoverStyle =
                "-fx-control-inner-background: #111811;" +
                "-fx-background-color: #111811;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-highlight-fill: " + LIME + ";" +
                "-fx-highlight-text-fill: #0B100B;" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 4px;";

        area.setStyle(
                normalStyle
        );

        area.setOnMouseEntered(e ->
                area.setStyle(
                        hoverStyle
                )
        );

        area.setOnMouseExited(e ->
                area.setStyle(
                        normalStyle
                )
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
                26,
                26
        );

        numberLabel.setAlignment(
                Pos.CENTER
        );

        numberLabel.setStyle(
                "-fx-background-color: " +
                        (
                                active
                                        ? LIME
                                        : "#252D25"
                        ) +
                        ";" +

                        "-fx-background-radius: 50%;" +

                        "-fx-text-fill: " +
                        (
                                active
                                        ? "#0B100B"
                                        : MUTED
                        ) +
                        ";" +

                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;"
        );

        Label textLabel =
                new Label(text);

        textLabel.setStyle(
                "-fx-text-fill: " +
                        (
                                active
                                        ? WHITE
                                        : MUTED
                        ) +
                        ";" +

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

    // =========================================================
    // PROGRESS LINE
    // =========================================================

    private static Region createLine(
            boolean active
    ) {

        Region line =
                new Region();

        line.setPrefWidth(35);
        line.setPrefHeight(2);

        line.setStyle(
                "-fx-background-color: " +
                        (
                                active
                                        ? LIME
                                        : "#293229"
                        ) +
                        ";"
        );

        return line;
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

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

        String normalStyle =
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;";

        String hoverStyle =
                "-fx-background-color: #D0FF4D;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;";

        button.setStyle(
                normalStyle
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        hoverStyle
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        normalStyle
                )
        );
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

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

        String normalStyle =
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        String hoverStyle =
                "-fx-background-color: #202B20;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        button.setStyle(
                normalStyle
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        hoverStyle
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        normalStyle
                )
        );
    }
}