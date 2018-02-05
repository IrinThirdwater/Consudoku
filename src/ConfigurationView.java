public interface ConfigurationView {

    void onSetConfigView (Configuration config);

    // Visual cues for selection/deselection
    void selectBox      (int b);
    void deselectBox    (int b);
    void selectRow      (int i);
    void deselectRow    (int i);
    void selectColumn   (int j);
    void deselectColumn (int j);
    void selectCell     (int i, int j);
    void deselectCell   (int i, int j);

    String toString ();

}
