package colorapp.ui;

import java.awt.image.BufferedImage;

import colorapp.audio.MusicType;
import colorapp.audio.SoundType;
import colorapp.domain.RGB;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class QuestLoopSceneBuilder extends SceneBuilder {
    
    public QuestLoopSceneBuilder() {
        super();
    }

    /**
     * Creates Scene for Quiz-loop
     * @param ui
     * @param loop
     * @param bufImage
     * @return
     */
    public Scene getColorQuestScene(ColorApp ui, QuizLoop loop, BufferedImage bufImage) {
        ImageView oneImage = new ImageView();

        Image image = SwingFXUtils.toFXImage(bufImage, null);
        oneImage.setImage(image);
        oneImage.setFitWidth(800);
        oneImage.setPreserveRatio(true);
        oneImage.setSmooth(true);
        oneImage.setCache(true);

        HBox mainPane = new HBox();
        mainPane.setPadding(new Insets(10));

        VBox leftPane = new VBox(oneImage);
        leftPane.setPadding(new Insets(10));
        
        VBox rightPane = new VBox();
        rightPane.setPadding(new Insets(10));
        rightPane.setSpacing(20);
        mainPane.getChildren().addAll(leftPane, rightPane);

        Label newQuessLabel = new Label("Arvauksesi");
        newQuessLabel.setPrefWidth(100);     
        newQuessLabel.setPadding(new Insets(10));   

        TextField newQuessInput = new TextField();
        newQuessInput.setPrefWidth(100);
        newQuessInput.setPadding(new Insets(10));

        // Quess-button
        Button createNewQuessButton = createQuessButton(newQuessInput, bufImage, ui, loop);

        // Abort -button
        Button abortButton = new Button("Poistu seikkailusta tästä");
        abortButton.setOnAction(e -> {
            ui.audioPlayer.startMusic(MusicType.Menu);
            ui.primaryStage.setScene(ui.colorListScene);  // ok?
        });        
        abortButton.setPadding(new Insets(10));

        rightPane.getChildren().addAll(newQuessLabel, newQuessInput, createNewQuessButton, abortButton);

        return new Scene(mainPane, 1600, 1200);
    }    

    private boolean isValidInput(String quess) {
        boolean returnValue;
        if (quess.length() != 6 || !quess.matches("-?[0-9a-fA-F]+")) {
            returnValue = false;
        } else {
            returnValue = true;
        }
        return returnValue;
    }

    protected Alert createInvalidInputAlert() {
        Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
        alert.setTitle("User ERROR!"); // window title
        alert.setHeaderText("ERROR ERROR.");
        alert.setContentText("RGB color must be 6 digits hexadecimal value - i.e. FF00FF");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        return alert;
    }

    protected Alert createGoodInputAlert(ColorApp app, RGB quess, RGB correct) {
        double percentage = quess.percentageCorrect(correct);
        Alert alert = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
        alert.setTitle("Vastaus hyväksytty vastaukseksi!"); // window title
        alert.setHeaderText("Analyysi valmis.");
        alert.setContentText("Vastaus oli " + percentage + "% oikein.");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        if (percentage < 50) {
            app.audioPlayer.playSound(SoundType.AnswerBad);;
        } else if (percentage < 90) {
            app.audioPlayer.playSound(SoundType.AnswerOk);;
        } else {
            app.audioPlayer.playSound(SoundType.AnswerGood);;
        }
        return alert;
    }    

    protected Button createQuessButton(TextField newQuessInput, BufferedImage bufImage, ColorApp ui, QuizLoop loop) {
        Button createNewQuessButton = new Button("Lukitse Vastaus");
        createNewQuessButton.setPadding(new Insets(10));
        createNewQuessButton.setOnAction(e -> {
            String quess = newQuessInput.getText();
            RGB averageColor = colorService.getAverageColor(bufImage);
            if (!isValidInput(quess)) {
                Alert alert = createInvalidInputAlert();
                alert.show();
            } else {
                var rgbQuess = new RGB(quess);
                Alert alert = createGoodInputAlert(ui, rgbQuess, averageColor);
                alert.showAndWait();
                loop.answered(ui, rgbQuess, averageColor);
            }
        });
        createNewQuessButton.setPadding(new Insets(10));
        return createNewQuessButton;        
    }
}
