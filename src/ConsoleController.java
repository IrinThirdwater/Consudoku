import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleController {

    public enum GameMode {
        PLAYING
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

    public ConsoleController (Configuration model, ConfigurationView view) throws Exception {
        this.model = model;
        this.view = view;
        disableJNativeHookConsoleOutput();
        initListener();
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
        // System.out.println(NativeKeyEvent.getKeyText(event.getKeyCode()));
        if (currentGameMode() == GameMode.PLAYING) {
            switch (currentInputMode()) {
                case CHOOSE_NUMBER: {
                    // set the number stuff
                    setInputMode(InputMode.CHOOSE_BOX);
                    break;
                }
                case CHOOSE_BOX: {
                    // highlight the chosen box
                    setInputMode(InputMode.CHOOSE_SUB_BOX);
                    break;
                }
                case CHOOSE_SUB_BOX: {
                    // highlight the cell
                    setInputMode(InputMode.CHOOSE_NUMBER);
                    break;
                }
            }
        }
    }

    public GameMode currentGameMode () {
        return gameMode;
    }
    public InputMode currentInputMode () {
        return inputMode;
    }
    public void setGameMode (GameMode gameMode) {
        this.gameMode = gameMode;
    }
    public void setInputMode (InputMode inputMode) {
        this.inputMode = inputMode;
    }

}
