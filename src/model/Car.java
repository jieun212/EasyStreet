/*
 * TCSS 305 - Winter 2016 
 * Assignment 3 -Easy Street
 */

package model;

import java.util.Map;

/**
 * The Car object object is a subclass of the AbstractVehicle class.
 * 
 * @author Jieun Lee
 * @version 1.0
 */
public class Car extends AbstractVehicle {

    /**
     * A default death time of Car.
     */
    protected static final int CAR_DEATHTIME = 5;

    /**
     * Constructs that takes an x-coordinate, an y-coordinate, a direction, and
     * a terrain.
     * 
     * @param theX the X.
     * @param theY the Y.
     * @param theDir the Direction.
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, CAR_DEATHTIME);
    }

    /**
     * Returns true if the given terrain is a street or a light and the given
     * light color is yellow or green. Also, it returns true when the given
     * terrain is not light and the given light color is red.
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
        } else if ((theLight == Light.GREEN || theLight == Light.YELLOW)
                 && (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT)) {
            canPass = true;
        } else {
            canPass = false;
        }
        return canPass;
    }

    /**
     * Returns the direction that this car can pass, based on the given map of
     * the neighboring terrain. A car prefer to drive straight ahead on the street
     * if it can. If it cannot go straight, it turns left. If it cannot turn
     * left, then it turns right. As a last resort, it turns around.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction currentDir = getDirection();
        Direction chooseDir = currentDir;

        if (theNeighbors.get(currentDir) == Terrain.STREET
            || theNeighbors.get(currentDir) == Terrain.LIGHT) {
            chooseDir = currentDir;
        } else if (theNeighbors.get(currentDir.left()) == Terrain.STREET
                 || theNeighbors.get(currentDir.left()) == Terrain.LIGHT) {
            chooseDir = currentDir.left();
        } else if (theNeighbors.get(currentDir.right()) == Terrain.STREET
                 || theNeighbors.get(currentDir.right()) == Terrain.LIGHT) {
            chooseDir = currentDir.right();
        } else {
            chooseDir = currentDir.reverse();
        } 
        return chooseDir;
    }
}
