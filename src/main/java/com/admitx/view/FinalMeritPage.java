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

public class FinalMeritPage {

    public static Scene getScene(){

        ApplicationData data =
                ApplicationData.getInstance();

        Label title =
                new Label("Final Merit List");

        title.setStyle(
                "-fx-font-size:26;" +
                "-fx-font-weight:bold;"
        );

        VBox card =
                new VBox(18);

        card.setPadding(new Insets(25));

        card.setAlignment(Pos.CENTER_LEFT);

        card.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:10;" +
                "-fx-border-color:#D9F99D;" +
                "-fx-border-radius:10;"
        );

        card.getChildren().addAll(

                detail(
                        "Candidate",
                        value(data.getCandidateName())
                ),

                detail(
                        "Final Merit Rank",
                        "1498"
                ),

                detail(
                        "Category Rank",
                        "Open - 701"
                ),

                detail(
                        "Eligible CAP Rounds",
                        "CAP Round 1, 2 and 3"
                )
        );

        Button optionForm =
                new Button("Proceed to College Search");

        optionForm.setStyle(
                "-fx-background-color:#65A30D;" +
                "-fx-text-fill:white;" +
                "-fx-pref-width:220;" +
                "-fx-pref-height:40;"
        );

        optionForm.setOnAction(e->
                Navigation.goTo(
                        CollegeSearchPage.getScene()
                )
        );

        VBox content =
                new VBox(
                        25,
                        title,
                        card,
                        optionForm
                );

        content.setPadding(new Insets(35));

        return new Scene(
                StudentLayout.create(
                        "Final Merit List",
                        content
                )
        );
    }

    private static VBox detail(
            String label,
            String value){

        Label l1=new Label(label);

        l1.setStyle("-fx-font-weight:bold;");

        Label l2=new Label(value);

        return new VBox(4,l1,l2);
    }

    private static String value(String text){

        if(text==null || text.isBlank())
            return "Not Available";

        return text;
    }
}