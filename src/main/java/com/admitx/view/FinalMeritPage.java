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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class FinalMeritPage {

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
                meritDAO.getCurrentStudentFinalMerit();

        boolean finalPublished =
                merit != null;

        // =====================================================
        // HEADING
        // =====================================================

        Label title =
                new Label("Final Merit List");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        finalPublished
                                ? "Your final merit details are now available."
                                : "Final merit list has not been published yet."
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
        // STATUS
        // =====================================================

        Label published =
                new Label(
                        finalPublished
                                ? "●  FINAL MERIT PUBLISHED"
                                : "●  FINAL MERIT NOT PUBLISHED"
                );

        if (finalPublished) {

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

            published.setStyle(
                    "-fx-background-color: #2A1B10;" +
                    "-fx-text-fill: " + ORANGE + ";" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 8 14 8 14;" +
                    "-fx-background-radius: 20px;" +
                    "-fx-border-color: #5C3518;" +
                    "-fx-border-radius: 20px;"
            );
        }

        HBox status =
                new HBox(
                        published
                );

        status.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // CANDIDATE INFORMATION
        // =====================================================

        Label candidateSection =
                sectionTitle(
                        "CANDIDATE"
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

        VBox candidateCard =
                new VBox(
                        15,
                        candidateSection,

                        detail(
                                "Candidate",
                                value(candidateName)
                        ),

                        detail(
                                "Student Email",
                                value(
                                        student.getEmail()
                                )
                        )
                );

        candidateCard.setPadding(
                new Insets(22)
        );

        styleCard(
                candidateCard
        );

        // =====================================================
        // FINAL MERIT INFORMATION
        // =====================================================

        Label meritSection =
                sectionTitle(
                        "FINAL MERIT INFORMATION"
                );

        VBox meritCard =
                new VBox(
                        15
                );

        meritCard.getChildren().add(
                meritSection
        );

        if (finalPublished) {

            meritCard.getChildren().addAll(

                    createRankCard(
                            "FINAL MERIT RANK",
                            String.valueOf(
                                    merit.getFinalMeritNumber()
                            )
                    ),

                    detail(
                            "Category",
                            value(
                                    merit.getCategory()
                            )
                    ),

                    detail(
                            "Category Rank",
                            value(
                                    merit.getCategory()
                            )
                                    + " - "
                                    + merit.getFinalCategoryRank()
                    ),

                    detail(
                            "CET Percentile",
                            value(
                                    merit.getCetPercentile()
                            )
                    ),

                    detail(
                            "Eligible CAP Rounds",
                            "CAP Round 1, 2 and 3"
                    ),

                    detail(
                            "Status",
                            "Final Merit Published"
                    )
            );

        } else {

            meritCard.getChildren().addAll(

                    createRankCard(
                            "FINAL MERIT RANK",
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
                            "Eligible CAP Rounds",
                            "Not Available"
                    ),

                    detail(
                            "Status",
                            "Not Published"
                    )
            );
        }

        meritCard.setPadding(
                new Insets(22)
        );

        styleCard(
                meritCard
        );

        // =====================================================
        // NEXT STEP
        // =====================================================

        Label nextTitle =
                sectionTitle(
                        "NEXT STEP"
                );

        Label nextDescription =
                new Label();

        if (finalPublished) {

            nextDescription.setText(
                    "Your final merit rank has been published. "
                            + "You can now search colleges and continue "
                            + "with preference filling."
            );

        } else {

            nextDescription.setText(
                    "The final merit list is not published yet. "
                            + "Please check again after the counsellor "
                            + "completes grievance review and publishes "
                            + "the final merit list."
            );
        }

        nextDescription.setWrapText(
                true
        );

        nextDescription.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox nextCard =
                new VBox(
                        8,
                        nextTitle,
                        nextDescription
                );

        nextCard.setPadding(
                new Insets(18)
        );

        nextCard.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 10px;"
        );

        // =====================================================
        // DASHBOARD BUTTON
        // =====================================================

        Button dashboard =
                new Button(
                        "← Dashboard"
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
                        "Refresh Status"
                );

        styleSecondaryButton(
                refresh
        );

        refresh.setOnAction(e ->

                Navigation.goTo(
                        FinalMeritPage.getScene()
                )
        );

        // =====================================================
        // COLLEGE SEARCH BUTTON
        // =====================================================

        Button collegeSearch =
                new Button(
                        "Proceed to College Search →"
                );

        collegeSearch.setPrefHeight(
                42
        );

        collegeSearch.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        collegeSearch.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #101510;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        collegeSearch.setDisable(
                !finalPublished
        );

        collegeSearch.setOnAction(e -> {

            if (!finalPublished) {

                return;
            }

            Navigation.goTo(
                    CollegeSearchPage.getScene()
            );
        });

        // =====================================================
        // BUTTON ROW
        // =====================================================

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        dashboard,
                        refresh,
                        spacer,
                        collegeSearch
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
                        status,
                        candidateCard,
                        meritCard,
                        nextCard,
                        buttons
                );

        content.setPadding(
                new Insets(30)
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

        return new Scene(
                StudentLayout.create(
                        "Final Merit List",
                        scrollPane
                )
        );
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private static Label sectionTitle(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
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
                "-fx-font-size: 36px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox box =
                new VBox(
                        5,
                        labelText,
                        rankText
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
    private static void styleCard(Region region) {

        region.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );
        }
}