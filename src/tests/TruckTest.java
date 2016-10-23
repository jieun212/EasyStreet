/*
 * TCSS 305 - Winter 2016 
 * Assignment 3 -Easy Street
 */

package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

import org.junit.Test;

/**
 * Truck test class. white box test.
 * 
 * @author Jieun Lee
 * @version 1.0
 */
public class TruckTest {

    // private Truck testTruck;

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /**
     * Test of Truck constructor.
     */
    @Test
    public void testTruck() {
        final Truck testTruck = new Truck(10, 11, Direction.NORTH);
        assertEquals("Truck x-coordinate not initiallized correctly!", 10, testTruck.getX());
        assertEquals("Truck y-coordinate not initiallized correctly!", 11, testTruck.getY());
        assertEquals("Truck direction not initiallized correctly!", Direction.NORTH,
                     testTruck.getDirection());
        assertTrue("Truck isAlive() falis initially!", testTruck.isAlive());
    }

    /**
     * Test of Truck canPass() method.
     */
    @Test
    public void testCanPass() {
        for (final Terrain testTerrain : Terrain.values()) {
            final Truck testTruck = new Truck(0, 0, Direction.NORTH);
            for (final Light testLight : Light.values()) {
                if (testTerrain == Terrain.STREET || testTerrain == Terrain.LIGHT) {
                    assertTrue("Truck should be able to pass" + testTerrain
                               + ", with light " + testLight,
                               testTruck.canPass(testTerrain, testLight));
                } else {
                    assertFalse("Truck should NOT be able to pass" + testTerrain
                                + ", with light " + testLight,
                                testTruck.canPass(testTerrain, testLight));
                }
            }
        }
    }
    
    

    /**
     * Test of Truck chooseDirection() method if two direction available.
     */
    @Test
    public void testChooseDirectionTwoDirectionAvailable() {
        /*
         * sets up Map (N=wall, W=wall) 
         *   W 
         * W t t
         *   ?   (S = street or light depending on t)
         */
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.WALL);
        for (final Terrain t : Terrain.values()) {
            if (t == Terrain.WALL || t == Terrain.GRASS || t == Terrain.TRAIL) {
                continue;
            }
            final Truck truck = new Truck(0, 0, Direction.NORTH);
            neighbors.put(Direction.EAST, t);
            if (t == Terrain.STREET) {
                neighbors.put(Direction.SOUTH, Terrain.LIGHT);
            } else {
                neighbors.put(Direction.SOUTH, Terrain.STREET);
            }

            int tries = 0;
            while (tries < TRIES_FOR_RANDOMNESS) {
                tries = tries + 1;
                final Direction dir = truck.chooseDirection(neighbors);

                assertTrue("on " + t + ", should choose E or S, was " + dir,
                           dir == Direction.EAST || dir == Direction.SOUTH);
            }
        }
    }
    
    /**
     * Test of Truck chooseDirection() method if one direction available.
     */
    @Test
    public void testChooseDirectionOneDirectionAvailabe() {
        /*
         * sets up Map (N=wall, W=wall, E=Grass) 
         *   W 
         * W t G 
         *   ?    (S = street or light depending on t)
         */
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        for (final Terrain t : Terrain.values()) {
            if (t == Terrain.WALL || t == Terrain.GRASS || t == Terrain.TRAIL) {
                continue;
            }
            final Truck truck = new Truck(0, 0, Direction.NORTH);
            if (t == Terrain.STREET) {
                neighbors.put(Direction.SOUTH, Terrain.LIGHT);
            } else {
                neighbors.put(Direction.SOUTH, Terrain.STREET);
            }
            int tries = 0;
            while (tries < TRIES_FOR_RANDOMNESS) {
                tries = tries + 1;
                final Direction dir = truck.chooseDirection(neighbors);
                assertSame("invalid dir chosen, should be south, was " + dir, Direction.SOUTH,
                           dir);
            }
        }
    }
    

}
