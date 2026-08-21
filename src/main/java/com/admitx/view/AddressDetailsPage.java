package com.admitx.view;

import com.admitx.view.Navigation;
import com.admitx.view.StudentLayout;
import com.admitx.model.ApplicationData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddressDetailsPage {

    public static Scene getScene() {
        ApplicationData data = ApplicationData.getInstance();
        Label title = new Label("Address Details");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        TextArea permanentAddress = new TextArea();
        permanentAddress.setPromptText("Enter Permanent Address");
        permanentAddress.setPrefRowCount(3);

        TextArea correspondenceAddress = new TextArea();
        correspondenceAddress.setPromptText("Enter Correspondence Address");
        correspondenceAddress.setPrefRowCount(3);

        ComboBox<String> state = new ComboBox<>();
        state.getItems().addAll(
                "Maharashtra",
                "Gujarat",
                "Karnataka",
                "Madhya Pradesh",
                "Goa",
                "Other"
        );
        state.setPromptText("Select State");

        TextField district = new TextField();
        district.setPromptText("Enter District");

        TextField taluka = new TextField();
        taluka.setPromptText("Enter Taluka");

        TextField pinCode = new TextField();
        pinCode.setPromptText("Enter PIN Code");

        GridPane form = new GridPane();

        form.setHgap(20);
        form.setVgap(20);
        form.setPadding(new Insets(20));

        addField(
                form,
                "Permanent Address",
                permanentAddress,
                0,
                0
        );

        addField(
                form,
                "Correspondence Address",
                correspondenceAddress,
                2,
                0
        );

        addField(
                form,
                "State",
                state,
                0,
                1
        );

        addField(
                form,
                "District",
                district,
                2,
                1
        );

        addField(
                form,
                "Taluka",
                taluka,
                0,
                2
        );

        addField(
                form,
                "PIN Code",
                pinCode,
                2,
                2
        );

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
                Navigation.goTo(PersonalDetailsPage.getScene())
        );

        nextButton.setOnAction(e -> {

            data.setPermanentAddress(
                    permanentAddress.getText()
            );

            data.setCorrespondenceAddress(
                    correspondenceAddress.getText()
            );

            data.setState(
                    state.getValue()
            );

            data.setDistrict(
                    district.getText()
            );

            data.setTaluka(
                    taluka.getText()
            );

            data.setPinCode(
                    pinCode.getText()
            );

            Navigation.goTo(
                    AcademicDetailsPage.getScene()
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
                        "Address Details",
                        scrollPane
                )
        );
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