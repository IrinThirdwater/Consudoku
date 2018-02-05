public class ConsoleBoard implements ConfigurationView {

    private Configuration config;
    private StringBuilder board;

    @Override public void onSetConfigView (Configuration config) {
        this.config = config;
        initBoard();
    }

    // Board Initialisation
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
                if (config.getConfig(i,j) > 0) {
                    placeNumber(i,j,(int)config.getConfig(i,j));
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
    private static final int[] HIGHLIGHT_BOX_INDEX = {
            0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12,
            38,             42,             46,             50,
            76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88,
            114,            118,            122,            126,
            152,153,154,155,156,157,158,159,160,161,162,163,164,
            190,            194,            198,            202,
            228,229,230,231,232,233,234,235,236,237,238,239,240
    };
    private static final String HIGHLIGHT_BOX =
            "▛▀▀▀▀▀▀▀▀▀▀▀▜▌││▐▌───┼───┼───▐▌││▐▌───┼───┼───▐▌││▐▙▄▄▄▄▄▄▄▄▄▄▄▟";
    /*
        "▛▀▀▀▀▀▀▀▀▀▀▀▜" +
        "▌   │   │   ▐" +
        "▌───┼───┼───▐" +
        "▌   │   │   ▐" +
        "▌───┼───┼───▐" +
        "▌   │   │   ▐" +
        "▙▄▄▄▄▄▄▄▄▄▄▄▟";
    */
    private static final int[] HIGHLIGHT_ROW_INDEX = {
            0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,35,36,38,42,46,50,54,58,62,66,70,74,
            76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,
            98,99,100,101,102,103,104,105,106,107,108,109,110,111,112
    };
    private static final String HIGHLIGHT_ROW =
            "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▜▌││┃││┃││▐▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟";
    /*
        "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▜" +
        "▌   │   │   ┃   │   │   ┃   │   │   ▐" +
        "▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟";
    */
    private static final int[] HIGHLIGHT_COLUMN_INDEX = {
            0,1,2,3,4,38,42,76,77,78,79,80,114,118,152,153,154,155,156,
            190,194,228,229,230,231,232,266,270,304,305,306,307,308,342,346,
            380,381,382,383,384,418,422,456,457,458,459,460,494,498,
            532,533,534,535,536,570,574,608,609,610,611,612,646,650,
            684,685,686,687,688
    };
    private static final String HIGHLIGHT_COLUMN =
            "▛▀▀▀▜▌▐▌───▐▌▐▌───▐▌▐▌━━━▐▌▐▌───▐▌▐▌───▐▌▐▌━━━▐▌▐▌───▐▌▐▌───▐▌▐▙▄▄▄▟";
    /*
        "▛▀▀▀▜" +
        "▌   ▐" +
        "▌───▐" +
        "▌   ▐" +
        "▌───▐" +
        "▌   ▐" +
        "▌━━━▐" +
        "▌   ▐" +
        "▌───▐" +
        "▌   ▐" +
        "▌───▐" +
        "▌   ▐" +
        "▌━━━▐" +
        "▌   ▐" +
        "▌───▐" +
        "▌   ▐" +
        "▌───▐" +
        "▌   ▐" +
        "▙▄▄▄▟";
    */
    private static final int[] HIGHLIGHT_CELL_INDEX = {
            0, 1, 2, 3, 4,
            38,         42,
            76,77,78,79,80
    };
    private static final String HIGHLIGHT_CELL =
            "▛▀▀▀▜▌▐▙▄▄▄▟";
    /*
        "▛▀▀▀▜" +
        "▌   ▐" +
        "▙▄▄▄▟";
    */
    /**
     * Highlight the b-th box on the board.
     *
     * @param b The box to highlight (0-8).
     */
    @Override public void selectBox         (int b) {
        int rowShift = 228*(b/3);
        int colShift = 12*(b%3);
        int boxIndexShift = rowShift + colShift;
        for (int i = 0; i < HIGHLIGHT_BOX_INDEX.length; i++) {
            String highlight = String.valueOf(HIGHLIGHT_BOX.charAt(i));
            int ii = HIGHLIGHT_BOX_INDEX[i] + boxIndexShift;
            board.replace(ii, ii + 1, highlight);
        }
    }
    /**
     * Return the b-th box on the board to the unhighlighted version.
     *
     * @param b The box to unhighlight (0-8).
     */
    @Override public void deselectBox       (int b) {
        int rowShift = 228*(b/3);
        int colShift = 12*(b%3);
        int boxIndexShift = rowShift + colShift;
        for (int i = 0; i < HIGHLIGHT_BOX_INDEX.length; i++) {
            int ii = HIGHLIGHT_BOX_INDEX[i] + boxIndexShift;
            String normal = String.valueOf(EMPTY_BOARD.charAt(ii));
            board.replace(ii, ii + 1, normal);
        }
    }
    /**
     * Highlight the i-th row on the board.
     *
     * @param i The row to highlight (0-8).
     */
    @Override public void selectRow         (int i) {
        int rowShift = 76*i;
        for (int n = 0; n < HIGHLIGHT_ROW_INDEX.length; n++) {
            String highlight = String.valueOf(HIGHLIGHT_ROW.charAt(n));
            int nn = HIGHLIGHT_ROW_INDEX[n] + rowShift;
            board.replace(nn, nn + 1, highlight);
        }
    }
    /**
     * Return the i-th row on the board to the unhighlighted version.
     *
     * @param i The row to unhighlight (0-8).
     */
    @Override public void deselectRow       (int i) {
        int rowShift = 76*i;
        for (int n = 0; n < HIGHLIGHT_ROW_INDEX.length; n++) {
            int nn = HIGHLIGHT_ROW_INDEX[n] + rowShift;
            String normal = String.valueOf(EMPTY_BOARD.charAt(nn));
            board.replace(nn, nn + 1, normal);
        }
    }
    /**
     * Highlight the j-th column on the board.
     *
     * @param j The column to highlight (0-8).
     */
    @Override public void selectColumn      (int j) {
        int colShift = 4*j;
        for (int n = 0; n < HIGHLIGHT_COLUMN_INDEX.length; n++) {
            String highlight = String.valueOf(HIGHLIGHT_COLUMN.charAt(n));
            int nn = HIGHLIGHT_COLUMN_INDEX[n] + colShift;
            board.replace(nn, nn + 1, highlight);
        }
    }
    /**
     * Return the j-th column on the board to the unhighlighted version.
     *
     * @param j The column to unhighlight (0-8).
     */
    @Override public void deselectColumn    (int j) {
        int colShift = 4*j;
        for (int n = 0; n < HIGHLIGHT_COLUMN_INDEX.length; n++) {
            int nn = HIGHLIGHT_COLUMN_INDEX[n] + colShift;
            String normal = String.valueOf(EMPTY_BOARD.charAt(nn));
            board.replace(nn, nn + 1, normal);
        }
    }
    /**
     * Highlight the cell on the i-th row and j-th column on the board.
     *
     * @param i The row number (0-8) of the cell to highlight.
     * @param j The column number (0-8) of the cell to highlight.
     */
    @Override public void selectCell        (int i, int j) {
        int rowShift = 76*i;
        int colShift = 4*j;
        int cellShift = rowShift + colShift;
        for (int n = 0; n < HIGHLIGHT_CELL_INDEX.length; n++) {
            String highlight = String.valueOf(HIGHLIGHT_CELL.charAt(n));
            int nn = HIGHLIGHT_CELL_INDEX[n] + cellShift;
            board.replace(nn, nn + 1, highlight);
        }
    }
    /**
     * Return the cell on the i-th row and the j-th column on the board to
     * the unhighlighted version.
     *
     * @param i The row number (0-8) of the cell to unhighlight.
     * @param j The column number (0-8) of the cell to unhighlight.
     */
    @Override public void deselectCell      (int i, int j) {
        int rowShift = 76*i;
        int colShift = 4*j;
        int cellShift = rowShift + colShift;
        for (int n = 0; n < HIGHLIGHT_CELL_INDEX.length; n++) {
            int nn = HIGHLIGHT_CELL_INDEX[n] + cellShift;
            String normal = String.valueOf(EMPTY_BOARD.charAt(nn));
            board.replace(nn, nn + 1, normal);
        }
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
