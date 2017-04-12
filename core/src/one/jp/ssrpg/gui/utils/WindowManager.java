package one.jp.ssrpg.gui.utils;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import java.util.HashSet;

import one.jp.ssrpg.gui.windows.SsrpgWindow;

/**
 * Created by Jp on 12/04/2017.
 */

public class WindowManager {

    private Stage stage;
    private HashSet<SsrpgWindow> activeWindows;

    public WindowManager(Stage stage) {
        this.stage = stage;
        activeWindows = new HashSet<>();
    }

    public void addWindow(SsrpgWindow window) {
        activeWindows.add(window);
        stage.addActor(window);
    }

    public void removeWindow(SsrpgWindow window) {
        int indexOfTargetWindow = stage.getActors().indexOf(window, true);
        if (indexOfTargetWindow > -1) stage.getActors().removeIndex(indexOfTargetWindow);
    }

    public int count() {
        return activeWindows.size();
    }

}
