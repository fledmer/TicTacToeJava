// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToe consoleGame = new TicTacToe(3);
        System.out.println("X turn: \n" + consoleGame.getMap());
        Scanner in = new Scanner(System.in);
        while(true){
            var str = in.nextLine();
            System.out.println(consoleGame.setInput(str));
        }
    }
}