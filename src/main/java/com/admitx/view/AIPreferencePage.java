package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AIPreferencePage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String FIELD = "#101610";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene(String aiResult) {

        Label badge = new Label("✦  AI PREFERENCE ANALYSIS");
        badge.setStyle(
                "-fx-background-color:#1D2A10;" +
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:8 14 8 14;" +
                "-fx-background-radius:20px;" +
                "-fx-border-color:#3D5520;" +
                "-fx-border-radius:20px;"
        );

        Label title = new Label("AI Preference Recommendation");
        title.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label subtitle = new Label(
                "Review the AI analysis, then choose whether to use the AI-recommended order or keep your original preference order."
        );
        subtitle.setWrapText(true);
        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox heading = new VBox(8, badge, title, subtitle);

        Label analysisTitle = new Label("ANALYSIS & RECOMMENDATION");
        analysisTitle.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + LIME + ";"
        );

        TextArea analysisArea = new TextArea(cleanResult(aiResult));
        analysisArea.setEditable(false);
        analysisArea.setWrapText(true);
        analysisArea.setPrefRowCount(22);
        analysisArea.setStyle(
                "-fx-control-inner-background:" + FIELD + ";" +
                "-fx-background-color:" + FIELD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-highlight-fill:" + LIME + ";" +
                "-fx-highlight-text-fill:#071007;" +
                "-fx-font-size:13px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;"
        );

        VBox analysisCard = new VBox(14, analysisTitle, analysisArea);
        analysisCard.setPadding(new Insets(22));
        analysisCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        Label note = new Label(
                "AI suggestions are advisory only. Actual CAP allotment depends on official cutoffs, category, seat availability and round movement."
        );
        note.setWrapText(true);
        note.setStyle(
                "-fx-background-color:#151B10;" +
                "-fx-text-fill:#B9C5B2;" +
                "-fx-font-size:12px;" +
                "-fx-padding:16px;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:#38452B;" +
                "-fx-border-radius:8px;"
        );

        Button originalButton = new Button("Go with Original Preference");
        styleSecondaryButton(originalButton);
        originalButton.setOnAction(e ->
                Navigation.goTo(OptionPreviewPage.getScene())
        );

        Button aiButton = new Button("✦ Go with AI Preference");
        stylePrimaryButton(aiButton);
        aiButton.setOnAction(e -> {
            boolean applied = PreferenceFillingPage.applyAiPreferenceOrder(aiResult);

            if (!applied) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("AI Preference");
                alert.setHeaderText("AI order could not be applied automatically");
                alert.setContentText(
                        "Your original preference order has been kept. You can review the AI advice and reorder the list manually."
                );
                alert.showAndWait();
            }

            Navigation.goTo(OptionPreviewPage.getScene());
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox buttons = new HBox(12, originalButton, spacer, aiButton);
        buttons.setAlignment(Pos.CENTER_LEFT);

        VBox content = new VBox(
                22,
                heading,
                analysisCard,
                note,
                buttons
        );
        content.setPadding(new Insets(20, 24, 30, 24));
        content.setStyle("-fx-background-color:" + BG + ";");

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        scrollPane.setStyle(
                "-fx-background:" + BG + ";" +
                "-fx-background-color:" + BG + ";"
        );

        return new Scene(
                StudentLayout.create("AI Preference", scrollPane)
        );
    }

    private static String cleanResult(String result) {
        if (result == null || result.isBlank()) {
            return "No AI analysis is available.";
        }

        int markerIndex = result.lastIndexOf("AI_ORDER:");
        if (markerIndex >= 0) {
            return result.substring(0, markerIndex).trim();
        }

        return result.trim();
    }

    private static void stylePrimaryButton(Button button) {
        button.setPrefHeight(44);
        button.setPadding(new Insets(0, 20, 0, 20));
        button.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
        );
    }

    private static void styleSecondaryButton(Button button) {
        button.setPrefHeight(44);
        button.setPadding(new Insets(0, 20, 0, 20));
        button.setStyle(
                "-fx-background-color:#171F17;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-border-color:#344034;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );
    }
}
