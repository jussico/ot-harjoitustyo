package colorapp.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Handles Analyzing picture
 */
public class ColorAnalysis {

    /**
     * calculate the average rgb color value of the given picture.
     * @param image
     * @return
     */
    public RGB getAverageColor(BufferedImage image) {

        int korkeus = image.getHeight();
        int leveys = image.getWidth();

        long pixeleit = korkeus * leveys;

        long allred = 0;
        long allgreen = 0;
        long allblue = 0;

        for (int x = 0; x < leveys; x++) {
            for (int y = 0; y < korkeus; y++) {
                int color = image.getRGB(x, y);
                int blue = color & 0xff;
                int green = (color & 0xff00) >> 8;
                int red = (color & 0xff0000) >> 16;
                allred = allred + red;
                allblue = allblue + blue;
                allgreen = allgreen + green;
            }
        }
        long averageRed = allred / pixeleit;
        long averageGreen = allgreen / pixeleit;
        long averageBlue = allblue / pixeleit;
        RGB returnValue = new RGB(averageRed, averageGreen, averageBlue);
        return returnValue;
    }

    // test

    public static void main(String[] args) {
        try {

            // String path = "file:///E:/kuva.jpg";
            String path = "file:///E:/sininen_kuva.jpg";
            path = path.replace("file://", "");
            File file = new File(path);
            BufferedImage image;
            image = ImageIO.read(file);
            System.out.println("@FILE:////-path - got image.");
            System.out.println("@FILE:////-path - image: " + image);

            ColorAnalysis colorService = new ColorAnalysis();
            RGB averageColor = colorService.getAverageColor(image);
            System.out.println("keskiarvo väri on: " + averageColor);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}