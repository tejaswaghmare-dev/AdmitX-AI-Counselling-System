package com.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Header {

    public static HBox create(String pageTitle) {

        Label title = new Label(pageTitle);

        title.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label portal = new Label("MHT CET CAP Counselling Portal");

        portal.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #BEF264;"
        );

        HBox header = new HBox(20, title, portal);

        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(18, 25, 18, 25));

        header.setStyle(
                "-fx-background-color: #000000;"
        );

        return header;
    }
}