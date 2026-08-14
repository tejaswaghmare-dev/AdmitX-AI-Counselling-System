package com.admitx.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StudentDashboard {

    private static final String NAVY = "#081B33";
    private static final String BLUE = "#173DB5";
    private static final String ORANGE = "#B45100";
    private static final String BG = "#F6F7F9";
    private static final String BORDER = "#D7D9DE";

    private static BorderPane root;
    private static StackPane centerPane;

    public static void showDashboard(Stage stage, String studentName,
                                     String applicationId) {

        root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG + ";");

        root.setTop(createHeader());
        root.setLeft(createSidebar());

        centerPane = new StackPane();
        centerPane.setStyle("-fx-background-color: " + BG + ";");

        root.setCenter(centerPane);

        showDashboardPage(studentName, applicationId);

        Scene scene = new Scene(root, 1450, 850);

        stage.setTitle("AdmitX - Student Dashboard");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static HBox createHeader() {

        HBox header = new HBox(25);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(0, 18, 0, 18));
        header.setPrefHeight(45);
        header.setStyle("-fx-background-color: " + ORANGE + ";");

        Label title = new Label("MHT CET CAP PORTAL");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label training = new Label(
                "DUMMY / TRAINING PORTAL — For Educational Practice Only"
        );
        training.setTextFill(Color.WHITE);
        training.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        Button notification = new Button("♧");
        Button profile = new Button("◎");

        styleHeaderButton(notification);
        styleHeaderButton(profile);

        notification.setOnAction(e ->
                showPage("Notifications",
                        "Notifications page will be added here.")
        );

        profile.setOnAction(e ->
                showPage("Profile",
                        "Student profile page will be added here.")
        );

        header.getChildren().addAll(
                title,
                training,
                space,
                notification,
                profile
        );

        return header;
    }

    private static VBox createSidebar() {

        VBox sidebar = new VBox();
        sidebar.setPrefWidth(300);
        sidebar.setStyle("-fx-background-color: " + NAVY + ";");

        VBox student = new VBox(4);
        student.setPadding(new Insets(25, 18, 25, 18));

        Label portal = new Label("Student Portal");
        portal.setTextFill(Color.web("#D7DDFF"));
        portal.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label year = new Label("CAP 2024-25");
        year.setTextFill(Color.web("#8493B8"));
        year.setFont(Font.font("Arial", 14));

        student.getChildren().addAll(portal, year);

        VBox menu = new VBox();

        Button dashboard = menuButton("▦", "Dashboard");
        Button application = menuButton("▤", "Application");
        Button documents = menuButton("⇧", "Documents");
        Button merit = menuButton("☷", "Merit List");
        Button college = menuButton("⌕", "College Search");
        Button preferences = menuButton("▤", "Preferences");
        Button cap = menuButton("☰", "CAP Rounds");
        Button admission = menuButton("♧", "Admission");
        Button notices = menuButton("⚑", "Notices");
        Button help = menuButton("?", "Help");

        menu.getChildren().addAll(
                dashboard,
                application,
                documents,
                merit,
                college,
                preferences,
                cap,
                admission,
                notices,
                help
        );

        dashboard.setOnAction(e ->
                showDashboardPage("Rahul Kumar", "STU00001")
        );

        application.setOnAction(e ->
                showPage("Application",
                        "Application page will be developed here.")
        );

        documents.setOnAction(e ->
                showPage("Documents",
                        "Documents page will be developed here.")
        );

        merit.setOnAction(e ->
                showPage("Merit List",
                        "Merit List page will be developed here.")
        );

        college.setOnAction(e ->
                showPage("College Search",
                        "College Search page will be developed here.")
        );

        preferences.setOnAction(e ->
                showPage("Preferences",
                        "Preferences page will be developed here.")
        );

        cap.setOnAction(e ->
                showPage("CAP Rounds",
                        "CAP Rounds page will be developed here.")
        );

        admission.setOnAction(e ->
                showPage("Admission",
                        "Admission page will be developed here.")
        );

        notices.setOnAction(e ->
                showPage("Notices",
                        "Notices page will be developed here.")
        );

        help.setOnAction(e ->
                showPage("Help",
                        "Help page will be developed here.")
        );

        sidebar.getChildren().addAll(student, menu);

        return sidebar;
    }

    private static Button menuButton(String icon, String text) {

        Button button = new Button();

        Label iconLabel = new Label(icon);
        iconLabel.setTextFill(Color.web("#D8E0FF"));
        iconLabel.setFont(Font.font(20));

        Label textLabel = new Label(text);
        textLabel.setTextFill(Color.web("#C8CEE0"));
        textLabel.setFont(Font.font("Arial", 16));

        HBox box = new HBox(17);
        box.setAlignment(Pos.CENTER_LEFT);
        box.getChildren().addAll(iconLabel, textLabel);

        button.setGraphic(box);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setPrefWidth(300);
        button.setPrefHeight(52);
        button.setPadding(new Insets(0, 20, 0, 20));
        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: #102A4A;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-cursor: hand;"
                )
        );

        return button;
    }

    private static void styleHeaderButton(Button button) {

        button.setPrefSize(40, 35);
        button.setFont(Font.font(22));
        button.setTextFill(Color.WHITE);
        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;"
        );
    }

    private static void showDashboardPage(String name, String id) {

        VBox page = new VBox(15);
        page.setPadding(new Insets(25));
        page.setStyle("-fx-background-color: " + BG + ";");

        HBox titleRow = new HBox();
        titleRow.setAlignment(Pos.CENTER_LEFT);

        VBox titleBox = new VBox(3);

        Label title = new Label("Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        Label welcome = new Label(
                "Welcome back, " + name + " (" + id + ")"
        );
        welcome.setFont(Font.font("Arial", 17));

        titleBox.getChildren().addAll(title, welcome);

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        VBox status = new VBox(3);
        status.setPadding(new Insets(10, 18, 10, 18));
        status.setPrefWidth(370);
        status.setStyle(
                "-fx-background-color: #E5EDFF;" +
                "-fx-border-color: #B8C8E8;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;"
        );

        Label statusTitle = new Label("▣  CURRENT STATUS");
        statusTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label statusText = new Label(
                "Document Verification Pending"
        );
        statusText.setTextFill(Color.web(ORANGE));
        statusText.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        status.getChildren().addAll(statusTitle, statusText);

        titleRow.getChildren().addAll(
                titleBox,
                space,
                status
        );

        Region line = new Region();
        line.setPrefHeight(1);
        line.setStyle("-fx-background-color: #D2D5DB;");

        HBox columns = new HBox(16);

        VBox left = new VBox(16);
        VBox right = new VBox(16);

        HBox.setHgrow(left, Priority.ALWAYS);
        right.setPrefWidth(350);

        left.getChildren().addAll(
                profileCard(name, id),
                actionCard(),
                journeyCard()
        );

        right.getChildren().addAll(
                noticesCard(),
                activityCard()
        );

        columns.getChildren().addAll(left, right);

        page.getChildren().addAll(
                titleRow,
                line,
                columns
        );

        setCenter(page);
    }

    private static VBox profileCard(String name, String id) {

        VBox card = card();

        HBox heading = new HBox();
        heading.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Profile Overview");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        Label complete = new Label("45% Complete");
        complete.setTextFill(Color.WHITE);
        complete.setPadding(new Insets(5, 10, 5, 10));
        complete.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 5;"
        );

        heading.getChildren().addAll(
                title,
                space,
                complete
        );

        ProgressBar progress = new ProgressBar(0.45);
        progress.setMaxWidth(Double.MAX_VALUE);

        HBox information = new HBox(80);

        VBox applicant = info("APPLICANT NAME", name);
        VBox application = info("APPLICATION ID", id);

        information.getChildren().addAll(
                applicant,
                application
        );

        card.getChildren().addAll(
                heading,
                progress,
                information
        );

        return card;
    }

    private static HBox actionCard() {

        HBox card = new HBox(20);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(20));
        card.setPrefHeight(140);

        card.setStyle(
                "-fx-background-color: linear-gradient(" +
                "to right,#1736AA,#3558C8);" +
                "-fx-background-radius: 12;"
        );

        VBox text = new VBox(7);

        Label title = new Label("Action Required");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        Label description = new Label(
                "Please complete your personal\n" +
                "information to proceed to document\n" +
                "upload."
        );

        description.setTextFill(Color.web("#E2E8FF"));
        description.setFont(Font.font("Arial", 16));

        text.getChildren().addAll(title, description);

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        Button personal = new Button(
                "Fill Personal Information  →"
        );

        personal.setPrefSize(310, 55);
        personal.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        personal.setTextFill(Color.web("#17327D"));
        personal.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        personal.setOnAction(e ->
                showPage(
                        "Personal Information",
                        "Personal Information page will be developed here."
                )
        );

        card.getChildren().addAll(
                text,
                space,
                personal
        );

        return card;
    }

    private static VBox journeyCard() {

        VBox card = card();

        Label title = new Label("Application Journey");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        HBox journey = new HBox();
        journey.setAlignment(Pos.CENTER);
        journey.setPadding(new Insets(20, 5, 10, 5));

        journey.getChildren().addAll(
                step("✓", "Registration", true),
                line(true),
                step("✓", "App Form", true),
                line(true),
                step("3", "Documents", false),
                line(false),
                step("4", "Verification", false),
                line(false),
                step("5", "Admission", false)
        );

        card.getChildren().addAll(title, journey);

        return card;
    }

    private static VBox noticesCard() {

        VBox card = card();

        Label title = new Label("Important Notices");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox notice1 = notice(
                "Today, 09:00 AM",
                "Document upload deadline extended to 15th Aug."
        );

        VBox notice2 = notice(
                "Yesterday",
                "Caste validity certificate format updated."
        );

        notice1.setOnMouseClicked(e ->
                showPage("Notice",
                        "Document upload deadline extended to 15th Aug.")
        );

        notice2.setOnMouseClicked(e ->
                showPage("Notice",
                        "Caste validity certificate format updated.")
        );

        card.getChildren().addAll(
                title,
                notice1,
                notice2
        );

        return card;
    }

    private static VBox activityCard() {

        VBox card = card();

        Label title = new Label("Recent Activity");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox activity1 = activity(
                "●",
                "Application Form Submitted",
                "10 Aug 2024, 14:30"
        );

        VBox activity2 = activity(
                "●",
                "Registration Completed",
                "08 Aug 2024, 10:15"
        );

        activity1.setOnMouseClicked(e ->
                showPage("Application Activity",
                        "Application Form Submitted")
        );

        activity2.setOnMouseClicked(e ->
                showPage("Registration Activity",
                        "Registration Completed")
        );

        card.getChildren().addAll(
                title,
                activity1,
                activity2
        );

        return card;
    }

    private static VBox card() {

        VBox box = new VBox(15);
        box.setPadding(new Insets(18));

        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        return box;
    }

    private static VBox info(String title, String value) {

        VBox box = new VBox(5);

        Label t = new Label(title);
        t.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label v = new Label(value);
        v.setFont(Font.font("Arial", 18));

        box.getChildren().addAll(t, v);

        return box;
    }

    private static VBox notice(String time, String text) {

        VBox box = new VBox(7);
        box.setPadding(new Insets(12, 14, 12, 16));

        box.setStyle(
                "-fx-background-color: #EEF3FF;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: " + ORANGE + ";" +
                "-fx-border-width: 0 0 0 4;" +
                "-fx-cursor: hand;"
        );

        Label timeLabel = new Label(time);
        timeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        Label message = new Label(text);
        message.setWrapText(true);
        message.setFont(Font.font("Arial", 14));

        box.getChildren().addAll(
                timeLabel,
                message
        );

        return box;
    }

    private static VBox activity(
            String icon,
            String title,
            String time) {

        VBox box = new VBox(3);
        box.setPadding(new Insets(10, 5, 10, 5));

        Label t = new Label(
                icon + "   " + title
        );
        t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Label d = new Label(
                "       " + time
        );
        d.setFont(Font.font("Arial", 12));

        box.getChildren().addAll(t, d);

        return box;
    }

    private static HBox step(
            String number,
            String title,
            boolean completed) {

        VBox content = new VBox(6);
        content.setAlignment(Pos.CENTER);

        Circle circle = new Circle(19);

        if (completed) {
            circle.setFill(Color.web(ORANGE));
        } else {
            circle.setFill(Color.web("#DFE8FA"));
        }

        Label numberLabel = new Label(number);
        numberLabel.setTextFill(
                completed ? Color.WHITE : Color.web("#555E70")
        );

        numberLabel.setFont(
                Font.font("Arial", FontWeight.BOLD, 14)
        );

        StackPane circlePane = new StackPane(
                circle,
                numberLabel
        );

        Label text = new Label(title);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        content.getChildren().addAll(
                circlePane,
                text
        );

        HBox result = new HBox(content);
        result.setAlignment(Pos.CENTER);

        return result;
    }

    private static Region line(boolean completed) {

        Region line = new Region();

        line.setPrefWidth(65);
        line.setPrefHeight(2);

        line.setStyle(
                "-fx-background-color: " +
                (completed ? ORANGE : "#B9BECA") + ";"
        );

        return line;
    }

    private static void showPage(
            String title,
            String description) {

        VBox page = new VBox(20);

        page.setAlignment(Pos.TOP_LEFT);
        page.setPadding(new Insets(35));

        Label heading = new Label(title);
        heading.setFont(
                Font.font("Arial", FontWeight.BOLD, 30)
        );

        Label text = new Label(description);
        text.setFont(Font.font("Arial", 18));
        text.setTextFill(Color.web("#555B68"));

        VBox placeholder = new VBox(15);
        placeholder.setPadding(new Insets(25));
        placeholder.setMaxWidth(Double.MAX_VALUE);

        placeholder.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;"
        );

        Label future = new Label(
                "This page is ready for future development."
        );

        future.setFont(Font.font("Arial", 16));

        placeholder.getChildren().add(future);

        page.getChildren().addAll(
                heading,
                text,
                placeholder
        );

        setCenter(page);
    }

    private static void setCenter(Node node) {

        centerPane.getChildren().clear();
        centerPane.getChildren().add(node);
    }

    private static class ProgressBar extends Region {

        private final Region fill = new Region();

        ProgressBar(double value) {

            setPrefHeight(10);
            setMaxWidth(Double.MAX_VALUE);

            setStyle(
                    "-fx-background-color: #DCE8FC;" +
                    "-fx-background-radius: 10;"
            );

            fill.setPrefHeight(10);
            fill.setPrefWidth(290);

            fill.setStyle(
                    "-fx-background-color: " + BLUE + ";" +
                    "-fx-background-radius: 10;"
            );

            getChildren().add(fill);
           
        }
    }
}