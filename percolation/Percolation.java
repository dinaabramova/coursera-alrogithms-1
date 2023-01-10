import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF unionUF;

    private int size;

    private int virtualTop = 0;

    private int virtualBottom;

    private boolean[] openedSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        int maxCount = n * n + 2;
        unionUF = new WeightedQuickUnionUF(maxCount);
        size = n;
        virtualBottom = n + 1;
        openedSites = new boolean[maxCount];
        openedSites[virtualTop] = false;
        openedSites[virtualBottom] = false;
    }

    private int[] getNeighbours(int row, int col) {
        // (1 - 1) * 5 + (5 - 1) = 4
        // (2 - 1) * 5 + (4 - 1) = 8
        int[] result = new int[4];
        int rowIndex = row - 1;
        int colIndex = col - 1;

        result[0] = col == 1 ? -1 : rowIndex * size + (colIndex - 1) + 1;
        result[1] = row == 1 ? -1 : (rowIndex - 1) * size + colIndex + 1;
        result[2] = col >= size ? -1 : rowIndex * size + (colIndex + 1) + 1;
        result[3] = row >= size ? -1 : (rowIndex + 1) * size + colIndex + 1;

        return result;
    }

    private int getElement(int row, int col) {
        return (row - 1) * size + col;
    }

    private int[] getPlacement(int number) {
        int[] result = new int[2];
        result[0] = number % size == 0 ? number / size : number / size + 1;
        result[1] = number - (result[0] - 1) * size;
        return result;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException();
        }
        int number = getElement(row, col);
        if (row == 1) {
            unionUF.union(number, virtualTop);
        }
        if (row == size) {
            unionUF.union(number, virtualBottom);
        }

        // соединить со всеми соседними открытыми ячейками
        int[] neigbours = getNeighbours(row, col);
        for (int i = 0; i < neigbours.length; i++) {
            if (neigbours[i] >= 0) {
                int[] placement = getPlacement(neigbours[i]);
                if (isOpen(placement[0], placement[1])) {
                    unionUF.union(neigbours[i], number);
                }
            }
        }

        openedSites[number] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException();
        }
        int number = getElement(row, col);
        return openedSites[number];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException();
        }
        return !isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < openedSites.length; i++) {
            if (openedSites[i]) {
                count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionUF.find(virtualTop) == unionUF.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}