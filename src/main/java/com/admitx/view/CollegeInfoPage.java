package com.admitx.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CollegeInfoPage {

    public static Scene getScene(
            CollegeSearchPage.College college) {

        Label title = new Label("College Information");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label collegeName =
                new Label(college.getName());

        collegeName.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        Label subtitle =
                new Label(
                        college.getDistrict() +
                        " • " +
                        college.getType()
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #3F6212;"
        );

        VBox collegeHeader =
                new VBox(
                        6,
                        collegeName,
                        subtitle
                );

        collegeHeader.setPadding(
                new Insets(20)
        );

        collegeHeader.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Label basicTitle =
                createSectionTitle("College Details");

        GridPane details =
                new GridPane();

        details.setHgap(25);
        details.setVgap(18);
        details.setPadding(new Insets(20));

        addDetail(
                details,
                "College Code",
                college.getCode(),
                0,
                0
        );

        addDetail(
                details,
                "College Name",
                college.getName(),
                2,
                0
        );

        addDetail(
                details,
                "District",
                college.getDistrict(),
                0,
                1
        );

        addDetail(
                details,
                "College Type",
                college.getType(),
                2,
                1
        );

        addDetail(
                details,
                "University",
                "Savitribai Phule Pune University",
                0,
                2
        );

        addDetail(
                details,
                "Intake",
                "120",
                2,
                2
        );

        VBox detailsCard =
                new VBox(
                        basicTitle,
                        details
                );

        detailsCard.setPadding(
                new Insets(5, 0, 5, 0)
        );

        detailsCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Label branchesTitle =
                createSectionTitle("Available Branches");

        VBox branches =
                new VBox(10);

        branches.getChildren().add(
                createBranch(
                        "Computer Engineering",
                        "120",
                        "95.20"
                )
        );

        branches.getChildren().add(
                createBranch(
                        "Information Technology",
                        "60",
                        "93.45"
                )
        );

        branches.getChildren().add(
                createBranch(
                        "Mechanical Engineering",
                        "60",
                        "82.30"
                )
        );

        branches.getChildren().add(
                createBranch(
                        "Civil Engineering",
                        "60",
                        "78.50"
                )
        );

        VBox branchCard =
                new VBox(
                        branchesTitle,
                        branches
                );

        branchCard.setPadding(
                new Insets(20)
        );

        branchCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Label cutoffTitle =
                createSectionTitle(
                        "Previous Year Dummy Cutoffs"
                );

        GridPane cutoff =
                new GridPane();

        cutoff.setHgap(25);
        cutoff.setVgap(15);

        addDetail(
                cutoff,
                "Computer Engineering",
                "95.20",
                0,
                0
        );

        addDetail(
                cutoff,
                "Information Technology",
                "93.45",
                2,
                0
        );

        addDetail(
                cutoff,
                "Mechanical Engineering",
                "82.30",
                0,
                1
        );

        addDetail(
                cutoff,
                "Civil Engineering",
                "78.50",
                2,
                1
        );

        VBox cutoffCard =
                new VBox(
                        cutoffTitle,
                        cutoff
                );

        cutoffCard.setPadding(
                new Insets(20)
        );

        cutoffCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Button backButton =
                new Button("Back");

        backButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        CollegeSearchPage.getScene()
                )
        );

        Button preferenceButton =
                new Button("Add to Preferences");

        preferenceButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        preferenceButton.setOnAction(e ->
                Navigation.goTo(
                        PreferenceFillingPage.getScene()
                )
        );

        HBox buttons =
                new HBox(
                        15,
                        backButton,
                        preferenceButton
                );

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        VBox content =
                new VBox(
                        20,
                        title,
                        collegeHeader,
                        detailsCard,
                        branchCard,
                        cutoffCard,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "College Information",
                        scrollPane
                )
        );
    }

    private static Label createSectionTitle(
            String text) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        return label;
    }

    private static void addDetail(
            GridPane grid,
            String labelText,
            String value,
            int column,
            int row) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #3F6212;"
        );

        VBox box =
                new VBox(
                        5,
                        label,
                        valueLabel
                );

        box.setPrefWidth(280);

        grid.add(
                box,
                column,
                row
        );
    }

    private static HBox createBranch(
            String branch,
            String intake,
            String cutoff) {

        Label branchLabel =
                new Label(branch);

        branchLabel.setPrefWidth(300);

        branchLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label intakeLabel =
                new Label("Intake: " + intake);

        intakeLabel.setPrefWidth(130);

        intakeLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #3F6212;"
        );

        Label cutoffLabel =
                new Label("Cutoff: " + cutoff);

        cutoffLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        HBox row =
                new HBox(
                        15,
                        branchLabel,
                        intakeLabel,
                        cutoffLabel
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(12)
        );

        row.setStyle(
                "-fx-background-color: #F7FEE7;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 7px;"
        );

        return row;
    }
}