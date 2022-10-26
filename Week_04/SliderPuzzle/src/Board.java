import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;

public class Board {

    /* The problem. 
    * The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 
    * square tiles labeled 1 through 8, plus a blank square. The goal is to 
    * rearrange the tiles so that they are in row-major order, using as few moves 
    * as possible. You are permitted to slide tiles either horizontally or vertically 
    * into the blank square. The following diagram shows a sequence of moves from an 
    * initial board (left) to the goal board (right).
    */

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    
    private int sliderBoard[][];
    private int boardSize;
    
    public Board(int[][] tiles) {
        /* Constructor.  
        * You may assume that the constructor receives an n-by-n array containing 
        * the n2 integers between 0 and n2 − 1, where 0 represents the blank square. 
        * You may also assume that 2 ≤ n < 128.
         */
        boardSize = tiles.length;
        sliderBoard = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                sliderBoard[i][j] = tiles[i][j];
            }
        }
    }
                                           
    // string representation of this board
    public String toString() {
        /*String representation.  
        * The toString() method returns a string composed 
         * of n + 1 lines. The first line contains the board size n; the remaining 
         * n lines contains the n-by-n grid of tiles in row-major order, using 0 to 
         * designate the blank square.
         */
        StringBuilder s = new StringBuilder();
        s.append(boardSize + "\n");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                s.append(String.format("%2d ", sliderBoard[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return boardSize;
    }

    // number of tiles out of place
    public int hamming() {
        /*The Hamming distance betweeen a board and the goal board is the number of 
        * tiles in the wrong position.
        */
        int countWrongPositions = 0;
        int currentCorrectNumber = 1;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (sliderBoard[i][j] != currentCorrectNumber) countWrongPositions++;
                currentCorrectNumber++;
            }
        }
        return countWrongPositions;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        /*The Manhattan distance between a board and the goal board is the sum of 
        * the Manhattan distances (sum of the vertical and horizontal distance) 
        * from the tiles to their goal positions. 
        */
        int cumulativeManhattan = 0;
        int targetNumber = 1;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (sliderBoard[i][j] != targetNumber)  cumulativeManhattan += getManhattanDistance(i, j, targetNumber);
                targetNumber++;
            }
        }
        return cumulativeManhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int currentCorrectNumber = 1;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (sliderBoard[i][j] != currentCorrectNumber) return false;
                currentCorrectNumber++;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        /*Comparing two boards for equality.  
        * Two boards are equal if they are have the same size and their corresponding 
        * tiles are in the same positions. The equals() method is inherited from 
        * java.lang.Object, so it must obey all of Java’s requirements.
        */
        Board that = (Board) y;

        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        if (that.dimension() != this.dimension()) return false;

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.getXYValue(i, j) != that.getXYValue(i, j)) return false;
                // if (sliderBoard[i][j] != that[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        /*Neighboring boards.  
        * The neighbors() method returns an iterable containing 
        * the neighbors of the board. Depending on the location of the blank square, 
        * a board can have 2, 3, or 4 neighbors.
        */
        // int numNeighbors;
        int tempValue;
        int size = this.dimension();
        int tempBoard[][] = new int[size][size];
        int zeroX = 0;
        int zeroY = 0;   // x, y position of zero tile
        int swapX, swapY;
        ArrayList<Board> neighborsList = new ArrayList<Board>();

        // Find location of zero tile
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.getXYValue(i, j) == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }

        /*
        // Set number of neighbours
        if (((zeroX == 0) || (zeroX == size-1)) && ((zeroY == 0) || (zeroY == size-1))) numNeighbors = 2;       // Zero tile in corner
        else if (((zeroX == 0) || (zeroX == size-1)) || ((zeroY == 0) || (zeroY == size-1))) numNeighbors = 3;  // Zero tile on edge row or colum, but not corner
        else numNeighbors = 4;                                                                                  // Zero tile off edge rows and columns ("Somewhere in the middle")
        */

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                swapX = zeroX + i;
                swapY = zeroY + j;
                if ((swapX >= 0 && swapX < size) &&                     // Check outside x bounds
                    (swapY >= 0 && swapY < size) &&                     // Check outside y bounds
                    //(swapX != zeroX && swapY != zeroY) &&              // Check not at the zero position
                    (Math.abs(i) != Math.abs(j)))                       // Check not on diagonals
                    {               
                    tempValue = this.getXYValue(swapX, swapY);          // Save value to swap
                    tempBoard = copyBoardArray(this);                   // Make copy of board
                    Board newBoard = new Board(tempBoard);              // Create new board object
                    newBoard.changeXYValue(swapX, swapY, 0);  //  
                    newBoard.changeXYValue(zeroX, zeroY, tempValue);    // Swap target tile with zero tile
                    neighborsList.add(newBoard);                        // Add board to ArrayList
                }
            }
        }
        return neighborsList;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board newBoard = new Board(this.sliderBoard);
        boolean found = false;
        int x1, y1, x2, y2, val1, val2;

        while (found == false) {

            x1 = StdRandom.uniformInt(boardSize);
            x2 = StdRandom.uniformInt(boardSize);
            y1 = StdRandom.uniformInt(boardSize);
            y2 = StdRandom.uniformInt(boardSize);

            if (!(x1 == x2 && y1 == y2) &&          // No self-swap
                (this.getXYValue(x1, y1) != 0) &&   //
                (this.getXYValue(x2, y2) != 0))     // No zero-values
                {
                val1 = this.getXYValue(x1, y1);
                val2 = this.getXYValue(x2, y2);
                newBoard.changeXYValue(x1, y1, val2);
                newBoard.changeXYValue(x2, y2, val1);
                found = true;
            }
        }
        return newBoard;
    }

    private int getXYValue(int x, int y){
        return sliderBoard[x][y];
    }

    private void changeXYValue(int x, int y, int newValue){
        this.sliderBoard[x][y] = newValue;
    }

    private int getManhattanDistance(int x, int y, int targetNumber){
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (sliderBoard[i][j] == targetNumber) return (Math.abs(x - i) + Math.abs(y - j));
            }
        }
        return 0; // Not found -> null (zero) tile
    }

    private int[][] copyBoardArray(Board sourceBoard){
        int boardSize = sourceBoard.dimension();
        int newBoard[][] = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                newBoard[i][j] = sourceBoard.getXYValue(i, j);
            }
        }
        return newBoard;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        /*Unit testing.  
        * Your main() method should call each public method directly 
        * and help verify that they works as prescribed (e.g., by printing results 
        * to standard output).
         */
        //for (String filename : args) {
            String filename = "tests/puzzle04.txt";
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }
            Board initial = new Board(tiles);
            System.out.println(initial.toString());

            // Test: Neighbours
            System.out.println("~Testing Neighbors~");
            for (Board boardInList : initial.neighbors()){
                System.out.println(boardInList.toString());
            }

            // Test: Twins
            System.out.println("~Testing Twins~");
            System.out.println("Base Board~");
            System.out.println(initial.toString());
            System.out.println("Twins~");
            Board twin01 = initial.twin();
            Board twin02 = initial.twin();
            Board twin03 = initial.twin();
            System.out.println(twin01.toString());
            System.out.println(twin02.toString());
            System.out.println(twin03.toString());

            // Test: Manhattan
            System.out.println("~Testing Manhattan~");
            System.out.print("Output: " + initial.manhattan() + "\n");
            System.out.println("Expected: 4");

        //}
        
    }
}