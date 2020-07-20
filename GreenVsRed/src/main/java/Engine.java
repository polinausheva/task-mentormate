import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Engine {

    public void run() {

        try {
            // get the height and width from the user
            int[] heightAndWidth = heightAndWidthFromUser();
            int height = heightAndWidth[0];
            int width = heightAndWidth[1];

            // create a grid
            Grid grid = new Grid(height, width);

            // get the initial grid from the user
            out.println("Enter Generation Zero:");
            int[][] currentGeneration = grid.fill(height, width);

            // get the coordinates and the number of iterations from the user
            int[] coordinatesAndIterationNum = coordinatesAndIterationNumFromUser();
            int x2 = coordinatesAndIterationNum[0];
            int y2 = coordinatesAndIterationNum[1];
            int timesToIterate = coordinatesAndIterationNum[2];

            // create a temporary grid holder when checking if an update for a cell is needed
            int[][] tempGrid = new int[height][width];
            grid.copy2Darray(currentGeneration, tempGrid);

            // create an updated grid holder, which updates the currentGeneration grid, so it stays up to date
            int[][] updatedGrid = new int[height][width];

            int timesChangedToGreen = 0;

            if (currentGeneration[y2][x2] == 1) {
                timesChangedToGreen++;
            }

            // go through each cell
            for (int i = 0; i < timesToIterate; i++) {

                for (int row = 0; row < currentGeneration.length; row++) {
                    for (int col = 0; col < currentGeneration[row].length; col++) {

                        int currentCell = currentGeneration[row][col];

                        if (currentCell == 1) {
                            // if the current cell is green - a cell is created
                            Cell green = new Green();
                            // updates the whole tempGrid if needed
                            tempGrid = green.update(currentGeneration, tempGrid, row, col);
                            // we get the changed cell and put it in the updated grid
                            grid.copyCell(row, col, tempGrid[row][col], updatedGrid);
                            // we remove the updated cell in the temporary grid, so there are 0 changes made in it
                            grid.copy2Darray(currentGeneration, tempGrid);

                        } else if (currentCell == 0) {
                            // if the current cell is red - a cell is created
                            Cell red = new Red();
                            // updates the whole tempGrid if needed
                            tempGrid = red.update(currentGeneration, tempGrid, row, col);
                            // we get the changed cell and put it in the updated grid
                            grid.copyCell(row, col, tempGrid[row][col], updatedGrid);
                            // we remove the updated cell in the temporary grid, so there are 0 changes made in it
                            grid.copy2Darray(currentGeneration, tempGrid);
                        }
                    }
                }

                // update the current generation and the temporary generation
                grid.copy2Darray(updatedGrid, currentGeneration);
                grid.copy2Darray(currentGeneration, tempGrid);
                // update the counter if the updated cell is green
                if (currentGeneration[y2][x2] == 1) {
                    timesChangedToGreen++;
                }
            }
            out.println(timesChangedToGreen);
        } catch (NumberFormatException e) {
            out.println("Incorrect format");
        } catch (ArrayIndexOutOfBoundsException e) {
            out.println("Incorrect input, the array was out of bounds");
        } catch (Exception e) {
            out.println("A mistake occurred");
        }
    }

    public int[] heightAndWidthFromUser() {
        out.println("enter height and width:");

        Scanner scanner = new Scanner(in);
        int height;
        int width;
        String line;
        String[] lineVector;

        line = scanner.nextLine();

        lineVector = line.split(",");

        height = Integer.parseInt(lineVector[0]);
        width = Integer.parseInt(lineVector[1]);

        int[] heightAndWidth = new int[2];
        heightAndWidth[0] = height;
        heightAndWidth[1] = width;
        return heightAndWidth;
    }

    public int[] coordinatesAndIterationNumFromUser() {
        out.println("Enter 2 coordinates and the number of iterations");

        Scanner scanner = new Scanner(System.in);

        int x2;
        int y2;
        int timesToIterate;
        String line;
        String[] lineVector;

        line = scanner.nextLine(); //read

        //separate all values by comma
        lineVector = line.split(",");

        //parsing the values to Integer
        x2 = Integer.parseInt(lineVector[0]);
        y2 = Integer.parseInt(lineVector[1]);
        timesToIterate = Integer.parseInt(lineVector[2]);

        int[] coordinatesAndIterationNum = new int[3];
        coordinatesAndIterationNum[0] = x2;
        coordinatesAndIterationNum[1] = y2;
        coordinatesAndIterationNum[2] = timesToIterate;
        return coordinatesAndIterationNum;
    }
}
