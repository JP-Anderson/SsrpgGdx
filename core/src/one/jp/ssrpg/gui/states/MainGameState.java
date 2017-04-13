package one.jp.ssrpg.gui.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.ArrayList;

import arch.interfaces.MapSessionInterface;
import arch.sessions.MapSession;
import arch.view.ConsoleIOHandler;
import map.gridsquares.GridSquare;
import one.jp.ssrpg.gui.utils.ShipScreenMenuBar;
import one.jp.ssrpg.gui.windows.ShipMapScreen;
import one.jp.ssrpg.gui.windows.ShipScreenWindow;
import ship.PlayerShip;

/**
 * Created by Jp on 12/04/2017.
 */

public class MainGameState extends State {

    ShipScreenWindow shipScreenWindow;
    ShipScreenMenuBar menuBar;
    MapSessionInterface sesh;
    ShipMapScreen mapScreen;

    public void render() {
        if (sesh.changes()) mapScreen.drawMap(sesh.gridMap());
        //stage.draw();
    }

    public void dispose() {
        //stage.dispose();
    }

    public MainGameState(Stage stage) {
        super(stage);
    }

    public void createShipScreen() {
        sesh = new MapSession();
        sesh.start(generatePlayerShip());
        ArrayList<ArrayList<GridSquare>> mapSegment = sesh.gridMap();

        shipScreenWindow = new ShipScreenWindow(new Skin(Gdx.files.internal("uiskin.json")), stage);
        //stage.addActor(shipScreenWindow);
        windows.addWindow(shipScreenWindow);

        menuBar = new ShipScreenMenuBar(shipScreenWindow);
        mapScreen = new ShipMapScreen("Ship Map Screen", sesh);
        mapScreen.drawMap(mapSegment);
        //stage.addActor(mapScreen);
        windows.addWindow(mapScreen);

        ArrayList<String> menuItems = new ArrayList<String>();
        menuItems.add("MAP");
        menuItems.add("SHIP");
        menuItems.add("CREW");
        menuItems.add("CARGO");
        menuItems.add("MODULES");
        menuItems.add("LAND");
        //stage.addActor(menuBar.generateMenuBar(menuItems));
        windows.addWindow(menuBar.generateMenuBar(menuItems));
        mapScreen.setY(150);
    }

    private PlayerShip generatePlayerShip() {
        return new PlayerShip.PlayerShipBuilder(new ConsoleIOHandler(), "TestShip",12).build();
    }

    public ArrayList<TextButton> menuButtons() {
        return menuBar.getButtons();
    }

}
