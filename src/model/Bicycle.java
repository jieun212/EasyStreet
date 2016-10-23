/*
 * TCSS 305 - Winter 2016 
 * Assignment 3 -Easy Street
 */

package model;

import java.util.Map;

/**
 * The bicycle object is a subclass of the AbstractVehicle class.
 * 
 * @author Jieun Lee
 * @version 1.0
 */
public class Bicycle extends AbstractVehicle {

    /**
     * A default death time of bicycle.
     */
    private static final int BICYCLE_DEATHTIME = 25;

    /**
     * Constructs that takes an x-coordinate, an y-coordinate, a direction, and
     * a terrain.
     * 
     * @param theX the X.
     * @param theY the Y.
     * @param theDir the Direction.
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, BICYCLE_DEATHTIME);
    }

    /**
     * Returns true if the given terrain is a trail, a street and a light with
     * light color green. Returns also true the given terrain is not a light and
     * the given light color is yellow or red.
     * 
     * @param theTerrain the terrain.
     * @param theLight the light of color.
     * @return whether or not this object may move onto the given type of
     *         terrain, when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass;

        if ((theLight == Light.YELLOW || theLight == Light.RED)
            && theTerrain != Terrain.LIGHT) {
            canPass = true;
        } else if ((theLight == Light.GREEN)
                 && (theTerrain == Terrain.TRAIL || theTerrain == Terrain.STREET
                     || theTerrain == Terrain.LIGHT)) {
            canPass = true;
        } else {
            canPass = false;
        }
        return canPass;
    }

    /**
     * Returns the direction that this bicycle can pass, based on the given map
     * of the neighboring terrain. If the neighbors' terrain is terrain, the
     * bicycle go to trail. If there is no trail straight ahead, to the left, or
     * to the right, then it moves to straight ahead on a street or light if it
     * can. If it cannot go straight, it turns left. If it cannot turn left,
     * then it turns right. As a last resort, it turns around.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction currentDir = getDirection();
        Direction chooseDir = currentDir;

        if (theNeighbors.get(currentDir) == Terrain.TRAIL) {
            chooseDir = currentDir;
        } else if (theNeighbors.get(currentDir.left()) == Terrain.TRAIL) {
            chooseDir = currentDir.left();
        } else if (theNeighbors.get(currentDir.right()) == Terrain.TRAIL) {
            chooseDir = currentDir.right();
        } else if (helperChooseDirection(theNeighbors.get(currentDir))) {
            chooseDir = currentDir;
        } else if (helperChooseDirection(theNeighbors.get(currentDir.left()))) {
            chooseDir = currentDir.left();
        } else if (helperChooseDirection(theNeighbors.get(currentDir.reverse()))) {
            chooseDir = currentDir.right();
        } else {
            chooseDir = currentDir.reverse();
        } 
        return chooseDir;
    }
    
    /**
     * A helper method of chooseDirection() method.
     * 
     * @param theTerrain the terrain.
     * @return whether or not this object may move onto the given type of
     *         terrain
     */
    private boolean helperChooseDirection(final Terrain theTerrain) {
        boolean canChoose = false;
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            canChoose = true;
        }
        return canChoose;
    }

}
