package com.admitx.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class GrievanceSubmissionPage {

    private static File proofFile;

    public static Scene getScene() {

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📋 Raise Grievance");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Submit your grievance with supporting proof");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Form Card
        VBox formCard = new VBox(16);
        formCard.setPadding(new Insets(25, 30, 30, 30));
        formCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label grievanceLabel = new Label("Describe your grievance");
        grievanceLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;"
        );

        TextArea grievance = new TextArea();
        grievance.setPromptText("Describe your grievance in detail...");
        grievance.setPrefRowCount(6);
        grievance.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 10;" +
                "-fx-font-size: 14px;"
        );

        // Upload section
        VBox uploadSection = new VBox(8);
        uploadSection.setPadding(new Insets(10, 0, 0, 0));

        Label uploadLabel = new Label("📎 Upload Proof");
        uploadLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;"
        );

        HBox uploadBox = new HBox(15);
        uploadBox.setAlignment(Pos.CENTER_LEFT);

        Label fileName = new Label("No proof uploaded");
        fileName.setStyle(
                "-fx-text-fill: #5A7D9E;" +
                "-fx-font-size: 14px;"
        );

        Button upload = new Button("Choose File");
        upload.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 130px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        upload.setOnMouseEntered(e ->
            upload.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 130px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        upload.setOnMouseExited(e ->
            upload.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 130px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        upload.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Documents",
                            "*.pdf",
                            "*.jpg",
                            "*.png"
                    )
            );
            Stage stage = (Stage) upload.getScene().getWindow();
            File file = chooser.showOpenDialog(stage);
            if (file != null) {
                proofFile = file;
                fileName.setText("✅ " + file.getName());
                fileName.setStyle(
                        "-fx-text-fill: #4ADE80;" +
                        "-fx-font-size: 14px;"
                );
            }
        });

        uploadBox.getChildren().addAll(upload, fileName);
        uploadSection.getChildren().addAll(uploadLabel, uploadBox);

        // Buttons
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        Button back = new Button("← Back");
        back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        back.setOnMouseEntered(e ->
            back.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnMouseExited(e ->
            back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnAction(e -> Navigation.goTo(ProvisionalMeritPage.getScene()));

        Button submit = new Button("✅ Submit");
        submit.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(6, 95, 70, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        submit.setOnMouseEntered(e ->
            submit.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(7, 138, 92, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        submit.setOnMouseExited(e ->
            submit.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(6, 95, 70, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        submit.setOnAction(e -> Navigation.goTo(FinalMeritPage.getScene()));

        buttons.getChildren().addAll(back, submit);

        formCard.getChildren().addAll(
                grievanceLabel,
                grievance,
                uploadSection
        );

        content.getChildren().addAll(title, subtitle, formCard, buttons);

        return new Scene(
                StudentLayout.create("Grievance", content)
        );
    }

    public static File getProofFile() {
        return proofFile;
    }
}