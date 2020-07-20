public class Green extends Cell {

    @Override
    public int[][] update(int[][] lastGeneratedGrid, int[][] tempGrid, int x, int y) {
        int greenNeighbours = super.calculateNeighbours(lastGeneratedGrid, x, y);
        if (shouldChangeColor(greenNeighbours)) {
            tempGrid[x][y] = 0;
        }
        return tempGrid;
    }


    @Override
    public boolean shouldChangeColor(int sum) {
        return sum != 2 && sum != 3 && sum != 6;
    }
}
