package com.cdio2;

import java.util.Scanner;
import java.util.Locale;
import java.util.ResourceBundle;

class Game {
    // init game objects and vars
    private Scanner scanner = new Scanner(System.in); 
    private Player player1 = new Player("Player 1", 1000);
    private Player player2 = new Player("Player 2", 1000);
    private Wallet player1Wallet = new Wallet("Player 1", 1000);
    private Wallet player2Wallet = new Wallet("Player 2", 1000);
    private boolean isPlayer1Turn = true;
    private boolean hasExtraTurn = false;
    private static ResourceBundle messages;

    public void start() {
        this.scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.ENGLISH);

        checkLanguage();
        System.out.println(getMessage("game.rules1"));
        System.out.println(getMessage("game.rules2"));
        System.out.println(getMessage("game.rules3"));

        // Set the number of sides for the dice
        System.out.println(getMessage("enter.sides"));
        int sides = Integer.parseInt(scanner.nextLine());
        Dice.setSides(sides, this);

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
                scanner.nextLine();
                break;
            }
        }
        scanner.close();
    }

    public void checkLanguage() {
        System.out.println("Choose language (en for English, da for Danish):");
        String langChoice = scanner.nextLine();

        String langCode;
        if (langChoice.equals("da")) {
            langCode = "da"; 
        } else {
            langCode = "en"; 
        }
        
        loadLanguage(langCode); 
    }

    // Load the resource bundle
    public static void loadLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        messages = ResourceBundle.getBundle("messages", locale);
    }

    // get a message based on a key and format it with optional arguments
    public String getMessage(String key, Object... args) {
        String message = messages.getString(key);
        return String.format(message, args);
    }

    public ResourceBundle getMessages() {
        return messages;
    }

    public Boolean isGameWon() {
        if (player1.getGold() >= 3000) {
            System.out.println(getMessage("game.won", player1.getName()));
            return true;
        }
        if (player2.getGold() >= 3000) {
            System.out.println(getMessage("game.won", player2.getName()));
            return true;
        }
        return false;
    }

    public void doPlayerTurn(Player player, Wallet wallet, String input) {
        if (input == null) {
            System.out.println(player.getName() + ", " + getMessage("press.enter"));
            input = scanner.nextLine();
        }

        if (input.equals("")) { // i.e. if enter is pressed
            Integer die1Result = Dice.roll();
            Integer die2Result = Dice.roll();
            Integer diceResult = die1Result + die2Result;

            wallet.addGold(diceResult);
            player.setGold(wallet.getGold());
            String message1 = Tiles.sendMessage(diceResult, messages);
            System.out.println(message1);

            System.out.println(getMessage("rolled.dice", player.getName(), die1Result, die2Result, diceResult));
            System.out.println(getMessage("total.gold", player.getName(), wallet.getGold()));

            // extra roll if dice sum is 10
            if (diceResult == 10) {
                Dice.clearFixedRoll(); // testing purposes
                hasExtraTurn = true; // testing purposes
                System.out.println(getMessage("extra.roll", player.getName(), diceResult));
                System.out.println(player.getName() + " " + getMessage("extra.turn"));
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