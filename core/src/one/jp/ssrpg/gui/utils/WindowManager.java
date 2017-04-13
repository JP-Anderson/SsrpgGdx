package one.jp.ssrpg.gui.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.utils.Predicate;
import com.sun.java.swing.plaf.windows.resources.windows;

import java.util.ArrayList;
import java.util.HashSet;

import arch.interfaces.MapSessionInterface;
import arch.sessions.MapSession;
import map.gridsquares.GridSquare;
import one.jp.ssrpg.gui.windows.ShipMapScreen;

/**
 * Created by Jp on 12/04/2017.
 */

public class WindowManager {

    private Stage stage;
    private HashSet<Actor> activeActors;

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

    private HorizontalGroup menu;

    public void moveMenuToTop() {
        if (menu == null) searchWindowsForMenu();
        else menu.toFront();
    }

    private void searchWindowsForMenu() {
        for (Actor a : activeActors) {
            if (a instanceof HorizontalGroup) {
                menu = (HorizontalGroup) a;
                a.toFront();
            }
        }
    }
}
