/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colorapp.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author asp
 */
public class ColorServiceTest {
    
    ColorService service;
    
    @Before
    public void setUp() {    	
        service = new ColorService(null);     
      
    }
    
    @Test
    public void quessCheckSucceedsWithCorrect() {
    	
    	boolean right = service.makeQuess("juu", "juu");    	
    	assertTrue(right);
    	
    }

    @Test
    public void quessCheckfailsWithInCorrect() {    	
    	boolean wrong = service.makeQuess("juu", "jyy");
    	assertFalse(wrong);
    	    
    }
    
}
