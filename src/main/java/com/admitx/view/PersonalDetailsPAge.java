package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;
import com.example.model.ApplicationData;
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

        Label title = new Label("Personal Details");
        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        TextField candidateName = new TextField();
        TextField fatherName = new TextField();
        TextField motherName = new TextField();
        DatePicker dob = new DatePicker();

        ComboBox<String> gender = new ComboBox<>();
        gender.getItems().addAll("Male", "Female", "Other");
        gender.setPromptText("Select Gender");

        ComboBox<String> nationality = new ComboBox<>();
        nationality.getItems().addAll("Indian", "Other");
        nationality.setPromptText("Select Nationality");

        TextField aadhaar = new TextField();

        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll(
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
        category.setPromptText("Select Category");

        TextField religion = new TextField();
        TextField caste = new TextField();

        ComboBox<String> minority = new ComboBox<>();
        minority.getItems().addAll("Yes", "No");
        minority.setPromptText("Select");

        ComboBox<String> pwd = new ComboBox<>();
        pwd.getItems().addAll("Yes", "No");
        pwd.setPromptText("Select");

        ComboBox<String> defence = new ComboBox<>();
        defence.getItems().addAll("Yes", "No");
        defence.setPromptText("Select");

        ComboBox<String> tfws = new ComboBox<>();
        tfws.getItems().addAll("Yes", "No");
        tfws.setPromptText("Select");

        ComboBox<String> ews = new ComboBox<>();
        ews.getItems().addAll("Yes", "No");
        ews.setPromptText("Select");

        GridPane form = new GridPane();

        form.setHgap(20);
        form.setVgap(15);
        form.setPadding(new Insets(20));

        addField(form, "Candidate Name", candidateName, 0, 0);
        addField(form, "Father's Name", fatherName, 2, 0);

        addField(form, "Mother's Name", motherName, 0, 1);
        addField(form, "Gender", gender, 2, 1);

        addField(form, "Date of Birth", dob, 0, 2);
        addField(form, "Nationality", nationality, 2, 2);

        addField(form, "Aadhaar (Dummy)", aadhaar, 0, 3);
        addField(form, "Category", category, 2, 3);

        addField(form, "Religion", religion, 0, 4);
        addField(form, "Caste", caste, 2, 4);

        addField(form, "Minority", minority, 0, 5);
        addField(form, "PwD", pwd, 2, 5);

        addField(form, "Defence", defence, 0, 6);
        addField(form, "TFWS", tfws, 2, 6);

        addField(form, "EWS", ews, 0, 7);

        Button backButton = new Button("Back");
        Button nextButton = new Button("Save & Continue");

        backButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        nextButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        backButton.setOnAction(e ->
                Navigation.goTo(StudentDashboardPage.getScene())
        );

        nextButton.setOnAction(e -> {

            data.setCandidateName(candidateName.getText());
            data.setFatherName(fatherName.getText());
            data.setMotherName(motherName.getText());
            data.setGender(gender.getValue());

            if (dob.getValue() != null) {
                data.setDob(dob.getValue().toString());
            }

            data.setNationality(nationality.getValue());
            data.setAadhaar(aadhaar.getText());
            data.setCategory(category.getValue());
            data.setReligion(religion.getText());
            data.setCaste(caste.getText());
            data.setMinority(minority.getValue());
            data.setPwd(pwd.getValue());
            data.setDefence(defence.getValue());
            data.setTfws(tfws.getValue());
            data.setEws(ews.getValue());

            Navigation.goTo(
                    AddressDetailsPage.getScene()
            );
        });

        HBox buttons = new HBox(15, backButton, nextButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox content = new VBox(
                15,
                title,
                form,
                buttons
        );

        content.setPadding(new Insets(30));
        content.setStyle("-fx-background-color: #F7FEE7;");

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #F7FEE7;");

        return new Scene(
                StudentLayout.create(
                        "Personal Details",
                        scrollPane
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
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        control.setPrefWidth(240);
        control.setPrefHeight(38);

        VBox box = new VBox(6, label, control);

        grid.add(box, column, row);
    }
}