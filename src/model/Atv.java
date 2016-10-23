/*
 * TCSS 305 - Winter 2016 
 * Assignment 3 -Easy Street
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The Atv object is the subclass of the AbstractVehicle class.
 * 
 * @author Jieun Lee
 * @version 1.0
 */
public class Atv extends AbstractVehicle {

    /**
     * A default death time of Atv.
     */
    protected static final int ATV_DEATHTIME = 15;

    /**
     * Constructs that takes an x-coordinate, an y-coordinate, a direction, and
     * a terrain.
     * 
     * @param theX the X.
     * @param theY the Y.
     * @param theDir the Direction.
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, ATV_DEATHTIME);
    }

    /**
     * Returns true if the given terrain is not a wall with any light color.
     * 
     * @param theTerrain the terrain.
     * @param theLight the light of color.
     * @return whether or not this object may move onto the given type of
     *         terrain, when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain == Terrain.WALL) {
            canPass = false;
        } else {
            canPass = true;
        }
        return canPass;
    }

    /**
     * Returns the random direction that this Atv can pass, based on the given
     * map of the neighboring terrain. The Atv never reverse direction.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Random random = new Random();
        final List<Direction> dirList = new ArrayList<Direction>();
        final Direction currentDirection = getDirection();
        dirList.add(currentDirection);
        dirList.add(currentDirection.left());
        dirList.add(currentDirection.right());

        final Direction randomDir = dirList.get(random.nextInt(dirList.size()));
        Direction chooseDir = randomDir;

        if (theNeighbors.get(randomDir) == Terrain.WALL) {
            chooseDir = dirList.get(random.nextInt(dirList.size()));
        } else {
            chooseDir = randomDir;
        }
        return chooseDir;
    }
}
