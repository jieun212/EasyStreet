/*
 * TCSS 305 - Winter 2016 
 * Assignment 3 -Easy Street
 */

package model;

/**
 * The Taxi object is a subclass of the Car class.
 * 
 * @author Jieun Lee
 * @version 1.0
 */
public class Taxi extends Car {

    /**
     * A default maximum stop clock cycle.
     */
    private static final int MAX_STOP_CLOCK_CYCLE = 3;

    /**
     * The count of stop clock cycle.
     */
    private int myCountStop;

    /**
     * Constructs that takes an x-coordinate, an y-coordinate, a direction, and
     * a terrain. Initializes myCountStop as 0;
     * 
     * @param theX the X.
     * @param theY the Y.
     * @param theDir the Direction.
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myCountStop = 0;
    }

    /**
     * Returns true if the given terrain is a street or a light and the given
     * light color is yellow or green. Returns true when the given terrain is
     * not light and the given light color is red. Returns true when the light
     * color is red and the taxi stays 3 clock cycles or until the light turns
     * green, whichever happens first.
     * 
     * @param theTerrain the terrain.
     * @param theLight the light of color.
     * @return whether or not this object may move onto the given type of
     *         terrain, when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theLight == Light.RED && theTerrain != Terrain.LIGHT) {
            canPass = true;
        } else if (myCountStop == MAX_STOP_CLOCK_CYCLE || theLight == Light.GREEN
                 || theLight == Light.YELLOW) {
            canPass = true;
            myCountStop = 0;
        } else {
            canPass = false;
            if (theLight == Light.RED && theTerrain == Terrain.LIGHT) {
                myCountStop++;
            }
        }
        return canPass;
    }
}
