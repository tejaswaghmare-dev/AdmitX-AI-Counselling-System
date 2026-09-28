package com.admitx.view;

import com.admitx.controller.StudentInfoAddController;
import com.admitx.model.Student;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ReservationDetailsPage {

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

        Label title = new Label("Reservation Details");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description = new Label(
                "Reservation information already entered in Personal Details is filled automatically."
        );
        description.setWrapText(true);
        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading = new VBox(6, title, description);

        Label progressTitle = new Label("APPLICATION PROGRESS");
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
                createLine(false),
                createStep("6", "Documents", false),
                createLine(false),
                createStep("7", "Preview", false)
        );
        progress.setAlignment(Pos.CENTER_LEFT);

        VBox progressCard = new VBox(12, progressTitle, progress);
        progressCard.setPadding(new Insets(18));
        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        TextField category = createReadOnlyField();
        TextField caste = createReadOnlyField();

        ComboBox<String> validityCertificate =
                createYesNoCombo("Select Validity Certificate Status");

        ComboBox<String> ncl =
                createYesNoCombo("Select NCL Status");

        TextField ews = createReadOnlyField();

        TextField income =
                createTextField("Enter annual family income");

        TextField minority = createReadOnlyField();
        TextField defence = createReadOnlyField();

        ComboBox<String> orphan =
                createYesNoCombo("Select Orphan Status");

        category.setText(displayValue(data.getCategory()));
        caste.setText(displayValue(data.getCaste()));
        ews.setText(displayValue(data.getEws()));
        minority.setText(displayValue(data.getMinority()));
        defence.setText(displayValue(data.getDefence()));

        restoreCombo(validityCertificate, data.getValidityCertificate());
        restoreCombo(ncl, data.getNcl());
        restoreCombo(orphan, data.getOrphan());

        if (Student.hasValue(data.getIncome())) {
            income.setText(data.getIncome());
        }

        boolean validityRequired = data.requiresValidityCertificate();
        boolean nclRequired = data.requiresNcl();
        boolean incomeRequired = data.requiresIncome();

        if (!validityRequired) {
            validityCertificate.getItems().setAll(
                    Student.NOT_APPLICABLE
            );
            validityCertificate.setValue(Student.NOT_APPLICABLE);
            validityCertificate.setDisable(true);
        }

        if (!nclRequired) {
            ncl.getItems().setAll(
                    Student.NOT_APPLICABLE
            );
            ncl.setValue(Student.NOT_APPLICABLE);
            ncl.setDisable(true);
        }

        if (!incomeRequired && !Student.hasValue(data.getIncome())) {
            income.setPromptText("Optional");
        }

        GridPane form = new GridPane();
        form.setHgap(22);
        form.setVgap(20);

        addField(form, "Category", category, 0, 0, false);
        addField(form, "Caste", caste, 1, 0, false);

        addField(
                form,
                "Validity Certificate",
                validityCertificate,
                0,
                1,
                validityRequired
        );

        addField(
                form,
                "NCL",
                ncl,
                1,
                1,
                nclRequired
        );

        addField(form, "EWS", ews, 0, 2, false);

        addField(
                form,
                "Annual Income",
                income,
                1,
                2,
                incomeRequired
        );

        addField(form, "Minority", minority, 0, 3, false);
        addField(form, "Defence", defence, 1, 3, false);
        addField(form, "Orphan", orphan, 0, 4, true);

        ColumnConstraints firstColumn = new ColumnConstraints();
        firstColumn.setPercentWidth(50);

        ColumnConstraints secondColumn = new ColumnConstraints();
        secondColumn.setPercentWidth(50);

        form.getColumnConstraints().addAll(
                firstColumn,
                secondColumn
        );

        Label noteTitle = new Label("RESERVATION INFORMATION");
        noteTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label required = new Label(
                "* Only fields marked with * are mandatory for this student."
        );
        required.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + ERROR + ";"
        );

        Label note = new Label(
                "Category, Caste, EWS, Minority and Defence are reused from Personal Details. " +
                "Fields that do not apply are automatically stored as Not Applicable."
        );
        note.setWrapText(true);
        note.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox formCard = new VBox(
                16,
                noteTitle,
                required,
                form,
                note
        );

        formCard.setPadding(new Insets(24));
        formCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        Button backButton = new Button("←  Back");
        styleSecondaryButton(backButton);

        Button nextButton = new Button("Save & Continue  →");
        stylePrimaryButton(nextButton);

        backButton.setOnAction(e ->
                Navigation.goTo(
                        HomeUniversityPage.getScene()
                )
        );

        nextButton.setOnAction(e -> {

            if (!Student.hasValue(data.getCategory())) {
                showValidation(
                        "Category is missing. Please go back to Personal Details and select your Category."
                );
                return;
            }

            if (data.requiresCasteDetails()
                    && !Student.hasValue(data.getCaste())) {

                showValidation(
                        "Caste is required for the selected category. Please update it in Personal Details."
                );
                return;
            }

            if (validityRequired
                    && validityCertificate.getValue() == null) {

                showValidation(
                        "Please select Validity Certificate status."
                );

                validityCertificate.requestFocus();
                return;
            }

            if (nclRequired
                    && ncl.getValue() == null) {

                showValidation(
                        "Please select NCL status."
                );

                ncl.requestFocus();
                return;
            }

            String incomeText = income.getText().trim();

            if (incomeRequired && incomeText.isEmpty()) {

                showValidation(
                        "Annual Income is required because EWS or TFWS applies to this student."
                );

                income.requestFocus();
                return;
            }

            if (!incomeText.isEmpty()
                    && !incomeText.matches("\\d+(\\.\\d+)?")) {

                showValidation(
                        "Annual Income must contain numbers only."
                );

                income.requestFocus();
                return;
            }

            if (orphan.getValue() == null) {

                showValidation(
                        "Please select Orphan status."
                );

                orphan.requestFocus();
                return;
            }

            data.setValidityCertificate(
                    validityRequired
                            ? validityCertificate.getValue()
                            : Student.NOT_APPLICABLE
            );

            data.setNcl(
                    nclRequired
                            ? ncl.getValue()
                            : Student.NOT_APPLICABLE
            );

            data.setIncome(
                    incomeText.isEmpty()
                            ? Student.NOT_APPLICABLE
                            : incomeText
            );

            data.setOrphan(
                    orphan.getValue()
            );

            data.setCaste(
                    Student.valueOrNotApplicable(
                            data.getCaste()
                    )
            );

            data.setEws(
                    Student.valueOrNotApplicable(
                            data.getEws()
                    )
            );

            data.setMinority(
                    Student.valueOrNotApplicable(
                            data.getMinority()
                    )
            );

            data.setDefence(
                    Student.valueOrNotApplicable(
                            data.getDefence()
                    )
            );

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
                                    DocumentUploadPage.getScene()
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
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttons = new HBox(
                12,
                backButton,
                spacer,
                nextButton
        );
        buttons.setAlignment(Pos.CENTER_LEFT);

        VBox content = new VBox(
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

        BorderPane page = new BorderPane();
        page.setCenter(scrollPane);

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

    private static String displayValue(String value) {
        return Student.valueOrNotApplicable(value);
    }

    private static void restoreCombo(
            ComboBox<String> comboBox,
            String value
    ) {

        if (value == null || value.isBlank()) {
            return;
        }

        if (!comboBox.getItems().contains(value)) {
            comboBox.getItems().add(value);
        }

        comboBox.setValue(value);
    }

    private static TextField createTextField(
            String prompt
    ) {

        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefHeight(44);
        field.setMaxWidth(Double.MAX_VALUE);

        styleTextField(field);

        return field;
    }

    private static TextField createReadOnlyField() {

        TextField field =
                createTextField("");

        field.setEditable(false);
        field.setFocusTraversable(false);

        field.setStyle(
                "-fx-background-color: #111811;" +
                "-fx-border-color: #344234;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 0 12px;"
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

        field.setStyle(normal);

        field.setOnMouseEntered(e ->
                field.setStyle(hover)
        );

        field.setOnMouseExited(e ->
                field.setStyle(normal)
        );
    }

    private static ComboBox<String> createYesNoCombo(
            String prompt
    ) {

        ComboBox<String> comboBox =
                new ComboBox<>();

        comboBox.getItems().addAll(
                "Yes",
                "No"
        );

        comboBox.setPromptText(prompt);
        comboBox.setPrefHeight(44);
        comboBox.setMaxWidth(Double.MAX_VALUE);

        styleComboBox(comboBox);

        return comboBox;
    }

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

        comboBox.setStyle(normal);

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
                                    "-fx-background-color: transparent;" +
                                    "-fx-text-fill: " + MUTED + ";" +
                                    "-fx-font-size: 13px;"
                            );

                        } else {

                            setText(item);

                            setStyle(
                                    "-fx-background-color: transparent;" +
                                    "-fx-text-fill: " + WHITE + ";" +
                                    "-fx-font-size: 13px;"
                            );
                        }
                    }
                }
        );

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

                        setText(item);

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
                comboBox.setStyle(hover)
        );

        comboBox.setOnMouseExited(e ->
                comboBox.setStyle(normal)
        );
    }

    private static void addField(
            GridPane grid,
            String labelText,
            Control control,
            int column,
            int row,
            boolean mandatory
    ) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        HBox labelRow =
                new HBox(3, label);

        if (mandatory) {

            Label star =
                    new Label("*");

            star.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + ERROR + ";"
            );

            labelRow.getChildren().add(star);
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
                "-fx-background-color: "
                        + (active ? LIME : "#252D25") + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: "
                        + (active ? "#0B100B" : MUTED) + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        Label textLabel =
                new Label(text);

        textLabel.setStyle(
                "-fx-text-fill: "
                        + (active ? WHITE : MUTED) + ";" +
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

        line.setPrefWidth(25);
        line.setPrefHeight(2);

        line.setStyle(
                "-fx-background-color: "
                        + (active ? LIME : "#354035") + ";"
        );

        return line;
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(44);
        button.setMinWidth(165);

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

        button.setStyle(normal);

        button.setOnMouseEntered(e ->
                button.setStyle(hover)
        );

        button.setOnMouseExited(e ->
                button.setStyle(normal)
        );
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
                "Please complete the required information"
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
                "Could not save reservation details"
        );

        alert.setContentText(
                "Please check your connection and try again."
        );

        alert.showAndWait();
    }
}
