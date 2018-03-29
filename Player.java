import org.omg.CORBA.MARSHAL;
/**
 * The Player program implements an application that
 * simply manage the work of the human player can do in
 * the BattleShip game .
 * @author Azhdari Muhammad .
 * @since 2018/3/29
 * @version 1.0
 *
 */

import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name ;
    private int ships ;
    private Board ownBoard ;
    private Board opponentBoard ;


    public Player ( String name ) {
        this.name = name ;
        ownBoard = new Board() ;
        opponentBoard = new Board() ;
        ships = 0 ;
    }

    /**
     * This Method get the start and end of ships from user
     * and send them to the home board of the player .
     *
     * @return void .
     */
    public void getShip () {
        System.out.println("Hello "+name);
        Scanner sc = new Scanner(System.in) ;
        int op , firstXShip = 0 , firstYShip = 0 , lastXShip = 0 , lastYShip = 0 ;
        System.out.println("now ! You can declare 5 ships :");
        for (int i = 1 ; i < 5 ; i ++ ) {
            System.out.println( "declare " + i + "st ship : ");
            System.out.println("Your ship is :\n 1) Horizontal  2) Vertical\n press 1 or 2 :  ");
            op = validInput(1,2) ;
            while (true) {
                System.out.println("Enter start cordinates of your ship : ");
                System.out.println("Enter x position of first of the ship : ");
                firstXShip = validInput(0, 9);
                System.out.println("Enter y position of first of the ship : ");
                firstYShip = validInput(0, 9);
                System.out.println("Enter x position of last of the ship : ");
                lastXShip = validInput(0, 9);
                System.out.println("Enter y position of last of the ship : ");
                lastYShip = validInput(0, 9);

                // check horizontal and vertical validity and check that two ships are not over together :

                if (op == 1) {
                    if (firstXShip != lastXShip || ownBoard.checkOverlab(firstXShip,firstYShip,lastXShip,lastYShip) || !shipLenthValidity(firstXShip,firstYShip,lastXShip,lastYShip)) {
                        System.err.println("Wrong input .");
                        System.out.println("Enter valid input .");
                        continue;
                    }
                    ships = ships + Math.abs( firstYShip - lastYShip ) ;
                    if (firstXShip == lastXShip)
                        break;
                }
                else{
                    if (firstYShip != lastYShip || ownBoard.checkOverlab(firstXShip,firstYShip,lastXShip,lastYShip) || !shipLenthValidity(firstXShip,firstYShip,lastXShip,lastYShip)) {
                        System.err.println("Wrong input .");
                        System.out.println("Enter valid input .");
                        continue;
                    }
                    ships = ships + Math.abs( firstXShip - lastXShip ) ;
                    if (firstYShip == lastYShip)
                        break;
                }


            }
            ownBoard.addShip(firstXShip,firstYShip,lastXShip,lastYShip);
        }

    }

    /**
     * This Method get start and end of a period
     * and get a number from user and if user enter
     * invalid number that out of the period print error
     * and wants from user to enter again .
     * @param start
     * @param finish
     * @return standard number that  in the period .
     */
    private int validInput (int start , int finish) {
        Scanner sc = new Scanner(System.in) ;
        String input ;
        int intInput ;
        while (true) {
            input = sc.next() ;
            try {
                intInput = Integer.parseInt(input) ;
            }catch (NumberFormatException ex) {
                System.err.println("Wrong input .");
                System.out.println("Enter valid input .");
                continue;
            }
            if (intInput < start || intInput >finish) {
                System.err.println("Wrong input .");
                System.out.println("press valid input .");
                continue;
            }
            else
                break;
        }
        return intInput ;
    }

    /**
     * This Method get cordinates of start and end af a ship
     * and chek that the lenth of the ship is standard .
     * It means that lenth of the ship should be  2 or 3 or 4 or 5 .
     * If lenth of the ship equals these numbers return true
     * and else return false .
     * @param xFirst
     * @param yFirst
     * @param xFinish
     * @param yFinish
     * @return true or false .
     */

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

    /**
     * This Method get the cordinates of shot of thr player and
     * pass it to the opponent board the player .
     *
     * @return void .
     */
    public void shoot () {
        while (true) {
            System.out.println("Choose one shot : \n 1) Faulted shot    2) Accurate shot ");
            int op = validInput(1, 2);
            int x, y;
            if (op == 1) {
                Random random = new Random();
                while (true) {
                    int rand = random.nextInt(2);
                    rand = rand - 1;
                    System.out.println("Enter X : ");
                    x = validInput(0, 9);
                    x += rand;
                    rand = random.nextInt(2);
                    rand = rand - 1;
                    System.out.println("Enter Y : ");
                    y = validInput(0, 9);
                    y += rand;
                    if (opponentBoard.checkOvershot(x, y)) {
                        System.err.println("This shot is repetitious .");
                        continue;
                    } else
                        break;
                }
            } else {
                while (true) {
                    System.out.println("Enter X : ");
                    x = validInput(0, 9);
                    System.out.println("Enter Y : ");
                    y = validInput(0, 9);
                    if (opponentBoard.checkOvershot(x, y)) {
                        System.err.println("This shot is repetitious .");
                        continue;
                    } else
                        break;
                }
            }
            if (opponentBoard.shotRight(x, y)) {
                System.out.println("Your shot is right ... \n shot again ...");
                destroyShip();
                opponentBoard.getShot(x, y);
                ownBoard.printOwnBoard();
                opponentBoard.printOpponentBoard();
                if ( winPlayer() ) {
                    System.out.println(name +" win ...");
                    System.exit(1);
                }
                continue;
            }
            else {
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
    private void destroyShip () {
        ships -- ;
    }

    public String getName() {
        return name;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public Board getOwnBoard() {
        return ownBoard;
    }

    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    public void setOwnBoard(Board ownBoard) {
        this.ownBoard = ownBoard;
    }
}
