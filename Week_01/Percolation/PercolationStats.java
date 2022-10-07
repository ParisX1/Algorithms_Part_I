/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private double resultsArray[];
    private double pValue;
    //private int iterationNum;
    public double mean;
    public double stdDev;
    public double confLow;
    public double confHigh;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n < 1 || trials < 1)
            throw new IllegalArgumentException("Grid size and trials must be one or greater.");

        resultsArray = new double[trials];
        //iterationNum = 0;

        for (int i = 0; i < trials; i++){
            int randRow;
            int randCol;

            Percolation testPercolation = new Percolation(n);
            while (!testPercolation.percolates()){
                randRow = StdRandom.uniformInt(n) + 1;
                randCol = StdRandom.uniformInt(n) + 1;
                if (!testPercolation.isOpen(randRow, randCol)){
                    testPercolation.open(randRow, randCol);
                }
            }
            pValue = (double) testPercolation.numberOfOpenSites() / (n * n);
            resultsArray[i] = pValue;
        }
        mean = mean();
        stdDev = stddev();
        confLow = confidenceLo(mean, stdDev, trials);
        confHigh = confidenceHi(mean, stdDev, trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(resultsArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(resultsArray);
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
        int gridSize = 10;
        int numTrials = 10000;
        if(args.length > 0) {
            gridSize = Integer.parseInt(args[0]);
            numTrials = Integer.parseInt(args[1]);
        }

        PercolationStats trailStats = new PercolationStats(gridSize, numTrials);
        System.out.println("mean \t\t\t\t = " + trailStats.mean);
        System.out.println("stddev \t\t\t\t = " + trailStats.stdDev);
        System.out.println("95% confidence interval \t = [" + trailStats.confLow + ", " + trailStats.confHigh + "]");

    }

}
