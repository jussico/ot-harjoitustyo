package colorapp.ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import colorapp.audio.MusicType;
import colorapp.domain.RGB;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class QuizLoop {

    private Iterator<BufferedImage> imageIterator;
    private List<RGB> answers;
    private List<RGB> correctAnswers;

    /**
     * set up the quiz-loop and start looping through pictures
     * @param app
     */
    public void loop(ColorApp app) {
        app.audioPlayer.startMusic(MusicType.Game);
        this.answers = new ArrayList<RGB>();
        this.correctAnswers = new ArrayList<RGB>();
        Collection<BufferedImage> values = app.images.values();
        ArrayList<BufferedImage> imagesArrayList = new ArrayList<>(values);
        this.imageIterator = imagesArrayList.iterator();
        next(app); // loop all the pics
    }

    /**
     * @param ui
     * @param answer
     * @param correctAnswer
     */
    public void answered(ColorApp ui, RGB answer, RGB correctAnswer) {
        this.answers.add(answer);
        this.correctAnswers.add(correctAnswer);
        next(ui);
    }

    /**
     * Move on to next picture in the quiz
     * @param ui
     */
    public void next(ColorApp app) {
        if (imageIterator.hasNext()) {
            BufferedImage next = imageIterator.next();
            Scene colorQuestScene = app.questBuilder.getColorQuestScene(app, this, next);
            app.primaryStage.setScene(colorQuestScene);
        } else {
            gameOver(app);
        }
    }

    /**
     * All pictures looped through. wrap it up.
     * @param app
     */
    private void gameOver(ColorApp app) {
        Alert alert = new Alert(AlertType.INFORMATION, "KAIKKI OHI!", ButtonType.OK);
        alert.setTitle("Vastaukset!"); // window title
        alert.setHeaderText("Valmiit ovat vastaukset.");
        List<String> content = new ArrayList<String>();
        for (int x = 0; x < answers.size(); x++) {
            RGB quess = answers.get(x);
            RGB correct = correctAnswers.get(x);
            String rivi = "vastauksesi: " + quess + " oikea vastaus: " + correct + " läheisyys prosentteina: " + quess.percentageCorrect(correct);
            content.add(rivi);
        }
        double percentageCorrect = RGB.percentageCorrect(answers, correctAnswers);
        content.add("Lopputulemana keskimäärin " + percentageCorrect + "% oikein.");

        if (percentageCorrect > 90) {
            app.audioPlayer.startMusic(MusicType.GameOverVictory);
        } else {
            app.audioPlayer.startMusic(MusicType.GameOverFailure);
        }

        String contentString = content.stream().reduce((t, u) -> t + "\n" + u).get();
        alert.setContentText(contentString);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(1280);
        alert.showAndWait();
        app.audioPlayer.startMusic(MusicType.Menu);
        app.primaryStage.setScene(app.colorListScene);
    }

}
