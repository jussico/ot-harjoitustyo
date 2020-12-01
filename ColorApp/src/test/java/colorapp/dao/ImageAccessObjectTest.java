package colorapp.dao;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author asp
 */
public class ImageAccessObjectTest {
        
    ImageAccessObject imageAccess;
    
    @Before
    public void setUp() {    	
        imageAccess = new ImageAccessObject(null);
    }

    @Test
    public void checkValidHttpsUrlWorks() {
        String pic1 = "https://upload.wikimedia.org/wikipedia/commons/5/56/Donald_Trump_official_portrait.jpg";
        Object x = imageAccess.getImage(pic1);
        assertNotNull(x);
    }

    // @Test
    // public void checkValidHttpUrlWorks() {
    //     Object x = imageAccess.getImage("http://jyy");
    //     assertNotNull(x);
    // }
    
    // @Test
    // public void checkValidFileUriWorks() {
    //     Object x = imageAccess.getImage("file:///jyy");
    //     assertNotNull(x);
    // }

    @Test
    public void checkUnvalidStringFails() {
        Object x = imageAccess.getImage("haeMulleKuva");
        assertNull(x);
    }
    
}
