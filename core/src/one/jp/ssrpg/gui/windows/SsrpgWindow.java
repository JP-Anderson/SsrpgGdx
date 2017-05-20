package one.jp.ssrpg.gui.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

/**
 * Created by Jp on 12/04/2017.
 */

public abstract class SsrpgWindow extends Window {

    public SsrpgWindow(String title, Skin skin) {
        super(title, new Skin(Gdx.files.internal("uiskin.json")));
    }

    public void setToStandardWindowSize() {
        this.setX(40);
        this.setY(28);
        this.setWidth(720);
        this.setHeight(135);
    }

}
