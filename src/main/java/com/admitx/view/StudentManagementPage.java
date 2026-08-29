package com.admitx.view;

import java.util.List;

import com.admitx.dao.ApplicationDAO;
import com.admitx.dao.ApplicationDAO.ApplicationRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class StudentManagementPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#131A13";

    private static final String LIME =
            "#B7FF00";

    private static final String TEXT =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    private static final String BORDER =
            "#293529";

    public static Scene getScene() {

        ApplicationDAO applicationDAO =
                new ApplicationDAO();

        ObservableList<ApplicationRecord> applications =
                FXCollections.observableArrayList();

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "Student Applications"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Review and verify submitted student applications."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        // =====================================================
        // STATISTICS
        // =====================================================

        Label totalValue =
                createStatValue("0");

        Label pendingValue =
                createStatValue("0");

        Label verifiedValue =
                createStatValue("0");

        Label rejectedValue =
                createStatValue("0");

        GridPane stats =
                new GridPane();

        stats.setHgap(15);

        stats.add(
                createStatCard(
                        "TOTAL APPLICATIONS",
                        totalValue
                ),
                0,
                0
        );

        stats.add(
                createStatCard(
                        "PENDING",
                        pendingValue
                ),
                1,
                0
        );

        stats.add(
                createStatCard(
                        "VERIFIED",
                        verifiedValue
                ),
                2,
                0
        );

        stats.add(
                createStatCard(
                        "REJECTED",
                        rejectedValue
                ),
                3,
                0
        );

        for (int i = 0; i < 4; i++) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(
                    25
            );

            stats.getColumnConstraints()
                    .add(column);
        }

        // =====================================================
        // SEARCH
        // =====================================================

        TextField search =
                new TextField();

        search.setPromptText(
                "Search by student name or email"
        );

        search.setPrefHeight(
                42
        );

        search.setStyle(
                "-fx-background-color: #0D120D;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #6F7A6F;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 0 14 0 14;"
        );

        HBox.setHgrow(
                search,
                Priority.ALWAYS
        );

        Button searchButton =
                createPrimaryButton(
                        "Search",
                        100
                );

        Button refreshButton =
                createDarkButton(
                        "Refresh",
                        100
                );

        HBox searchBox =
                new HBox(
                        10,
                        search,
                        searchButton,
                        refreshButton
                );

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setPadding(
                new Insets(18)
        );

        searchBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        // =====================================================
        // TABLE
        // =====================================================

        TableView<ApplicationRecord> table =
                new TableView<>();

        TableColumn<ApplicationRecord, String>
                nameColumn =
                new TableColumn<>(
                        "Candidate"
                );

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "candidateName"
                )
        );

        TableColumn<ApplicationRecord, String>
                emailColumn =
                new TableColumn<>(
                        "Student Email"
                );

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "studentEmail"
                )
        );

        TableColumn<ApplicationRecord, String>
                categoryColumn =
                new TableColumn<>(
                        "Category"
                );

        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "category"
                )
        );

        TableColumn<ApplicationRecord, String>
                applicationColumn =
                new TableColumn<>(
                        "Application"
                );

        applicationColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        TableColumn<ApplicationRecord, String>
                verificationColumn =
                new TableColumn<>(
                        "Verification"
                );

        verificationColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "verificationStatus"
                )
        );

        TableColumn<ApplicationRecord, String>
                submittedColumn =
                new TableColumn<>(
                        "Submitted"
                );

        submittedColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "submittedAt"
                )
        );

        table.getColumns().addAll(
                nameColumn,
                emailColumn,
                categoryColumn,
                applicationColumn,
                verificationColumn,
                submittedColumn
        );

        table.setPrefHeight(
                380
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        // =====================================================
        // COMMENTS
        // =====================================================

        Label commentTitle =
                new Label(
                        "Counsellor Comments"
                );

        commentTitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        TextArea comments =
                new TextArea();

        comments.setPromptText(
                "Enter counsellor remarks..."
        );

        comments.setPrefRowCount(
                3
        );

        comments.setWrapText(
                true
        );

        comments.setStyle(
                "-fx-control-inner-background: #0D120D;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #6F7A6F;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;"
        );

        // =====================================================
        // LOAD DATA
        // =====================================================

        Runnable loadApplications = () -> {

            List<ApplicationRecord> data =
                    applicationDAO
                            .getAllApplications();

            applications.setAll(
                    data
            );

            table.setItems(
                    applications
            );

            totalValue.setText(
                    String.valueOf(
                            data.size()
                    )
            );

            int pending = 0;
            int verified = 0;
            int rejected = 0;

            for (
                    ApplicationRecord application :
                    data
            ) {

                String status =
                        application
                                .getVerificationStatus();

                if (
                        "Pending"
                                .equalsIgnoreCase(status)
                ) {

                    pending++;

                } else if (
                        "Verified"
                                .equalsIgnoreCase(status)
                ) {

                    verified++;

                } else if (
                        "Rejected"
                                .equalsIgnoreCase(status)
                ) {

                    rejected++;
                }
            }

            pendingValue.setText(
                    String.valueOf(pending)
            );

            verifiedValue.setText(
                    String.valueOf(verified)
            );

            rejectedValue.setText(
                    String.valueOf(rejected)
            );
        };

        loadApplications.run();

        // =====================================================
        // SEARCH
        // =====================================================

        searchButton.setOnAction(e -> {

            String keyword =
                    search.getText()
                            .trim()
                            .toLowerCase();

            if (keyword.isEmpty()) {

                table.setItems(
                        applications
                );

                return;
            }

            ObservableList<ApplicationRecord>
                    filtered =
                    FXCollections
                            .observableArrayList();

            for (
                    ApplicationRecord application :
                    applications
            ) {

                String name =
                        safe(
                                application
                                        .getCandidateName()
                        )
                                .toLowerCase();

                String email =
                        safe(
                                application
                                        .getStudentEmail()
                        )
                                .toLowerCase();

                if (
                        name.contains(keyword)
                                ||
                        email.contains(keyword)
                ) {

                    filtered.add(
                            application
                    );
                }
            }

            table.setItems(
                    filtered
            );
        });

        search.setOnAction(e ->
                searchButton.fire()
        );

        refreshButton.setOnAction(e -> {

            search.clear();

            loadApplications.run();
        });

        // =====================================================
        // SELECTED STUDENT
        // =====================================================

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (
                                observable,
                                oldValue,
                                selected
                        ) -> {

                            if (selected != null) {

                                comments.setText(
                                        safe(
                                                selected
                                                        .getCounsellorComment()
                                        )
                                );
                            }
                        }
                );

        // =====================================================
        // BUTTONS
        // =====================================================

        Button view =
                createDarkButton(
                        "View Application",
                        150
                );

        Button verify =
                createPrimaryButton(
                        "Verify",
                        110
                );

        Button reject =
                createDangerButton(
                        "Reject",
                        110
                );

        Button saveComment =
                createDarkButton(
                        "Save Comment",
                        140
                );

        // =====================================================
        // VIEW
        // =====================================================

        view.setOnAction(e -> {

            ApplicationRecord selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Student Applications",
                        "Please select an application."
                );

                return;
            }

            String information =
                    "Candidate: "
                            + safe(
                            selected
                                    .getCandidateName()
                    )

                    + "\n\nStudent Email: "
                    + safe(
                            selected
                                    .getStudentEmail()
                    )

                    + "\nMobile: "
                    + safe(
                            selected
                                    .getMobileNumber()
                    )

                    + "\nCategory: "
                    + safe(
                            selected
                                    .getCategory()
                    )

                    + "\n\nApplication Status: "
                    + safe(
                            selected
                                    .getStatus()
                    )

                    + "\nVerification Status: "
                    + safe(
                            selected
                                    .getVerificationStatus()
                    )

                    + "\nSubmitted: "
                    + safe(
                            selected
                                    .getSubmittedAt()
                    )

                    + "\n\nCounsellor Comment:\n"
                    + safe(
                            selected
                                    .getCounsellorComment()
                    );

            showMessage(
                    Alert.AlertType.INFORMATION,
                    "Application Details",
                    information
            );
        });

        // =====================================================
        // VERIFY
        // =====================================================

        verify.setOnAction(e -> {

            ApplicationRecord selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Verification",
                        "Please select an application."
                );

                return;
            }

            boolean success =
                    applicationDAO
                            .verifyApplication(
                                    selected
                                            .getStudentEmail()
                            );

            if (success) {

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Verification",
                        "Application verified successfully."
                );

                loadApplications.run();

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Verification",
                        "Unable to verify application."
                );
            }
        });

        // =====================================================
        // REJECT
        // =====================================================

        reject.setOnAction(e -> {

            ApplicationRecord selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Application",
                        "Please select an application."
                );

                return;
            }

            Alert confirmation =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirmation.setTitle(
                    "Reject Application"
            );

            confirmation.setHeaderText(
                    "Reject selected application?"
            );

            confirmation.setContentText(
                    selected.getStudentEmail()
            );

            confirmation.showAndWait()
                    .ifPresent(response -> {

                        if (
                                response
                                        == ButtonType.OK
                        ) {

                            boolean success =
                                    applicationDAO
                                            .rejectApplication(
                                                    selected
                                                            .getStudentEmail()
                                            );

                            if (success) {

                                showMessage(
                                        Alert.AlertType.INFORMATION,
                                        "Application",
                                        "Application rejected."
                                );

                                loadApplications.run();

                            } else {

                                showMessage(
                                        Alert.AlertType.ERROR,
                                        "Application",
                                        "Unable to reject application."
                                );
                            }
                        }
                    });
        });

        // =====================================================
        // SAVE COMMENT
        // =====================================================

        saveComment.setOnAction(e -> {

            ApplicationRecord selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Comment",
                        "Please select an application."
                );

                return;
            }

            String comment =
                    comments.getText()
                            .trim();

            if (comment.isEmpty()) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Comment",
                        "Please enter a comment."
                );

                return;
            }

            boolean success =
                    applicationDAO
                            .saveCounsellorComment(
                                    selected
                                            .getStudentEmail(),
                                    comment
                            );

            if (success) {

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Comment",
                        "Comment saved successfully."
                );

                loadApplications.run();

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Comment",
                        "Unable to save comment."
                );
            }
        });

        // =====================================================
        // ACTION BAR
        // =====================================================

        HBox actions =
                new HBox(
                        10,
                        view,
                        verify,
                        reject
                );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        Region commentSpacer =
                new Region();

        HBox.setHgrow(
                commentSpacer,
                Priority.ALWAYS
        );

        HBox commentActions =
                new HBox(
                        commentSpacer,
                        saveComment
                );

        VBox commentCard =
                new VBox(
                        10,
                        commentTitle,
                        comments,
                        commentActions
                );

        commentCard.setPadding(
                new Insets(18)
        );

        commentCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content =
                new VBox(
                        20,
                        heading,
                        stats,
                        searchBox,
                        table,
                        actions,
                        commentCard
                );

        content.setPadding(
                new Insets(5)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

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

        BorderPane layout =
                CounsellorLayout.create(
                        "Students",
                        scrollPane
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private static VBox createStatCard(
            String title,
            Label value
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox card =
                new VBox(
                        7,
                        titleLabel,
                        value
                );

        card.setPadding(
                new Insets(16)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        return card;
    }

    private static Label createStatValue(
            String value
    ) {

        Label label =
                new Label(value);

        label.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    // =========================================================
    // BUTTONS
    // =========================================================

    private static Button createPrimaryButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    private static Button createDarkButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color: #1C251C;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #354235;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    private static Button createDangerButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color: #DC2626;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================================================
    // SAFE
    // =========================================================

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

    // =========================================================
    // ALERT
    // =========================================================

    private static void showMessage(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}