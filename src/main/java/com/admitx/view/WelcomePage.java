package com.admitx.view;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class WelcomePage {

    // =========================================================
    // ADMITX COLOR SYSTEM
    // =========================================================

    private static final String BLACK = "#040505";
    private static final String DARK = "#161613";
    private static final String CARD = "#2B2C2B";

    private static final String LIME = "#C6E92F";
    private static final String LIME_2 = "#8AA30B";
    private static final String LIME_3 = "#5E7107";

    private static final String OLIVE = "#3F340D";

    private static final String WHITE = "#FBFBFB";
    private static final String BEIGE = "#D4CBB6";

    private static final String GREY = "#9A9D91";
    private static final String MUTED = "#60645B";
    private static final String BORDER = "#2B2C2B";


    // =========================================================
    // MAIN SCENE
    // =========================================================

    public static Scene getScene() {

        // =====================================================
        // ROOT
        // =====================================================

        StackPane root = new StackPane();


        // =====================================================
        // DARK BACKGROUND
        // =====================================================

        BackgroundFill backgroundFill =
                new BackgroundFill(

                        new LinearGradient(
                                0,
                                0,
                                1,
                                1,
                                true,
                                CycleMethod.NO_CYCLE,

                                new Stop(
                                        0.0,
                                        Color.web(BLACK)
                                ),

                                new Stop(
                                        0.40,
                                        Color.web("#080907")
                                ),

                                new Stop(
                                        0.70,
                                        Color.web(DARK)
                                ),

                                new Stop(
                                        1.0,
                                        Color.web(BLACK)
                                )
                        ),

                        CornerRadii.EMPTY,
                        Insets.EMPTY
                );


        root.setBackground(
                new Background(backgroundFill)
        );


        // =====================================================
        // FULL SCREEN STUDENT IMAGE
        // =====================================================

        ImageView studentImage =
                new ImageView();

        try {

            Image image =
                    new Image(
                            "assets/images/admitxstd.jpeg"
                    );

            studentImage.setImage(image);

            studentImage.setPreserveRatio(true);

            studentImage.setSmooth(true);

            /*
             * The image is intentionally large.
             * It will cover most of the screen.
             */

            studentImage.setOpacity(0.55);

        } catch (Exception e) {

            System.out.println(
                    "Student image could not be loaded."
            );

            System.out.println(
                    e.getMessage()
            );
        }


        // =====================================================
        // RESPONSIVE STUDENT IMAGE SIZE
        // =====================================================

        studentImage.fitWidthProperty()
                .bind(
                        root.widthProperty()
                                .multiply(0.90)
                );

        studentImage.fitHeightProperty()
                .bind(
                        root.heightProperty()
                                .multiply(0.95)
                );


        StackPane.setAlignment(
                studentImage,
                Pos.CENTER
        );


        // =====================================================
        // IMAGE DARK OVERLAY
        // =====================================================

        Region imageOverlay =
                new Region();

        imageOverlay.setMouseTransparent(
                true
        );

        imageOverlay.setStyle(

                "-fx-background-color: " +

                "linear-gradient(" +

                "to right," +

                "rgba(4,5,5,0.18)," +

                "rgba(4,5,5,0.42)," +

                "rgba(4,5,5,0.82)," +

                "rgba(4,5,5,0.97)" +

                ");"
        );


        imageOverlay.prefWidthProperty()
                .bind(
                        root.widthProperty()
                );

        imageOverlay.prefHeightProperty()
                .bind(
                        root.heightProperty()
                );


        // =====================================================
        // SECOND DARK OVERLAY
        // =====================================================

        Region topDarkOverlay =
                new Region();

        topDarkOverlay.setMouseTransparent(
                true
        );

        topDarkOverlay.setStyle(

                "-fx-background-color: " +

                "linear-gradient(" +

                "to bottom," +

                "rgba(4,5,5,0.65)," +

                "transparent 35%," +

                "rgba(4,5,5,0.60)" +

                ");"
        );


        topDarkOverlay.prefWidthProperty()
                .bind(
                        root.widthProperty()
                );

        topDarkOverlay.prefHeightProperty()
                .bind(
                        root.heightProperty()
                );


        // =====================================================
        // DECORATIVE DIAGONAL SHAPES
        // =====================================================

        Polygon diagonal1 =
                new Polygon(

                        700, 0,
                        1100, 0,
                        1100, 160,
                        780, 60
                );

        diagonal1.setFill(
                Color.web(
                        LIME_3,
                        0.18
                )
        );


        Polygon diagonal2 =
                new Polygon(

                        850, 0,
                        1100, 0,
                        1100, 90
                );

        diagonal2.setFill(
                Color.web(
                        LIME,
                        0.08
                ));


        Polygon diagonal3 =
                new Polygon(

                        0, 650,
                        0, 800,
                        330, 800,
                        110, 660
                );

        diagonal3.setFill(
                Color.web(
                        OLIVE,
                        0.20
                ));


        // =====================================================
        // TOP BAR
        // =====================================================

        HBox topBar =
                new HBox();

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setPadding(
                new Insets(
                        20,
                        50,
                        0,
                        55
                )
        );


        // =====================================================
        // LARGE ADMITX LOGO
        // ORIGINAL SIZE: 1600 x 570
        // =====================================================

        ImageView logoView =
                new ImageView();

        try {

            Image logo =
                    new Image(
                            "/assets/images/admitxlogo.jpeg"
                    );

            logoView.setImage(
                    logo
            );

            /*
             * LARGE LOGO
             *
             * Original:
             * 1600 x 570
             *
             * Display:
             * approximately 300 x 107
             */

            logoView.setFitWidth(
                    300
            );

            logoView.setFitHeight(
                    107
            );

            logoView.setPreserveRatio(
                    true
            );

            logoView.setSmooth(
                    true
            );

        } catch (Exception e) {

            System.out.println(
                    "Logo could not be loaded."
            );

            System.out.println(
                    e.getMessage()
            );
        }


        topBar.getChildren().add(
                logoView
        );


        // =====================================================
        // TOP SPACER
        // =====================================================

        Region topSpacer =
                new Region();

        HBox.setHgrow(
                topSpacer,
                Priority.ALWAYS
        );


        // =====================================================
        // USER GUIDE
        // =====================================================

        Button guideButton =
                new Button(
                        "USER GUIDE"
                );

        guideButton.setCursor(
                Cursor.HAND
        );

        applyGuideStyle(
                guideButton,
                false
        );

        guideButton.setOnMouseEntered(
                e -> applyGuideStyle(
                        guideButton,
                        true
                )
        );

        guideButton.setOnMouseExited(
                e -> applyGuideStyle(
                        guideButton,
                        false
                )
        );

        guideButton.setOnAction(
                e -> showGuide()
        );


        topBar.getChildren().addAll(

                topSpacer,
                guideButton
        );


        // =====================================================
        // LEFT CONTENT
        // =====================================================

        VBox leftContent =
                new VBox(
                        18
                );

        leftContent.setAlignment(
                Pos.CENTER_LEFT
        );

        leftContent.setMaxWidth(
                540
        );


        // =====================================================
        // SMALL LABEL
        // =====================================================

        Label smallLabel =
                new Label(
                        "MHT CET • CAP ADMISSION PLATFORM"
                );

        smallLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        smallLabel.setTextFill(
                Color.web(LIME)
        );

        smallLabel.setStyle(
                "-fx-letter-spacing: 2px;"
        );


        // =====================================================
        // MAIN HEADING
        // =====================================================

        Label heading =
                new Label(
                        "YOUR ADMISSION.\nYOUR FUTURE."
                );

        heading.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        55
                )
        );

        heading.setTextFill(
                Color.web(WHITE)
        );

        heading.setLineSpacing(
                -5
        );

        heading.setEffect(
                new DropShadow(
                        30,
                        Color.web(
                                LIME,
                                0.10
                        )
                )
        );


        // =====================================================
        // LIME HEADING
        // =====================================================

        Label limeText =
                new Label(
                        "MAKE THE RIGHT CHOICE."
                );

        limeText.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        18
                )
        );

        limeText.setTextFill(
                Color.web(LIME)
        );

        limeText.setStyle(
                "-fx-letter-spacing: 1px;"
        );


        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label description =
                new Label(

                        "AI-powered counselling that helps you " +
                        "understand colleges, track CAP rounds " +
                        "and make smarter admission decisions."
                );

        description.setFont(
                Font.font(
                        "Segoe UI",
                        15
                )
        );

        description.setTextFill(
                Color.web(BEIGE)
        );

        description.setWrapText(
                true
        );

        description.setMaxWidth(
                470
        );


        // =====================================================
        // FEATURES
        // =====================================================

        VBox features =
                new VBox(
                        12
                );

        features.getChildren().addAll(

                createFeature(
                        "01",
                        "SMART ALLOTMENT",
                        "Better college choices"
                ),

                createFeature(
                        "02",
                        "CAP ROUND TRACKING",
                        "Never miss an update"
                ),

                createFeature(
                        "03",
                        "COLLEGE ANALYTICS",
                        "Decide with confidence"
                )
        );


        leftContent.getChildren().addAll(

                smallLabel,
                heading,
                limeText,
                description,
                features
        );


        // =====================================================
        // LEFT SECTION
        // =====================================================

        StackPane leftSection =
                new StackPane();

        leftSection.setAlignment(
                Pos.CENTER_LEFT
        );

        leftSection.setMaxWidth(
                590
        );

        leftSection.getChildren().add(
                leftContent
        );


        // =====================================================
        // RIGHT ACTION CARD
        // =====================================================

        VBox actionCard =
                new VBox(
                        17
                );

        actionCard.setAlignment(
                Pos.TOP_CENTER
        );

        actionCard.setPrefWidth(
                380
        );

        actionCard.setMaxWidth(
                380
        );

        actionCard.setPadding(
                new Insets(
                        36,
                        32,
                        32,
                        32
                )
        );

        actionCard.setStyle(

                "-fx-background-color:" +
                "rgba(16,18,17,0.96);" +

                "-fx-background-radius:20;" +

                "-fx-border-color:" +
                "rgba(198,233,47,0.15);" +

                "-fx-border-radius:20;" +

                "-fx-border-width:1;"
        );


        actionCard.setEffect(
                new DropShadow(
                        45,
                        Color.color(
                                0,
                                0,
                                0,
                                0.75
                        )
                )
        );


        // =====================================================
        // CARD HEADER
        // =====================================================

        Label cardSmall =
                new Label(
                        "WELCOME TO"
                );

        cardSmall.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        cardSmall.setTextFill(
                Color.web(MUTED)
        );

        cardSmall.setStyle(
                "-fx-letter-spacing:2px;"
        );


        Label cardTitle =
                new Label(
                        "ADMITX"
                );

        cardTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        30
                )
        );

        cardTitle.setTextFill(
                Color.web(WHITE)
        );


        Label cardDescription =
                new Label(
                        "Choose how you want to continue"
                );

        cardDescription.setFont(
                Font.font(
                        "Segoe UI",
                        12
                )
        );

        cardDescription.setTextFill(
                Color.web(GREY)
        );


        // =====================================================
        // STUDENT LABEL
        // =====================================================

        Label studentLabel =
                new Label(
                        "STUDENT"
                );

        studentLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        studentLabel.setTextFill(
                Color.web(LIME)
        );

        studentLabel.setStyle(
                "-fx-letter-spacing:2px;"
        );


        // =====================================================
        // STUDENT LOGIN
        // =====================================================

        Button loginButton =
                new Button(
                        "STUDENT LOGIN   →"
                );

        loginButton.setMaxWidth(
                Double.MAX_VALUE
        );

        loginButton.setPrefHeight(
                52
        );

        loginButton.setCursor(
                Cursor.HAND
        );

        loginButton.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        13
                )
        );

        applyPrimaryButton(
                loginButton,
                false
        );

        loginButton.setOnMouseEntered(
                e -> applyPrimaryButton(
                        loginButton,
                        true
                )
        );

        loginButton.setOnMouseExited(
                e -> applyPrimaryButton(
                        loginButton,
                        false
                )
        );


        // =====================================================
        // STUDENT REGISTRATION
        // =====================================================

        Button registerButton =
                new Button(
                        "CREATE STUDENT ACCOUNT"
                );

        registerButton.setMaxWidth(
                Double.MAX_VALUE
        );

        registerButton.setPrefHeight(
                48
        );

        registerButton.setCursor(
                Cursor.HAND
        );

        registerButton.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        12
                )
        );

        applySecondaryButton(
                registerButton,
                false
        );

        registerButton.setOnMouseEntered(
                e -> applySecondaryButton(
                        registerButton,
                        true
                )
        );

        registerButton.setOnMouseExited(
                e -> applySecondaryButton(
                        registerButton,
                        false
                )
        );


        // =====================================================
        // DIVIDER
        // =====================================================

        HBox divider =
                createDivider();


        // =====================================================
        // COUNSELLOR
        // =====================================================

        Label counsellorLabel =
                new Label(
                        "COUNSELLOR PORTAL"
                );

        counsellorLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        counsellorLabel.setTextFill(
                Color.web(MUTED)
        );

        counsellorLabel.setStyle(
                "-fx-letter-spacing:1.5px;"
        );


        Button counsellorButton =
                new Button(
                        "COUNSELLOR LOGIN   →"
                );

        counsellorButton.setMaxWidth(
                Double.MAX_VALUE
        );

        counsellorButton.setPrefHeight(
                43
        );

        counsellorButton.setCursor(
                Cursor.HAND
        );

        counsellorButton.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        applyCounsellorButton(
                counsellorButton,
                false
        );

        counsellorButton.setOnMouseEntered(
                e -> applyCounsellorButton(
                        counsellorButton,
                        true
                )
        );

        counsellorButton.setOnMouseExited(
                e -> applyCounsellorButton(
                        counsellorButton,
                        false
                )
        );


        // =====================================================
        // NAVIGATION
        // =====================================================

        loginButton.setOnAction(
                e -> Navigation.goTo(
                        StudentLoginPage.getScene()
                )
        );


        registerButton.setOnAction(
                e -> Navigation.goTo(
                        StudentSignupPage.getScene()
                )
        );


        counsellorButton.setOnAction(
                e -> Navigation.goTo(
                        CounsellorLoginPage.getScene()
                )
        );


        // =====================================================
        // CARD CONTENT
        // =====================================================

        actionCard.getChildren().addAll(

                cardSmall,
                cardTitle,
                cardDescription,

                createSpacing(8),

                studentLabel,
                loginButton,
                registerButton,

                divider,

                counsellorLabel,
                counsellorButton
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        HBox mainContent =
                new HBox(
                        80
                );

        mainContent.setAlignment(
                Pos.CENTER
        );

        mainContent.setPadding(
                new Insets(
                        20,
                        70,
                        20,
                        70
                )
        );


        HBox.setHgrow(
                leftSection,
                Priority.ALWAYS
        );


        mainContent.getChildren().addAll(

                leftSection,
                actionCard
        );


        // =====================================================
        // FOOTER
        // =====================================================

        Label footer =
                new Label(
                        "© 2026 ADMITX  •  SMARTER ADMISSIONS"
                );

        footer.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        9
                )
        );

        footer.setTextFill(
                Color.web(MUTED)
        );

        footer.setStyle(
                "-fx-letter-spacing:1px;"
        );


        // =====================================================
        // PAGE
        // =====================================================

        BorderPane page =
                new BorderPane();

        page.setTop(
                topBar
        );

        page.setCenter(
                mainContent
        );


        BorderPane.setAlignment(
                footer,
                Pos.CENTER
        );


        BorderPane.setMargin(
                footer,
                new Insets(
                        0,
                        0,
                        20,
                        0
                )
        );


        page.setBottom(
                footer
        );


        // =====================================================
        // ROOT LAYERS
        // =====================================================

        root.getChildren().addAll(

                studentImage,

                imageOverlay,

                topDarkOverlay,

                diagonal1,
                diagonal2,
                diagonal3,

                page
        );


        // =====================================================
        // FULL SCREEN SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root
                );


        // =====================================================
        // FADE ANIMATION
        // =====================================================

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(700),
                        root
                );

        fade.setFromValue(
                0
        );

        fade.setToValue(
                1
        );

        fade.play();


        return scene;
    }


    // =========================================================
    // FEATURE
    // =========================================================

    private static HBox createFeature(

            String number,
            String title,
            String description
    ) {

        HBox box =
                new HBox(
                        12
                );

        box.setAlignment(
                Pos.CENTER_LEFT
        );


        Label numberLabel =
                new Label(
                        number
                );

        numberLabel.setPrefWidth(
                25
        );

        numberLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        numberLabel.setTextFill(
                Color.web(LIME)
        );


        VBox text =
                new VBox(
                        2
                );


        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        titleLabel.setTextFill(
                Color.web(WHITE)
        );


        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setFont(
                Font.font(
                        "Segoe UI",
                        10
                )
        );

        descriptionLabel.setTextFill(
                Color.web(MUTED)
        );


        text.getChildren().addAll(

                titleLabel,
                descriptionLabel
        );


        box.getChildren().addAll(

                numberLabel,
                text
        );


        return box;
    }


    // =========================================================
    // DIVIDER
    // =========================================================

    private static HBox createDivider() {

        HBox divider =
                new HBox(
                        10
                );

        divider.setAlignment(
                Pos.CENTER
        );


        Region line1 =
                new Region();

        Region line2 =
                new Region();


        HBox.setHgrow(
                line1,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                line2,
                Priority.ALWAYS
        );


        line1.setPrefHeight(
                1
        );

        line2.setPrefHeight(
                1
        );


        line1.setStyle(
                "-fx-background-color:"
                + BORDER + ";"
        );

        line2.setStyle(
                "-fx-background-color:"
                + BORDER + ";"
        );


        Label or =
                new Label(
                        "OR"
                );

        or.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        9
                )
        );

        or.setTextFill(
                Color.web(MUTED)
        );


        divider.getChildren().addAll(

                line1,
                or,
                line2
        );


        return divider;
    }


    // =========================================================
    // SPACING
    // =========================================================

    private static Region createSpacing(
            double height
    ) {

        Region region =
                new Region();

        region.setPrefHeight(
                height
        );

        return region;
    }


    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private static void applyPrimaryButton(

            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(

                    "-fx-background-color:"
                    + LIME + ";"

                    + "-fx-text-fill:#050505;"

                    + "-fx-background-radius:10;"

                    + "-fx-font-weight:bold;"

                    + "-fx-font-size:13px;"

                    + "-fx-effect:dropshadow("
                    + "gaussian,"
                    + "rgba(198,233,47,0.45),"
                    + "22,0,0,5);"
            );

        } else {

            button.setStyle(

                    "-fx-background-color:"
                    + LIME_2 + ";"

                    + "-fx-text-fill:#FFFFFF;"

                    + "-fx-background-radius:10;"

                    + "-fx-font-weight:bold;"

                    + "-fx-font-size:13px;"

                    + "-fx-effect:dropshadow("
                    + "gaussian,"
                    + "rgba(138,163,11,0.25),"
                    + "15,0,0,4);"
            );
        }
    }


    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private static void applySecondaryButton(

            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(

                    "-fx-background-color:"
                    + "rgba(198,233,47,0.08);"

                    + "-fx-text-fill:"
                    + LIME + ";"

                    + "-fx-background-radius:10;"

                    + "-fx-border-color:"
                    + LIME_2 + ";"

                    + "-fx-border-radius:10;"

                    + "-fx-border-width:1;"

                    + "-fx-font-weight:bold;"

                    + "-fx-font-size:12px;"
            );

        } else {

            button.setStyle(

                    "-fx-background-color:"
                    + "rgba(255,255,255,0.025);"

                    + "-fx-text-fill:"
                    + WHITE + ";"

                    + "-fx-background-radius:10;"

                    + "-fx-border-color:"
                    + BORDER + ";"

                    + "-fx-border-radius:10;"

                    + "-fx-border-width:1;"

                    + "-fx-font-weight:bold;"

                    + "-fx-font-size:12px;"
            );
        }
    }


    // =========================================================
    // COUNSELLOR BUTTON
    // =========================================================

    private static void applyCounsellorButton(

            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(

                    "-fx-background-color:"
                    + "rgba(198,233,47,0.06);"

                    + "-fx-text-fill:"
                    + LIME + ";"

                    + "-fx-background-radius:9;"

                    + "-fx-border-color:"
                    + "rgba(198,233,47,0.25);"

                    + "-fx-border-radius:9;"

                    + "-fx-border-width:1;"

                    + "-fx-font-weight:bold;"

                    + "-fx-font-size:11px;"
            );

        } else {

            button.setStyle(

                    "-fx-background-color:"
                    + "transparent;"

                    + "-fx-text-fill:"
                    + GREY + ";"

                    + "-fx-background-radius:9;"

                    + "-fx-border-color:"
                    + BORDER + ";"

                    + "-fx-border-radius:9;"

                    + "-fx-border-width:1;"

                    + "-fx-font-weight:bold;"

                    + "-fx-font-size:11px;"
            );
        }
    }


    // =========================================================
    // GUIDE BUTTON
    // =========================================================

    private static void applyGuideStyle(

            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(

                    "-fx-background-color:"
                    + "rgba(198,233,47,0.06);"

                    + "-fx-text-fill:"
                    + LIME + ";"

                    + "-fx-font-size:10px;"

                    + "-fx-font-weight:bold;"

                    + "-fx-background-radius:8;"

                    + "-fx-border-color:"
                    + "rgba(198,233,47,0.15);"

                    + "-fx-border-radius:8;"

                    + "-fx-border-width:1;"
            );

        } else {

            button.setStyle(

                    "-fx-background-color:"
                    + "transparent;"

                    + "-fx-text-fill:"
                    + GREY + ";"

                    + "-fx-font-size:10px;"

                    + "-fx-font-weight:bold;"
            );
        }
    }


    // =========================================================
    // USER GUIDE
    // =========================================================

    private static void showGuide() {

        System.out.println(
                "User Guide clicked - implement guide view"
        );
    }
}