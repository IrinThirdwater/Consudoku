/**
 * Describes a 9x9 sudoku board based on norvig.com/sudoku.html
 */
public class Board {

    /*
    We label the cells on a sudoku board as follows:

        1   2   3   4   5   6   7   8   9
      ┏━━━┯━━━┯━━━┳━━━┯━━━┯━━━┳━━━┯━━━┯━━━┓
    1 ┃1,1│   │   ┃   │   │   ┃   │   │   ┃
      ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
    2 ┃2,1│   │   ┃   │   │   ┃   │   │   ┃
      ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
    3 ┃   │   │   ┃   │   │   ┃   │   │   ┃
      ┣━━━┿━━━┿━━━╋━━━┿━━━┿━━━╋━━━┿━━━┿━━━┫
    4 ┃   │   │   ┃   │   │   ┃   │   │   ┃
      ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
    5 ┃   │   │   ┃   │   │   ┃   │   │   ┃
      ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
    6 ┃   │   │   ┃   │   │   ┃   │   │   ┃
      ┣━━━┿━━━┿━━━╋━━━┿━━━┿━━━╋━━━┿━━━┿━━━┫
    7 ┃   │   │   ┃7,4│   │   ┃   │   │   ┃
      ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
    8 ┃   │   │   ┃   │   │   ┃   │   │   ┃
      ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
    9 ┃   │   │   ┃   │   │   ┃   │   │9,9┃
      ┗━━━┷━━━┷━━━┻━━━┷━━━┷━━━┻━━━┷━━━┷━━━┛

    Where cell i,j is on row i, column j.

    Call a set of cells in the same row, column, or the big
    3x3 group of cells a unit. Call cells sharing a unit peers.
     */

    private byte[][] cells;

    private byte[] row (int i) {
        byte[] r = new byte[9];
        for (int col = 0; col < 9; col++) {
            r[col] = cells[i-1][col];
        }
        return r;
    }
    private byte[] col (int j) {
        byte[] c = new byte[9];
        for (int row = 0; row < 9; row++) {
            c[row] = cells[row][j-1];
        }
        return c;
    }
    private byte[] box (int k) {
        byte[] b = new byte[9];
        int baseRow = 3*((k-1)/3);
        int baseCol = 3*((k-1)%3);
        for (int cell = 0; cell < 9; cell++) {
            b[cell] = cells[baseRow + (cell/3)][baseCol + (cell%3)];
        }
        return b;
    }
    private byte[] peer (int i, int j) {
        byte[] p = new byte[];
        
        return p;
    }

}
