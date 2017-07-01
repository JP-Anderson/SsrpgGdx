package one.jp.ssrpg.gui.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.utils.Predicate;

import java.util.ArrayList;
import java.util.HashSet;

import arch.interfaces.MapSessionInterface;
import map.gridsquares.GridSquare;
import one.jp.ssrpg.gui.windows.ShipScreenWindow;
import one.jp.ssrpg.gui.windows.map.ShipMapScreen;
import one.jp.ssrpg.gui.windows.trade.ShipTradeScreen;
import one.jp.ssrpg.gui.windows.SsrpgWindow;

/**
 * Created by Jp on 12/04/2017.
 */

public class WindowManager {

    private Stage stage;
    private HashSet<Actor> activeActors;
    private ShipScreenMenuBar menuBar;

    public WindowManager(Stage stage) {
        this.stage = stage;
        activeActors = new HashSet<>();
    }

    public void addWindow(Actor window) {
        activeActors.add(window);
        stage.addActor(window);
    }

    public boolean hasWindow(Actor window) {
        return stage.getActors().contains(window, true);
    }

    public void removeWindow(Actor window) {
        int indexOfTargetActor = stage.getActors().indexOf(window, true);
        if (indexOfTargetActor > -1) stage.getActors().removeIndex(indexOfTargetActor);
    }

    public int count() {
        return activeActors.size();
    }

    public void closeAllWindows() {
        ArrayList<Actor> windows = new ArrayList<>();
        for (Actor a : activeActors)
            if (a instanceof SsrpgWindow){
                windows.add(a);
                stage.getActors().removeIndex(stage.getActors().indexOf(a, true));
            }

        for (Actor w : windows) {
            activeActors.remove(w);
        }
    }

    public boolean closeWindow(Predicate<Actor> actorPredicate) {
        Actor desiredScreen = stage.getActors().select(actorPredicate).iterator().next();
        if (desiredScreen != null) {
            stage.getActors().removeIndex(stage.getActors().indexOf(desiredScreen, true));
            return true;
        }
        return false;
    }

    public Actor getWindow(Predicate<Actor> actorPredicate) {
        return stage.getActors().select(actorPredicate).iterator().next();
    }

    public void drawMenuBar(ShipScreenWindow topWindow, ArrayList<String> optionalMenuItems) {
        menuBar = new ShipScreenMenuBar(topWindow);
        this.addWindow(menuBar.generateMenuBar(optionalMenuItems));
    }

    public MapSessionInterface mapSession;
    ShipMapScreen mapScreen;

    public void drawMapWindow() {
        ArrayList<ArrayList<GridSquare>> mapSegment = mapSession.gridMap();
        mapScreen = new ShipMapScreen("Ship Map Screen", mapSession);
        mapScreen.drawMap(mapSegment);
        addWindow(mapScreen);
        mapScreen.setY(150);
    }

    public void render() {
        if (mapScreen != null)
            if (mapSession.changes()) mapScreen.drawMap(mapSession.gridMap());
    }

    public void moveMenuToTop() {
        for (Actor a : activeActors) {
            if (a instanceof HorizontalGroup) {
                a.toFront();
            }
        }
    }

    public void drawTradeWindow() {
        ShipTradeScreen trade = new ShipTradeScreen();
        addWindow(trade);
    }
    
}
