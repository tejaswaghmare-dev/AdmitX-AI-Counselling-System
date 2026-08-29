package com.admitx.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.admitx.dao.NoticeDAO;
import com.admitx.model.Notice;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class NoticeBoardPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#141B14";

    private static final String BORDER =
            "#293529";

    private static final String LIME =
            "#B7FF00";

    private static final String WHITE =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    public static Scene getScene() {

        // =========================================================
        // FIRESTORE
        // =========================================================

        NoticeDAO noticeDAO =
                new NoticeDAO();

        List<Notice> publishedNotices =
                noticeDAO
                        .getPublishedNotices();

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Notice Board"
                );

        title.setStyle(
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Stay updated with important CAP counselling announcements."
                );

        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        // =========================================================
        // SECTION TITLE
        // =========================================================

        Label sectionTitle =
                new Label(
                        "LATEST NOTICES"
                );

        sectionTitle.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + LIME + ";"
        );

        // =========================================================
        // NOTICE LIST
        // =========================================================

        VBox notices =
                new VBox(
                        12
                );

        if (
                publishedNotices == null
                ||
                publishedNotices.isEmpty()
        ) {

            Label empty =
                    new Label(
                            "No notices have been published yet."
                    );

            empty.setStyle(
                    "-fx-font-size:13px;" +
                    "-fx-text-fill:"
                            + MUTED + ";" +
                    "-fx-padding:20px;"
            );

            notices.getChildren()
                    .add(
                            empty
                    );

        } else {

            for (
                    Notice notice :
                    publishedNotices
            ) {

                notices.getChildren()
                        .add(
                                createNotice(
                                        notice
                                )
                        );
            }
        }

        // =========================================================
        // NOTICE CARD
        // =========================================================

        VBox noticeCard =
                new VBox(
                        14,
                        sectionTitle,
                        notices
                );

        noticeCard.setPadding(
                new Insets(
                        22
                )
        );

        noticeCard.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        // =========================================================
        // NOTE
        // =========================================================

        Label note =
                new Label(
                        "Check the Notice Board regularly for CAP round schedules, "
                                + "document verification updates, merit announcements "
                                + "and option form updates."
                );

        note.setWrapText(
                true
        );

        note.setStyle(
                "-fx-background-color:#151B10;" +
                "-fx-text-fill:#B9C5B2;" +
                "-fx-font-size:12px;" +
                "-fx-padding:14px;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:#38452B;" +
                "-fx-border-radius:8px;"
        );

        // =========================================================
        // BUTTONS
        // =========================================================

        Button refresh =
                new Button(
                        "↻ Refresh"
                );

        styleDarkButton(
                refresh
        );

        refresh.setOnAction(e ->

                Navigation.goTo(
                        getScene()
                )
        );

        Button back =
                new Button(
                        "← Dashboard"
                );

        styleDarkButton(
                back
        );

        back.setOnAction(e ->

                Navigation.goTo(
                        StudentDashboardPage
                                .getScene()
                )
        );

        HBox buttons =
                new HBox(
                        10,
                        refresh,
                        back
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================================================
        // CONTENT
        // =========================================================

        VBox content =
                new VBox(
                        22,
                        heading,
                        noticeCard,
                        note,
                        buttons
                );

        content.setPadding(
                new Insets(
                        30
                )
        );

        content.setAlignment(
                Pos.TOP_LEFT
        );

        content.setStyle(
                "-fx-background-color:"
                        + BG + ";"
        );

        // =========================================================
        // SCROLL
        // =========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background:"
                        + BG + ";" +
                "-fx-background-color:"
                        + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Notice Board",
                        scrollPane
                )
        );
    }

    // =============================================================
    // CREATE NOTICE UI
    // =============================================================

    private static VBox createNotice(
            Notice notice
    ) {

        // =========================================================
        // TAG
        // =========================================================

        String tag =
                notice.getTag();

        if (
                tag == null
                ||
                tag.isBlank()
        ) {

            tag =
                    "GENERAL";
        }

        Label tagLabel =
                new Label(
                        tag
                );

        tagLabel.setStyle(
                "-fx-background-color:#1D2A10;" +
                "-fx-text-fill:"
                        + LIME + ";" +
                "-fx-font-size:9px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:5 9 5 9;" +
                "-fx-background-radius:14px;"
        );

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        notice.getTitle()
                );

        title.setStyle(
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + WHITE + ";"
        );

        // =========================================================
        // DESCRIPTION
        // =========================================================

        Label text =
                new Label(
                        notice.getDescription()
                );

        text.setWrapText(
                true
        );

        text.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        // =========================================================
        // DATE
        // =========================================================

        Label date =
                new Label(
                        formatDate(
                                notice.getCreatedAt()
                        )
                );

        date.setStyle(
                "-fx-font-size:10px;" +
                "-fx-text-fill:#657065;"
        );

        VBox textBox =
                new VBox(
                        6,
                        title,
                        text,
                        date
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox header =
                new HBox(
                        12,
                        textBox,
                        spacer,
                        tagLabel
                );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox box =
                new VBox(
                        header
                );

        box.setPadding(
                new Insets(
                        16
                )
        );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color:#0F150F;" +
                "-fx-background-radius:9px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:9px;"
        );

        return box;
    }

    // =============================================================
    // DATE
    // =============================================================

    private static String formatDate(
            long milliseconds
    ) {

        if (
                milliseconds <= 0
        ) {

            return "";
        }

        SimpleDateFormat formatter =
                new SimpleDateFormat(
                        "dd MMM yyyy, hh:mm a"
                );

        return formatter.format(
                new Date(
                        milliseconds
                )
        );
    }

    // =============================================================
    // BUTTON
    // =============================================================

    private static void styleDarkButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        button.setStyle(
                "-fx-background-color:#171F17;" +
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-border-color:#344034;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );
    }
}