package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class GrievanceSubmissionPage {

    private static File proofFile;

    public static Scene getScene(){

        Label title = new Label("Raise Grievance");

        title.setStyle(
                "-fx-font-size:26;" +
                "-fx-font-weight:bold;"
        );

        TextArea grievance = new TextArea();

        grievance.setPromptText(
                "Describe your grievance..."
        );

        grievance.setPrefRowCount(6);

        Label fileName =
                new Label("No proof uploaded");

        Button upload =
                new Button("Upload Proof");

        upload.setOnAction(e->{

            FileChooser chooser =
                    new FileChooser();

            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Documents",
                            "*.pdf",
                            "*.jpg",
                            "*.png"
                    )
            );

            Stage stage =
                    (Stage)upload.getScene().getWindow();

            File file =
                    chooser.showOpenDialog(stage);

            if(file!=null){

                proofFile=file;

                fileName.setText(file.getName());
            }
        });

        Button back =
                new Button("Back");

        Button submit =
                new Button("Submit");

        back.setStyle(
                "-fx-background-color:#4D7C0F;" +
                "-fx-text-fill:white;"
        );

        submit.setStyle(
                "-fx-background-color:#65A30D;" +
                "-fx-text-fill:white;"
        );

        back.setOnAction(e->
                Navigation.goTo(
                        ProvisionalMeritPage.getScene()
                )
        );

        submit.setOnAction(e->
                Navigation.goTo(
                        FinalMeritPage.getScene()
                )
        );

        HBox buttons =
                new HBox(15,back,submit);

        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox content =
                new VBox(
                        20,
                        title,
                        grievance,
                        upload,
                        fileName,
                        buttons
                );

        content.setPadding(new Insets(35));

        return new Scene(
                StudentLayout.create(
                        "Grievance",
                        content
                )
        );
    }

    public static File getProofFile(){

        return proofFile;
    }
}