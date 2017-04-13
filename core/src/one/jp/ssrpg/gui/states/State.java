package one.jp.ssrpg.gui.states;

import com.badlogic.gdx.scenes.scene2d.Stage;

import one.jp.ssrpg.gui.utils.WindowManager;

/**
 * Created by Jp on 12/04/2017.
 */

public abstract class State {

    WindowManager windows;

    public State(Stage stage) {
        windows = new WindowManager(stage);
    }

}
