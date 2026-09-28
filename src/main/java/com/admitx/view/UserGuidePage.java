package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class UserGuidePage {

    public static Scene getScene() {

        // =====================================================
        // HEADER
        // =====================================================

        Label logo = new Label("ADMITX");

        logo.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #C6E92F;"
        );

        Label tagline = new Label("SMARTER ADMISSIONS");

        tagline.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #B8B8B0;"
        );

        VBox logoBox = new VBox(2, logo, tagline);

        BorderPane header = new BorderPane();

        header.setLeft(logoBox);

        header.setPadding(new Insets(14, 35, 14, 35));

        header.setStyle(
                "-fx-background-color: #050606;" +
                "-fx-border-color: transparent transparent #3A3415 transparent;" +
                "-fx-border-width: 0 0 1 0;"
        );


        // =====================================================
        // PAGE TITLE
        // =====================================================

        Label title = new Label("Before You Continue");

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #F5F5F0;"
        );

        Label subtitle = new Label(
                "Understand how the AdmitX admission simulation works."
        );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #AAA9A0;"
        );


        // =====================================================
        // IMPORTANT NOTICE
        // =====================================================

        Label noticeIcon = new Label("⚠");

        noticeIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-text-fill: #C6E92F;"
        );

        Label noticeTitle = new Label("SIMULATION NOTICE");

        noticeTitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #C6E92F;"
        );

        Label noticeText = new Label(
                "AdmitX provides simulated admission results for educational and guidance purposes."
        );

        noticeText.setWrapText(true);

        noticeText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #E5E5DF;"
        );

        VBox noticeContent = new VBox(4, noticeTitle, noticeText);

        HBox noticeBox = new HBox(
                12,
                noticeIcon,
                noticeContent
        );

        noticeBox.setAlignment(Pos.CENTER_LEFT);

        noticeBox.setPadding(new Insets(15, 18, 15, 18));

        noticeBox.setStyle(
                "-fx-background-color: #171813;" +
                "-fx-border-color: #9BBE20;" +
                "-fx-border-width: 1;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );


        // =====================================================
        // INFORMATION CARDS - 2 COLUMN GRID
        // =====================================================

        GridPane cardsGrid = new GridPane();

        cardsGrid.setHgap(14);
        cardsGrid.setVgap(14);

        cardsGrid.setMaxWidth(1050);


        VBox card1 = createCard(
                "01",
                "ABOUT ADMITX",
                "A smart simulation that helps you understand college counselling and seat allotment."
        );

        VBox card2 = createCard(
                "02",
                "SIMULATION RESULTS",
                "Results shown are simulated and may differ from official CET/CAP allotments."
        );

        VBox card3 = createCard(
                "03",
                "NO GUARANTEE",
                "Actual admission depends on merit, eligibility, cut-offs and seat availability."
        );

        VBox card4 = createCard(
                "04",
                "YOUR RESPONSIBILITY",
                "Enter correct percentile and preferences for a more accurate simulation."
        );

        VBox card5 = createCard(
                "05",
                "AI & CAP GUIDANCE",
                "Recommendations and CAP rounds are educational features of the AdmitX simulation."
        );

        VBox card6 = createCard(
                "✓",
                "ALWAYS VERIFY",
                "Check official CET/CAP sources before making any real admission decision."
        );


        cardsGrid.add(card1, 0, 0);
        cardsGrid.add(card2, 1, 0);

        cardsGrid.add(card3, 0, 1);
        cardsGrid.add(card4, 1, 1);

        cardsGrid.add(card5, 0, 2);
        cardsGrid.add(card6, 1, 2);


        // Make both columns equal width
        for (int i = 0; i < 2; i++) {
            javafx.scene.layout.ColumnConstraints column =
                    new javafx.scene.layout.ColumnConstraints();

            column.setPercentWidth(50);

            cardsGrid.getColumnConstraints().add(column);
        }


        // =====================================================
        // AGREEMENT
        // =====================================================

        CheckBox agreeCheckBox = new CheckBox(
                "I understand that AdmitX provides simulated results for educational purposes."
        );

        agreeCheckBox.setWrapText(true);

        agreeCheckBox.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #F0F0EB;"
        );


        Button continueButton = new Button("AGREE & CONTINUE  →");

        continueButton.setPrefWidth(250);
        continueButton.setPrefHeight(42);

        continueButton.setDisable(true);

        setButtonDisabledStyle(continueButton);


        agreeCheckBox.selectedProperty().addListener(
                (observable, oldValue, selected) -> {

                    continueButton.setDisable(!selected);

                    if (selected) {
                        continueButton.setStyle(
                                "-fx-background-color: #C6E92F;" +
                                "-fx-text-fill: #080A08;" +
                                "-fx-font-size: 13px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8;" +
                                "-fx-cursor: hand;"
                        );
                    } else {
                        setButtonDisabledStyle(continueButton);
                    }
                }
        );


        continueButton.setOnAction(e ->
                Navigation.goTo(StudentLoginPage.getScene())
        );


        VBox agreementBox = new VBox(
                12,
                agreeCheckBox,
                continueButton
        );

        agreementBox.setAlignment(Pos.CENTER);

        agreementBox.setPadding(new Insets(18));

        agreementBox.setMaxWidth(1050);

        agreementBox.setStyle(
                "-fx-background-color: #111210;" +
                "-fx-border-color: #3A3B34;" +
                "-fx-border-width: 1;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = new VBox(
                16,
                title,
                subtitle,
                noticeBox,
                cardsGrid,
                agreementBox
        );

        content.setAlignment(Pos.TOP_CENTER);

        content.setPadding(new Insets(25, 40, 20, 40));

        content.setMaxWidth(1100);


        // =====================================================
        // CENTER WRAPPER
        // =====================================================

        VBox centerWrapper = new VBox(content);

        centerWrapper.setAlignment(Pos.TOP_CENTER);

        centerWrapper.setPadding(new Insets(0, 20, 0, 20));


        // =====================================================
        // FOOTER
        // =====================================================

        Label footer = new Label(
                "© 2026 ADMITX  •  Smarter Admissions"
        );

        footer.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #686860;"
        );

        HBox footerBox = new HBox(footer);

        footerBox.setAlignment(Pos.CENTER);

        footerBox.setPadding(new Insets(10));


        // =====================================================
        // ROOT
        // =====================================================

        BorderPane root = new BorderPane();

        root.setTop(header);

        root.setCenter(centerWrapper);

        root.setBottom(footerBox);

        root.setStyle(
                "-fx-background-color: #050606;"
        );


        return new Scene(root, 1200, 800);
    }


    // =====================================================
    // CARD CREATOR
    // =====================================================

    private static VBox createCard(
            String number,
            String heading,
            String content
    ) {

        Label numberLabel = new Label(number);

        numberLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #C6E92F;"
        );


        Label headingLabel = new Label(heading);

        headingLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #F5F5F0;"
        );


        HBox headingBox = new HBox(
                8,
                numberLabel,
                headingLabel
        );

        headingBox.setAlignment(Pos.CENTER_LEFT);


        Label contentLabel = new Label(content);

        contentLabel.setWrapText(true);

        contentLabel.setStyle(
                "-fx-font-size: 11.5px;" +
                "-fx-text-fill: #B9B9B1;" +
                "-fx-line-spacing: 2px;"
        );


        VBox card = new VBox(
                8,
                headingBox,
                contentLabel
        );

        card.setPadding(new Insets(16));

        card.setPrefHeight(110);

        card.setStyle(
                "-fx-background-color: #242522;" +
                "-fx-border-color: #3A3B37;" +
                "-fx-border-width: 1;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );


        GridPane.setHgrow(card, Priority.ALWAYS);

        return card;
    }


    // =====================================================
    // DISABLED BUTTON STYLE
    // =====================================================

    private static void setButtonDisabledStyle(Button button) {

        button.setStyle(
                "-fx-background-color: #292A28;" +
                "-fx-text-fill: #6C6D68;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;"
        );
    }
}