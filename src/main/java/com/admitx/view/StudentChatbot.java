package com.admitx.view;

import com.admitx.service.OpenAIService;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class StudentChatbot {

    private static final String LIME = "#BEF264";
    private static final String DARK = "#111811";

    private static VBox messagesBox;
    private static ScrollPane scrollPane;

    // Drag variables
    private static double dragStartX;
    private static double dragStartY;

    private static double startTranslateX;
    private static double startTranslateY;

    private static boolean dragged = false;

    // =========================================================
    // CREATE CHATBOT
    // =========================================================

    public static Node create() {

        StackPane root = new StackPane();

        root.setPickOnBounds(false);

        VBox chatBox = createChatBox();

        StackPane robot = createRobot();

        chatBox.setVisible(false);
        chatBox.setManaged(false);

        VBox container = new VBox(10);

        container.setAlignment(
                Pos.BOTTOM_RIGHT
        );

        container.getChildren().addAll(
                chatBox,
                robot
        );

        root.getChildren().add(container);

        // =====================================================
        // CLICK ROBOT -> OPEN / CLOSE CHAT
        // =====================================================

        robot.setOnMouseClicked(event -> {

            // Prevent chat opening after dragging
            if (dragged) {

                dragged = false;

                return;
            }

            boolean show =
                    !chatBox.isVisible();

            chatBox.setVisible(show);

            chatBox.setManaged(show);

            if (show) {

                FadeTransition fade =
                        new FadeTransition(
                                Duration.millis(250),
                                chatBox
                        );

                fade.setFromValue(0);

                fade.setToValue(1);

                fade.play();
            }
        });

        // Make robot draggable
        makeDraggable(
                root,
                robot
        );

        // Floating animation
        startFloatingAnimation(
                robot
        );

        return root;
    }

    // =========================================================
    // ROBOT
    // =========================================================

    private static StackPane createRobot() {

        StackPane robot =
                new StackPane();

        robot.setPrefSize(
                85,
                85
        );

        robot.setMinSize(
                85,
                85
        );

        robot.setMaxSize(
                85,
                85
        );

        Circle background =
                new Circle(40);

        background.setFill(
                Color.web(LIME)
        );

        background.setStroke(
                Color.web("#84CC16")
        );

        background.setStrokeWidth(3);

        Rectangle face =
                new Rectangle(
                        52,
                        36
                );

        face.setArcWidth(15);

        face.setArcHeight(15);

        face.setFill(
                Color.web(DARK)
        );

        Circle leftEye =
                new Circle(5);

        Circle rightEye =
                new Circle(5);

        leftEye.setFill(
                Color.web(LIME)
        );

        rightEye.setFill(
                Color.web(LIME)
        );

        HBox eyes =
                new HBox(14);

        eyes.setAlignment(
                Pos.CENTER
        );

        eyes.getChildren().addAll(
                leftEye,
                rightEye
        );

        robot.getChildren().addAll(
                background,
                face,
                eyes
        );

        robot.setStyle(
                "-fx-cursor: hand;"
        );

        blinkEyes(
                leftEye,
                rightEye
        );

        return robot;
    }

    // =========================================================
    // CHAT BOX
    // =========================================================

    private static VBox createChatBox() {

        VBox chatBox =
                new VBox(10);

        chatBox.setPadding(
                new Insets(14)
        );

        chatBox.setPrefWidth(330);

        chatBox.setPrefHeight(430);

        chatBox.setMaxWidth(330);

        chatBox.setMaxHeight(430);

        chatBox.setStyle(
                "-fx-background-color: #151D15;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #BEF264;" +
                "-fx-border-radius: 16;" +
                "-fx-border-width: 1;"
        );

        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox(5);

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label(
                        "🤖 AdmitX Assistant"
                );

        title.setStyle(
                "-fx-text-fill: #BEF264;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;"
        );

        Label status =
                new Label(
                        "● Online"
                );

        status.setStyle(
                "-fx-text-fill: #84CC16;" +
                "-fx-font-size: 11px;"
        );

        header.getChildren().addAll(
                title,
                status
        );

        // =====================================================
        // MESSAGE AREA
        // =====================================================

        messagesBox =
                new VBox(10);

        messagesBox.setPadding(
                new Insets(10)
        );

        scrollPane =
                new ScrollPane(
                        messagesBox
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background: transparent;" +
                "-fx-background-color: transparent;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        // Welcome message

        addBotMessage(
                "Hi! 👋 I'm your AdmitX Assistant.\n" +
                "How can I help you today?"
        );

        // =====================================================
        // INPUT
        // =====================================================

        TextField input =
                new TextField();

        input.setPromptText(
                "Ask me anything..."
        );

        input.setStyle(
                "-fx-background-color: #253025;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #9CA39C;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 10;" +
                "-fx-font-size: 13px;"
        );

        Button send =
                new Button("Send");

        send.setStyle(
                "-fx-background-color: #BEF264;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 9 15;"
        );

        HBox inputArea =
                new HBox(8);

        inputArea.setAlignment(
                Pos.CENTER
        );

        HBox.setHgrow(
                input,
                Priority.ALWAYS
        );

        inputArea.getChildren().addAll(
                input,
                send
        );

        // Send button

        send.setOnAction(event ->
                sendMessage(input)
        );

        // Press ENTER

        input.setOnAction(event ->
                sendMessage(input)
        );

        chatBox.getChildren().addAll(
                header,
                scrollPane,
                inputArea
        );

        return chatBox;
    }

    // =========================================================
    // SEND MESSAGE TO AI
    // =========================================================

    private static void sendMessage(
            TextField input
    ) {

        String text =
                input
                        .getText()
                        .trim();

        if (text.isEmpty()) {
            return;
        }

        // Show student message

        addUserMessage(text);

        input.clear();

        // Show typing indicator

        showTyping();

        // =====================================================
        // AI TASK
        // =====================================================

        Task<String> aiTask =
                new Task<>() {

                    @Override
                    protected String call() {

                        return OpenAIService.askAI(
                                text
                        );
                    }
                };

        // =====================================================
        // AI SUCCESS
        // =====================================================

        aiTask.setOnSucceeded(event -> {

            removeTyping();

            String response =
                    aiTask.getValue();

            if (response == null
                    || response.isBlank()) {

                response =
                        "Sorry, I couldn't generate a response.";
            }

            addBotMessage(
                    response
            );
        });

        // =====================================================
        // AI FAILED
        // =====================================================

        aiTask.setOnFailed(event -> {

            removeTyping();

            addBotMessage(
                    "Sorry, I couldn't connect to the AI right now."
            );

            if (aiTask.getException() != null) {

                aiTask
                        .getException()
                        .printStackTrace();
            }
        });

        Thread thread =
                new Thread(aiTask);

        thread.setDaemon(true);

        thread.start();
    }

    // =========================================================
    // USER MESSAGE
    // =========================================================

    private static void addUserMessage(
            String message
    ) {

        Label bubble =
                new Label(message);

        bubble.setWrapText(true);

        bubble.setMaxWidth(220);

        bubble.setPadding(
                new Insets(
                        8,
                        12,
                        8,
                        12
                )
        );

        bubble.setStyle(
                "-fx-background-color: #BEF264;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-background-radius: 14;" +
                "-fx-font-size: 13px;"
        );

        HBox row =
                new HBox(bubble);

        row.setAlignment(
                Pos.CENTER_RIGHT
        );

        messagesBox
                .getChildren()
                .add(row);

        scrollToBottom();
    }

    // =========================================================
    // BOT MESSAGE
    // =========================================================

    private static void addBotMessage(
            String message
    ) {

        Label bubble =
                new Label(message);

        bubble.setWrapText(true);

        bubble.setMaxWidth(220);

        bubble.setPadding(
                new Insets(
                        8,
                        12,
                        8,
                        12
                )
        );

        bubble.setStyle(
                "-fx-background-color: #253025;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 14;" +
                "-fx-font-size: 13px;"
        );

        HBox row =
                new HBox(bubble);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        messagesBox
                .getChildren()
                .add(row);

        scrollToBottom();
    }

    // =========================================================
    // TYPING INDICATOR
    // =========================================================

    private static void showTyping() {

        removeTyping();

        Label typing =
                new Label(
                        "AdmitX is typing..."
                );

        typing.setStyle(
                "-fx-text-fill: #A3A3A3;" +
                "-fx-font-size: 11px;" +
                "-fx-font-style: italic;"
        );

        HBox row =
                new HBox(typing);

        row.setId(
                "typingRow"
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        messagesBox
                .getChildren()
                .add(row);

        scrollToBottom();
    }

    // =========================================================
    // REMOVE TYPING
    // =========================================================

    private static void removeTyping() {

        if (messagesBox == null) {
            return;
        }

        messagesBox
                .getChildren()
                .removeIf(
                        node ->
                                "typingRow"
                                        .equals(
                                                node.getId()
                                        )
                );
    }

    // =========================================================
    // SCROLL DOWN
    // =========================================================

    private static void scrollToBottom() {

        if (scrollPane == null) {
            return;
        }

        javafx.application.Platform.runLater(
                () ->
                        scrollPane.setVvalue(
                                1.0
                        )
        );
    }

    // =========================================================
    // DRAG CHATBOT
    // =========================================================

    private static void makeDraggable(
            StackPane root,
            StackPane robot
    ) {

        // =====================================================
        // MOUSE PRESSED
        // =====================================================

        robot.setOnMousePressed(event -> {

            dragged = false;

            dragStartX =
                    event.getSceneX();

            dragStartY =
                    event.getSceneY();

            startTranslateX =
                    root.getTranslateX();

            startTranslateY =
                    root.getTranslateY();
        });

        // =====================================================
        // MOUSE DRAGGED
        // =====================================================

        robot.setOnMouseDragged(event -> {

            double deltaX =
                    event.getSceneX()
                            - dragStartX;

            double deltaY =
                    event.getSceneY()
                            - dragStartY;

            // Ignore tiny mouse movements

            if (Math.abs(deltaX) > 3
                    || Math.abs(deltaY) > 3) {

                dragged = true;
            }

            root.setTranslateX(
                    startTranslateX
                            + deltaX
            );

            root.setTranslateY(
                    startTranslateY
                            + deltaY
            );
        });
    }

    // =========================================================
    // FLOATING ANIMATION
    // =========================================================

    private static void startFloatingAnimation(
            Node robot
    ) {

        TranslateTransition floating =
                new TranslateTransition(
                        Duration.seconds(1.5),
                        robot
                );

        floating.setFromY(0);

        floating.setToY(-10);

        floating.setCycleCount(
                Animation.INDEFINITE
        );

        floating.setAutoReverse(true);

        floating.play();
    }

    // =========================================================
    // BLINK EYES
    // =========================================================

    private static void blinkEyes(
            Circle leftEye,
            Circle rightEye
    ) {

        FadeTransition blinkLeft =
                new FadeTransition(
                        Duration.millis(300),
                        leftEye
                );

        FadeTransition blinkRight =
                new FadeTransition(
                        Duration.millis(300),
                        rightEye
                );

        blinkLeft.setFromValue(1);

        blinkLeft.setToValue(0.15);

        blinkRight.setFromValue(1);

        blinkRight.setToValue(0.15);

        blinkLeft.setAutoReverse(true);

        blinkRight.setAutoReverse(true);

        blinkLeft.setCycleCount(
                Animation.INDEFINITE
        );

        blinkRight.setCycleCount(
                Animation.INDEFINITE
        );

        blinkLeft.setDelay(
                Duration.seconds(2)
        );

        blinkRight.setDelay(
                Duration.seconds(2)
        );

        blinkLeft.play();

        blinkRight.play();
    }
}