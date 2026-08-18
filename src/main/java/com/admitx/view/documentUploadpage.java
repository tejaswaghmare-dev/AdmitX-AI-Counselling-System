package com.admitx.view;

import com.admitx.view.Navigation;
import com.admitx.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.admitx.model.ApplicationData;

import java.io.File;
import java.util.Map;

public class DocumentUploadPage {

    private static final ApplicationData data = ApplicationData.getInstance();

    public static Scene getScene() {

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📎 Document Upload");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Upload all required documents for verification");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Documents Card
        VBox documentsCard = new VBox(12);
        documentsCard.setPadding(new Insets(25, 30, 30, 30));
        documentsCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        VBox documents = new VBox(10);

        documents.getChildren().add(createDocumentRow("SSC Marksheet"));
        documents.getChildren().add(createDocumentRow("HSC Marksheet"));
        documents.getChildren().add(createDocumentRow("CET Scorecard"));
        documents.getChildren().add(createDocumentRow("Leaving Certificate"));
        documents.getChildren().add(createDocumentRow("Domicile"));
        documents.getChildren().add(createDocumentRow("Caste Certificate"));
        documents.getChildren().add(createDocumentRow("Validity"));
        documents.getChildren().add(createDocumentRow("NCL"));
        documents.getChildren().add(createDocumentRow("Income Certificate"));
        documents.getChildren().add(createDocumentRow("EWS Certificate"));
        documents.getChildren().add(createDocumentRow("Gap Certificate"));
        documents.getChildren().add(createDocumentRow("Photo"));
        documents.getChildren().add(createDocumentRow("Signature"));

        documentsCard.getChildren().add(documents);

        // Buttons
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setPadding(new Insets(10, 0, 0, 0));

        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e ->
            backButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e ->
            backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnAction(e -> Navigation.goTo(ReservationDetailsPage.getScene()));

        Button nextButton = new Button("Save & Continue →");
        nextButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        nextButton.setOnMouseEntered(e ->
            nextButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        nextButton.setOnMouseExited(e ->
            nextButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        nextButton.setOnAction(e -> Navigation.goTo(PreviewApplicationPage.getScene()));

        buttons.getChildren().addAll(backButton, nextButton);

        content.getChildren().addAll(title, subtitle, documentsCard, buttons);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: #0A0A0F;" +
                "-fx-background-color: #0A0A0F;"
        );

        return new Scene(
                StudentLayout.create("Document Upload", scrollPane)
        );
    }

    private static HBox createDocumentRow(String documentName) {
        Label documentLabel = new Label(documentName);
        documentLabel.setPrefWidth(200);
        documentLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;"
        );

        Label fileName = new Label("No file selected");
        fileName.setPrefWidth(280);
        fileName.setStyle(
                "-fx-text-fill: #5A7D9E;" +
                "-fx-font-size: 13px;"
        );

        Button uploadButton = new Button("Choose File");
        uploadButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 6px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 6 14 6 14;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 6px;" +
                "-fx-border-width: 1px;"
        );
        uploadButton.setOnMouseEntered(e ->
            uploadButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 6px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 6 14 6 14;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 6px;" +
                "-fx-border-width: 1px;"
            )
        );
        uploadButton.setOnMouseExited(e ->
            uploadButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 6px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 6 14 6 14;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 6px;" +
                "-fx-border-width: 1px;"
            )
        );

        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select " + documentName);
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Documents",
                            "*.pdf",
                            "*.jpg",
                            "*.jpeg",
                            "*.png"
                    )
            );
            Stage stage = (Stage) uploadButton.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                data.getUploadedDocuments().put(documentName, file);
                fileName.setText("✅ " + file.getName());
                fileName.setStyle(
                        "-fx-text-fill: #4ADE80;" +
                        "-fx-font-size: 13px;"
                );
            }
        });

        HBox row = new HBox(15, documentLabel, fileName, uploadButton);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10, 14, 10, 14));
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

    public static Map<String, File> getUploadedDocuments() {
        return data.getUploadedDocuments();
    }
}
