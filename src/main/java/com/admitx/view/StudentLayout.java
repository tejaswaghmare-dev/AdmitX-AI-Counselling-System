package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class StudentLayout {

    private static final String BACKGROUND = "#0B100B";

    public static BorderPane create(
            String pageTitle,
            Node content
    ) {

        BorderPane layout = new BorderPane();

        layout.setTop(
                Header.create(pageTitle)
        );

        layout.setLeft(
                Sidebar.create(pageTitle)
        );

        StackPane contentArea = new StackPane();

        contentArea.setPadding(
                new Insets(25)
        );

        contentArea.setStyle(
                "-fx-background-color: "
                        + BACKGROUND + ";"
        );

        contentArea.getChildren().add(content);

        // Node chatbot = StudentChatbot.create();

        // contentArea.getChildren().add(chatbot);

        // StackPane.setAlignment(
        //         chatbot,
        //         Pos.TOP_CENTER
        // );

        // StackPane.setMargin(
        //         chatbot,
        //         new Insets(0, 20, 100, 0)
        // );

        layout.setCenter(contentArea);

        layout.setStyle(
                "-fx-background-color: "
                        + BACKGROUND + ";"
        );

        return layout;
    }
}