public class PercolationStats {
    double[] results;
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        results = new double[trials];
        for (int i = 0; i < trials; i++)
        {
            Percolation perc = new Percolation(n);
            while(perc.percolates() == false)
            {
                int row = (int) (Math.random() * perc.N + 1.0);
                int column = (int) (Math.random() * perc.N + 1.0);
                perc.open(row, column);
            }
            double percent = perc.numberOfOpenSites()/Math.pow(perc.N,2);
            results[i] = percent;
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        double sum = 0;
        for (int i = 0; i < results.length; i++)
        {
            sum += results[i];
        }

        return sum/results.length;
    }

    public double stddev()                        // sample standard deviation of percolation threshold
    {
        double mean = mean();
        double sum = 0;
        for( int i = 0; i< results.length; i++)
            sum += Math.pow((results[i]-mean),2);
        double temp = sum /(results.length-1);
        return Math.sqrt(sum);
    }

    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - 1.96*stddev()/Math.sqrt(results.length);
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + 1.96*stddev()/Math.sqrt(results.length);
    }

    public static void main(String[] args)        // test client (described below)
    {
        PercolationStats newTest = new PercolationStats(200, 100);
        System.out.println("mean = " + newTest.mean());
        System.out.println("stddev = " + newTest.stddev());
        System.out.printf("95 confidence interval = [%f, %f]", newTest.confidenceLo(), newTest.confidenceHi() );
    }

}