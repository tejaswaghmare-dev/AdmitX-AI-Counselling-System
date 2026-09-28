package com.admitx.view;

import com.admitx.dao.ApplicationDAO;
import com.admitx.dao.ApplicationDAO.ApplicationRecord;
import com.admitx.dao.ApplicationDAO.DocumentVerificationRecord;
import com.admitx.util.AsyncTaskRunner;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CounsellorDocumentVerificationPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String RED = "#FF6B6B";
    private static final String ORANGE = "#F59E0B";

    private static final ApplicationDAO applicationDAO =
            new ApplicationDAO();

    private static final ObservableList<ApplicationRecord> pendingApplications =
            FXCollections.observableArrayList();

    private static TableView<ApplicationRecord> applicationTable;
    private static VBox documentList;
    private static Label selectedStudentLabel;
    private static Label selectedStudentDetails;
    private static Label selectedStatusLabel;
    private static Button verifyApplicationButton;
    private static Button rejectApplicationButton;
    private static TextArea applicationRemark;
    private static ProgressIndicator documentLoading;
    private static ApplicationRecord selectedApplication;

    public static Scene getScene() {

        BorderPane host = new BorderPane();
        host.setStyle("-fx-background-color:" + BG + ";");

        VBox loading = new VBox(15);
        loading.setAlignment(Pos.CENTER);
        loading.setStyle("-fx-background-color:" + BG + ";");

        ProgressIndicator indicator = new ProgressIndicator();
        indicator.setPrefSize(55, 55);

        Label loadingLabel = new Label("Loading pending applications...");
        loadingLabel.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:13px;"
        );

        loading.getChildren().addAll(indicator, loadingLabel);
        host.setCenter(loading);

        Scene scene = new Scene(
                CounsellorLayout.create(
                        "Document Verification",
                        host
                ),
                1400,
                800
        );

        loadPendingApplications(host);

        return scene;
    }

    private static void loadPendingApplications(BorderPane host) {

        AsyncTaskRunner.run(
                applicationDAO::getAllApplications,
                applications -> {
                    pendingApplications.clear();

                    if (applications != null) {
                        applications.stream()
                                .filter(application ->
                                        "Pending".equalsIgnoreCase(
                                                application.getVerificationStatus()
                                        )
                                )
                                .sorted(Comparator.comparing(
                                        ApplicationRecord::getCandidateName,
                                        String.CASE_INSENSITIVE_ORDER
                                ))
                                .forEach(pendingApplications::add);
                    }

                    host.setCenter(createPageContent());
                },
                error -> {
                    error.printStackTrace();
                    host.setCenter(createPageContent());
                    showError(
                            "Loading Failed",
                            "Could not load pending applications. Please try again."
                    );
                }
        );
    }

    private static VBox createPageContent() {

        Label title = new Label("Student Document Verification");
        title.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label subtitle = new Label(
                "Review uploaded student documents before approving the application."
        );
        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox heading = new VBox(6, title, subtitle);

        VBox applicationPanel = createApplicationPanel();
        VBox verificationPanel = createVerificationPanel();

        HBox workspace = new HBox(
                18,
                applicationPanel,
                verificationPanel
        );
        workspace.setFillHeight(true);
        HBox.setHgrow(verificationPanel, Priority.ALWAYS);

        if (!pendingApplications.isEmpty()) {
            applicationTable.getSelectionModel().selectFirst();
        }

        VBox content = new VBox(22, heading, workspace);
        content.setPadding(new Insets(26));
        content.setStyle("-fx-background-color:" + BG + ";");
        content.setFillWidth(true);

        VBox.setVgrow(workspace, Priority.ALWAYS);

        return content;
    }

    private static VBox createApplicationPanel() {

        Label sectionTitle =
                createSectionTitle("PENDING APPLICATIONS");

        Label countLabel = new Label(
                pendingApplications.size()
                        + " application(s) waiting for review"
        );

        countLabel.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        applicationTable =
                new TableView<>(pendingApplications);

        applicationTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        applicationTable.setPrefWidth(470);
        applicationTable.setMinWidth(420);
        applicationTable.setPrefHeight(470);
        applicationTable.setFixedCellSize(48);

        applicationTable.setStyle(
                "-fx-background-color:" + ROW + ";" +
                "-fx-control-inner-background:" + ROW + ";" +
                "-fx-table-cell-border-color:" + BORDER + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-selection-bar:#263619;" +
                "-fx-selection-bar-non-focused:#263619;" +
                "-fx-focus-color:transparent;" +
                "-fx-faint-focus-color:transparent;"
        );

        Label placeholder =
                new Label("No applications are pending verification.");

        placeholder.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        applicationTable.setPlaceholder(placeholder);

        TableColumn<ApplicationRecord, String> nameColumn =
                new TableColumn<>("Candidate");

        nameColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        displayName(data.getValue())
                )
        );

        TableColumn<ApplicationRecord, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        safe(data.getValue().getStudentEmail())
                )
        );

        TableColumn<ApplicationRecord, String> categoryColumn =
                new TableColumn<>("Category");

        categoryColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        safe(data.getValue().getCategory())
                )
        );

        styleTableColumn(nameColumn);
        styleTableColumn(emailColumn);
        styleTableColumn(categoryColumn);

        applicationTable.getColumns().addAll(
                nameColumn,
                emailColumn,
                categoryColumn
        );

        applicationTable.setRowFactory(
                table -> {
                    TableRow<ApplicationRecord> row =
                            new TableRow<>();

                    row.setStyle(
                            "-fx-background-color:" + ROW + ";" +
                            "-fx-border-color:transparent transparent "
                                    + BORDER + " transparent;" +
                            "-fx-border-width:0 0 1 0;"
                    );

                    row.selectedProperty()
                            .addListener(
                                    (observable, oldValue, selected) -> {
                                        if (selected) {
                                            row.setStyle(
                                                    "-fx-background-color:#263619;" +
                                                    "-fx-border-color:transparent transparent "
                                                            + LIME + " transparent;" +
                                                    "-fx-border-width:0 0 1 0;"
                                            );
                                        } else {
                                            row.setStyle(
                                                    "-fx-background-color:" + ROW + ";" +
                                                    "-fx-border-color:transparent transparent "
                                                            + BORDER + " transparent;" +
                                                    "-fx-border-width:0 0 1 0;"
                                            );
                                        }
                                    }
                            );

                    return row;
                }
        );

        applicationTable.skinProperty()
                .addListener((obs, oldSkin, newSkin) -> {
                    applicationTable.applyCss();

                    applicationTable.lookupAll(".column-header")
                            .forEach(node ->
                                    node.setStyle(
                                            "-fx-background-color:#1B251B;" +
                                            "-fx-border-color:" + BORDER + ";"
                                    )
                            );

                    applicationTable.lookupAll(".column-header .label")
                            .forEach(node ->
                                    node.setStyle(
                                            "-fx-text-fill:" + WHITE + ";" +
                                            "-fx-font-weight:bold;" +
                                            "-fx-font-size:12px;"
                                    )
                            );

                    applicationTable.lookupAll(".filler")
                            .forEach(node ->
                                    node.setStyle(
                                            "-fx-background-color:#1B251B;"
                                    )
                            );
                });

        applicationTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedApplication = newValue;
                        loadStudentDocuments(newValue);
                    }
                });

        VBox tableContainer =
                new VBox(applicationTable);

        tableContainer.setStyle(
                "-fx-background-color:" + ROW + ";" +
                "-fx-background-radius:8px;"
        );

        VBox.setVgrow(
                applicationTable,
                Priority.ALWAYS
        );

        VBox card = new VBox(
                14,
                sectionTitle,
                countLabel,
                tableContainer
        );

        card.setPrefWidth(470);
        card.setMinWidth(420);
        card.setMaxWidth(500);

        VBox.setVgrow(
                tableContainer,
                Priority.ALWAYS
        );

        styleCard(card);

        return card;
    }

    private static VBox createVerificationPanel() {

        selectedStudentLabel = new Label("Select an application");
        selectedStudentLabel.setStyle(
                "-fx-font-size:22px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        selectedStudentDetails = createMutedLabel(
                "Choose a student from the pending applications list."
        );
        selectedStudentDetails.setWrapText(true);

        selectedStatusLabel = new Label("PENDING");
        selectedStatusLabel.setStyle(statusStyle("Pending"));

        Region headingSpacer = new Region();
        HBox.setHgrow(headingSpacer, Priority.ALWAYS);

        HBox studentHeading = new HBox(
                12,
                new VBox(5, selectedStudentLabel, selectedStudentDetails),
                headingSpacer,
                selectedStatusLabel
        );
        studentHeading.setAlignment(Pos.CENTER_LEFT);

        Label documentsTitle = createSectionTitle("UPLOADED DOCUMENTS");

        documentLoading = new ProgressIndicator();
        documentLoading.setPrefSize(38, 38);
        documentLoading.setVisible(false);
        documentLoading.setManaged(false);

        documentList = new VBox(10);
        documentList.getChildren().add(
                createMutedLabel("Select a student to view uploaded documents.")
        );

        VBox documentContainer = new VBox(
                12,
                documentsTitle,
                documentLoading,
                documentList
        );

        ScrollPane documentScroll = new ScrollPane(documentContainer);
        documentScroll.setFitToWidth(true);
        documentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        documentScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        documentScroll.setStyle(
                "-fx-background:" + CARD + ";" +
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:transparent;"
        );
        documentScroll.setPrefHeight(300);
        documentScroll.setMinHeight(180);
        documentScroll.setMaxHeight(320);

        Label remarkTitle = createSectionTitle("APPLICATION REMARK");

        applicationRemark = new TextArea();
        applicationRemark.setPromptText(
                "Add a remark for the student, especially when rejecting an application..."
        );
        applicationRemark.setWrapText(true);
        applicationRemark.setPrefRowCount(3);
        applicationRemark.setMaxHeight(95);
        applicationRemark.setStyle(
                "-fx-control-inner-background:" + ROW + ";" +
                "-fx-background-color:" + ROW + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-prompt-text-fill:" + MUTED + ";" +
                "-fx-highlight-fill:" + LIME + ";" +
                "-fx-highlight-text-fill:#0B100B;" +
                "-fx-font-size:13px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:4px;"
        );

        rejectApplicationButton = new Button("Reject Application");
        styleRejectButton(rejectApplicationButton);
        rejectApplicationButton.setDisable(true);
        rejectApplicationButton.setOnAction(e -> rejectSelectedApplication());

        verifyApplicationButton = new Button("Verify Application");
        stylePrimaryButton(verifyApplicationButton);
        verifyApplicationButton.setDisable(true);
        verifyApplicationButton.setOnAction(e -> verifySelectedApplication());

        Region buttonSpacer = new Region();
        HBox.setHgrow(buttonSpacer, Priority.ALWAYS);

        HBox buttons = new HBox(
                12,
                rejectApplicationButton,
                buttonSpacer,
                verifyApplicationButton
        );
        buttons.setAlignment(Pos.CENTER_LEFT);

        VBox panel = new VBox(
                18,
                studentHeading,
                documentScroll,
                remarkTitle,
                applicationRemark,
                buttons
        );
        panel.setMinWidth(0);
        panel.setMaxWidth(Double.MAX_VALUE);
        styleCard(panel);
        HBox.setHgrow(panel, Priority.ALWAYS);

        return panel;
    }

    private static void loadStudentDocuments(ApplicationRecord application) {

        if (application == null) {
            return;
        }

        selectedStudentLabel.setText(displayName(application));
        selectedStudentDetails.setText(
                safe(application.getStudentEmail())
                        + "  •  "
                        + safe(application.getMobileNumber())
                        + "  •  Category: "
                        + safe(application.getCategory())
        );
        selectedStatusLabel.setText("PENDING");
        selectedStatusLabel.setStyle(statusStyle("Pending"));
        applicationRemark.setText(
                safe(application.getCounsellorComment())
        );

        verifyApplicationButton.setDisable(true);
        rejectApplicationButton.setDisable(true);
        documentList.getChildren().clear();
        documentLoading.setManaged(true);
        documentLoading.setVisible(true);

        String email = application.getStudentEmail();

        AsyncTaskRunner.run(
                () -> {
                    Map<String, String> urls =
                            applicationDAO.getStudentDocumentUrls(email);
                    Map<String, DocumentVerificationRecord> verification =
                            applicationDAO.getDocumentVerification(email);
                    return new StudentDocumentData(urls, verification);
                },
                data -> {
                    if (selectedApplication == null
                            || !safe(selectedApplication.getStudentEmail())
                            .equalsIgnoreCase(safe(email))) {
                        return;
                    }

                    documentLoading.setVisible(false);
                    documentLoading.setManaged(false);
                    renderDocuments(data);
                    rejectApplicationButton.setDisable(false);
                    refreshVerifyButton(data);
                },
                error -> {
                    error.printStackTrace();
                    documentLoading.setVisible(false);
                    documentLoading.setManaged(false);
                    documentList.getChildren().setAll(
                            createErrorLabel("Unable to load student documents.")
                    );
                    rejectApplicationButton.setDisable(false);
                    verifyApplicationButton.setDisable(true);
                }
        );
    }

    private static void renderDocuments(StudentDocumentData data) {

        documentList.getChildren().clear();

        if (data.urls().isEmpty()) {
            documentList.getChildren().add(
                    createErrorLabel("No uploaded documents were found for this student.")
            );
            return;
        }

        List<String> documentNames = new ArrayList<>(data.urls().keySet());
        documentNames.sort(String.CASE_INSENSITIVE_ORDER);

        for (String documentName : documentNames) {
            String url = data.urls().get(documentName);
            DocumentVerificationRecord verification =
                    data.verification().get(documentName);

            documentList.getChildren().add(
                    createDocumentRow(
                            documentName,
                            url,
                            verification
                    )
            );
        }
    }

    private static HBox createDocumentRow(
            String documentName,
            String url,
            DocumentVerificationRecord verification
    ) {

        String currentStatus = verification == null
                ? "Pending"
                : verification.getStatus();

        String currentRemark = verification == null
                ? ""
                : verification.getRemark();

        Label nameLabel = new Label(documentName);
        nameLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label statusLabel = new Label(currentStatus.toUpperCase());
        statusLabel.setMinWidth(82);
        statusLabel.setAlignment(Pos.CENTER);
        statusLabel.setStyle(statusStyle(currentStatus));

        Label remarkLabel = new Label(
                currentRemark.isBlank()
                        ? "No document remark"
                        : currentRemark
        );
        remarkLabel.setWrapText(true);
        remarkLabel.setStyle(
                "-fx-font-size:10px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox documentText = new VBox(4, nameLabel, remarkLabel);
        HBox.setHgrow(documentText, Priority.ALWAYS);
        documentText.setMaxWidth(Double.MAX_VALUE);

        Button viewButton = new Button("View");
        styleSecondaryButton(viewButton);
        viewButton.setOnAction(e -> openDocument(url));

        Button verifyButton = new Button("Verify");
        styleSmallVerifyButton(verifyButton);

        Button rejectButton = new Button("Reject");
        styleSmallRejectButton(rejectButton);

        verifyButton.setOnAction(e ->
                saveDocumentStatus(
                        documentName,
                        "Verified",
                        "",
                        statusLabel,
                        remarkLabel,
                        verifyButton,
                        rejectButton
                )
        );

        rejectButton.setOnAction(e -> {
            TextArea reason = new TextArea();
            reason.setPromptText("Reason for rejecting this document...");
            reason.setWrapText(true);
            reason.setPrefRowCount(3);

            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Reject Document");
            dialog.setHeaderText("Reject " + documentName + "?");
            dialog.getDialogPane().setContent(reason);

            dialog.showAndWait().ifPresent(buttonType -> {
                if (buttonType == javafx.scene.control.ButtonType.OK) {
                    if (reason.getText() == null
                            || reason.getText().isBlank()) {
                        showError(
                                "Remark Required",
                                "Please enter the reason for rejecting this document."
                        );
                        return;
                    }

                    saveDocumentStatus(
                            documentName,
                            "Rejected",
                            reason.getText(),
                            statusLabel,
                            remarkLabel,
                            verifyButton,
                            rejectButton
                    );
                }
            });
        });

        HBox row = new HBox(
                10,
                documentText,
                statusLabel,
                viewButton,
                verifyButton,
                rejectButton
        );
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(13));
        row.setMaxWidth(Double.MAX_VALUE);
        row.setStyle(
                "-fx-background-color:" + ROW + ";" +
                "-fx-background-radius:9px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:9px;"
        );

        return row;
    }

    private static void saveDocumentStatus(
            String documentName,
            String status,
            String remark,
            Label statusLabel,
            Label remarkLabel,
            Button verifyButton,
            Button rejectButton
    ) {

        if (selectedApplication == null) {
            return;
        }

        String email = selectedApplication.getStudentEmail();

        verifyButton.setDisable(true);
        rejectButton.setDisable(true);
        statusLabel.setText("SAVING...");
        statusLabel.setStyle(statusStyle("Pending"));

        AsyncTaskRunner.run(
                () -> applicationDAO.saveDocumentVerification(
                        email,
                        documentName,
                        status,
                        remark
                ),
                saved -> {
                    if (Boolean.TRUE.equals(saved)) {
                        statusLabel.setText(status.toUpperCase());
                        statusLabel.setStyle(statusStyle(status));
                        remarkLabel.setText(
                                remark == null || remark.isBlank()
                                        ? "No document remark"
                                        : remark.trim()
                        );
                        loadStudentDocuments(selectedApplication);
                    } else {
                        statusLabel.setText("FAILED");
                        statusLabel.setStyle(statusStyle("Rejected"));
                        verifyButton.setDisable(false);
                        rejectButton.setDisable(false);
                        showError(
                                "Save Failed",
                                "Could not save document verification."
                        );
                    }
                },
                error -> {
                    error.printStackTrace();
                    statusLabel.setText("FAILED");
                    statusLabel.setStyle(statusStyle("Rejected"));
                    verifyButton.setDisable(false);
                    rejectButton.setDisable(false);
                    showError(
                            "Save Failed",
                            "Could not save document verification."
                    );
                }
        );
    }

    private static void refreshVerifyButton(StudentDocumentData data) {

        if (data.urls().isEmpty()) {
            verifyApplicationButton.setDisable(true);
            return;
        }

        boolean allVerified = true;

        for (String documentName : data.urls().keySet()) {
            DocumentVerificationRecord record =
                    data.verification().get(documentName);

            if (record == null
                    || !"Verified".equalsIgnoreCase(record.getStatus())) {
                allVerified = false;
                break;
            }
        }

        verifyApplicationButton.setDisable(!allVerified);
    }

    private static void verifySelectedApplication() {

        if (selectedApplication == null) {
            return;
        }

        String email = selectedApplication.getStudentEmail();
        String comment = applicationRemark.getText();

        verifyApplicationButton.setDisable(true);
        rejectApplicationButton.setDisable(true);
        verifyApplicationButton.setText("Verifying...");

        AsyncTaskRunner.run(
                () -> {
                    boolean commentSaved =
                            applicationDAO.saveCounsellorComment(email, comment);

                    if (!commentSaved) {
                        return false;
                    }

                    return applicationDAO.verifyApplication(email);
                },
                verified -> {
                    verifyApplicationButton.setText("Verify Application");

                    if (Boolean.TRUE.equals(verified)) {
                        showInfo(
                                "Application Verified",
                                "The student's documents and application have been verified."
                        );
                        reloadAfterDecision();
                    } else {
                        verifyApplicationButton.setDisable(false);
                        rejectApplicationButton.setDisable(false);
                        showError(
                                "Verification Incomplete",
                                "Every uploaded document must be marked Verified before the application can be verified."
                        );
                    }
                },
                error -> {
                    error.printStackTrace();
                    verifyApplicationButton.setText("Verify Application");
                    verifyApplicationButton.setDisable(false);
                    rejectApplicationButton.setDisable(false);
                    showError(
                            "Verification Failed",
                            "Could not verify this application."
                    );
                }
        );
    }

    private static void rejectSelectedApplication() {

        if (selectedApplication == null) {
            return;
        }

        String comment = applicationRemark.getText();

        if (comment == null || comment.isBlank()) {
            showError(
                    "Remark Required",
                    "Please enter a reason before rejecting the application."
            );
            return;
        }

        String email = selectedApplication.getStudentEmail();

        rejectApplicationButton.setDisable(true);
        verifyApplicationButton.setDisable(true);
        rejectApplicationButton.setText("Rejecting...");

        AsyncTaskRunner.run(
                () -> {
                    boolean commentSaved =
                            applicationDAO.saveCounsellorComment(email, comment);

                    if (!commentSaved) {
                        return false;
                    }

                    return applicationDAO.rejectApplication(email);
                },
                rejected -> {
                    rejectApplicationButton.setText("Reject Application");

                    if (Boolean.TRUE.equals(rejected)) {
                        showInfo(
                                "Application Rejected",
                                "The application has been rejected and the counsellor remark was saved."
                        );
                        reloadAfterDecision();
                    } else {
                        rejectApplicationButton.setDisable(false);
                        showError(
                                "Rejection Failed",
                                "Could not reject this application."
                        );
                    }
                },
                error -> {
                    error.printStackTrace();
                    rejectApplicationButton.setText("Reject Application");
                    rejectApplicationButton.setDisable(false);
                    showError(
                            "Rejection Failed",
                            "Could not reject this application."
                    );
                }
        );
    }

    private static void reloadAfterDecision() {

        if (selectedApplication != null) {
            pendingApplications.remove(selectedApplication);
        }

        selectedApplication = null;
        selectedStudentLabel.setText("Select an application");
        selectedStudentDetails.setText(
                "Choose a student from the pending applications list."
        );
        selectedStatusLabel.setText("PENDING");
        applicationRemark.clear();
        documentList.getChildren().setAll(
                createMutedLabel("Select a student to view uploaded documents.")
        );
        verifyApplicationButton.setDisable(true);
        rejectApplicationButton.setDisable(true);

        if (!pendingApplications.isEmpty()) {
            applicationTable.getSelectionModel().selectFirst();
        }
    }

    private static void openDocument(String url) {

        try {
            if (url == null || url.isBlank()) {
                showError("Document Missing", "No document URL is available.");
                return;
            }

            if (!Desktop.isDesktopSupported()) {
                showError(
                        "Cannot Open Document",
                        "Opening links is not supported on this computer."
                );
                return;
            }

            Desktop.getDesktop().browse(new URI(url));

        } catch (Exception e) {
            e.printStackTrace();
            showError(
                    "Cannot Open Document",
                    "The uploaded document could not be opened."
            );
        }
    }

    private static String displayName(ApplicationRecord application) {

        String candidate = safe(application.getCandidateName());

        if (!candidate.isBlank()) {
            return candidate;
        }

        String student = safe(application.getStudentName());
        return student.isBlank() ? "Student" : student;
    }

    private static String safe(String value) {
        return value == null ? "" : value;
    }

    private static Label createSectionTitle(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + LIME + ";"
        );
        return label;
    }

    private static Label createMutedLabel(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size:11px;" +
                "-fx-text-fill:" + MUTED + ";"
        );
        return label;
    }

    private static Label createErrorLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:" + RED + ";"
        );
        return label;
    }

    private static void styleTableColumn(
            TableColumn<ApplicationRecord, String> column
    ) {

        column.setStyle(
                "-fx-alignment:CENTER_LEFT;"
        );

        column.setCellFactory(
                col -> new TableCell<>() {

                    {
                        setStyle(
                                "-fx-background-color:transparent;" +
                                "-fx-text-fill:" + WHITE + ";" +
                                "-fx-font-size:12px;" +
                                "-fx-padding:0 12px;" +
                                "-fx-border-color:transparent;"
                        );
                    }

                    @Override
                    protected void updateItem(
                            String item,
                            boolean empty
                    ) {

                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item);
                            setStyle(
                                    "-fx-background-color:transparent;" +
                                    "-fx-text-fill:" + WHITE + ";" +
                                    "-fx-font-size:12px;" +
                                    "-fx-padding:0 12px;" +
                                    "-fx-border-color:transparent;"
                            );
                        }
                    }
                }
        );
    }

    private static String statusStyle(String status) {

        String color;
        String background;

        if ("Verified".equalsIgnoreCase(status)) {
            color = LIME;
            background = "#1D2A10";
        } else if ("Rejected".equalsIgnoreCase(status)) {
            color = RED;
            background = "#351616";
        } else {
            color = ORANGE;
            background = "#33260E";
        }

        return "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + color + ";" +
                "-fx-background-color:" + background + ";" +
                "-fx-padding:6px 10px;" +
                "-fx-background-radius:20px;";
    }

    private static void styleCard(Region region) {
        region.setPadding(new Insets(20));
        region.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );
    }

    private static void stylePrimaryButton(Button button) {
        button.setPrefHeight(40);
        button.setPadding(new Insets(0, 18, 0, 18));
        button.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
        );
    }

    private static void styleRejectButton(Button button) {
        button.setPrefHeight(40);
        button.setPadding(new Insets(0, 18, 0, 18));
        button.setStyle(
                "-fx-background-color:#351616;" +
                "-fx-text-fill:" + RED + ";" +
                "-fx-border-color:#673333;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );
    }

    private static void styleSecondaryButton(Button button) {
        button.setPrefHeight(34);
        button.setStyle(
                "-fx-background-color:#202B20;" +
                "-fx-text-fill:" + LIME + ";" +
                "-fx-border-color:#3B4A3B;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );
    }

    private static void styleSmallVerifyButton(Button button) {
        button.setPrefHeight(34);
        button.setStyle(
                "-fx-background-color:#1D2A10;" +
                "-fx-text-fill:" + LIME + ";" +
                "-fx-border-color:#425C1C;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );
    }

    private static void styleSmallRejectButton(Button button) {
        button.setPrefHeight(34);
        button.setStyle(
                "-fx-background-color:#351616;" +
                "-fx-text-fill:" + RED + ";" +
                "-fx-border-color:#673333;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );
    }

    private static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private record StudentDocumentData(
            Map<String, String> urls,
            Map<String, DocumentVerificationRecord> verification
    ) {
    }
}
