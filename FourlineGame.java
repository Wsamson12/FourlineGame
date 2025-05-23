/*=============================================================================
|Programmer:  Wong Ho Shun Sang
|Language:  Java
|To Compile:  javac FourlineGame.java
|To Run:  java FourlineGame
|Module:  ITP3914 - Programming 
|Course:  IT114105- Higher Diploma in Software Engineering
|Created:  13-November-2022 by Wong Ho Shun Sang 
|Description:  To create a  Four-in-a-Line game    
+===========================================================================*/
import java.util.Scanner;
public class FourlineGame{	
    public static void main(String[] args){
        int num;
        Scanner kb = new Scanner(System.in);   
        int[] colIndex = {0,0,0,0,0,0,0};   // height of chess
        int winFlag = 0;
        boolean fullFlag = false;
        int currentPlayer = 1;
        //create grid
        int tableSize = 6;  
        int[] [] mTable= new int [6] [7]; 

        //To run
        while (true) {  
        drawGrid(tableSize,mTable);
        //input for Players
        System.out.println("Player " +currentPlayer+ " type a column (0-6) or 9 to quit current game: ");
        num=kb.nextInt();
        //quit
        if(num==9){
            System.out.println("Bye Bye!");
            break;
        }
        // check error, input again
        else if (getError(num, colIndex))
            continue;
        //add chess for player 1 and player 2 
        else {
            mTable[colIndex[num]][num] = currentPlayer; //  currentplayer 1
            colIndex[num] += 1;
            winFlag = getWin(mTable, currentPlayer); //save getwin in winFlag
            currentPlayer = 3-currentPlayer;    // currentplayer 2= 3-1
        }

        //check draw
        fullFlag = true;            //is full
        for (int i=0;i<7;i++)
            if (colIndex[i] < 5){
                fullFlag = false;   //not full
                break;
            }
            //check win, draw or continue
            if (fullFlag && winFlag == 0){
                System.out.println("DRAW!");
                break;
            }
            else if (winFlag != 0){
                drawGrid(tableSize,mTable);
                System.out.println("Player "+ winFlag +" win this game!");
                break;
            }
        } 
    }

    public static void drawGrid(int tableSize,int [][]mTable){   
        for (int i=tableSize-1;i>=0;i--){
            System.out.printf("%2d |", i);
            for(int j=0;j<7;j++){
                System.out.print(mTable[i][j]+" ");
            }    
            System.out.println();
        }
        // print bottow row
        System.out.println("   +--------------");
        System.out.print( "    0 1 2 3 4 5 6" );
        System.out.println();	
    }

    public static boolean getError(int num, int[] colIndex){  
    //check range
    if (num!=9 && num >6|| num<0){
        System.out.println("Range of column should be 0 to 6!");
        return true;
    } 
    //check full
    else if (colIndex[num] > 5){
        System.out.println("Column " + num + " is full!");
        return true;
    }
    else return false;
    }

    public static int getWin(int[][] mTable, int currentPlayer){
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                // Do not check that all columns are 0 
                if (mTable[i][j] == 0)
                    continue;
                //check Vertical,Horizontal,DiagonalLeft and DiagonalRight
                else {
                    if (checkVertical(currentPlayer, mTable, i, j) || 
                        checkHorizontal(currentPlayer, mTable, i, j) || 
                        checkDiagonalLeft(currentPlayer, mTable, i, j) || 
                        checkDiagonalRight(currentPlayer, mTable, i, j) )
                        return currentPlayer;
                }
            }
        }
        return 0;
    }

    public static boolean checkVertical(int playerNum, int[][] mTable, int currentR, int currentC){
        if (currentR+3 > 5)
            return false;
        else {
            if (mTable[currentR][currentC] == playerNum && 
                mTable[currentR+1][currentC] == playerNum && 
                mTable[currentR+2][currentC] == playerNum && 
                mTable[currentR+3][currentC] == playerNum)
                return true;
                else return false;
        }
    }

    public static boolean checkHorizontal(int playerNum, int[][] mTable, int currentR, int currentC){
        if (currentC+3 > 6)
            return false;
        else {
            if (mTable[currentR][currentC] == playerNum &&
                mTable[currentR][currentC+1] == playerNum && 
                mTable[currentR][currentC+2] == playerNum && 
                mTable[currentR][currentC+3] == playerNum)
                return true;
                else return false;
        }
    }

    public static boolean checkDiagonalLeft(int playerNum, int[][] mTable, int currentR, int currentC){
        if (currentC-3 < 0 || currentR+3 > 5)
            return false;
        else{
            if (mTable[currentR][currentC] == playerNum &&
                mTable[currentR+1][currentC-1] == playerNum && 
                mTable[currentR+2][currentC-2] == playerNum && 
                mTable[currentR+3][currentC-3] == playerNum)
                return true;
                else return false;
        }
    }

    public static boolean checkDiagonalRight(int playerNum, int[][] mTable, int currentR, int currentC){
        if (currentC+3 > 6 || currentR+3 > 5)
            return false;
        else{
            if (mTable[currentR][currentC] == playerNum && 
                mTable[currentR+1][currentC+1] == playerNum && 
                mTable[currentR+2][currentC+2] == playerNum && 
                mTable[currentR+3][currentC+3] == playerNum)
                return true;
                else return false;
        }
    }
}    
    
    
    
              
