package com.admitx.view;

import com.admitx.model.Student;
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
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

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

        TextField permanentAddress = createTextField();
        TextField permanentState = createTextField();
        TextField permanentDistrict = createTextField();
        TextField permanentTaluka = createTextField();
        TextField permanentPincode = createTextField();

        TextField correspondenceAddress = createTextField();
        TextField correspondenceState = createTextField();
        TextField correspondenceDistrict = createTextField();
        TextField correspondenceTaluka = createTextField();
        TextField correspondencePincode = createTextField();

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

        sameAddress.setOnAction(e -> {

            if (sameAddress.isSelected()) {

                String paddress = permanentAddress.getText();
                System.out.println(paddress);


                String state = permanentState.getText();
                 System.out.println(state);
                
                String district = permanentDistrict.getText();
                 System.out.println(district);

                String taluka = permanentTaluka.getText();
                 System.out.println(taluka);

                String pincode =  permanentPincode.getText();
                 System.out.println(pincode);
            }
        });

        VBox addressSection = new VBox(
                15,
                permanentCard,
                sameAddress,
                correspondenceCard
        );

        Button backButton = new Button("←  Back");

        styleSecondaryButton(backButton);

        Button nextButton = new Button(
                "Save & Continue  →"
        );

        stylePrimaryButton(nextButton);

        backButton.setOnAction(e ->
                Navigation.goTo(
                        PersonalDetailsPage.getScene()
                )
        );

        nextButton.setOnAction(e -> {

            /*
             * Store the address data here when the
             * corresponding fields are available
             * in ApplicationData.
             */

            Navigation.goTo(
                    AcademicDetailsPage.getScene()
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
                        "Address Details",
                        page
                )
        );
    }

    private static VBox createAddressCard(
            String sectionTitle,
            TextField address,
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

        TextArea addressArea =
                new TextArea();

        addressArea.setPrefRowCount(3);
        addressArea.setWrapText(true);
        addressArea.setPromptText(
                "Enter complete address"
        );

        styleControl(addressArea);

        GridPane form =
                new GridPane();

        form.setHgap(20);
        form.setVgap(20);

        addField(
                form,
                "Address",
                addressArea,
                0,
                0,
                2
        );

        addField(
                form,
                "State",
                state,
                0,
                1,
                1
        );

        addField(
                form,
                "District",
                district,
                1,
                1,
                1
        );

        addField(
                form,
                "Taluka",
                taluka,
                0,
                2,
                1
        );

        addField(
                form,
                "Pincode",
                pincode,
                1,
                2,
                1
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

        VBox card = new VBox(
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

    private static void addField(
            GridPane grid,
            String labelText,
            Control control,
            int column,
            int row,
            int span
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

        GridPane.setColumnSpan(
                box,
                span
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

        line.setPrefWidth(35);
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