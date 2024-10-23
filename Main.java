import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private Scanner scanner;
    private Boolean isPlayer1Turn = true; 
    private Player player1 = new Player("Player 1", 0);
    private Player player2 = new Player("Player 2", 0);

    public void start() {
        this.scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.ENGLISH);

        while (true) {
            if (isPlayer1Turn) { 
                doPlayerTurn(player1); 
                isPlayer1Turn = false; 
            } else { 
                doPlayerTurn(player2);
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

    public void doPlayerTurn(Player player) {
        System.out.println(player.name + ", press enter to roll the dice:");
        String input = scanner.nextLine();
        
        if (input.equals("")) { // i.e. if enter is pressed
            Integer die1Result = Dice.roll();
            Integer die2Result = Dice.roll();
            Integer diceResult = die1Result + die2Result;

            // add gold
        }
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

    // add extra turn function
}

class Dice {
    private static final Random random = new Random(); 

    public static int roll() {
        return random.nextInt(6) + 1; 
    }
}