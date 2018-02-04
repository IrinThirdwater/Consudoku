/**
 * Describes a 9x9 Sudoku board.
 */
public class Configuration {

    private byte[][] config;
    private StringBuilder board;

    private static final String EMPTY_BOARD =
        "┏━━━┯━━━┯━━━┳━━━┯━━━┯━━━┳━━━┯━━━┯━━━┓\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┠───┼───┼───╂───┼───┼───╂───┼───┼───┨\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┠───┼───┼───╂───┼───┼───╂───┼───┼───┨\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┣━━━┿━━━┿━━━╋━━━┿━━━┿━━━╋━━━┿━━━┿━━━┫\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┠───┼───┼───╂───┼───┼───╂───┼───┼───┨\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┠───┼───┼───╂───┼───┼───╂───┼───┼───┨\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┣━━━┿━━━┿━━━╋━━━┿━━━┿━━━╋━━━┿━━━┿━━━┫\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┠───┼───┼───╂───┼───┼───╂───┼───┼───┨\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┠───┼───┼───╂───┼───┼───╂───┼───┼───┨\n" +
        "┃   │   │   ┃   │   │   ┃   │   │   ┃\n" +
        "┗━━━┷━━━┷━━━┻━━━┷━━━┷━━━┻━━━┷━━━┷━━━┛";
    private static final String HIGHLIGHT_BOX =
        "▛▀▀▀▀▀▀▀▀▀▀▀▜" +
        "▌   │   │   ▐" +
        "▌───┼───┼───▐" +
        "▌   │   │   ▐" +
        "▌───┼───┼───▐" +
        "▌   │   │   ▐" +
        "▙▄▄▄▄▄▄▄▄▄▄▄▟";
    private static final String HIGHLIGHT_ROW =
        "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▜\n" +
        "▌   │   │   ┃   │   │   ┃   │   │   ▐\n" +
        "▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟";
    private static final String HIGHLIGHT_COLUMN =
        "▛▀▀▀▜\n" +
        "▌   ▐\n" +
        "▌───▐\n" +
        "▌   ▐\n" +
        "▌───▐\n" +
        "▌   ▐\n" +
        "▌━━━▐\n" +
        "▌   ▐\n" +
        "▌───▐\n" +
        "▌   ▐\n" +
        "▌───▐\n" +
        "▌   ▐\n" +
        "▌━━━▐\n" +
        "▌   ▐\n" +
        "▌───▐\n" +
        "▌   ▐\n" +
        "▌───▐\n" +
        "▌   ▐\n" +
        "▙▄▄▄▟";
    private static final String HIGHLIGHT_CELL =
        "▛▀▀▀▜" +
        "▌   ▐" +
        "▙▄▄▄▟";

    /**
     * Sudoku board configuration constructor.
     *
     * @param config A 2-dimensional byte array of size 9x9.
     */
    public Configuration (byte[][] config) {
        this.config = config;
        initBoard();
    }
    /**
     * Sudoku board configuration constructor.
     *
     * @param configString A string of length 81.
     */
    public Configuration (String configString) {
        config = new byte[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                byte b = (byte) (configString.charAt(9*row + col) - 48);
                if (1 <= b && b <= 9) {
                    config[row][col] = b;
                } else {
                    config[row][col] = 0;
                }
            }
        }
        initBoard();
    }

    // Board Initialisation
    /**
     * Initialise an internal human-friendly representation of the board.
     */
    private void initBoard () {
        board = new StringBuilder();
        board.append(EMPTY_BOARD);
        placeHintNumbers();
    }
    /**
     * Place the hint numbers from config to the board.
     */
    private void placeHintNumbers () {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (config[i][j] > 0) {
                    placeNumber(i,j,(int)config[i][j]);
                }
            }
        }
    }
    /**
     * Place the number n on row i, column j to the board.
     *
     * @param i Row number (0-8).
     * @param j Column number (0-8).
     * @param n Number to put in (1-9).
     */
    private void placeNumber (int i, int j, int n) {
        int index = coordToBoardIndex(i,j);
        board.replace(index, index + 1, ""+n);
    }
    /**
     * Convert 0-based board indices to index of the corresponding position
     * in the board StringBuilder.
     *
     * @param i Row number (0-8).
     * @param j Column number (0-8).
     * @return Board StringBuilder index.
     */
    private int coordToBoardIndex (int i, int j) {
        return 38*(2*i + 1) + (4*j + 2);
    }

    // Board Highlighting
    /**
     * Highlight the b-th box on the board.
     *
     * @param b The box to highlight (0-8).
     */
    private void highlightBox (int b) {

    }
    /**
     * Return the b-th box on the board to the unhighlighted version.
     *
     * @param b The box to unhighlight (0-8).
     */
    private void unhighlightBox (int b) {

    }
    /**
     * Highlight the i-th row on the board.
     *
     * @param i The row to highlight (0-8).
     */
    private void highlightRow (int i) {

    }
    /**
     * Return the i-th row on the board to the unhighlighted version.
     *
     * @param i The row to unhighlight (0-8).
     */
    private void unhighlightRow (int i) {

    }
    /**
     * Highlight the j-th column on the board.
     *
     * @param j The column to highlight (0-8).
     */
    private void highlightColumn (int j) {

    }
    /**
     * Return the j-th column on the board to the unhighlighted version.
     *
     * @param j The column to unhighlight (0-8).
     */
    private void unhighlightColumn (int j) {

    }
    /**
     * Highlight the cell on the i-th row and j-th column on the board.
     *
     * @param i The row number (0-8) of the cell to highlight.
     * @param j The column number (0-8) of the cell to highlight.
     */
    private void highlightCell (int i, int j) {

    }
    /**
     * Return the cell on the i-th row and the j-th column on the board to
     * the unhighlighted version.
     *
     * @param i The row number (0-8) of the cell to unhighlight.
     * @param j The column number (0-8) of the cell to unhighlight.
     */
    private void unhighlightCell (int i, int j) {

    }

    /**
     * Check if the configuration is solved.
     *
     * @return Boolean stating whether the configuration is solved.
     */
    public boolean isSolved () {
        // For each of the 9 rows/columns/boxes
        for (int i = 0; i < 9; i++) {
            // Row
            byte[] count = new byte[9];
            for (int rowCol = 0; rowCol < 9; rowCol++) {
                byte val = config[i][rowCol];
                if (val == 0 || ++count[val-1] > 1) return false;
            }
            // Column
            count = new byte[9];
            for (int colRow = 0; colRow < 9; colRow++) {
                byte val = config[colRow][i];
                if (val == 0 || ++count[val-1] > 1) return false;
            }
            // Box
            count = new byte[9];
            for (int boxIndex = 0; boxIndex < 9; boxIndex++) {
                byte val = config[3*(i/3) + (boxIndex/3)][3*(i%3) + (boxIndex%3)];
                if (val == 0 || ++count[val-1] > 1) return false;
            }
        }
        return true;
    }

    /**
     * Prints the board configuration in a human-friendly format.
     *
     * @return String representing the board.
     */
    @Override public String toString () {
        return board.toString();
    }

}
