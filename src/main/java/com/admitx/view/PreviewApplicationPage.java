package com.admitx.view;

import com.admitx.model.Student;
import com.admitx.view.Navigation;
import com.admitx.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.File;
import java.util.Map;

import java.awt.Desktop;
import java.net.URI;

public class PreviewApplicationPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Student data =
                Student.getInstance();

        Label title =
                new Label("Preview Application");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Review your application carefully before submitting."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        Label progressTitle =
                new Label("APPLICATION PROGRESS");

        progressTitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox progress =
                new HBox(
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
                        createLine(true),

                        createStep("6", "Documents", true),
                        createLine(true),

                        createStep("7", "Preview", true)
                );

        progress.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox progressCard =
                new VBox(
                        10,
                        progressTitle,
                        progress
                );

        progressCard.setPadding(
                new Insets(16)
        );

        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        VBox applicationDetails =
                new VBox(10);

        addSection(
                applicationDetails,
                "PERSONAL DETAILS"
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Candidate Name",
                        data.getCandidateName()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Father's Name",
                        data.getFatherName()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Mother's Name",
                        data.getMotherName()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Gender",
                        data.getGender()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Date of Birth",
                        data.getDob()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Nationality",
                        data.getNationality()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Category",
                        data.getCategory()
                )
        );

        addSection(
                applicationDetails,
                "ADDRESS DETAILS"
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Permanent Address",
                        data.getPermanentAddress()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Correspondence Address",
                        data.getCorrespondenceAddress()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "State",
                        data.getState()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "District",
                        data.getDistrict()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Taluka",
                        data.getTaluka()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "PIN Code",
                        data.getPinCode()
                )
        );

        addSection(
                applicationDetails,
                "ACADEMIC DETAILS"
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "SSC Details",
                        data.getSscDetails()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "HSC Details",
                        data.getHscDetails()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Diploma Details",
                        data.getDiplomaDetails()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "PCM Marks",
                        data.getPcmMarks()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "MHT CET Percentile",
                        data.getCetPercentile()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "JEE Main Percentile",
                        data.getJeePercentile()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Year of Passing",
                        data.getYearOfPassing()
                )
        );

        addSection(
                applicationDetails,
                "HOME UNIVERSITY & ELIGIBILITY"
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "State",
                        data.getState()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Home University",
                        data.getHomeUniversity()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Candidate Type",
                        data.getCandidateType()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Maharashtra Type",
                        data.getMaharashtraType()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Domicile Status",
                        data.getDomicileStatus()
                )
        );

        addSection(
                applicationDetails,
                "RESERVATION DETAILS"
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Category",
                        data.getCategory()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Caste",
                        data.getCaste()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Validity Certificate",
                        data.getValidityCertificate()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "NCL",
                        data.getNcl()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "EWS",
                        data.getEws()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Income",
                        data.getIncome()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Minority",
                        data.getMinority()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Defence",
                        data.getDefence()
                )
        );

        applicationDetails.getChildren().add(
                createDetail(
                        "Orphan",
                        data.getOrphan()
                )
        );

        addSection(
        applicationDetails,
        "UPLOADED DOCUMENTS"
);

Map<String, File> documents =
        DocumentUploadPage.getUploadedDocuments();

Map<String, String> documentUrls =
        data.getUploadedDocumentUrls();

if (documents.isEmpty()) {

    applicationDetails.getChildren().add(
            createDetail(
                    "Documents",
                    "No documents uploaded"
            )
    );

} else {

    for (
            Map.Entry<String, File> entry :
            documents.entrySet()
    ) {

        String documentName =
                entry.getKey();

        File file =
                entry.getValue();

        String cloudinaryUrl =
                documentUrls.get(documentName);

        applicationDetails.getChildren().add(
                createDocumentDetail(
                        documentName,
                        file.getName(),
                        cloudinaryUrl
                )
        );
    }
}

        VBox detailsCard =
                new VBox(
                        15,
                        applicationDetails
                );

        detailsCard.setPadding(
                new Insets(22)
        );

        detailsCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        Label warning =
                new Label(
                        "⚠ Please verify all information before submitting. " +
                        "Once submitted, changes may not be allowed."
                );

        warning.setWrapText(true);

        warning.setStyle(
                "-fx-background-color: #211F0F;" +
                "-fx-text-fill: #D9E6C8;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #665F20;" +
                "-fx-border-radius: 8px;" +
                "-fx-font-size: 12px;"
        );

        Button editButton =
                new Button("←  Edit Application");

        styleSecondaryButton(
                editButton
        );

        Button submitButton =
                new Button("Submit Application  ✓");

        stylePrimaryButton(
                submitButton
        );

        editButton.setOnAction(e ->
                Navigation.goTo(
                        PersonalDetailsPage.getScene()
                )
        );

        submitButton.setOnAction(e ->
                Navigation.goTo(
                        ApplicationStatusPage.getScene()
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        editButton,
                        spacer,
                        submitButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        progressCard,
                        detailsCard,
                        warning,
                        buttons
                );

        content.setPadding(
                new Insets(5)
        );

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

        BorderPane page =
                new BorderPane();

        page.setCenter(
                scrollPane
        );

        page.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Preview Application",
                        page
                )
        );
    }

    private static void addSection(
            VBox container,
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-padding: 12 0 5 0;"
        );

        container.getChildren().add(
                label
        );
    }

    private static HBox createDetail(
            String field,
            String value
    ) {

        Label fieldLabel =
                new Label(field);

        fieldLabel.setPrefWidth(
                240
        );

        fieldLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label valueLabel =
                new Label(
                        value(value)
                );

        valueLabel.setWrapText(true);

        valueLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox row =
                new HBox(
                        15,
                        fieldLabel,
                        valueLabel
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        12,
                        15,
                        12,
                        15
                )
        );

        row.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        return row;
    }
    private static HBox createDocumentDetail(
        String documentName,
        String fileName,
        String cloudinaryUrl
) {

    Label fieldLabel =
            new Label(documentName);

    fieldLabel.setPrefWidth(240);

    fieldLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + WHITE + ";"
    );

    Label valueLabel =
            new Label(fileName);

    valueLabel.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: " + LIME + ";"
    );

    Region spacer =
            new Region();

    HBox.setHgrow(
            spacer,
            Priority.ALWAYS
    );

    Button viewButton =
            new Button("View");

    viewButton.setStyle(
            "-fx-background-color: #202B20;" +
            "-fx-text-fill: " + LIME + ";" +
            "-fx-border-color: #3B4A3B;" +
            "-fx-border-radius: 7px;" +
            "-fx-background-radius: 7px;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
    );

    if (
            cloudinaryUrl == null ||
            cloudinaryUrl.isBlank()
    ) {

        viewButton.setDisable(true);
        viewButton.setText("Not Uploaded");

    } else {

        viewButton.setOnAction(e -> {

            try {

                Desktop.getDesktop().browse(
                        new URI(cloudinaryUrl)
                );

            } catch (Exception ex) {

                ex.printStackTrace();

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setTitle(
                        "View Document"
                );

                alert.setHeaderText(
                        null
                );

                alert.setContentText(
                        "Unable to open this document."
                );

                alert.showAndWait();
            }
        });
    }

    HBox row =
            new HBox(
                    15,
                    fieldLabel,
                    valueLabel,
                    spacer,
                    viewButton
            );

    row.setAlignment(
            Pos.CENTER_LEFT
    );

    row.setPadding(
            new Insets(
                    12,
                    15,
                    12,
                    15
            )
    );

    row.setStyle(
            "-fx-background-color: " + ROW + ";" +
            "-fx-background-radius: 8px;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-radius: 8px;"
    );

    return row;
}

    private static String value(
            String text
    ) {

        if (
                text == null ||
                text.isBlank()
        ) {

            return "Not Saved";
        }

        return text;
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

        line.setPrefWidth(
                25
        );

        line.setPrefHeight(
                2
        );

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
    }
}