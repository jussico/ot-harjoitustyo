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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorUi extends Application {
    private QuizService quizService;
    private ColorService colorService;
    private ImageAccessObject imageAccessObject;

    private Scene colorListScene;
    private Scene colorQuestScene;

    private Label menuLabel = new Label();

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

        String username = "seikkailija";
        menuLabel.setText(username + " seikkailee");

        primaryStage.setScene(colorListScene);

        Button startButton = new Button("start the quest");
        startButton.setOnAction(e -> {
            primaryStage.setScene(colorQuestScene);
        });

        String pic1 = "https://upload.wikimedia.org/wikipedia/commons/5/56/Donald_Trump_official_portrait.jpg";

        String pic2 = "https://upload.wikimedia.org/wikipedia/commons/b/b4/Loser_sign_croped.jpg";

        // editable list of strings
        TextArea listOfPicsTextArea = new TextArea() {
            {
                setWrapText(true);
                setText(pic1 +"\n" + pic2);
            }
        };
        Button debugButton = new Button("Debug list stuff");
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
                BufferedImage bufImage = imagesArrayList.get(0);

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

                    List<ImageView> kuvat = Arrays.asList(iv2);
                    // test
                    colorListPane.getChildren().addAll(kuvat);
                }
            }

        });

        // colorListPane.getChildren().addAll(inputPane, startButton);
        colorListPane.getChildren().addAll(inputPane, startButton, listOfPicsTextArea, debugButton);

        colorListScene = new Scene(colorListPane, 300, 250);

        // colorQuest scene

        VBox colorQuestPane = new VBox(10);

        HBox newQuessPane = new HBox(10);
        newQuessPane.setPadding(new Insets(10));
        TextField newQuessInput = new TextField();
        Label newQuessLabel = new Label("quess");
        newQuessLabel.setPrefWidth(100);
        newQuessPane.getChildren().addAll(newQuessLabel, newQuessInput);

        Label quessCreationMessage = new Label();

        Button createNewQuessButton = new Button("quess");
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

        colorQuestPane.getChildren().addAll(quessCreationMessage, newQuessPane, createNewQuessButton);

        colorQuestScene = new Scene(colorQuestPane, 300, 250);

        // seutp primary stage

        primaryStage.setTitle("ColorQuests");
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

    public static void main(String[] args) {
        launch(args);
    }

}
