/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Daniel Zimmerman
 * Last Updated: 9/12/2024
 */
package zimmermand;

import java.util.Scanner;

/**
 * this class asks the user for a number of dice to roll, the amount
 * of sides on each die, and the amount of times the set of dice will
 * be rolled then rolls each set of die the specified amount of times,
 * then shows a histogram of how many times each possible total was rolled.
 */
public class Driver {
    /**
     * the minimum number of dice that must be rolled in a set
     */
    public static final int MIN_DICE = 2;
    /**
     * the maximum number of dice that can be rolled in a set
     */
    public static final int MAX_DICE = 10;

    public static void main(String[] args) {
        Driver driver = new Driver();
        int[] input = driver.getInput();
        Die[] dice = driver.createDice(input[0], input[1]);
        int[] rolls = driver.rollDice(dice, input[1], input[2]);
        int max = driver.findMax(rolls);
        driver.report(input[0], rolls, max);
    }

    private int[] getInput() {
        int[] input = new int[3];
        System.out.print("Please enter the number of dice to roll, " +
                "how many sides the dice have,\n" +
                "and how many rolls to complete, separating the values by a space.\n" +
                "Example: \"2 6 1000\"\n\nEnter configuration:");
        while(true) {
            Scanner in = new Scanner(System.in);
            String strInput = in.nextLine();
            strInput += " ";
            input[0] = -1;
            input[1] = -1;
            input[2] = -1;
            try {
                input[0] = Integer.parseInt(strInput.substring(0, strInput.indexOf(" ")));
                strInput = strInput.substring(strInput.indexOf(" ") + 1);
                input[1] = Integer.parseInt(strInput.substring(0, strInput.indexOf(" ")));
                strInput = strInput.substring(strInput.indexOf(" ") + 1);
                input[2] = Integer.parseInt(strInput.substring(0, strInput.indexOf(" ")));
            } catch (StringIndexOutOfBoundsException e) {
                int flag = 2;
                if (input[0] == -1) {
                    flag = 0;
                } else if (input[1] == -1) {
                    flag = 1;
                }
                System.out.print("Invalid input: Expected 3 values " +
                        "but only received " + flag + "\n" +
                        "Please enter the number of dice to roll, how many sides the dice have,\n" +
                        "and how many rolls to complete, separating the values by a space.\n" +
                        "Example: \"2 6 1000\"\n\nEnter configuration:");
                continue;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input: All values must be whole numbers.\n" +
                        "Please enter the number of dice to roll, how many sides the dice have,\n" +
                        "and how many rolls to complete, separating the values by a space.\n" +
                        "Example: \"2 6 1000\"\n\nEnter configuration:");
                continue;
            }

            if (!(input[0] >= MIN_DICE && input[0] <= MAX_DICE)) {
                System.out.print("Bad die creation: Illegal number of sides: "+input[0] +
                        "\nPlease enter the number of dice to roll, " +
                        "how many sides the dice have,\n" +
                        "and how many rolls to complete, separating the values by a space.\n" +
                        "Example: \"2 6 1000\"\n" +
                        "\n" +
                        "Enter configuration:");
                continue;
            }
            if(!(input[1] >= Die.MIN_SIDES && input[1] <= Die.MAX_SIDES)) {
                System.out.print("Bad die creation: Illegal number of die: "+input[1] +
                        "\nPlease enter the number of dice to roll, how " +
                        "many sides the dice have,\n" +
                        "and how many rolls to complete, separating the values by a space.\n" +
                        "Example: \"2 6 1000\"\n" +
                        "\n" +
                        "Enter configuration:");
                continue;
            }
            break;
        }
        return input;
    }

    private Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    private int[] rollDice(Die[] dice, int numSides, int numRolls) {
        int[] rolls = new int[dice.length*numSides + 1];
        int setTotal = 0;
        for (int i = 0; i < numRolls; i++) {
            for (Die die : dice) {
                die.roll();
                setTotal += die.getCurrentValue();
            }
            rolls[setTotal]++;
            setTotal = 0;
        }
        return rolls;
    }

    private int findMax(int[] rolls) {
        int max = rolls[0];
        for (int i = 1; i < rolls.length; i++) {
            if (rolls[i] > max) {
                max = rolls[i];
            }
        }
        return max;
    }

    private void report(int numDice, int[] rolls, int max) {
        final int ten = 10;
        for (int i = numDice; i < rolls.length; i++) {
            int scaler = (int)Math.floor((double)ten * rolls[i]/max);
            String stars = "";
            for(int star = 0; star < scaler; star++) {
                stars += "*";
            }
            System.out.printf("%-2d:" + "%-9d" + "%s\n", i, rolls[i], stars);

        }
    }
}