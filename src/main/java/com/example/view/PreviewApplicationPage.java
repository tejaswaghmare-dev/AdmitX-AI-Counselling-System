package com.example.view;


import com.example.model.ApplicationData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Map;

public class PreviewApplicationPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        Label title = new Label("Preview Application");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        VBox applicationDetails = new VBox(12);

        applicationDetails.getChildren().add(
                createSectionTitle("Personal Details")
        );

        applicationDetails.getChildren().add(
                createDetail("Candidate Name", (data.getCandidateName())
        ));

        applicationDetails.getChildren().add(
                createDetail("Father's Name", (data.getFatherName()))
        );

        applicationDetails.getChildren().add(
                createDetail("Mother's Name", (data.getMotherName()))
        );

        applicationDetails.getChildren().add(
                createDetail("Gender", (data.getGender()))
        );

        applicationDetails.getChildren().add(
                createDetail("Date of Birth", (data.getDob()))
        );

        applicationDetails.getChildren().add(
                createDetail("Nationality", data.getNationality())
        );

        applicationDetails.getChildren().add(
                createDetail("Category", data.getCategory())
        );

        applicationDetails.getChildren().add(
                createSectionTitle("Address Details")
        );

        applicationDetails.getChildren().add(
                createDetail("Permanent Address", data.getPermanentAddress())
        );

        applicationDetails.getChildren().add(
                createDetail("Correspondence Address", data.getCorrespondenceAddress())
        );

        applicationDetails.getChildren().add(
                createDetail("State", data.getState())
        );

        applicationDetails.getChildren().add(
                createDetail("District", data.getDistrict())
        );

        applicationDetails.getChildren().add(
                createDetail("Taluka", data.getTaluka())
        );

        applicationDetails.getChildren().add(
                createDetail("PIN Code", data.getPinCode())
        );

        applicationDetails.getChildren().add(
                createSectionTitle("Academic Details")
        );

        applicationDetails.getChildren().add(
                createDetail("SSC Details", data.getSscDetails())
        );

        applicationDetails.getChildren().add(
                createDetail("HSC Details", data.getHscDetails())
        );

        applicationDetails.getChildren().add(
                createDetail("Diploma Details", data.getDiplomaDetails())
        );

        applicationDetails.getChildren().add(
                createDetail("PCM Marks", data.getPcmMarks())
        );

        applicationDetails.getChildren().add(
                createDetail("MHT CET Percentile", data.getCetPercentile())
        );

        applicationDetails.getChildren().add(
                createDetail("JEE Main Percentile", data.getJeePercentile())
        );

        applicationDetails.getChildren().add(
                createDetail("Year of Passing", data.getYearOfPassing())
        );

        applicationDetails.getChildren().add(
                createSectionTitle("Home University & Eligibility")
        );

        applicationDetails.getChildren().add(
                createDetail("State", data.getState())
        );

        applicationDetails.getChildren().add(
                createDetail("Home University", data.getHomeUniversity())
        );

        applicationDetails.getChildren().add(
                createDetail("Candidate Type", data.getCandidateType())
        );

        applicationDetails.getChildren().add(
                createDetail("Maharashtra Type", data.getMaharashtraType())
        );

        applicationDetails.getChildren().add(
                createDetail("Domicile Status", data.getDomicileStatus())
        );

        applicationDetails.getChildren().add(
                createSectionTitle("Reservation Details")
        );

        applicationDetails.getChildren().add(
                createDetail("Category", data.getCategory())
        );

        applicationDetails.getChildren().add(
                createDetail("Caste", data.getCaste())
        );

        applicationDetails.getChildren().add(
                createDetail("Validity Certificate", data.getValidityCertificate())
        );

        applicationDetails.getChildren().add(
                createDetail("NCL", data.getNcl())
        );

        applicationDetails.getChildren().add(
                createDetail("EWS", data.getEws())
        );

        applicationDetails.getChildren().add(
                createDetail("Income", data.getIncome())
        );

        applicationDetails.getChildren().add(
                createDetail("Minority", data.getMinority())
        );

        applicationDetails.getChildren().add(
                createDetail("Defence", data.getDefence())
        );

        applicationDetails.getChildren().add(
                createDetail("Orphan", data.getOrphan())
        );

        applicationDetails.getChildren().add(
                createSectionTitle("Uploaded Documents")
        );

        Map<String, File> documents =
                DocumentUploadPage.getUploadedDocuments();

        if (documents.isEmpty()) {

            applicationDetails.getChildren().add(
                    createDetail(
                            "Documents",
                            "No documents uploaded"
                    )
            );

        } else {

            for (Map.Entry<String, File> entry :
                    documents.entrySet()) {

                applicationDetails.getChildren().add(
                        createDetail(
                                entry.getKey(),
                                entry.getValue().getName()
                        )
                );
            }
        }

        Button editButton = new Button("Edit");

        Button submitButton = new Button("Submit Application");

        editButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        submitButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        editButton.setOnAction(e ->
                Navigation.goTo(PersonalDetailsPage.getScene())
        );

        submitButton.setOnAction(e ->
                Navigation.goTo(ApplicationStatusPage.getScene())
        );

        HBox buttons = new HBox(
                15,
                editButton,
                submitButton
        );

        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox content = new VBox(
                20,
                title,
                applicationDetails,
                buttons
        );

        content.setPadding(new Insets(30));

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Preview Application",
                        scrollPane
                )
        );
    }

    private static Label createSectionTitle(
            String text
    ) {

        Label label = new Label(text);

        label.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;" +
                "-fx-padding: 15 0 5 0;"
        );

        return label;
    }

    private static HBox createDetail(
            String field,
            String value
    ) {

        Label fieldLabel =
                new Label(field);

        fieldLabel.setPrefWidth(220);

        fieldLabel.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-text-fill: #3F6212;"
        );

        HBox row = new HBox(
                15,
                fieldLabel,
                valueLabel
        );

        row.setPadding(
                new Insets(10)
        );

        row.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 6px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 6px;"
        );

        return row;
    }
    private static String value(String text) {

    if (text == null || text.isBlank()) {
        return "Not Saved";
    }
    return text;
}
}