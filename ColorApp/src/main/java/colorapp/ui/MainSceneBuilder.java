package colorapp.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Class to build UI components
 */
public class MainSceneBuilder extends SceneBuilder {

    public MainSceneBuilder() {
        super();
    }

    /**
    * Creates FileChooser
    */
    public FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
        return fileChooser;
    }

    /**
     * Creates MenuBar
     * @param ui
     * @return
     */
    protected MenuBar getMenu(ColorApp ui) {
        // create a menu
        Menu m = new Menu("Menu");

        // create menuitems
        MenuItem m1 = new MenuItem("Avaa Lista-tiedosto");

        m1.setOnAction(event -> {
            File file = ui.fileChooser.showOpenDialog(ui.primaryStage);
            if (file != null) {
                List<String> fileLines = ui.fileService.readFile(file);
                String linesAsText = fileLines.stream().reduce((t, u) -> t + "\n" + u).get();
                ui.listOfPicsTextArea.setText(linesAsText);
            }
        });

        MenuItem m2 = new MenuItem("Tallenna Lista-tiedosto");
        m2.setOnAction(event -> {
            File file = ui.fileChooser.showSaveDialog(ui.primaryStage);
            if (file != null) {
                ui.fileService.writeFile(file, ui.listOfPicsTextArea.getText());
            }
        });
        
        MenuItem m3 = new MenuItem("Exit");
        m3.setOnAction(event -> {
            System.exit(0);
        });

        // add menu items to menu
        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);

        // create a menubar
        MenuBar mb = new MenuBar();

        // add menu to menubar
        mb.getMenus().add(m);

        return mb;
    }

    /**
     * Creates TextArea for list of pictures 
     * @param startButton
     * @return text area which lists picture urls
     */
    protected TextArea getPictureUrlListTextArea(Button startButton) {

        List<String> asList = Arrays.asList(
            "https://media.deseretdigital.com/file/9290918263?crop%3Dtop%3A0%7Cleft%3A0%7Cwidth%3A1260%7Cheight%3A670%7Cgravity%3ACenter%26quality%3D55%26interlace%3Dnone%26resize%3Dwidth%3A1260%26order%3Dresize%2Ccrop%26c%3D14%26a%3De0f131f0",
            "https://www.telegraph.co.uk/content/dam/Travel/2018/December/12-apostles-iStock-174850284.jpg",
            "https://images.newscientist.com/wp-content/uploads/2019/04/02124658/g2bgwn-2.jpg",
            "https://images.unsplash.com/photo-1543971883-436917f70bdf?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MTR8fGdyZWF0JTIwd2FsbCUyMG9mJTIwY2hpbmF8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&w=1000&q=80"
        // "https://www.nationalgeographic.com/content/dam/animals/thumbs/rights-exempt/fish/g/great-white-shark_thumb.jpg",
        // "https://cichicago.net/wp-content/uploads/2017/11/14799070396_c42d2982e3_b.jpg",
        // "https://ak.picdn.net/shutterstock/videos/9268259/thumb/1.jpg?ip=x480",
        // "https://www.shockya.com/news/wp-content/uploads/Oz-The-Great-And-Powerful-Movie-Review.jpg",
        // "https://media-cdn.tripadvisor.com/media/photo-s/13/98/8f/c2/great-wall-hiking-tours.jpg",
        // "https://e360.yale.edu/assets/site/_1500x1500_fit_center-center_80/Adam_Welz_shark.jpg",
        // "https://wl-brightside.cf.tsp.li/resize/728x/jpg/33f/b8a/60863d5189af87971c3874fccd.jpg",
        // "https://images.unsplash.com/photo-1567604539011-ce1f37c5d657?ixid=MXwxMjA3fDB8MHxzZWFyY2h8M3x8dGhlJTIwZ3JlYXQlMjB3YWxsJTIwb2YlMjBjaGluYXxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80",
        // "https://picturecorrect-wpengine.netdna-ssl.com/wp-content/uploads/2014/08/great-photography-tips-570x380.jpg",
        // "https://www.aucklandmuseum.com/getmedia/eab45d3f-00e2-4a9a-b63a-861fc1a336fa/great-outdoors-700px-467px.jpg?ext=.jpg&width=950",
        // "https://i.pinimg.com/originals/88/bc/dd/88bcddfd91a03c530d201ed282bd74d8.jpg",
        // "file:///E:/kuva.jpg"
        );

        TextArea listOfPicsTextArea = new TextArea() {
            String linesAsText = asList.stream().reduce((t, u) -> t + "\n" + u).get();
            {
                setWrapText(true);
                setText(linesAsText);
            }
        };
        listOfPicsTextArea.textProperty().addListener((obs, old, niu) -> {
            startButton.setDisable(true);
        });
        return listOfPicsTextArea;
    }

    /**
     * Creates Main Scene
     * @param ui
     * @return
     */
    public Scene getColorListScene(ColorApp ui) {
        VBox colorListPane = new VBox(10);
        HBox inputPane = new HBox(10);
        colorListPane.setPadding(new Insets(10));
        {
            String username = "seikkailija";
            ui.menuLabel.setText(username + " seikkailee");
        }
        VBox menuVBox = new VBox(ui.builder.getMenu(ui)); 
        Button startButton = new Button("Aloita väriseikkailu!");
        startButton.setOnAction(e -> {
            
            ui.quizLoop.loop(ui);
        });
        startButton.setDisable(true);   // disable by default
        ui.listOfPicsTextArea = ui.builder.getPictureUrlListTextArea(startButton);

        Button debugButton = createDebugButton(ui, colorListPane, startButton);

        colorListPane.getChildren().addAll(menuVBox, inputPane, ui.listOfPicsTextArea, debugButton, startButton);
        return new Scene(colorListPane, 1600, 1200);        
    }

    private Button createDebugButton(ColorApp ui, VBox colorListPane, Button startButton) {
        Button debugButton = new Button("Tarkista ja hae kuvat.");
        debugButton.setOnAction(event -> {
            List<String> listOfPics = new ArrayList<>(Arrays.asList(ui.listOfPicsTextArea.getText().split("\n")));
            List<String> verifyPicturePaths = ui.imageAccessObject.verifyPicturePaths(listOfPics);
            if (verifyPicturePaths.size() > 0) {
                Alert alert = new Alert(AlertType.INFORMATION, "NON ACCEPTER!", ButtonType.OK);
                alert.setTitle("TENKKAPOO!"); // window title
                alert.setHeaderText("EI SAANU LADATTUA! \nJOTTAI TARTTIS TEHRÄ??!??");
                alert.setContentText(String.join("\n", verifyPicturePaths));
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            } else {
                ui.images = ui.imageAccessObject.getImages(listOfPics);
                Collection<BufferedImage> values = ui.images.values();
                ArrayList<BufferedImage> imagesArrayList = new ArrayList<>(values);
                colorListPane.getChildren().removeAll(ui.kuvat);                    // remove old pics
                ui.kuvat.clear();
                for (BufferedImage bufImage : imagesArrayList) {
                    Image image = SwingFXUtils.toFXImage(bufImage, null);
                    {
                        ImageView iv2 = new ImageView();
                        iv2.setImage(image);
                        iv2.setFitWidth(50);
                        iv2.setPreserveRatio(true);
                        iv2.setSmooth(true);
                        iv2.setCache(true);
                        ui.kuvat.add(iv2);
                    }
                }
                colorListPane.getChildren().addAll(ui.kuvat);
                Alert alert = new Alert(AlertType.INFORMATION, "LES PHOTOS SONT BONNES!", ButtonType.OK);
                alert.setTitle("Bueno!"); // window title
                alert.setHeaderText("Start quiz button enabled.");
                alert.setContentText(String.join("\n", verifyPicturePaths));
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
                startButton.setDisable(false);
            }
        });    
        return debugButton;    
    }

}
