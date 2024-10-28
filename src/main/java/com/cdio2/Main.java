package com.cdio2;

import java.util.Random;
import java.util.Scanner;
import java.util.Locale;
import java.util.ResourceBundle;

class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    // init game objects and vars
    private Scanner scanner = new Scanner(System.in); 
    private Player player1 = new Player("Player 1", 1000);
    private Player player2 = new Player("Player 2", 1000);
    private Wallet player1Wallet = new Wallet("Player 1", 1000);
    private Wallet player2Wallet = new Wallet("Player 2", 1000);
    private boolean isPlayer1Turn = true;
    private boolean hasExtraTurn = false;

    public void start() {
        this.scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.ENGLISH);

        checkLanguage();

        // Set the number of sides for the dice
        System.out.println("Enter the number of sides for the dice (minimum 2):");
        int sides = Integer.parseInt(scanner.nextLine());
        Dice.setSides(sides);

        // main game loop
        while (true) {
            if (isPlayer1Turn) { 
                doPlayerTurn(player1, player1Wallet, null); 
                isPlayer1Turn = false; 
            } else { 
                doPlayerTurn(player2, player2Wallet, null);
                isPlayer1Turn = true;
            }

            if (isGameWon() == true) {
                break;
            }
        }
        scanner.close();
    }

    public void checkLanguage() {
        System.out.println("Choose language (en for english, da for danish):");
        String langChoice = scanner.nextLine();

        String langCode;
        if (langChoice.equals("da")) {
            langCode = "da"; 
        } else {
            langCode = "en"; 
        }
        
        Tiles.loadLanguage(langCode); 
    }

    public Boolean isGameWon() {
        if (player1.getGold() >= 3000) {
            System.out.println("Game ended! Player 1 won");
            return true;
        }
        if (player2.getGold() >= 3000) {
            System.out.println("Game ended! Player 2 won");
            return true;
        }
        return false;
    }

    public void doPlayerTurn(Player player, Wallet wallet, String input) {
        if (input == null) {
            System.out.println(player.getName() + ", press enter to roll the dice:");
            input = scanner.nextLine();
        }

        if (input.equals("")) { // i.e. if enter is pressed
            Integer die1Result = Dice.roll();
            Integer die2Result = Dice.roll();
            Integer diceResult = die1Result + die2Result;

            wallet.addGold(diceResult);
            player.setGold(wallet.getGold());
            String message = Tiles.sendMessage(diceResult);
            System.out.println(message);

            System.out.println(player.getName() + " rolled: " + die1Result + " and " + die2Result + " , totalling " + diceResult);
            System.out.println(player.getName() + "'s total gold is now: " + wallet.getGold());

            // extra roll if dice sum is 10
            if (diceResult == 10) {
                Dice.clearFixedRoll(); // testing purposes
                hasExtraTurn = true; // testing purposes
                System.out.println(player.getName() + " rolled: " + diceResult + " they get another turn");
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
    private String name;
    private Integer gold;

    Player(String name, Integer gold) {
        this.name = name;
        this.gold = gold;
    }

    // player getters
    public String getName() {
        return this.name;
    }

    public Integer getGold() {
        return this.gold;
    }

    // player setters
    public void setName(String nameSet) {
        this.name = nameSet;
    }

    public void setGold(Integer goldSet) {
        this.gold = goldSet;
    }
}

class Wallet {
    private String owner;
    private Integer gold;

    public Wallet(String owner, Integer gold) {
        this.owner = owner;
        this.gold = gold;
    }

    // wallet getters
    public String getOwner() {
        return this.owner;
    }

    public Integer getGold() {
        return this.gold;
    }

    // wallet setters
    public void setOwner(String ownerSet) {
        this.owner = ownerSet;
    }

    public void setGold(Integer goldSet) {
        this.gold = goldSet;
    }

    public void addGold(Integer goldAdded) { // one-liners to save space
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

class Tiles {

    private static ResourceBundle messages;

    // load language bundle
    public static void loadLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        messages = ResourceBundle.getBundle("messages", locale);
    }

    public static String sendMessage(Integer tileOn) { 
        String messageKey;
        switch (tileOn) {
            case 2: messageKey = "tower"; break;
            case 3: messageKey = "crater"; break;
            case 4: messageKey = "palace"; break;
            case 5: messageKey = "cold_desert"; break;
            case 6: messageKey = "walled_city"; break;
            case 7: messageKey = "monastery"; break;
            case 8: messageKey = "black_cave"; break;
            case 9: messageKey = "huts"; break;
            case 10: messageKey = "werewall"; break;
            case 11: messageKey = "pit"; break;
            case 12: messageKey = "goldmine"; break;
            default: messageKey = "unknown"; break;
        }
        return messages.getString(messageKey);
    }
}

class Dice {
    private static final Random random = new Random();
    private static Integer fixedRoll = null;
    private static int sides = 6;

    public static void setSides(int newSides) {
        if (newSides >= 2) { // minimum 2 sides for a dice
            sides = newSides;
        } else {
            System.out.println("Dice must have at least 2 sides.");
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