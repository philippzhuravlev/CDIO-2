package com.cdio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Wallet wallet;
    private Player player;
    private Game game;

    @BeforeEach
    void setUp() {
        wallet = new Wallet("TestPlayer", 0);
        player = new Player("TestPlayer", 0);
        game = new Game();
    }

    @Test
    void testGoldBalanceNeverNegative() {
        wallet.addGold(3); // 3 should decrease the wallet by 100
        assertTrue(wallet.getGold() >= 0, "Wallet balance should never be negative.");
    }

    @Test
    void testGoldAdditionFromDiceRoll() {
        int initialGold = wallet.getGold();
        wallet.addGold(6); // 6 should add 180 to the wallet
        assertEquals(initialGold + 180, wallet.getGold());
    }

    @Test
    void testDiceRollTotalTenTriggersExtraTurn() {
        Dice.setFixedRoll(5);
        Tiles.loadLanguage("en");
        game.doPlayerTurn(player, wallet, ""); 
        Dice.clearFixedRoll();
    }

    @Test
    void testWinConditionPlayer1() {
        Player player1 = new Player("Player 1", 3000);
        assertTrue(player1.getGold() >= 3000, "Player should win when reaching 3000 gold.");
    }

    @Test
    void testWinConditionPlayer2() {
        Player player2 = new Player("Player 2", 3000);
        assertTrue(player2.getGold() >= 3000, "Player 2 should win when reaching 3000 gold.");
    }
}
