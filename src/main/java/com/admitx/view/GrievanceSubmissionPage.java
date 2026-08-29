package com.admitx.view;

import java.io.File;
import java.util.Map;

import com.admitx.config.CloudinaryConfig;
import com.admitx.dao.GrievanceDAO;
import com.admitx.dao.GrievanceDAO.GrievanceRecord;
import com.admitx.dao.MeritDAO;
import com.admitx.dao.MeritDAO.MeritRecord;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GrievanceSubmissionPage {

    private static File proofFile;

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String ORANGE = "#F97316";

    public static Scene getScene() {

        proofFile = null;

        MeritDAO meritDAO =
                new MeritDAO();

        GrievanceDAO grievanceDAO =
                new GrievanceDAO();

        MeritRecord merit =
                meritDAO.getCurrentStudentMerit();

        GrievanceRecord existingGrievance =
                grievanceDAO.getCurrentStudentGrievance();

        // =====================================================
        // HEADING
        // =====================================================

        Label title =
                new Label(
                        "Raise Grievance"
                );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Submit your grievance regarding the provisional merit list."
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

        // =====================================================
        // MERIT INFORMATION
        // =====================================================

        VBox meritBox =
                new VBox(
                        10
                );

        meritBox.setPadding(
                new Insets(20)
        );

        meritBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label meritTitle =
                new Label(
                        "PROVISIONAL MERIT"
                );

        meritTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        if (merit != null) {

            meritBox.getChildren().addAll(

                    meritTitle,

                    detail(
                            "Provisional Merit Number",
                            String.valueOf(
                                    merit.getProvisionalMeritNumber()
                            )
                    ),

                    detail(
                            "CET Percentile",
                            safe(
                                    merit.getCetPercentile()
                            )
                    ),

                    detail(
                            "Category",
                            safe(
                                    merit.getCategory()
                            )
                    )
            );

        } else {

            meritBox.getChildren().addAll(

                    meritTitle,

                    detail(
                            "Status",
                            "Provisional merit list not available."
                    )
            );
        }

        // =====================================================
        // GRIEVANCE DETAILS
        // =====================================================

        Label grievanceLabel =
                new Label(
                        "GRIEVANCE DETAILS"
                );

        grievanceLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        TextArea grievance =
                new TextArea();

        grievance.setPromptText(
                "Describe your grievance clearly..."
        );

        grievance.setPrefRowCount(
                7
        );

        grievance.setWrapText(
                true
        );

        grievance.setStyle(
                "-fx-control-inner-background: " + CARD + ";" +
                "-fx-background-color: " + CARD + ";" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: #667066;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 12px;"
        );

        VBox grievanceBox =
                new VBox(
                        10,
                        grievanceLabel,
                        grievance
                );

        grievanceBox.setPadding(
                new Insets(20)
        );

        grievanceBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        // =====================================================
        // SUPPORTING PROOF
        // =====================================================

        Label proofLabel =
                new Label(
                        "SUPPORTING PROOF"
                );

        proofLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label fileName =
                new Label(
                        "No proof uploaded"
                );

        fileName.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;"
        );

        Button upload =
                new Button(
                        "Choose File"
                );

        upload.setPrefHeight(
                40
        );

        upload.setStyle(
                "-fx-background-color: #1B2615;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        upload.setOnAction(e -> {

            FileChooser chooser =
                    new FileChooser();

            chooser.setTitle(
                    "Select Supporting Proof"
            );

            chooser.getExtensionFilters().add(

                    new FileChooser.ExtensionFilter(
                            "Documents",
                            "*.pdf",
                            "*.jpg",
                            "*.jpeg",
                            "*.png"
                    )
            );

            Stage stage =
                    (Stage) upload
                            .getScene()
                            .getWindow();

            File file =
                    chooser.showOpenDialog(
                            stage
                    );

            if (file != null) {

                proofFile =
                        file;

                fileName.setText(
                        file.getName()
                );

                fileName.setStyle(
                        "-fx-text-fill: " + LIME + ";" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;"
                );
            }
        });

        HBox uploadRow =
                new HBox(
                        15,
                        upload,
                        fileName
                );

        uploadRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label proofNote =
                new Label(
                        "Supported formats: PDF, JPG, JPEG and PNG."
                );

        proofNote.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox proofBox =
                new VBox(
                        10,
                        proofLabel,
                        uploadRow,
                        proofNote
                );

        proofBox.setPadding(
                new Insets(20)
        );

        proofBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        // =====================================================
        // EXISTING GRIEVANCE
        // =====================================================

        VBox statusBox =
                new VBox(
                        10
                );

        statusBox.setPadding(
                new Insets(20)
        );

        statusBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label statusTitle =
                new Label(
                        "GRIEVANCE STATUS"
                );

        statusTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        statusBox.getChildren().add(
                statusTitle
        );

        if (existingGrievance != null) {

            statusBox.getChildren().addAll(

                    detail(
                            "Status",
                            safe(
                                    existingGrievance.getStatus()
                            )
                    ),

                    detail(
                            "Submitted Grievance",
                            safe(
                                    existingGrievance.getGrievanceText()
                            )
                    )
            );

            if (
                    existingGrievance.getCounsellorComment() != null &&
                    !existingGrievance
                            .getCounsellorComment()
                            .isBlank()
            ) {

                statusBox.getChildren().add(

                        detail(
                                "Counsellor Comment",
                                existingGrievance
                                        .getCounsellorComment()
                        )
                );
            }

        } else {

            statusBox.getChildren().add(

                    detail(
                            "Status",
                            "No grievance submitted"
                    )
            );
        }

        // =====================================================
        // BUTTONS
        // =====================================================

        Button back =
                new Button(
                        "← Back"
                );

        styleSecondaryButton(
                back
        );

        back.setOnAction(e ->

                Navigation.goTo(
                        ProvisionalMeritPage.getScene()
                )
        );

        Button submit =
                new Button(
                        "Submit Grievance"
                );

        submit.setPrefHeight(
                42
        );

        submit.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        // =====================================================
        // PREVENT INVALID / DUPLICATE SUBMISSION
        // =====================================================

        if (merit == null) {

            submit.setDisable(
                    true
            );
        }

        if (
                existingGrievance != null &&
                (
                        "Pending".equalsIgnoreCase(
                                existingGrievance.getStatus()
                        ) ||
                        "Approved".equalsIgnoreCase(
                                existingGrievance.getStatus()
                        )
                )
        ) {

            submit.setDisable(
                    true
            );

            grievance.setDisable(
                    true
            );

            upload.setDisable(
                    true
            );
        }

        // =====================================================
        // SUBMIT GRIEVANCE
        // =====================================================

        submit.setOnAction(e -> {

            String grievanceText =
                    grievance.getText();

            if (
                    grievanceText == null ||
                    grievanceText.trim().isEmpty()
            ) {

                message(
                        Alert.AlertType.WARNING,
                        "Grievance Required",
                        "Please describe your grievance before submitting."
                );

                return;
            }

            if (merit == null) {

                message(
                        Alert.AlertType.WARNING,
                        "Merit Not Available",
                        "Your provisional merit list is not available."
                );

                return;
            }

            submit.setDisable(
                    true
            );

            String proofUrl =
                    "";

            String uploadedFileName =
                    "";

            try {

                if (proofFile != null) {

                    Cloudinary cloudinary =
                            CloudinaryConfig
                                    .getCloudinary();

                    Map<?, ?> uploadResult =
                            cloudinary
                                    .uploader()
                                    .upload(
                                            proofFile,
                                            ObjectUtils.asMap(
                                                    "resource_type",
                                                    "auto",
                                                    "folder",
                                                    "admitx/grievances"
                                            )
                                    );

                    Object secureUrl =
                            uploadResult.get(
                                    "secure_url"
                            );

                    if (secureUrl != null) {

                        proofUrl =
                                secureUrl.toString();
                    }

                    uploadedFileName =
                            proofFile.getName();
                }

                boolean success =
                        grievanceDAO.submitGrievance(
                                grievanceText.trim(),
                                proofUrl,
                                uploadedFileName,
                                merit.getProvisionalMeritNumber()
                        );

                if (success) {

                    proofFile =
                            null;

                    message(
                            Alert.AlertType.INFORMATION,
                            "Grievance Submitted",
                            "Your grievance has been submitted successfully. "
                                    + "It is now pending counsellor review."
                    );

                    Navigation.goTo(
                            GrievanceSubmissionPage.getScene()
                    );

                } else {

                    submit.setDisable(
                            false
                    );

                    message(
                            Alert.AlertType.ERROR,
                            "Submission Failed",
                            "Unable to submit your grievance."
                    );
                }

            } catch (Exception exception) {

                exception.printStackTrace();

                submit.setDisable(
                        false
                );

                message(
                        Alert.AlertType.ERROR,
                        "Upload Failed",
                        "Unable to upload the supporting proof."
                );
            }
        });

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        back,
                        spacer,
                        submit
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content =
                new VBox(
                        22,
                        heading,
                        meritBox,
                        grievanceBox,
                        proofBox,
                        statusBox,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Grievance",
                        scrollPane
                )
        );
    }

    // =========================================================
    // DETAIL BOX
    // =========================================================

    private static VBox detail(
            String label,
            String value
    ) {

        Label labelText =
                new Label(
                        label
                );

        labelText.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueText =
                new Label(
                        safe(value)
                );

        valueText.setWrapText(
                true
        );

        valueText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        VBox box =
                new VBox(
                        5,
                        labelText,
                        valueText
                );

        box.setPadding(
                new Insets(12)
        );

        box.setStyle(
                "-fx-background-color: #0F150F;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        return box;
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // ALERT
    // =========================================================

    private static void message(
            Alert.AlertType type,
            String title,
            String text
    ) {

        Alert alert =
                new Alert(
                        type
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                text
        );

        alert.showAndWait();
    }

    private static String safe(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return "Not Available";
        }

        return value;
    }

    public static File getProofFile() {

        return proofFile;
    }
}