/*
 * TCSS 305 - Winter 2016 
 * Assignment 3 -Easy Street
 */

package model;

import java.util.Map;

/**
 * The Human object is a subclass of the AbstractVehicle class.
 * 
 * @author Jieun Lee
 * @version 1.0
 */
public class Human extends AbstractVehicle {

    /**
     * A default death time of Human.
     */
    private static final int HUMAN_DEATHTIME = 45;

    /**
     * The Terrain.
     */
    private final Terrain myTerrain;

    /**
     * Constructs that takes an x-coordinate, an y-coordinate, a direction, and
     * a terrain.
     * 
     * @param theX the X.
     * @param theY the Y.
     * @param theDir the Direction.
     * @param theTerrain the terrain.
     */
    public Human(final int theX, final int theY, final Direction theDir,
                 final Terrain theTerrain) {
        super(theX, theY, theDir, HUMAN_DEATHTIME);
        myTerrain = theTerrain;
    }

    /**
     * Returns true if the given terrain is same as initial terrain with any
     * light color. Terrains of street and light are considered the same terrain
     * for Human object.
     * 
     * @param theTerrain the terrain.
     * @param theLight the light of color.
     * @return whether or not this object may move onto the given type of
     *         terrain, when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if ((myTerrain == theTerrain)
            || ((myTerrain == Terrain.LIGHT && theTerrain == Terrain.STREET))
            || ((myTerrain == Terrain.STREET && theTerrain == Terrain.LIGHT))) {
            canPass = true;
        } else {
            canPass = false;
        }
        return canPass;
    }

    /**
     * Returns the random direction that this human can pass, based on the given
     * map of the neighboring terrain.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
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
