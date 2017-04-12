package one.jp.ssrpg.gui.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import one.jp.ssrpg.gui.Styles;

/**
 * Created by Jp on 31/03/2017.
 */

public class ShipScreenWindow extends Window {

    private Stage stage;
    private String currentRunningApplication = "";
    private Table screen;

    public ShipScreenWindow(Skin skin, Stage stage) {
        super("", skin);
        this.stage = stage;
        setPosition(400, 100);
        defaults().space(8);
        row().fill().expandX();
        setX(39);
        setY(29);
        setWidth(720);
        setHeight(135);
        setVisible(true);
        setMovable(false);
        setColor(Color.BLACK);
    }

    public void enableScreen(String screenName) {
        removePreviousScreenFromStage();
        if (!currentRunningApplication.equals(screenName)) {
            createNewScreen();
            changeScreen(screenName);
        }
    }

    private void removePreviousScreenFromStage() {
        int screenIndexInStage = stage.getActors().indexOf(screen, true);
        if (screenIndexInStage > -1) stage.getActors().get(stage.getActors().indexOf(screen, true)).remove();
    }

    private void createNewScreen() {
        screen = new Table();
        screen.setX(40);
        screen.setY(28);
        screen.setWidth(720);
        screen.setHeight(135);

        Pixmap pm1 = new Pixmap(1, 1, Pixmap.Format.RGB565);
        pm1.setColor(Color.BLACK);
        pm1.fill();

        screen.setVisible(true);
        screen.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(pm1))));
    }

    private void changeScreen(String screenName) {
        currentRunningApplication = screenName;

        if (screenName.equals("MAP")) {
            TextButton optionButton = new TextButton("Map screen!", Styles.menuButtonStyle());
            screen.add(optionButton);
        }
        if (screenName.equals("SHIP")) {
            TextButton optionButton = new TextButton("Ship status and overview", Styles.menuButtonStyle());
            screen.add(optionButton);
        }
        if (screenName.equals("CREW")) {
            TextButton optionButton = new TextButton("Crew stats screen!", Styles.menuButtonStyle());
            screen.add(optionButton);
        }
        if (screenName.equals("CARGO")) {
            TextButton optionButton = new TextButton("Cargo hold", Styles.menuButtonStyle());
            screen.add(optionButton);
        }
        stage.addActor(screen);
    }

    public void changeApplication(String applicationToShow) {
        currentRunningApplication = applicationToShow;
        Label label = this.getTitleLabel();
        label.setText(applicationToShow);
        label.setColor(Color.CORAL);
    }

}
