package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class AboutUsPage {

    // =====================================================
    // COLORS
    // =====================================================

    private static final String BLACK = "#050705";
    private static final String DARK = "#0D120C";
    private static final String DARK_2 = "#121A10";
    private static final String LIME = "#B7FF00";
    private static final String LIME_DARK = "#8CC900";
    private static final String WHITE = "#F8FAF5";
    private static final String TEXT = "#DDE5D7";
    private static final String GREY = "#9BA69A";
    private static final String MUTED = "#687266";
    private static final String BORDER = "#263323";


    // =====================================================
    // GET SCENE
    // =====================================================

    public static Scene getScene() {

        // =====================================================
        // MAIN CONTAINER
        // =====================================================

        VBox mainContainer = new VBox(70);

        mainContainer.setPadding(
                new Insets(55, 80, 70, 80)
        );

        mainContainer.setAlignment(Pos.TOP_CENTER);

        mainContainer.setStyle(
                "-fx-background-color: " + BLACK + ";"
        );


        // =====================================================
        // HEADER
        // =====================================================

        Label smallTitle = new Label(
                "THE STORY BEHIND THE PROJECT"
        );

        smallTitle.setFont(
                Font.font("Arial", FontWeight.BOLD, 13)
        );

        smallTitle.setTextFill(
                Color.web(LIME)
        );


        Label title = new Label(
                "ABOUT ADMITX"
        );

        title.setFont(
                Font.font("Arial", FontWeight.BOLD, 48)
        );

        title.setTextFill(
                Color.web(WHITE)
        );


        VBox header = new VBox(
                12,
                smallTitle,
                title
        );

        header.setAlignment(Pos.CENTER);


        // =====================================================
        // BIG SHASHI SIR HERO SECTION
        // =====================================================

        Image shashiImage = new Image(
                AboutUsPage.class
                        .getResourceAsStream(
                                "/assets/images/shashi-sir.png"
                        )
        );


        ImageView shashiImageView =
                new ImageView(shashiImage);


        shashiImageView.setFitWidth(300);

        shashiImageView.setFitHeight(390);

        shashiImageView.setPreserveRatio(true);

        shashiImageView.setSmooth(true);


        VBox imageBox = new VBox(
                shashiImageView
        );

        imageBox.setAlignment(Pos.CENTER);

        imageBox.setPadding(
                new Insets(8)
        );

        imageBox.setStyle(

                "-fx-background-color: " + DARK_2 + ";" +

                "-fx-background-radius: 25;" +

                "-fx-border-color: " + LIME + ";" +

                "-fx-border-width: 2;" +

                "-fx-border-radius: 25;"
        );


        // =====================================================
        // RIGHT SIDE CONTENT
        // =====================================================

        Label specialSmall = new Label(
                "SPECIAL RECOGNITION"
        );

        specialSmall.setFont(
                Font.font("Arial", FontWeight.BOLD, 13)
        );

        specialSmall.setTextFill(
                Color.web(LIME)
        );


        Label shashiTitle = new Label(
                "A Special Thanks\nto Shashi Sir"
        );

        shashiTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        38
                )
        );

        shashiTitle.setTextFill(
                Color.web(WHITE)
        );


        Label shashiRole = new Label(
                "Core2Web"
        );

        shashiRole.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        shashiRole.setTextFill(
                Color.web(LIME)
        );


        Label shashiDescription = new Label(

                "Every great journey begins with someone who inspires you to learn, " +

                "challenge yourself and think differently.\n\n" +

                "We sincerely thank Shashi Sir and Core2Web for their valuable guidance, " +

                "inspiration and contribution to our learning journey.\n\n" +

                "The knowledge, practical approach and strong programming foundation we gained " +

                "through this journey helped us transform our ideas into ADMITX."
        );

        shashiDescription.setFont(
                Font.font("Arial", 16)
        );

        shashiDescription.setTextFill(
                Color.web(TEXT)
        );

        shashiDescription.setWrapText(true);

        shashiDescription.setMaxWidth(520);

        shashiDescription.setLineSpacing(5);


        VBox shashiContent = new VBox(

                16,

                specialSmall,

                shashiTitle,

                shashiRole,

                shashiDescription
        );

        shashiContent.setAlignment(
                Pos.CENTER_LEFT
        );


        HBox shashiHero = new HBox(

                70,

                imageBox,

                shashiContent
        );

        shashiHero.setAlignment(
                Pos.CENTER
        );

        shashiHero.setMaxWidth(
                1000
        );

        shashiHero.setPadding(
                new Insets(35)
        );


        shashiHero.setStyle(

                "-fx-background-color: " + DARK + ";" +

                "-fx-background-radius: 28;" +

                "-fx-border-color: " + BORDER + ";" +

                "-fx-border-width: 1;" +

                "-fx-border-radius: 28;"
        );


        // =====================================================
        // DIVIDER
        // =====================================================

        Region divider1 =
                createDivider();


        // =====================================================
        // OUR MISSION
        // =====================================================

        Label projectSmall = new Label(
                "OUR MISSION"
        );

        projectSmall.setFont(
                Font.font("Arial", FontWeight.BOLD, 13)
        );

        projectSmall.setTextFill(
                Color.web(LIME)
        );


        Label projectTitle = new Label(

                "Simplifying the Journey\n" +
                "from Student to College"
        );

        projectTitle.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        36
                )
        );

        projectTitle.setTextFill(
                Color.web(WHITE)
        );


        Label projectDescription = new Label(

                "ADMITX is a smart College Admission Counselling and CAP Allotment " +

                "Management System designed to simplify the admission process.\n\n" +

                "Students can explore suitable colleges based on their CET percentile, " +

                "preferences, college cutoffs and seat availability.\n\n" +

                "The system also supports CAP allotment, Freeze and Betterment options, " +

                "making the complete counselling process more organized and transparent."
        );

        projectDescription.setFont(
                Font.font("Arial", 16)
        );

        projectDescription.setTextFill(
                Color.web(TEXT)
        );

        projectDescription.setWrapText(true);

        projectDescription.setMaxWidth(850);

        projectDescription.setLineSpacing(6);

        projectDescription.setTextAlignment(
                TextAlignment.CENTER
        );


        VBox projectSection = new VBox(

                18,

                projectSmall,

                projectTitle,

                projectDescription
        );

        projectSection.setAlignment(
                Pos.CENTER
        );

        projectSection.setMaxWidth(
                950
        );


        // =====================================================
        // ADMITX FEATURES
        // =====================================================

        HBox features = new HBox(20);

        features.setAlignment(
                Pos.CENTER
        );


        features.getChildren().addAll(

                createFeature(

                        "01",

                        "Smart Matching",

                        "Find suitable colleges based on eligibility and preferences."
                ),

                createFeature(

                        "02",

                        "CAP Allotment",

                        "Manage seats, cutoffs and student allotments efficiently."
                ),

                createFeature(

                        "03",

                        "Freeze & Betterment",

                        "Support multiple admission decisions across CAP rounds."
                )
        );


        // =====================================================
        // TEAM SECTION
        // =====================================================

        VBox teamSection = new VBox(22);

        teamSection.setAlignment(
                Pos.CENTER
        );

        teamSection.setMaxWidth(
                1000
        );


        Label teamSmall = new Label(
                "THE PEOPLE BEHIND ADMITX"
        );

        teamSmall.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        teamSmall.setTextFill(
                Color.web(LIME)
        );


        Label teamTitle = new Label(
                "Built Through Teamwork"
        );

        teamTitle.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        36
                )
        );

        teamTitle.setTextFill(
                Color.web(WHITE)
        );


        Label teamDescription = new Label(

                "ADMITX was developed through collaboration, creativity and continuous learning. " +

                "Every member contributed their ideas, skills and effort to bring this project to life."
        );

        teamDescription.setFont(
                Font.font("Arial", 16)
        );

        teamDescription.setTextFill(
                Color.web(GREY)
        );

        teamDescription.setWrapText(true);

        teamDescription.setMaxWidth(750);

        teamDescription.setTextAlignment(
                TextAlignment.CENTER
        );


        HBox teamNames = new HBox(15);

        teamNames.setAlignment(
                Pos.CENTER
        );


        teamNames.getChildren().addAll(

                createNameCard(
                        "Tejas Waghmare",
                        "Developer"
                ),

                createNameCard(
                        "Yash Batte",
                        "Developer"
                ),

                createNameCard(
                        "Sushant Salunke",
                        "Developer"
                ),

                createNameCard(
                        "Om Hubad",
                        "Developer"
                )
        );


        teamSection.getChildren().addAll(

                teamSmall,

                teamTitle,

                teamDescription,

                teamNames
        );


        // =====================================================
        // THANK YOU SECTION
        // =====================================================

        VBox thanksSection = new VBox(30);

        thanksSection.setAlignment(
                Pos.CENTER
        );

        thanksSection.setPadding(
                new Insets(40)
        );

        thanksSection.setMaxWidth(
                1000
        );


        thanksSection.setStyle(

                "-fx-background-color: " + DARK + ";" +

                "-fx-background-radius: 25;" +

                "-fx-border-color: " + BORDER + ";" +

                "-fx-border-radius: 25;"
        );


        Label thanksSmall = new Label(
                "WITH GRATITUDE"
        );

        thanksSmall.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        thanksSmall.setTextFill(
                Color.web(LIME)
        );


        Label thanksTitle = new Label(
                "Thank You"
        );

        thanksTitle.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        40
                )
        );

        thanksTitle.setTextFill(
                Color.web(WHITE)
        );


        Label thanksText = new Label(

                "A heartfelt thank you to everyone who supported, guided and encouraged us throughout this journey."
        );

        thanksText.setFont(
                Font.font("Arial", 16)
        );

        thanksText.setTextFill(
                Color.web(GREY)
        );


        // =====================================================
        // INSTRUCTORS
        // =====================================================

        Label instructorsTitle =
                createThanksHeading(
                        "OUR INSTRUCTORS"
                );


        HBox instructors = new HBox(30);

        instructors.setAlignment(
                Pos.CENTER
        );


        instructors.getChildren().addAll(

                createSimpleName("Sachin Sir"),

                createSimpleName("Pramod Sir"),

                createSimpleName("Akshay Sir")
        );


        // =====================================================
        // SUPER MENTORS
        // =====================================================

        Label mentorsTitle =
                createThanksHeading(
                        "OUR SUPER MENTORS"
                );


        HBox mentors = new HBox(40);

        mentors.setAlignment(
                Pos.CENTER
        );


        mentors.getChildren().addAll(

                createSimpleName("Shiv Sir"),

                createSimpleName("Subodh Sir")
        );


        Label finalMessage = new Label(

                "And a special thanks to all our Mentors and Team Leads\n" +

                "for their guidance, feedback, support and encouragement."
        );

        finalMessage.setFont(
                Font.font("Arial", 15)
        );

        finalMessage.setTextFill(
                Color.web(TEXT)
        );

        finalMessage.setTextAlignment(
                TextAlignment.CENTER
        );


        thanksSection.getChildren().addAll(

                thanksSmall,

                thanksTitle,

                thanksText,

                instructorsTitle,

                instructors,

                mentorsTitle,

                mentors,

                finalMessage
        );


        // =====================================================
        // FOOTER
        // =====================================================

        Label footerLine = new Label(

                "────────────────────────────────────────────────"
        );

        footerLine.setTextFill(
                Color.web(BORDER)
        );


        Label footer = new Label(

                "ADMITX  •  SMART COLLEGE ADMISSION COUNSELLING SYSTEM"
        );

        footer.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        footer.setTextFill(
                Color.web(MUTED)
        );


        VBox footerBox = new VBox(

                12,

                footerLine,

                footer
        );

        footerBox.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        mainContainer.getChildren().addAll(

                header,

                shashiHero,

                divider1,

                projectSection,

                features,

                teamSection,

                thanksSection,

                footerBox
        );


        // ====================================================
        BorderPane root = CounsellorLayout.create(
                "About Us",
                mainContainer
        );

        return new Scene(root);


    }


    // =====================================================
    // FEATURE CARD
    // =====================================================

    private static VBox createFeature(

            String number,

            String title,

            String description

    ) {

        Label numberLabel =
                new Label(number);

        numberLabel.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        numberLabel.setTextFill(
                Color.web(LIME)
        );


        Label titleLabel =
                new Label(title);

        titleLabel.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        titleLabel.setTextFill(
                Color.web(WHITE)
        );


        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setFont(
                Font.font("Arial", 14)
        );

        descriptionLabel.setTextFill(
                Color.web(GREY)
        );

        descriptionLabel.setWrapText(true);

        descriptionLabel.setTextAlignment(
                TextAlignment.CENTER
        );


        VBox card = new VBox(

                14,

                numberLabel,

                titleLabel,

                descriptionLabel
        );

        card.setAlignment(
                Pos.TOP_CENTER
        );

        card.setPadding(
                new Insets(25)
        );

        card.setPrefWidth(290);

        card.setMinHeight(190);


        card.setStyle(

                "-fx-background-color: " + DARK + ";" +

                "-fx-background-radius: 20;" +

                "-fx-border-color: " + BORDER + ";" +

                "-fx-border-radius: 20;"
        );


        return card;
    }


    // =====================================================
    // TEAM NAME CARD
    // =====================================================

    private static VBox createNameCard(

            String name,

            String role

    ) {

        Label nameLabel =
                new Label(name);

        nameLabel.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        nameLabel.setTextFill(
                Color.web(WHITE)
        );


        Label roleLabel =
                new Label(role);

        roleLabel.setFont(
                Font.font("Arial", 13)
        );

        roleLabel.setTextFill(
                Color.web(LIME_DARK)
        );


        VBox card = new VBox(

                8,

                nameLabel,

                roleLabel
        );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPadding(
                new Insets(20)
        );

        card.setPrefWidth(210);


        card.setStyle(

                "-fx-background-color: " + DARK + ";" +

                "-fx-background-radius: 15;" +

                "-fx-border-color: " + BORDER + ";" +

                "-fx-border-radius: 15;"
        );


        return card;
    }


    // =====================================================
    // SIMPLE THANK YOU NAME
    // =====================================================

    private static VBox createSimpleName(
            String name
    ) {

        Label star =
                new Label("✦");

        star.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        star.setTextFill(
                Color.web(LIME)
        );


        Label nameLabel =
                new Label(name);

        nameLabel.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        nameLabel.setTextFill(
                Color.web(WHITE)
        );


        VBox box = new VBox(

                8,

                star,

                nameLabel
        );

        box.setAlignment(
                Pos.CENTER
        );

        box.setMinWidth(
                180
        );


        return box;
    }


    // =====================================================
    // THANKS HEADING
    // =====================================================

    private static Label createThanksHeading(
            String text
    ) {

        Label heading =
                new Label(text);

        heading.setFont(

                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        heading.setTextFill(
                Color.web(LIME)
        );

        heading.setPadding(
                new Insets(
                        10,
                        0,
                        0,
                        0
                )
        );

        return heading;
    }


    // =====================================================
    // DIVIDER
    // =====================================================

    private static Region createDivider() {

        Region divider =
                new Region();

        divider.setPrefHeight(1);

        divider.setMaxWidth(
                850
        );

        divider.setStyle(

                "-fx-background-color: " + BORDER + ";"
        );


        return divider;
    }
}