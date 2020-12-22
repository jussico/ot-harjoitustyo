package colorapp.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Gets image files from the net or local file and converts them to the kind of
 * format needed to analyse image colors.
 */
public class ImageService {

    /**
     * verify that given picture procotols are correct ones.
     * @param listOfPics
     * @return
     */
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
                returnMessages.add("Tuntematon protokolla: " + path);
            }
        }
        return returnMessages;
    }

    /**
     * try to get image and catch possible error and report failure.
     * @param imagePath
     * @param returnMessages
     */
    private void tryToGetImageAndReportPossibleError(String imagePath, List<String> returnMessages) {
        try {
            getImage(imagePath);
        } catch (Exception x) {
            returnMessages.add("Kuvaa ei saatu ladattua: " + imagePath);
        }
    }

    /**
     * get images from list of picture paths / URLs
     * @param listOfPics
     * @return
     */
    public Map<String, BufferedImage> getImages(List<String> listOfPics) {
        Map<String, BufferedImage> pathsToImagesMap = new HashMap<String, BufferedImage>();
        for (String location : listOfPics) {
            BufferedImage image = getImage(location);
            pathsToImagesMap.put(location, image);
        }
        return pathsToImagesMap;
    }

    /**
     * get image from the web ( http:// / https:// ) or local file ( file:/// )
     * @param path
     * @return
     */
    private BufferedImage getImage(String path) {

        BufferedImage returnValue = null;

        if (path.startsWith("https://") || path.startsWith("http://")) {
            System.out.println("@HTTP-method path: " + path);
            try {
                URL url = new URL(path);
                BufferedImage image = ImageIO.read(url);
                returnValue = image;
            } catch (Exception x) {
                throw new RuntimeException(x);
            }
        } else if (path.startsWith("file:///")) {
            try {
                path = path.replace("file://", "");
                File file = new File(path);
                BufferedImage image;
                image = ImageIO.read(file);
                returnValue = image;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("virheellinen protokolla");
        }
        return returnValue;
    }

    // main test

    public static void main(String[] args) {
        String path = "file:///E:/kuva.jpg";


        path = path.replace("file://", "");
        File file = new File(path);
        BufferedImage image;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
