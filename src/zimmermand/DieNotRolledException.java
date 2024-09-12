/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Daniel Zimmerman
 * Last Updated: 9/12/2024
 */
package zimmermand;

/**
 * this error is thrown when a die from the Die class is checked without rolling the die first
 */
public class DieNotRolledException extends RuntimeException {
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
