package com.cdio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

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

    static Stream<Object[]> sendMessageArguments() {
        return Stream.of(new Object[][]{
            {2, "You entered the tower! Inside the tower, you found a chest with 250 gold!\n"},
            {3, "Oops, you fell into the Crater! Your wallet took a hit. -100 coins!\n"},
            {4, "You've been welcomed at the Palace Gates and received 100 coins!\n"},
            {5, "Brrr! The Cold Desert froze 20 coins from your wallet.\n"},
            {6, "Welcome to the Walled City! You gain 180 coins!\n"},
            {7, "You meditate at the Monastery. No gains, no losses.\n"},
            {8, "You venture into the Black Cave and lose 70 coins!\n"},
            {9, "The Huts in the Mountain gift you 60 coins for being a wonderful guest.\n"},
            {10, "Beware the Werewall! You lose 80 coins, but get an extra turn!\n"},
            {11, "You stumble into The Pit, losing 50 coins.\n"},
            {12, "Jackpot! You've struck gold in the Goldmine! +650 coins!\n"},
            {99, "You entered a mysterious place... nothing happens.\n"}
        });
    }
    @ParameterizedTest(name = "Tile {0} should return message key {1}")
    @MethodSource("sendMessageArguments")
    void testSendMessage(int tileOn, String expectedMessageKey) {
        Tiles.loadLanguage("en");
        assertEquals(expectedMessageKey, Tiles.sendMessage(tileOn));
    }

    @ParameterizedTest(name = "Tile {0} should adjust gold by {1}")
    @DisplayName("Test addGold for all tiles")
    @CsvSource({
        "2, 250",
        "3, -100",
        "4, 100",
        "5, -20",
        "6, 180",
        "7, 0",
        "8, -70",
        "9, 60",
        "10, -80",
        "11, -50",
        "12, 650",
        "99, 0"
    })
    void testAddGold(int tileOn, int expectedGoldChange) {
        int initialGold = wallet.getGold();
        wallet.addGold(tileOn);
        
        int expectedGold = initialGold + expectedGoldChange; //Math.max(initialGold + expectedGoldChange, 0);
        assertEquals(expectedGold, wallet.getGold());
    }

}
