package colorapp.domain;

import java.util.List;

/**
 * Represents single RGB Color
 */
public class RGB {
    
    private int red;
    private int green;
    private int blue;

    public RGB(long red, long green, long blue) {
        this((int) red, (int) green, (int) blue);  // all good.
    }

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public RGB(String hexString) {
        this.red = Integer.parseInt(hexString.substring(0, 2), 16);
        this.green = Integer.parseInt(hexString.substring(2, 4), 16);
        this.blue = Integer.parseInt(hexString.substring(4, 6), 16);
    }

    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

    public int getRed() {
        return red;
    }

    public String getAsHex() {
        return String.format("%02X", red) + String.format("%02X", green) + String.format("%02X", blue);
    }

    /**
     * calculate how close this RGB instance is to another RGB instance.
     * @param other
     * @return
     */
    public int calculateClosenessValue(RGB other) {
        return Math.abs(red - other.red) + Math.abs(green - other.green) + Math.abs(blue - other.blue);
    }

    /**
     * calculate percentage closeness to another color. ( note: percentage definition not unambiguous..  )
     * @param other
     * @return
     */
    public double percentageCorrect(RGB other) {
        int value = calculateClosenessValue(other);
        double prosentti = 100 - Math.round(((value / 3.0) / 255.0) * 100.0);
        return prosentti;
    }

    /**
     * calculate percentage between two RGB instances. ( note: percentage definition not unambiguous..  )
     * @param vasen
     * @param oikea
     * @return
     */
    public static double percentageCorrect(List<RGB> vasen, List<RGB> oikea) {
        long allCloseNow = 0;
        for (int x = 0; x < vasen.size(); x++) {
            allCloseNow = allCloseNow + vasen.get(x).calculateClosenessValue(oikea.get(x));
        }
        double prosentti = 100 - Math.round(((allCloseNow / 3.0) / (255.0 * vasen.size())) * 100.0);
        return prosentti;
    }

    @Override
    public String toString() {
        return getAsHex();
    }

    // test

    public static void main(String[] args) {
        
        String a = "111111";

        RGB rgb = new RGB(a);

        System.out.println("a: " + a);
        System.out.println("a RGB: " + rgb);

    }

}
