package colorapp.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Class to build UI components
 */
public class ColorUIBuilder {

    /**
     * 
     * @param startButton
     * @return text area which lists picture urls
     */
    protected TextArea getPictureUrlListTextArea(Button startButton) {

        // TODO: move stuff to build here

        TextArea listOfPicsTextArea = new TextArea() {
            String pic1 = "https://upload.wikimedia.org/wikipedia/commons/5/56/Donald_Trump_official_portrait.jpg";

            String pic2 = "https://upload.wikimedia.org/wikipedia/commons/b/b4/Loser_sign_croped.jpg";
            {
                setWrapText(true);
                setText(pic1 + "\n" + pic2);
            }
        };
        listOfPicsTextArea.textProperty().addListener((obs,old,niu)->{
            startButton.setDisable(true);
        });

        return listOfPicsTextArea;
    }


}
