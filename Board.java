/**
 * The Board program impements an application that
 * simply craet and print and manage the main table of the BattleShip game
 * for each player .
 *
 * @author Azhdari Muhammad
 * @since 2018/3/29
 * @version 1.0
 *
 */

import com.sun.org.apache.regexp.internal.RE;


public class Board {
    private int [][] table ;
    public static final String RESET =  "\u001B[0m" ;
    public static final String RED = "\u001B[41m" ;
    public static final String BLUE = "\u001B[44m" ;
    public static final String GREEN =  "\u001B[42m" ;
    public static final String YELLOW =  "\u001B[43m" ;

    public Board () {
        table = new int[10][10] ;
    }

    /**
     * This Method print and draw the home board of the player .
     *
     * @return void .
     */
    public void printOwnBoard () {

        for (int i = -1 ; i < 10 ; i++ ) {
            for ( int j = -1 ; j < 10 ; j ++ ) {
                if ( i < 0 ) {
                    if ( j < 0) {
                        System.out.print("  |");
                    }
                    else {
                        System.out.print(" "+ j +" |");
                    }
                }
                else if ( j < 0 ) {
                    System.out.print( i + " |");
                }
                else {
                    if (table[i][j] == 1)
                    System.out.print(" " + BLUE +"@" + RESET + " |");
                    else if (table[i][j] == -1)
                        System.out.print(" " + RED + "#" +RESET+ " |");
                    else
                        System.out.print("   |");
                  //  System.out.print(" " + table[i][j] + " |");
                }
            }
            System.out.println();
            for (int j = -1 ; j < 10 ; j++ ) {
                if ( j < 0 ) {
                    System.out.print("--|");
                }
                else {
                    System.out.print("---+");
                }

            }
            System.out.println();
        }
    }

    /**
     * This Method print and draw the opponent Board of the player
     *
     * @return void
     */
    public void printOpponentBoard () {

        for (int i = -1 ; i < 10 ; i++ ) {
            for ( int j = -1 ; j < 10 ; j ++ ) {
                if ( i < 0 ) {
                    if ( j < 0) {
                        System.out.print("  |");
                    }
                    else {
                        System.out.print(" "+ j +" |");
                    }
                }
                else if ( j < 0 ) {
                    System.out.print( i + " |");
                }
                else {
                    if (table[i][j] == -1)
                        System.out.print(" " + GREEN +"&" + RESET + " |");
                    else if (table[i][j] == 2)
                        System.out.print(" "+ YELLOW + "X" + RESET + " |");
                    else
                        System.out.print(" "+ " " + " |");
                  //  System.out.print(" " + table[i][j] + " |" );
                }
            }
            System.out.println();
            for (int j = -1 ; j < 10 ; j++ ) {
                if ( j < 0 ) {
                    System.out.print("--|");
                }
                else {
                    System.out.print("---+");
                }

            }
            System.out.println();
        }
    }

    /**
     * This Method get the start and end cordinates of a ship and
     * add it to the home board of the player .
     * @param firstXShip
     * @param firstYShip
     * @param lastXShip
     * @param lastYShip
     * @return void
     */
    public void addShip ( int firstXShip , int firstYShip , int lastXShip , int lastYShip ) {
        if (firstXShip == lastXShip) {
            for (int i = firstYShip ; i <= lastYShip ;  i ++ ) {
                table[firstXShip][i] = 1 ;
            }
        }
        else {
            for (int i = firstXShip ; i <= lastXShip ; i ++ ) {
                table[i][firstYShip] = 1 ;
            }
        }
    }

    /**
     * This Method get the start and end of a ship and
     * check that if the ship is over the another ship
     * return {true} else return {false} .
     * @param firstXShip
     * @param firstYShip
     * @param lastXShip
     * @param lastYShip
     * @return true or false .
     */
    public boolean checkOverlab ( int firstXShip , int firstYShip , int lastXShip , int lastYShip ) {
        if ( firstXShip == lastXShip ) {
            for (int i = firstYShip ; i <= lastYShip ;  i ++ ) {
                if (table[firstXShip][i] == 1)
                    return true ;
            }
            return false ;
        }
        else {
            for (int i = firstXShip ; i <= lastXShip ; i ++ ) {
               if ( table[i][firstYShip] == 1 )
                   return true ;
            }
            return false ;
        }
    }

    /**
     * This Method get the cordinates of shot of the player
     * and if the shot is repetitious return true
     * else return false .
     * @param x
     * @param y
     * @return true or false .
     */
    public boolean checkOvershot (int x , int y) {
        if (table[x][y] == -1 || table[x][y] == 2 )
            return true ;
        else
            return false ;
    }

    /**
     * This Method gets the cordinates of shot of the player
     * and add it to the opponent board of the player .
     * @param x
     * @param y
     * @return void .
     */
    public void getShot (int x , int y) {
        if (table[x][y] == 1)
            table[x][y] = -1 ;
        else
            table[x][y] = 2 ;
    }

    /**
     * This Method gets cordinates of shot of the player
     * and if the shot is right return true and else return
     * false .
     * @param x
     * @param y
     * @return true or false .
     */
    public boolean shotRight (int x , int y) {
        if  (table[x][y] == 1)
            return true ;
        else
            return false ;
    }

    /**
     * Get home or opponent board of the player .
     * @return int[][] .
     */
    public int[][] getTable() {
        return table;
    }
}
