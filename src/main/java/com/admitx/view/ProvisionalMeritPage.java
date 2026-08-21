package com.admitx.view;

import com.admitx.view.Navigation;
import com.admitx.view.StudentLayout;
import com.admitx.model.ApplicationData;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProvisionalMeritPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        Label title = new Label("Provisional Merit List");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        VBox card = new VBox(18);

        card.setPadding(new Insets(25));
        card.setAlignment(Pos.CENTER_LEFT);

        card.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:10;" +
                "-fx-border-color:#D9F99D;" +
                "-fx-border-radius:10;"
        );

        card.getChildren().addAll(

                detail("Candidate", value(data.getCandidateName())),

                detail("Application ID", "MHTCET20260001"),

                detail("MHT CET Percentile", value(data.getCetPercentile())),

                detail("Provisional Merit No.", "1542"),

                detail("Category Rank", "Open - 742"),

                detail("Status", "Published")
        );

        Button grievance = new Button("Raise Grievance");

        grievance.setStyle(
                "-fx-background-color:#EA580C;" +
                "-fx-text-fill:white;" +
                "-fx-pref-width:180;" +
                "-fx-pref-height:40;"
        );

        grievance.setOnAction(e ->
                Navigation.goTo(
                        GrievanceSubmissionPage.getScene()
                )
        );

        Button dashboard = new Button("Dashboard");

        dashboard.setStyle(
                "-fx-background-color:#65A30D;" +
                "-fx-text-fill:white;" +
                "-fx-pref-width:160;" +
                "-fx-pref-height:40;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content = new VBox(25,
                title,
                card,
                grievance,
                dashboard
        );

        content.setPadding(new Insets(35));

        return new Scene(
                StudentLayout.create(
                        "Provisional Merit List",
                        content
                )
        );
    }

    private static VBox detail(String label, String value){

        Label l1 = new Label(label);

        l1.setStyle("-fx-font-weight:bold;");

        Label l2 = new Label(value);

        VBox box = new VBox(4,l1,l2);

        return box;
    }

    private static String value(String text){

        if(text==null || text.isBlank())
            return "Not Available";

        return text;
    }
}