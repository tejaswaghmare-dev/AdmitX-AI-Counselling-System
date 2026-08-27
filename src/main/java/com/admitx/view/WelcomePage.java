package com.admitx.view;



import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private static final String BLACK = "#050705";
    private static final String DARK = "#0C110B";
    

    private static final String LIME = "#B7FF00";
    private static final String LIME_DARK = "#8CC900";
   

    private static final String WHITE = "#F8FAF5";
    private static final String TEXT = "#DDE5D7";
    private static final String GREY = "#9BA69A";
    private static final String MUTED = "#687266";
    private static final String BORDER = "#283326";

    public static Scene getScene() {

        StackPane root = new StackPane();

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                new LinearGradient(
                                        0,
                                        0,
                                        1,
                                        1,
                                        true,
                                        CycleMethod.NO_CYCLE,
                                        new Stop(
                                                0,
                                                Color.web(BLACK)
                                        ),
                                        new Stop(
                                                0.45,
                                                Color.web(DARK)
                                        ),
                                        new Stop(
                                                1,
                                                Color.web("#172016")
                                        )
                                ),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // =====================================================
        // BACKGROUND IMAGE
        // =====================================================

        ImageView studentImage =
                new ImageView();

        try {

            Image image =
                    new Image(
                            "/assets/images/admitxstd.jpeg"
                    );

            studentImage.setImage(image);

            studentImage.setPreserveRatio(true);
            studentImage.setSmooth(true);
            studentImage.setOpacity(0.42);

        } catch (Exception e) {

            System.out.println(
                    "Student image could not be loaded."
            );
        }

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
        // IMAGE OVERLAY
        // =====================================================

        Region imageOverlay =
                new Region();

        imageOverlay.setMouseTransparent(true);

        imageOverlay.setStyle(
                "-fx-background-color:" +
                "linear-gradient(" +
                "to right," +
                "rgba(5,7,5,0.10)," +
                "rgba(5,7,5,0.50)," +
                "rgba(5,7,5,0.94)" +
                ");"
        );

        imageOverlay.prefWidthProperty()
                .bind(root.widthProperty());

        imageOverlay.prefHeightProperty()
                .bind(root.heightProperty());

        // =====================================================
        // TOP OVERLAY
        // =====================================================

        Region topOverlay =
                new Region();

        topOverlay.setMouseTransparent(true);

        topOverlay.setStyle(
                "-fx-background-color:" +
                "linear-gradient(" +
                "to bottom," +
                "rgba(5,7,5,0.85)," +
                "transparent 38%," +
                "rgba(5,7,5,0.65)" +
                ");"
        );

        topOverlay.prefWidthProperty()
                .bind(root.widthProperty());

        topOverlay.prefHeightProperty()
                .bind(root.heightProperty());

        // =====================================================
        // DECORATIVE LIME SHAPES
        // =====================================================

        Polygon shape1 =
                new Polygon(
                        800, 0,
                        1200, 0,
                        1200, 160,
                        930, 55
                );

        shape1.setFill(
                Color.web(
                        LIME,
                        0.08
                )
        );

        Polygon shape2 =
                new Polygon(
                        950, 0,
                        1200, 0,
                        1200, 90
                );

        shape2.setFill(
                Color.web(
                        LIME,
                        0.12
                )
        );

        Polygon shape3 =
                new Polygon(
                        0, 650,
                        0, 800,
                        350, 800,
                        120, 660
                );

        shape3.setFill(
                Color.web(
                        LIME_DARK,
                        0.12
                )
        );

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
                        55,
                        0,
                        55
                )
        );

        // =====================================================
        // LOGO
        // =====================================================

        ImageView logoView =
                new ImageView();

        try {

            Image logo =
                    new Image(
                            "assets/images/admitxlogo.jpeg"
                    );

            logoView.setImage(logo);

            logoView.setFitWidth(220);
            logoView.setFitHeight(80);

            logoView.setPreserveRatio(true);
            logoView.setSmooth(true);

        } catch (Exception e) {

            Label logoText =
                    new Label("ADMITX AI");

            logoText.setStyle(
                    "-fx-text-fill:" + LIME + ";" +
                    "-fx-font-size:28px;" +
                    "-fx-font-weight:bold;"
            );
        }

        topBar.getChildren().add(
                logoView
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
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

        guideButton.setPrefHeight(38);
        guideButton.setPrefWidth(120);

        guideButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:9;" +
                "-fx-background-radius:9;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;"
        );

        guideButton.setOnMouseEntered(
                e -> guideButton.setStyle(
                        "-fx-background-color:" +
                        LIME + ";" +
                        "-fx-text-fill:" +
                        BLACK + ";" +
                        "-fx-border-color:" +
                        LIME + ";" +
                        "-fx-border-radius:9;" +
                        "-fx-background-radius:9;" +
                        "-fx-font-size:11px;" +
                        "-fx-font-weight:bold;"
                )
        );

        guideButton.setOnMouseExited(
                e -> guideButton.setStyle(
                        "-fx-background-color:transparent;" +
                        "-fx-text-fill:" + TEXT + ";" +
                        "-fx-border-color:" + BORDER + ";" +
                        "-fx-border-radius:9;" +
                        "-fx-background-radius:9;" +
                        "-fx-font-size:11px;" +
                        "-fx-font-weight:bold;"
                )
        );

        guideButton.setOnAction(
                e -> Navigation.goTo(UserGuidePage.getScene())
        );

        topBar.getChildren().addAll(
                spacer,
                guideButton
        );

        // =====================================================
        // LEFT CONTENT
        // =====================================================

        VBox leftContent =
                new VBox(16);

        leftContent.setAlignment(
                Pos.CENTER_LEFT
        );

        leftContent.setMaxWidth(570);

        Label smallLabel =
                new Label(
                        "MHT CET  •  CAP ADMISSION PLATFORM"
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
                "-fx-letter-spacing:2px;"
        );

        Label heading =
                new Label(
                        "YOUR ADMISSION.\nYOUR FUTURE."
                );

        heading.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        54
                )
        );

        heading.setTextFill(
                Color.web(WHITE)
        );

        heading.setLineSpacing(-5);

        heading.setEffect(
                new DropShadow(
                        28,
                        Color.web(
                                LIME,
                                0.12
                        )
                )
        );

        Label limeHeading =
                new Label(
                        "MAKE THE RIGHT CHOICE."
                );

        limeHeading.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        19
                )
        );

        limeHeading.setTextFill(
                Color.web(LIME)
        );

        limeHeading.setStyle(
                "-fx-letter-spacing:1px;"
        );

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
                Color.web(TEXT)
        );

        description.setWrapText(true);

        description.setMaxWidth(500);

        // =====================================================
        // FEATURES
        // =====================================================

        VBox features =
                new VBox(13);

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
                limeHeading,
                description,
                features
        );

        StackPane leftSection =
                new StackPane();

        leftSection.setAlignment(
                Pos.CENTER_LEFT
        );

        leftSection.setMaxWidth(600);

        leftSection.getChildren().add(
                leftContent
        );

        // =====================================================
        // RIGHT ACTION CARD
        // =====================================================

        VBox actionCard =
                new VBox(15);

        actionCard.setAlignment(
                Pos.TOP_CENTER
        );

        actionCard.setPrefWidth(390);
        actionCard.setMaxWidth(390);

        actionCard.setPadding(
                new Insets(
                        35,
                        32,
                        32,
                        32
                )
        );

        actionCard.setStyle(
                "-fx-background-color:" +
                "rgba(18,24,18,0.97);" +
                "-fx-background-radius:22;" +
                "-fx-border-color:" +
                "rgba(183,255,0,0.20);" +
                "-fx-border-radius:22;" +
                "-fx-border-width:1;"
        );

        actionCard.setEffect(
                new DropShadow(
                        45,
                        Color.color(
                                0,
                                0,
                                0,
                                0.80
                        )
                )
        );

        // =====================================================
        // CARD HEADER
        // =====================================================

        Label welcome =
                new Label(
                        "WELCOME TO"
                );

        welcome.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        welcome.setTextFill(
                Color.web(MUTED)
        );

        welcome.setStyle(
                "-fx-letter-spacing:2px;"
        );

        Label admitx =
                new Label(
                        "ADMITX AI"
                );

        admitx.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        31
                )
        );

        admitx.setTextFill(
                Color.web(WHITE)
        );

        Label choose =
                new Label(
                        "Choose how you want to continue"
                );

        choose.setFont(
                Font.font(
                        "Segoe UI",
                        12
                )
        );

        choose.setTextFill(
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
                        "STUDENT LOGIN     →"
                );

        stylePrimaryButton(
                loginButton
        );

        loginButton.setOnMouseEntered(
                e -> primaryHover(
                        loginButton
                )
        );

        loginButton.setOnMouseExited(
                e -> stylePrimaryButton(
                        loginButton
                )
        );

        loginButton.setOnAction(
                e -> Navigation.goTo(
                        StudentLoginPage.getScene()
                )
        );

        // =====================================================
        // REGISTRATION
        // =====================================================

        Button registerButton =
                new Button(
                        "CREATE STUDENT ACCOUNT"
                );

        styleSecondaryButton(
                registerButton
        );

        registerButton.setOnMouseEntered(
                e -> secondaryHover(
                        registerButton
                )
        );

        registerButton.setOnMouseExited(
                e -> styleSecondaryButton(
                        registerButton
                )
        );

        registerButton.setOnAction(
                e -> Navigation.goTo(
                        StudentRegistrationPage.getScene()
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
                Color.web(GREY)
        );

        counsellorLabel.setStyle(
                "-fx-letter-spacing:1.5px;"
        );

        Button counsellorButton =
                new Button(
                        "COUNSELLOR LOGIN     →"
                );

        styleCounsellorButton(
                counsellorButton
        );

        counsellorButton.setOnMouseEntered(
                e -> counsellorHover(
                        counsellorButton
                )
        );

        counsellorButton.setOnMouseExited(
                e -> styleCounsellorButton(
                        counsellorButton
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
                welcome,
                admitx,
                choose,
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
                new HBox(75);

        mainContent.setAlignment(
                Pos.CENTER
        );

        mainContent.setPadding(
                new Insets(
                        25,
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
                        "© 2026 ADMITX AI  •  SMARTER ADMISSIONS"
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
                        18,
                        0
                )
        );

        page.setBottom(
                footer
        );

        // =====================================================
        // ROOT
        // =====================================================

        root.getChildren().addAll(
                studentImage,
                imageOverlay,
                topOverlay,
                shape1,
                shape2,
                shape3,
                page
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(root);

        // =====================================================
        // FADE ANIMATION
        // =====================================================

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(650),
                        root
                );

        fade.setFromValue(0);
        fade.setToValue(1);
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
                new HBox(13);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        Label numberLabel =
                new Label(number);

        numberLabel.setPrefWidth(27);

        numberLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        numberLabel.setTextFill(
                Color.web(LIME)
        );

        VBox text =
                new VBox(2);

        Label titleLabel =
                new Label(title);

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
                new Label(description);

        descriptionLabel.setFont(
                Font.font(
                        "Segoe UI",
                        10
                )
        );

        descriptionLabel.setTextFill(
                Color.web(GREY)
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
                new HBox(10);

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

        line1.setPrefHeight(1);
        line2.setPrefHeight(1);

        line1.setStyle(
                "-fx-background-color:" +
                BORDER + ";"
        );

        line2.setStyle(
                "-fx-background-color:" +
                BORDER + ";"
        );

        Label or =
                new Label("OR");

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

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(52);

        button.setCursor(
                Cursor.HAND
        );

        button.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        13
                )
        );

        button.setStyle(
                "-fx-background-color:" +
                LIME_DARK + ";" +
                "-fx-text-fill:white;" +
                "-fx-background-radius:10;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:13px;"
        );
    }

    private static void primaryHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color:" +
                LIME + ";" +
                "-fx-text-fill:" +
                BLACK + ";" +
                "-fx-background-radius:10;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:13px;" +
                "-fx-effect:dropshadow(" +
                "gaussian," +
                "rgba(183,255,0,0.40)," +
                "20,0,0,4);"
        );
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(48);

        button.setCursor(
                Cursor.HAND
        );

        button.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        12
                )
        );

        button.setStyle(
                "-fx-background-color:" +
                "rgba(255,255,255,0.025);" +
                "-fx-text-fill:" +
                WHITE + ";" +
                "-fx-background-radius:10;" +
                "-fx-border-color:" +
                BORDER + ";" +
                "-fx-border-radius:10;" +
                "-fx-border-width:1;" +
                "-fx-font-size:12px;"
        );
    }

    private static void secondaryHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color:" +
                "rgba(183,255,0,0.08);" +
                "-fx-text-fill:" +
                LIME + ";" +
                "-fx-background-radius:10;" +
                "-fx-border-color:" +
                LIME_DARK + ";" +
                "-fx-border-radius:10;" +
                "-fx-border-width:1;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );
    }

    // =========================================================
    // COUNSELLOR BUTTON
    // =========================================================

    private static void styleCounsellorButton(
            Button button
    ) {

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(44);

        button.setCursor(
                Cursor.HAND
        );

        button.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        button.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" +
                GREY + ";" +
                "-fx-background-radius:9;" +
                "-fx-border-color:" +
                BORDER + ";" +
                "-fx-border-radius:9;" +
                "-fx-border-width:1;" +
                "-fx-font-size:11px;"
        );
    }

    private static void counsellorHover(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color:" +
                "rgba(183,255,0,0.07);" +
                "-fx-text-fill:" +
                LIME + ";" +
                "-fx-background-radius:9;" +
                "-fx-border-color:" +
                LIME_DARK + ";" +
                "-fx-border-radius:9;" +
                "-fx-border-width:1;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;"
        );
    }

    // =========================================================
    // USER GUIDE
    // =========================================================

    private static void showGuide() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "AdmitX AI - User Guide"
        );

        alert.setHeaderText(
                "How to use AdmitX AI"
        );

        alert.setContentText(
                "1. Student Login - Login using your Application ID or Email.\n\n" +
                "2. Student Registration - Create your student account.\n\n" +
                "3. Complete Application - Fill your personal, academic and reservation details.\n\n" +
                "4. Upload Documents - Upload the required documents.\n\n" +
                "5. Merit List - Check your provisional and final merit status.\n\n" +
                "6. Option Form - Search colleges and fill your preferences.\n\n" +
                "7. CAP Rounds - Track allotment, freeze or request betterment.\n\n" +
                "8. Admission - Complete the final admission process."
        );

        alert.showAndWait();
    }
}