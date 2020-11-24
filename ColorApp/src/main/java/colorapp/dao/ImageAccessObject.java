package colorapp.dao;

/**
 * Gets image files from the net or local file and converts them to the kind of format needed to analyse image colors.
 */
public class ImageAccessObject {
    
    public Object getImage(String location) {

        Object returnValue = "";    // TODO: some image format.

        if (location.startsWith("https://")) {
            // ok. do something.
        } else if (location.startsWith("http://")) {
            // ok. do something.
        } else if (location.startsWith("file:///")) {
            // ok. do something else.
        } else {
            // not ok.
            return null;
        }

        return returnValue;
    }

}
