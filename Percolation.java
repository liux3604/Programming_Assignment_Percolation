public class Percolation {
    boolean[] openOrNot;
    int[] parent;
    int[] size;
    int N;
    int numOfOpenSites;

    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        numOfOpenSites=0;
        N = n;
        openOrNot = new boolean[(int)Math.pow(N,2)+2];
        parent = new int[(int)Math.pow(N,2)+2];
        size = new int[(int)Math.pow(N,2)+2];
        for(int i=0;i<(int)Math.pow(N,2)+2;i++)
        {
            parent[i]=i;
        }
        for(int i=0;i<(int)Math.pow(N,2)+2;i++)
        {
            size[i]=1;
        }
        openOrNot[0]=true;
        openOrNot[(int)Math.pow(N,2)+1]=true;
        for(int i=1;i<=N;i++)
        {
            open(1,i);
            union(0,i);
        }
        for(int i=1; i<=N; i++)
        {
            open(N,i);
            union((int)Math.pow(N,2)+1,N*(N-1)+i);
        }
    }

    private int root(int index)
    {
        if (parent[index] == index)
        {
            return index;
        }
        else
        {
            return root(parent[index]);
        }

    }

    private void union(int index1, int index2)
    {
        int root1 = root(index1);
        int size1 = size[root1];
        int root2 = root(index2);
        int size2 = size[root2];
        if(size1 <= size2 && root1!=root2)
        {
            parent[root1] = root2;
            size[root2] += size[root1];
        }
        if (size1 > size2 && root1!=root2)
        {
            parent[root2] = root1;
            size[root1] += size[root2];
        }
    }

    private boolean find(int index1, int index2)
    {
        if(root(index1) == root(index2))
            return true;
        else
            return false;
    }



    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        // the index of the grid is (row-1)*N+column in the array
        int index = (row-1)*N+col;
        if (openOrNot[index] != true)
        {
            openOrNot[index] = true;
            numOfOpenSites++;

            int m;
            int n;

            m=row-1;
            n=col;
            if( m>0 && m<=N && n>0 && n<=N && isOpen(m,n))
            {
                int index2 = (m-1)*N+n;
                union(index, index2);
            }

            m=row+1;
            n=col;
            if( m>0 && m<=N && n>0 && n<=N && isOpen(m,n))
            {
                int index2 = (m-1)*N+n;
                union(index, index2);
            }

            m=row;
            n=col-1;
            if( m>0 && m<=N && n>0 && n<=N && isOpen(m,n))
            {
                int index2 = (m-1)*N+n;
                union(index, index2);
            }

            m=row;
            n=col+1;
            if( m>0 && m<=N && n>0 && n<=N && isOpen(m,n))
            {
                int index2 = (m-1)*N+n;
                union(index, index2);
            }
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        int index = (row - 1) * N + col;
        return openOrNot[index] == true;
    }


    public int numberOfOpenSites() // number of open sites
    {
        return numOfOpenSites;
    }

    public boolean percolates() // does the system percolate?
    {
        // similar to the find method in weighted-union-find
        return find(0, (int) Math.pow(N,2)+1);
    }



    public static void main(String[] args) // test client (optional)
    {
        Percolation perc = new Percolation(100);
        while(perc.percolates() == false)
        {
            int row = (int) (Math.random() * perc.N + 1.0);
            int column = (int) (Math.random() * perc.N + 1.0);
            perc.open(row, column);
            System.out.println("Num of sites open: " + perc.numOfOpenSites);
        }
        double percent = perc.numberOfOpenSites()/Math.pow(perc.N,2);
        System.out.println("The radio is: " + percent);
    }
}
