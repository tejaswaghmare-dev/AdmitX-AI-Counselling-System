package com.admitx.view;



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

import com.admitx.model.ApplicationData;

public class PreviewApplicationPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("Preview Application");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Review your application before submitting");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Application Details Card
        VBox applicationDetails = new VBox(10);
        applicationDetails.setPadding(new Insets(25, 30, 30, 30));
        applicationDetails.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        applicationDetails.getChildren().add(createSectionTitle("👤 Personal Details"));
        applicationDetails.getChildren().add(createDetail("Candidate Name", value(data.getCandidateName())));
        applicationDetails.getChildren().add(createDetail("Father's Name", value(data.getFatherName())));
        applicationDetails.getChildren().add(createDetail("Mother's Name", value(data.getMotherName())));
        applicationDetails.getChildren().add(createDetail("Gender", value(data.getGender())));
        applicationDetails.getChildren().add(createDetail("Date of Birth", value(data.getDob())));
        applicationDetails.getChildren().add(createDetail("Nationality", value(data.getNationality())));
        applicationDetails.getChildren().add(createDetail("Category", value(data.getCategory())));

        applicationDetails.getChildren().add(createSectionTitle("📍 Address Details"));
        applicationDetails.getChildren().add(createDetail("Permanent Address", value(data.getPermanentAddress())));
        applicationDetails.getChildren().add(createDetail("Correspondence Address", value(data.getCorrespondenceAddress())));
        applicationDetails.getChildren().add(createDetail("State", value(data.getState())));
        applicationDetails.getChildren().add(createDetail("District", value(data.getDistrict())));
        applicationDetails.getChildren().add(createDetail("Taluka", value(data.getTaluka())));
        applicationDetails.getChildren().add(createDetail("PIN Code", value(data.getPinCode())));

        applicationDetails.getChildren().add(createSectionTitle("🎓 Academic Details"));
        applicationDetails.getChildren().add(createDetail("SSC Details", value(data.getSscDetails())));
        applicationDetails.getChildren().add(createDetail("HSC Details", value(data.getHscDetails())));
        applicationDetails.getChildren().add(createDetail("Diploma Details", value(data.getDiplomaDetails())));
        applicationDetails.getChildren().add(createDetail("PCM Marks", value(data.getPcmMarks())));
        applicationDetails.getChildren().add(createDetail("MHT CET Percentile", value(data.getCetPercentile())));
        applicationDetails.getChildren().add(createDetail("JEE Main Percentile", value(data.getJeePercentile())));
        applicationDetails.getChildren().add(createDetail("Year of Passing", value(data.getYearOfPassing())));

        applicationDetails.getChildren().add(createSectionTitle("🏛️ Home University & Eligibility"));
        applicationDetails.getChildren().add(createDetail("State", value(data.getState())));
        applicationDetails.getChildren().add(createDetail("Home University", value(data.getHomeUniversity())));
        applicationDetails.getChildren().add(createDetail("Candidate Type", value(data.getCandidateType())));
        applicationDetails.getChildren().add(createDetail("Maharashtra Type", value(data.getMaharashtraType())));
        applicationDetails.getChildren().add(createDetail("Domicile Status", value(data.getDomicileStatus())));

        applicationDetails.getChildren().add(createSectionTitle("📋 Reservation Details"));
        applicationDetails.getChildren().add(createDetail("Category", value(data.getCategory())));
        applicationDetails.getChildren().add(createDetail("Caste", value(data.getCaste())));
        applicationDetails.getChildren().add(createDetail("Validity Certificate", value(data.getValidityCertificate())));
        applicationDetails.getChildren().add(createDetail("NCL", value(data.getNcl())));
        applicationDetails.getChildren().add(createDetail("EWS", value(data.getEws())));
        applicationDetails.getChildren().add(createDetail("Income", value(data.getIncome())));
        applicationDetails.getChildren().add(createDetail("Minority", value(data.getMinority())));
        applicationDetails.getChildren().add(createDetail("Defence", value(data.getDefence())));
        applicationDetails.getChildren().add(createDetail("Orphan", value(data.getOrphan())));

        applicationDetails.getChildren().add(createSectionTitle("📎 Uploaded Documents"));
        Map<String, File> documents = DocumentUploadPage.getUploadedDocuments();

        if (documents.isEmpty()) {
            applicationDetails.getChildren().add(createDetail("Documents", "No documents uploaded"));
        } else {
            for (Map.Entry<String, File> entry : documents.entrySet()) {
                applicationDetails.getChildren().add(createDetail(entry.getKey(), entry.getValue().getName()));
            }
        }

        // Buttons
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setPadding(new Insets(15, 0, 0, 0));

        Button editButton = new Button("✏️ Edit");
        editButton.setStyle(
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
        editButton.setOnMouseEntered(e ->
            editButton.setStyle(
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
        editButton.setOnMouseExited(e ->
            editButton.setStyle(
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
        editButton.setOnAction(e -> Navigation.goTo(PersonalDetailsPage.getScene()));

        Button submitButton = new Button("✅ Submit Application");
        submitButton.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(6, 95, 70, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        submitButton.setOnMouseEntered(e ->
            submitButton.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(7, 138, 92, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        submitButton.setOnMouseExited(e ->
            submitButton.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(6, 95, 70, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        submitButton.setOnAction(e -> Navigation.goTo(ApplicationStatusPage.getScene()));

        buttons.getChildren().addAll(editButton, submitButton);

        content.getChildren().addAll(title, subtitle, applicationDetails, buttons);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: #0A0A0F;" +
                "-fx-background-color: #0A0A0F;"
        );

        return new Scene(
                StudentLayout.create("Preview Application", scrollPane)
        );
    }

    private static Label createSectionTitle(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #60A5FA;" +
                "-fx-padding: 15 0 8 0;" +
                "-fx-font-family: 'Segoe UI';"
        );
        return label;
    }

    private static HBox createDetail(String field, String value) {
        Label fieldLabel = new Label(field);
        fieldLabel.setPrefWidth(220);
        fieldLabel.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;"
        );

        Label valueLabel = new Label(value);
        valueLabel.setStyle(
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;"
        );

        HBox row = new HBox(15, fieldLabel, valueLabel);
        row.setPadding(new Insets(10, 12, 10, 12));
        row.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.08);" +
                "-fx-border-radius: 8px;"
        );

        row.setOnMouseEntered(e ->
            row.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;"
            )
        );
        row.setOnMouseExited(e ->
            row.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.08);" +
                "-fx-border-radius: 8px;"
            )
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
