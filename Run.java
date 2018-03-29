/**
 * The Run program is the main class of this
 * BattleShip Game .
 * @author Azhdari Muhammad
 * @since 2018/3/29
 * @version 1.0
 */

import java.io.IOException;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Hello ! Welcome to BattleShip :) ... ") ;
        System.out.println("1) 1 Player ") ;
        System.out.println("2) 2 Players ") ;
        Scanner sc = new Scanner(System.in) ;
        int op ;
        while (true) {
            op = sc.nextInt() ;
            if (op == 1) {
                ComputerPlay cmp = new ComputerPlay();
                System.out.println("Enter your name : ");
                String name = sc.next();
                Player player = new Player(name);
                GameManager gameManager = new GameManager(player, cmp);
                gameManager.playSingle();
                break;
            }
            else if (op == 2) {
                System.out.println("Enter name of first player : ");
                String name = sc.next();
                Player player1 = new Player(name);
                System.out.println("Enter name of second player : ");
                name = sc.next();
                Player player2 = new Player(name);
                GameManager gameManager = new GameManager(player1, player2);
                gameManager.playDoubles();
                break;
            } else {
                System.err.println("Wrong input ...");
                continue;
            }
        }
    }
}
