import java.util.ArrayList;
import java.util.List;

/**
 * Describes a 9x9 sudoku board based on norvig.com/sudoku.html
 */
public class Board {

    private List<List<Cell>> cells;

    public Board () {
        cells = new ArrayList<>(9);
        for (int row = 0; row < 9; row++) {
            cells.add(new ArrayList<>(9));
            for (int col = 0; col < 9; col++) {
                cells.get(row).add(new Cell());
            }
        }
    }

    // Mutators
    public void setValue (int i, int j, byte n) {
        cells.get(i-1).get(j-1).setValue(n);
    }
    public void setHint (int i, int j, byte n) {
        cells.get(i-1).set(j-1, Cell.newHint(n));
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sb.append(cells.get(row).get(col).toString());
                if (col < 9-1) {
                    sb.append("|");
                } else {
                    sb.append("\n");
                }
            }
            if (row == 2 || row == 5) {
                sb.append("-----------------\n");
            }
        }
        return sb.toString();
    }

    public static void main (String[] args) {
        Board b = new Board();
        b.setHint(2,7,(byte)8);
        b.setValue(3,2,(byte)4);
        System.out.println(b);
    }

}
