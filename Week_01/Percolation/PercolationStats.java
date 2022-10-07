/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1)
            throw new IllegalArgumentException("Grid size and trials must be one or greater.");

        for (int i = 0; i < trials; i++){
            int randRow;
            int randCol;

            Percolation testPercolation = new Percolation(n);
            while (!testPercolation.percolates()){
                randRow = StdRandom.uniformInt(n+1);
                randCol = StdRandom.uniformInt(n+1);
                if (!testPercolation.isOpen(randRow, randCol)){
                    testPercolation.open(randRow, randCol);
                }
            }
        }
    }

    // sample mean of percolation threshold
    public double mean(int[] valuesArray) {
        return StdStats.mean(valuesArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev(int[] valuesArray) {
        return StdStats.stddev(valuesArray);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(double mean, double stdDev, int num) {
        return mean - ( (1.96 * stdDev) / (Math.sqrt(num)) );
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(double mean, double stdDev, int num) {
        return mean + ( (1.96 * stdDev) / (Math.sqrt(num)) );
    }

    // test client (see below)
    public static void main(String[] args) {
        /*
        Also, include a main() method that takes two command-line arguments
        n and T, performs T independent computational experiments (discussed above)
        on an n-by-n grid, and prints the sample mean, sample standard deviation,
        and the 95% confidence interval for the percolation threshold. Use StdRandom
        to generate random numbers; use StdStats to compute the sample mean and sample
        standard deviation.
         */

        PercolationStats trailStats = new PercolationStats(10, 100);
        System.out.println("mean /t" + trailStats.)

    }

}
