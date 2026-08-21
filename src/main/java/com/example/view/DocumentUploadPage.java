package com.example.view;



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
import com.example.model.ApplicationData;
import java.io.File;

import java.util.Map;

public class DocumentUploadPage {

    private static final ApplicationData data =ApplicationData.getInstance();

    public static Scene getScene() {

        Label title = new Label("Document Upload");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        VBox documents = new VBox(12);

        documents.getChildren().add(
                createDocumentRow("SSC Marksheet")
        );

        documents.getChildren().add(
                createDocumentRow("HSC Marksheet")
        );

        documents.getChildren().add(
                createDocumentRow("CET Scorecard")
        );

        documents.getChildren().add(
                createDocumentRow("Leaving Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("Domicile")
        );

        documents.getChildren().add(
                createDocumentRow("Caste Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("Validity")
        );

        documents.getChildren().add(
                createDocumentRow("NCL")
        );

        documents.getChildren().add(
                createDocumentRow("Income Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("EWS Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("Gap Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("Photo")
        );

        documents.getChildren().add(
                createDocumentRow("Signature")
        );

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
                Navigation.goTo(ReservationDetailsPage.getScene())
        );

        nextButton.setOnAction(e ->
                Navigation.goTo(PreviewApplicationPage.getScene())
        );

        HBox buttons = new HBox(
                15,
                backButton,
                nextButton
        );

        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox content = new VBox(
                20,
                title,
                documents,
                buttons
        );

        content.setPadding(new Insets(30));

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        ScrollPane scrollPane = new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Document Upload",
                        scrollPane
                )
        );
    }

    private static HBox createDocumentRow(String documentName) {

        Label documentLabel = new Label(documentName);

        documentLabel.setPrefWidth(220);

        documentLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label fileName = new Label("No file selected");

        fileName.setPrefWidth(300);

        fileName.setStyle(
                "-fx-text-fill: #4D7C0F;"
        );

        Button uploadButton = new Button("Choose File");

        uploadButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 6px;"
        );

        uploadButton.setOnAction(e -> {

            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle(
                    "Select " + documentName
            );

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Documents",
                            "*.pdf",
                            "*.jpg",
                            "*.jpeg",
                            "*.png"
                    )
            );

            Stage stage =
                    (Stage) uploadButton.getScene().getWindow();

            File file =
                    fileChooser.showOpenDialog(stage);

            if (file != null) {

                data.getUploadedDocuments().put(
                    documentName,
                    file
);

                fileName.setText(
                        file.getName()
                );
            }
        });

        HBox row = new HBox(
                15,
                documentLabel,
                fileName,
                uploadButton
        );

        row.setAlignment(Pos.CENTER_LEFT);

        row.setPadding(
                new Insets(12)
        );

        row.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 8px;"
        );

        return row;
    }

    public static Map<String, File> getUploadedDocuments() {
        return data.getUploadedDocuments();
}
}