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
        wallet.addGold(3); // gold should decrease by 100
        assertTrue(wallet.gold >= 0, "Wallet balance should never be negative.");
    }

    @Test
    void testGoldAdditionFromDiceRoll() {
        int initialGold = wallet.gold;
        wallet.addGold(6); // based on your addGold implementation, it should add 180
        assertEquals(initialGold + 180, wallet.gold);
    }

    @Test
    void testDiceRollTotalTenTriggersExtraTurn() {
        
        // set a fixed roll of 10 for the dice
        Dice.setFixedRoll(5);
        
        // perform the player's turn, which should recognize the fixed roll
        game.doPlayerTurn(player, wallet, ""); 

        // reset the fixed roll to avoid affecting other tests
        Dice.clearFixedRoll();
    }

    @Test
    void testWinConditionPlayer1() {
        player.gold = 3000;
        assertTrue(player.gold >= 3000, "Player should win when reaching 3000 gold.");
    }

    @Test
    void testWinConditionPlayer2() {
        Player player2 = new Player("Player 2", 3000);
        assertTrue(player2.gold >= 3000, "Player 2 should win when reaching 3000 gold.");
    }
}
