package colorapp.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

/**
 * Gets image files from the net or local file and converts them to the kind of
 * format needed to analyse image colors.
 */
public class ImageAccessObject {

    public ImageAccessObject(Properties properties) {
        // TODO: tee jotain ehkä
    }

    public List<String> verifyPicturePaths(List<String> listOfPics) {

        List<String> returnMessages = new ArrayList<>();

        for (String path : listOfPics) {
            if (path.startsWith("https://")) {
                tryToGetImageAndReportPossibleError(path, returnMessages);
            } else if (path.startsWith("http://")) {
                tryToGetImageAndReportPossibleError(path, returnMessages);
            } else if (path.startsWith("file:///")) {
                tryToGetImageAndReportPossibleError(path, returnMessages);
            } else {
                returnMessages.add("NOT ACCEPTED: " + path);
            }
        }
        return returnMessages;
    }

    private void tryToGetImageAndReportPossibleError(String imagePath, List<String> returnMessages) {
        try {
            getImage(imagePath);
        } catch (Exception x) {
            returnMessages.add("ERROR GETTING IMAGE: " + imagePath);    
        }
    }

    public Map<String, BufferedImage> getImages(List<String> listOfPics) {
        Map<String, BufferedImage> pathsToImagesMap = new HashMap<String, BufferedImage>();
        for (String location : listOfPics) {
            BufferedImage image = getImage(location);
            pathsToImagesMap.put(location, image);
        }
        return pathsToImagesMap;
    }

    public BufferedImage getImage(String path) {

        BufferedImage returnValue = null;

        if (path.startsWith("https://") || path.startsWith("http://")) {
            // ok. do something.
            System.out.println("@HTTP-method path: " + path);
            try {
{
    // BufferedImage image = ImageIO.read(new File("c:\\test\\image.png"));
    // BufferedImage image = ImageIO.read(new URL("https://example.com/image.png"));
}

                // URL url = new URL("http://developerfeed.com/sites/default/files/have_a_question.png");
                URL url = new URL(path);
                // read the url
                BufferedImage image = ImageIO.read(url);
                System.out.println("@HTTP-method - got image.");
                System.out.println("@HTTP-method - image: " + image);

                returnValue = image;

                // for png
                // ImageIO.write(image, "png", new File("/tmp/have_a_question.png"));
                ImageIO.write(image, "png", new File("X.png"));
                System.out.println("@HTTP-method - saved .png.");

                // for jpg
                // ImageIO.write(image, "jpg", new File("/tmp/have_a_question.jpg"));
                ImageIO.write(image, "jpg", new File("X.jpg"));
                System.out.println("@HTTP-method - saved .jpg.");
            } catch (Exception x) {
                throw new RuntimeException(x);
            }

        } else if (path.startsWith("file:///")) {
        }

        return returnValue;
    }

}
