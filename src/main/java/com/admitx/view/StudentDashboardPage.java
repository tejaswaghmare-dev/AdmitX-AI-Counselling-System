package com.admitx.view;

import com.admitx.config.FirebaseConfig;
import com.admitx.dao.MeritDAO;
import com.admitx.dao.MeritDAO.MeritRecord;

import com.admitx.model.CAPAllotment;
import com.admitx.model.Student;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
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

        Student student = Student.getInstance();

        BorderPane dashboardHost = new BorderPane();
        dashboardHost.setStyle("-fx-background-color:" + BG + ";");

        Label loadingLabel = new Label("Loading dashboard...");
        loadingLabel.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:14px;"
        );

        VBox loadingBox = new VBox(12, loadingLabel);
        loadingBox.setAlignment(Pos.CENTER);
        loadingBox.setPadding(new Insets(40));
        dashboardHost.setCenter(loadingBox);

        Scene scene = new Scene(
                StudentLayout.create(
                        "Student Dashboard",
                        dashboardHost
                )
        );

        com.admitx.util.AsyncTaskRunner.run(
                StudentDashboardPage::loadDashboardData,
                data -> {
                    VBox content = buildDashboardContent(student, data);

                    ScrollPane scrollPane = new ScrollPane(content);
                    scrollPane.setFitToWidth(true);
                    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    scrollPane.setPannable(true);
                    scrollPane.setStyle(
                            "-fx-background:" + BG + ";" +
                            "-fx-background-color:" + BG + ";" +
                            "-fx-border-color:transparent;"
                    );

                    dashboardHost.setCenter(scrollPane);
                },
                error -> {
                    error.printStackTrace();

                    Label errorLabel = new Label(
                            "Unable to load dashboard data. Please try again."
                    );
                    errorLabel.setWrapText(true);
                    errorLabel.setStyle(
                            "-fx-text-fill:#F87171;" +
                            "-fx-font-size:14px;"
                    );

                    Button retryButton = new Button("Retry");
                    retryButton.setStyle(
                            "-fx-background-color:" + LIME + ";" +
                            "-fx-text-fill:#0B100B;" +
                            "-fx-font-weight:bold;" +
                            "-fx-background-radius:8px;" +
                            "-fx-padding:10 18 10 18;" +
                            "-fx-cursor:hand;"
                    );
                    retryButton.setOnAction(e ->
                            Navigation.goTo(getScene())
                    );

                    VBox errorBox = new VBox(
                            14,
                            errorLabel,
                            retryButton
                    );
                    errorBox.setAlignment(Pos.CENTER);
                    errorBox.setPadding(new Insets(40));
                    dashboardHost.setCenter(errorBox);
                }
        );

        return scene;
    }

    private static DashboardData loadDashboardData() throws Exception {

        DashboardData data = new DashboardData();

        String email = Student.getInstance().getEmail();

        if (email == null || email.isBlank()) {
            data.verificationStatus = "Not Submitted";
            return data;
        }

        Firestore db = FirebaseConfig.getFirestore();

        // Start all independent Firestore reads together.
        ApiFuture<DocumentSnapshot> applicationFuture =
                db.collection("Applications")
                        .document(email)
                        .get();

        ApiFuture<DocumentSnapshot> meritSettingsFuture =
                db.collection("MeritSettings")
                        .document("status")
                        .get();

        ApiFuture<DocumentSnapshot> meritFuture =
                db.collection("MeritList")
                        .document(email)
                        .get();

        ApiFuture<DocumentSnapshot> preferenceFuture =
                db.collection("StudentPreferences")
                        .document(email)
                        .get();

        ApiFuture<DocumentSnapshot> allotmentFuture =
                db.collection("CAPAllotments")
                        .document(email)
                        .get();

        // The requests are already running in parallel while we wait here.
        DocumentSnapshot applicationDocument = applicationFuture.get();
        DocumentSnapshot meritSettingsDocument = meritSettingsFuture.get();
        DocumentSnapshot meritDocument = meritFuture.get();
        DocumentSnapshot preferenceDocument = preferenceFuture.get();
        DocumentSnapshot allotmentDocument = allotmentFuture.get();

        // APPLICATION: one read instead of two.
        if (applicationDocument.exists()) {
            String verification = applicationDocument.getString("verificationStatus");
            data.verificationStatus =
                    verification == null || verification.isBlank()
                            ? "Pending"
                            : verification;

            String status = applicationDocument.getString("status");
            data.applicationSubmitted =
                    "Submitted".equalsIgnoreCase(status);
        } else {
            data.verificationStatus = "Not Submitted";
            data.applicationSubmitted = false;
        }

        // MERIT SETTINGS: one read instead of two.
        if (meritSettingsDocument.exists()) {
            data.provisionalPublished = Boolean.TRUE.equals(
                    meritSettingsDocument.getBoolean("provisionalPublished")
            );

            data.finalPublished = Boolean.TRUE.equals(
                    meritSettingsDocument.getBoolean("finalPublished")
            );
        }

        // MERIT RECORD: one read instead of two.
        if (meritDocument.exists()) {
            MeritRecord merit = toMeritRecord(meritDocument);

            if (merit.isProvisionalPublished()) {
                data.provisionalMerit = merit;
            }

            if (merit.isFinalPublished()) {
                data.finalMerit = merit;
            }
        }

        // PREFERENCES: one read.
        if (preferenceDocument.exists()) {
            data.preferencesLocked = Boolean.TRUE.equals(
                    preferenceDocument.getBoolean("locked")
            );
        }

        // CAP: one document read for all three rounds instead of three reads.
        if (allotmentDocument.exists()) {
            data.round1 = toAllotment(allotmentDocument, email, 1);
            data.round2 = toAllotment(allotmentDocument, email, 2);
            data.round3 = toAllotment(allotmentDocument, email, 3);
        }

        return data;
    }

    private static MeritRecord toMeritRecord(
            DocumentSnapshot document
    ) {

        MeritRecord record = new MeritRecord();

        record.setStudentEmail(document.getString("studentEmail"));
        record.setCandidateName(document.getString("candidateName"));
        record.setCategory(document.getString("category"));
        record.setCetPercentile(document.getString("cetPercentile"));

        Long provisionalMerit = document.getLong("provisionalMeritNumber");
        if (provisionalMerit != null) {
            record.setProvisionalMeritNumber(provisionalMerit.intValue());
        }

        Long categoryRank = document.getLong("categoryRank");
        if (categoryRank != null) {
            record.setCategoryRank(categoryRank.intValue());
        }

        Long finalMerit = document.getLong("finalMeritNumber");
        if (finalMerit != null) {
            record.setFinalMeritNumber(finalMerit.intValue());
        }

        Long finalCategoryRank = document.getLong("finalCategoryRank");
        if (finalCategoryRank != null) {
            record.setFinalCategoryRank(finalCategoryRank.intValue());
        }

        record.setProvisionalPublished(Boolean.TRUE.equals(
                document.getBoolean("provisionalPublished")
        ));

        record.setFinalGenerated(Boolean.TRUE.equals(
                document.getBoolean("finalGenerated")
        ));

        record.setFinalPublished(Boolean.TRUE.equals(
                document.getBoolean("finalPublished")
        ));

        record.setStatus(document.getString("status"));

        return record;
    }

    @SuppressWarnings("unchecked")
    private static CAPAllotment toAllotment(
            DocumentSnapshot document,
            String email,
            int round
    ) {

        Object value = document.get("round" + round);

        if (!(value instanceof Map<?, ?> rawMap)) {
            return null;
        }

        Map<String, Object> roundData =
                (Map<String, Object>) rawMap;

        if (!Boolean.TRUE.equals(roundData.get("published"))) {
            return null;
        }

        CAPAllotment allotment = new CAPAllotment();
        allotment.setStudentEmail(email);
        allotment.setRound(round);
        allotment.setCollege(mapString(roundData, "college"));
        allotment.setBranch(mapString(roundData, "branch"));
        allotment.setPreviousCollege(mapString(roundData, "previousCollege"));
        allotment.setPreviousBranch(mapString(roundData, "previousBranch"));
        allotment.setStatus(mapString(roundData, "status"));
        allotment.setUpgradeStatus(mapString(roundData, "upgradeStatus"));
        allotment.setDecision(mapString(roundData, "decision"));
        allotment.setPublished(true);

        Object preferenceNumber = roundData.get("preferenceNumber");
        if (preferenceNumber instanceof Number number) {
            allotment.setPreferenceNumber(number.intValue());
        }

        return allotment;
    }

    private static String mapString(
            Map<String, Object> map,
            String key
    ) {
        Object value = map.get(key);
        return value == null ? "" : String.valueOf(value);
    }

    private static VBox buildDashboardContent(
            Student student,
            DashboardData data
    ) {

        String studentName = student.getDisplayName();
        String studentEmail = student.getEmail();

        if (studentName == null || studentName.isBlank()) {
            studentName = "Student";
        }

        String verificationStatus = data.verificationStatus;
        boolean applicationSubmitted = data.applicationSubmitted;

        boolean applicationVerified =
                "Verified".equalsIgnoreCase(verificationStatus);

        boolean applicationRejected =
                "Rejected".equalsIgnoreCase(verificationStatus);

        boolean correctionRequired =
                "Correction Required".equalsIgnoreCase(verificationStatus);

        boolean provisionalPublished = data.provisionalPublished;
        boolean finalPublished = data.finalPublished;

        MeritRecord provisionalMerit = data.provisionalMerit;
        MeritRecord finalMerit = data.finalMerit;

        boolean studentHasProvisionalMerit = provisionalMerit != null;
        boolean studentHasFinalMerit = finalMerit != null;

        boolean preferencesLocked = data.preferencesLocked;
        boolean preferencesStarted =
                !PreferenceFillingPage.getPreferences().isEmpty()
                        || preferencesLocked;

        CAPAllotment round1 = data.round1;
        CAPAllotment round2 = data.round2;
        CAPAllotment round3 = data.round3;

        String capStatus = "Not Started";
        String capDescription =
                "CAP rounds have not been published yet.";

        if (round1 != null) {
            capStatus = "Round 1 Published";
            capDescription = buildRoundDescription(round1);
        }

        if (round2 != null) {
            capStatus = "Round 2 Published";
            capDescription = buildRoundDescription(round2);
        }

        if (round3 != null) {
            capStatus = "Final Allotment";
            capDescription = buildRoundDescription(round3);
        }

        boolean admissionAccepted =
                round3 != null
                        && "Admission Accepted".equalsIgnoreCase(
                                round3.getDecision()
                        );

        if (admissionAccepted) {
            capStatus = "Admission Accepted";
            capDescription =
                    safe(round3.getCollege())
                            + " • "
                            + safe(round3.getBranch());
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
                            student,
                            data
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

            profileBackground.setMinHeight(7);
            profileBackground.setPrefHeight(7);
            profileBackground.setMaxHeight(7);

            profileBackground.setStyle(
                    "-fx-background-color:#293329;" +
                    "-fx-background-radius:10px;"
            );

            Region profileProgress =
                    new Region();

            profileProgress.setMinHeight(7);
            profileProgress.setPrefHeight(7);
            profileProgress.setMaxHeight(7);

            profileProgress.setStyle(
                    "-fx-background-color:" + LIME + ";" +
                    "-fx-background-radius:10px;"
            );

            StackPaneWrapper progressWrapper =
                    new StackPaneWrapper(
                            profileBackground,
                            profileProgress
                    );

            progressWrapper.setMinHeight(7);
            progressWrapper.setPrefHeight(7);
            progressWrapper.setMaxHeight(7);
            progressWrapper.setMaxWidth(Double.MAX_VALUE);

            profileBackground.prefWidthProperty()
                    .bind(progressWrapper.widthProperty());

            profileBackground.maxWidthProperty()
                    .bind(progressWrapper.widthProperty());

            profileProgress.prefWidthProperty()
                    .bind(
                            progressWrapper.widthProperty()
                                    .multiply(profilePercentage / 100.0)
                    );

            profileProgress.maxWidthProperty()
                    .bind(profileProgress.prefWidthProperty());

            Label profileHint =
                    new Label(
                            getProfileCompletionMessage(
                                    data,
                                    profilePercentage
                            )
                    );

            profileHint.setWrapText(true);
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

            profileCard.setMinWidth(0);
            profileCard.setMaxWidth(Double.MAX_VALUE);

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

            } else if (correctionRequired) {

                nextHeading.setText(
                        "Document Correction Required"
                );

                nextDescription.setText(
                        "The counsellor requested corrections in one or more documents. "
                                + "Replace the rejected documents and resubmit your application."
                );

                continueButton.setText(
                        "Correct Documents  →"
                );

                continueButton.setOnAction(e ->

                        Navigation.goTo(
                                CorrectionDocumentPage.getScene()
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

            nextCard.setMinWidth(0);
            nextCard.setMaxWidth(Double.MAX_VALUE);

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

            ColumnConstraints statusColumn1 = new ColumnConstraints();
            statusColumn1.setPercentWidth(50);
            statusColumn1.setHgrow(Priority.ALWAYS);

            ColumnConstraints statusColumn2 = new ColumnConstraints();
            statusColumn2.setPercentWidth(50);
            statusColumn2.setHgrow(Priority.ALWAYS);

            statusGrid.getColumnConstraints().addAll(
                    statusColumn1,
                    statusColumn2
            );

            statusGrid.setMaxWidth(Double.MAX_VALUE);

            // =====================================================
            // APPLICATION STATUS CARD
            // =====================================================

            String applicationCardStatus;

            String applicationDescription;

            if (correctionRequired) {

                applicationCardStatus =
                        "Correction Required";

                applicationDescription =
                        "Re-upload rejected documents and resubmit";

            } else if (applicationRejected) {

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

            GridPane capProgress =
                    new GridPane();

            capProgress.setHgap(20);
            capProgress.setVgap(16);

            ColumnConstraints progressColumn1 = new ColumnConstraints();
            progressColumn1.setPercentWidth(33.333);
            progressColumn1.setHgrow(Priority.ALWAYS);

            ColumnConstraints progressColumn2 = new ColumnConstraints();
            progressColumn2.setPercentWidth(33.333);
            progressColumn2.setHgrow(Priority.ALWAYS);

            ColumnConstraints progressColumn3 = new ColumnConstraints();
            progressColumn3.setPercentWidth(33.333);
            progressColumn3.setHgrow(Priority.ALWAYS);

            capProgress.getColumnConstraints().addAll(
                    progressColumn1,
                    progressColumn2,
                    progressColumn3
            );

            capProgress.add(step1, 0, 0);
            capProgress.add(step2, 1, 0);
            capProgress.add(step3, 2, 0);
            capProgress.add(step4, 0, 1);
            capProgress.add(step5, 1, 1);
            capProgress.add(step6, 2, 1);

            VBox progressCard =
                    new VBox(
                            15,
                            progressTitle,
                            capProgress
                    );

            progressCard.setPadding(
                    new Insets(20)
            );

            progressCard.setMaxWidth(Double.MAX_VALUE);

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

            topCards.setFillHeight(true);
            topCards.setMaxWidth(Double.MAX_VALUE);

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
                    new Insets(8, 8, 24, 8)
            );

            content.setFillWidth(
                    true
            );

            content.setMaxWidth(Double.MAX_VALUE);
            content.setStyle("-fx-background-color:" + BG + ";");


        return content;
    }

    private static class DashboardData {
        private String verificationStatus;
        private boolean applicationSubmitted;
        private boolean provisionalPublished;
        private boolean finalPublished;
        private MeritRecord provisionalMerit;
        private MeritRecord finalMerit;
        private boolean preferencesLocked;
        private CAPAllotment round1;
        private CAPAllotment round2;
        private CAPAllotment round3;
    }

    // =========================================================
    // PROFILE PERCENTAGE
    // =========================================================

    private static int calculateProfilePercentage(
            Student student,
            DashboardData data
    ) {

        String verificationStatus = data.verificationStatus;

        boolean applicationVerified =
                "Verified".equalsIgnoreCase(verificationStatus);

        boolean applicationRejected =
                "Rejected".equalsIgnoreCase(verificationStatus);

        boolean correctionRequired =
                "Correction Required".equalsIgnoreCase(verificationStatus);

        boolean admissionAccepted =
                data.round3 != null
                        && "Admission Accepted".equalsIgnoreCase(
                                data.round3.getDecision()
                        );

        // 100% only when the complete counselling process is finished.
        if (admissionAccepted) {
            return 100;
        }

        // After submission/verification keep the application at 90%
        // until final admission is accepted.
        if (data.applicationSubmitted
                || applicationVerified
                || applicationRejected
                || correctionRequired) {
            return 90;
        }

        // Before submission, calculate progress from application fields.
        int completed = 0;
        int total = 24;

        if (hasValue(student.getUsername())) completed++;
        if (hasValue(student.getEmail())) completed++;
        if (hasValue(student.getMobileno())) completed++;
        if (hasValue(student.getCandidateName())) completed++;
        if (hasValue(student.getFatherName())) completed++;
        if (hasValue(student.getMotherName())) completed++;
        if (hasValue(student.getGender())) completed++;
        if (hasValue(student.getDob())) completed++;
        if (hasValue(student.getNationality())) completed++;
        if (hasValue(student.getPermanentAddress())) completed++;
        if (hasValue(student.getCorrespondenceAddress())) completed++;
        if (hasValue(student.getState())) completed++;
        if (hasValue(student.getDistrict())) completed++;
        if (hasValue(student.getTaluka())) completed++;
        if (hasValue(student.getPinCode())) completed++;
        if (hasValue(student.getSscDetails())) completed++;
        if (hasValue(student.getHscDetails())) completed++;
        if (hasValue(student.getPcmMarks())) completed++;
        if (hasValue(student.getCetPercentile())) completed++;
        if (hasValue(student.getHomeUniversity())) completed++;
        if (hasValue(student.getCandidateType())) completed++;
        if (hasValue(student.getDomicileStatus())) completed++;
        if (hasValue(student.getCategory())) completed++;

        if (student.getUploadedDocumentUrls() != null
                && !student.getUploadedDocumentUrls().isEmpty()) {
            completed++;
        }

        int percentage = (completed * 80) / total;

        return Math.min(80, Math.max(0, percentage));
    }

    private static String getProfileCompletionMessage(
            DashboardData data,
            int profilePercentage
    ) {

        String verificationStatus = data.verificationStatus;

        boolean admissionAccepted =
                data.round3 != null
                        && "Admission Accepted".equalsIgnoreCase(
                                data.round3.getDecision()
                        );

        if (admissionAccepted && profilePercentage == 100) {
            return "Counselling process completed successfully. Admission confirmed.";
        }

        if ("Verified".equalsIgnoreCase(verificationStatus)) {
            return "Application verified. Continue the remaining counselling process.";
        }

        if ("Correction Required".equalsIgnoreCase(verificationStatus)) {
            return "Document correction required. Re-upload rejected documents and resubmit.";
        }

        if ("Rejected".equalsIgnoreCase(verificationStatus)) {
            return "Application verification failed. Check counsellor remarks.";
        }

        if (data.applicationSubmitted) {
            return "Application submitted. Verification is pending.";
        }

        if (profilePercentage >= 80) {
            return "Application details are complete. Submit the application for verification.";
        }

        return "Complete your application details to continue.";
    }

    private static boolean hasValue(String value) {
        return value != null && !value.isBlank();
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

        card.setMinWidth(0);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setMinHeight(170);

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

        card.setMinWidth(0);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setMinHeight(
                135
        );

        GridPane.setHgrow(card, Priority.ALWAYS);

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

        step.setMinWidth(0);
        step.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(step, Priority.ALWAYS);

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

            getChildren().addAll(
                    background,
                    progress
            );

            javafx.scene.layout.StackPane.setAlignment(
                    background,
                    Pos.CENTER_LEFT
            );

            javafx.scene.layout.StackPane.setAlignment(
                    progress,
                    Pos.CENTER_LEFT
            );

            setAlignment(Pos.CENTER_LEFT);
            setMaxWidth(Double.MAX_VALUE);
        }
    }
}