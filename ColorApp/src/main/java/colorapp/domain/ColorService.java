package colorapp.domain;

import java.util.Properties;// TODO: oikea?

/**
 * Sovelluslogiikasta vastaava luokka 
 */

public class ColorService {
        
    public ColorService(Properties properties) {
        // TODO: tee jotain ehkä       
        
    }
    
    /**
    * Uuden arvauksen tekeminen.
    *
    * @param   quess	arvaus
    * @param	correct	oikea vastaus
    */
    
    public boolean makeQuess(String quess, String correct) {
    	// TODO
    	if(quess.equals(correct)) {
    		return true;
    	} else {
    		return false;
    	}
    
    }
    
}