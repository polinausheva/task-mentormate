import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grid {

    int height;
    int width;
    private List<Cell> cells;

    public Grid(int height, int width){
        this.height=height;
        this.width=width;
    }

    public int[][] fill(int height, int width) {
        int[][] twoDm = new int[height][width];
        Scanner scan = new Scanner(in);
        int i;
        int j;

        for (i = 0; i < height; i++) {
            out.print("Enter numbers: ");
            String[] line = scan.nextLine().split("");
            for (j = 0; j < width; j++) {
                twoDm[i][j] = Integer.parseInt(line[j]);
            }
        }
        return twoDm;
    }

    public int[][] copyCell(int x1, int y1, int value, int[][] result) {

        result[x1][y1] = value;
        return result;
    }


    public int[][] copy2Darray(int[][] original, int[][] result) {
        if (original == null) {
            return null;
        }

        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
