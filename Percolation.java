public class Percolation {
    boolean[][] grids;
    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        grids= new boolean[n][n];
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (grids[row][col] != true)
        {
            grids[row][col] = true;
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return ( grids[row][col] == true );
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {

    }

    public int numberOfOpenSites() // number of open sites
    {

    }

    public boolean percolates() // does the system percolate?
    {

    }

    public static void main(String[] args) // test client (optional)
    {}
}