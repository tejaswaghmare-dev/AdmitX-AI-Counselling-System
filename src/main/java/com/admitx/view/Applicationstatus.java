package com.admitx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Applicationstatus extends Application {

    private final String BG = "#F8F9FF";
    private final String BLUE = "#0B1C30";
    private final String PRIMARY = "#3159C9";
    private final String RED = "#BA1A1A";

    @Override
    public void start(Stage stage) {

        // =====================================================
        // ROOT
        // =====================================================

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = new VBox(18);

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );


        // =====================================================
        // HEADER
        // =====================================================

        Label breadcrumb = new Label(
                "Home    ›    Application    ›    Status"
        );

        breadcrumb.setStyle(
                "-fx-text-fill: #707584;" +
                "-fx-font-size: 14px;"
        );


        Label heading = new Label(
                "Application Status"
        );

        heading.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;"
        );


        Label subtitle = new Label(
                "Track the progress of your CAP registration application."
        );

        subtitle.setStyle(
                "-fx-text-fill: #444653;" +
                "-fx-font-size: 16px;"
        );


        VBox header = new VBox(
                6,
                breadcrumb,
                heading,
                subtitle
        );


        // =====================================================
        // MAIN AREA
        // =====================================================

        HBox mainArea = new HBox(18);

        HBox.setHgrow(
                mainArea,
                Priority.ALWAYS
        );


        VBox leftColumn = new VBox(18);

        HBox.setHgrow(
                leftColumn,
                Priority.ALWAYS
        );


        VBox rightColumn = new VBox(18);

        rightColumn.setPrefWidth(340);


        // =====================================================
        // APPLICATION REJECTED CARD
        // =====================================================

        VBox rejectedCard = new VBox(14);

        rejectedCard.setPadding(
                new Insets(20)
        );

        rejectedCard.setStyle(
                "-fx-background-color: #FFDAD6;" +
                "-fx-border-color: #E99A94;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );


        Label rejectedTitle = new Label(
                "ⓘ   Action Required: Application Rejected"
        );

        rejectedTitle.setStyle(
                "-fx-text-fill: " + RED + ";" +
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;"
        );


        Label rejectedText = new Label(
                "Your application has been reviewed and requires " +
                "corrections before it can be verified. Please review " +
                "the counselor remarks below."
        );

        rejectedText.setWrapText(true);

        rejectedText.setStyle(
                "-fx-text-fill: #222222;" +
                "-fx-font-size: 16px;"
        );


        // =====================================================
        // COUNSELOR REMARKS
        // =====================================================

        VBox remarks = new VBox(8);

        remarks.setPadding(
                new Insets(16)
        );

        remarks.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 5;"
        );


        Label remarkTitle = new Label(
                "COUNSELOR REMARKS"
        );

        remarkTitle.setStyle(
                "-fx-text-fill: #707584;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );


        Label remark = new Label(
                "\"The uploaded HSC Marksheet image is blurry and " +
                "illegible. Please re-upload a clear, high-resolution " +
                "scanned copy.\""
        );

        remark.setWrapText(true);

        remark.setStyle(
                "-fx-text-fill: #444653;" +
                "-fx-font-size: 15px;" +
                "-fx-font-style: italic;"
        );


        remarks.getChildren().addAll(
                remarkTitle,
                remark
        );


        // =====================================================
        // CORRECT BUTTON
        // =====================================================

        Button correctButton = new Button(
                "⇧  Correct & Resubmit"
        );

        correctButton.setStyle(
                "-fx-background-color: " + RED + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 11 20 11 20;" +
                "-fx-background-radius: 5;"
        );


        rejectedCard.getChildren().addAll(
                rejectedTitle,
                rejectedText,
                remarks,
                correctButton
        );


        // =====================================================
        // VERIFICATION TIMELINE
        // =====================================================

        VBox timeline = new VBox();

        timeline.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #C4C5D5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );


        Label timelineTitle = new Label(
                "Verification Timeline"
        );

        timelineTitle.setPadding(
                new Insets(18)
        );

        timelineTitle.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );


        Separator timelineSeparator = new Separator();


        VBox steps = new VBox(5);

        steps.setPadding(
                new Insets(20, 25, 25, 25)
        );


        addStep(
                steps,
                "✓",
                "Application Drafted",
                "Oct 12, 2024 - 10:30 AM",
                PRIMARY
        );


        addStep(
                steps,
                "✓",
                "Application Submitted",
                "Oct 14, 2024 - 02:15 PM",
                PRIMARY
        );


        addStep(
                steps,
                "✓",
                "Under Verification",
                "Assigned to E-Scrutiny Center (FC-402)\n" +
                "Oct 15, 2024 - 09:00 AM",
                PRIMARY
        );


        addStep(
                steps,
                "×",
                "Correction Required",
                "Verification halted due to document discrepancy.\n" +
                "Oct 16, 2024 - 11:45 AM",
                RED
        );


        addStep(
                steps,
                "⚙",
                "Verified",
                "Pending successful resubmission",
                "#9AA0AA"
        );


        timeline.getChildren().addAll(
                timelineTitle,
                timelineSeparator,
                steps
        );


        leftColumn.getChildren().addAll(
                rejectedCard,
                timeline
        );


        // =====================================================
        // APPLICATION SUMMARY
        // =====================================================

        VBox summary = new VBox(12);

        summary.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #C4C5D5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );


        Label summaryTitle = new Label(
                "Application Summary"
        );

        summaryTitle.setPadding(
                new Insets(18)
        );

        summaryTitle.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;"
        );


        Separator summarySeparator = new Separator();


        VBox summaryData = new VBox(14);

        summaryData.setPadding(
                new Insets(10, 18, 18, 18)
        );


        addInfo(
                summaryData,
                "Application ID",
                "EN24105839"
        );


        addInfo(
                summaryData,
                "Candidate Name",
                "Rahul Sharma"
        );


        addInfo(
                summaryData,
                "Candidature Type",
                "Maharashtra State (Type A)"
        );


        Label statusTitle = new Label(
                "Current Status"
        );

        statusTitle.setStyle(
                "-fx-text-fill: #707584;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );


        Label statusLabel = new Label(
                "Correction Required"
        );

        statusLabel.setStyle(
                "-fx-background-color: #FFDAD6;" +
                "-fx-text-fill: " + RED + ";" +
                "-fx-padding: 7 13 7 13;" +
                "-fx-background-radius: 20;"
        );


        summaryData.getChildren().addAll(
                statusTitle,
                statusLabel
        );


        summary.getChildren().addAll(
                summaryTitle,
                summarySeparator,
                summaryData
        );


        // =====================================================
        // HELP CARD
        // =====================================================

        VBox help = new VBox(12);

        help.setPadding(
                new Insets(18)
        );

        help.setStyle(
                "-fx-background-color: #EAF1FF;" +
                "-fx-border-color: #C4D5FF;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );


        Label helpTitle = new Label(
                "♧  Need Help?"
        );

        helpTitle.setStyle(
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );


        Label helpText = new Label(
                "If you are unsure how to correct the application, " +
                "refer to the user manual or contact support."
        );

        helpText.setWrapText(true);

        helpText.setStyle(
                "-fx-text-fill: #444653;" +
                "-fx-font-size: 15px;"
        );


        Label manual = new Label(
                "▣  Read User Manual"
        );

        manual.setStyle(
                "-fx-text-fill: #1749C6;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );


        Label contact = new Label(
                "⌕  Contact E-Scrutiny Center"
        );

        contact.setStyle(
                "-fx-text-fill: #1749C6;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );


        help.getChildren().addAll(
                helpTitle,
                helpText,
                manual,
                contact
        );


        rightColumn.getChildren().addAll(
                summary,
                help
        );


        // =====================================================
        // ADD LEFT + RIGHT
        // =====================================================

        mainArea.getChildren().addAll(
                leftColumn,
                rightColumn
        );


        // =====================================================
        // ADD HEADER + MAIN AREA
        // =====================================================

        content.getChildren().addAll(
                header,
                mainArea
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scroll = new ScrollPane(
                content
        );

        scroll.setFitToWidth(true);

        scroll.setStyle(
                "-fx-background-color: " + BG + ";"
        );


        root.setCenter(scroll);


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene = new Scene(
                root,
                1360,
                850
        );


        stage.setTitle(
                "MHT CET CAP Portal - Application Status"
        );

        stage.setScene(scene);

        stage.setMinWidth(1000);
        stage.setMinHeight(700);

        stage.show();
    }


    // =========================================================
    // TIMELINE STEP METHOD
    // =========================================================

    private void addStep(
            VBox parent,
            String symbol,
            String title,
            String description,
            String color
    ) {

        HBox row = new HBox(15);

        row.setAlignment(
                Pos.TOP_LEFT
        );

        row.setPadding(
                new Insets(8)
        );


        Label icon = new Label(
                symbol
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setPrefSize(
                45,
                45
        );


        icon.setStyle(
                "-fx-background-color: " + color + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );


        VBox text = new VBox(5);


        Label titleLabel = new Label(
                title
        );

        titleLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                (color.equals(RED)
                        ? RED
                        : BLUE) +
                ";"
        );


        Label descLabel = new Label(
                description
        );

        descLabel.setWrapText(true);

        descLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #555B6B;"
        );


        text.getChildren().addAll(
                titleLabel,
                descLabel
        );


        row.getChildren().addAll(
                icon,
                text
        );


        parent.getChildren().add(
                row
        );
    }


    // =========================================================
    // SUMMARY INFORMATION METHOD
    // =========================================================

    private void addInfo(
            VBox parent,
            String title,
            String value
    ) {

        Label titleLabel = new Label(
                title
        );

        titleLabel.setStyle(
                "-fx-text-fill: #707584;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );


        Label valueLabel = new Label(
                value
        );

        valueLabel.setWrapText(true);

        valueLabel.setStyle(
                "-fx-text-fill: #172033;" +
                "-fx-font-size: 16px;"
        );


        parent.getChildren().addAll(
                titleLabel,
                valueLabel
        );
    }


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        launch(args);
    }
}
