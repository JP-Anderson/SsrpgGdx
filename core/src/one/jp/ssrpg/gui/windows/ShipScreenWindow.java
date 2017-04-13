package one.jp.ssrpg.gui.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import one.jp.ssrpg.gui.Styles;
import one.jp.ssrpg.gui.utils.WindowManager;

/**
 * Created by Jp on 31/03/2017.
 */

public class ShipScreenWindow extends SsrpgWindow {

    WindowManager windowManager;
    private String currentRunningApplication = "";
    private Table screen;

    public ShipScreenWindow(Skin skin, WindowManager windowManager) {
        super("", skin);
        this.windowManager = windowManager;
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
        if (windowManager.hasWindow(screen)) {
            windowManager.removeWindow(screen);
        }
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
        changeApplication(screenName);

        if (screenName.equals("MAP")) {
            TextButton optionButton = new TextButton("Map screen!", Styles.menuButtonStyle());
            screen.add(optionButton);
            windowManager.drawMapWindow();
        }
        if (screenName.equals("SHIP")) {
            TextButton optionButton = new TextButton("Ship status and overview", Styles.menuButtonStyle());
            windowManager.closeWindow(a -> a instanceof ShipMapScreen);
            screen.add(optionButton);
        }
        if (screenName.equals("CREW")) {
            TextButton optionButton = new TextButton("Crew stats screen!", Styles.menuButtonStyle());
            windowManager.closeWindow(a -> a instanceof ShipMapScreen);
            screen.add(optionButton);
        }
        if (screenName.equals("CARGO")) {
            TextButton optionButton = new TextButton("Cargo hold", Styles.menuButtonStyle());
            windowManager.closeWindow(a -> a instanceof ShipMapScreen);
            screen.add(optionButton);
        }
        windowManager.moveMenuToTop();
    }

    private void changeApplication(String applicationToShow) {
        currentRunningApplication = applicationToShow;
        Label label = this.getTitleLabel();
        label.setText(applicationToShow);
        label.setColor(Color.CORAL);
    }

}
