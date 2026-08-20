package com.admitx.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class HelpCentrePage {

    public static Scene getScene() {

        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("❓ Help Centre");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Find answers to your questions and get support");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Help sections with dark theme
        TitledPane faq = createTitledPane(
                "📋 Frequently Asked Questions",
                "Q: How do I register?\nA: Click on Student Registration and fill in your details.\n\n" +
                "Q: What documents are required?\nA: You need SSC, HSC, and other academic documents.\n\n" +
                "Q: How is merit list prepared?\nA: Based on your MHT CET percentile and category.\n\n" +
                "Q: What is the CAP process?\nA: Registration → Application → Merit List → Option Form → CAP Rounds → Admission."
        );

        TitledPane counsellor = createTitledPane(
                "📞 Counsellor Contact",
                "For any assistance, please contact:\n\n" +
                "📞 Phone: 020-12345678\n" +
                "📧 Email: counsellor@admitx.edu.in\n" +
                "🕐 Timings: 10:00 AM - 5:00 PM (Mon-Fri)"
        );

        TitledPane guide = createTitledPane(
                "📖 User Guide",
                "Step-by-step guide to complete your admission process:\n\n" +
                "1. Registration - Create your account\n" +
                "2. Application - Fill personal, academic and reservation details\n" +
                "3. Document Upload - Upload required documents\n" +
                "4. Merit List - Check your provisional merit rank\n" +
                "5. Option Form - Fill your college preferences\n" +
                "6. CAP Rounds - Participate in counselling rounds\n" +
                "7. Admission - Confirm your admission"
        );

        TitledPane cap = createTitledPane(
                "🔄 CAP Process Guide",
                "Centralized Admission Process (CAP) Flow:\n\n" +
                "📝 Registration → 📋 Application Form → 📊 Merit List Generation → ✏️ Option Form Filling → 🔄 CAP Round 1 → 🔄 CAP Round 2 → ✅ Admission Confirmation\n\n" +
                "Each round includes: Allotment → Student Decision (Freeze/Float/Reject) → Reallocation"
        );

        faq.setExpanded(false);
        counsellor.setExpanded(false);
        guide.setExpanded(false);
        cap.setExpanded(false);

        VBox help = new VBox(10, faq, counsellor, guide, cap);
        help.setMaxWidth(800);

        Button dashboard = new Button("← Dashboard");
        dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        dashboard.setOnMouseEntered(e ->
            dashboard.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboard.setOnMouseExited(e ->
            dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboard.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        // Footer
        Label footer = new Label("© 2026 AdmitX · Help Centre");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.5;" +
                "-fx-padding: 20 0 0 0;"
        );

        content.getChildren().addAll(title, subtitle, help, dashboard, footer);

        return new Scene(
                StudentLayout.create("Help Centre", content)
        );
    }

    private static TitledPane createTitledPane(String title, String content) {
        Label contentLabel = new Label(content);
        contentLabel.setWrapText(true);
        contentLabel.setStyle(
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-line-spacing: 3px;" +
                "-fx-padding: 5 0 5 0;"
        );

        TitledPane pane = new TitledPane(title, contentLabel);
        pane.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;" +
                "-fx-text-fill: #E8EDF5;"
        );
        pane.setCollapsible(true);
        pane.setAnimated(true);

        // Style the title
        pane.setStyle(pane.getStyle() +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;"
        );

        return pane;
    }
}