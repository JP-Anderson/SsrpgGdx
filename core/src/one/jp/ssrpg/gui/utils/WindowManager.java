package one.jp.ssrpg.gui.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.HashSet;

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

    public void removeWindow(Actor window) {
        int indexOfTargetActor = stage.getActors().indexOf(window, true);
        if (indexOfTargetActor > -1) stage.getActors().removeIndex(indexOfTargetActor);
    }

    public int count() {
        return activeActors.size();
    }

}
