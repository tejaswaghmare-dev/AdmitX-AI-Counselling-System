package com.admitx.view;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class StudentRegistrationPage {

    // =========================================================
    // COLOR PALETTE
    // =========================================================

    private static final String BG_DARK = "#080B12";
    private static final String BG_BLUE = "#101A2D";

    private static final String CARD = "#111827";
    private static final String CARD_2 = "#151F32";

    private static final String BLUE = "#4A7FB5";
    private static final String BLUE_LIGHT = "#6C9FD2";
    private static final String BLUE_DARK = "#1E3A5F";

    private static final String WHITE = "#F3F6FA";
    private static final String TEXT = "#C7D3E2";
    private static final String MUTED = "#7F91A7";

    private static final String INPUT = "#0B111B";
    private static final String BORDER = "#26364B";

    // =========================================================
    // GET SCENE
    // =========================================================

    public static Scene getScene() {

        // =====================================================
        // ROOT
        // =====================================================

        StackPane root = new StackPane();

        // =====================================================
        // BACKGROUND
        // =====================================================

        LinearGradient gradient = new LinearGradient(
                0,
                0,
                1,
                1,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#080B12")),
                new Stop(0.35, Color.web("#0D1421")),
                new Stop(0.7, Color.web("#101D33")),
                new Stop(1, Color.web("#080B12"))
        );

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                gradient,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // =====================================================
        // DECORATIVE BLUE GLOW
        // =====================================================

        Region glowTop = new Region();

        glowTop.setPrefSize(300, 300);

        glowTop.setStyle(
                "-fx-background-color: rgba(74,127,181,0.08);" +
                "-fx-background-radius: 300;"
        );

        glowTop.setEffect(
                new DropShadow(
                        120,
                        Color.web(BLUE, 0.25)
                )
        );

        StackPane.setAlignment(
                glowTop,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                glowTop,
                new Insets(-150, -100, 0, 0)
        );

        // =====================================================
        // MAIN CONTAINER
        // =====================================================

        VBox main = new VBox(10);

        main.setAlignment(Pos.CENTER);

        main.setMaxWidth(480);

        main.setPadding(
                new Insets(25, 30, 25, 30)
        );

        // =====================================================
        // TOP BAR
        // =====================================================

        HBox topBar = new HBox();

        topBar.setAlignment(Pos.CENTER_LEFT);

        topBar.setPrefWidth(450);

        Button backButton = new Button("←  Back");

        backButton.setCursor(Cursor.HAND);

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 7 14 7 14;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #26364B;" +
                "-fx-border-radius: 8;" +
                "-fx-border-width: 1;"
        );

        backButton.setOnMouseEntered(e ->
                backButton.setStyle(
                        "-fx-background-color: rgba(74,127,181,0.12);" +
                        "-fx-text-fill: #B8D4EF;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 7 14 7 14;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: #4A7FB5;" +
                        "-fx-border-radius: 8;" +
                        "-fx-border-width: 1;"
                )
        );

        backButton.setOnMouseExited(e ->
                backButton.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: #8AA8C7;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 7 14 7 14;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: #26364B;" +
                        "-fx-border-radius: 8;" +
                        "-fx-border-width: 1;"
                )
        );

        topBar.getChildren().add(backButton);

        // =====================================================
        // BRAND
        // =====================================================

        Label brand = new Label("ADMITX");

        brand.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        14
                )
        );

        brand.setTextFill(
                Color.web(BLUE_LIGHT)
        );

        brand.setStyle(
                "-fx-letter-spacing: 4px;"
        );

        // =====================================================
        // ICON
        // =====================================================

        Label icon = new Label("📝");

        icon.setFont(
                Font.font(
                        "Segoe UI Emoji",
                        38
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label("Create Account");

        title.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        34
                )
        );

        title.setTextFill(
                Color.web(WHITE)
        );

        title.setEffect(
                new DropShadow(
                        20,
                        Color.web(BLUE, 0.18)
                )
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        Label subtitle = new Label(
                "Create your student account and start your admission journey"
        );

        subtitle.setFont(
                Font.font(
                        "Segoe UI",
                        13
                )
        );

        subtitle.setTextFill(
                Color.web(MUTED)
        );

        // =====================================================
        // FORM CARD
        // =====================================================

        VBox formCard = new VBox(15);

        formCard.setAlignment(
                Pos.CENTER_LEFT
        );

        formCard.setPadding(
                new Insets(28, 32, 28, 32)
        );

        formCard.setMaxWidth(450);

        formCard.setStyle(
                "-fx-background-color: #111827;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #26364B;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );

        formCard.setEffect(
                new DropShadow(
                        35,
                        0,
                        15,
                        Color.color(0, 0, 0, 0.55)
                )
        );

        // =====================================================
        // CARD HEADER
        // =====================================================

        Label formTitle = new Label(
                "Student Registration"
        );

        formTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        18
                )
        );

        formTitle.setTextFill(
                Color.web(WHITE)
        );

        Label formSubtitle = new Label(
                "Fill in your details below"
        );

        formSubtitle.setFont(
                Font.font(
                        "Segoe UI",
                        12
                )
        );

        formSubtitle.setTextFill(
                Color.web(MUTED)
        );

        VBox cardHeader = new VBox(4);

        cardHeader.getChildren().addAll(
                formTitle,
                formSubtitle
        );

        // =====================================================
        // FIELDS
        // =====================================================

        VBox nameBox = createTextField(
                "FULL NAME",
                "Enter your full name"
        );

        TextField name =
                (TextField) nameBox.getProperties()
                        .get("field");

        VBox emailBox = createTextField(
                "EMAIL ADDRESS",
                "Enter your email address"
        );

        TextField email =
                (TextField) emailBox.getProperties()
                        .get("field");

        VBox mobileBox = createTextField(
                "MOBILE NUMBER",
                "Enter your mobile number"
        );

        TextField mobile =
                (TextField) mobileBox.getProperties()
                        .get("field");

        VBox passwordBox = createPasswordField(
                "PASSWORD",
                "Create a password"
        );

        PasswordField password =
                (PasswordField) passwordBox.getProperties()
                        .get("field");

        VBox confirmBox = createPasswordField(
                "CONFIRM PASSWORD",
                "Confirm your password"
        );

        PasswordField confirmPassword =
                (PasswordField) confirmBox.getProperties()
                        .get("field");

        // =====================================================
        // REGISTER BUTTON
        // =====================================================

        Button registerButton =
                new Button("CREATE ACCOUNT");

        registerButton.setMaxWidth(
                Double.MAX_VALUE
        );

        registerButton.setPrefHeight(48);

        registerButton.setCursor(
                Cursor.HAND
        );

        registerButton.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        14
                )
        );

        setRegisterButtonStyle(
                registerButton,
                false
        );

        registerButton.setOnMouseEntered(e ->
                setRegisterButtonStyle(
                        registerButton,
                        true
                )
        );

        registerButton.setOnMouseExited(e ->
                setRegisterButtonStyle(
                        registerButton,
                        false
                )
        );

        // =====================================================
        // LOGIN LINK
        // =====================================================

        HBox loginBox = new HBox(5);

        loginBox.setAlignment(
                Pos.CENTER
        );

        Label already =
                new Label(
                        "Already have an account?"
                );

        already.setTextFill(
                Color.web(MUTED)
        );

        already.setFont(
                Font.font(
                        "Segoe UI",
                        12
                )
        );

        Hyperlink login =
                new Hyperlink("Sign In");

        login.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        12
                )
        );

        login.setTextFill(
                Color.web(BLUE_LIGHT)
        );

        login.setStyle(
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;"
        );

        login.setOnMouseEntered(e ->
                login.setTextFill(
                        Color.web("#9CC6EA")
                )
        );

        login.setOnMouseExited(e ->
                login.setTextFill(
                        Color.web(BLUE_LIGHT)
                )
        );

        loginBox.getChildren().addAll(
                already,
                login
        );

        // =====================================================
        // CARD CONTENT
        // =====================================================

        formCard.getChildren().addAll(
                cardHeader,
                createSpacing(4),

                nameBox,
                emailBox,
                mobileBox,
                passwordBox,
                confirmBox,

                createSpacing(3),

                registerButton,

                createDivider(),

                loginBox
        );

        // =====================================================
        // FOOTER
        // =====================================================

        Label footer = new Label(
                "© 2026 AdmitX  •  Secure Registration"
        );

        footer.setFont(
                Font.font(
                        "Segoe UI",
                        10
                )
        );

        footer.setTextFill(
                Color.web(
                        MUTED,
                        0.65
                )
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        main.getChildren().addAll(
                topBar,
                brand,
                icon,
                title,
                subtitle,
                formCard,
                footer
        );

        // =====================================================
        // ROOT
        // =====================================================

        root.getChildren().addAll(
                glowTop,
                main
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        backButton.setOnAction(e ->
                Navigation.goTo(
                        WelcomePage.getScene()
                )
        );

        login.setOnAction(e ->
                Navigation.goTo(
                        StudentLoginPage.getScene()
                )
        );

        registerButton.setOnAction(e -> {

            String nameValue =
                    name.getText().trim();

            String emailValue =
                    email.getText().trim();

            String mobileValue =
                    mobile.getText().trim();

            String passwordValue =
                    password.getText();

            String confirmValue =
                    confirmPassword.getText();

            if (nameValue.isEmpty()
                    || emailValue.isEmpty()
                    || mobileValue.isEmpty()
                    || passwordValue.isEmpty()
                    || confirmValue.isEmpty()) {

                System.out.println(
                        "Please fill all fields"
                );

                return;
            }

            if (!passwordValue.equals(confirmValue)) {

                System.out.println(
                        "Passwords do not match"
                );

                return;
            }

            System.out.println(
                    "Registration successful"
            );

            Navigation.goTo(
                    StudentLoginPage.getScene()
            );
        });

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root,
                        900,
                        750
                );

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
    // TEXT FIELD CREATOR
    // =========================================================

    private static VBox createTextField(
            String labelText,
            String prompt
    ) {

        VBox box =
                new VBox(6);

        Label label =
                new Label(labelText);

        label.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        label.setTextFill(
                Color.web("#8FA3BA")
        );

        TextField field =
                new TextField();

        field.setPromptText(prompt);

        field.setPrefHeight(45);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setFont(
                Font.font(
                        "Segoe UI",
                        13
                )
        );

        setFieldStyle(
                field,
                false
        );

        field.focusedProperty().addListener(
                (obs, oldValue, focused) ->
                        setFieldStyle(
                                field,
                                focused
                        )
        );

        box.getChildren().addAll(
                label,
                field
        );

        box.getProperties().put(
                "field",
                field
        );

        return box;
    }

    // =========================================================
    // PASSWORD FIELD CREATOR
    // =========================================================

    private static VBox createPasswordField(
            String labelText,
            String prompt
    ) {

        VBox box =
                new VBox(6);

        Label label =
                new Label(labelText);

        label.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        label.setTextFill(
                Color.web("#8FA3BA")
        );

        PasswordField field =
                new PasswordField();

        field.setPromptText(prompt);

        field.setPrefHeight(45);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setFont(
                Font.font(
                        "Segoe UI",
                        13
                )
        );

        setFieldStyle(
                field,
                false
        );

        field.focusedProperty().addListener(
                (obs, oldValue, focused) ->
                        setFieldStyle(
                                field,
                                focused
                        )
        );

        box.getChildren().addAll(
                label,
                field
        );

        box.getProperties().put(
                "field",
                field
        );

        return box;
    }

    // =========================================================
    // FIELD STYLE
    // =========================================================

    private static void setFieldStyle(
            TextField field,
            boolean focused
    ) {

        String border =
                focused
                        ? BLUE
                        : BORDER;

        String glow =
                focused
                        ? "-fx-effect: dropshadow(" +
                          "gaussian, rgba(74,127,181,0.35)," +
                          "12,0,0,0);"
                        : "";

        field.setStyle(
                "-fx-background-color: " + INPUT + ";" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: #62748A;" +
                "-fx-background-radius: 9;" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 9;" +
                "-fx-border-width: " +
                (focused ? "1.5" : "1") + ";" +
                "-fx-padding: 0 14 0 14;" +
                "-fx-font-size: 13px;" +
                glow
        );
    }

    // =========================================================
    // REGISTER BUTTON STYLE
    // =========================================================

    private static void setRegisterButtonStyle(
            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(
                    "-fx-background-color: #5F94C7;" +
                    "-fx-text-fill: #FFFFFF;" +
                    "-fx-background-radius: 10;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-effect: dropshadow(" +
                    "gaussian, rgba(74,127,181,0.55)," +
                    "20,0,0,5);"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: #1E3A5F;" +
                    "-fx-text-fill: #E8EDF5;" +
                    "-fx-background-radius: 10;" +
                    "-fx-font-size: 14px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: #365D83;" +
                    "-fx-border-radius: 10;" +
                    "-fx-border-width: 1;" +
                    "-fx-effect: dropshadow(" +
                    "gaussian, rgba(30,58,95,0.4)," +
                    "14,0,0,4);"
            );
        }
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

        Region left =
                new Region();

        Region right =
                new Region();

        HBox.setHgrow(
                left,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                right,
                Priority.ALWAYS
        );

        left.setPrefHeight(1);
        right.setPrefHeight(1);

        left.setStyle(
                "-fx-background-color: #26364B;"
        );

        right.setStyle(
                "-fx-background-color: #26364B;"
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
                Color.web("#62748A")
        );

        divider.getChildren().addAll(
                left,
                or,
                right
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

        region.setMinHeight(height);

        region.setPrefHeight(height);

        return region;
    }
}