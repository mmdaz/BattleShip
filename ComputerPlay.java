/**
 * The ComputerPlay program implements an application that
 * simply handle the works that the computer can do and play .
 *
 * @author Azhdari Muhammad
 * @since 2018/3/29
 * @version 1.0
 */

import java.util.Random;

public class ComputerPlay {
    private int ships ;
    private Board ownBoard ;
    private Board opponentBoard ;

    public ComputerPlay () {
        ships = 0 ;
        ownBoard = new Board() ;
        opponentBoard = new Board() ;
    }

    public void getShip () {
        int firstXShip = 0 , firstYShip = 0 , lastXShip = 0 , lastYShip = 0 ;
        for (int i = 0 ; i < 5 ; i++ ) {
            Random random = new Random();
            int rand = random.nextInt(2);
            rand++;
            while (true) {
                if (rand == 1) {
                    firstXShip = random.nextInt(10);
                    lastXShip = firstXShip;
                    firstYShip = random.nextInt(10);
                    lastYShip = random.nextInt(10);
                } else {
                    firstYShip = random.nextInt(10);
                    lastYShip = firstYShip;
                    firstXShip = random.nextInt(10);
                    lastXShip = random.nextInt(10);
                }
                if (ownBoard.checkOverlab(firstXShip,firstYShip,lastXShip,lastYShip) ||!shipLenthValidity(firstXShip,firstYShip,lastXShip,lastYShip) )
                    continue;
                else
                    break;
            }
            ownBoard.addShip(firstXShip,firstYShip,lastXShip,lastYShip);
        }
    }

    public void shot () {
        Random random = new Random();
        int x, y;
        while (true) {
            while (true) {
                x = random.nextInt(10);
                y = random.nextInt(10);
                if (opponentBoard.checkOvershot(x, y)) {
                    continue;
                } else
                    break;
            }
                if (opponentBoard.shotRight(x, y)) {
                    this.ships--;
                    if (winPlayer()) {
                        System.out.println("You lose ...");
                        System.exit(1);
                    }
                    continue;
                } else {
                    opponentBoard.getShot(x, y);
                    break;
                }
            }
    }

    private boolean winPlayer () {
        for (int i = 0 ; i < 10 ; i++ ) {
            for (int j = 0 ; j < 10 ; j++ ) {
                if (opponentBoard.getTable()[i][j] == 1)
                    return false ;
            }
        }
        return true ;
    }

    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    public Board getOwnBoard() {
        return ownBoard;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public int getShips() {
        return ships;
    }

    private boolean shipLenthValidity (int xFirst , int yFirst , int xFinish , int yFinish ) {
        if ( xFirst == xFinish ) {
            if (Math.abs( yFinish - yFirst ) < 1  || Math.abs( yFinish - yFirst ) > 5 )
                return false ;
            else
                return true ;
        }
        else {
            if ( Math.abs( xFinish - xFirst ) < 1 || Math.abs( xFinish - xFirst ) > 5 )
                return false ;
            else
                return true ;
        }
    }

}
