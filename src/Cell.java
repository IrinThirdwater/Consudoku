import java.util.HashSet;
import java.util.Set;

/**
 * Represent cells on a sudoku board.
 */
public class Cell {

    /**
     * A hint cell has a fixed value in the interval [1,9].
     * A value cell has a value in the interval [1,9] and
     * its value can change.
     * A note cell is an element of the power set of the
     * interval [1,9] excluding the empty set. The choice
     * of element can change.
     * A blank cell has no values.
     */
    public enum Type { HINT, VALUE, NOTE, BLANK }

    private Type      type;
    private byte      value; // With 0 representing blanks
    private Set<Byte> notes;

    // Constructors
    /**
     * Creates a blank cell.
     * Blank cells can later hold values or notes,
     * but not hints.
     * To create hints, use the static hint cell constructor.
     */
    public Cell () {
        this(Type.BLANK, (byte)0);
    }
    /**
     * Static constructor for hint cells.
     * Hint cells cannot be turned into cells of other types.
     * Use the default constructor to create blank cells,
     * or the corresponding static constructors for other
     * cell types.
     *
     * @throws IllegalAccessException if n is not in the interval [1,9].
     * @param n byte in the interval [1,9].
     * @return hint cell with the given number.
     */
    public static Cell newHint (byte n) {
        if (n < 1 || n > 9) {
            throw new IllegalArgumentException(
                "Hint value must be in the interval [1,9]."
            );
        }
        return new Cell(Type.HINT, n);
    }
    private Cell (Type type, byte n) {
        this.type  = type;
        this.value = n;
        switch (type) {
            case HINT: {
                break;
            }
            default: {
                notes = new HashSet<>();
            }
        }
    }

    // Mutators
    /**
     * Set the cell to hold the specified value. Works on value, note,
     * and blank cells.
     * This method removes all the previous notes on the cell (if any).
     *
     * @throws IllegalStateException if the cell is a hint cell.
     * @throws IllegalArgumentException if n is not in the interval [1,9].
     * @param n byte in the interval [1,9].
     */
    public void setValue (byte n) {
        if (type == Type.HINT) {
            throw new IllegalStateException(
                "Cannot change a hint cell value."
            );
        }
        if (n < 1 || n > 9) {
            throw new IllegalArgumentException(
                "Value must be in the interval [1,9]."
            );
        }
        type  = Type.VALUE;
        value = n;
        notes.clear();
    }
    /**
     * Remove the cell value. Works on value, note, and blank cells.
     *
     * @throws IllegalStateException if the cell is a hint cell.
     */
    public void setBlank () {
        if (type == Type.HINT) {
            throw new IllegalStateException(
                "Cannot remove a hint cell value."
            );
        }
        type  = Type.BLANK;
        value = (byte)0;
        notes.clear();
    }
    /**
     * TODO
     *
     * @param n
     */
    public void addNote (byte n) {

    }
    /**
     * TODO
     *
     * @param n
     */
    public void removeNote (byte n) {

    }
    /**
     * Remove the notes and change the cell type to blank.
     * Works on note cells only.
     *
     * @throws IllegalStateException if the cell is not a note.
     */
    public void clearNotes () {
        if (type != Type.NOTE) {
            throw new IllegalStateException(
                "Cannot clear notes on non-note cells."
            );
        }
        notes.clear();
        type = Type.BLANK;
    }

    // Accessors
    public Type      getType  () {
        return type;
    }
    public byte      getValue () {
        return value;
    }
    public Set<Byte> getNotes () {
        return notes;
    }

}
