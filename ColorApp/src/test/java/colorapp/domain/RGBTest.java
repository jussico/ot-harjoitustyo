package colorapp.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RGBTest {
    
    @Test
    public void testCreateRGB() {
        new RGB(10L, 10L, 20L);

        new RGB(1,2,3);

        new RGB("aabbcc");

        Exception xx = null;
        try {
            new RGB("jussia");
        } catch (Exception x) {
            xx = x;
        }
        assertNotNull(xx);
    }
    
    @Test
    public void testGetHex() {
        var rgb = new RGB(10,10,10);
        assertEquals("0A0A0A", rgb.getAsHex());
    }

    @Test
    public void testCalculateClonessValue() {
        var rgb = new RGB(10,10,10);
        var rgb2 = new RGB(11,11,11);
        int calculateClosenessValue = rgb.calculateClosenessValue(rgb2);
        assertEquals(3, calculateClosenessValue);
    }

    @Test
    public void testPercentageCorrect() {
        var rgb = new RGB(10,10,10);
        var rgb2 = new RGB(10,10,10);
        double percentageCorrect = rgb.percentageCorrect(rgb2);
        assertEquals(100, percentageCorrect, 0);
    }

    @Test
    public void testPercentageCorrectLists() {
        var rgb = new RGB(10,10,10);
        var rgb2 = new RGB(10,10,10);
        double percentageCorrect = RGB.percentageCorrect(Arrays.asList(rgb), Arrays.asList(rgb2));
        assertEquals(100, percentageCorrect, 0);        
    }

    // main test
    public static void main(String[] args) {
        var taa = new RGBTest();    
        taa.testCalculateClonessValue();
    }

}
