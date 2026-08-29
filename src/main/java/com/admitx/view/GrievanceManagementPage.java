package com.admitx.view;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;

import com.admitx.dao.GrievanceDAO;
import com.admitx.dao.GrievanceDAO.GrievanceRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GrievanceManagementPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String RED = "#DC2626";

    private static final GrievanceDAO grievanceDAO =
            new GrievanceDAO();

    private static final ObservableList<GrievanceRecord> data =
            FXCollections.observableArrayList();

    public static Scene getScene() {

        // =====================================================
        // HEADING
        // =====================================================

        Label title =
                new Label("Grievance Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Review student grievances before final merit publication."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        5,
                        title,
                        subtitle
                );

        // =====================================================
        // STATS
        // =====================================================

        int pending =
                grievanceDAO.getPendingCount();

        int approved =
                grievanceDAO.getApprovedCount();

        int rejected =
                grievanceDAO.getRejectedCount();

        HBox stats =
                new HBox(
                        15,
                        createStatCard(
                                "Pending",
                                String.valueOf(pending)
                        ),
                        createStatCard(
                                "Approved",
                                String.valueOf(approved)
                        ),
                        createStatCard(
                                "Rejected",
                                String.valueOf(rejected)
                        )
                );

        HBox.setHgrow(
                stats.getChildren().get(0),
                Priority.ALWAYS
        );

        HBox.setHgrow(
                stats.getChildren().get(1),
                Priority.ALWAYS
        );

        HBox.setHgrow(
                stats.getChildren().get(2),
                Priority.ALWAYS
        );

        // =====================================================
        // TABLE
        // =====================================================

        TableView<GrievanceRecord> table =
                new TableView<>();

        table.setItems(data);

        TableColumn<GrievanceRecord, String> nameColumn =
                new TableColumn<>("Candidate");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "candidateName"
                )
        );

        TableColumn<GrievanceRecord, String> emailColumn =
                new TableColumn<>("Student Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "studentEmail"
                )
        );

        TableColumn<GrievanceRecord, Integer> meritColumn =
                new TableColumn<>("Merit No.");

        meritColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "provisionalMeritNumber"
                )
        );

        TableColumn<GrievanceRecord, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        nameColumn.setPrefWidth(200);
        emailColumn.setPrefWidth(280);
        meritColumn.setPrefWidth(120);
        statusColumn.setPrefWidth(150);

        table.getColumns().addAll(
                nameColumn,
                emailColumn,
                meritColumn,
                statusColumn
        );

        table.setPrefHeight(280);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-control-inner-background: " + CARD + ";" +
                "-fx-table-cell-border-color: " + BORDER + ";" +
                "-fx-text-background-color: " + WHITE + ";"
        );

        loadGrievances();

        VBox tableCard =
                new VBox(
                        12,
                        sectionTitle("STUDENT GRIEVANCES"),
                        table
                );

        tableCard.setPadding(
                new Insets(20)
        );

        styleCard(tableCard);

        // =====================================================
        // SELECTED GRIEVANCE DETAILS
        // =====================================================

        Label selectedStudent =
                new Label("No grievance selected");

        selectedStudent.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label emailValue =
                new Label("-");

        Label meritValue =
                new Label("-");

        Label statusValue =
                new Label("-");

        Label grievanceValue =
                new Label(
                        "Select a grievance from the table."
                );

        grievanceValue.setWrapText(true);

        styleValue(emailValue);
        styleValue(meritValue);
        styleValue(statusValue);
        styleValue(grievanceValue);

        VBox selectedDetails =
                new VBox(
                        12,
                        sectionTitle("SELECTED GRIEVANCE"),
                        selectedStudent,
                        detailRow(
                                "Student Email",
                                emailValue
                        ),
                        detailRow(
                                "Provisional Merit",
                                meritValue
                        ),
                        detailRow(
                                "Status",
                                statusValue
                        ),
                        detailRow(
                                "Grievance",
                                grievanceValue
                        )
                );

        selectedDetails.setPadding(
                new Insets(20)
        );

        styleCard(selectedDetails);

        // =====================================================
        // COUNSELLOR COMMENT
        // =====================================================

        TextArea comment =
                new TextArea();

        comment.setPromptText(
                "Enter counsellor comment..."
        );

        comment.setPrefRowCount(4);

        comment.setWrapText(true);

        comment.setStyle(
                "-fx-control-inner-background: #0F150F;" +
                "-fx-background-color: #0F150F;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: #687268;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;"
        );

        VBox commentCard =
                new VBox(
                        10,
                        sectionTitle("COUNSELLOR COMMENT"),
                        comment
                );

        commentCard.setPadding(
                new Insets(20)
        );

        styleCard(commentCard);

        // =====================================================
        // BUTTONS
        // =====================================================

        Button viewProof =
                new Button("View Proof");

        Button approve =
                new Button("Approve Grievance");

        Button reject =
                new Button("Reject Grievance");

        Button refresh =
                new Button("Refresh");

        Button back =
                new Button("← Merit List");

        styleSecondary(viewProof);
        stylePrimary(approve);
        styleDanger(reject);
        styleSecondary(refresh);
        styleSecondary(back);

        viewProof.setDisable(true);
        approve.setDisable(true);
        reject.setDisable(true);

        // =====================================================
        // TABLE SELECTION
        // =====================================================

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, selected) -> {

                            if (selected == null) {

                                return;
                            }

                            selectedStudent.setText(
                                    safe(
                                            selected.getCandidateName()
                                    )
                            );

                            emailValue.setText(
                                    safe(
                                            selected.getStudentEmail()
                                    )
                            );

                            meritValue.setText(
                                    String.valueOf(
                                            selected.getProvisionalMeritNumber()
                                    )
                            );

                            statusValue.setText(
                                    safe(
                                            selected.getStatus()
                                    )
                            );

                            grievanceValue.setText(
                                    safe(
                                            selected.getGrievanceText()
                                    )
                            );

                            comment.setText(
                                    selected.getCounsellorComment() == null
                                            ? ""
                                            : selected.getCounsellorComment()
                            );

                            boolean hasProof =
                                    selected.getProofUrl() != null &&
                                    !selected.getProofUrl().isBlank();

                            viewProof.setDisable(
                                    !hasProof
                            );

                            boolean pendingStatus =
                                    "Pending".equalsIgnoreCase(
                                            selected.getStatus()
                                    );

                            approve.setDisable(
                                    !pendingStatus
                            );

                            reject.setDisable(
                                    !pendingStatus
                            );

                            comment.setDisable(
                                    !pendingStatus
                            );
                        }
                );

        // =====================================================
        // VIEW PROOF
        // =====================================================

        viewProof.setOnAction(e -> {

            GrievanceRecord selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                message(
                        Alert.AlertType.WARNING,
                        "Select Grievance",
                        "Please select a grievance."
                );

                return;
            }

            String proofUrl =
                    selected.getProofUrl();

            if (
                    proofUrl == null ||
                    proofUrl.isBlank()
            ) {

                message(
                        Alert.AlertType.INFORMATION,
                        "No Proof",
                        "This student did not upload supporting proof."
                );

                return;
            }

            try {

                if (Desktop.isDesktopSupported()) {

                    Desktop.getDesktop()
                            .browse(
                                    new URI(proofUrl)
                            );

                } else {

                    message(
                            Alert.AlertType.INFORMATION,
                            "Proof URL",
                            proofUrl
                    );
                }

            } catch (Exception exception) {

                exception.printStackTrace();

                message(
                        Alert.AlertType.ERROR,
                        "Open Failed",
                        "Unable to open the supporting proof."
                );
            }
        });

        // =====================================================
        // APPROVE
        // =====================================================

        approve.setOnAction(e -> {

            GrievanceRecord selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                message(
                        Alert.AlertType.WARNING,
                        "Select Grievance",
                        "Please select a grievance first."
                );

                return;
            }

            boolean success =
                    grievanceDAO.approveGrievance(
                            selected.getStudentEmail(),
                            comment.getText().trim()
                    );

            if (success) {

                message(
                        Alert.AlertType.INFORMATION,
                        "Grievance Approved",
                        "The student's grievance has been approved."
                );

                Navigation.goTo(
                        GrievanceManagementPage.getScene()
                );

            } else {

                message(
                        Alert.AlertType.ERROR,
                        "Failed",
                        "Unable to approve the grievance."
                );
            }
        });

        // =====================================================
        // REJECT
        // =====================================================

        reject.setOnAction(e -> {

            GrievanceRecord selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                message(
                        Alert.AlertType.WARNING,
                        "Select Grievance",
                        "Please select a grievance first."
                );

                return;
            }

            boolean success =
                    grievanceDAO.rejectGrievance(
                            selected.getStudentEmail(),
                            comment.getText().trim()
                    );

            if (success) {

                message(
                        Alert.AlertType.INFORMATION,
                        "Grievance Rejected",
                        "The student's grievance has been rejected."
                );

                Navigation.goTo(
                        GrievanceManagementPage.getScene()
                );

            } else {

                message(
                        Alert.AlertType.ERROR,
                        "Failed",
                        "Unable to reject the grievance."
                );
            }
        });

        // =====================================================
        // REFRESH
        // =====================================================

        refresh.setOnAction(e ->

                Navigation.goTo(
                        GrievanceManagementPage.getScene()
                )
        );

        // =====================================================
        // BACK
        // =====================================================

        back.setOnAction(e ->

                Navigation.goTo(
                        MeritListManagementPage.getScene()
                )
        );

        HBox actions =
                new HBox(
                        12,
                        back,
                        refresh,
                        viewProof,
                        reject,
                        approve
                );

        actions.setAlignment(
                Pos.CENTER_RIGHT
        );

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content =
                new VBox(
                        20,
                        heading,
                        stats,
                        tableCard,
                        selectedDetails,
                        commentCard,
                        actions
                );

        content.setPadding(
                new Insets(25)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
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

        return new Scene(
                CounsellorLayout.create(
                        "Merit List",
                        scrollPane
                ),
                1400,
                800
        );
    }

    // =========================================================
    // LOAD FIRESTORE
    // =========================================================

    private static void loadGrievances() {

        data.clear();

        List<GrievanceRecord> grievances =
                grievanceDAO.getAllGrievances();

        data.addAll(grievances);
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private static VBox createStatCard(
            String title,
            String value
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox box =
                new VBox(
                        5,
                        titleLabel,
                        valueLabel
                );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setPadding(
                new Insets(18)
        );

        styleCard(box);

        return box;
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private static Label sectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private static VBox detailRow(
            String title,
            Label value
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox box =
                new VBox(
                        4,
                        titleLabel,
                        value
                );

        box.setPadding(
                new Insets(10)
        );

        box.setStyle(
                "-fx-background-color: #0F150F;" +
                "-fx-background-radius: 7px;"
        );

        return box;
    }

    private static void styleValue(
            Label label
    ) {

        label.setWrapText(true);

        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );
    }

    private static void styleCard(
            javafx.scene.layout.Region region
    ) {

        region.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );
    }

    // =========================================================
    // BUTTON STYLES
    // =========================================================

    private static void stylePrimary(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #101510;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleDanger(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setStyle(
                "-fx-background-color: " + RED + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleSecondary(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
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
                new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);

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
}