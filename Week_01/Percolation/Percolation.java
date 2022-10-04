import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    public static void main(String[] args) {
       /*
        1. Start with a n x n grid = total_blocks
        2. Perculation is when there is a path from the top of the grid to the bottom
        2. States can be :
          - open
          - open & connected
          - closed
        3. Initialise all blocks to closed
        4. Randomally select blocks to open-state
        5. Track the number_of_iterations_performed (ie "open blocks" created)
        5. Check if "opening" this new block has enabled perculation
        6. Once perculation is enabled we calculate p*

        p* = number_of_iterations_performed / total_blocks

        We re-run this process x times (millions?) as a monte-carlo simulation
        The average of these results is our output
       */  
    }


    
}
