/*
 * TCSS 305 - Winter 2016 
 * Assignment 3 -Easy Street
 */

package model;

import java.util.Map;

/**
 * The Truck object is a subclass of the AbstractVehicle class.
 * 
 * @author Jieun Lee
 * @version 1.0
 */
public class Truck extends AbstractVehicle {

    /**
     * A default death time of Truck.
     */
    private static final int TRUCK_DEATHTIME = 0;

    /**
     * Constructs that takes an x-coordinate, an y-coordinate, a direction, and
     * a terrain.
     * 
     * @param theX the X.
     * @param theY the Y.
     * @param theDir the Direction.
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, TRUCK_DEATHTIME);
    }

    /**
     * Returns true if the given terrain is a street or a light with any light
     * color.
     * 
     * @param theTerrain the terrain.
     * @param theLight the light of color.
     * @return whether or not this object may move onto the given type of
     *         terrain, when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            canPass = true;
        } else {
            canPass = false;
        }
        return canPass;
    }

    /**
     * Returns the random direction that this truck can pass, based on the given
     * map of the neighboring terrain.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction randomDir = Direction.random();
        Direction chooseDir;
        if (canPass(theNeighbors.get(randomDir), Light.GREEN)) {
            chooseDir = randomDir;
        } else {
            randomDir = randomDir.left();
            if (canPass(theNeighbors.get(randomDir), Light.GREEN)) {
                chooseDir = randomDir;
            } else {
                randomDir = randomDir.reverse();
                if (canPass(theNeighbors.get(randomDir), Light.GREEN)) {
                    chooseDir = randomDir;
                } else {
                    randomDir = randomDir.right();
                    chooseDir = randomDir;
                }
            }
        }
        return chooseDir;
    }
}
