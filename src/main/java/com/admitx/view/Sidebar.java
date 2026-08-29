package com.admitx.view;

import com.admitx.model.Student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Sidebar {

    private static final String BG =
            "#0A0F0A";

    private static final String LIME =
            "#B7FF00";

    private static final String TEXT =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    private static final String HOVER =
            "#1B2817";

    public static VBox create(
            String activePage
    ) {

        // =====================================================
        // BRANDING
        // =====================================================

        Label logo =
                new Label(
                        "ADMITX"
                );

        logo.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:25px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "AI STUDENT PORTAL"
                );

        subtitle.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;"
        );

        VBox branding =
                new VBox(
                        2,
                        logo,
                        subtitle
                );

        branding.setAlignment(
                Pos.CENTER_LEFT
        );

        branding.setPadding(
                new Insets(
                        5,
                        8,
                        25,
                        8
                )
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        Button dashboard =
                createButton(
                        "⌂",
                        "Dashboard",
                        "Student Dashboard",
                        activePage
                );

        Button application =
                createButton(
                        "▣",
                        "Application",
                        "Personal Details",
                        activePage
                );

        Button merit =
                createButton(
                        "★",
                        "Merit List",
                        "Provisional Merit List",
                        activePage
                );

        Button college =
                createButton(
                        "⌕",
                        "College Search",
                        "College Search",
                        activePage
                );

        Button preference =
                createButton(
                        "☷",
                        "Preference Filling",
                        "Preference Filling",
                        activePage
                );

        Button cap1 =
                createButton(
                        "◉",
                        "CAP Round 1",
                        "CAP Round 1",
                        activePage
                );

        Button cap2 =
                createButton(
                        "◉",
                        "CAP Round 2",
                        "CAP Round 2",
                        activePage
                );

        Button cap3 =
                createButton(
                        "◉",
                        "CAP Round 3",
                        "CAP Round 3",
                        activePage
                );

        Button admission =
                createButton(
                        "✓",
                        "Admission",
                        "Admission Confirmation",
                        activePage
                );

        Button notices =
                createButton(
                        "●",
                        "Notices",
                        "Notice Board",
                        activePage
                );

        Button profile =
                createButton(
                        "●",
                        "Profile",
                        "Student Profile",
                        activePage
                );

        Button help =
                createButton(
                        "?",
                        "Help",
                        "Help Centre",
                        activePage
                );

        Button logout =
                createButton(
                        "⇥",
                        "Logout",
                        "Logout",
                        activePage
                );

        // =====================================================
        // NAVIGATION
        // =====================================================

        dashboard.setOnAction(e ->

                Navigation.goTo(
                        StudentDashboardPage
                                .getScene()
                )
        );

        application.setOnAction(e ->

                Navigation.goTo(
                        PersonalDetailsPage
                                .getScene()
                )
        );

        merit.setOnAction(e ->

                Navigation.goTo(
                        ProvisionalMeritPage
                                .getScene()
                )
        );

        college.setOnAction(e ->

                Navigation.goTo(
                        CollegeSearchPage
                                .getScene()
                )
        );

        preference.setOnAction(e ->

                Navigation.goTo(
                        PreferenceFillingPage
                                .getScene()
                )
        );

        cap1.setOnAction(e ->

                Navigation.goTo(
                        CAPRound1Page
                                .getScene()
                )
        );

        cap2.setOnAction(e ->

                Navigation.goTo(
                        CAPRound2Page
                                .getScene()
                )
        );

        cap3.setOnAction(e ->

                Navigation.goTo(
                        CAPRound3Page
                                .getScene()
                )
        );

        admission.setOnAction(e ->

                Navigation.goTo(
                        AdmissionConfirmationPage
                                .getScene()
                )
        );

        notices.setOnAction(e ->

                Navigation.goTo(
                        NoticeBoardPage
                                .getScene()
                )
        );

        profile.setOnAction(e ->

                Navigation.goTo(
                        StudentProfilePage
                                .getScene()
                )
        );

        help.setOnAction(e ->

                Navigation.goTo(
                        HelpCentrePage
                                .getScene()
                )
        );

        // =====================================================
        // LOGOUT
        // =====================================================

        logout.setOnAction(e -> {

            clearStudentSession();

            Navigation.goTo(
                    WelcomePage
                            .getScene()
            );
        });

        // =====================================================
        // MENU
        // =====================================================

        VBox menu =
                new VBox(
                        5,
                        dashboard,
                        application,
                        merit,
                        college,
                        preference,
                        cap1,
                        cap2,
                        cap3,
                        admission,
                        notices,
                        profile,
                        help
                );

        menu.setFillWidth(
                true
        );

        // =====================================================
        // ACCOUNT SECTION
        // =====================================================

        Label accountLabel =
                new Label(
                        "ACCOUNT"
                );

        accountLabel.setStyle(
                "-fx-text-fill:#596359;" +
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 8 8 8;"
        );

        VBox bottom =
                new VBox(
                        8,
                        accountLabel,
                        logout
                );

        bottom.setFillWidth(
                true
        );

        // =====================================================
        // SCROLLABLE MENU
        // =====================================================

        ScrollPane menuScroll =
                new ScrollPane(
                        menu
                );

        menuScroll.setFitToWidth(
                true
        );

        menuScroll.setHbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .NEVER
        );

        menuScroll.setVbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .AS_NEEDED
        );

        menuScroll.setStyle(
                "-fx-background:transparent;" +
                "-fx-background-color:transparent;" +
                "-fx-border-color:transparent;"
        );

        VBox.setVgrow(
                menuScroll,
                Priority.ALWAYS
        );

        // =====================================================
        // SIDEBAR
        // =====================================================

        VBox sidebar =
                new VBox(
                        branding,
                        menuScroll,
                        bottom
                );

        sidebar.setPadding(
                new Insets(
                        22,
                        14,
                        18,
                        14
                )
        );

        sidebar.setPrefWidth(
                245
        );

        sidebar.setMinWidth(
                245
        );

        sidebar.setAlignment(
                Pos.TOP_CENTER
        );

        sidebar.setStyle(
                "-fx-background-color:" + BG + ";" +
                "-fx-border-color:#202820;" +
                "-fx-border-width:0 1 0 0;"
        );

        return sidebar;
    }

    // =========================================================
    // CLEAR STUDENT SESSION
    // =========================================================

    private static void clearStudentSession() {

        /*
         * PreferenceFillingPage stores preferences in a static
         * ObservableList.
         *
         * Clearing it prevents Student A's unsaved preferences
         * appearing when Student B logs in.
         */

        PreferenceFillingPage
                .getPreferences()
                .clear();

        Student student =
                Student.getInstance();

        /*
         * These are the most important session values.
         *
         * Your DAOs use the student's email to find that
         * student's Firestore documents.
         */

        student.setEmail(
                null
        );

        student.setUsername(
                null
        );

        student.setMobileno(
                null
        );
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private static Button createButton(
            String icon,
            String text,
            String page,
            String activePage
    ) {

        Label iconLabel =
                new Label(
                        icon
                );

        iconLabel.setMinWidth(
                25
        );

        iconLabel.setAlignment(
                Pos.CENTER
        );

        Label textLabel =
                new Label(
                        text
                );

        HBox content =
                new HBox(
                        12,
                        iconLabel,
                        textLabel
                );

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        Button button =
                new Button();

        button.setGraphic(
                content
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                46
        );

        button.setMinHeight(
                46
        );

        boolean active =
                isActive(
                        page,
                        activePage
                );

        if (active) {

            applyActiveStyle(
                    button,
                    iconLabel,
                    textLabel
            );

        } else {

            applyNormalStyle(
                    button,
                    iconLabel,
                    textLabel
            );

            button.setOnMouseEntered(e ->

                    applyHoverStyle(
                            button,
                            iconLabel,
                            textLabel
                    )
            );

            button.setOnMouseExited(e ->

                    applyNormalStyle(
                            button,
                            iconLabel,
                            textLabel
                    )
            );
        }

        return button;
    }

    // =========================================================
    // ACTIVE PAGE
    // =========================================================

    private static boolean isActive(
            String page,
            String activePage
    ) {

        if (activePage == null) {

            return false;
        }

        // Direct page match

        if (
                page.equals(
                        activePage
                )
        ) {

            return true;
        }

        // =====================================================
        // APPLICATION SECTION
        // =====================================================

        if (
                page.equals(
                        "Personal Details"
                )
        ) {

            return activePage.equals(
                    "Address Details"
            )
                    ||
                    activePage.equals(
                            "Academic Details"
                    )
                    ||
                    activePage.equals(
                            "Home University & Eligibility"
                    )
                    ||
                    activePage.equals(
                            "Reservation Details"
                    )
                    ||
                    activePage.equals(
                            "Document Upload"
                    )
                    ||
                    activePage.equals(
                            "Preview Application"
                    )
                    ||
                    activePage.equals(
                            "Application Status"
                    );
        }

        // =====================================================
        // MERIT SECTION
        // =====================================================

        if (
                page.equals(
                        "Provisional Merit List"
                )
        ) {

            return activePage.equals(
                    "Grievance"
            )
                    ||
                    activePage.equals(
                            "Raise Grievance"
                    )
                    ||
                    activePage.equals(
                            "Final Merit List"
                    );
        }

        // =====================================================
        // COLLEGE SECTION
        // =====================================================

        if (
                page.equals(
                        "College Search"
                )
        ) {

            return activePage.equals(
                    "College Information"
            );
        }

        // =====================================================
        // PREFERENCE SECTION
        // =====================================================

        if (
                page.equals(
                        "Preference Filling"
                )
        ) {

            return activePage.equals(
                    "Option Form Preview"
            )
                    ||
                    activePage.equals(
                            "Option Form Confirmation"
                    );
        }

        // =====================================================
        // CAP ROUND 1
        // =====================================================

        if (
                page.equals(
                        "CAP Round 1"
                )
        ) {

            return activePage.equals(
                    "CAP Round 1 Confirmation"
            );
        }

        // =====================================================
        // CAP ROUND 2
        // =====================================================

        if (
                page.equals(
                        "CAP Round 2"
                )
        ) {

            return activePage.equals(
                    "CAP Round 2 Confirmation"
            );
        }

        // =====================================================
        // CAP ROUND 3
        // =====================================================

        if (
                page.equals(
                        "CAP Round 3"
                )
        ) {

            return activePage.equals(
                    "CAP Round 3 Confirmation"
            );
        }

        // =====================================================
        // ADMISSION
        // =====================================================

        if (
                page.equals(
                        "Admission Confirmation"
                )
        ) {

            return activePage.equals(
                    "Admission"
            )
                    ||
                    activePage.equals(
                            "Admission Confirmation"
                    );
        }

        return false;
    }

    // =========================================================
    // ACTIVE STYLE
    // =========================================================

    private static void applyActiveStyle(
            Button button,
            Label icon,
            Label text
    ) {

        icon.setStyle(
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;"
        );

        text.setStyle(
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        button.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-background-radius:9px;" +
                "-fx-padding:0 12 0 12;" +
                "-fx-alignment:CENTER_LEFT;" +
                "-fx-cursor:hand;"
        );
    }

    // =========================================================
    // NORMAL STYLE
    // =========================================================

    private static void applyNormalStyle(
            Button button,
            Label icon,
            Label text
    ) {

        icon.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:16px;"
        );

        text.setStyle(
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        button.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:9px;" +
                "-fx-padding:0 12 0 12;" +
                "-fx-alignment:CENTER_LEFT;" +
                "-fx-cursor:hand;"
        );
    }

    // =========================================================
    // HOVER STYLE
    // =========================================================

    private static void applyHoverStyle(
            Button button,
            Label icon,
            Label text
    ) {

        icon.setStyle(
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:16px;"
        );

        text.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        button.setStyle(
                "-fx-background-color:" + HOVER + ";" +
                "-fx-background-radius:9px;" +
                "-fx-padding:0 12 0 12;" +
                "-fx-alignment:CENTER_LEFT;" +
                "-fx-cursor:hand;"
        );
    }
}