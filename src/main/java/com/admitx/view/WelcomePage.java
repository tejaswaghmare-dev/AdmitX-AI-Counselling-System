package com.admitx.view;

import com.admitx.view.Navigation;
import com.admitx.view.CounsellorLoginPage;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WelcomePage {

    public static Scene getScene() {

        // Dark gradient background
        BackgroundFill gradientFill = new BackgroundFill(
                new javafx.scene.paint.LinearGradient(
                        0, 0, 1, 1,
                        true,
                        javafx.scene.paint.CycleMethod.NO_CYCLE,
                        new javafx.scene.paint.Stop(0, Color.web("#0A0A0F")),
                        new javafx.scene.paint.Stop(0.4, Color.web("#1A1A2E")),
                        new javafx.scene.paint.Stop(0.7, Color.web("#16213E")),
                        new javafx.scene.paint.Stop(1, Color.web("#0A0A0F"))
                ),
                CornerRadii.EMPTY,
                Insets.EMPTY
        );
        
        Background background = new Background(gradientFill);

        // Main container
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50, 40, 50, 40));
        root.setBackground(background);

        // Logo/Icon section with glow
        VBox logoContainer = new VBox(5);
        logoContainer.setAlignment(Pos.CENTER);
        
        // Animated glowing icon
        Text iconText = new Text("🎓");
        iconText.setFont(Font.font("Segoe UI Emoji", 80));
        Glow glow = new Glow(0.3);
        iconText.setEffect(glow);
        
        Label capLabel = new Label("MHT CET CAP");
        capLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #6C8CBF;" +
                "-fx-letter-spacing: 4px;" +
                "-fx-opacity: 0.7;"
        );
        
        logoContainer.getChildren().addAll(iconText, capLabel);
        logoContainer.setPadding(new Insets(0, 0, 10, 0));

        // Main Title with dark theme glow
        Label title = new Label("AdmitX");
        title.setStyle(
                "-fx-font-size: 56px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );
        DropShadow titleShadow = new DropShadow(25, Color.web("#4A7FB5", 0.3));
        title.setEffect(titleShadow);

        // Subtitle
        Label subtitle = new Label("AI-Powered Counselling & Admission Portal");
        subtitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-opacity: 0.8;"
        );

        // Decorative divider with dark theme
        HBox divider = new HBox();
        divider.setAlignment(Pos.CENTER);
        Region line1 = new Region();
        line1.setPrefWidth(80);
        line1.setStyle("-fx-background-color: #4A7FB5; -fx-min-height: 2px; -fx-opacity: 0.4;");
        Label dot = new Label("◆");
        dot.setStyle("-fx-text-fill: #4A7FB5; -fx-font-size: 10px; -fx-padding: 0 10 0 10; -fx-opacity: 0.6;");
        Region line2 = new Region();
        line2.setPrefWidth(80);
        line2.setStyle("-fx-background-color: #4A7FB5; -fx-min-height: 2px; -fx-opacity: 0.4;");
        divider.getChildren().addAll(line1, dot, line2);

        // Feature badges with dark theme
        HBox badgeContainer = new HBox(15);
        badgeContainer.setAlignment(Pos.CENTER);
        badgeContainer.setPadding(new Insets(10, 0, 10, 0));
        
        String[][] badges = {
            {"📊", "Analytics"},
            {"🎯", "Smart Allotment"},
            {"🔄", "Multi-Round"}
        };
        
        for (String[] badge : badges) {
            VBox badgeBox = new VBox(2);
            badgeBox.setAlignment(Pos.CENTER);
            
            Label iconLabel = new Label(badge[0]);
            iconLabel.setStyle("-fx-font-size: 16px;");
            
            Label textLabel = new Label(badge[1]);
            textLabel.setStyle(
                    "-fx-text-fill: #8AA8C7;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;"
            );
            
            badgeBox.getChildren().addAll(iconLabel, textLabel);
            badgeBox.setStyle(
                    "-fx-background-color: rgba(74, 127, 181, 0.08);" +
                    "-fx-padding: 8 16 8 16;" +
                    "-fx-background-radius: 20px;" +
                    "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                    "-fx-border-radius: 20px;" +
                    "-fx-border-width: 1px;"
            );
            badgeContainer.getChildren().add(badgeBox);
        }

        // Button container
        VBox buttonContainer = new VBox(12);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(15, 0, 10, 0));

        // Dark theme button styles
        String primaryButtonStyle = 
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 48px;" +
                "-fx-background-radius: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.6), 15, 0, 0, 5);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;";
        
        String secondaryButtonStyle = 
                "-fx-background-color: rgba(30, 58, 95, 0.3);" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 48px;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1.5px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        String counsellorButtonStyle = 
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #5A7D9E;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.1);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;";

        // Student Login Button
        Button loginButton = new Button("🔐 Student Login");
        loginButton.setStyle(primaryButtonStyle);
        loginButton.setOnMouseEntered(e -> 
            loginButton.setStyle(
                primaryButtonStyle.replace("#1E3A5F", "#2A4A75")
            )
        );
        loginButton.setOnMouseExited(e -> 
            loginButton.setStyle(primaryButtonStyle)
        );
        
        // Student Registration Button
        Button registerButton = new Button("📝 Student Registration");
        registerButton.setStyle(secondaryButtonStyle);
        registerButton.setOnMouseEntered(e -> 
            registerButton.setStyle(
                secondaryButtonStyle.replace("rgba(30, 58, 95, 0.3)", "rgba(30, 58, 95, 0.5)")
            )
        );
        registerButton.setOnMouseExited(e -> 
            registerButton.setStyle(secondaryButtonStyle)
        );

        // Counsellor Button
        Button counsellorButton = new Button("👤 Counsellor Login");
        counsellorButton.setStyle(counsellorButtonStyle);
        counsellorButton.setOnMouseEntered(e -> 
            counsellorButton.setStyle(
                counsellorButtonStyle.replace("rgba(74, 127, 181, 0.1)", "rgba(74, 127, 181, 0.2)")
            )
        );
        counsellorButton.setOnMouseExited(e -> 
            counsellorButton.setStyle(counsellorButtonStyle)
        );

        // Guide Button
        Button guideButton = new Button("📖 User Guide");
        guideButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #3D5A78;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-underline: true;" +
                "-fx-opacity: 0.6;"
        );
        guideButton.setOnMouseEntered(e -> 
            guideButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #5A7D9E;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-underline: true;" +
                "-fx-opacity: 0.8;"
            )
        );
        guideButton.setOnMouseExited(e -> 
            guideButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #3D5A78;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-underline: true;" +
                "-fx-opacity: 0.6;"
            )
        );

        // Action handlers
        loginButton.setOnAction(e -> Navigation.goTo(StudentLoginPage.getScene()));
        registerButton.setOnAction(e -> Navigation.goTo(StudentRegistrationPage.getScene()));
        counsellorButton.setOnAction(e -> Navigation.goTo(CounsellorLoginPage.getScene()));
        guideButton.setOnAction(e -> showGuide());

        // Add all buttons to container
        buttonContainer.getChildren().addAll(
                loginButton,
                registerButton,
                counsellorButton
        );

        // Footer with dark theme
        Label footer = new Label("© 2026 AdmitX · All rights reserved");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 12px;" +
                "-fx-opacity: 0.5;"
        );
        
        // Decorative line above footer
        Region footerLine = new Region();
        footerLine.setPrefWidth(200);
        footerLine.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-min-height: 1px;"
        );
        
        VBox footerBox = new VBox(8);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setPadding(new Insets(20, 0, 0, 0));
        footerBox.getChildren().addAll(footerLine, footer);

        // Assemble everything
        root.getChildren().addAll(
                logoContainer,
                title,
                subtitle,
                divider,
                badgeContainer,
                buttonContainer,
                guideButton,
                footerBox
        );

        // Scene with dark theme
        Scene scene = new Scene(root, 900, 700);
        
        // Add subtle animation to title
        title.setScaleX(0.95);
        title.setScaleY(0.95);
        
        // Fade-in animation
        javafx.animation.FadeTransition ft = new javafx.animation.FadeTransition(
                javafx.util.Duration.millis(800), root
        );
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        return scene;
    }

    private static void showGuide() {
        // Implement guide dialog or navigation
        System.out.println("User Guide clicked - implement guide view");
    }
}