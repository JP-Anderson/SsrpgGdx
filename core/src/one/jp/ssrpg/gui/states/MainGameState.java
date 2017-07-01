package one.jp.ssrpg.gui.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

import arch.sessions.GameStateManager;
import arch.sessions.MapSession;
import arch.view.ConsoleIOHandler;
import one.jp.ssrpg.gui.utils.ShipScreenMenuBar;
import one.jp.ssrpg.gui.windows.ShipScreenWindow;
import ship.PlayerShip;
import util.dataload.csv.loaders.MapLoader;

/**
 * Created by Jp on 12/04/2017.
 */

public class MainGameState extends State {

    GameStateManager gameStateManager;
    ShipScreenWindow shipScreenWindow;
    ShipScreenMenuBar menuBar;

    public void render() {
        windows.render();
    }

    public void dispose() {
        //stage.dispose();
    }

    public MainGameState(Stage stage) {
        super(stage);
    }

    public void createShipScreen() {
        gameStateManager = new GameStateManager(generatePlayerShip(), MapLoader.loadMap());
        windows.mapSession = gameStateManager.spawnMapSession();
        //windows.mapSession.start(generatePlayerShip(), gameStateManager.getMapState());

        shipScreenWindow = new ShipScreenWindow(new Skin(Gdx.files.internal("uiskin.json")), windows);
        windows.addWindow(shipScreenWindow);

        windows.drawMenuBar(shipScreenWindow, null);
    }

    // This mocks a player ship for now, need to replace this with a GUI wizard for creating a ship
    private PlayerShip generatePlayerShip() {
        return new PlayerShip.PlayerShipBuilder(new ConsoleIOHandler(), "TestShip",12).build();
    }

    public ArrayList<TextButton> menuButtons() {
        return menuBar.getButtons();
    }

}
