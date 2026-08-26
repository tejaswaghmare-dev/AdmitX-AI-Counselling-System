package com.admitx.view;


import com.admitx.controller.StudentInfoAddController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class PersonalDetailsPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        StudentInfoAddController controller = new StudentInfoAddController();

        Label title = new Label("Personal Details");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description = new Label(
                "Enter your basic personal information as mentioned in your application documents."
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
                createStep("2", "Address", false),
                createLine(false),
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

        TextField candidateName = createTextField();
        TextField fatherName = createTextField();
        TextField motherName = createTextField();
        DatePicker dob = new DatePicker();

        styleControl(dob);

        ComboBox<String> gender = createComboBox(
                "Male",
                "Female",
                "Other"
        );

        ComboBox<String> nationality = createComboBox(
                "Indian",
                "Other"
        );

        TextField aadhaar = createTextField();

        ComboBox<String> category = createComboBox(
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

        TextField religion = createTextField();
        TextField caste = createTextField();

        ComboBox<String> minority = createYesNoCombo();
        ComboBox<String> pwd = createYesNoCombo();
        ComboBox<String> defence = createYesNoCombo();
        ComboBox<String> tfws = createYesNoCombo();
        ComboBox<String> ews = createYesNoCombo();

        GridPane form = new GridPane();

        form.setHgap(20);
        form.setVgap(20);
        form.setPadding(new Insets(22));

        addField(form, "Candidate Name", candidateName, 0, 0);
        addField(form, "Father's Name", fatherName, 1, 0);

        addField(form, "Mother's Name", motherName, 0, 1);
        addField(form, "Gender", gender, 1, 1);

        addField(form, "Date of Birth", dob, 0, 2);
        addField(form, "Nationality", nationality, 1, 2);

        addField(form, "Aadhaar (Dummy)", aadhaar, 0, 3);
        addField(form, "Category", category, 1, 3);

        addField(form, "Religion", religion, 0, 4);
        addField(form, "Caste", caste, 1, 4);

        addField(form, "Minority", minority, 0, 5);
        addField(form, "PwD", pwd, 1, 5);

        addField(form, "Defence", defence, 0, 6);
        addField(form, "TFWS", tfws, 1, 6);

        addField(form, "EWS", ews, 0, 7);

        ColumnConstraints firstColumn = new ColumnConstraints();
        firstColumn.setPercentWidth(50);

        ColumnConstraints secondColumn = new ColumnConstraints();
        secondColumn.setPercentWidth(50);

        form.getColumnConstraints().addAll(
                firstColumn,
                secondColumn
        );

        VBox formCard = new VBox(form);

        formCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        Button backButton = new Button("←  Back");

        styleSecondaryButton(backButton);

        Button nextButton = new Button(
                "Save & Continue  →"
        );

        stylePrimaryButton(nextButton);

        backButton.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        nextButton.setOnAction(e -> {

            String name = candidateName.getText();
            System.out.println(name);

            String fname = fatherName.getText();
            System.out.println(fname);

            String mname  = motherName.getText();
            System.out.println(mname);
            String gende = gender.getValue();
            System.out.println(gende);

            String dbirth = " ";
            if (dob.getValue() != null) {
                dbirth = dob.getValue().toString();
            }
            System.out.println(dbirth);


            String nation = nationality.getValue();
            System.out.println(nation);

            String adhar  = aadhaar.getText();
            System.out.println(adhar);
            
            String cate = category.getValue();
            System.out.println(cate);

            String reli = religion.getText();
            System.out.println(reli);

            String cast = caste.getText();
            System.out.println(cast);

            String minor = minority.getValue();
            System.out.println(minor);

            String pwdd = pwd.getValue();
            System.out.println(pwdd);

            String defen = defence.getValue();
            System.out.println(defen);

            String tf = tfws.getValue();
            System.out.println(tf);
            
            String ew = ews.getValue();
            System.out.println(ew);

            controller.addStudentInfo(
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

            


            Navigation.goTo(
                    AddressDetailsPage.getScene()
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

        content.setPadding(new Insets(5));
        content.setFillWidth(true);

        ScrollPane scrollPane = new ScrollPane(content);

        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        BorderPane page = new BorderPane();

        page.setCenter(scrollPane);

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

    private static TextField createTextField() {

        TextField field = new TextField();

        field.setPrefHeight(40);
        field.setMaxWidth(Double.MAX_VALUE);

        styleControl(field);

        return field;
    }

    private static ComboBox<String> createComboBox(
            String... items
    ) {

        ComboBox<String> comboBox =
                new ComboBox<>();

        comboBox.getItems().addAll(items);

        comboBox.setPromptText("Select");

        comboBox.setPrefHeight(40);
        comboBox.setMaxWidth(Double.MAX_VALUE);

        styleControl(comboBox);

        return comboBox;
    }

    private static ComboBox<String> createYesNoCombo() {

        return createComboBox(
                "Yes",
                "No"
        );
    }

    private static void styleControl(Control control) {

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

    private static void addField(
            GridPane grid,
            String labelText,
            Control control,
            int column,
            int row
    ) {

        Label label = new Label(labelText);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        VBox box = new VBox(
                7,
                label,
                control
        );

        GridPane.setFillWidth(box, true);

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

        numberLabel.setMinSize(26, 26);
        numberLabel.setAlignment(Pos.CENTER);

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

        HBox step = new HBox(
                6,
                numberLabel,
                textLabel
        );

        step.setAlignment(Pos.CENTER_LEFT);

        return step;
    }

    private static Region createLine(
            boolean active
    ) {

        Region line = new Region();

        line.setPrefWidth(35);
        line.setPrefHeight(2);

        line.setStyle(
                "-fx-background-color: " +
                        (active ? LIME : "#293229") + ";"
        );

        return line;
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(42);
        button.setPadding(
                new Insets(0, 20, 0, 20)
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
                new Insets(0, 20, 0, 20)
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