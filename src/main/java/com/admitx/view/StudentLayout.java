package com.admitx.view;

import javafx.geometry.Insets;
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

        layout.setTop(Header.create(pageTitle));
        layout.setLeft(Sidebar.create());

        StackPane contentArea = new StackPane();
        contentArea.setPadding(new Insets(25));
        contentArea.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        contentArea.getChildren().add(content);

        layout.setCenter(contentArea);

        layout.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        return layout;
    }
}