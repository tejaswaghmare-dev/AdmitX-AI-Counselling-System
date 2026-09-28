package com.admitx.view;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import com.admitx.controller.ImageUploadController;
import com.admitx.controller.StudentInfoAddController;
import com.admitx.dao.ApplicationDAO;
import com.admitx.dao.ApplicationDAO.DocumentVerificationRecord;
import com.admitx.model.Student;
import com.admitx.util.AsyncTaskRunner;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CorrectionDocumentPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String RED = "#FF6B6B";
    private static final String ORANGE = "#F59E0B";

    private static final ApplicationDAO applicationDAO = new ApplicationDAO();
    private static final Student student = Student.getInstance();

    public static Scene getScene() {
        Scene loadingScene = createLoadingScene();

        AsyncTaskRunner.run(
                CorrectionDocumentPage::loadCorrectionData,
                data -> Navigation.goTo(createScene(data)),
                error -> {
                    error.printStackTrace();
                    showError("Correction Documents", "Could not load correction details.");
                    Navigation.goTo(ApplicationStatusPage.getScene());
                }
        );

        return loadingScene;
    }

    private static CorrectionData loadCorrectionData() {
        String email = student.getEmail();
        Map<String, String> urls = applicationDAO.getStudentDocumentUrls(email);
        Map<String, DocumentVerificationRecord> verification =
                applicationDAO.getDocumentVerification(email);
        String comment = applicationDAO.getCounsellorComment(email);

        Map<String, CorrectionItem> rejected = new LinkedHashMap<>();

        for (Map.Entry<String, DocumentVerificationRecord> entry : verification.entrySet()) {
            DocumentVerificationRecord record = entry.getValue();
            if (record != null && "Rejected".equalsIgnoreCase(record.getStatus())) {
                rejected.put(
                        entry.getKey(),
                        new CorrectionItem(
                                entry.getKey(),
                                urls.getOrDefault(entry.getKey(), ""),
                                record.getRemark()
                        )
                );
            }
        }

        return new CorrectionData(rejected, comment);
    }

    private static Scene createScene(CorrectionData correctionData) {
        Label title = new Label("Document Corrections");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: " + WHITE + ";");

        Label subtitle = new Label(
                "Re-upload only the documents rejected by the counsellor, then resubmit your application."
        );
        subtitle.setWrapText(true);
        subtitle.setStyle("-fx-font-size: 13px; -fx-text-fill: " + MUTED + ";");

        VBox heading = new VBox(6, title, subtitle);

        Label remarkTitle = new Label("COUNSELLOR REMARK");
        remarkTitle.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: " + LIME + ";");

        Label overallRemark = new Label(
                correctionData.counsellorComment() == null || correctionData.counsellorComment().isBlank()
                        ? "Check the rejected document remarks below."
                        : correctionData.counsellorComment()
        );
        overallRemark.setWrapText(true);
        overallRemark.setStyle("-fx-font-size: 13px; -fx-text-fill: " + WHITE + ";");

        VBox remarkCard = new VBox(8, remarkTitle, overallRemark);
        remarkCard.setPadding(new Insets(18));
        remarkCard.setStyle(cardStyle());

        VBox documents = new VBox(12);
        Label docsTitle = new Label("DOCUMENTS REQUIRING CORRECTION");
        docsTitle.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: " + LIME + ";");
        documents.getChildren().add(docsTitle);

        Button resubmitButton = new Button("Resubmit Corrected Application  →");
        stylePrimaryButton(resubmitButton);

        if (correctionData.rejectedDocuments().isEmpty()) {
            Label none = new Label("No rejected documents remain. You can resubmit your application.");
            none.setWrapText(true);
            none.setStyle("-fx-text-fill: " + LIME + "; -fx-font-size: 13px;");
            documents.getChildren().add(none);
            resubmitButton.setDisable(false);
        } else {
            resubmitButton.setDisable(true);
            for (CorrectionItem item : correctionData.rejectedDocuments().values()) {
                documents.getChildren().add(createCorrectionRow(item, resubmitButton));
            }
        }

        VBox documentCard = new VBox(14, documents);
        documentCard.setPadding(new Insets(20));
        documentCard.setStyle(cardStyle());

        Label resubmitHint = new Label(
                correctionData.rejectedDocuments().isEmpty()
                        ? "All corrections are ready for resubmission."
                        : "Resubmit becomes available after every rejected document is replaced successfully."
        );
        resubmitHint.setWrapText(true);
        resubmitHint.setStyle("-fx-font-size: 11px; -fx-text-fill: " + MUTED + ";");

        Button backButton = new Button("←  Application Status");
        styleSecondaryButton(backButton);
        backButton.setOnAction(e -> Navigation.goTo(ApplicationStatusPage.getScene()));

        resubmitButton.setOnAction(e -> {
            resubmitButton.setDisable(true);
            resubmitButton.setText("Resubmitting...");

            AsyncTaskRunner.run(
                    applicationDAO::resubmitCorrections,
                    saved -> {
                        if (Boolean.TRUE.equals(saved)) {
                            showInfo(
                                    "Correction Submitted",
                                    "Your corrected documents were submitted successfully and the application is pending verification again."
                            );
                            Navigation.goTo(ApplicationStatusPage.getScene());
                        } else {
                            resubmitButton.setText("Resubmit Corrected Application  →");
                            resubmitButton.setDisable(false);
                            showError(
                                    "Resubmission Incomplete",
                                    "One or more rejected documents still need to be re-uploaded."
                            );
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        resubmitButton.setText("Resubmit Corrected Application  →");
                        resubmitButton.setDisable(false);
                        showError("Resubmission Failed", "Could not resubmit the corrected application.");
                    }
            );
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox actions = new HBox(12, backButton, spacer, resubmitButton);
        actions.setAlignment(Pos.CENTER_LEFT);

        VBox content = new VBox(20, heading, remarkCard, documentCard, resubmitHint, actions);
        content.setPadding(new Insets(5));

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: " + BG + "; -fx-background-color: " + BG + ";");

        BorderPane page = new BorderPane(scrollPane);
        page.setStyle("-fx-background-color: " + BG + ";");

        return new Scene(StudentLayout.create("Document Corrections", page));
    }

    private static VBox createCorrectionRow(CorrectionItem item, Button resubmitButton) {
        Label name = new Label(item.documentName());
        name.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: " + WHITE + ";");

        Label status = new Label("CORRECTION REQUIRED");
        status.setStyle(
                "-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: " + ORANGE + ";"
        );

        Label reason = new Label(
                item.remark() == null || item.remark().isBlank()
                        ? "Counsellor marked this document for correction."
                        : "Reason: " + item.remark()
        );
        reason.setWrapText(true);
        reason.setStyle("-fx-font-size: 12px; -fx-text-fill: " + MUTED + ";");

        Label fileStatus = new Label("Upload a corrected file.");
        fileStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: " + MUTED + ";");

        Button viewButton = new Button("View Current");
        styleSmallButton(viewButton);
        viewButton.setDisable(item.url() == null || item.url().isBlank());
        viewButton.setOnAction(e -> openDocument(item.url()));

        Button uploadButton = new Button("Re-upload");
        stylePrimarySmallButton(uploadButton);

        uploadButton.setOnAction(e -> chooseAndUpload(item.documentName(), uploadButton, fileStatus, resubmitButton));

        HBox actions = new HBox(10, viewButton, uploadButton);
        actions.setAlignment(Pos.CENTER_LEFT);

        VBox row = new VBox(8, name, status, reason, fileStatus, actions);
        row.setPadding(new Insets(16));
        row.setStyle(
                "-fx-background-color: #0F150F; -fx-background-radius: 9px; "
                        + "-fx-border-color: " + BORDER + "; -fx-border-radius: 9px;"
        );

        return row;
    }

    private static void chooseAndUpload(
            String documentName,
            Button uploadButton,
            Label fileStatus,
            Button resubmitButton
    ) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select corrected " + documentName);
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Documents", "*.pdf", "*.jpg", "*.jpeg", "*.png"
                )
        );

        Stage stage = (Stage) uploadButton.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);

        if (file == null) {
            return;
        }

        uploadButton.setDisable(true);
        uploadButton.setText("Uploading...");
        fileStatus.setText("Uploading " + file.getName() + "...");
        fileStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: " + LIME + ";");

        Task<String> uploadTask = new Task<>() {
            @Override
            protected String call() {
                return new ImageUploadController().imageUpload(file);
            }
        };

        uploadTask.setOnSucceeded(event -> {
            String url = uploadTask.getValue();

            if (url == null || url.isBlank()) {
                uploadButton.setDisable(false);
                uploadButton.setText("Try Again");
                fileStatus.setText("Upload failed.");
                fileStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: " + RED + ";");
                return;
            }

            student.getUploadedDocuments().put(documentName, file);
            student.getUploadedDocumentUrls().put(documentName, url);

            AsyncTaskRunner.run(
                    () -> {
                        boolean studentSaved = new StudentInfoAddController().saveApplicationSection();
                        if (!studentSaved) {
                            return false;
                        }
                        return applicationDAO.markDocumentReuploaded(student.getEmail(), documentName);
                    },
                    saved -> {
                        if (Boolean.TRUE.equals(saved)) {
                            uploadButton.setText("Corrected ✓");
                            uploadButton.setDisable(true);
                            fileStatus.setText(file.getName() + "  ✓ Ready for re-verification");
                            fileStatus.setStyle(
                                    "-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: " + LIME + ";"
                            );

                            AsyncTaskRunner.run(
                                    () -> applicationDAO.hasRejectedDocuments(student.getEmail()),
                                    hasRejected -> resubmitButton.setDisable(Boolean.TRUE.equals(hasRejected)),
                                    error -> resubmitButton.setDisable(true)
                            );
                        } else {
                            uploadButton.setDisable(false);
                            uploadButton.setText("Try Again");
                            fileStatus.setText("Could not save corrected document.");
                            fileStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: " + RED + ";");
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        uploadButton.setDisable(false);
                        uploadButton.setText("Try Again");
                        fileStatus.setText("Could not save corrected document.");
                        fileStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: " + RED + ";");
                    }
            );
        });

        uploadTask.setOnFailed(event -> {
            uploadButton.setDisable(false);
            uploadButton.setText("Try Again");
            fileStatus.setText("Upload failed.");
            fileStatus.setStyle("-fx-font-size: 11px; -fx-text-fill: " + RED + ";");
        });

        Thread thread = new Thread(uploadTask, "correction-document-upload");
        thread.setDaemon(true);
        thread.start();
    }

    private static Scene createLoadingScene() {
        ProgressIndicator indicator = new ProgressIndicator();
        indicator.setStyle("-fx-accent: " + LIME + ";");

        Label label = new Label("Loading correction details...");
        label.setStyle("-fx-text-fill: " + MUTED + "; -fx-font-size: 13px;");

        VBox box = new VBox(14, indicator, label);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: " + BG + ";");

        return new Scene(StudentLayout.create("Document Corrections", box));
    }

    private static void openDocument(String url) {
        try {
            if (url == null || url.isBlank() || !Desktop.isDesktopSupported()) {
                showError("Document", "The document cannot be opened on this computer.");
                return;
            }
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
            showError("Document", "Could not open the document.");
        }
    }

    private static String cardStyle() {
        return "-fx-background-color: " + CARD + ";"
                + "-fx-background-radius: 12px;"
                + "-fx-border-color: " + BORDER + ";"
                + "-fx-border-radius: 12px;";
    }

    private static void stylePrimaryButton(Button button) {
        button.setStyle(
                "-fx-background-color: " + LIME + "; -fx-text-fill: #0B100B; "
                        + "-fx-font-weight: bold; -fx-padding: 11 18; -fx-background-radius: 7px;"
        );
    }

    private static void styleSecondaryButton(Button button) {
        button.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: " + WHITE + "; "
                        + "-fx-border-color: " + BORDER + "; -fx-border-radius: 7px; "
                        + "-fx-background-radius: 7px; -fx-padding: 10 16;"
        );
    }

    private static void styleSmallButton(Button button) {
        button.setStyle(
                "-fx-background-color: #202A20; -fx-text-fill: " + WHITE + "; "
                        + "-fx-border-color: " + BORDER + "; -fx-border-radius: 6px; "
                        + "-fx-background-radius: 6px; -fx-padding: 7 12;"
        );
    }

    private static void stylePrimarySmallButton(Button button) {
        button.setStyle(
                "-fx-background-color: " + LIME + "; -fx-text-fill: #0B100B; "
                        + "-fx-font-weight: bold; -fx-background-radius: 6px; -fx-padding: 7 12;"
        );
    }

    private static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private record CorrectionItem(String documentName, String url, String remark) {}

    private record CorrectionData(
            Map<String, CorrectionItem> rejectedDocuments,
            String counsellorComment
    ) {}
}
