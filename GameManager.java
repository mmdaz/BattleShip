/**
 * The GameManager program implements an application
 * that manage 1 Player or 2 Players mode of the game
 * and handle them .
 *
 * @author Azhdari Muhammad
 * @since 2018/3/29
 * @version 1.0
 *
 */

import java.io.IOException;
public class GameManager {

    private Player player1 ;
    private Player player2 ;
    private ComputerPlay computerPlay ;

    public GameManager ( Player player1 , Player player2 ) {
        this.player1 = player1 ;
        this.player2 = player2 ;
    }

    public GameManager ( Player player1 , ComputerPlay computerPlay ) {
        this.player1 = player1 ;
        this.computerPlay = computerPlay ;
        player2 = null ;
    }

    public void playDoubles () throws IOException, InterruptedException {
        player1.getShip();
        player2.getShip();
        player1.setOpponentBoard(player2.getOwnBoard());
        player2.setOpponentBoard(player1.getOwnBoard());
        while (true) {
                for (int i = 0; i < 1000; i++) {
                    System.out.println();
                }
                System.out.println(player1.getName()+" Shot ...\n\n");
                player1.getOwnBoard().printOwnBoard();
                player1.getOpponentBoard().printOpponentBoard();
                player1.shoot();
                for (int i = 0; i < 1000; i++) {
                    System.out.println();
                }
                player1.getOwnBoard().printOwnBoard();
                player1.getOpponentBoard().printOpponentBoard();

            System.out.println("give keyboard to " + player2.getName());
            try{
                Thread.sleep(3000);
            }
            catch(InterruptedException e){
                System.out.println(e);
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            for (int i = 0; i < 1000; i++) {
                System.out.println();
            }
            System.out.println(player2.getName()+" Shot ...\n\n");
            player2.getOwnBoard().printOwnBoard();
            player2.getOpponentBoard().printOpponentBoard();
            player2.shoot();
            for (int i = 0; i < 1000; i++) {
                System.out.println();
            }
            player2.getOwnBoard().printOwnBoard();
            player2.getOpponentBoard().printOpponentBoard();

        }
    }

    public void playSingle () {
        player1.getShip();
        computerPlay.getShip();
        player1.setOpponentBoard(computerPlay.getOwnBoard());
        computerPlay.setOpponentBoard(player1.getOwnBoard());
        while (true) {
            for (int i = 0 ; i < 1000 ; i++ ) {
                System.out.println();
            }
            System.out.println(player1.getName()+" Shot ...\n\n");
            player1.getOwnBoard().printOwnBoard();
            player1.getOpponentBoard().printOpponentBoard();
            player1.shoot();
            for (int i = 0; i < 1000; i++) {
                System.out.println();
            }
            player1.getOwnBoard().printOwnBoard();
            player1.getOpponentBoard().printOpponentBoard();
            computerPlay.shot();
        }
    }


}
