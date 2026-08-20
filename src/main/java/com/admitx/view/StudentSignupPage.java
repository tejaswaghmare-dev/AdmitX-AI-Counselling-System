package com.admitx.view;

import com.admitx.controller.AuthController;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class StudentSignupPage {

    // =========================================================
    // COLOR PALETTE
    // =========================================================

    private static final String BG = "#040505";
    private static final String CARD = "#101211";
    private static final String INPUT = "#0A0C0B";

    private static final String LIME = "#C6E92F";
    private static final String LIME_MEDIUM = "#8AA30B";
    private static final String LIME_DARK = "#5E7107";

    private static final String WHITE = "#F7F7F5";
    private static final String SECONDARY = "#9A9D91";
    private static final String MUTED = "#62665D";
    private static final String BORDER = "#292D27";


    // =========================================================
    // GET SCENE
    // =========================================================

    public static Scene getScene() {

        // =====================================================
        // ROOT
        // =====================================================

        StackPane root = new StackPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );


        // =====================================================
        // BACKGROUND GRADIENT
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
                                        0,
                                        Color.web("#040505")
                                ),

                                new Stop(
                                        0.45,
                                        Color.web("#070908")
                                ),

                                new Stop(
                                        1,
                                        Color.web("#101209")
                                )
                        ),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                );

        root.setBackground(
                new Background(backgroundFill)
        );


        // =====================================================
        // LIME GLOW
        // =====================================================

        Circle glow =
                new Circle(180);

        glow.setFill(
                Color.web(
                        LIME,
                        0.035
                )
        );

        glow.setEffect(
                new DropShadow(
                        100,
                        Color.web(
                                LIME,
                                0.15
                        )
                )
        );

        StackPane.setAlignment(
                glow,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                glow,
                new Insets(
                        -100,
                        -100,
                        0,
                        0
                )
        );


        // =====================================================
        // MAIN CONTAINER
        // =====================================================

        VBox mainContainer =
                new VBox(14);

        mainContainer.setAlignment(
                Pos.CENTER
        );

        mainContainer.setPadding(
                new Insets(
                        25,
                        30,
                        20,
                        30
                )
        );


        // =====================================================
        // BRAND
        // =====================================================

        Label brand =
                new Label("ADMITX");

        brand.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        16
                )
        );

        brand.setTextFill(
                Color.web(LIME)
        );

        brand.setStyle(
                "-fx-letter-spacing: 4px;"
        );


        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "CREATE ACCOUNT"
                );

        title.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        28
                )
        );

        title.setTextFill(
                Color.web(WHITE)
        );


        // =====================================================
        // SUBTITLE
        // =====================================================

        Label subtitle =
                new Label(
                        "Create your student account to get started"
                );

        subtitle.setFont(
                Font.font(
                        "Segoe UI",
                        12
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY)
        );


        // =====================================================
        // FORM CARD
        // =====================================================

        VBox formCard =
                new VBox(12);

        formCard.setAlignment(
                Pos.TOP_CENTER
        );

        formCard.setPadding(
                new Insets(
                        25,
                        32,
                        25,
                        32
                )
        );

        formCard.setPrefWidth(400);

        formCard.setMaxWidth(400);

        formCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );

        formCard.setEffect(
                new DropShadow(
                        35,
                        Color.color(
                                0,
                                0,
                                0,
                                0.65
                        )
                )
        );


        // =====================================================
        // FORM HEADER
        // =====================================================

        VBox header =
                new VBox(3);

        header.setAlignment(
                Pos.CENTER_LEFT
        );


        Label formTitle =
                new Label(
                        "Student Registration"
                );

        formTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        17
                )
        );

        formTitle.setTextFill(
                Color.web(WHITE)
        );


        Label formSubtitle =
                new Label(
                        "Enter your details below"
                );

        formSubtitle.setFont(
                Font.font(
                        "Segoe UI",
                        11
                )
        );

        formSubtitle.setTextFill(
                Color.web(MUTED)
        );


        header.getChildren().addAll(
                formTitle,
                formSubtitle
        );


        // =====================================================
        // NAME FIELD
        // =====================================================

        VBox nameBox =
                createFieldContainer(
                        "FULL NAME",
                        "Enter your full name"
                );

        TextField nameField =
                (TextField)
                        nameBox
                                .getProperties()
                                .get("field");


        // =====================================================
        // EMAIL FIELD
        // =====================================================

        VBox emailBox =
                createFieldContainer(
                        "EMAIL ADDRESS",
                        "Enter your email address"
                );

        TextField emailField =
                (TextField)
                        emailBox
                                .getProperties()
                                .get("field");


        // =====================================================
        // PASSWORD FIELD
        // =====================================================

        VBox passwordBox =
                createPasswordContainer();

        PasswordField passwordField =
                (PasswordField)
                        passwordBox
                                .getProperties()
                                .get("field");


        // =====================================================
        // PASSWORD HINT
        // =====================================================

        Label passwordHint =
                new Label(
                        "Minimum 6 characters"
                );

        passwordHint.setFont(
                Font.font(
                        "Segoe UI",
                        9
                )
        );

        passwordHint.setTextFill(
                Color.web(MUTED)
        );


        // =====================================================
        // SIGN UP BUTTON
        // =====================================================

        Button signupButton =
                new Button(
                        "CREATE ACCOUNT"
                );

        signupButton.setMaxWidth(
                Double.MAX_VALUE
        );

        signupButton.setPrefHeight(
                46
        );

        signupButton.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        13
                )
        );

        signupButton.setCursor(
                Cursor.HAND
        );

        applySignupStyle(
                signupButton,
                false
        );


        signupButton.setOnMouseEntered(
                e ->
                        applySignupStyle(
                                signupButton,
                                true
                        )
        );


        signupButton.setOnMouseExited(
                e ->
                        applySignupStyle(
                                signupButton,
                                false
                        )
        );


        // =====================================================
        // DIVIDER
        // =====================================================

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
                "-fx-background-color: " +
                BORDER + ";"
        );

        line2.setStyle(
                "-fx-background-color: " +
                BORDER + ";"
        );


        Label orLabel =
                new Label("OR");

        orLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        9
                )
        );

        orLabel.setTextFill(
                Color.web(MUTED)
        );


        divider.getChildren().addAll(
                line1,
                orLabel,
                line2
        );


        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton =
                new Button(
                        "←  BACK TO LOGIN"
                );

        backButton.setMaxWidth(
                Double.MAX_VALUE
        );

        backButton.setPrefHeight(
                40
        );

        backButton.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        backButton.setCursor(
                Cursor.HAND
        );


        applyBackStyle(
                backButton,
                false
        );


        backButton.setOnMouseEntered(
                e ->
                        applyBackStyle(
                                backButton,
                                true
                        )
        );


        backButton.setOnMouseExited(
                e ->
                        applyBackStyle(
                                backButton,
                                false
                        )
        );


        // =====================================================
        // AUTH CONTROLLER
        // =====================================================

        AuthController controller =
                new AuthController();


        // =====================================================
        // SIGN UP ACTION
        // =====================================================

        signupButton.setOnAction(
                e -> {

                    String name =
                            nameField
                                    .getText()
                                    .trim();

                    String email =
                            emailField
                                    .getText()
                                    .trim();

                    String password =
                            passwordField
                                    .getText();


                    // -----------------------------------------
                    // NAME VALIDATION
                    // -----------------------------------------

                    if (name.isEmpty()) {

                        showError(
                                nameField
                        );

                        return;
                    }


                    // -----------------------------------------
                    // EMAIL VALIDATION
                    // -----------------------------------------

                    if (email.isEmpty()) {

                        showError(
                                emailField
                        );

                        return;
                    }


                    // -----------------------------------------
                    // PASSWORD VALIDATION
                    // -----------------------------------------

                    if (password.isEmpty()) {

                        showError(
                                passwordField
                        );

                        return;
                    }


                    if (password.length() < 6) {

                        showError(
                                passwordField
                        );

                        return;
                    }


                    // -----------------------------------------
                    // SIGNUP
                    // -----------------------------------------

                    boolean success =
                            controller.signUp(
                                    email,
                                    password
                            );


                    if (success) {

                        System.out.println(
                                "Successfully signed up"
                        );

                        /*
                         * After successful signup:
                         *
                         * Navigation.goTo(
                         *     StudentLoginPage.getScene()
                         * );
                         */

                    } else {

                        System.out.println(
                                "Signup unsuccessful"
                        );
                    }
                }
        );


        // =====================================================
        // BACK ACTION
        // =====================================================

        backButton.setOnAction(
                e ->
                        Navigation.goTo(
                                StudentLoginPage.getScene()
                        )
        );


        // =====================================================
        // CARD CONTENT
        // =====================================================

        formCard.getChildren().addAll(

                header,

                nameBox,

                emailBox,

                passwordBox,

                passwordHint,

                signupButton,

                divider,

                backButton
        );


        // =====================================================
        // FOOTER
        // =====================================================

        Label footer =
                new Label(
                        "© 2026 AdmitX  •  Secure Registration"
                );

        footer.setFont(
                Font.font(
                        "Segoe UI",
                        9
                )
        );

        footer.setTextFill(
                Color.web(
                        SECONDARY,
                        0.45
                )
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        mainContainer.getChildren().addAll(

                brand,

                title,

                subtitle,

                formCard,

                footer
        );


        // =====================================================
        // ROOT CONTENT
        // =====================================================

        root.getChildren().addAll(
                glow,
                mainContainer
        );


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root,
                        900,
                        700
                );


        // =====================================================
        // FADE ANIMATION
        // =====================================================

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(600),
                        root
                );

        fade.setFromValue(0);

        fade.setToValue(1);

        fade.play();


        return scene;
    }


    // =========================================================
    // TEXT FIELD CONTAINER
    // =========================================================

    private static VBox createFieldContainer(
            String labelText,
            String prompt
    ) {

        VBox container =
                new VBox(6);


        Label label =
                new Label(
                        labelText
                );

        label.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        label.setTextFill(
                Color.web(SECONDARY)
        );


        TextField field =
                new TextField();

        field.setPromptText(
                prompt
        );

        field.setPrefHeight(
                44
        );

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setFont(
                Font.font(
                        "Segoe UI",
                        13
                )
        );


        applyFieldStyle(
                field,
                false
        );


        field.focusedProperty()
                .addListener(
                        (obs, oldValue, focused) ->

                                applyFieldStyle(
                                        field,
                                        focused
                                )
                );


        container.getChildren().addAll(
                label,
                field
        );


        container.getProperties().put(
                "field",
                field
        );


        return container;
    }


    // =========================================================
    // PASSWORD CONTAINER
    // =========================================================

    private static VBox createPasswordContainer() {

        VBox container =
                new VBox(6);


        Label label =
                new Label(
                        "PASSWORD"
                );

        label.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        label.setTextFill(
                Color.web(SECONDARY)
        );


        PasswordField field =
                new PasswordField();

        field.setPromptText(
                "Create a password"
        );

        field.setPrefHeight(
                44
        );

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setFont(
                Font.font(
                        "Segoe UI",
                        13
                )
        );


        applyFieldStyle(
                field,
                false
        );


        field.focusedProperty()
                .addListener(
                        (obs, oldValue, focused) ->

                                applyFieldStyle(
                                        field,
                                        focused
                                )
                );


        container.getChildren().addAll(
                label,
                field
        );


        container.getProperties().put(
                "field",
                field
        );


        return container;
    }


    // =========================================================
    // FIELD STYLE
    // =========================================================

    private static void applyFieldStyle(
            TextField field,
            boolean focused
    ) {

        String borderColor =
                focused
                        ? LIME
                        : BORDER;


        String shadow =
                focused

                        ? "-fx-effect: dropshadow(" +
                          "gaussian, rgba(198,233,47,0.18), " +
                          "12, 0, 0, 0);"

                        : "";


        field.setStyle(

                "-fx-background-color: " +
                INPUT + ";" +

                "-fx-text-fill: " +
                WHITE + ";" +

                "-fx-prompt-text-fill: " +
                "#666A61;" +

                "-fx-background-radius: 9;" +

                "-fx-border-color: " +
                borderColor + ";" +

                "-fx-border-radius: 9;" +

                "-fx-border-width: " +
                (focused ? "1.5" : "1") + ";" +

                "-fx-padding: 0 14 0 14;" +

                "-fx-font-size: 13px;" +

                shadow
        );
    }


    // =========================================================
    // SIGNUP BUTTON STYLE
    // =========================================================

    private static void applySignupStyle(
            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(

                    "-fx-background-color: " +
                    LIME + ";" +

                    "-fx-text-fill: #050505;" +

                    "-fx-background-radius: 10;" +

                    "-fx-font-weight: bold;" +

                    "-fx-font-size: 13px;" +

                    "-fx-effect: dropshadow(" +
                    "gaussian, rgba(198,233,47,0.45), " +
                    "22, 0, 0, 5);"
            );

        } else {

            button.setStyle(

                    "-fx-background-color: " +
                    LIME_MEDIUM + ";" +

                    "-fx-text-fill: #FFFFFF;" +

                    "-fx-background-radius: 10;" +

                    "-fx-font-weight: bold;" +

                    "-fx-font-size: 13px;" +

                    "-fx-effect: dropshadow(" +
                    "gaussian, rgba(138,163,11,0.25), " +
                    "15, 0, 0, 4);"
            );
        }
    }


    // =========================================================
    // BACK BUTTON STYLE
    // =========================================================

    private static void applyBackStyle(
            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(

                    "-fx-background-color: " +
                    "rgba(198,233,47,0.06);" +

                    "-fx-text-fill: " +
                    LIME + ";" +

                    "-fx-background-radius: 9;" +

                    "-fx-border-color: " +
                    LIME_DARK + ";" +

                    "-fx-border-radius: 9;" +

                    "-fx-border-width: 1;" +

                    "-fx-font-weight: bold;" +

                    "-fx-font-size: 11px;"
            );

        } else {

            button.setStyle(

                    "-fx-background-color: transparent;" +

                    "-fx-text-fill: " +
                    SECONDARY + ";" +

                    "-fx-background-radius: 9;" +

                    "-fx-border-color: " +
                    BORDER + ";" +

                    "-fx-border-radius: 9;" +

                    "-fx-border-width: 1;" +

                    "-fx-font-weight: bold;" +

                    "-fx-font-size: 11px;"
            );
        }
    }


    // =========================================================
    // ERROR STYLE
    // =========================================================

    private static void showError(
            TextField field
    ) {

        field.setStyle(

                "-fx-background-color: " +
                INPUT + ";" +

                "-fx-text-fill: " +
                WHITE + ";" +

                "-fx-prompt-text-fill: #666A61;" +

                "-fx-background-radius: 9;" +

                "-fx-border-color: #8A3D34;" +

                "-fx-border-radius: 9;" +

                "-fx-border-width: 1.5;" +

                "-fx-padding: 0 14 0 14;" +

                "-fx-font-size: 13px;"
        );
    }
}