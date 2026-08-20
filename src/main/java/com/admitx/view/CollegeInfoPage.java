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

    public static Scene getScene(CollegeSearchPage.College college) {

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("🏛️ College Information");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label collegeName = new Label(college.getName());
        collegeName.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #60A5FA;" +
                "-fx-font-family: 'Segoe UI';"
        );

        // Details Card
        VBox detailsCard = new VBox(15);
        detailsCard.setPadding(new Insets(25, 30, 30, 30));
        detailsCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        GridPane details = new GridPane();
        details.setHgap(40);
        details.setVgap(18);
        details.setPadding(new Insets(10, 0, 5, 0));

        addDetail(details, "College Code", college.getCode(), 0, 0);
        addDetail(details, "College Name", college.getName(), 2, 0);
        addDetail(details, "District", college.getDistrict(), 0, 1);
        addDetail(details, "College Type", college.getType(), 2, 1);
        addDetail(details, "University", "Savitribai Phule Pune University", 0, 2);
        addDetail(details, "Intake", "120", 2, 2);

        detailsCard.getChildren().add(details);

        // Branches Section
        VBox branchesSection = new VBox(12);
        branchesSection.setPadding(new Insets(10, 0, 0, 0));

        Label branchesTitle = new Label("📚 Branches");
        branchesTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        VBox branches = new VBox(10);
        branches.getChildren().add(createBranch("Computer Engineering", "120", "95.20"));
        branches.getChildren().add(createBranch("Information Technology", "60", "93.45"));
        branches.getChildren().add(createBranch("Mechanical Engineering", "60", "82.30"));
        branches.getChildren().add(createBranch("Civil Engineering", "60", "78.50"));

        branchesSection.getChildren().addAll(branchesTitle, branches);

        // Cutoff Section
        VBox cutoffSection = new VBox(12);
        cutoffSection.setPadding(new Insets(10, 0, 0, 0));

        Label cutoffTitle = new Label("📊 Previous Year Dummy Cutoffs");
        cutoffTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        VBox cutoffCard = new VBox(12);
        cutoffCard.setPadding(new Insets(15, 20, 20, 20));
        cutoffCard.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.08);" +
                "-fx-border-radius: 12px;"
        );

        GridPane cutoff = new GridPane();
        cutoff.setHgap(40);
        cutoff.setVgap(14);

        addDetail(cutoff, "Computer Engineering", "95.20", 0, 0);
        addDetail(cutoff, "Information Technology", "93.45", 2, 0);
        addDetail(cutoff, "Mechanical Engineering", "82.30", 0, 1);
        addDetail(cutoff, "Civil Engineering", "78.50", 2, 1);

        cutoffCard.getChildren().add(cutoff);
        cutoffSection.getChildren().addAll(cutoffTitle, cutoffCard);

        // Buttons
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setPadding(new Insets(10, 0, 0, 0));

        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e ->
            backButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e ->
            backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnAction(e -> Navigation.goTo(CollegeSearchPage.getScene()));

        Button preferenceButton = new Button("➕ Add to Preferences");
        preferenceButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        preferenceButton.setOnMouseEntered(e ->
            preferenceButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        preferenceButton.setOnMouseExited(e ->
            preferenceButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        preferenceButton.setOnAction(e -> Navigation.goTo(PreferenceFillingPage.getScene()));

        buttons.getChildren().addAll(backButton, preferenceButton);

        content.getChildren().addAll(
                title,
                collegeName,
                detailsCard,
                branchesSection,
                cutoffSection,
                buttons
        );

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: #0A0A0F;" +
                "-fx-background-color: #0A0A0F;"
        );

        return new Scene(
                StudentLayout.create("College Information", scrollPane)
        );
    }

    private static void addDetail(GridPane grid, String labelText, String value, int column, int row) {
        Label label = new Label(labelText);
        label.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;"
        );

        Label valueLabel = new Label(value);
        valueLabel.setStyle(
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;"
        );

        VBox box = new VBox(5, label, valueLabel);
        box.setPrefWidth(280);
        box.setPadding(new Insets(6, 0, 6, 0));

        grid.add(box, column, row);
    }

    private static HBox createBranch(String branch, String intake, String cutoff) {
        Label branchLabel = new Label(branch);
        branchLabel.setPrefWidth(300);
        branchLabel.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;"
        );

        Label intakeLabel = new Label("📊 Intake: " + intake);
        intakeLabel.setPrefWidth(130);
        intakeLabel.setStyle(
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;"
        );

        Label cutoffLabel = new Label("📈 Cutoff: " + cutoff);
        cutoffLabel.setStyle(
                "-fx-text-fill: #60A5FA;" +
                "-fx-font-size: 13px;"
        );

        HBox row = new HBox(15, branchLabel, intakeLabel, cutoffLabel);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(12, 16, 12, 16));
        row.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.08);" +
                "-fx-border-radius: 8px;"
        );

        row.setOnMouseEntered(e ->
            row.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;"
            )
        );
        row.setOnMouseExited(e ->
            row.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.08);" +
                "-fx-border-radius: 8px;"
            )
        );

        return row;
    }
}