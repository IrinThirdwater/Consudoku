package console;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleController {

    public enum GameMode {
        PLAYING,
        IDLE
    }
    public enum InputMode {
        CHOOSE_BOX,
        CHOOSE_SUB_BOX,
        CHOOSE_NUMBER
    }

    private Configuration model;
    private ConfigurationView view;

    private GameMode gameMode;
    private InputMode inputMode;
    private Map<String,Integer> controlScheme;

    private int chosenBox;
    private int chosenCell;

    private final String operatingSystem = System.getProperty("os.name");

    public ConsoleController (Configuration model, ConfigurationView view) throws Exception {
        this.model = model;
        this.view = view;
        initControlScheme();
        disableJNativeHookConsoleOutput();
        initListener();
        displayBoard();
    }

    private void initControlScheme () {
        controlScheme = new HashMap<>();
        // QWE | ASD | ZXC scheme
        String[] schemeQWE = {"Space","Q","W","E","A","S","D","Z","X","C"};
        for (int i = 0; i <= 9; i++) {
            controlScheme.put(schemeQWE[i], i);
        }
        // Reverse numpad scheme (phone numpad)
        String[] schemeReverseNumpad = {"0","7","8","9","4","5","6","1","2","3"};
        for (int i = 0; i <= 9; i++) {
            controlScheme.put(schemeReverseNumpad[i], i);
        }
    }

    private void disableJNativeHookConsoleOutput () {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);
    }
    private void initListener () throws NativeHookException {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override public void nativeKeyPressed (NativeKeyEvent event) {}
            @Override public void nativeKeyTyped (NativeKeyEvent event) {}
            @Override public void nativeKeyReleased (NativeKeyEvent event) {
                onKeyReleased(event);
            }
        });
    }

    private void onKeyReleased (NativeKeyEvent event) {
        System.out.print("\b \b");
        if (currentGameMode() == GameMode.PLAYING) {
            String input = NativeKeyEvent.getKeyText(event.getKeyCode());
            if (isValidInput(input)) {
                int num = getControlScheme().get(input);
                switch (currentInputMode()) {
                    case CHOOSE_NUMBER: {
                        if (num == 0) {
                            model.deleteNumber(getChosenI(), getChosenJ());
                        } else {
                            model.placeNumber(getChosenI(), getChosenJ(), num);
                        }
                        view.deselectCell(getChosenI(), getChosenJ());
                        setInputMode(InputMode.CHOOSE_BOX);
                        displayBoard();
                        checkBoard();
                        break;
                    }
                    case CHOOSE_BOX: {
                        if (num > 0) {
                            chosenBox = num;
                            view.selectBox(num - 1);
                            setInputMode(InputMode.CHOOSE_SUB_BOX);
                            displayBoard();
                        }
                        break;

                    }
                    case CHOOSE_SUB_BOX: {
                        if (num > 0) {
                            chosenCell = num;
                            if (!model.isHint(getChosenI(), getChosenJ())) {
                                view.deselectBox(chosenBox - 1);
                                view.selectCell(getChosenI(), getChosenJ());
                                setInputMode(InputMode.CHOOSE_NUMBER);
                                displayBoard();
                            }
                        }
                        break;
                    }
                }
            }
        }
    }
    private boolean isValidInput (String input) {
        return getControlScheme().containsKey(input);
    }
    private void checkBoard () {
        if (model.isFilled()) {
            if (model.isSolved()) {
                System.out.println("Congratulations, you won!");
                setGameMode(GameMode.IDLE);
            } else {
                System.out.println("Oops! You have some error(s)!");
            }
        }
    }

    private void displayBoard () {
        try {
            clearConsole();
            System.out.println(view);
        } catch (Exception e) {
            System.err.println("Error displaying the board!");
            e.printStackTrace();
        }
    }
    private void clearConsole () throws Exception {
        /*if (operatingSystem .contains("Windows")) {
            Runtime.getRuntime().exec("cls");
        } else {
            Runtime.getRuntime().exec("clear");
        }*/
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public GameMode currentGameMode () {
        return gameMode;
    }
    public InputMode currentInputMode () {
        return inputMode;
    }
    public Map<String,Integer> getControlScheme () {
        return controlScheme;
    }
    private int getChosenI () {
        int boxRowShift = 3*((chosenBox-1)/3);
        int cellRowShift = (chosenCell-1)/3;
        return boxRowShift + cellRowShift;
    }
    private int getChosenJ () {
        int boxColShift = 3*((chosenBox-1)%3);
        int cellColShift = (chosenCell-1)%3;
        return boxColShift + cellColShift;
    }
    public void setGameMode (GameMode gameMode) {
        this.gameMode = gameMode;
    }
    public void setInputMode (InputMode inputMode) {
        this.inputMode = inputMode;
    }

}
