/* *****************************************************************************
 *  Name:              Tom
 *  Coursera User ID:  ?
 *  Last modified:     10/7/2022
 **************************************************************************** */

// All methods assume values passed are indexed from 1.

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridSize;
    private boolean[][] grid;
    private int virtualTopSite;
    private int virtualBottomSite;
    private int numSitesOpen;
    private WeightedQuickUnionUF unionFindObject;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        gridSize = n;
        numSitesOpen = 0;

        if (gridSize <= 0)
            throw new IllegalArgumentException("Size must be greater than zero");

        grid = new boolean[gridSize][gridSize];

        // Create new union find object & Set top and bottom virtual sites
        int totalSites = gridSize * gridSize + 2; // Allow for top and bottom virtual sites
        virtualTopSite = 0;
        virtualBottomSite = totalSites - 1;
        unionFindObject = new WeightedQuickUnionUF(totalSites);

        // Connect top and bottom rows to virtual sites
        int topSitetoConnect;
        int bottomSitetoConnect;
        for (int i = 1; i <= gridSize; i++) {
            topSitetoConnect = xyTo1D(1, i);
            bottomSitetoConnect = xyTo1D(gridSize, i);
            unionFindObject.union(virtualTopSite, topSitetoConnect);
            unionFindObject.union(virtualBottomSite, bottomSitetoConnect);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        if (grid[row - 1][col - 1] == false) {
            grid[row - 1][col - 1] = true;
            linkSites(row, col);
            numSitesOpen++;
        }
    }

    private void linkSites(int row, int col) {
        // Link site left
        int rowToLink;
        int colToLink;
        int baseSite = xyTo1D(row, col);
        int linkSite;

        // Link site left
        if (col > 1) {
            rowToLink = row;
            colToLink = col - 1;
            if (isOpen(rowToLink, colToLink)) {
                linkSite = xyTo1D(rowToLink, colToLink);
                unionFindObject.union(baseSite, linkSite);
            }
        }

        // Link site right
        if (col < (gridSize)) {
            rowToLink = row;
            colToLink = col + 1;
            if (isOpen(rowToLink, colToLink)) {
                linkSite = xyTo1D(rowToLink, colToLink);
                unionFindObject.union(baseSite, linkSite);
            }
        }

        // Link site above
        if (row > 1) {
            rowToLink = row - 1;
            colToLink = col;
            if (isOpen(rowToLink, colToLink)) {
                linkSite = xyTo1D(rowToLink, colToLink);
                unionFindObject.union(baseSite, linkSite);
            }
        }

        // Link site below
        if (row < (gridSize)) {
            rowToLink = row + 1;
            colToLink = col;
            if (isOpen(rowToLink, colToLink)) {
                linkSite = xyTo1D(rowToLink, colToLink);
                unionFindObject.union(baseSite, linkSite);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return this.grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        int siteToCheck = xyTo1D(row, col);
        boolean isJoined = unionFindObject.find(virtualTopSite) == unionFindObject.find(
                siteToCheck);
        boolean isOpen = isOpen(row, col);
        return isJoined && isOpen;
        // return unionFindObject.find(virtualTopSite) == unionFindObject.find(siteToCheck);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numSitesOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFindObject.find(virtualTopSite) == unionFindObject.find(virtualBottomSite);
    }

    // Take 2d row, col co-ordinates and converts to 1d index
    private int xyTo1D(int row, int col) {
        validateIndices(row, col);
        row = row - 1; // Rebase to zero-index
        col = col - 1;
        return (row * gridSize) + col + 1; // Add one due to virtual top site
    }

    // Are row, col values valid?
    private void validateIndices(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
            throw new IllegalArgumentException(
                    "row: " + row + "\n" +
                            "col: " + col + "\n" +
                            "x, y must be greater than zero and less than or equal to " + gridSize);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        // Percolation testPerc = new Percolation(-1);

    }

}
