package com.example.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class StudentLayout {

    public static BorderPane create(
            String pageTitle,
            Node content
    ) {

        BorderPane layout = new BorderPane();

        layout.setTop(Header.create(pageTitle));
        layout.setLeft(Sidebar.create());
        layout.setCenter(content);

        return layout;
    }
}