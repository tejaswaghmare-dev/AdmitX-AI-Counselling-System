package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;
import com.example.model.ApplicationData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReservationDetailsPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        Label title = new Label("Reservation Details");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        ComboBox<String> category = createYesNoCombo();
        TextField caste = new TextField();

        ComboBox<String> validityCertificate = createYesNoCombo();
        ComboBox<String> ncl = createYesNoCombo();

        ComboBox<String> ews = createYesNoCombo();
        TextField income = new TextField();

        ComboBox<String> minority = createYesNoCombo();
        ComboBox<String> defence = createYesNoCombo();

        ComboBox<String> orphan = createYesNoCombo();

        GridPane form = new GridPane();

        form.setHgap(20);
        form.setVgap(20);
        form.setPadding(new Insets(20));

        addField(form, "Category", category, 0, 0);
        addField(form, "Caste", caste, 2, 0);

        addField(
                form,
                "Validity Certificate",
                validityCertificate,
                0,
                1
        );

        addField(form, "NCL", ncl, 2, 1);

        addField(form, "EWS", ews, 0, 2);
        addField(form, "Income", income, 2, 2);

        addField(form, "Minority", minority, 0, 3);
        addField(form, "Defence", defence, 2, 3);

        addField(form, "Orphan", orphan, 0, 4);

        Button backButton = new Button("Back");
        Button nextButton = new Button("Save & Continue");

        backButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        nextButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        backButton.setOnAction(e ->
                Navigation.goTo(HomeUniversityPage.getScene())
        );

        nextButton.setOnAction(e -> {

            data.setCategory(
                    category.getValue()
            );

            data.setCaste(
                    caste.getText()
            );

            data.setValidityCertificate(
                    validityCertificate.getValue()
            );

            data.setNcl(
                    ncl.getValue()
            );

            data.setEws(
                    ews.getValue()
            );

            data.setIncome(
                    income.getText()
            );

            data.setMinority(
                    minority.getValue()
            );

            data.setDefence(
                    defence.getValue()
            );

            data.setOrphan(
                    orphan.getValue()
            );

            Navigation.goTo(
                    DocumentUploadPage.getScene()
            );
        });

        HBox buttons = new HBox(
                15,
                backButton,
                nextButton
        );

        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox content = new VBox(
                15,
                title,
                form,
                buttons
        );

        content.setPadding(new Insets(30));

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        ScrollPane scrollPane = new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Reservation Details",
                        scrollPane
                )
        );
    }

    private static ComboBox<String> createYesNoCombo() {

        ComboBox<String> comboBox = new ComboBox<>();

        comboBox.getItems().addAll(
                "Yes",
                "No"
        );

        comboBox.setPromptText("Select");

        return comboBox;
    }

    private static void addField(
            GridPane grid,
            String labelText,
            Control control,
            int column,
            int row
    ) {

        Label label = new Label(labelText);

        label.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        control.setPrefWidth(300);
        control.setPrefHeight(38);

        VBox box = new VBox(
                6,
                label,
                control
        );

        grid.add(box, column, row);
    }
}