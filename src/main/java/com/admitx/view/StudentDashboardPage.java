package com.admitx.view;

import com.admitx.dao.ApplicationDAO;
import com.admitx.dao.CAPAllotmentDAO;
import com.admitx.dao.MeritDAO;
import com.admitx.dao.PreferenceDAO;
import com.admitx.dao.MeritDAO.MeritRecord;

import com.admitx.model.CAPAllotment;
import com.admitx.model.Student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class StudentDashboardPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#141B14";

    private static final String BORDER =
            "#273327";

    private static final String LIME =
            "#B7FF00";

    private static final String WHITE =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    public static Scene getScene() {

        // =====================================================
        // STUDENT
        // =====================================================

        Student student =
                Student.getInstance();

        String studentName =
                student.getUsername();

        String studentEmail =
                student.getEmail();

        if (
                studentName == null ||
                studentName.isBlank()
        ) {

            studentName =
                    "Student";
        }

        // =====================================================
        // DAO
        // =====================================================

        ApplicationDAO applicationDAO =
                new ApplicationDAO();

        MeritDAO meritDAO =
                new MeritDAO();

        PreferenceDAO preferenceDAO =
                new PreferenceDAO();

        CAPAllotmentDAO capDAO =
                new CAPAllotmentDAO();

        // =====================================================
        // APPLICATION DATA
        // =====================================================

        String verificationStatus =
                applicationDAO
                        .getVerificationStatus();

        boolean applicationSubmitted =
                applicationDAO
                        .isApplicationSubmitted();

        boolean applicationVerified =
                "Verified"
                        .equalsIgnoreCase(
                                verificationStatus
                        );

        boolean applicationRejected =
                "Rejected"
                        .equalsIgnoreCase(
                                verificationStatus
                        );

        // =====================================================
        // MERIT DATA
        // =====================================================

        boolean provisionalPublished =
                meritDAO
                        .isProvisionalPublished();

        boolean finalPublished =
                meritDAO
                        .isFinalPublished();

        MeritRecord provisionalMerit =
                meritDAO
                        .getCurrentStudentMerit();

        MeritRecord finalMerit =
                meritDAO
                        .getCurrentStudentFinalMerit();

        boolean studentHasProvisionalMerit =
                provisionalMerit != null;

        boolean studentHasFinalMerit =
                finalMerit != null;

        // =====================================================
        // PREFERENCE DATA
        // =====================================================

        boolean preferencesLocked =
                preferenceDAO
                        .isPreferenceLocked();

        boolean preferencesStarted =
                !PreferenceFillingPage
                        .getPreferences()
                        .isEmpty()
                        ||
                        preferencesLocked;

        // =====================================================
        // CAP DATA
        // =====================================================

        CAPAllotment round1 =
                capDAO
                        .getStudentAllotment(
                                1
                        );

        CAPAllotment round2 =
                capDAO
                        .getStudentAllotment(
                                2
                        );

        CAPAllotment round3 =
                capDAO
                        .getStudentAllotment(
                                3
                        );

        // =====================================================
        // CAP STATUS
        // =====================================================

        String capStatus =
                "Not Started";

        String capDescription =
                "CAP rounds have not been published yet.";

        if (round1 != null) {

            capStatus =
                    "Round 1 Published";

            capDescription =
                    buildRoundDescription(
                            round1
                    );
        }

        if (round2 != null) {

            capStatus =
                    "Round 2 Published";

            capDescription =
                    buildRoundDescription(
                            round2
                    );
        }

        if (round3 != null) {

            capStatus =
                    "Final Allotment";

            capDescription =
                    buildRoundDescription(
                            round3
                    );
        }

        // =====================================================
        // ADMISSION STATUS
        // =====================================================

        boolean admissionAccepted =
                round3 != null
                        &&
                        "Admission Accepted"
                                .equalsIgnoreCase(
                                        round3
                                                .getDecision()
                                );

        if (admissionAccepted) {

            capStatus =
                    "Admission Accepted";

            capDescription =
                    safe(
                            round3.getCollege()
                    )
                            + " • "
                            +
                            safe(
                                    round3.getBranch()
                            );
        }

        // =====================================================
        // WELCOME
        // =====================================================

        Label welcome =
                new Label(
                        "Welcome back, "
                                + studentName
                );

        welcome.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label description =
                new Label(
                        "Track your MHT CET CAP counselling progress from one place."
                );

        description.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        welcome,
                        description
                );

        // =====================================================
        // PROFILE CARD
        // =====================================================

        int profilePercentage =
                calculateProfilePercentage(
                        student
                );

        Label profileTitle =
                new Label(
                        "PROFILE COMPLETION"
                );

        profileTitle.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        Label profileValue =
                new Label(
                        profilePercentage
                                + "%"
                );

        profileValue.setStyle(
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + LIME + ";"
        );

        Region profileBackground =
                new Region();

        profileBackground
                .setPrefHeight(
                        7
                );

        profileBackground
                .setStyle(
                        "-fx-background-color:#293329;" +
                        "-fx-background-radius:10px;"
                );

        Region profileProgress =
                new Region();

        profileProgress
                .setPrefHeight(
                        7
                );

        profileProgress
                .setPrefWidth(
                        profilePercentage
                                * 3
                );

        profileProgress
                .setMaxWidth(
                        profilePercentage
                                * 3
                );

        profileProgress
                .setStyle(
                        "-fx-background-color:"
                                + LIME + ";" +
                        "-fx-background-radius:10px;"
                );

        StackPaneWrapper progressWrapper =
                new StackPaneWrapper(
                        profileBackground,
                        profileProgress
                );

        Label profileHint =
                new Label(
                        profilePercentage >= 100
                                ?
                                "Your basic profile is complete."
                                :
                                "Complete your application details to continue."
                );

        profileHint.setStyle(
                "-fx-font-size:11px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox profileCard =
                createCard(
                        profileTitle,
                        profileValue,
                        progressWrapper,
                        profileHint
                );

        // =====================================================
        // NEXT STEP CARD
        // =====================================================

        Label nextTitle =
                new Label(
                        "NEXT STEP"
                );

        nextTitle.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + LIME + ";"
        );

        Label nextHeading =
                new Label();

        nextHeading.setStyle(
                "-fx-font-size:19px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label nextDescription =
                new Label();

        nextDescription
                .setWrapText(
                        true
                );

        nextDescription
                .setStyle(
                        "-fx-font-size:12px;" +
                        "-fx-text-fill:" + MUTED + ";"
                );

        Button continueButton =
                new Button();

        continueButton
                .setPrefHeight(
                        40
                );

        continueButton
                .setStyle(
                        "-fx-background-color:"
                                + LIME + ";" +
                        "-fx-text-fill:#0B100B;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-background-radius:8px;" +
                        "-fx-padding:0 18 0 18;" +
                        "-fx-cursor:hand;"
                );

        // =====================================================
        // NEXT STEP LOGIC
        // =====================================================

        if (admissionAccepted) {

            nextHeading.setText(
                    "Admission Confirmed"
            );

            nextDescription.setText(
                    "Your final CAP seat has been accepted. "
                            +
                            "View your admission confirmation."
            );

            continueButton.setText(
                    "View Admission  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            AdmissionConfirmationPage
                                    .getScene()
                    )
            );

        } else if (round3 != null) {

            nextHeading.setText(
                    "CAP Round 3 Final Allotment"
            );

            nextDescription.setText(
                    "Your final seat has been published. "
                            +
                            "Review the allotment and accept your admission."
            );

            continueButton.setText(
                    "View Round 3  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            CAPRound3Page
                                    .getScene()
                    )
            );

        } else if (
                round2 != null
                        &&
                        "Betterment Requested"
                                .equalsIgnoreCase(
                                        round2.getDecision()
                                )
        ) {

            nextHeading.setText(
                    "Waiting for CAP Round 3"
            );

            nextDescription.setText(
                    "You requested Betterment in Round 2. "
                            +
                            "Wait for the counsellor to publish Round 3."
            );

            continueButton.setText(
                    "View Round 2"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            CAPRound2Page
                                    .getScene()
                    )
            );

        } else if (round2 != null) {

            nextHeading.setText(
                    "CAP Round 2 Result"
            );

            nextDescription.setText(
                    "Your Round 2 result is available. "
                            +
                            "Review your seat and select your decision."
            );

            continueButton.setText(
                    "View Round 2  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            CAPRound2Page
                                    .getScene()
                    )
            );

        } else if (
                round1 != null
                        &&
                        "Betterment Requested"
                                .equalsIgnoreCase(
                                        round1.getDecision()
                                )
        ) {

            nextHeading.setText(
                    "Waiting for CAP Round 2"
            );

            nextDescription.setText(
                    "You requested Betterment in Round 1. "
                            +
                            "Wait for the counsellor to publish Round 2."
            );

            continueButton.setText(
                    "View Round 1"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            CAPRound1Page
                                    .getScene()
                    )
            );

        } else if (round1 != null) {

            nextHeading.setText(
                    "CAP Round 1 Result"
            );

            nextDescription.setText(
                    "Your Round 1 allotment is available. "
                            +
                            "Review the result and select your decision."
            );

            continueButton.setText(
                    "View Round 1  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            CAPRound1Page
                                    .getScene()
                    )
            );

        } else if (preferencesLocked) {

            nextHeading.setText(
                    "Preferences Locked"
            );

            nextDescription.setText(
                    "Your option form is locked successfully. "
                            +
                            "Wait for the counsellor to publish CAP Round 1."
            );

            continueButton.setText(
                    "View Preferences  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            PreferenceFillingPage
                                    .getScene()
                    )
            );

        } else if (studentHasFinalMerit) {

            nextHeading.setText(
                    "Final Merit Published"
            );

            nextDescription.setText(
                    "Your final merit rank is "
                            +
                            finalMerit
                                    .getFinalMeritNumber()
                            +
                            ". Continue with college search and preference filling."
            );

            if (preferencesStarted) {

                continueButton.setText(
                        "Continue Preference Filling  →"
                );

                continueButton.setOnAction(e ->

                        Navigation.goTo(
                                PreferenceFillingPage
                                        .getScene()
                        )
                );

            } else {

                continueButton.setText(
                        "Search Colleges  →"
                );

                continueButton.setOnAction(e ->

                        Navigation.goTo(
                                CollegeSearchPage
                                        .getScene()
                        )
                );
            }

        } else if (finalPublished) {

            nextHeading.setText(
                    "Check Final Merit"
            );

            nextDescription.setText(
                    "The Final Merit List has been published. "
                            +
                            "Check your final merit status."
            );

            continueButton.setText(
                    "View Final Merit  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            FinalMeritPage
                                    .getScene()
                    )
            );

        } else if (studentHasProvisionalMerit) {

            nextHeading.setText(
                    "Provisional Merit Published"
            );

            nextDescription.setText(
                    "Your provisional merit rank is "
                            +
                            provisionalMerit
                                    .getProvisionalMeritNumber()
                            +
                            ". Review your merit details and raise a grievance if required."
            );

            continueButton.setText(
                    "View Provisional Merit  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            ProvisionalMeritPage
                                    .getScene()
                    )
            );

        } else if (provisionalPublished) {

            nextHeading.setText(
                    "Check Provisional Merit"
            );

            nextDescription.setText(
                    "The Provisional Merit List has been published. "
                            +
                            "Check your provisional merit status."
            );

            continueButton.setText(
                    "View Provisional Merit  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            ProvisionalMeritPage
                                    .getScene()
                    )
            );

        } else if (applicationRejected) {

            nextHeading.setText(
                    "Application Rejected"
            );

            nextDescription.setText(
                    "Your application was rejected during verification. "
                            +
                            "Check your application status and counsellor remarks."
            );

            continueButton.setText(
                    "View Application Status  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            ApplicationStatusPage
                                    .getScene()
                    )
            );

        } else if (
                applicationSubmitted
                        &&
                        !applicationVerified
        ) {

            nextHeading.setText(
                    "Verification Pending"
            );

            nextDescription.setText(
                    "Your application has been submitted successfully. "
                            +
                            "Wait for counsellor verification."
            );

            continueButton.setText(
                    "View Application Status  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            ApplicationStatusPage
                                    .getScene()
                    )
            );

        } else if (applicationVerified) {

            nextHeading.setText(
                    "Waiting for Provisional Merit"
            );

            nextDescription.setText(
                    "Your application is verified. "
                            +
                            "Wait for the counsellor to publish "
                            +
                            "the Provisional Merit List."
            );

            continueButton.setText(
                    "View Merit Status  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            ProvisionalMeritPage
                                    .getScene()
                    )
            );

        } else {

            nextHeading.setText(
                    "Complete Your Application"
            );

            nextDescription.setText(
                    "Complete your application details and submit "
                            +
                            "the application for counsellor verification."
            );

            continueButton.setText(
                    "Continue Application  →"
            );

            continueButton.setOnAction(e ->

                    Navigation.goTo(
                            PersonalDetailsPage
                                    .getScene()
                    )
            );
        }

        VBox nextCard =
                new VBox(
                        10,
                        nextTitle,
                        nextHeading,
                        nextDescription,
                        continueButton
                );

        nextCard.setPadding(
                new Insets(20)
        );

        nextCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );

        // =====================================================
        // STATUS GRID
        // =====================================================

        GridPane statusGrid =
                new GridPane();

        statusGrid.setHgap(
                15
        );

        statusGrid.setVgap(
                15
        );

        // =====================================================
        // APPLICATION STATUS CARD
        // =====================================================

        String applicationCardStatus;

        String applicationDescription;

        if (applicationRejected) {

            applicationCardStatus =
                    "Rejected";

            applicationDescription =
                    "Application rejected by counsellor";

        } else if (applicationVerified) {

            applicationCardStatus =
                    "Verified";

            applicationDescription =
                    "Application verified successfully";

        } else if (applicationSubmitted) {

            applicationCardStatus =
                    "Pending Verification";

            applicationDescription =
                    "Submitted and waiting for counsellor verification";

        } else {

            applicationCardStatus =
                    "Draft";

            applicationDescription =
                    "Complete and submit your application";
        }

        statusGrid.add(
                createStatusCard(
                        "APPLICATION",
                        applicationCardStatus,
                        applicationDescription,
                        "✎"
                ),
                0,
                0
        );

        // =====================================================
        // DOCUMENT STATUS
        // =====================================================

        String documentStatus;

        String documentDescription;

        if (applicationVerified) {

            documentStatus =
                    "Verified";

            documentDescription =
                    "Documents verified by counsellor";

        } else if (applicationSubmitted) {

            documentStatus =
                    "Under Review";

            documentDescription =
                    "Documents are under counsellor verification";

        } else {

            documentStatus =
                    "Pending";

            documentDescription =
                    "Submit application for document verification";
        }

        statusGrid.add(
                createStatusCard(
                        "DOCUMENTS",
                        documentStatus,
                        documentDescription,
                        "▣"
                ),
                1,
                0
        );

        // =====================================================
        // MERIT STATUS
        // =====================================================

        String meritStatus;

        String meritDescription;

        if (studentHasFinalMerit) {

            meritStatus =
                    "Final Rank "
                            +
                            finalMerit
                                    .getFinalMeritNumber();

            meritDescription =
                    safe(
                            finalMerit
                                    .getCategory()
                    )
                            +
                            " Category • Rank "
                            +
                            finalMerit
                                    .getFinalCategoryRank();

        } else if (finalPublished) {

            meritStatus =
                    "Final Merit Published";

            meritDescription =
                    "Check your final merit result";

        } else if (studentHasProvisionalMerit) {

            meritStatus =
                    "Provisional Rank "
                            +
                            provisionalMerit
                                    .getProvisionalMeritNumber();

            meritDescription =
                    safe(
                            provisionalMerit
                                    .getCategory()
                    )
                            +
                            " Category • Rank "
                            +
                            provisionalMerit
                                    .getCategoryRank();

        } else if (provisionalPublished) {

            meritStatus =
                    "Provisional Published";

            meritDescription =
                    "Check your provisional merit result";

        } else if (applicationVerified) {

            meritStatus =
                    "Waiting";

            meritDescription =
                    "Waiting for provisional merit publication";

        } else {

            meritStatus =
                    "Not Available";

            meritDescription =
                    "Application verification required";
        }

        statusGrid.add(
                createStatusCard(
                        "MERIT STATUS",
                        meritStatus,
                        meritDescription,
                        "★"
                ),
                0,
                1
        );

        // =====================================================
        // CAP STATUS
        // =====================================================

        statusGrid.add(
                createStatusCard(
                        "CAP ROUND",
                        capStatus,
                        capDescription,
                        "◉"
                ),
                1,
                1
        );

        // =====================================================
        // PREFERENCE STATUS CARD
        // =====================================================

        String preferenceStatus;

        String preferenceDescription;

        if (preferencesLocked) {

            preferenceStatus =
                    "Locked";

            preferenceDescription =
                    "Option form successfully locked";

        } else if (preferencesStarted) {

            preferenceStatus =
                    "In Progress";

            preferenceDescription =
                    "Continue filling and lock your preferences";

        } else if (studentHasFinalMerit) {

            preferenceStatus =
                    "Available";

            preferenceDescription =
                    "College search and option form are available";

        } else {

            preferenceStatus =
                    "Not Available";

            preferenceDescription =
                    "Final Merit publication required";
        }

        statusGrid.add(
                createStatusCard(
                        "PREFERENCES",
                        preferenceStatus,
                        preferenceDescription,
                        "☷"
                ),
                0,
                2
        );

        // =====================================================
        // ADMISSION CARD
        // =====================================================

        String admissionStatus;

        String admissionDescription;

        if (admissionAccepted) {

            admissionStatus =
                    "Confirmed";

            admissionDescription =
                    safe(
                            round3.getCollege()
                    )
                            +
                            " • "
                            +
                            safe(
                                    round3.getBranch()
                            );

        } else if (round3 != null) {

            admissionStatus =
                    "Action Required";

            admissionDescription =
                    "Review Round 3 and accept your final seat";

        } else {

            admissionStatus =
                    "Pending";

            admissionDescription =
                    "Complete CAP rounds to confirm admission";
        }

        statusGrid.add(
                createStatusCard(
                        "ADMISSION",
                        admissionStatus,
                        admissionDescription,
                        "✓"
                ),
                1,
                2
        );

        // =====================================================
        // COUNSELLING PROGRESS
        // =====================================================

        Label progressTitle =
                new Label(
                        "CAP COUNSELLING PROGRESS"
                );

        progressTitle.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        boolean registrationComplete =
                studentEmail != null
                        &&
                        !studentEmail
                                .isBlank();

        boolean applicationComplete =
                applicationVerified;

        boolean meritComplete =
                studentHasFinalMerit;

        boolean preferenceComplete =
                preferencesLocked;

        boolean allotmentComplete =
                round3 != null;

        boolean finalComplete =
                admissionAccepted;

        HBox step1 =
                createStep(
                        "01",
                        "Registration",
                        registrationComplete
                );

        HBox step2 =
                createStep(
                        "02",
                        "Application",
                        applicationComplete
                );

        HBox step3 =
                createStep(
                        "03",
                        "Merit List",
                        meritComplete
                );

        HBox step4 =
                createStep(
                        "04",
                        "Preferences",
                        preferenceComplete
                );

        HBox step5 =
                createStep(
                        "05",
                        "Seat Allotment",
                        allotmentComplete
                );

        HBox step6 =
                createStep(
                        "06",
                        "Admission",
                        finalComplete
                );

        HBox capProgress =
                new HBox(
                        20,
                        step1,
                        step2,
                        step3,
                        step4,
                        step5,
                        step6
                );

        capProgress.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox progressCard =
                new VBox(
                        15,
                        progressTitle,
                        capProgress
                );

        progressCard.setPadding(
                new Insets(20)
        );

        progressCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );

        // =====================================================
        // TOP CARDS
        // =====================================================

        HBox topCards =
                new HBox(
                        15,
                        profileCard,
                        nextCard
                );

        HBox.setHgrow(
                profileCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                nextCard,
                Priority.ALWAYS
        );

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content =
                new VBox(
                        22,
                        heading,
                        topCards,
                        statusGrid,
                        progressCard
                );

        content.setPadding(
                new Insets(5)
        );

        content.setFillWidth(
                true
        );

        // =====================================================
        // WRAPPER
        // =====================================================

        BorderPane wrapper =
                new BorderPane();

        wrapper.setCenter(
                content
        );

        wrapper.setStyle(
                "-fx-background-color:"
                        + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Student Dashboard",
                        wrapper
                )
        );
    }

    // =========================================================
    // PROFILE PERCENTAGE
    // =========================================================

    private static int calculateProfilePercentage(
            Student student
    ) {

        int completed =
                0;

        int total =
                5;

        if (
                student.getUsername() != null
                        &&
                        !student.getUsername()
                                .isBlank()
        ) {

            completed++;
        }

        if (
                student.getEmail() != null
                        &&
                        !student.getEmail()
                                .isBlank()
        ) {

            completed++;
        }

        if (
                student.getMobileno() != null
                        &&
                        !student.getMobileno()
                                .isBlank()
        ) {

            completed++;
        }

        if (
                student.getGender() != null
                        &&
                        !student.getGender()
                                .isBlank()
        ) {

            completed++;
        }

        if (
                student.getNationality() != null
                        &&
                        !student.getNationality()
                                .isBlank()
        ) {

            completed++;
        }

        return (
                completed
                        * 100
        ) / total;
    }

    // =========================================================
    // ROUND DESCRIPTION
    // =========================================================

    private static String buildRoundDescription(
            CAPAllotment allotment
    ) {

        if (allotment == null) {

            return "Result not available";
        }

        String decision =
                allotment
                        .getDecision();

        if (
                decision != null
                        &&
                        !decision.isBlank()
                        &&
                        !"Pending"
                                .equalsIgnoreCase(
                                        decision
                                )
        ) {

            return decision
                    +
                    " • "
                    +
                    safe(
                            allotment
                                    .getCollege()
                    );
        }

        return safe(
                allotment
                        .getCollege()
        )
                +
                " • "
                +
                safe(
                        allotment
                                .getBranch()
                );
    }

    // =========================================================
    // SAFE VALUE
    // =========================================================

    private static String safe(
            String value
    ) {

        if (
                value == null
                        ||
                        value.isBlank()
        ) {

            return "Not Available";
        }

        return value;
    }

    // =========================================================
    // CREATE CARD
    // =========================================================

    private static VBox createCard(
            javafx.scene.Node... nodes
    ) {

        VBox card =
                new VBox(
                        8,
                        nodes
                );

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );

        return card;
    }

    // =========================================================
    // STATUS CARD
    // =========================================================

    private static VBox createStatusCard(
            String title,
            String value,
            String description,
            String icon
    ) {

        Label iconLabel =
                new Label(
                        icon
                );

        iconLabel.setStyle(
                "-fx-text-fill:"
                        + LIME + ";" +
                "-fx-font-size:18px;"
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-text-fill:"
                        + MUTED + ";" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );

        HBox header =
                new HBox(
                        10,
                        iconLabel,
                        titleLabel
                );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setWrapText(
                true
        );

        valueLabel.setStyle(
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setStyle(
                "-fx-text-fill:"
                        + MUTED + ";" +
                "-fx-font-size:11px;"
        );

        VBox card =
                new VBox(
                        12,
                        header,
                        valueLabel,
                        descriptionLabel
                );

        card.setPadding(
                new Insets(18)
        );

        card.setPrefWidth(
                260
        );

        card.setMinHeight(
                135
        );

        card.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;" +
                "-fx-background-radius:12px;"
        );

        return card;
    }

    // =========================================================
    // PROGRESS STEP
    // =========================================================

    private static HBox createStep(
            String number,
            String text,
            boolean completed
    ) {

        Label numberLabel =
                new Label(
                        completed
                                ?
                                "✓"
                                :
                                number
                );

        numberLabel.setMinSize(
                30,
                30
        );

        numberLabel.setAlignment(
                Pos.CENTER
        );

        numberLabel.setStyle(
                "-fx-background-color:"
                        +
                        (
                                completed
                                        ?
                                        LIME
                                        :
                                        "#202820"
                        )
                        +
                        ";" +
                        "-fx-background-radius:50%;" +
                        "-fx-text-fill:"
                        +
                        (
                                completed
                                        ?
                                        "#0B100B"
                                        :
                                        MUTED
                        )
                        +
                        ";" +
                        "-fx-font-size:10px;" +
                        "-fx-font-weight:bold;"
        );

        Label textLabel =
                new Label(
                        text
                );

        textLabel.setStyle(
                "-fx-text-fill:"
                        +
                        (
                                completed
                                        ?
                                        WHITE
                                        :
                                        MUTED
                        )
                        +
                        ";" +
                        "-fx-font-size:11px;" +
                        "-fx-font-weight:bold;"
        );

        HBox step =
                new HBox(
                        8,
                        numberLabel,
                        textLabel
                );

        step.setAlignment(
                Pos.CENTER_LEFT
        );

        return step;
    }

    // =========================================================
    // PROGRESS BAR WRAPPER
    // =========================================================

    private static class StackPaneWrapper
            extends javafx.scene.layout.StackPane {

        public StackPaneWrapper(
                Region background,
                Region progress
        ) {

            getChildren()
                    .addAll(
                            background,
                            progress
                    );

            setAlignment(
                    progress,
                    Pos.CENTER_LEFT
            );

            setMaxWidth(
                    Double.MAX_VALUE
            );
        }
    }
}