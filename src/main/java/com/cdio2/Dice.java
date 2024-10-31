package com.cdio2;

import java.util.Random;

class Dice {
    private static final Random random = new Random();
    private static Integer fixedRoll = null;
    private static int sides = 6;

    public static void setSides(int newSides, Game game) {
        if (newSides >= 2) { // minimum 2 sides for a dice
            sides = newSides;
        } else {
            System.out.println(game.getMessage("dice.sides.error"));
        }
    }

    // testing purposes
    public static void setFixedRoll(Integer roll) {
        fixedRoll = roll;
    }

    // testing puposes
    public static void clearFixedRoll() {
        fixedRoll = null;
    }

    public static int roll() {
        if (fixedRoll != null) {
            return fixedRoll;
        }
        return random.nextInt(sides) + 1;
    }
}
