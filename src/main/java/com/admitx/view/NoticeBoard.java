package com.admitx.view;




import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Node;

public class NoticeBoard  {

    private final String NAV_STYLE =
            "-fx-text-fill: #aeb9cc;" +
            "-fx-background-color: transparent;" +
            "-fx-font-size: 16px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 14 20 14 28;" +
            "-fx-cursor: hand;";

   
    public Node getPage() {

    VBox mainContent = new VBox(20);

    mainContent.setPadding(
            new Insets(25, 35, 30, 30)
    );

    mainContent.setStyle(
            "-fx-background-color: #F6F7F9;"
    );

    // =========================
    // HEADER
    // =========================

    Label title = new Label("Notice Board");

    title.setFont(
            Font.font(
                    "Arial",
                    FontWeight.BOLD,
                    29
            )
    );

    title.setTextFill(
            Color.web("#101828")
    );


    Label subtitle = new Label(
            "Important updates and announcements for CAP 2024-25."
    );

    subtitle.setFont(
            Font.font("Arial", 17)
    );

    subtitle.setTextFill(
            Color.web("#3e4655")
    );


    VBox headingArea = new VBox(
            5,
            title,
            subtitle
    );


    // =========================
    // BUTTONS
    // =========================

    Button filterButton =
            new Button("≡  Filter");

    filterButton.setStyle(
            "-fx-background-color: #e8eefb;" +
            "-fx-border-color: #bdc7d9;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 10 20;"
    );


    Button markRead =
            new Button("Mark All as Read");

    markRead.setStyle(
            "-fx-background-color: #092a87;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 11 18;"
    );


    HBox buttons = new HBox(
            10,
            filterButton,
            markRead
    );

    buttons.setAlignment(
            Pos.CENTER_RIGHT
    );


    HBox header = new HBox();

    header.setAlignment(
            Pos.CENTER_LEFT
    );


    Region headerSpace = new Region();

    HBox.setHgrow(
            headerSpace,
            Priority.ALWAYS
    );


    header.getChildren().addAll(
            headingArea,
            headerSpace,
            buttons
    );


    Separator separator = new Separator();

    // =========================
    // NOTICE CARDS
    // =========================

    VBox notices = new VBox(18);


    VBox card1 = createNoticeCard(
            "!",
            "Merit List Published",
            "HIGH PRIORITY",
            "The final merit list for Engineering admissions has been published.",
            "Please log in to check your merit status and rank.",
            "Today, 10:00 AM",
            "#c90000",
            true,
            true
    );


    VBox card2 = createNoticeCard(
            "ⓘ",
            "Registration Open",
            "INFORMATION",
            "The CAP registration portal is now open.",
            "Candidates are advised to complete their profile and document upload before the deadline.",
            "Yesterday, 02:30 PM",
            "#173e9e",
            true,
            false
    );


    VBox card3 = createNoticeCard(
            "▤",
            "Document Verification Guidelines Updated",
            "UPDATE",
            "Please review the updated guidelines for acceptable formats and sizes",
            "for document uploads during the verification process.",
            "Oct 24, 2024",
            "#7b8798",
            false,
            false
    );


    VBox card4 = createNoticeCard(
            "▣",
            "System Maintenance Scheduled",
            "",
            "The portal will undergo scheduled maintenance on Sunday from 2 AM to 4 AM.",
            "Services may be temporarily unavailable.",
            "Oct 20, 2024",
            "#7b8798",
            false,
            false
    );


    notices.getChildren().addAll(
            card1,
            card2,
            card3,
            card4
    );


    mainContent.getChildren().addAll(
            header,
            separator,
            notices
    );


    // =========================
    // BUTTON ACTIONS
    // =========================

    markRead.setOnAction(e -> {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION,
                "All notices have been marked as read."
        );

        alert.setHeaderText("Success");

        alert.showAndWait();
    });


    filterButton.setOnAction(e -> {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION,
                "Filter option selected."
        );

        alert.setHeaderText("Notice Filter");

        alert.showAndWait();
    });


    return mainContent;
}

    // =====================================================
    // NOTICE CARD METHOD
    // =====================================================

    private VBox createNoticeCard(
        String iconText,
        String titleText,
        String tagText,
        String line1,
        String line2,
        String dateText,
        String accentColor,
        boolean unread,
        boolean showLink
) {

    VBox card = new VBox(8);

    card.setPadding(new Insets(18, 20, 18, 20));

    card.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #c7ccd5;" +
            "-fx-border-radius: 10;" +
            "-fx-background-radius: 10;"
    );

    // TOP ROW
    HBox topRow = new HBox(12);
    topRow.setAlignment(Pos.CENTER_LEFT);

    Label icon = new Label(iconText);
    icon.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    icon.setTextFill(Color.web(accentColor));

    Label title = new Label(titleText);
    title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    title.setTextFill(Color.web("#111827"));

    topRow.getChildren().addAll(icon, title);

    // TAG
    if (!tagText.isEmpty()) {

        Label tag = new Label(tagText);

        tag.setPadding(new Insets(4, 9, 4, 9));

        String tagBackground =
                accentColor.equals("#c90000")
                        ? "#fce6e2"
                        : "#dbe8ff";

        tag.setStyle(
                "-fx-background-color: " + tagBackground + ";" +
                "-fx-background-radius: 12;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        tag.setTextFill(Color.web(accentColor));

        topRow.getChildren().add(tag);
    }

    // UNREAD DOT
    if (unread) {

        Circle dot = new Circle(5);
        dot.setFill(Color.web("#143a9b"));

        topRow.getChildren().add(dot);
    }

    // DATE
    Region space = new Region();
    HBox.setHgrow(space, Priority.ALWAYS);

    Label date = new Label(dateText);
    date.setFont(Font.font("Arial", 14));
    date.setTextFill(Color.web("#707785"));

    topRow.getChildren().addAll(space, date);

    // BODY
    Label body1 = new Label(line1);
    body1.setFont(Font.font("Arial", 17));
    body1.setTextFill(Color.web("#303846"));

    Label body2 = new Label(line2);
    body2.setFont(Font.font("Arial", 17));
    body2.setTextFill(Color.web("#303846"));

    card.getChildren().addAll(
            topRow,
            body1,
            body2
    );

    // VIEW MERIT LIST
    if (showLink) {

        Label link = new Label("View Merit List");

        link.setFont(
                Font.font("Arial", FontWeight.BOLD, 15)
        );

        link.setTextFill(Color.web("#092a87"));

        link.setStyle("-fx-cursor: hand;");

        link.setOnMouseClicked(e -> {

            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION
            );

            alert.setTitle("Merit List");
            alert.setHeaderText("Merit List");
            alert.setContentText(
                    "Merit List page opened."
            );

            alert.showAndWait();
        });

        card.getChildren().add(link);
    }

    // LEFT COLORED LINE
    Region leftBorder = new Region();

    leftBorder.setPrefWidth(6);
    leftBorder.setMinWidth(6);
    leftBorder.setMaxWidth(6);

    leftBorder.setStyle(
            "-fx-background-color: " + accentColor + ";" +
            "-fx-background-radius: 6;"
    );

    // BORDER + CARD
    HBox wrapper = new HBox();

    wrapper.setAlignment(Pos.CENTER_LEFT);

    wrapper.getChildren().addAll(
            leftBorder,
            card
    );

    HBox.setHgrow(card, Priority.ALWAYS);

    VBox result = new VBox(wrapper);

    return result;
}
}
