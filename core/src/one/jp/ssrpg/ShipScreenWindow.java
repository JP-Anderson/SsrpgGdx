package one.jp.ssrpg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Jp on 31/03/2017.
 */

public class ShipScreenWindow extends Window {

    private String currentRunningApplication;

    public ShipScreenWindow(Skin skin) {
        super("", skin);
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

    public void changeApplication(String applicationToShow) {
        currentRunningApplication = applicationToShow;
        Label label = this.getTitleLabel();
        label.setText(applicationToShow);
        label.setColor(Color.CORAL);
    }

}
