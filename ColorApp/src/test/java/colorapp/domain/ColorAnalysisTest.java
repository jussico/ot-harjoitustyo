package colorapp.domain;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.junit.Test;

import colorapp.service.ImageService;

public class ColorAnalysisTest {
    
    @Test
    public void checkValidHttpsUrlWorks() {
        ImageService imageService = new ImageService();
        String pic1 = "https://upload.wikimedia.org/wikipedia/commons/5/56/Donald_Trump_official_portrait.jpg";
        var x = imageService.getImages(Arrays.asList(pic1));
        
        BufferedImage bufferedImage = x.get(pic1);

        ColorAnalysis colorAnalysis = new ColorAnalysis();
        RGB averageColor = colorAnalysis.getAverageColor(bufferedImage);
        assertNotNull(averageColor);

    }
}
