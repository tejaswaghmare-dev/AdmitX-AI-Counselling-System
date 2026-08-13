package com.admitx.view;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class DocumentVerification {
    private final String applicationId;
    private final BorderPane page = new BorderPane();
    private final VBox documentList = new VBox();
    private final List<DocumentItem> documents = new ArrayList<>();
    private Label progressLabel;
    private ProgressBar progressBar;
    private Label pendingLabel;
    private static final String BG = "#F6F8FC";
    private static final String BLUE = "#123AA5";
    private static final String DARK = "#0B1B34";
    private static final String GREEN = "#187A45";
    private static final String RED = "#B42318";
    private static final String BORDER = "#C9CED9";
    public DocumentVerification(String applicationId) {
        this.applicationId = applicationId;
        documents.add(new DocumentItem(
                "Passport Size Photo",
                "Recent color photo with white background. Max 50KB.",
                50
        ));
        documents.add(new DocumentItem(
                "Scanned Signature",
                "Signature in black ink on white paper. Max 50KB.",
                50
        ));
        documents.add(new DocumentItem(
                "HSC Marksheet",
                "Original scanned copy required. Max 500KB.",
                500
        ));
        documents.add(new DocumentItem(
                "HSC Marksheet 2",
                "Required marksheet document. Max 500KB.",
                500
        ));
        documents.add(new DocumentItem(
                "MHT CET Marksheet",
                "Original MHT CET marksheet required. Max 500KB.",
                500
        ));
    }
    public Node getPage(Stage stage) {
        page.setStyle("-fx-background-color: " + BG + ";");
        VBox content = new VBox(18);
        content.setPadding(new Insets(25, 40, 30, 40));
        HBox breadcrumb = new HBox(12);
        Label home = label("Home", 15, false);
        Label arrow1 = label("›", 18, false);
        Label application = label("Application", 15, false);
        Label arrow2 = label("›", 18, false);
        Label current = label("Document Upload", 15, true);
        breadcrumb.getChildren().addAll(
                home, arrow1, application, arrow2, current
        );
        Label title = label(
                "Document Verification & Upload",
                34,
                true
        );
        Label subtitle = label(
                "Please provide clear, legible copies of all required documents to proceed with your application.",
                16,
                false
        );
        content.getChildren().addAll(
                breadcrumb,
                title,
                subtitle,
                journey()
        );
        HBox middle = new HBox(25);
        VBox left = new VBox(18);
        VBox right = new VBox(18);
        HBox.setHgrow(left, Priority.ALWAYS);
        right.setPrefWidth(330);
        left.getChildren().addAll(
                guidelineCard(),
                documentsCard()
        );
        right.getChildren().addAll(
                progressCard(),
                assistanceCard()
        );
        middle.getChildren().addAll(left, right);
        content.getChildren().add(middle);
        HBox bottom = bottomButtons(stage);
        content.getChildren().add(bottom);
        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: transparent;"
        );
        page.setCenter(scroll);
        refresh();
        return page;
    }
    private VBox journey() {
        VBox box = card();
        HBox steps = new HBox();
        steps.setAlignment(Pos.CENTER);
        steps.getChildren().addAll(
                step("✓", "Registration", true),
                line(true),
                step("✓", "Details", true),
                line(true),
                step("3", "Uploads", false),
                line(false),
                step("4", "Payment", false)
        );
        box.getChildren().add(steps);
        return box;
    }
    private VBox guidelineCard() {
        VBox box = card();
        Label title = label(
                "ⓘ   Important Upload Guidelines",
                20,
                true
        );
        Label rules = label(
                "• Accepted file formats: JPEG, JPG, PNG, PDF.\n" +
                "• Maximum file size: 500 KB. Photo/Signature: 50 KB.\n" +
                "• Ensure documents are scanned clearly and all text is legible.\n" +
                "• Blurred documents may be rejected.",
                15,
                false
        );
        rules.setWrapText(true);
        box.getChildren().addAll(title, rules);
        box.setStyle(
                "-fx-background-color: #EEF4FF;" +
                "-fx-border-color: #C6D2E9;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );
        return box;
    }
    private VBox documentsCard() {
        VBox box = new VBox();
        HBox heading = new HBox();
        heading.setAlignment(Pos.CENTER_LEFT);
        heading.setPadding(new Insets(16));
        Label title = label(
                "Required Documents Checklist",
                20,
                true
        );
        RegionSpace space = new RegionSpace();
        HBox.setHgrow(space, Priority.ALWAYS);
        Label count = new Label();
        count.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        count.setTextFill(Color.web(BLUE));
        heading.getChildren().addAll(title, space, count);
        documentList.setSpacing(0);
        box.getChildren().addAll(
                heading,
                documentList
        );
        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );
        return box;
    }
    private VBox progressCard() {
        VBox box = card();
        HBox heading = new HBox();
        Label title = label(
                "Upload Progress",
                20,
                true
        );
        RegionSpace space = new RegionSpace();
        HBox.setHgrow(space, Priority.ALWAYS);
        progressLabel = label("0%", 15, true);
        progressLabel.setTextFill(Color.web(BLUE));
        heading.getChildren().addAll(
                title,
                space,
                progressLabel
        );
        progressBar = new ProgressBar();
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressBar.setPrefHeight(12);
        pendingLabel = label(
                "5 compulsory documents are still pending upload.",
                15,
                false
        );
        pendingLabel.setWrapText(true);
        box.getChildren().addAll(
                heading,
                progressBar,
                pendingLabel
        );
        return box;
    }
    private VBox assistanceCard() {
        VBox box = card();
        Label title = label(
                "♧  Need Assistance?",
                20,
                true
        );
        Label text = label(
                "If you are facing issues uploading documents, " +
                "please contact technical support.",
                15,
                false
        );
        text.setWrapText(true);
        Label phone = label(
                "☎   1800-123-4567",
                15,
                true
        );
        Label email = label(
                "✉   support@maha-cet.org",
                15,
                true
        );
        box.getChildren().addAll(
                title,
                text,
                phone,
                email
        );
        return box;
    }
    private HBox documentRow(DocumentItem item) {
        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(15));
        VBox information = new VBox(5);
        HBox.setHgrow(information, Priority.ALWAYS);
        Label title = label(item.name, 18, true);
        Label description = label(
                item.description,
                14,
                false
        );
        description.setWrapText(true);
        Label status = label(
                item.file == null
                        ? "● Not Uploaded"
                        : "✓ Uploaded",
                13,
                true
        );
        status.setTextFill(
                item.file == null
                        ? Color.web("#555F72")
                        : Color.web(GREEN)
        );
        information.getChildren().addAll(
                title,
                description,
                status
        );
        Button upload = new Button(
                item.file == null ? "Upload" : "Replace"
        );
        upload.setPrefWidth(120);
        upload.setPrefHeight(40);
        upload.setStyle(
                "-fx-background-color: " +
                (item.file == null ? "white" : BLUE) +
                ";" +
                "-fx-text-fill: " +
                (item.file == null ? BLUE : "white") +
                ";" +
                "-fx-border-color: " + BLUE + ";" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
        upload.setOnAction(e ->
                uploadDocument(item, row)
        );
        Button preview = new Button("Preview");
        preview.setPrefWidth(100);
        preview.setPrefHeight(40);
        preview.setDisable(item.file == null);
        preview.setOnAction(e ->
                previewDocument(item)
        );
        HBox buttons = new HBox(8);
        buttons.getChildren().addAll(
                upload,
                preview
        );
        row.getChildren().addAll(
                information,
                buttons
        );
        row.setStyle(
                "-fx-border-color: #D4D8E0;" +
                "-fx-border-width: 0 0 1 0;"
        );
        return row;
    }
    private void uploadDocument(
            DocumentItem item,
            HBox oldRow) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle(
                "Upload " + item.name
        );
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(
                        "Supported Files",
                        "*.jpg",
                        "*.jpeg",
                        "*.png",
                        "*.pdf"
                )
        );
        File selected = chooser.showOpenDialog(
                oldRow.getScene().getWindow()
        );
        if (selected == null) {
            return;
        }
        long sizeKB = selected.length() / 1024;
        if (sizeKB > item.maxKB) {
            showError(
                    item.name +
                    " must be smaller than " +
                    item.maxKB +
                    " KB."
            );
            return;
        }
        try {
            Path folder = getStudentFolder();
            Files.createDirectories(folder);
            String extension = getExtension(
                    selected.getName()
            );
            String fileName =
                    cleanName(item.name) +
                    extension;
            Path destination =
                    folder.resolve(fileName);
            Files.copy(
                    selected.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );
            item.file = destination.toFile();
            saveMetadata();
            refresh();
        } catch (IOException ex) {
            showError(
                    "Unable to save document.\n" +
                    ex.getMessage()
            );
        }
    }
    private void previewDocument(DocumentItem item) {
        if (item.file == null) {
            return;
        }
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(
                        item.file
                );
            } else {
                showError(
                        "Preview is not supported on this system."
                );
            }
        } catch (IOException ex) {
            showError(
                    "Unable to open document."
            );
        }
    }
    private Path getStudentFolder() {
        return Paths.get(
                System.getProperty("user.home"),
                "AdmitX-Documents",
                applicationId
        );
    }
    private void saveMetadata() {
        try {
            Path folder = getStudentFolder();
            Files.createDirectories(folder);
            Path file = folder.resolve(
                    "document-status.txt"
            );
            StringBuilder data = new StringBuilder();
            data.append("Application ID: ")
                    .append(applicationId)
                    .append("\n\n");
            for (DocumentItem item : documents) {
                data.append(item.name)
                        .append(" = ")
                        .append(
                                item.file == null
                                        ? "NOT UPLOADED"
                                        : item.file.getName()
                        )
                        .append("\n");
            }
            Files.writeString(
                    file,
                    data.toString()
            );
        } catch (IOException ignored) {
        }
    }
    private void refresh() {
        documentList.getChildren().clear();
        int uploaded = 0;
        for (DocumentItem item : documents) {
            Path expected = getStudentFolder()
                    .resolve(
                            cleanName(item.name) +
                            ".jpg"
                    );
            if (Files.exists(expected)) {
                item.file = expected.toFile();
            }
            if (item.file != null) {
                uploaded++;
            }
            documentList.getChildren().add(
                    documentRow(item)
            );
        }
        int total = documents.size();
        double progress =
                (double) uploaded / total;
        progressBar.setProgress(progress);
        progressLabel.setText(
                (int) (progress * 100) + "%"
        );
        int pending = total - uploaded;
        pendingLabel.setText(
                pending +
                " compulsory documents are still pending upload."
        );
    }
    private HBox bottomButtons(Stage stage) {
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(10, 0, 0, 0));
        Button back = new Button("←  Back");
        back.setPrefSize(120, 45);
        back.setOnAction(e ->
                StudentDashboard.showDashboard(
                        stage,
                        "Rahul Kumar",
                        applicationId
                )
        );
        RegionSpace space = new RegionSpace();
        HBox.setHgrow(space, Priority.ALWAYS);
        Button continueButton =
                new Button("Save & Continue  →");
        continueButton.setPrefSize(200, 45);
        continueButton.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );
        continueButton.setOnAction(e -> {
            boolean complete = true;
            for (DocumentItem item : documents) {
                if (item.file == null) {
                    complete = false;
                    break;
                }
            }
            if (!complete) {
                showError(
                        "Please upload all required documents."
                );
            } else {
                saveMetadata();
                showInfo(
                        "Documents Saved",
                        "All documents have been saved successfully."
                );
            }
        });
        box.getChildren().addAll(
                back,
                space,
                continueButton
        );
        return box;
    }
    private VBox card() {
        VBox box = new VBox(15);
        box.setPadding(new Insets(20));
        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );
        return box;
    }
    private HBox step(
            String number,
            String name,
            boolean completed) {
        VBox content = new VBox(7);
        content.setAlignment(Pos.CENTER);
        Label circle = new Label(number);
        circle.setAlignment(Pos.CENTER);
        circle.setPrefSize(45, 45);
        circle.setStyle(
                "-fx-background-color: " +
                (completed ? GREEN : BLUE) +
                ";" +
                "-fx-background-radius: 50;"
        );
        circle.setTextFill(Color.WHITE);
        circle.setFont(
                Font.font("Arial", FontWeight.BOLD, 17)
        );
        Label title = label(
                name,
                14,
                true
        );
        content.getChildren().addAll(
                circle,
                title
        );
        HBox result = new HBox(content);
        result.setAlignment(Pos.CENTER);
        return result;
    }
    private Region line(boolean completed) {
        Region line = new Region();
        line.setPrefWidth(170);
        line.setPrefHeight(3);
        line.setStyle(
                "-fx-background-color: " +
                (completed ? GREEN : "#BEC4D1") +
                ";"
        );
        return line;
    }
    private Label label(
            String text,
            int size,
            boolean bold) {
        Label label = new Label(text);
        label.setFont(
                Font.font(
                        "Arial",
                        bold
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        size
                )
        );
        label.setTextFill(
                Color.web(DARK)
        );
        return label;
    }
    private String cleanName(String name) {
        return name
                .replaceAll("[^a-zA-Z0-9]+", "_")
                .replaceAll("_$", "");
    }
    private String getExtension(String name) {
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return "";
        }
        return name.substring(index)
                .toLowerCase();
    }
    private void showError(String message) {
        Alert alert = new Alert(
                Alert.AlertType.ERROR
        );
        alert.setTitle("Document Upload");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showInfo(
            String title,
            String message) {
        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private static class DocumentItem {
        String name;
        String description;
        int maxKB;
        File file;
        DocumentItem(
                String name,
                String description,
                int maxKB) {
            this.name = name;
            this.description = description;
            this.maxKB = maxKB;
        }
    }
    private static class RegionSpace
            extends Region {
    }
}

