/**
 * Describes a 9x9 Sudoku board.
 */
public class Configuration {

    private byte[][] config;
    private ConfigurationView view;

    /**
     * Sudoku board configuration constructor.
     *
     * @param config A 2-dimensional byte array of size 9x9.
     */
    Configuration (byte[][] config) {
        this.config = config;
    }
    /**
     * Sudoku board configuration constructor.
     *
     * @param configString A string of length 81.
     */
    Configuration (String configString) {
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
    }

    /**
     * Get the board configuration data on row i, column j.
     *
     * @return The number (0-9) on row i, column j.
     */
    public byte getConfig (int i, int j) {
        return config[i][j];
    }
    /**
     * Set the view of this configuration.
     *
     * @param view A ConfigurationView for this configuration.
     */
    public void setConfigView (ConfigurationView view) {
        this.view = view;
        view.onSetConfigView(this);
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
     * Returns a string representation of the configuration based on the
     * current view.
     *
     * @return String representation of the configuration based on the current view.
     */
    public String toString () {
        return view.toString();
    }

}
