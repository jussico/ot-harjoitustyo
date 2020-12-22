package colorapp.ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import colorapp.audio.AudioPlayer;
import colorapp.audio.MusicType;
import colorapp.domain.ColorAnalysis;
import colorapp.service.FileService;
import colorapp.service.ImageService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Application Main Class
 */
public class ColorApp extends Application {

    protected MainSceneBuilder builder = new MainSceneBuilder();
    protected QuestLoopSceneBuilder questBuilder = new QuestLoopSceneBuilder();
    protected ColorAnalysis colorService;
    protected ImageService imageAccessObject;
    protected Scene colorListScene;
    protected Label menuLabel = new Label();
    protected List<ImageView> kuvat = new ArrayList<ImageView>();
    protected FileChooser fileChooser;
    protected FileService fileService;
    protected TextArea listOfPicsTextArea;
    protected Map<String, BufferedImage> images;
    protected QuizLoop quizLoop;
    protected AudioPlayer audioPlayer;

    @Override
    public void init() throws Exception {
        colorService = new ColorAnalysis();
        imageAccessObject = new ImageService();
        fileService = new FileService();
        fileChooser = builder.getFileChooser();
        quizLoop = new QuizLoop();
        this.audioPlayer = new AudioPlayer();
    }

    protected Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        colorListScene = builder.getColorListScene(this);
        primaryStage.setTitle("Seikkailu värimaailmassa");
        primaryStage.setScene(colorListScene);
        primaryStage.show();
        audioPlayer.startMusic(MusicType.Menu);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
