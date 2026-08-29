package com.admitx.view;

import com.admitx.dao.ApplicationDAO;
import com.admitx.dao.CAPAllotmentDAO;
import com.admitx.dao.CollegeDAO;
import com.admitx.dao.GrievanceDAO;
import com.admitx.dao.MeritDAO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CounsellorDashboardPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#141B14";

    private static final String ROW =
            "#0F150F";

    private static final String BORDER =
            "#293529";

    private static final String LIME =
            "#B7FF00";

    private static final String WHITE =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    public static Scene getScene() {

        // =====================================================
        // DAO
        // =====================================================

        ApplicationDAO applicationDAO =
                new ApplicationDAO();

        CAPAllotmentDAO capDAO =
                new CAPAllotmentDAO();

        MeritDAO meritDAO =
                new MeritDAO();

        GrievanceDAO grievanceDAO =
                new GrievanceDAO();

        CollegeDAO collegeDAO =
                new CollegeDAO();

        // =====================================================
        // APPLICATION COUNTS
        // =====================================================

        int totalApplications =
                applicationDAO
                        .getTotalApplicationCount();

        int verifiedApplications =
                applicationDAO
                        .getVerifiedApplicationCount();

        int pendingApplications =
                applicationDAO
                        .getPendingApplicationCount();

        int rejectedApplications =
                applicationDAO
                        .getRejectedApplicationCount();

        // =====================================================
        // COLLEGE COUNT
        // =====================================================

        int totalColleges =
                collegeDAO
                        .getAllColleges()
                        .size();

        // =====================================================
        // MERIT DATA
        // =====================================================

        int eligibleStudents =
                meritDAO
                        .getEligibleStudentCount();

        int provisionalPublishedCount =
                meritDAO
                        .getPublishedMeritCount();

        int finalPublishedCount =
                meritDAO
                        .getFinalPublishedCount();

        boolean provisionalGenerated =
                meritDAO
                        .isProvisionalGenerated();

        boolean provisionalPublished =
                meritDAO
                        .isProvisionalPublished();

        boolean finalGenerated =
                meritDAO
                        .isFinalGenerated();

        boolean finalPublished =
                meritDAO
                        .isFinalPublished();

        // =====================================================
        // GRIEVANCE DATA
        // =====================================================

        int pendingGrievances =
                grievanceDAO
                        .getPendingCount();

        int approvedGrievances =
                grievanceDAO
                        .getApprovedCount();

        int rejectedGrievances =
                grievanceDAO
                        .getRejectedCount();

        // =====================================================
        // PREFERENCE / CAP DATA
        // =====================================================

        int lockedPreferences =
                capDAO
                        .getLockedPreferenceCount();

        int round1Frozen =
                capDAO
                        .getRound1FrozenCount();

        int round1Betterment =
                capDAO
                        .getRound1BettermentCount();

        int round1Rejected =
                capDAO
                        .getRound1RejectedCount();

        int round2Frozen =
                capDAO
                        .getRound2FrozenCount();

        int round2Betterment =
                capDAO
                        .getRound2BettermentCount();

        int finalAdmissions =
                capDAO
                        .getFinalAdmissionCount();

        // =====================================================
        // VERIFICATION PERCENTAGE
        // =====================================================

        double verificationPercentage =
                totalApplications == 0
                        ? 0
                        :
                        (
                                verifiedApplications
                                        * 100.0
                        )
                                /
                                totalApplications;

        String verificationText =
                String.format(
                        "%.1f%% verified",
                        verificationPercentage
                );

        // =====================================================
        // CURRENT CAP STATUS
        // =====================================================

        String currentCAPStatus =
                determineCAPStatus(
                        lockedPreferences,
                        round1Frozen,
                        round1Betterment,
                        round1Rejected,
                        round2Frozen,
                        round2Betterment,
                        finalAdmissions
                );

        String capStatusDescription =
                determineCAPDescription(
                        currentCAPStatus
                );

        // =====================================================
        // OVERALL PROGRESS
        // =====================================================

        int overallProgress =
                calculateOverallProgress(
                        verifiedApplications,
                        provisionalPublished,
                        finalPublished,
                        lockedPreferences,
                        finalAdmissions
                );

        // =====================================================
        // HEADING
        // =====================================================

        Label title =
                new Label(
                        "Counsellor Dashboard"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Monitor applications, merit activity, "
                                + "preferences and CAP counselling progress."
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
        // PRIMARY STATISTICS
        // =====================================================

        GridPane stats =
                new GridPane();

        stats.setHgap(
                16
        );

        stats.setVgap(
                16
        );

        VBox totalStudentsCard =
                createStatCard(
                        "TOTAL APPLICATIONS",
                        String.valueOf(
                                totalApplications
                        ),
                        "Submitted student applications"
                );

        VBox verifiedStudentsCard =
                createStatCard(
                        "VERIFIED STUDENTS",
                        String.valueOf(
                                verifiedApplications
                        ),
                        verificationText
                );

        VBox pendingVerificationCard =
                createStatCard(
                        "PENDING VERIFICATION",
                        String.valueOf(
                                pendingApplications
                        ),
                        "Requires counsellor review"
                );

        VBox capStatusCard =
                createStatCard(
                        "CAP STATUS",
                        currentCAPStatus,
                        capStatusDescription
                );

        stats.add(
                totalStudentsCard,
                0,
                0
        );

        stats.add(
                verifiedStudentsCard,
                1,
                0
        );

        stats.add(
                pendingVerificationCard,
                2,
                0
        );

        stats.add(
                capStatusCard,
                3,
                0
        );

        for (
                int i = 0;
                i < 4;
                i++
        ) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(
                    25
            );

            stats
                    .getColumnConstraints()
                    .add(
                            column
                    );
        }

        // =====================================================
        // SECONDARY STATISTICS
        // =====================================================

        GridPane secondaryStats =
                new GridPane();

        secondaryStats.setHgap(
                16
        );

        secondaryStats.setVgap(
                16
        );

        secondaryStats.add(
                createStatCard(
                        "COLLEGES",
                        String.valueOf(
                                totalColleges
                        ),
                        "Available colleges"
                ),
                0,
                0
        );

        secondaryStats.add(
                createStatCard(
                        "ELIGIBLE STUDENTS",
                        String.valueOf(
                                eligibleStudents
                        ),
                        "Eligible for merit process"
                ),
                1,
                0
        );

        secondaryStats.add(
                createStatCard(
                        "LOCKED OPTION FORMS",
                        String.valueOf(
                                lockedPreferences
                        ),
                        "Ready for CAP allotment"
                ),
                2,
                0
        );

        secondaryStats.add(
                createStatCard(
                        "FINAL ADMISSIONS",
                        String.valueOf(
                                finalAdmissions
                        ),
                        "Students accepted final seat"
                ),
                3,
                0
        );

        for (
                int i = 0;
                i < 4;
                i++
        ) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(
                    25
            );

            secondaryStats
                    .getColumnConstraints()
                    .add(
                            column
                    );
        }

        // =====================================================
        // COUNSELLING PROGRESS
        // =====================================================

        VBox progressCard =
                createProgressCard(
                        overallProgress,
                        currentCAPStatus,
                        totalApplications,
                        verifiedApplications,
                        lockedPreferences,
                        finalAdmissions
                );

        // =====================================================
        // PENDING ACTIONS
        // =====================================================

        Label actionTitle =
                createSectionTitle(
                        "PENDING ACTIONS"
                );

        VBox pendingActions =
                new VBox(
                        10
                );

        pendingActions
                .getChildren()
                .add(
                        createActionRow(
                                "Student Verifications",
                                pendingApplications
                                        + " applications pending"
                        )
                );

        pendingActions
                .getChildren()
                .add(
                        createActionRow(
                                "Rejected Applications",
                                rejectedApplications
                                        + " applications rejected"
                        )
                );

        pendingActions
                .getChildren()
                .add(
                        createActionRow(
                                "Grievances",
                                pendingGrievances
                                        + " grievances require review"
                        )
                );

        pendingActions
                .getChildren()
                .add(
                        createActionRow(
                                "Option Forms",
                                lockedPreferences
                                        + " locked preference forms"
                        )
                );

        VBox actionCard =
                new VBox(
                        14,
                        actionTitle,
                        pendingActions
                );

        styleCard(
                actionCard
        );

        // =====================================================
        // SYSTEM STATUS
        // =====================================================

        Label statusTitle =
                createSectionTitle(
                        "SYSTEM STATUS"
                );

        VBox systemStatus =
                new VBox(
                        10,
                        createActivityRow(
                                "Provisional Merit",
                                getProvisionalStatus(
                                        provisionalGenerated,
                                        provisionalPublished,
                                        provisionalPublishedCount
                                )
                        ),
                        createActivityRow(
                                "Final Merit",
                                getFinalMeritStatus(
                                        finalGenerated,
                                        finalPublished,
                                        finalPublishedCount
                                )
                        ),
                        createActivityRow(
                                "Grievance Review",
                                pendingGrievances
                                        + " pending • "
                                        + approvedGrievances
                                        + " approved • "
                                        + rejectedGrievances
                                        + " rejected"
                        ),
                        createActivityRow(
                                "CAP Counselling",
                                currentCAPStatus
                                        + " • "
                                        + capStatusDescription
                        )
                );

        VBox statusCard =
                new VBox(
                        14,
                        statusTitle,
                        systemStatus
                );

        styleCard(
                statusCard
        );

        // =====================================================
        // CAP ROUND DETAILS
        // =====================================================

        Label capDetailsTitle =
                createSectionTitle(
                        "CAP ROUND DETAILS"
                );

        VBox capDetails =
                new VBox(
                        10,
                        createActivityRow(
                                "Round 1 Freeze",
                                round1Frozen
                                        + " students accepted seats"
                        ),
                        createActivityRow(
                                "Round 1 Betterment",
                                round1Betterment
                                        + " students requested betterment"
                        ),
                        createActivityRow(
                                "Round 1 Rejected",
                                round1Rejected
                                        + " students rejected seats"
                        ),
                        createActivityRow(
                                "Round 2 Freeze",
                                round2Frozen
                                        + " students accepted seats"
                        ),
                        createActivityRow(
                                "Round 2 Betterment",
                                round2Betterment
                                        + " students requested betterment"
                        ),
                        createActivityRow(
                                "Final Admission",
                                finalAdmissions
                                        + " students confirmed admission"
                        )
                );

        VBox capDetailsCard =
                new VBox(
                        14,
                        capDetailsTitle,
                        capDetails
                );

        styleCard(
                capDetailsCard
        );

        // =====================================================
        // LOWER SECTION
        // =====================================================

        HBox lowerSection =
                new HBox(
                        16,
                        progressCard,
                        actionCard,
                        statusCard
                );

        HBox.setHgrow(
                progressCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                actionCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                statusCard,
                Priority.ALWAYS
        );

        progressCard.setMaxWidth(
                Double.MAX_VALUE
        );

        actionCard.setMaxWidth(
                Double.MAX_VALUE
        );

        statusCard.setMaxWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox root =
                new VBox(
                        24,
                        heading,
                        stats,
                        secondaryStats,
                        lowerSection,
                        capDetailsCard
                );

        root.setPadding(
                new Insets(
                        30
                )
        );

        root.setStyle(
                "-fx-background-color: "
                        + BG + ";"
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        root
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background: "
                        + BG + ";" +
                "-fx-background-color: "
                        + BG + ";" +
                "-fx-border-color: transparent;"
        );

        // =====================================================
        // COUNSELLOR LAYOUT
        // =====================================================

        BorderPane layout =
                CounsellorLayout.create(
                        "Dashboard",
                        scrollPane
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    // =========================================================
    // DETERMINE CAP STATUS
    // =========================================================

    private static String determineCAPStatus(
            int lockedPreferences,
            int round1Frozen,
            int round1Betterment,
            int round1Rejected,
            int round2Frozen,
            int round2Betterment,
            int finalAdmissions
    ) {

        if (finalAdmissions > 0) {

            return "Round 3";
        }

        if (
                round2Frozen > 0
                        ||
                        round2Betterment > 0
        ) {

            return "Round 2";
        }

        if (
                round1Frozen > 0
                        ||
                        round1Betterment > 0
                        ||
                        round1Rejected > 0
        ) {

            return "Round 1";
        }

        if (lockedPreferences > 0) {

            return "CAP Ready";
        }

        return "Not Started";
    }

    // =========================================================
    // CAP DESCRIPTION
    // =========================================================

    private static String determineCAPDescription(
            String status
    ) {

        if (
                "Round 3".equals(
                        status
                )
        ) {

            return "Final admission process active";
        }

        if (
                "Round 2".equals(
                        status
                )
        ) {

            return "Round 2 counselling activity";
        }

        if (
                "Round 1".equals(
                        status
                )
        ) {

            return "Round 1 counselling activity";
        }

        if (
                "CAP Ready".equals(
                        status
                )
        ) {

            return "Locked option forms available";
        }

        return "CAP counselling has not started";
    }

    // =========================================================
    // OVERALL PROGRESS
    // =========================================================

    private static int calculateOverallProgress(
            int verifiedApplications,
            boolean provisionalPublished,
            boolean finalPublished,
            int lockedPreferences,
            int finalAdmissions
    ) {

        int progress =
                0;

        if (verifiedApplications > 0) {

            progress += 20;
        }

        if (provisionalPublished) {

            progress += 20;
        }

        if (finalPublished) {

            progress += 20;
        }

        if (lockedPreferences > 0) {

            progress += 20;
        }

        if (finalAdmissions > 0) {

            progress += 20;
        }

        return progress;
    }

    // =========================================================
    // PROVISIONAL STATUS
    // =========================================================

    private static String getProvisionalStatus(
            boolean generated,
            boolean published,
            int publishedCount
    ) {

        if (published) {

            return "Published • "
                    + publishedCount
                    + " merit records";
        }

        if (generated) {

            return "Generated • Waiting for publication";
        }

        return "Not generated";
    }

    // =========================================================
    // FINAL MERIT STATUS
    // =========================================================

    private static String getFinalMeritStatus(
            boolean generated,
            boolean published,
            int publishedCount
    ) {

        if (published) {

            return "Published • "
                    + publishedCount
                    + " final merit records";
        }

        if (generated) {

            return "Generated • Waiting for publication";
        }

        return "Not generated";
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private static VBox createStatCard(
            String title,
            String value,
            String description
    ) {

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + MUTED + ";"
        );

        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setWrapText(
                true
        );

        valueLabel.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + LIME + ";"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: "
                        + MUTED + ";"
        );

        VBox card =
                new VBox(
                        7,
                        titleLabel,
                        valueLabel,
                        descriptionLabel
                );

        card.setPadding(
                new Insets(
                        18
                )
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setMinHeight(
                125
        );

        card.setStyle(
                "-fx-background-color: "
                        + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: "
                        + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        return card;
    }

    // =========================================================
    // PROGRESS CARD
    // =========================================================

    private static VBox createProgressCard(
            int overallProgress,
            String capStatus,
            int totalApplications,
            int verifiedApplications,
            int lockedPreferences,
            int finalAdmissions
    ) {

        Label title =
                createSectionTitle(
                        "COUNSELLING PROGRESS"
                );

        Label round =
                new Label(
                        capStatus
                );

        round.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + WHITE + ";"
        );

        Label percentage =
                new Label(
                        overallProgress
                                + "%"
                );

        percentage.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + LIME + ";"
        );

        Region track =
                new Region();

        track.setPrefHeight(
                8
        );

        track.setMaxWidth(
                Double.MAX_VALUE
        );

        track.setStyle(
                "-fx-background-color: #263026;" +
                "-fx-background-radius: 10px;"
        );

        Region progress =
                new Region();

        progress.setPrefHeight(
                8
        );

        progress.prefWidthProperty()
                .bind(
                        track
                                .widthProperty()
                                .multiply(
                                        overallProgress
                                                / 100.0
                                )
                );

        progress.setStyle(
                "-fx-background-color: "
                        + LIME + ";" +
                "-fx-background-radius: 10px;"
        );

        StackPane progressBar =
                new StackPane(
                        track,
                        progress
                );

        progressBar.setAlignment(
                Pos.CENTER_LEFT
        );

        progressBar.setMaxWidth(
                Double.MAX_VALUE
        );

        Label applications =
                createMutedLabel(
                        "Applications: "
                                + totalApplications
                );

        Label verified =
                createMutedLabel(
                        "Verified: "
                                + verifiedApplications
                );

        Label locked =
                createMutedLabel(
                        "Option Forms Locked: "
                                + lockedPreferences
                );

        Label admissions =
                createMutedLabel(
                        "Final Admissions: "
                                + finalAdmissions
                );

        VBox card =
                new VBox(
                        12,
                        title,
                        round,
                        percentage,
                        progressBar,
                        applications,
                        verified,
                        locked,
                        admissions
                );

        styleCard(
                card
        );

        return card;
    }

    // =========================================================
    // ACTION ROW
    // =========================================================

    private static HBox createActionRow(
            String title,
            String description
    ) {

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + WHITE + ";"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: "
                        + MUTED + ";"
        );

        VBox text =
                new VBox(
                        3,
                        titleLabel,
                        descriptionLabel
                );

        Label dot =
                new Label(
                        "●"
                );

        dot.setStyle(
                "-fx-text-fill: "
                        + LIME + ";" +
                "-fx-font-size: 10px;"
        );

        HBox row =
                new HBox(
                        10,
                        dot,
                        text
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        10
                )
        );

        row.setStyle(
                "-fx-background-color: "
                        + ROW + ";" +
                "-fx-background-radius: 8px;"
        );

        return row;
    }

    // =========================================================
    // ACTIVITY ROW
    // =========================================================

    private static HBox createActivityRow(
            String title,
            String description
    ) {

        Label icon =
                new Label(
                        "✓"
                );

        icon.setMinSize(
                28,
                28
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: "
                        + LIME + ";" +
                "-fx-font-weight: bold;"
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + WHITE + ";"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: "
                        + MUTED + ";"
        );

        VBox text =
                new VBox(
                        3,
                        titleLabel,
                        descriptionLabel
                );

        HBox row =
                new HBox(
                        10,
                        icon,
                        text
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        8
                )
        );

        return row;
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + LIME + ";"
        );

        return label;
    }

    // =========================================================
    // MUTED LABEL
    // =========================================================

    private static Label createMutedLabel(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: "
                        + MUTED + ";"
        );

        return label;
    }

    // =========================================================
    // CARD STYLE
    // =========================================================

    private static void styleCard(
            Region region
    ) {

        region.setPadding(
                new Insets(
                        20
                )
        );

        region.setStyle(
                "-fx-background-color: "
                        + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: "
                        + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );
    }
}