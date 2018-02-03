/**
 * Describes a 9x9 Sudoku board.
 */
public class Configuration {

    private byte[][] config;
    private StringBuilder board;

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

    /**
     * Initialise an internal human-friendly representation of the board.
     */
    private void initBoard () {
        /*
            Format example:
            ┏━━━┯━━━┯━━━┳━━━┯━━━┯━━━┳━━━┯━━━┯━━━┓
            ┃ 𝟣 │   │   ┃   │   │ 𝟨 ┃   │   │ 𝟫 ┃
            ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
            ┃   │   │   ┃ 𝟩 │   │ 𝟫 ┃   │   │   ┃
            ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
            ┃   │ 𝟪 │   ┃   │ 𝟤 │   ┃   │   │   ┃
            ┣━━━┿━━━┿━━━╋━━━┿━━━┿━━━╋━━━┿━━━┿━━━┫
            ┃   │   │   ┃   │   │   ┃ 𝟨 │   │   ┃
            ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
            ┃ 𝟥 │ 𝟦 │   ┃ 𝟨 │   │ 𝟪 ┃   │   │   ┃
            ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
            ┃ 𝟨 │   │   ┃   │   │ 𝟤 ┃   │   │ 𝟧 ┃
            ┣━━━┿━━━┿━━━╋━━━┿━━━┿━━━╋━━━┿━━━┿━━━┫
            ┃   │   │ 𝟣 ┃   │   │   ┃   │   │ 𝟩 ┃
            ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
            ┃   │   │   ┃ 𝟪 │   │   ┃   │ 𝟥 │   ┃
            ┠───┼───┼───╂───┼───┼───╂───┼───┼───┨
            ┃   │   │ 𝟦 ┃   │   │ 𝟩 ┃   │   │   ┃
            ┗━━━┷━━━┷━━━┻━━━┷━━━┷━━━┻━━━┷━━━┷━━━┛
         */
        board = new StringBuilder();

        for (int rowBorder = 0; rowBorder < 19; rowBorder++) {
            for (int colBorder = 0; colBorder < 19; colBorder++) {
                if (rowBorder%2 == 0) {
                // Horizontal lines
                    if (rowBorder == 0) { // Top row
                        if (colBorder == 0)         board.append('┏');
                        else if (colBorder == 18)   board.append('┓');
                        else if (colBorder%6 == 0)  board.append('┳');
                        else if (colBorder%2 == 0)  board.append('┯');
                        else                        board.append("━━━");
                    } else if (rowBorder == 18) { // Bottom row
                        if (colBorder == 0)         board.append('┗');
                        else if (colBorder == 18)   board.append('┛');
                        else if (colBorder%6 == 0)  board.append('┻');
                        else if (colBorder%2 == 0)  board.append('┷');
                        else                        board.append("━━━");
                    } else if (rowBorder%6 == 0) { // 3x3 row
                        if (colBorder == 0)         board.append('┣');
                        else if (colBorder == 18)   board.append('┫');
                        else if (colBorder%6 == 0)  board.append('╋');
                        else if (colBorder%2 == 0)  board.append('┿');
                        else                        board.append("━━━");
                    } else { // Normal row
                        if (colBorder == 0)         board.append('┠');
                        else if (colBorder == 18)   board.append('┨');
                        else if (colBorder%6 == 0)  board.append('╂');
                        else if (colBorder%2 == 0)  board.append('┼');
                        else                        board.append("───");
                    }
                } else {
                // Numbers row
                    if (colBorder%6 == 0) { // 3x3 column
                        board.append('┃');
                    } else if (colBorder%2 == 0) { // Normal column
                        board.append('│');
                    } else { // Hint number or empty
                        board.append(' ');
                        byte hintNumber = config[rowBorder/2][colBorder/2];
                        if (hintNumber > 0) {
                            board.append(byteToBoldString(hintNumber));
                        } else {
                            board.append(' ');
                        }
                        board.append(' ');
                    }
                }
            }
            if (rowBorder < 18) board.append('\n');
        }
    }

    /**
     * Takes a byte in the interval [1,9] and converts it into a string of
     * equivalent value but in bold, e.g. 8 -> 𝟴.
     * 
     * Used to distinguish hint numbers from user-filled numbers on the console.
     *
     * @param x Byte in the interval [1,9].
     * @return String that looks like a bold version of x.
     */
    private String byteToBoldString (byte x) {
        switch (x) {
            case 1: return "\uD835\uDFE3";
            case 2: return "\uD835\uDFE4";
            case 3: return "\uD835\uDFE5";
            case 4: return "\uD835\uDFE6";
            case 5: return "\uD835\uDFE7";
            case 6: return "\uD835\uDFE8";
            case 7: return "\uD835\uDFE9";
            case 8: return "\uD835\uDFEA";
            case 9: return "\uD835\uDFEB";
        }
        return "";
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
