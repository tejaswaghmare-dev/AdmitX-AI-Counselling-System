package com.admitx.view;

import com.admitx.dao.MeritDAO;
import com.admitx.dao.MeritDAO.MeritRecord;
import com.admitx.model.Student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ProvisionalMeritPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String ORANGE = "#F97316";

    public static Scene getScene() {

        Student student =
                Student.getInstance();

        MeritDAO meritDAO =
                new MeritDAO();

        MeritRecord merit =
                meritDAO.getCurrentStudentMerit();

        boolean meritPublished =
                merit != null;

        // =====================================================
        // HEADING
        // =====================================================

        Label title =
                new Label(
                        "Provisional Merit List"
                );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Your provisional merit information for CAP counselling."
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
        // PUBLISH STATUS
        // =====================================================

        Label published =
                new Label();

        if (meritPublished) {

            published.setText(
                    "●  PUBLISHED"
            );

            published.setStyle(
                    "-fx-background-color: #1D2A10;" +
                    "-fx-text-fill: " + LIME + ";" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 8 14 8 14;" +
                    "-fx-background-radius: 20px;" +
                    "-fx-border-color: #3D5520;" +
                    "-fx-border-radius: 20px;"
            );

        } else {

            published.setText(
                    "●  NOT PUBLISHED"
            );

            published.setStyle(
                    "-fx-background-color: #241810;" +
                    "-fx-text-fill: " + ORANGE + ";" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 8 14 8 14;" +
                    "-fx-background-radius: 20px;" +
                    "-fx-border-color: #5A3720;" +
                    "-fx-border-radius: 20px;"
            );
        }

        HBox statusRow =
                new HBox(
                        published
                );

        statusRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // CANDIDATE CARD
        // =====================================================

        VBox candidateCard =
                new VBox(
                        15
                );

        candidateCard.setPadding(
                new Insets(22)
        );

        candidateCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label candidateTitle =
                new Label(
                        "CANDIDATE INFORMATION"
                );

        candidateTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        String candidateName;

        if (
                merit != null &&
                merit.getCandidateName() != null &&
                !merit.getCandidateName().isBlank()
        ) {

            candidateName =
                    merit.getCandidateName();

        } else if (
                student.getCandidateName() != null &&
                !student.getCandidateName().isBlank()
        ) {

            candidateName =
                    student.getCandidateName();

        } else {

            candidateName =
                    student.getUsername();
        }

        String studentEmail =
                student.getEmail();

        String percentile =
                merit != null
                        ? merit.getCetPercentile()
                        : student.getCetPercentile();

        candidateCard.getChildren().addAll(

                candidateTitle,

                detail(
                        "Candidate",
                        value(candidateName)
                ),

                detail(
                        "Student Email",
                        value(studentEmail)
                ),

                detail(
                        "MHT CET Percentile",
                        value(percentile)
                )
        );

        // =====================================================
        // MERIT CARD
        // =====================================================

        Label meritTitle =
                new Label(
                        "MERIT INFORMATION"
                );

        meritTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox meritCard =
                new VBox(
                        15
                );

        meritCard.setPadding(
                new Insets(22)
        );

        meritCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        if (meritPublished) {

            String category =
                    value(
                            merit.getCategory()
                    );

            String categoryRank =
                    category
                            + " - "
                            + merit.getCategoryRank();

            meritCard.getChildren().addAll(

                    meritTitle,

                    createRankCard(
                            "PROVISIONAL MERIT NO.",
                            String.valueOf(
                                    merit.getProvisionalMeritNumber()
                            )
                    ),

                    detail(
                            "Category",
                            category
                    ),

                    detail(
                            "Category Rank",
                            categoryRank
                    ),

                    detail(
                            "CET Percentile",
                            value(
                                    merit.getCetPercentile()
                            )
                    ),

                    detail(
                            "Status",
                            "Published"
                    )
            );

        } else {

            meritCard.getChildren().addAll(

                    meritTitle,

                    createRankCard(
                            "PROVISIONAL MERIT NO.",
                            "--"
                    ),

                    detail(
                            "Category",
                            value(
                                    student.getCategory()
                            )
                    ),

                    detail(
                            "Category Rank",
                            "Not Available"
                    ),

                    detail(
                            "CET Percentile",
                            value(
                                    student.getCetPercentile()
                            )
                    ),

                    detail(
                            "Status",
                            "Not Published"
                    )
            );
        }

        // =====================================================
        // INFORMATION MESSAGE
        // =====================================================

        Label info =
                new Label();

        if (meritPublished) {

            info.setText(
                    "Your provisional merit number is used during "
                            + "the CAP seat allotment process. "
                            + "If you find any incorrect information, "
                            + "you can raise a grievance."
            );

        } else {

            info.setText(
                    "The provisional merit list has not been published "
                            + "for your application yet. "
                            + "Please check again after the counsellor "
                            + "publishes the merit list."
            );
        }

        info.setWrapText(
                true
        );

        info.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 15px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;"
        );

        // =====================================================
        // GRIEVANCE BUTTON
        // =====================================================

        Button grievance =
                new Button(
                        "Raise Grievance"
                );

        styleGrievanceButton(
                grievance
        );

        grievance.setDisable(
                !meritPublished
        );

        grievance.setOnAction(e ->

                Navigation.goTo(
                        GrievanceSubmissionPage.getScene()
                )
        );

        // =====================================================
        // DASHBOARD BUTTON
        // =====================================================

        Button dashboard =
                new Button(
                        "←  Dashboard"
                );

        styleSecondaryButton(
                dashboard
        );

        dashboard.setOnAction(e ->

                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        // =====================================================
        // REFRESH BUTTON
        // =====================================================

        Button refresh =
                new Button(
                        "Refresh"
                );

        styleSecondaryButton(
                refresh
        );

        refresh.setOnAction(e ->

                Navigation.goTo(
                        ProvisionalMeritPage.getScene()
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons;

        if (meritPublished) {

            buttons =
                    new HBox(
                            12,
                            dashboard,
                            refresh,
                            spacer,
                            grievance
                    );

        } else {

            buttons =
                    new HBox(
                            12,
                            dashboard,
                            spacer,
                            refresh
                    );
        }

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
                        statusRow,
                        candidateCard,
                        meritCard,
                        info,
                        buttons
                );

        content.setPadding(
                new Insets(5)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =====================================================
        // SCROLL
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
                        "Provisional Merit List",
                        page
                )
        );
    }

    // =========================================================
    // DETAIL
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
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueText =
                new Label(
                        value(value)
                );

        valueText.setWrapText(
                true
        );

        valueText.setStyle(
                "-fx-font-size: 15px;" +
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
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        return box;
    }

    // =========================================================
    // RANK CARD
    // =========================================================

    private static VBox createRankCard(
            String label,
            String rank
    ) {

        Label labelText =
                new Label(
                        label
                );

        labelText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label rankText =
                new Label(
                        rank
                );

        rankText.setStyle(
                "-fx-font-size: 34px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox box =
                new VBox(
                        5,
                        labelText,
                        rankText
                );

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setPadding(
                new Insets(16)
        );

        box.setStyle(
                "-fx-background-color: #18220F;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 10px;"
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

    // =========================================================
    // GRIEVANCE BUTTON
    // =========================================================

    private static void styleGrievanceButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // SAFE VALUE
    // =========================================================

    private static String value(
            String text
    ) {

        if (
                text == null ||
                text.isBlank()
        ) {

            return "Not Available";
        }

        return text;
    }
}