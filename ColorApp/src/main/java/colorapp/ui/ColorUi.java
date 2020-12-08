package colorapp.ui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import colorapp.dao.ImageAccessObject;
import colorapp.domain.ColorService;
import colorapp.domain.QuizService;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Application User Interface
 */
public class ColorUi extends Application {

    private ColorUIBuilder builder = new ColorUIBuilder();

    private QuizService quizService;
    private ColorService colorService;
    private ImageAccessObject imageAccessObject;

    private Scene colorListScene;
    private Scene colorQuestScene;

    private Label menuLabel = new Label();

    private List<ImageView> kuvat = new ArrayList<ImageView>();

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        colorService = new ColorService(properties);
        quizService = new QuizService(properties);
        imageAccessObject = new ImageAccessObject(properties);
    }

    @Override
    public void start(Stage primaryStage) {
        // colorListScene

        VBox colorListPane = new VBox(10);
        HBox inputPane = new HBox(10);
        colorListPane.setPadding(new Insets(10));

        {
            String username = "seikkailija";
            menuLabel.setText(username + " seikkailee");
        }

        primaryStage.setScene(colorListScene);

        Button startButton = new Button("Aloita väriseikkailu!");
        startButton.setOnAction(e -> {
            primaryStage.setScene(colorQuestScene);
        });
        startButton.setDisable(true);   // disable by default

        TextArea listOfPicsTextArea = builder.getPictureUrlListTextArea(startButton);

        Button debugButton = new Button("Tarkista ja hae kuvat.");
        debugButton.setOnAction(event -> {
            List<String> listOfPics = new ArrayList<>(Arrays.asList(listOfPicsTextArea.getText().split("\n")));
            System.out.println(listOfPics);
            List<String> verifyPicturePaths = imageAccessObject.verifyPicturePaths(listOfPics);
            System.out.println(verifyPicturePaths);

            if (verifyPicturePaths.size() > 0) {
                Alert alert = new Alert(AlertType.INFORMATION, "NON ACCEPTER!", ButtonType.OK);
                alert.setTitle("TENKKAPOO!"); // window title
                alert.setHeaderText("Ihan väärin nämä kuvien polut.");
                alert.setContentText(String.join("\n", verifyPicturePaths));
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            } else {
                Map<String, BufferedImage> images = imageAccessObject.getImages(listOfPics);

                // get some image for testing..
                // Object[] array = images.keySet().toArray();
                Collection<BufferedImage> values = images.values();
                ArrayList<BufferedImage> imagesArrayList = new ArrayList<>(values);

                // remove old pics
                colorListPane.getChildren().removeAll(kuvat);
                kuvat.clear();
                
                for (BufferedImage bufImage : imagesArrayList) {
                    // BufferedImage bufImage = imagesArrayList.get(0);
                    Image image = SwingFXUtils.toFXImage(bufImage, null);
                    // test showing image
                    {
                        // simple displays ImageView the image as is
                        // ImageView iv1 = new ImageView();
                        // iv1.setImage(image);
                        // resizes the image to have width of 100 while preserving the ratio and using
                        // higher quality filtering method; this ImageView is also cached to
                        // improve performance
                        ImageView iv2 = new ImageView();
                        iv2.setImage(image);
                        iv2.setFitWidth(50);
                        iv2.setPreserveRatio(true);
                        iv2.setSmooth(true);
                        iv2.setCache(true);
                        kuvat.add(iv2);
                    }
                }
                // test
                colorListPane.getChildren().addAll(kuvat);

                //
                Alert alert = new Alert(AlertType.INFORMATION, "LES PHOTOS SONT BONNES!", ButtonType.OK);
                alert.setTitle("Bueno!"); // window title
                alert.setHeaderText("Start quiz button enabled.");
                alert.setContentText(String.join("\n", verifyPicturePaths));
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();

                startButton.setDisable(false);
            }
        });

        // colorListPane.getChildren().addAll(inputPane, startButton);
        colorListPane.getChildren().addAll(inputPane, listOfPicsTextArea, debugButton, startButton);

        colorListScene = new Scene(colorListPane, 1600, 1200);

        // colorQuest scene

        VBox colorQuestPane = new VBox(10);

        HBox newQuessPane = new HBox(10);
        newQuessPane.setPadding(new Insets(10));
        TextField newQuessInput = new TextField();
        Label newQuessLabel = new Label("Arvauksesi");
        newQuessLabel.setPrefWidth(100);
        newQuessPane.getChildren().addAll(newQuessLabel, newQuessInput);

        Label quessCreationMessage = new Label();

        Button createNewQuessButton = new Button("Lähetä vastaus");
        createNewQuessButton.setPadding(new Insets(10));

        createNewQuessButton.setOnAction(e -> {
            String quess = newQuessInput.getText();

            String oikea = "FFFFFF"; // TODO: jostain

            if (quess.length() != 6) {
                quessCreationMessage.setText("RGB color must be 6 digits - i.e. FF00FF");
                quessCreationMessage.setTextFill(Color.RED);
            } else if (quizService.makeQuess(quess, oikea)) {
                quessCreationMessage.setText("");
                primaryStage.setScene(colorQuestScene);
            }

        });

        // abort -button
        Button abortButton = new Button("Poistu seikkailusta tästä");
        abortButton.setPadding(new Insets(10));

        abortButton.setOnAction(e -> {
            primaryStage.setScene(colorListScene);  // ok?
        });        

        colorQuestPane.getChildren().addAll(quessCreationMessage, newQuessPane, createNewQuessButton, abortButton);

        colorQuestScene = new Scene(colorQuestPane, 300, 250);

        // seutp primary stage

        primaryStage.setTitle("Seikkailu värimaailmassa");
        primaryStage.setScene(colorListScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
        });
    }

    @Override
    public void stop() {
        // tee lopetustoimenpiteet täällä
        System.out.println("sovellus sulkeutuu");
    }

    /**
     * Ui entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
