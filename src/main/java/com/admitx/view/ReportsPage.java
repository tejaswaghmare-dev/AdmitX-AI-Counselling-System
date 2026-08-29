package com.admitx.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.admitx.dao.ApplicationDAO;
import com.admitx.dao.ApplicationDAO.ApplicationRecord;
import com.admitx.dao.CAPAllotmentDAO;
import com.admitx.dao.CollegeDAO;
import com.admitx.dao.GrievanceDAO;
import com.admitx.dao.MeritDAO;
import com.admitx.dao.PreferenceDAO;
import com.admitx.model.College;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ReportsPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#131A13";

    private static final String ROW =
            "#0F150F";

    private static final String BORDER =
            "#293529";

    private static final String LIME =
            "#B7FF00";

    private static final String TEXT =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    public static Scene getScene() {

        // =========================================================
        // DAO
        // =========================================================

        ApplicationDAO applicationDAO =
                new ApplicationDAO();

        CollegeDAO collegeDAO =
                new CollegeDAO();

        MeritDAO meritDAO =
                new MeritDAO();

        CAPAllotmentDAO capDAO =
                new CAPAllotmentDAO();

        GrievanceDAO grievanceDAO =
                new GrievanceDAO();

        PreferenceDAO preferenceDAO =
                new PreferenceDAO();

        // =========================================================
        // APPLICATION DATA
        // =========================================================

        List<ApplicationRecord> applications =
                applicationDAO
                        .getAllApplications();

        int totalStudents =
                applicationDAO
                        .getTotalApplicationCount();

        int verifiedStudents =
                applicationDAO
                        .getVerifiedApplicationCount();

        int pendingStudents =
                applicationDAO
                        .getPendingApplicationCount();

        int rejectedStudents =
                applicationDAO
                        .getRejectedApplicationCount();

        // =========================================================
        // COLLEGE DATA
        // =========================================================

        List<College> colleges =
                collegeDAO
                        .getAllColleges();

        Set<String> uniqueCollegeNames =
                new HashSet<>();

        Set<String> uniqueBranches =
                new HashSet<>();

        int intakeCount =
                0;

        if (colleges != null) {

            for (College college : colleges) {

                if (
                        college.getCollegeName() != null
                        &&
                        !college.getCollegeName().isBlank()
                ) {

                    uniqueCollegeNames.add(
                            college.getCollegeName()
                    );
                }

                if (
                        college.getBranch() != null
                        &&
                        !college.getBranch().isBlank()
                ) {

                    uniqueBranches.add(
                            college.getBranch()
                    );
                }

                intakeCount +=
                        college.getIntake();
            }
        }
        final int totalIntake =
        intakeCount;

        int participatingColleges =
                uniqueCollegeNames.size();

        int participatingColleges1 =
                uniqueCollegeNames.size();

        // =========================================================
        // MERIT DATA
        // =========================================================

        int eligibleStudents =
                meritDAO
                        .getEligibleStudentCount();

        int provisionalPublished =
                meritDAO
                        .getPublishedMeritCount();

        int finalPublished =
                meritDAO
                        .getFinalPublishedCount();

        boolean provisionalGenerated =
                meritDAO
                        .isProvisionalGenerated();

        boolean provisionalListPublished =
                meritDAO
                        .isProvisionalPublished();

        boolean finalGenerated =
                meritDAO
                        .isFinalGenerated();

        boolean finalListPublished =
                meritDAO
                        .isFinalPublished();

        // =========================================================
        // GRIEVANCE DATA
        // =========================================================

        int pendingGrievances =
                grievanceDAO
                        .getPendingCount();

        int approvedGrievances =
                grievanceDAO
                        .getApprovedCount();

        int rejectedGrievances =
                grievanceDAO
                        .getRejectedCount();

        // =========================================================
        // OPTION FORM DATA
        // =========================================================

        int formsStarted =
                preferenceDAO
                        .getStartedPreferenceCount();

        int formsLocked =
                preferenceDAO
                        .getLockedPreferenceCount();

        // =========================================================
        // CAP DATA
        // =========================================================

        int round1Freeze =
                capDAO
                        .getRound1FrozenCount();

        int round1Betterment =
                capDAO
                        .getRound1BettermentCount();

        int round1Rejected =
                capDAO
                        .getRound1RejectedCount();

        int round2Freeze =
                capDAO
                        .getRound2FrozenCount();

        int round2Betterment =
                capDAO
                        .getRound2BettermentCount();

        int finalAdmissions =
                capDAO
                        .getFinalAdmissionCount();

        int capRoundsWithActivity =
                calculateCAPRounds(
                        round1Freeze,
                        round1Betterment,
                        round1Rejected,
                        round2Freeze,
                        round2Betterment,
                        finalAdmissions
                );

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Reports"
                );

        title.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Generate and review counselling reports using live Firestore data."
                );

        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        // =========================================================
        // STUDENT REPORT
        // =========================================================

        Button studentReport =
                createReportButton(
                        "Student Report",
                        "Application and verification statistics."
                );

        studentReport.setOnAction(e -> {

            String report =
                    "STUDENT REPORT\n\n"
                    + "Total Applications : "
                    + totalStudents
                    + "\n"
                    + "Verified Students : "
                    + verifiedStudents
                    + "\n"
                    + "Pending Verification : "
                    + pendingStudents
                    + "\n"
                    + "Rejected Applications : "
                    + rejectedStudents
                    + "\n\n"
                    + "Verification Rate : "
                    + calculatePercentage(
                            verifiedStudents,
                            totalStudents
                    )
                    + "%";

            showReport(
                    "Student Report",
                    report
            );
        });

        // =========================================================
        // MERIT REPORT
        // =========================================================

        Button meritReport =
                createReportButton(
                        "Merit Report",
                        "Provisional and final merit statistics."
                );

        meritReport.setOnAction(e -> {

            String report =
                    "MERIT REPORT\n\n"
                    + "Eligible Students : "
                    + eligibleStudents
                    + "\n\n"
                    + "Provisional Generated : "
                    + yesNo(
                            provisionalGenerated
                    )
                    + "\n"
                    + "Provisional Published : "
                    + yesNo(
                            provisionalListPublished
                    )
                    + "\n"
                    + "Students in Provisional Merit : "
                    + provisionalPublished
                    + "\n\n"
                    + "Final Merit Generated : "
                    + yesNo(
                            finalGenerated
                    )
                    + "\n"
                    + "Final Merit Published : "
                    + yesNo(
                            finalListPublished
                    )
                    + "\n"
                    + "Students in Final Merit : "
                    + finalPublished
                    + "\n\n"
                    + "Pending Grievances : "
                    + pendingGrievances;

            showReport(
                    "Merit Report",
                    report
            );
        });

        // =========================================================
        // COLLEGE REPORT
        // =========================================================

        Button collegeReport =
                createReportButton(
                        "College-wise Report",
                        "Participating colleges and intake information."
                );

        collegeReport.setOnAction(e -> {

            StringBuilder report =
                    new StringBuilder();

            report.append(
                    "COLLEGE-WISE REPORT\n\n"
            );

            report.append(
                    "Participating Colleges : "
            )
            .append(
                    participatingColleges1
            )
            .append(
                    "\n"
            );

            report.append(
                    "College-Branch Records : "
            )
            .append(
                    colleges == null
                            ? 0
                            : colleges.size()
            )
            .append(
                    "\n"
            );

            report.append(
                    "Total Intake : "
            )
            .append(
                    totalIntake
            )
            .append(
                    "\n\n"
            );

            if (
                    colleges == null ||
                    colleges.isEmpty()
            ) {

                report.append(
                        "No college records available."
                );

            } else {

                Set<String> displayed =
                        new HashSet<>();

                for (
                        College college :
                        colleges
                ) {

                    String collegeName =
                            college.getCollegeName();

                    if (
                            collegeName == null ||
                            collegeName.isBlank() ||
                            displayed.contains(
                                    collegeName
                            )
                    ) {

                        continue;
                    }

                    displayed.add(
                            collegeName
                    );

                    report.append(
                            "• "
                    )
                    .append(
                            collegeName
                    )
                    .append(
                            "\n"
                    );
                }
            }

            showReport(
                    "College-wise Report",
                    report.toString()
            );
        });

        // =========================================================
        // BRANCH REPORT
        // =========================================================

        Button branchReport =
                createReportButton(
                        "Branch-wise Report",
                        "Available branches and intake information."
                );

        branchReport.setOnAction(e -> {

            Map<String, Integer> branchIntake =
                    new HashMap<>();

            if (colleges != null) {

                for (College college : colleges) {

                    String branch =
                            college.getBranch();

                    if (
                            branch == null ||
                            branch.isBlank()
                    ) {

                        continue;
                    }

                    branchIntake.put(
                            branch,
                            branchIntake.getOrDefault(
                                    branch,
                                    0
                            )
                            +
                            college.getIntake()
                    );
                }
            }

            StringBuilder report =
                    new StringBuilder();

            report.append(
                    "BRANCH-WISE REPORT\n\n"
            );

            report.append(
                    "Total Branches : "
            )
            .append(
                    uniqueBranches.size()
            )
            .append(
                    "\n\n"
            );

            if (
                    branchIntake.isEmpty()
            ) {

                report.append(
                        "No branch data available."
                );

            } else {

                for (
                        Map.Entry<String, Integer> entry :
                        branchIntake.entrySet()
                ) {

                    report.append(
                            entry.getKey()
                    )
                    .append(
                            " : "
                    )
                    .append(
                            entry.getValue()
                    )
                    .append(
                            " seats\n"
                    );
                }
            }

            showReport(
                    "Branch-wise Report",
                    report.toString()
            );
        });

        // =========================================================
        // CATEGORY REPORT
        // =========================================================

        Button categoryReport =
                createReportButton(
                        "Category-wise Report",
                        "Submitted applications grouped by category."
                );

        categoryReport.setOnAction(e -> {

            Map<String, Integer> categoryCount =
                    new HashMap<>();

            if (applications != null) {

                for (
                        ApplicationRecord application :
                        applications
                ) {

                    String category =
                            application
                                    .getCategory();

                    if (
                            category == null ||
                            category.isBlank()
                    ) {

                        category =
                                "Not Specified";
                    }

                    categoryCount.put(
                            category,
                            categoryCount
                                    .getOrDefault(
                                            category,
                                            0
                                    )
                                    + 1
                    );
                }
            }

            StringBuilder report =
                    new StringBuilder();

            report.append(
                    "CATEGORY-WISE REPORT\n\n"
            );

            report.append(
                    "Total Applications : "
            )
            .append(
                    totalStudents
            )
            .append(
                    "\n\n"
            );

            if (
                    categoryCount.isEmpty()
            ) {

                report.append(
                        "No category information available."
                );

            } else {

                for (
                        Map.Entry<String, Integer> entry :
                        categoryCount.entrySet()
                ) {

                    report.append(
                            entry.getKey()
                    )
                    .append(
                            " : "
                    )
                    .append(
                            entry.getValue()
                    )
                    .append(
                            "\n"
                    );
                }
            }

            showReport(
                    "Category-wise Report",
                    report.toString()
            );
        });

        // =========================================================
        // ROUND REPORT
        // =========================================================

        Button roundReport =
                createReportButton(
                        "Round-wise Report",
                        "CAP Round 1, Round 2 and final admission statistics."
                );

        roundReport.setOnAction(e -> {

            String report =
                    "CAP ROUND REPORT\n\n"

                    + "LOCKED OPTION FORMS\n"
                    + "Forms Started : "
                    + formsStarted
                    + "\n"
                    + "Forms Locked : "
                    + formsLocked
                    + "\n\n"

                    + "CAP ROUND 1\n"
                    + "Freeze : "
                    + round1Freeze
                    + "\n"
                    + "Betterment : "
                    + round1Betterment
                    + "\n"
                    + "Rejected : "
                    + round1Rejected
                    + "\n\n"

                    + "CAP ROUND 2\n"
                    + "Freeze : "
                    + round2Freeze
                    + "\n"
                    + "Betterment : "
                    + round2Betterment
                    + "\n\n"

                    + "FINAL ADMISSION\n"
                    + "Admissions Accepted : "
                    + finalAdmissions;

            showReport(
                    "Round-wise Report",
                    report
            );
        });

        // =========================================================
        // REPORT GRID
        // =========================================================

        GridPane reportGrid =
                new GridPane();

        reportGrid.setHgap(
                16
        );

        reportGrid.setVgap(
                16
        );

        reportGrid.add(
                studentReport,
                0,
                0
        );

        reportGrid.add(
                meritReport,
                1,
                0
        );

        reportGrid.add(
                collegeReport,
                0,
                1
        );

        reportGrid.add(
                branchReport,
                1,
                1
        );

        reportGrid.add(
                categoryReport,
                0,
                2
        );

        reportGrid.add(
                roundReport,
                1,
                2
        );

        reportGrid.getColumnConstraints()
                .addAll(
                        createColumn(),
                        createColumn()
                );

        VBox reportCard =
                new VBox(
                        14,
                        createSectionTitle(
                                "AVAILABLE REPORTS"
                        ),
                        reportGrid
                );

        styleCard(
                reportCard
        );

        // =========================================================
        // MAIN OVERVIEW
        // =========================================================

        VBox overviewCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "REPORT OVERVIEW"
                        ),

                        createOverviewRow(
                                "Applications",
                                String.valueOf(
                                        totalStudents
                                )
                        ),

                        createOverviewRow(
                                "Verified Students",
                                String.valueOf(
                                        verifiedStudents
                                )
                        ),

                        createOverviewRow(
                                "Participating Colleges",
                                String.valueOf(
                                        participatingColleges1
                                )
                        ),

                        createOverviewRow(
                                "Final Merit Students",
                                String.valueOf(
                                        finalPublished
                                )
                        ),

                        createOverviewRow(
                                "Locked Option Forms",
                                String.valueOf(
                                        formsLocked
                                )
                        ),

                        createOverviewRow(
                                "CAP Rounds With Activity",
                                capRoundsWithActivity
                                        + " / 3"
                        ),

                        createOverviewRow(
                                "Final Admissions",
                                String.valueOf(
                                        finalAdmissions
                                )
                        )
                );

        styleCard(
                overviewCard
        );

        // =========================================================
        // GRIEVANCE OVERVIEW
        // =========================================================

        VBox grievanceCard =
                new VBox(
                        12,

                        createSectionTitle(
                                "GRIEVANCE REPORT"
                        ),

                        createOverviewRow(
                                "Pending",
                                String.valueOf(
                                        pendingGrievances
                                )
                        ),

                        createOverviewRow(
                                "Approved",
                                String.valueOf(
                                        approvedGrievances
                                )
                        ),

                        createOverviewRow(
                                "Rejected",
                                String.valueOf(
                                        rejectedGrievances
                                )
                        )
                );

        styleCard(
                grievanceCard
        );

        // =========================================================
        // APPLICATION OVERVIEW
        // =========================================================

        VBox applicationCard =
                new VBox(
                        12,

                        createSectionTitle(
                                "APPLICATION REPORT"
                        ),

                        createOverviewRow(
                                "Total",
                                String.valueOf(
                                        totalStudents
                                )
                        ),

                        createOverviewRow(
                                "Verified",
                                String.valueOf(
                                        verifiedStudents
                                )
                        ),

                        createOverviewRow(
                                "Pending",
                                String.valueOf(
                                        pendingStudents
                                )
                        ),

                        createOverviewRow(
                                "Rejected",
                                String.valueOf(
                                        rejectedStudents
                                )
                        )
                );

        styleCard(
                applicationCard
        );

        HBox statisticsRow =
                new HBox(
                        16,
                        applicationCard,
                        grievanceCard
                );

        HBox.setHgrow(
                applicationCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                grievanceCard,
                Priority.ALWAYS
        );

        applicationCard.setMaxWidth(
                Double.MAX_VALUE
        );

        grievanceCard.setMaxWidth(
                Double.MAX_VALUE
        );

        // =========================================================
        // REFRESH
        // =========================================================

        Button refreshButton =
                new Button(
                        "Refresh Reports"
                );

        refreshButton.setStyle(
                "-fx-background-color:"
                        + LIME + ";" +
                "-fx-text-fill:#071007;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:10 18 10 18;" +
                "-fx-cursor:hand;"
        );

        refreshButton.setOnAction(e ->

                Navigation.goTo(
                        getScene()
                )
        );

        HBox refreshRow =
                new HBox(
                        refreshButton
                );

        refreshRow.setAlignment(
                Pos.CENTER_RIGHT
        );

        // =========================================================
        // NOTE
        // =========================================================

        Label note =
                new Label(
                        "Reports are generated from the current Firestore data. "
                                + "Use Refresh Reports after applications, merit lists, "
                                + "option forms or CAP allotments are updated."
                );

        note.setWrapText(
                true
        );

        note.setStyle(
                "-fx-background-color:#151B10;" +
                "-fx-text-fill:#B9C5B2;" +
                "-fx-font-size:12px;" +
                "-fx-padding:14px;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:#38452B;" +
                "-fx-border-radius:8px;"
        );

        // =========================================================
        // ROOT
        // =========================================================

        VBox root =
                new VBox(
                        20,
                        heading,
                        refreshRow,
                        reportCard,
                        overviewCard,
                        statisticsRow,
                        note
                );

        root.setPadding(
                new Insets(
                        25
                )
        );

        root.setStyle(
                "-fx-background-color:"
                        + BG + ";"
        );

        // =========================================================
        // SCROLL
        // =========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        root
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background:"
                        + BG + ";" +
                "-fx-background-color:"
                        + BG + ";" +
                "-fx-border-color:transparent;"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "Reports",
                        scrollPane
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    // =============================================================
    // CALCULATE CAP ROUND ACTIVITY
    // =============================================================

    private static int calculateCAPRounds(
            int round1Freeze,
            int round1Betterment,
            int round1Rejected,
            int round2Freeze,
            int round2Betterment,
            int finalAdmissions
    ) {

        if (
                finalAdmissions > 0
        ) {

            return 3;
        }

        if (
                round2Freeze > 0 ||
                round2Betterment > 0
        ) {

            return 2;
        }

        if (
                round1Freeze > 0 ||
                round1Betterment > 0 ||
                round1Rejected > 0
        ) {

            return 1;
        }

        return 0;
    }

    // =============================================================
    // PERCENTAGE
    // =============================================================

    private static String calculatePercentage(
            int value,
            int total
    ) {

        if (
                total == 0
        ) {

            return "0.0";
        }

        double percentage =
                value * 100.0
                        / total;

        return String.format(
                "%.1f",
                percentage
        );
    }

    // =============================================================
    // YES / NO
    // =============================================================

    private static String yesNo(
            boolean value
    ) {

        return value
                ? "Yes"
                : "No";
    }

    // =============================================================
    // SECTION TITLE
    // =============================================================

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + LIME + ";"
        );

        return label;
    }

    // =============================================================
    // REPORT BUTTON
    // =============================================================

    private static Button createReportButton(
            String title,
            String description
    ) {

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setStyle(
                "-fx-font-size:10px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        Label action =
                new Label(
                        "View Report  →"
                );

        action.setStyle(
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + LIME + ";"
        );

        VBox graphic =
                new VBox(
                        7,
                        titleLabel,
                        descriptionLabel,
                        action
                );

        graphic.setAlignment(
                Pos.CENTER_LEFT
        );

        Button button =
                new Button();

        button.setGraphic(
                graphic
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                105
        );

        button.setStyle(
                "-fx-background-color:"
                        + ROW + ";" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:9px;" +
                "-fx-background-radius:9px;" +
                "-fx-padding:14px;" +
                "-fx-cursor:hand;"
        );

        GridPane.setHgrow(
                button,
                Priority.ALWAYS
        );

        return button;
    }

    // =============================================================
    // OVERVIEW ROW
    // =============================================================

    private static HBox createOverviewRow(
            String label,
            String value
    ) {

        Label labelText =
                new Label(
                        label
                );

        labelText.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label valueText =
                new Label(
                        value
                );

        valueText.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        HBox row =
                new HBox(
                        labelText,
                        spacer,
                        valueText
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
                "-fx-background-color:"
                        + ROW + ";" +
                "-fx-background-radius:7px;"
        );

        return row;
    }

    // =============================================================
    // COLUMN
    // =============================================================

    private static ColumnConstraints createColumn() {

        ColumnConstraints column =
                new ColumnConstraints();

        column.setPercentWidth(
                50
        );

        return column;
    }

    // =============================================================
    // CARD STYLE
    // =============================================================

    private static void styleCard(
            Region region
    ) {

        region.setPadding(
                new Insets(
                        20
                )
        );

        region.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:10px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:10px;"
        );
    }

    // =============================================================
    // REPORT DIALOG
    // =============================================================

    private static void showReport(
            String title,
            String content
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                title
        );

        TextArea area =
                new TextArea(
                        content
                );

        area.setEditable(
                false
        );

        area.setWrapText(
                true
        );

        area.setPrefWidth(
                600
        );

        area.setPrefHeight(
                450
        );

        area.setStyle(
                "-fx-font-size:13px;"
        );

        alert.getDialogPane()
                .setContent(
                        area
                );

        alert.showAndWait();
    }
}