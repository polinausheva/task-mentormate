import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Cell {

    protected int greenNeighbours;
    protected int redNeighbours;

    //col , row : representing the cell you want to find neighbours to
    public int calculateNeighbours(int[][] grid, int col, int row) {

        int sum = 0;
        //find all surrounding cells by adding +/- 1 to col and row
        for (int colNum = col - 1; colNum <= (col + 1); colNum += 1) {

            for (int rowNum = row - 1; rowNum <= (row + 1); rowNum += 1) {

                //if not the center cell
                if (!((colNum == col) && (rowNum == row))) {

                    //make sure it is within  grid
                    if (withinGrid(colNum, rowNum, grid)) {
                       int currentCell = grid[colNum][rowNum];
                        if (currentCell==1) {
                            sum++;
                        }
                    }
                }
            }
        }
        return sum;
    }

    //define if cell represented by colNum, rowNum is inside grid
    //function used by neighbours()
    private boolean withinGrid(int colNum, int rowNum, int[][] grid) {

        if ((colNum < 0) || (rowNum < 0)) {
            return false;    //false if row or col are negative
        }
        return (colNum < grid.length) && (rowNum < grid.length);    //false if row or col are bigger than grid
    }

    public abstract int[][] update(int[][] lastGeneratedGrid,int[][] tempGrid, int x, int y);

    public abstract boolean shouldChangeColor(int sum);
}
