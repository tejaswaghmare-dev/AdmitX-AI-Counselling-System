package com.admitx.view;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;

import javafx.scene.effect.DropShadow;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.util.Duration;

import java.net.URL;
import java.util.Random;


public class AdmitXWelcomePage {

    private static final Random random = new Random();

    private static final Color NEON_GREEN =
            Color.web("#C8FF00");

    private static final Color CYAN =
            Color.web("#39BFFF");

    private static final Color ORANGE =
            Color.web("#FF9D00");


    // =============================================================
    // GET SCENE
    // =============================================================

    public static Scene getScene() {


        // =========================================================
        // ROOT
        // =========================================================

        StackPane root = new StackPane();

        root.setStyle(
                "-fx-background-color: #010403;"
        );


        // =========================================================
        // ANIMATED BACKGROUND
        // =========================================================

        Canvas background = new Canvas();

        createAnimatedBackground(background);


        // =========================================================
        // MAIN CONTENT
        // =========================================================

        VBox content = new VBox();

        content.setAlignment(Pos.CENTER);

        content.setSpacing(18);

        content.setPickOnBounds(false);


        // =========================================================
        // ADMITX LOGO
        // =========================================================

        ImageView admitXLogo =
                createImageView(
                        "/assets/images/admitx-logo.png"
                );

        admitXLogo.setPreserveRatio(true);

        // Visual correction
        admitXLogo.setTranslateY(8);


        DropShadow admitGlow =
                new DropShadow();

        admitGlow.setColor(
                Color.rgb(
                        190,
                        255,
                        0,
                        0.45
                )
        );

        admitGlow.setRadius(25);

        admitXLogo.setEffect(admitGlow);


        // =========================================================
        // SUPER-X LOGO
        // =========================================================

        ImageView superXLogo =
                createImageView(
                        "/assets/images/superx-logo.png"
                );

        superXLogo.setPreserveRatio(true);

        // Visual correction
        superXLogo.setTranslateY(-5);


        DropShadow superGlow =
                new DropShadow();

        superGlow.setColor(
                Color.rgb(
                        40,
                        170,
                        255,
                        0.40
                )
        );

        superGlow.setRadius(25);

        superXLogo.setEffect(superGlow);


        // =========================================================
        // ADMITX CONTAINER
        // =========================================================

        StackPane admitContainer =
                new StackPane();

        admitContainer.setPrefSize(
                520,
                280
        );

        admitContainer.setMinSize(
                520,
                280
        );

        admitContainer.setAlignment(Pos.CENTER);

        admitContainer.setOpacity(0);

        admitContainer.setTranslateX(-160);

        admitContainer.getChildren().add(
                admitXLogo
        );


        // =========================================================
        // SUPER-X CONTAINER
        // =========================================================

        StackPane superContainer =
                new StackPane();

        superContainer.setPrefSize(
                520,
                280
        );

        superContainer.setMinSize(
                520,
                280
        );

        superContainer.setAlignment(Pos.CENTER);

        superContainer.setOpacity(0);

        superContainer.setTranslateX(160);

        superContainer.getChildren().add(
                superXLogo
        );


        // =========================================================
        // COLLABORATION X
        // LEFT = PALE YELLOW
        // RIGHT = BLUE
        // =========================================================

        Text collaborationX =
                new Text("X");


        collaborationX.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        150
                )
        );


        collaborationX.setFill(

                new LinearGradient(

                        0,
                        0,

                        1,
                        0,

                        true,

                        CycleMethod.NO_CYCLE,


                        // LEFT SIDE
                        new Stop(
                                0.0,
                                Color.web("#F5FF9A")
                        ),

                        new Stop(
                                0.25,
                                Color.web("#E2FF45")
                        ),

                        new Stop(
                                0.48,
                                Color.web("#C8FF00")
                        ),


                        // CENTER BLEND
                        new Stop(
                                0.52,
                                Color.web("#A8EFC0")
                        ),


                        // RIGHT SIDE
                        new Stop(
                                0.65,
                                Color.web("#65D5FF")
                        ),

                        new Stop(
                                0.82,
                                Color.web("#249FFF")
                        ),

                        new Stop(
                                1.0,
                                Color.web("#8BDFFF")
                        )
                )
        );


        collaborationX.setOpacity(0);

        collaborationX.setScaleX(0.15);

        collaborationX.setScaleY(0.15);

        collaborationX.setRotate(0);


        // =========================================================
        // X GLOW
        // =========================================================

        DropShadow xGlow1 =
                new DropShadow();

        xGlow1.setColor(
                Color.rgb(
                        210,
                        255,
                        80,
                        0.75
                )
        );

        xGlow1.setRadius(30);

        xGlow1.setSpread(0.12);


        DropShadow xGlow2 =
                new DropShadow();

        xGlow2.setColor(
                Color.rgb(
                        50,
                        180,
                        255,
                        0.38
                )
        );

        xGlow2.setRadius(60);

        xGlow2.setInput(xGlow1);

        collaborationX.setEffect(xGlow2);


        // =========================================================
        // X CONTAINER
        // =========================================================

        StackPane xContainer =
                new StackPane();

        xContainer.setPrefSize(
                220,
                250
        );

        xContainer.setMinSize(
                220,
                250
        );

        xContainer.setAlignment(Pos.CENTER);

        xContainer.getChildren().add(
                collaborationX
        );


        // =========================================================
        // MAIN COLLABORATION ROW
        // =========================================================

        HBox collaborationBox =
                new HBox();

        collaborationBox.setAlignment(
                Pos.CENTER
        );

        collaborationBox.setSpacing(0);

        collaborationBox.getChildren().addAll(

                admitContainer,

                xContainer,

                superContainer
        );


        // =========================================================
        // ENERGY DIVIDER
        // =========================================================

        Rectangle leftLine =
                new Rectangle();

        leftLine.setWidth(300);

        leftLine.setHeight(2);

        leftLine.setFill(

                new LinearGradient(

                        0,
                        0,

                        1,
                        0,

                        true,

                        CycleMethod.NO_CYCLE,

                        new Stop(
                                0,
                                Color.TRANSPARENT
                        ),

                        new Stop(
                                1,
                                NEON_GREEN
                        )
                )
        );


        Rectangle centerLine =
                new Rectangle();

        centerLine.setWidth(170);

        centerLine.setHeight(3);

        centerLine.setFill(

                new LinearGradient(

                        0,
                        0,

                        1,
                        0,

                        true,

                        CycleMethod.NO_CYCLE,

                        new Stop(
                                0,
                                Color.web("#E8FF60")
                        ),

                        new Stop(
                                0.5,
                                Color.web("#C8FF00")
                        ),

                        new Stop(
                                1,
                                Color.web("#6FD8FF")
                        )
                )
        );


        DropShadow lineGlow =
                new DropShadow();

        lineGlow.setColor(
                Color.rgb(
                        180,
                        255,
                        0,
                        0.75
                )
        );

        lineGlow.setRadius(15);

        centerLine.setEffect(lineGlow);


        Rectangle rightLine =
                new Rectangle();

        rightLine.setWidth(300);

        rightLine.setHeight(2);

        rightLine.setFill(

                new LinearGradient(

                        0,
                        0,

                        1,
                        0,

                        true,

                        CycleMethod.NO_CYCLE,

                        new Stop(
                                0,
                                CYAN
                        ),

                        new Stop(
                                1,
                                Color.TRANSPARENT
                        )
                )
        );


        HBox energyDivider =
                new HBox();

        energyDivider.setAlignment(
                Pos.CENTER
        );

        energyDivider.setSpacing(10);

        energyDivider.setOpacity(0);

        energyDivider.setScaleX(0.2);

        energyDivider.getChildren().addAll(

                leftLine,

                centerLine,

                rightLine
        );


        // =========================================================
        // ORIGINAL SUBTITLE
        // =========================================================

        Text subtitle =
                new Text(
                        "Your intelligent admission journey starts here"
                );


        subtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        20
                )
        );


        subtitle.setFill(
                new LinearGradient(
                        0, 0,
                        1, 0,
                        true,
                        CycleMethod.NO_CYCLE,

                        new Stop(0.0, Color.web("#F3FF8A")),
                        new Stop(0.30, Color.web("#DFFF3F")),
                        new Stop(0.48, Color.web("#C8FF00")),

                        new Stop(0.65, Color.web("#65D5FF")),
                        new Stop(0.82, Color.web("#249FFF")),
                        new Stop(1.0, Color.web("#8BDFFF"))
                )
        );


        subtitle.setOpacity(0);

        subtitle.setTranslateY(15);


        // =========================================================
        // CONTINUE BUTTON
        // =========================================================

        Button continueButton =
                new Button(
                        "CONTINUE   →"
                );


        continueButton.setPrefWidth(280);

        continueButton.setPrefHeight(60);

        continueButton.setOpacity(0);

        continueButton.setTranslateY(20);


        continueButton.setStyle(

                "-fx-background-color: "
                        + "linear-gradient(to right, "
                        + "#F3FF8A 0%, "
                        + "#DFFF3F 30%, "
                        + "#C8FF00 48%, "
                        + "#65D5FF 65%, "
                        + "#249FFF 100%);"

                + "-fx-text-fill: #050505;"

                + "-fx-font-size: 17px;"

                + "-fx-font-weight: bold;"

                + "-fx-background-radius: 30;"

                + "-fx-cursor: hand;"
        );


        DropShadow buttonGlow =
                new DropShadow();

        buttonGlow.setColor(
                Color.rgb(
                        190,
                        255,
                        0,
                        0.60
                )
        );

        buttonGlow.setRadius(28);

        buttonGlow.setSpread(0.12);

        continueButton.setEffect(
                buttonGlow
        );


        // =========================================================
        // BUTTON HOVER
        // =========================================================

        continueButton.setOnMouseEntered(e -> {

            ScaleTransition scale =
                    new ScaleTransition(
                            Duration.millis(180),
                            continueButton
                    );

            scale.setToX(1.05);

            scale.setToY(1.05);

            scale.play();
        });


        continueButton.setOnMouseExited(e -> {

            ScaleTransition scale =
                    new ScaleTransition(
                            Duration.millis(180),
                            continueButton
                    );

            scale.setToX(1);

            scale.setToY(1);

            scale.play();
        });


        // =========================================================
        // BUTTON ACTION
        // =========================================================

        continueButton.setOnAction(e -> {

            Navigation.goTo(
                    WelcomePage.getScene()
            );

        });


        // =========================================================
        // ADD CONTENT
        // =========================================================

        content.getChildren().addAll(

                collaborationBox,

                energyDivider,

                subtitle,

                continueButton
        );


        // =========================================================
        // ADD TO ROOT
        // =========================================================

        root.getChildren().addAll(

                background,

                content
        );


        // =========================================================
        // CREATE SCENE
        // =========================================================

        Scene scene =
                new Scene(
                        root,
                        1400,
                        850
                );


        // =========================================================
        // RESPONSIVE BACKGROUND
        // =========================================================

        background.widthProperty().bind(
                scene.widthProperty()
        );

        background.heightProperty().bind(
                scene.heightProperty()
        );


        // =========================================================
        // RESPONSIVE LOGOS
        // =========================================================

        admitXLogo.fitWidthProperty().bind(
                scene.widthProperty()
                        .multiply(0.40)
        );


        superXLogo.fitWidthProperty().bind(
                scene.widthProperty()
                        .multiply(0.40)
        );


        // =========================================================
        // START INTRO
        // =========================================================

        playIntro(

                admitContainer,

                collaborationX,

                superContainer,

                energyDivider,

                subtitle,

                continueButton
        );


        return scene;
    }


    // =============================================================
    // IMAGE LOADER
    // =============================================================

    private static ImageView createImageView(
            String path
    ) {

        URL resource =
                AdmitXWelcomePage.class
                        .getResource(path);


        if (resource == null) {

            System.out.println(
                    "IMAGE NOT FOUND: " + path
            );

            return new ImageView();
        }


        Image image =
                new Image(
                        resource.toExternalForm()
                );


        return new ImageView(image);
    }


    // =============================================================
    // INTRO ANIMATION
    // =============================================================

    private static void playIntro(

            StackPane admitContainer,

            Text collaborationX,

            StackPane superContainer,

            HBox energyDivider,

            Text subtitle,

            Button continueButton
    ) {


        // =========================================================
        // INITIAL PAUSE
        // =========================================================

        PauseTransition firstPause =
                new PauseTransition(
                        Duration.seconds(0.5)
                );


        // =========================================================
        // ADMITX ENTERS FROM LEFT
        // =========================================================

        FadeTransition admitFade =
                new FadeTransition(
                        Duration.seconds(1.1),
                        admitContainer
                );

        admitFade.setToValue(1);


        TranslateTransition admitMove =
                new TranslateTransition(
                        Duration.seconds(1.1),
                        admitContainer
                );

        admitMove.setToX(0);


        ParallelTransition admitAnimation =
                new ParallelTransition(

                        admitFade,

                        admitMove
                );


        // =========================================================
        // SUPER-X ENTERS FROM RIGHT
        // =========================================================

        FadeTransition superFade =
                new FadeTransition(
                        Duration.seconds(1.1),
                        superContainer
                );

        superFade.setToValue(1);


        TranslateTransition superMove =
                new TranslateTransition(
                        Duration.seconds(1.1),
                        superContainer
                );

        superMove.setToX(0);


        ParallelTransition superAnimation =
                new ParallelTransition(

                        superFade,

                        superMove
                );


        ParallelTransition brandsEnter =
                new ParallelTransition(

                        admitAnimation,

                        superAnimation
                );


        // =========================================================
        // CENTER X APPEARS
        // =========================================================

        FadeTransition xFade =
                new FadeTransition(
                        Duration.millis(550),
                        collaborationX
                );

        xFade.setToValue(1);


        ScaleTransition xScale =
                new ScaleTransition(
                        Duration.millis(750),
                        collaborationX
                );

        xScale.setToX(1);

        xScale.setToY(1);


        ParallelTransition xAnimation =
                new ParallelTransition(

                        xFade,

                        xScale
                );


        // =========================================================
        // COMPLETE INTRO
        // =========================================================

        SequentialTransition sequence =
                new SequentialTransition(

                        firstPause,

                        brandsEnter,

                        xAnimation
                );


        sequence.setOnFinished(e -> {

            animateDivider(

                    energyDivider,

                    subtitle,

                    continueButton,

                    collaborationX,

                    admitContainer,

                    superContainer
            );

        });


        sequence.play();
    }


    // =============================================================
    // DIVIDER ANIMATION
    // =============================================================

    private static void animateDivider(

            HBox energyDivider,

            Text subtitle,

            Button continueButton,

            Text collaborationX,

            StackPane admitContainer,

            StackPane superContainer
    ) {


        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(500),
                        energyDivider
                );

        fade.setToValue(1);


        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(700),
                        energyDivider
                );

        scale.setToX(1);


        ParallelTransition animation =
                new ParallelTransition(

                        fade,

                        scale
                );


        animation.setOnFinished(e -> {

            animateSubtitle(

                    subtitle,

                    continueButton,

                    collaborationX,

                    admitContainer,

                    superContainer
            );

        });


        animation.play();
    }


    // =============================================================
    // SUBTITLE ANIMATION
    // =============================================================

    private static void animateSubtitle(

            Text subtitle,

            Button continueButton,

            Text collaborationX,

            StackPane admitContainer,

            StackPane superContainer
    ) {


        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(650),
                        subtitle
                );

        fade.setToValue(1);


        TranslateTransition move =
                new TranslateTransition(
                        Duration.millis(650),
                        subtitle
                );

        move.setToY(0);


        ParallelTransition animation =
                new ParallelTransition(

                        fade,

                        move
                );


        animation.setOnFinished(e -> {

            animateButton(
                    continueButton
            );

            startXPulse(
                    collaborationX
            );

            startContainerFloating(
                    admitContainer,
                    superContainer
            );

        });


        animation.play();
    }


    // =============================================================
    // BUTTON ANIMATION
    // =============================================================

    private static void animateButton(
            Button button
    ) {


        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(700),
                        button
                );

        fade.setToValue(1);


        TranslateTransition move =
                new TranslateTransition(
                        Duration.millis(700),
                        button
                );

        move.setToY(0);


        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(700),
                        button
                );

        scale.setFromX(0.85);

        scale.setFromY(0.85);

        scale.setToX(1);

        scale.setToY(1);


        ParallelTransition animation =
                new ParallelTransition(

                        fade,

                        move,

                        scale
                );


        animation.play();
    }


    // =============================================================
    // X PULSE
    // =============================================================

    private static void startXPulse(
            Text collaborationX
    ) {


        ScaleTransition pulse =
                new ScaleTransition(
                        Duration.seconds(1.8),
                        collaborationX
                );


        pulse.setFromX(1);

        pulse.setFromY(1);

        pulse.setToX(1.06);

        pulse.setToY(1.06);

        pulse.setAutoReverse(true);

        pulse.setCycleCount(
                Animation.INDEFINITE
        );

        pulse.play();
    }


    // =============================================================
    // FLOATING BRAND CONTAINERS
    // =============================================================

    private static void startContainerFloating(

            StackPane admitContainer,

            StackPane superContainer
    ) {


        TranslateTransition admitFloat =
                new TranslateTransition(
                        Duration.seconds(3.2),
                        admitContainer
                );


        admitFloat.setFromY(0);

        admitFloat.setToY(-6);

        admitFloat.setAutoReverse(true);

        admitFloat.setCycleCount(
                Animation.INDEFINITE
        );

        admitFloat.play();


        TranslateTransition superFloat =
                new TranslateTransition(
                        Duration.seconds(3.5),
                        superContainer
                );


        superFloat.setFromY(0);

        superFloat.setToY(-6);

        superFloat.setAutoReverse(true);

        superFloat.setCycleCount(
                Animation.INDEFINITE
        );

        superFloat.play();
    }


    // =============================================================
    // ANIMATED CINEMATIC BACKGROUND
    // =============================================================

    private static void createAnimatedBackground(
            Canvas canvas
    ) {


        GraphicsContext gc =
                canvas.getGraphicsContext2D();


        final int particleCount = 100;


        double[] x =
                new double[particleCount];

        double[] y =
                new double[particleCount];

        double[] speed =
                new double[particleCount];

        double[] size =
                new double[particleCount];


        for (int i = 0; i < particleCount; i++) {

            x[i] =
                    random.nextDouble() * 1400;

            y[i] =
                    random.nextDouble() * 850;

            speed[i] =
                    0.05
                            + random.nextDouble() * 0.25;

            size[i] =
                    1
                            + random.nextDouble() * 2.5;
        }


        final double[] time = {0};


        AnimationTimer timer =
                new AnimationTimer() {

                    @Override
                    public void handle(long now) {


                        double width =
                                canvas.getWidth();

                        double height =
                                canvas.getHeight();


                        if (width <= 0 || height <= 0) {
                            return;
                        }


                        time[0] += 0.008;


                        // =================================================
                        // DARK BACKGROUND
                        // =================================================

                        gc.setFill(
                                Color.rgb(1, 5, 3)
                        );

                        gc.fillRect(
                                0,
                                0,
                                width,
                                height
                        );


                        // =================================================
                        // LEFT GREEN GLOW
                        // =================================================

                        RadialGradient leftGlow =
                                new RadialGradient(

                                        0,

                                        0,

                                        width * 0.22,

                                        height * 0.42,

                                        width * 0.48,

                                        false,

                                        CycleMethod.NO_CYCLE,

                                        new Stop(
                                                0,
                                                Color.rgb(
                                                        60,
                                                        255,
                                                        0,
                                                        0.14
                                                )
                                        ),

                                        new Stop(
                                                1,
                                                Color.TRANSPARENT
                                        )
                                );


                        gc.setFill(leftGlow);

                        gc.fillRect(
                                0,
                                0,
                                width,
                                height
                        );


                        // =================================================
                        // RIGHT BLUE GLOW
                        // =================================================

                        RadialGradient rightGlow =
                                new RadialGradient(

                                        0,

                                        0,

                                        width * 0.80,

                                        height * 0.42,

                                        width * 0.48,

                                        false,

                                        CycleMethod.NO_CYCLE,

                                        new Stop(
                                                0,
                                                Color.rgb(
                                                        0,
                                                        140,
                                                        255,
                                                        0.12
                                                )
                                        ),

                                        new Stop(
                                                1,
                                                Color.TRANSPARENT
                                        )
                                );


                        gc.setFill(rightGlow);

                        gc.fillRect(
                                0,
                                0,
                                width,
                                height
                        );


                        // =================================================
                        // PARTICLES
                        // =================================================

                        for (int i = 0;
                             i < particleCount;
                             i++) {


                            y[i] -= speed[i];


                            if (y[i] < -10) {

                                y[i] =
                                        height + 10;

                                x[i] =
                                        random.nextDouble()
                                                * width;
                            }


                            if (x[i] < width * 0.52) {

                                gc.setFill(
                                        Color.rgb(
                                                190,
                                                255,
                                                0,
                                                0.48
                                        )
                                );

                            } else {

                                gc.setFill(
                                        Color.rgb(
                                                40,
                                                170,
                                                255,
                                                0.42
                                        )
                                );
                            }


                            gc.fillOval(

                                    x[i],

                                    y[i],

                                    size[i],

                                    size[i]
                            );
                        }


                        // =================================================
                        // TOP LEFT ENERGY
                        // =================================================

                        gc.setStroke(
                                Color.rgb(
                                        180,
                                        255,
                                        0,
                                        0.50
                                )
                        );

                        gc.setLineWidth(2);


                        gc.beginPath();

                        gc.moveTo(
                                0,
                                height * 0.10
                        );

                        gc.quadraticCurveTo(

                                width * 0.15,

                                height * 0.01,

                                width * 0.32,

                                0
                        );

                        gc.stroke();


                        // =================================================
                        // TOP RIGHT BLUE ENERGY
                        // =================================================

                        gc.setStroke(
                                Color.rgb(
                                        40,
                                        170,
                                        255,
                                        0.55
                                )
                        );

                        gc.setLineWidth(2);


                        gc.beginPath();

                        gc.moveTo(
                                width,
                                height * 0.10
                        );

                        gc.quadraticCurveTo(

                                width * 0.85,

                                height * 0.01,

                                width * 0.70,

                                0
                        );

                        gc.stroke();


                        // =================================================
                        // BOTTOM LEFT GREEN CURVE
                        // =================================================

                        gc.setStroke(
                                Color.rgb(
                                        170,
                                        255,
                                        0,
                                        0.55
                                )
                        );

                        gc.setLineWidth(2);


                        gc.beginPath();

                        gc.moveTo(
                                0,
                                height * 0.72
                        );

                        gc.quadraticCurveTo(

                                width * 0.18,

                                height * 0.96,

                                width * 0.42,

                                height * 0.82
                        );

                        gc.stroke();


                        // =================================================
                        // BOTTOM RIGHT BLUE CURVE
                        // =================================================

                        gc.setStroke(
                                Color.rgb(
                                        40,
                                        170,
                                        255,
                                        0.55
                                )
                        );

                        gc.setLineWidth(2);


                        gc.beginPath();

                        gc.moveTo(
                                width,
                                height * 0.72
                        );

                        gc.quadraticCurveTo(

                                width * 0.82,

                                height * 0.96,

                                width * 0.60,

                                height * 0.82
                        );

                        gc.stroke();


                        // =================================================
                        // ORANGE SUPER-X ENERGY
                        // =================================================

                        gc.setStroke(
                                Color.rgb(
                                        255,
                                        145,
                                        0,
                                        0.45
                                )
                        );

                        gc.setLineWidth(1.5);


                        gc.beginPath();

                        gc.moveTo(
                                width,
                                height * 0.68
                        );

                        gc.quadraticCurveTo(

                                width * 0.88,

                                height * 0.91,

                                width * 0.68,

                                height * 0.84
                        );

                        gc.stroke();


                        // =================================================
                        // HORIZON
                        // =================================================

                        double horizon =
                                height * 0.79;


                        LinearGradient horizonGlow =
                                new LinearGradient(

                                        0,

                                        0,

                                        width,

                                        0,

                                        false,

                                        CycleMethod.NO_CYCLE,

                                        new Stop(
                                                0,
                                                Color.TRANSPARENT
                                        ),

                                        new Stop(
                                                0.25,
                                                Color.rgb(
                                                        100,
                                                        255,
                                                        0,
                                                        0.45
                                                )
                                        ),

                                        new Stop(
                                                0.50,
                                                Color.rgb(
                                                        220,
                                                        255,
                                                        100,
                                                        1
                                                )
                                        ),

                                        new Stop(
                                                0.75,
                                                Color.rgb(
                                                        0,
                                                        180,
                                                        255,
                                                        0.45
                                                )
                                        ),

                                        new Stop(
                                                1,
                                                Color.TRANSPARENT
                                        )
                                );


                        gc.setStroke(
                                horizonGlow
                        );

                        gc.setLineWidth(2);

                        gc.strokeLine(
                                0,
                                horizon,
                                width,
                                horizon
                        );


                        // =================================================
                        // REFLECTIVE FLOOR
                        // =================================================

                        LinearGradient floor =
                                new LinearGradient(

                                        0,

                                        horizon,

                                        0,

                                        height,

                                        false,

                                        CycleMethod.NO_CYCLE,

                                        new Stop(
                                                0,
                                                Color.rgb(
                                                        10,
                                                        70,
                                                        25,
                                                        0.25
                                                )
                                        ),

                                        new Stop(
                                                1,
                                                Color.rgb(
                                                        0,
                                                        0,
                                                        0,
                                                        0.85
                                                )
                                        )
                                );


                        gc.setFill(floor);

                        gc.fillRect(
                                0,
                                horizon,
                                width,
                                height - horizon
                        );


                        // =================================================
                        // FLOOR PERSPECTIVE LINES
                        // =================================================

                        gc.setStroke(
                                Color.rgb(
                                        130,
                                        255,
                                        0,
                                        0.10
                                )
                        );

                        gc.setLineWidth(1);


                        for (int i = 0; i < 14; i++) {

                            double bottomX =
                                    width * i / 13.0;


                            gc.strokeLine(

                                    width / 2,

                                    horizon,

                                    bottomX,

                                    height
                            );
                        }


                        // =================================================
                        // PULSING HORIZON LIGHT
                        // =================================================

                        double pulse =
                                0.5
                                        + Math.sin(
                                        time[0]
                                ) * 0.5;


                        gc.setFill(
                                Color.rgb(
                                        190,
                                        255,
                                        0,
                                        0.10 + pulse * 0.10
                                )
                        );


                        gc.fillOval(

                                width / 2 - 100,

                                horizon - 45,

                                200,

                                90
                        );
                    }
                };


        timer.start();
    }
}