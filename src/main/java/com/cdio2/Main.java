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

            if (isGameWon() == true) {
                break;
            }
        }
        scanner.close();
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
    public static String sendMessage(Integer tileOn) { 
        String message = "";
        switch (tileOn) {
            case 2: message = "You entered the tower! \n Inside the tower you found a with a chest with 250 gold in it! \n"; break;
            case 3: message = "Oops, you fell into the Crater!\nGood news: you're now an expert in geology. Bad news: your wallet took a hit. -100 coins!\n"; break;
            case 4: message = "You’ve been welcomed at the Palace Gates.\n They mistook you for royalty and handed you 100 coins. Don't ask why, just smile and wave! \n"; break;
            case 5: message = "Brrr! The Cold Desert is freezing your hopes and your wallet.\n Your frostbitten fingers fumble away 20 coins.\n"; break;
            case 6: message = "Welcome to the Walled City, where the streets are paved with gold. . .\nwell, maybe not gold, but close enough. You gain 180 coins!\n"; break;
            case 7: message = "You spend some time meditating at the Monastery.\nInner peace is great and all, but your wallet remains unchanged. No gains, no losses.\n"; break;
            case 8: message = "You venture into the Black Cave and... what’s that?\nOh, just your luck running away. You lose 70 coins!\n"; break;
            case 9: message = "The Huts in the Mountain welcome you with open arms and delicious stew.\nThey gift you 60 coins for being such a wonderful guest.\n"; break;
            case 10: message = "Beware the Werewall! Werewall? What?!?\nYou lose 80 coins, but the Were and the Wall gives you an extra turn. Spooky, but kinda cool, right?\n"; break;
            case 11: message = "You stumble into The Pit.\nTurns out it's not as deep as your financial loss. You lose 50 coins but climb out relatively unharmed.\n"; break;
            case 12: message = "Jackpot! You’ve struck gold in the Goldmine!\nTime to buy that solid-gold yacht you’ve always dreamed of. +650 coins!\n"; break;
            default: message = "You entered ?VOID? \nHow did you end up here? "; break;
        }
        return message;
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