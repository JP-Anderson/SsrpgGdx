package one.jp.ssrpg.gui.states;

import com.badlogic.gdx.scenes.scene2d.Stage;

import arch.sessions.GameStateManager;
import one.jp.ssrpg.gui.utils.WindowManager;

/**
 * Created by Jp on 12/04/2017.
 */

public abstract class State {

    GameStateManager gameStateManager;
    WindowManager windows;
    Stage stage;

    public State(Stage stage, GameStateManager gsm) {
        this.stage = stage;
        this.gameStateManager = gsm;
        this.windows = new WindowManager(stage, gameStateManager);
    }

}
