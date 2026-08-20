package com.admitx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import javafx.stage.Stage;

public class LandingPage extends Application {

        public static Stage LandingPagestage;
        


        @Override
        public void start(Stage stage) {

                // =========================================================
                // HEADER
                // =========================================================

                Label title = new Label("AdmitX");
                title.setFont(Font.font("sans-serif", 42));
                title.setStyle(
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
                );

                Label subtitle = new Label(
                        "AI-Powered Admission Counselling Simulator \n Inspired by the MHT-CET Counseling Process"
                );
                subtitle.setFont(Font.font("sans-serif", 25));
                subtitle.setStyle(
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;"
                );

                VBox header = new VBox(10, title, subtitle);
                header.setAlignment(Pos.CENTER);
                header.setPadding(new Insets(25));

                header.setStyle("""
                        -fx-background-color:
                        linear-gradient(to right, #9C075C, #C2185B);
                        """);


                // =========================================================
                // MAIN TITLE
                // =========================================================

                Label chooseRole = new Label("Choose Your Role");

                chooseRole.setFont(Font.font("sans-serif", 20));

                chooseRole.setStyle("""
                        -fx-font-weight: bold;
                        -fx-text-fill: #202020;
                        """);


                // =========================================================
                // STUDENT IMAGE
                // =========================================================

                Image studentImg = new Image(
                        getClass().getResourceAsStream(
                                "/assets/images/s.png"
                        )
                );

                ImageView studentView = new ImageView(studentImg);

                studentView.setFitWidth(150);
                studentView.setFitHeight(150);
                studentView.setPreserveRatio(false);

                // Perfect circular clipping
                Circle studentClip = new Circle(90);
                studentClip.setCenterX(90);
                studentClip.setCenterY(90);

                studentView.setClip(studentClip);

                StackPane studentImagePane = new StackPane(studentView);

                studentImagePane.setPrefSize(180, 180);
                studentImagePane.setMaxSize(180, 180);

                studentImagePane.setBackground(
                        new Background(
                                new BackgroundFill(
                                        Color.web("#dbeafe"),
                                        new CornerRadii(100),
                                        Insets.EMPTY
                                )
                        )
                );


                // =========================================================
                // STUDENT TITLE
                // =========================================================

                Label studentTitle = new Label("Continue as a Student");
                studentTitle.setFont(Font.font("sans-serif",20));

        

                studentTitle.setStyle("""
                        -fx-font-weight: bold;
                        -fx-text-fill: #070808;
                        """);


                // =========================================================
                // STUDENT BUTTON
                // =========================================================

                
                Button studentBtn = new Button("Get started ->");

                studentBtn.setPrefWidth(200);
                studentBtn.setPrefHeight(45);

                studentBtn.setStyle("""
                        -fx-background-color: #051c4d;
                        -fx-text-fill: white;
                        -fx-font-size: 16px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 10;
                        -fx-cursor: hand;
                        """);


                // =========================================================
                // STUDENT CARD
                // =========================================================

                VBox studentCard = new VBox(15,studentImagePane,studentTitle, studentBtn);

                studentCard.setAlignment(Pos.CENTER);

                studentCard.setPadding(new Insets(25));

                studentCard.setPrefWidth(350);
                studentCard.setPrefHeight(400);

                studentCard.setStyle("""
                        -fx-background-color: white;
                        -fx-background-radius: 20;
                        -fx-border-radius: 20;
                        -fx-border-color: #d1d5db;
                        -fx-effect: dropshadow(
                        three-pass-box,
                        rgba(0,0,0,0.15),
                        15,
                        0,
                        0,
                        5
                        );
                        """);


                // =========================================================
                // COUNSELOR IMAGE
                // =========================================================

                Image counselorImg = new Image(
                        getClass().getResourceAsStream(
                                "/assets/images/counss.png"
                        )
                );

                ImageView counselorView = new ImageView(counselorImg);

                counselorView.setFitWidth(180);
                counselorView.setFitHeight(180);
                counselorView.setPreserveRatio(false);

                // Perfect circular clipping
                Circle counselorClip = new Circle(90);
                counselorClip.setCenterX(90);
                counselorClip.setCenterY(90);

                counselorView.setClip(counselorClip);

                StackPane counselorImagePane = new StackPane(counselorView);

                counselorImagePane.setPrefSize(180, 180);
                counselorImagePane.setMaxSize(180, 180);

                counselorImagePane.setBackground(
                        new Background(
                                new BackgroundFill(
                                        Color.web("#ccfbf1"),
                                        new CornerRadii(100),
                                        Insets.EMPTY
                                )
                        )
                );


                // =========================================================
                // COUNSELOR TITLE
                // =========================================================

                Label counselorTitle = new Label("Continue as a Counselor");
                counselorTitle.setFont(Font.font("sans-serif",20));

                

                counselorTitle.setStyle("""
                        -fx-font-weight: bold;
                        -fx-text-fill: #202423;
                        """);


                // =========================================================
                // COUNSELOR BUTTON
                // =========================================================

                Button counselorBtn = new Button("Get started->");
                counselorBtn.setFont(Font.font("sans-serif"));

                counselorBtn.setPrefWidth(200);
                counselorBtn.setPrefHeight(45);

                counselorBtn.setStyle("""
                        -fx-background-color: #06685c;
                        -fx-text-fill: white;
                        -fx-font-size: 16px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 10;
                        -fx-cursor: hand;
                        """);


                // =========================================================
                // COUNSELOR CARD
                // =========================================================

                VBox counselorCard = new VBox(15,counselorImagePane,counselorTitle,  counselorBtn);

                counselorCard.setAlignment(Pos.CENTER);

                counselorCard.setPadding(new Insets(25));

                counselorCard.setPrefWidth(350);
                counselorCard.setPrefHeight(400);

                counselorCard.setStyle("""
                        -fx-background-color: white;
                        -fx-background-radius: 20;
                        -fx-border-radius: 20;
                        -fx-border-color: #d1d5db;
                        -fx-effect: dropshadow(
                        three-pass-box,
                        rgba(0,0,0,0.15),
                        15,
                        0,
                        0,
                        5
                        );
                        """);


                // =========================================================
                // CARD HOVER EFFECT
                // =========================================================

                studentCard.setOnMouseEntered(e -> {
                studentCard.setScaleX(1.05);
                studentCard.setScaleY(1.05);
                });

                studentCard.setOnMouseExited(e -> {
                studentCard.setScaleX(1);
                studentCard.setScaleY(1);
                });


                counselorCard.setOnMouseEntered(e -> {
                counselorCard.setScaleX(1.05);
                counselorCard.setScaleY(1.05);
                });

                counselorCard.setOnMouseExited(e -> {
                counselorCard.setScaleX(1);
                counselorCard.setScaleY(1);
                });


                // =========================================================
                // CARDS CONTAINER
                // =========================================================

                HBox cards = new HBox(40,studentCard, counselorCard);

                cards.setAlignment(Pos.CENTER);


                // =========================================================
                // CENTER CONTENT
                // =========================================================

                VBox centerContent = new VBox(40,chooseRole,cards);

                centerContent.setAlignment(Pos.CENTER);

                centerContent.setPadding(new Insets(30));


                // =========================================================
                // FOOTER
                // =========================================================

                Label footer = new Label("© 2026 AdmitX | AI-Powered Counseling & Seat Allocation");
                footer.setFont(Font.font("sans-serif"));

                footer.setStyle("""
                        -fx-text-fill: #6b7280;
                        -fx-font-size: 13px;
                        """);

                footer.setPadding(new Insets(15));

                BorderPane.setAlignment(footer,Pos.CENTER);

                // =========================================================
                // ROOT
                // =========================================================

                BorderPane root = new BorderPane();

                root.setTop(header);
                root.setCenter(centerContent);
                root.setBottom(footer);

                root.setStyle("-fx-background-color: #f4f7fc;");


                // =========================================================
                // SCENE
                // =========================================================

                Scene scene = new Scene(
                        root,
                        1366,
                        700
                );

                // navigation for student button
                LandingPagestage = stage;
                
                studentBtn.setOnAction(e->{
                        StudentSignupPage studentSignup = new StudentSignupPage();

                        LandingPagestage.setScene(studentSignup.getScene());
                });

                // navigation for cousellor button

                counselorBtn.setOnAction(e->{
                        CounselorSignup counselorSignup = new CounselorSignup();

                        LandingPagestage.setScene(counselorSignup.getScenefromCounselorsignup());
                });

                // =========================================================
                // STAGE
                // =========================================================

                stage.setTitle("AdmitX");

                stage.setScene(scene);

                stage.setMinWidth(700);
                stage.setMinHeight(550);

                stage.show();
    }

}

// Learn. Practice. Understand the
// CAP Admission Process.

// Welcome to the official training environment for the Maharashtra
// Health and Technical Common Entrance Test (MHT CET)
// Centralised Admission Process (CAP) for Engineering. Experience
// the real workflow without real consequences.
// ⚠️ **IMPORTANT: This is a training simulator. Any data entered here will NOT affect actual CAP admissions.**
