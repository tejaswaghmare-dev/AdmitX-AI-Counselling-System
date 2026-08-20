package com.admitx.view;



import com.admitx.model.ApplicationData;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PersonalDetailsPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("Personal Details");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Please provide your personal information");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Form Card
        VBox formCard = new VBox(20);
        formCard.setPadding(new Insets(25, 30, 30, 30));
        formCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        GridPane form = new GridPane();
        form.setHgap(40);
        form.setVgap(18);
        form.setPadding(new Insets(10, 0, 5, 0));

        String fieldStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 38px;" +
                "-fx-pref-width: 240px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-font-size: 14px;";

        String comboStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 38px;" +
                "-fx-pref-width: 240px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 10 0 10;" +
                "-fx-font-size: 14px;";

        TextField candidateName = new TextField();
        candidateName.setPromptText("Enter full name");
        candidateName.setStyle(fieldStyle);

        TextField fatherName = new TextField();
        fatherName.setPromptText("Enter father's name");
        fatherName.setStyle(fieldStyle);

        TextField motherName = new TextField();
        motherName.setPromptText("Enter mother's name");
        motherName.setStyle(fieldStyle);

        DatePicker dob = new DatePicker();
        dob.setPromptText("Select Date of Birth");
        dob.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 38px;" +
                "-fx-pref-width: 240px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-font-size: 14px;"
        );

        ComboBox<String> gender = new ComboBox<>();
        gender.getItems().addAll("Male", "Female", "Other");
        gender.setPromptText("Select Gender");
        gender.setStyle(comboStyle);

        ComboBox<String> nationality = new ComboBox<>();
        nationality.getItems().addAll("Indian", "Other");
        nationality.setPromptText("Select Nationality");
        nationality.setStyle(comboStyle);


        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll("Open", "OBC", "SC", "ST", "VJ/DT", "NT-A", "NT-B", "NT-C", "NT-D", "EWS");
        category.setPromptText("Select Category");
        category.setStyle(comboStyle);

       

        

        ComboBox<String> minority = new ComboBox<>();
        minority.getItems().addAll("Yes", "No");
        minority.setPromptText("Select");
        minority.setStyle(comboStyle);

        ComboBox<String> pwd = new ComboBox<>();
        pwd.getItems().addAll("Yes", "No");
        pwd.setPromptText("Select");
        pwd.setStyle(comboStyle);

        ComboBox<String> defence = new ComboBox<>();
        defence.getItems().addAll("Yes", "No");
        defence.setPromptText("Select");
        defence.setStyle(comboStyle);

        ComboBox<String> tfws = new ComboBox<>();
        tfws.getItems().addAll("Yes", "No");
        tfws.setPromptText("Select");
        tfws.setStyle(comboStyle);

        ComboBox<String> ews = new ComboBox<>();
        ews.getItems().addAll("Yes", "No");
        ews.setPromptText("Select");
        ews.setStyle(comboStyle);

        addField(form, "Candidate Name", candidateName, 0, 0);
        addField(form, "Father's Name", fatherName, 2, 0);
        addField(form, "Mother's Name", motherName, 0, 1);
        addField(form, "Gender", gender, 2, 1);
        addField(form, "Date of Birth", dob, 0, 2);
        addField(form, "Nationality", nationality, 2, 2);
        
        addField(form, "Category", category, 2, 3);
       
        
        addField(form, "Minority", minority, 0, 5);
        addField(form, "PwD", pwd, 2, 5);
        addField(form, "Defence", defence, 0, 6);
        addField(form, "TFWS", tfws, 2, 6);
        addField(form, "EWS", ews, 0, 7);

        formCard.getChildren().add(form);

        // Buttons
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e ->
            backButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e ->
            backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        Button nextButton = new Button("Save & Continue →");
        nextButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        nextButton.setOnMouseEntered(e ->
            nextButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        nextButton.setOnMouseExited(e ->
            nextButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        // Keep original logic
        nextButton.setOnAction(e -> {
            data.setCandidateName(candidateName.getText());
            data.setFatherName(fatherName.getText());
            data.setMotherName(motherName.getText());
            data.setGender(gender.getValue());
            if (dob.getValue() != null) {
                data.setDob(dob.getValue().toString());
            }
            data.setNationality(nationality.getValue());
           
            data.setCategory(category.getValue());
           
            
            data.setMinority(minority.getValue());
            data.setPwd(pwd.getValue());
            data.setDefence(defence.getValue());
            data.setTfws(tfws.getValue());
            data.setEws(ews.getValue());
            Navigation.goTo(AddressDetailsPage.getScene());
        });

        buttons.getChildren().addAll(backButton, nextButton);

        content.getChildren().addAll(title, subtitle, formCard, buttons);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: #0A0A0F;" +
                "-fx-background-color: #0A0A0F;"
        );

        return new Scene(
                StudentLayout.create("Personal Details", scrollPane)
        );
    }

    private static void addField(GridPane grid, String labelText, Control control, int column, int row) {
        Label label = new Label(labelText);
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;"
        );
        control.setPrefWidth(240);
        control.setPrefHeight(38);
        VBox box = new VBox(6, label, control);
        grid.add(box, column, row);
    }
}
