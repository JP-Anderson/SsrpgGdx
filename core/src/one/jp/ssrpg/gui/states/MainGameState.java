package one.jp.ssrpg.gui.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

import arch.sessions.MapSession;
import arch.view.ConsoleIOHandler;
import one.jp.ssrpg.gui.utils.ShipScreenMenuBar;
import one.jp.ssrpg.gui.windows.ShipScreenWindow;
import ship.PlayerShip;

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

    public MainGameState(Stage stage) {
        super(stage);
    }

    public void createShipScreen() {
        windows.mapSession = new MapSession();
        windows.mapSession.start(generatePlayerShip());

        shipScreenWindow = new ShipScreenWindow(new Skin(Gdx.files.internal("uiskin.json")), windows);
        windows.addWindow(shipScreenWindow);

        menuBar = new ShipScreenMenuBar(shipScreenWindow);
        ArrayList<String> optionalMenuItems = new ArrayList<String>();
        optionalMenuItems.add("LAND");
        windows.addWindow(menuBar.generateMenuBar(optionalMenuItems));
    }

    private PlayerShip generatePlayerShip() {
        return new PlayerShip.PlayerShipBuilder(new ConsoleIOHandler(), "TestShip",12).build();
    }

    public ArrayList<TextButton> menuButtons() {
        return menuBar.getButtons();
    }

}
