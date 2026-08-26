package com.admitx.view;
import com.admitx.service.OpenAIService;
import javafx.concurrent.Task;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
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

    public static Node create() {

        StackPane root = new StackPane();
        root.setPickOnBounds(false);

        VBox chatBox = createChatBox();
        StackPane robot = createRobot();

        chatBox.setVisible(false);
        chatBox.setManaged(false);

        VBox container = new VBox(10);
        container.setAlignment(Pos.BOTTOM_RIGHT);

        container.getChildren().addAll(
                chatBox,
                robot
        );

        root.getChildren().add(container);

        robot.setOnMouseClicked(e -> {

            boolean show = !chatBox.isVisible();

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

        startFloatingAnimation(robot);

        return root;
    }

    private static StackPane createRobot() {

        StackPane robot = new StackPane();

        robot.setPrefSize(85, 85);

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

        HBox header =
                new HBox();

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
                        "  ● Online"
                );

        status.setStyle(
                "-fx-text-fill: #84CC16;" +
                "-fx-font-size: 11px;"
        );

        header.getChildren().addAll(
                title,
                status
        );

        messagesBox =
                new VBox(10);

        messagesBox.setPadding(
                new Insets(10)
        );

        scrollPane =
                new ScrollPane(messagesBox);

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

        addBotMessage(
                "Hi! 👋 I'm your AdmitX Assistant.\nHow can I help you today?"
        );

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

        send.setOnAction(e ->
                sendMessage(input)
        );

        input.setOnAction(e ->
                sendMessage(input)
        );

        chatBox.getChildren().addAll(
                header,
                scrollPane,
                inputArea
        );

        return chatBox;
    }

    private static void sendMessage(
        TextField input
) {

    String text =
            input.getText().trim();

    if (text.isEmpty()) {
        return;
    }

    addUserMessage(text);

    input.clear();

    showTyping();

    Task<String> aiTask =
            new Task<>() {

                @Override
                protected String call() {

                    return OpenAIService.askAI(
                            text
                    );
                }
            };

    aiTask.setOnSucceeded(e -> {

        removeTyping();

        addBotMessage(
                aiTask.getValue()
        );
    });

    aiTask.setOnFailed(e -> {

        removeTyping();

        addBotMessage(
                "Sorry, I couldn't connect to the AI right now."
        );
    });

    Thread thread =
            new Thread(aiTask);

    thread.setDaemon(true);

    thread.start();
}

    private static void addUserMessage(
            String message
    ) {

        Label bubble =
                new Label(message);

        bubble.setWrapText(true);

        bubble.setMaxWidth(220);

        bubble.setPadding(
                new Insets(8, 12, 8, 12)
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

        messagesBox.getChildren().add(row);

        scrollToBottom();
    }

    private static void addBotMessage(
            String message
    ) {

        Label bubble =
                new Label(message);

        bubble.setWrapText(true);

        bubble.setMaxWidth(220);

        bubble.setPadding(
                new Insets(8, 12, 8, 12)
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

        messagesBox.getChildren().add(row);

        scrollToBottom();
    }

    private static void showTyping() {

        Label typing =
                new Label(
                        "AdmitX is typing..."
                );

        typing.setId(
                "typingLabel"
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

        messagesBox.getChildren().add(row);

        scrollToBottom();
    }

    private static void removeTyping() {

        messagesBox.getChildren()
                .removeIf(node ->
                        "typingRow".equals(
                                node.getId()
                        )
                );
    }

    private static String getBotResponse(
            String message
    ) {

        String text =
                message.toLowerCase();

        if (text.contains("hello")
                || text.contains("hi")
                || text.contains("hey")) {

            return "Hello! 👋 How can I help you with your admission process?";
        }

        if (text.contains("application")) {

            return "Go to the Application section and complete Personal, Address, Academic, Eligibility and Reservation details.";
        }

        if (text.contains("document")
                || text.contains("upload")) {

            return "Open the Documents section and upload the required certificates and marksheets. Make sure every uploaded document is correct before submission.";
        }

        if (text.contains("merit")) {

            return "You can check your Provisional and Final Merit status from the Merit List section.";
        }

        if (text.contains("grievance")) {

            return "If you find any issue in your provisional merit details, open the Merit List section and select Raise Grievance.";
        }

        if (text.contains("college")) {

            return "Use College Search to find institutes and view their available branches, fees and other information.";
        }

        if (text.contains("preference")
                || text.contains("option form")) {

            return "In Preference Filling, add colleges and branches in your preferred order. Higher preferences should contain the options you want most.";
        }

        if (text.contains("cap")
                || text.contains("round")) {

            return "CAP rounds are used for seat allotment. After allotment, you may receive options such as Freeze, Betterment or Reject depending on the round.";
        }

        if (text.contains("seat")
                || text.contains("allotment")) {

            return "Your allotted college and branch will appear in the CAP Round section once the allotment result is published.";
        }

        if (text.contains("freeze")) {

            return "Freeze means you accept the allotted seat and do not want to participate in further betterment rounds.";
        }

        if (text.contains("betterment")) {

            return "Betterment means you keep the current eligible seat while participating for a higher preference in the next CAP round.";
        }

        if (text.contains("status")) {

            return "You can check your Application, Documents, Merit and CAP Round status from the Student Dashboard.";
        }

        if (text.contains("help")) {

            return "You can ask me about application, documents, merit list, colleges, preference filling, grievances or CAP rounds.";
        }

        if (text.contains("thank")) {

            return "You're welcome! 😊 I'm here whenever you need help.";
        }

        return "I can currently help with Application, Documents, Merit List, College Search, Preference Filling, Grievances and CAP Rounds.";
    }

    private static void scrollToBottom() {

        javafx.application.Platform.runLater(() ->
                scrollPane.setVvalue(1.0)
        );
    }

    private static void startFloatingAnimation(
            Node robot
    ) {

        TranslateTransition floating =
                new TranslateTransition(
                        Duration.seconds(1.5),
                        robot
                );

        floating.setFromY(0);
        floating.setToY(-12);

        floating.setCycleCount(
                Animation.INDEFINITE
        );

        floating.setAutoReverse(true);

        floating.play();
    }

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