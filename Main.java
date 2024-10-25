package com.cdio2;

import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner);
        game.start();
    }
}

class Game {
    private Scanner scanner;
    private Boolean isPlayer1Turn = true; 
    private Player player1 = new Player("Player 1", 0);
    private Player player2 = new Player("Player 2", 0);
    private Wallet player1Wallet = new Wallet("Player 1", 0);
    private Wallet player2Wallet = new Wallet("Player 2", 0);
    private boolean hasExtraTurn = false;

    public Game(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        this.scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.ENGLISH);

        while (true) {

            if (isPlayer1Turn) { 
                doPlayerTurn(player1, player1Wallet, null); 
                isPlayer1Turn = false; 
            } else { 
                doPlayerTurn(player2, player2Wallet, null);
                isPlayer1Turn = true;
            }

            // normal win condition
            if (player1.gold >= 3000) {
                System.out.println("Game ended! Player 1 won");
                break;
            }
            if (player2.gold >= 3000) {
                System.out.println("Game ended! Player 2 won");
                break;
            }
        }
        scanner.close();
    }

    public void doPlayerTurn(Player player, Wallet wallet, String input) {
        if (input == null) {
            System.out.println(player.name + ", press enter to roll the dice:");
            input = scanner.nextLine();
        }

        if (input.equals("")) { // i.e. if enter is pressed
            Integer die1Result = Dice.roll();
            Integer die2Result = Dice.roll();
            Integer diceResult = die1Result + die2Result;

            wallet.addGold(diceResult);
            player.gold = wallet.gold;

            System.out.println(player.name + " rolled: " + die1Result + " and " + die2Result + " , totalling " + diceResult);
            System.out.println(player.name + "'s total gold is now: " + wallet.gold);

            // extra roll if dice sum is 10
            if (diceResult == 10) {
                Dice.clearFixedRoll(); // testing purposes
                hasExtraTurn = true; // testing purposes
                System.out.println(player.name + " rolled: " + diceResult + " they get another turn");
                doPlayerTurn(player, wallet, "");
            } else {
                hasExtraTurn = false;
            }
        }
    }
    // testing purposes
    public boolean hasExtraTurn() {
        return hasExtraTurn;
    }
}



class Player {
    String name;
    Integer gold;

    Player(String name, Integer gold) {
        this.name = name;
        this.gold = gold;
    }

    // add get set methods
}

class Wallet {
    String owner;
    Integer gold;

    public Wallet(String owner, Integer gold) {
        this.owner = owner;
        this.gold = gold;
    }

    public void addGold(Integer goldAdded) { // these are one-liners to save space
        switch (goldAdded) {
            case 2: gold += 250; break;
            case 3: gold -= 100; break;
            case 4: gold += 100; break;
            case 5: gold -= 20; break;
            case 6: gold += 180; break;
            case 7: gold += 0; break;
            case 8: gold -= 70; break;
            case 9: gold += 60; break;
            case 10: gold -= 80; break;
            case 11: gold -= 50; break;
            case 12: gold +=650; break;
            default: break;
        }

        // Wallet cannot go under 0
        if (this.gold < 0) {
            this.gold = 0;
        }
    }
}

class Dice {
    private static final Random random = new Random();
    private static Integer fixedRoll = null;

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
        return random.nextInt(6) + 1;
    }
}