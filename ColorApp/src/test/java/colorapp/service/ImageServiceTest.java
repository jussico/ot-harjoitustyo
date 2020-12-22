package colorapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;

public class ImageServiceTest {

    @Test
    public void checkValidHttpsUrlWorks() {
        ImageService imageService = new ImageService();
        String pic1 = "https://upload.wikimedia.org/wikipedia/commons/5/56/Donald_Trump_official_portrait.jpg";
        var x = imageService.getImages(Arrays.asList(pic1));
        assertEquals(x.size(), 1);
    }

    @Test
    public void checkInvalidHttpUrlFails() {
        ImageService imageService = new ImageService();
        Exception xx = null;
        try {
            imageService.getImages(Arrays.asList("http://NOTHING"));
        } catch ( Exception x) {
            xx = x;
        }
        assertNotNull(xx);
    }
    
    @Test
    public void checkInValidFileUriFails() {
        ImageService imageService = new ImageService();
        Exception xx = null;
        try {
            imageService.getImages(Arrays.asList("file:///NOTHING"));
        } catch ( Exception x) {
            xx = x;
        }
        assertNotNull(xx);
    }

    @Test
    public void checkUnvalidStringFails() {
        ImageService imageService = new ImageService();
        Exception xx = null;
        try {
            imageService.getImages(Arrays.asList("MITÄMISSÄÄ"));
        } catch ( Exception x) {
            xx = x;
        }
        assertNotNull(xx);
    }

}
