package one.jp.ssrpg.gui.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

import arch.sessions.GameStateManager;
import one.jp.ssrpg.gui.Assets;
import one.jp.ssrpg.gui.utils.ShipScreenMenuBar;
import one.jp.ssrpg.gui.windows.ShipScreenWindow;

/**
 * Created by Jp on 12/04/2017.
 */

public class MainGameState extends State {

    ShipScreenWindow shipScreenWindow;
    ShipScreenMenuBar menuBar;

    public void render() {
        windows.render();
    }

    public void dispose() {
        //stage.dispose();
    }

    public MainGameState(Stage stage, GameStateManager gsm) {
        super(stage, gsm);
    }

    public void createShipScreen() {
        windows.mapSession = gameStateManager.spawnMapSession();
        shipScreenWindow = new ShipScreenWindow(Assets.skin, windows);
        windows.addWindow(shipScreenWindow);
        windows.drawMenuBar("");
    }

    public ArrayList<TextButton> menuButtons() {
        return menuBar.getButtons();
    }

}
