/*
 * TCSS 305 - Winter 2016 
 * Assignment 3 -Easy Street
 */

package model;

/**
 * An abstract class implements Vehicle interface .
 * 
 * @author Jieun Lee
 * @version 1.0
 */
public abstract class AbstractVehicle implements Vehicle {

    /**
     * The x-coordinate.
     */
    private int myX;

    /**
     * The y-coordinate.
     */
    private int myY;

    /**
     * The direction.
     */
    private Direction myDir;

    /**
     * The being alive.
     */
    private boolean myDead;

    /**
     * The death time.
     */
    private int myDeathTime;

    /**
     * The initial x-coordinate.
     */
    private final int myInitialX;

    /**
     * The initial y-coordinate.
     */
    private final int myInitialY;

    /**
     * The initial direction.
     */
    private final Direction myInitialDir;

    /**
     * The initial death time.
     */
    private final int myInitialDeathTime;

    /**
     * Protected constructor that accepts an x-coordinate, an y-coordinate, a
     * direction and death time. Stores the initial x-coordinate, y-coordinate,
     * direction and death time.
     * 
     * @param theX the x-coordinate.
     * @param theY the y-coordinate.
     * @param theDir the direction.
     * @param theDeathTime the death time.
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir,
                              final int theDeathTime) {
        myX = theX;
        myY = theY;
        myDir = theDir;
        myDead = false;
        myDeathTime = theDeathTime;

        myInitialX = theX;
        myInitialY = theY;
        myInitialDir = theDir;
        myInitialDeathTime = theDeathTime;
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     * 
     * @param theOter The other object.
     */
    @Override
    public void collide(final Vehicle theOther) {
        if ((this.getX() == theOther.getX() && this.getY() == theOther.getY())
            && (isAlive() && theOther.isAlive())
            && (getDeathTime() > theOther.getDeathTime())) {
            myDead = true;
            myDeathTime = 0;
        }
    }

    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     * 
     * @return the number of updates.
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /**
     * Returns the file name of the image for this Vehicle object, such as
     * "car.gif". If dead, "car_dead.gif".
     * 
     * @return the file name.
     */
    @Override
    public String getImageFileName() {
        final StringBuilder imgName = new StringBuilder(128);
        imgName.append(getClass().getSimpleName().toLowerCase());
        if (!isAlive()) {
            imgName.append("_dead");
        }
        imgName.append(".gif");
        return imgName.toString();
    }

    /**
     * Returns this Vehicle object's direction.
     * 
     * @return the direction.
     */
    @Override
    public Direction getDirection() {
        return myDir;
    }

    /**
     * Returns this Vehicle object's x-coordinate.
     * 
     * @return the x-coordinate.
     */
    @Override
    public int getX() {
        return myX;
    }

    /**
     * Returns this Vehicle object's y-coordinate.
     * 
     * @return the y-coordinate.
     */
    @Override
    public int getY() {
        return myY;
    }

    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     * 
     * @return true if the object is alive, false otherwise.
     */
    @Override
    public boolean isAlive() {
        boolean isAlive;
        if (myDead) {
            isAlive = false;
        } else {
            isAlive = true;
            setDirection(getDirection());
        }
        return isAlive;
    }

    /**
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */
    @Override
    public void poke() {
        if (!isAlive()) {
            myDeathTime++;
            if (myDeathTime == myInitialDeathTime) {
                myDead = false;
            }
        }
    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        // return to initial state (position, direction, being alive
        setX(myInitialX);
        setY(myInitialY);
        setDirection(myInitialDir);
        myDead = false;
    }

    /**
     * Sets this object's facing direction to the given value.
     * 
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDir = theDir;
    }

    /**
     * Sets this object's x-coordinate to the given value.
     * 
     * @param theX The new x-coordinate.
     */
    @Override
    public void setX(final int theX) {
        myX = theX;
    }

    /**
     * Sets this object's y-coordinate to the given value.
     * 
     * @param theY The new y-coordinate.
     */
    @Override
    public void setY(final int theY) {
        myY = theY;
    }

    /**
     * Returns a String representation of this object's name and death time.
     * 
     * @return String representation of this object's name and death time.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " Poke: " + getDeathTime();
    }

}
