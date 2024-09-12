/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Daniel Zimmerman
 * Last Updated: 9/12/2024
 */
package zimmermand;

import java.util.Random;

/**
 * this class is used to create a die that can have any number of sides
 * between 2 and 100, and roll the die for a random value on the die.
 */
public class Die {

    /**
     * this int is the minimum number of sides a die must have
     */
    public static final int MIN_SIDES = 2;
    /**
     * this int is the maximum number of sides a die can have
     */
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private Random random;

    /**
     * this is the constructor of the Die class
     * @param numSides number of sides the die will have, this must be between 2 and 100
     * @throws IllegalArgumentException throws when numSides is outside specified range
     */
    public Die(int numSides) {
        if(numSides <= MIN_SIDES || numSides >= MAX_SIDES) {
            throw new IllegalArgumentException("numSides must be between 2 and 100");
        }
        this.numSides = numSides;
        random = new Random();
    }

    /**
     * this method gives the die a random value to be checked
     */
    public void roll() {
        currentValue = random.nextInt(numSides) + 1;
        //System.out.println(currentValue);
    }

    /**
     * this method checks the value rolled by the die
     * @return the value rolled by die
     * @throws DieNotRolledException throws when dies is checked without rolling first
     */
    public int getCurrentValue() {
        if(currentValue < 1 || currentValue > MAX_SIDES) {
            System.out.println(currentValue);
            throw new DieNotRolledException();
        }
        int value = currentValue;
        currentValue = 0;
        return value;
    }
}